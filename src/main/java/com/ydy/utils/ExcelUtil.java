/**
 * 
 */
package com.ydy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ydy.annotation.ExcelColumn;

/**
 * @author xuzhaojie
 *
 *         2018年12月20日 上午8:43:01
 */
public class ExcelUtil {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static Workbook getWorkbook(File file) throws IOException {
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel&nbsp;2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

	@SuppressWarnings({ "resource" })
	public static <T> boolean writeExcel(List<T> dataList, String finalXlsxPath) throws IOException,
			IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		OutputStream out = null;
		if (dataList == null || dataList.size() <= 0) {
			throw new NullPointerException("dataList can't be empty");
		}

		XSSFWorkbook workBook = new XSSFWorkbook();
		// sheet 对应一个工作页
		XSSFSheet sheet = workBook.createSheet("drugcatalog");
		/**
		 * 删除原有数据，除了属性列
		 */
		int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
		for (; rowNumber > 0; rowNumber--) {
			sheet.removeRowBreak(rowNumber);
		}
		/**
		 * 往Excel中写新数据
		 */
		Class<?> clazz = dataList.get(0).getClass();// 获取泛型类型
		Map<Field, Integer> fieldMap = new HashMap<Field, Integer>();// 用于后面列对应
		Field[] fields = clazz.getDeclaredFields();// 得到类所有字段
		ExcelColumn excelColumn = null;// 注解
		XSSFRow row = sheet.createRow(0);// 创建第一行,row由0开始,为字段列
		Cell cell = null;
		StringBuilder value = null;
		for (Field f : fields) {// 反射类所有字段
			excelColumn = f.getAnnotation(ExcelColumn.class);// 查找这个字段是否有这个注解
			if (excelColumn != null) {// 如果该注解不为空
				fieldMap.put(f, excelColumn.index());// 加入字段对应第index列
				cell = row.createCell(excelColumn.index());// 创建index列的cell
				value = new StringBuilder();
				value.append(excelColumn.value());
				value.append("(" + f.getName() + ")");
				cell.setCellValue(value.toString());// 设置cell的值
			}
		}
		int index = 1;// 创建第二行,row由1开始,为字段列
		Field field = null;
		for (T t : dataList) {
			row = sheet.createRow(index++);// 创建第index行
			for (Entry<Field, Integer> entry : fieldMap.entrySet()) {// 循环map
				cell = row.createCell(entry.getValue());// 创建第value(上面的index)列
				field = entry.getKey();// 得到该字段
				field.setAccessible(true);// 设置private可改
				if (field.get(t) != null) {// 字段值不为空
					cell.setCellValue(field.get(t).toString());// 设置值
				} else {
					cell.setCellValue("");// 设置空
				}
			}
		}
		// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
		if (!FileUtil.createFileIfNotFound(finalXlsxPath))
			return false;
		out = new FileOutputStream(finalXlsxPath);
		workBook.write(out);
		return true;
	}

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public static <T> List<T> readExcel(File file, Class<T> clazz)
			throws InstantiationException, IllegalAccessException, IOException {
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		// 创建输入流，读取Excel
		Workbook wb = getWorkbook(file);
		// Excel的页签数量
		// 每个页签创建一个Sheet对象
		Sheet sheet = wb.getSheetAt(0);// 得到第一个sheet
		int rowTotal = sheet.getLastRowNum();// 看看有sheet有多少行
		List<T> list = new ArrayList<T>(rowTotal - 1);// 0行不是数据
		Field[] fields = clazz.getDeclaredFields();// 反射类所有字段
		Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();// 用于后面列对应
		ExcelColumn excelColumn = null;// 注解
		for (Field f : fields) {// 反射类所有字段
			excelColumn = f.getAnnotation(ExcelColumn.class);// 查找这个字段是否有这个注解
			if (excelColumn != null) {// 如果该注解不为空
				fieldMap.put(excelColumn.index(), f);// 设置列对应字段
			}
		}
		T t = null;
		Row row = null;
		Cell cell = null;
		Field field = null;
		for (int i = 1; i <= rowTotal; i++) {// 遍历所有行，从第二行(index=1)开始
			t = clazz.newInstance();// 构建一个类
			row = sheet.getRow(i);// 得到该行
			for (Entry<Integer, Field> entry : fieldMap.entrySet()) {// 遍历map
				cell = row.getCell(entry.getKey());// 得到第index列数据
				cell.setCellType(CellType.STRING);//设置单元格格式
				field = entry.getValue();// 得到对应的字段
				field.setAccessible(true);// 设置private可改
				field.set(t, cell.getStringCellValue());// 设置值
			}
			list.add(t);
		}
		return list;
	}

}

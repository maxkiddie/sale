package com.ydy.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ydy.vo.other.FileDto;

public class FileUtil {
	private final static Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static String uploadFile(byte[] file, String filePath, String fileName) throws IOException {
		createDirIfNotFound(filePath);
		String f = filePath + File.separator + fileName;
		FileOutputStream out = new FileOutputStream(f);
		out.write(file);
		out.flush();
		out.close();
		return f;
	}

	public static boolean createDirIfNotFound(String dirpath) {
		File file = new File(dirpath);
		if (!file.exists()) {
			log.info("文件路径不存在!");
			if (file.mkdirs()) {
				log.info("文件路径创建成功!创建文件路径:" + dirpath);
			} else {
				log.error("文件路径创建失败!");
				return false;
			}
		}
		return true;
	}

	public static boolean createFileIfNotFound(String filepath) {
		File file = new File(filepath);
		if (!file.exists()) {
			String dirpath = filepath.substring(0, filepath.lastIndexOf(File.separator));
			if (createDirIfNotFound(dirpath)) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					log.error("文件创建失败！" + filepath);
					log.error(e.getMessage(), e);
					return false;
				}
			} else {
				log.error("创建路径失败!" + dirpath);
				return false;
			}
		} else {
			log.info("文件存在!直接修改文件,绝对路径为:" + filepath);
		}
		return true;
	}

	/**
	 * 判断是否符合文件后缀 xuzhaojie -2016-9-14 上午11:54:27
	 */
	public static boolean isFitFormat(String filename, String[] fotmat) {
		// 如果文件名为空或者格式，则直接错误
		if (StringUtils.isNull(filename) || fotmat == null)
			return false;
		String name = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		for (String str : fotmat) {
			if (name.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	public static FileDto getFile(String path) throws FileNotFoundException {
		if (path == null || "".equals(path)) {
			throw new NullPointerException("传入路径为空");
		}
		File file = new File(path);
		FileDto dto = new FileDto();
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		if (file.isDirectory()) {
			dto.setIsFile(0);
			dto.setAbsolutePath(file.getAbsolutePath());
			dto.setFileName(file.getName());
			dto.setParentPath(file.getParent());
			List<FileDto> subList = new LinkedList<FileDto>();
			FileDto temp = null;
			for (File f : file.listFiles()) {
				temp = new FileDto();
				temp.setAbsolutePath(f.getAbsolutePath());
				temp.setFileName(f.getName());
				temp.setIsFile(f.isDirectory() ? 0 : 1);
				temp.setParentPath(f.getParent());
				subList.add(temp);
			}
			dto.setSubList(subList);
		} else {
			dto.setIsFile(1);
			dto.setAbsolutePath(file.getAbsolutePath());
			dto.setFileName(file.getName());
			dto.setParentPath(file.getParent());
			dto.setSubList(null);
		}
		return dto;
	}
}

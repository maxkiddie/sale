/*******************************************************************************
 * 毫秒值转时间
 ******************************************************************************/
function timestampToTime(timestamp) {
	var now = new Date(timestamp);
	y = now.getFullYear(), m = ("0" + (now.getMonth() + 1)).slice(-2),
			d = ("0" + now.getDate()).slice(-2);
	return y + "-" + m + "-" + d + " " + now.toTimeString().substr(0, 8);
}
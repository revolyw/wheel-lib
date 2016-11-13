//输入提示通用方法
//@param limit 限制输入的个数
//@param str 源串
//@param msgdoc 显示提示消息的元素
function limit(limit, str, msgdoc) {
	if(str == undefined) return;
	if(str == null) str = '';
	//计算当前输入个数
	var index = countStr(str);
	var outoflimit = 0;
	if(index > limit) {
		outoflimit = parseInt(index) - parseInt(limit);
		$(msgdoc).html("<strong >您已经<span style='color:red;'>超出</span>了<span style='color:red'>" + outoflimit + "</span>个字</strong>");
	} else {
		$(msgdoc).html("<strong >您已经输入了<span style='color:red;'>" + index + "</span>个字</strong>");
	}
}

//输入字数计算通用方法
//汉字算一个，字母和数字算半个
function countStr(str){
	if(str == '' || str == null || str == undefined) return 0;
	var index = 0;
	for(var i = 0; i < str.length; i++){
		var a = str.substring(i, i + 1);
		var regex = /^[0-9a-zA-Z]{1}/;
		if(regex.test(a))
			index += 0.5;
		else index += 1;
	}
	//进一
	index = parseInt(index + 0.5);
	return index;
}

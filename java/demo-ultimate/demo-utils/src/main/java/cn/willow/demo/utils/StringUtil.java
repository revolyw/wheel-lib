/**
 * 字符串工具类
 * Created by Willow on 16/11/3.
 */
public class StringUtil {

	/**
	 * 字符串判空
	 *
	 * @param str [description]
	 * @return [description]
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 检测字符串是否是移动端UA字符串
	 *
	 * @param userAgent [description]
	 * @return [description]
	 */
	public static boolean isMobile(String userAgent) {
		if (isEmpty(userAgent)) {
			return false;
		}
		userAgent = userAgent.toLowerCase();
		// user-agnet判断
		String isWapBrowser = "noki,eric,amoi,leno,sony,mot-,sec-,sams,black,sie-,shar,amoi,mc21,r380,aur ,up.b,winw,upg1,upsi,qwap,jigs,java,alca,mits,my s,wapj,fetc,alav,wapa,pana,cect,benq,sout,comp,sed-,capi,lg,ctl,nec,tcl,bird,daxi,dbte,east,phil,haie,keji,pant,dopo,zte";
		for (String wapStr : isWapBrowser.split(",")) {
			if (userAgent.startsWith(wapStr)) {
				return true;
			}
		}
		String otherWapString = "wap,phone,mobile,android,symb,midp,mini,opera mobi,nokia,series,armv,palm,up.b,ucweb,iPhone";
		for (String wapStr : otherWapString.split(",")) {
			if (userAgent.indexOf(wapStr) != -1 && userAgent.indexOf("ipad") == -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 计算字词串长度
	 * 英文单词算1个长度
	 *
	 * @param str
	 * @return
	 */
	public static int lengthForWordAndChinese(String str) {
		if (isEmpty(str)) {
			return 0;
		}
		int length = 0;
		str = str.trim();
		String[] splitArray = str.split(" ");
		for (String stringUnit : splitArray) {
			if (isEnglishWord(stringUnit)) {
				length++;
			} else {
				length += stringUnit.length();
			}
		}
		return length;
	}

	/**
	 * 截断字词串
	 * 英文单词算1个词
	 *
	 * @param str
	 * @return
	 */
	public static String substringForWordAndChinese(String str, int limit) {
		if (isEmpty(str)) {
			return "";
		}
		StringBuilder substring = new StringBuilder();
		int length = 0;
		str = str.trim();
		String[] splitArray = str.split(" ");
		for (String stringUnit : splitArray) {
			if (isEnglishWord(stringUnit)) {
				if (++length > limit) {
					//超过limit，返回
					break;
				}
				substring.append(stringUnit).append(" ");
			} else {
				int unitLength = stringUnit.length();
				int redundantOffset = length + unitLength - limit;
				if (redundantOffset > 0) {
					//超limit，处理完返回
					String appendString = stringUnit.substring(0, unitLength - redundantOffset);
					substring.append(appendString);
//                    length += appendString.length();
					break;
				}
				length += unitLength;
				substring.append(stringUnit).append(" ");
			}
		}
		return substring.toString().trim();
	}
}

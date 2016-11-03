/**
 * 字符串工具类
 * Created by Willow on 16/11/3.
 */
public class StringUtil {

    /**
     * 字符串判空
     * @param  str [description]
     * @return     [description]
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 检测字符串是否是移动端UA字符串
     * @param  userAgent [description]
     * @return           [description]
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
}

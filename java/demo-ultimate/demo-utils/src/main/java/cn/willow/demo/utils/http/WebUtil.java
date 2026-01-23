package cn.willow.demo.utils.http;

import cn.willow.demo.utils.StringConst;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * web 开发常用工具
 */
public final class WebUtil {

    /**
     * 编码URL
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                return URLEncoder.encode(url, StringConst.CHARSET_UTF8);
            } catch (Throwable e) {
            }
        }

        return url;
    }

    /**
     * 解码URL
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                return URLDecoder.decode(url, StringConst.CHARSET_UTF8);
            } catch (Throwable e) {
            }
        }

        return url;
    }

}

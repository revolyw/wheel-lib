package cn.willow.demo.utils.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.InputStream;
import java.util.Map;


/**
 * 请求配置类
 *
 */
@Setter
@Getter
public class HttpConfig {
    private HttpConfig() {
    }

    /**
     * 获取实例
     *
     * @return 返回当前对象
     */
    public static HttpConfig custom() {
        return new HttpConfig();
    }

    /**
     * HttpClient对象
     */
    private HttpClient client;

    /**
     * 请求方法
     */
    private HttpRequestBase method;

    /**
     * Header头信息
     */
    private Map<String, Object> headers;

    /**
     * 传递参数
     */
    private Map<String, Object> maps;

    /**
     * 输入编码
     */
    private String inenc = "UTF-8";

    /**
     * 输出编码
     */
    private String outenc = "UTF-8";

    /**
     * 是否使用代理
     */
    private boolean proxy = false;

    /**
     * 解决多线程下载时，stream被close的问题
     */
    private static final ThreadLocal<InputStream> inputs = new ThreadLocal<>();

    /**
     * 解决多线程处理时，url被覆盖问题
     */
    private static final ThreadLocal<String> urls = new ThreadLocal<>();

    /**
     * @param client HttpClient对象
     * @return 返回当前对象
     */
    public HttpConfig client(HttpClient client) {
        this.client = client;
        return this;
    }

    /**
     * @param url 资源url
     * @return 返回当前对象
     */
    public HttpConfig url(String url) {
        urls.set(url);
        return this;
    }

    public HttpConfig url(String url, Map<String, Object> params) {
        if (params == null || params.size() == 0) {
            return url(url);
        }

        StringBuilder sb = new StringBuilder("?");
        params.forEach((key, value) -> sb.append(key)
                .append("=")
                .append(WebUtil.encodeUrl(value.toString()))
                .append("&"));
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        urls.set(url + sb.toString());
        return this;
    }

    /**
     * @param headers Header头信息
     * @return 返回当前对象
     */
    public HttpConfig headers(Map<String, Object> headers) {
        if (this.headers == null || headers == null) {
            this.headers = headers;
        } else {
            this.headers.putAll(headers);
        }
        return this;
    }

    /**
     * @param maps 传递参数
     * @return 返回当前对象
     */
    public HttpConfig maps(Map<String, Object> maps) {
        synchronized (getClass()) {
            if (this.maps == null || maps == null) {
                this.maps = maps;
            } else {
                this.maps.putAll(maps);
            }
        }
        return this;
    }


    public HttpConfig proxy() {
        this.proxy = true;
        return this;
    }

    public HttpConfig get() {
        this.method = new HttpGet();
        return this;
    }

    public HttpConfig post() {
        this.method = new HttpPost();
        return this;
    }

    public HttpConfig put() {
        this.method = new HttpPut();
        return this;
    }

    public HttpConfig patch() {
        this.method = new HttpPatch();
        return this;
    }

    public HttpConfig delete() {
        this.method = new HttpDelete();
        return this;
    }

    public String getUrls() {
        return urls.get();
    }
}

package cn.willow.demo.utils.http;

import cn.willow.demo.utils.io.CloseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http 请求工具
 * @author willow
 * @date 2024/7/17
 */
@Slf4j
public class HttpClientUtil {

    private static final CloseableHttpClient clientHTTP;
    private static final CloseableHttpClient clientHTTPS;
    private static CloseableHttpClient clientHttpProxy;


    /**
     * 连接池大小
     */
    private static final int MAX_TOTAL = 1000;
    /**
     * 每路由的最高并发
     */
    private static final int MAX_PER_ROUTE = 500;

    /**
     * 代理ip和端口
     */
    private static String PROXY_HOST;
    private static int PROXY_PORT;

    public static String getProxyHost() {
        return PROXY_HOST;
    }

    public static int getProxyPort() {
        return PROXY_PORT;
    }

    static {
        clientHTTP = HCB.custom()
                .pool(MAX_TOTAL, MAX_PER_ROUTE)
                .timeout(HCB.CONNECTION_REQUEST_TIMEOUT, HCB.CONNECTION_TIMEOUT, HCB.SOCKET_RIMEOUT)
                .build();

        clientHTTPS = HCB.custom()
                .ssl()
                .pool(MAX_TOTAL, MAX_PER_ROUTE)
                .timeout(HCB.CONNECTION_REQUEST_TIMEOUT, HCB.CONNECTION_TIMEOUT, HCB.SOCKET_RIMEOUT)
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            CloseUtil.closeQuiet(clientHTTP);
            CloseUtil.closeQuiet(clientHTTPS);
            CloseUtil.closeQuiet(clientHttpProxy);
        }));
    }

    public static void initProxy(String hostOrIP, int port) {

        HCB hcb = HCB.custom().ssl();

        if (StringUtils.isNotEmpty(hostOrIP) && port > 0) {
            PROXY_HOST = hostOrIP;
            PROXY_PORT = port;
            hcb.proxy(hostOrIP, port);

            clientHttpProxy = HCB.custom()
                    .proxy(hostOrIP, port)
                    .pool(MAX_TOTAL, MAX_PER_ROUTE)
                    .timeout(HCB.CONNECTION_REQUEST_TIMEOUT, HCB.CONNECTION_TIMEOUT, HCB.SOCKET_RIMEOUT)
                    .build();
        }
    }

    private static void create(HttpConfig config) {
        if (config.getClient() == null) {
            if (config.isProxy()) {
                if (clientHttpProxy == null) {
                    throw new RuntimeException("未指定默认的代理服务器，请使用 initProxy 初始化.");
                }
                config.client((clientHttpProxy));
            } else if (config.getUrls().toLowerCase().startsWith("https://")) {
                config.client(clientHTTPS);
            } else {
                config.client(clientHTTP);
            }
        }
    }

    public static String get(String url) {
        return send(HttpConfig.custom()
                .url(url)
                .get());
    }

    public static String getByProxy(String url) {
        return send(HttpConfig.custom()
                .url(url)
                .proxy()
                .get());
    }


    public static String get(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url, params)
                .get());
    }

    public static String getByProxy(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url, params)
                .proxy()
                .get());
    }

    public static String get(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url, params)
                .headers(headers)
                .get());
    }

    public static String getByProxy(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url, params)
                .headers(headers)
                .proxy()
                .get());
    }

    public static HttpResponse get2Response(String url) throws Exception {
        return send2Response(HttpConfig.custom()
                .url(url)
                .get());
    }

    public static HttpResponse get2ResponseByProxy(String url) throws Exception {
        return send2Response(HttpConfig.custom()
                .url(url)
                .proxy()
                .get());
    }

    public static String getByThrowable(String url) throws Exception {
        return sendByThrowable(HttpConfig.custom()
                .url(url)
                .get());
    }

    public static String getByThrowableByProxy(String url) throws Exception {
        return sendByThrowable(HttpConfig.custom()
                .url(url)
                .proxy()
                .get());
    }

    public static String postByThrowableByProxy(String url, Map<String, Object> params) throws Exception {
        return sendByThrowable(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .post());
    }

    public static String post(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String postByProxy(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .post());
    }

    public static String post(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    public static String postByProxy(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .proxy()
                .post());
    }

    public static String post(String url, byte[] bytes) {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpConst.ENTITY_BYTES, bytes);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String postByProxy(String url, byte[] bytes) {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpConst.ENTITY_BYTES, bytes);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .post());
    }

    public static String post(String url, byte[] bytes, Map<String, Object> headers) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(HttpConst.ENTITY_BYTES, bytes);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    public static String postByProxy(String url, byte[] bytes, Map<String, Object> headers) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(HttpConst.ENTITY_BYTES, bytes);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .proxy()
                .post());
    }

    public static String post(String url, String str) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(HttpConst.ENTITY_STRING, str);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String postByProxy(String url, String str) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(HttpConst.ENTITY_STRING, str);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .post());
    }

    public static String post(String url, String str, Map<String, Object> headers) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(HttpConst.ENTITY_STRING, str);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    public static String postByProxy(String url, String str, Map<String, Object> headers) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(HttpConst.ENTITY_STRING, str);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .proxy()
                .post());
    }

    public static String put(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .put());
    }

    public static String putByProxy(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .put());
    }

    public static String put(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .put());
    }

    public static String putByProxy(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .proxy()
                .put());
    }

    public static String put(String url, byte[] bytes, Map<String, Object> headers) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(HttpConst.ENTITY_BYTES, bytes);
        return put(url, paramsMap, headers);
    }

    public static String putByProxy(String url, byte[] bytes, Map<String, Object> headers) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(HttpConst.ENTITY_BYTES, bytes);
        return putByProxy(url, paramsMap, headers);
    }

    public static String delete(String url) {
        return send(HttpConfig.custom()
                .url(url)
                .delete());
    }

    public static String deleteByProxy(String url) {
        return send(HttpConfig.custom()
                .url(url)
                .proxy()
                .delete());
    }

    public static String delete(String url, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .headers(headers)
                .delete());
    }

    public static String deleteByProxy(String url, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .headers(headers)
                .proxy()
                .delete());
    }

    public static String patch(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .patch());
    }

    public static String patchByProxy(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .patch());
    }

    public static String patch(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .patch());
    }

    public static String patchByProxy(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .proxy()
                .patch());
    }

    public static String upload(String url, String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpConst.ENTITY_FILE, filePath);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String uploadByProxy(String url, String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpConst.ENTITY_FILE, filePath);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .proxy()
                .post());
    }

    public static String upload(String url, Map<String, Object> headers, String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpConst.ENTITY_FILE, filePath);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    public static String uploadByProxy(String url, Map<String, Object> headers, String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put(HttpConst.ENTITY_FILE, filePath);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .proxy()
                .post());
    }

    public static String send(HttpConfig config) {
        try {
            return fmt2String(executeByThrowable(config), config);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage(), e);
        }
        return null;
    }

    public static HttpResponse send2Response(HttpConfig config) throws Exception {
        return executeByThrowable(config);
    }

    public static String sendByThrowable(HttpConfig config) throws Exception {
        return fmt2String(executeByThrowable(config), config);
    }

    private static HttpResponse executeByThrowable(HttpConfig config) throws Exception {
        // 设置client对象
        create(config);
        // 创建请求对象
        HttpRequestBase request = config.getMethod();
        // 设置请求URL
        request.setURI(URI.create(config.getUrls()));
        // 添加headers
        Map<String, Object> headers = config.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                request.addHeader(key, String.valueOf(headers.get(key)));
            }
        }
        // 设置entity
        if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
            List<NameValuePair> nvps = new ArrayList<>();
            //装填参数
            HttpEntity entity = map2HttpEntity(nvps, config.getMaps(), config.getInenc());
            //设置参数到请求对象中
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
        // 执行http请求（第二个参数传null，会使用client设置的defaultRequestConfig）
        return config.getClient().execute(request, (org.apache.http.protocol.HttpContext) null);
    }

    private static HttpEntity map2HttpEntity(List<NameValuePair> nvps, Map<String, Object> map, String inenc) {
        HttpEntity entity = null;
        if (map == null || map.isEmpty()) {
            return null;
        }

        boolean isSpecial = false;
        // 拼接参数
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //判断是否在之中
            if (HttpConst.SPECIAL_ENTITIY.contains(entry.getKey())) {
                isSpecial = true;
                if (HttpConst.ENTITY_STRING.contains(entry.getKey())) {
                    entity = new StringEntity(String.valueOf(entry.getValue()), inenc);
                    break;
                } else if (HttpConst.ENTITY_BYTES.equals(entry.getKey())) {
                    entity = new ByteArrayEntity((byte[]) entry.getValue());
                    break;
                } else if (HttpConst.ENTITY_FILE.equals(entry.getKey())) {
                    if (File.class.isAssignableFrom(entry.getValue().getClass())) {
                        entity = new FileEntity((File) entry.getValue(), ContentType.APPLICATION_OCTET_STREAM);
                    } else if (entry.getValue().getClass() == String.class) {
                        entity = new FileEntity(new File((String) entry.getValue()), ContentType.create("text/plain", "UTF-8"));
                    }
                    break;
                }
            } else {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            try {
                if (!isSpecial) {
                    entity = new UrlEncodedFormEntity(nvps, inenc);
                }
            } catch (UnsupportedEncodingException e) {
                log.error("error: {}", e.getMessage(), e);
            }
        }
        return entity;
    }

    private static String fmt2String(HttpResponse resp, HttpConfig config) {
        String body = "";
        try {
            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), config.getOutenc());
            } else {
                //有可能是head请求
                body = resp.getStatusLine().toString();
            }
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            log.error("error: {}", e.getMessage(), e);
        } finally {
            close(resp);
        }
        return body;
    }

    private static void close(HttpResponse resp) {
        try {
            if (resp == null) {
                return;
            }
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}

package cn.willow.demo.utils.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The SSL protocol version
 *
 */
@AllArgsConstructor
@Getter
public enum SSLProtocolVersion {
    SSL("SSL"),
    SSLv3("SSLv3"),
    TLSv1("TLSv1"),
    TLSv1_1("TLSv1.1"),
    TLSv1_2("TLSv1.2");

    private final String name;

    public static SSLProtocolVersion find(String name) {
        for (SSLProtocolVersion pv : SSLProtocolVersion.values()) {
            if (pv.getName().equalsIgnoreCase(name)) {
                return pv;
            }
        }
        throw new RuntimeException("未支持当前ssl版本号：" + name);
    }
}

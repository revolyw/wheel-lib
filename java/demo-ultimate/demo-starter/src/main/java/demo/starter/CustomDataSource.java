package demo.starter;

/**
 * @author willow
 * @since 2024/12/17
 */
public class CustomDataSource {
    private String host;
    private String port;

    public CustomDataSource() {
    }

    public CustomDataSource(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

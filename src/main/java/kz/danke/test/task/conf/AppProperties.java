package kz.danke.test.task.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Web web = new Web();

    public static class Web {

        private String hostname;

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }

    public Web getWeb() {
        return web;
    }
}

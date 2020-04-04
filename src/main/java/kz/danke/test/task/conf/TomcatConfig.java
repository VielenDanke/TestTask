package kz.danke.test.task.conf;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig implements TomcatConnectorCustomizer {

    @Override
    public void customize(Connector connector) {
        connector.setAttribute("relaxedQueryChars", "[]|{}^&#x5c;&#x60;&quot;&lt;&gt;");
        connector.setAttribute("relaxedPathChars", "[]|");
    }
}

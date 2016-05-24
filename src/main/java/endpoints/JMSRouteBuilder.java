package endpoints;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.stereotype.Component;

@Component
public class JMSRouteBuilder extends FatJarRouter {

    @Override
    public void configure() throws Exception {
        from("direct:sayHello")
                .setBody(constant("Hello"))
                .loadBalance()
                .failover(3, false, true)
                .inOut("jms:requestQ1?" +
                                "replyTo=responseQ1&" +
                                "useMessageIDAsCorrelationID=true&" +
                                "requestTimeout=1s",
                        "jms:requestQ2?" +
                                "replyTo=responseQ2&" +
                                "useMessageIDAsCorrelationID=true&" +
                                "requestTimeout=1s")
                .end()
                .setBody(constant("Hello Back"));
    }
}

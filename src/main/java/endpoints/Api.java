package endpoints;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by leekong on 25/09/15.
 */
@RestController
@RequestMapping(value = "/endpoints", produces = {APPLICATION_JSON_VALUE})
public class Api {

    @Produce(uri = "direct:sayHello")
    private ProducerTemplate sayHelloProducer;

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    private void sayHello() {
        sayHelloProducer.requestBody("Hello");
    }
}

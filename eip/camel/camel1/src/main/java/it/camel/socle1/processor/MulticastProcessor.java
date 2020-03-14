package it.camel.socle1.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

public class MulticastProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		ProducerTemplate producer = exchange.getContext().createProducerTemplate();
		producer.send("direct:endpoint", exchange);
		if(exchange.getException() != null) {
		System.out.println("exception has oocured in MulticastProcessor");
		} else {
			exchange.setIn(in);
			producer.send("amq:queue:output", exchange);
		}
		
	}

}

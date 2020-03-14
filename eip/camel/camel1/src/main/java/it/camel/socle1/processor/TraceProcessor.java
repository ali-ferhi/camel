package it.camel.socle1.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

public class TraceProcessor implements Processor {
	
	private String step;
	
	private String state;
	
	public TraceProcessor(String step) {
		this.step = step;
	}

	public void process(Exchange exchange) throws Exception {
		
		ProducerTemplate producer = exchange.getContext().createProducerTemplate();
		String in = exchange.getIn().getBody(String.class);
		String trace = "trace : \n"
						+ "\tstep : " + step + "\n"
						+ "\tstate : " + state + "\n"
						+ "\tmessage : " + in;
		producer.sendBody("amq:queue:trace", trace);
		exchange.getOut().setBody(in);		
	}
	
	public void setState(String state) {
		this.state = state;
	}

}

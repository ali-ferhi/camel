package it.camel.socle1.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TranfoProcessor implements Processor {
	
	private TraceProcessor traceProcessor = new TraceProcessor("transfo");
	
	public void process(Exchange exchange) throws Exception {
		String in = exchange.getIn().getBody(String.class);
		if("transfo KO".equals(in)) {
			traceProcessor.setState("KO");
			traceProcessor.process(exchange);
			throw new Exception();
		}		
		String out = "*** " + in + " ***";		
		traceProcessor.setState("OK");
		traceProcessor.process(exchange);
		exchange.getOut().setBody(out);
	}

}

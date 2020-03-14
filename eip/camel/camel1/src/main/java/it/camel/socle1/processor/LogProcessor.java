package it.camel.socle1.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LogProcessor implements Processor {
	
	private String msg;
	
	public LogProcessor(String msg) {
		this.msg = msg;
	}
	public void process(Exchange exchange) throws Exception {
		System.out.println(msg);
		
	}

}

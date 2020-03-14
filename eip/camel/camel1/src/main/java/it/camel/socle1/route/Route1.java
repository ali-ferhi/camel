package it.camel.socle1.route;

import org.apache.camel.builder.RouteBuilder;

import it.camel.socle1.processor.LogProcessor;
import it.camel.socle1.processor.MulticastProcessor;
import it.camel.socle1.processor.TraceProcessor;

public class Route1 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
		.handled(true)
		.process(new LogProcessor("exception has oocured in Route1"));
		from("amq:queue:input")
		.process(new TraceProcessor("input"))
		/*.multicast()
		.to("amq:queue:output","direct:endpoint");*/
		.process(new MulticastProcessor())
		.end();
	}

}

package it.camel.socle1.route;

import org.apache.camel.builder.RouteBuilder;

import it.camel.socle1.processor.LogProcessor;
import it.camel.socle1.processor.TranfoProcessor;

public class Route2 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		/*onException(Exception.class)
		.handled(false)
		.process(new LogProcessor("exception has oocured in Route2"));*/
		from("direct:endpoint")
		.errorHandler(noErrorHandler())
		.process(new TranfoProcessor())
		.to("amq:queue:output");
		
	}

}

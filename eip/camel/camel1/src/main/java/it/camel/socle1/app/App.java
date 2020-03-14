package it.camel.socle1.app;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.jms.connection.JmsTransactionManager;

import it.camel.socle1.route.Route1;
import it.camel.socle1.route.Route2;

public class App {

	public static void main(String[] args) {
		
		CamelContext context = new DefaultCamelContext();
		
		ActiveMQComponent amqCpt = new ActiveMQComponent();
		
		ActiveMQConnectionFactory amqConnectFactory = new ActiveMQConnectionFactory();
		amqConnectFactory.setBrokerURL("tcp://localhost:61616");
		amqConnectFactory.setUserName("admin");
		amqConnectFactory.setPassword("admin");
		
		JmsConfiguration jmsConf = new JmsConfiguration();
		JmsTransactionManager jmsTxManager = new JmsTransactionManager();
		jmsTxManager.setConnectionFactory(amqConnectFactory);
		
		jmsConf.setConnectionFactory(amqConnectFactory);
		jmsConf.setTransacted(true);
		jmsConf.setTransactionManager(jmsTxManager);
		
		amqCpt.setConfiguration(jmsConf);
		
		SpringTransactionPolicy txPolicy = new SpringTransactionPolicy();
		txPolicy.setTransactionManager(jmsTxManager);
		
		context.addComponent("amq", amqCpt);
		try {
			context.addRoutes(new Route1());
			context.addRoutes(new Route2());
			context.start();
			System.out.println("Camel engine is started.");
			//Thread.sleep(10000);

		} catch (Exception e) {
			System.out.println("Error on starting Camel engine.");
			e.printStackTrace();
		} finally {
			try {
				context.stop();
				System.out.println("Camel engine is stopped.");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}

	}

}

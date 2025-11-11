
package it.asl.teramo.healthmiddleware.hl7.route;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.message.MDM_T10;
import ca.uhn.hl7v2.model.v251.message.MDM_T02;
import ca.uhn.hl7v2.model.v251.message.OML_O21;
import ca.uhn.hl7v2.model.v251.message.QBP_Q11;
import ca.uhn.hl7v2.model.v251.message.QBP_Q21;
import ca.uhn.hl7v2.parser.Parser;
import it.asl.teramo.healthmiddleware.hl7.service.interfaces.message.HL7InboudHL7251;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Camel route that exposes an HTTP endpoint (Undertow) to receive HL7 v2.5.1 messages in XML.
 * Port and path are configured via application properties
 */
@Component
public class HL7251ReceiverRoute extends RouteBuilder {

    @Value("${hl7.receiver.port}")
    private String port;

    @Value("${hl7.receiver.path}")
    private String path;

    @Value("${hl7.receiver.url}")
    private String url;

    final
    HL7InboudHL7251 hl7InboudHL7251;

    public HL7251ReceiverRoute(HL7InboudHL7251 hl7InboudHL7251) {
        this.hl7InboudHL7251 = hl7InboudHL7251;
    }


    @Override
    public void configure() {
        // Global error handling: return 500 with message
        onException(Exception.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .setBody(simple("<ACK><status>ERROR</status><message>${exception.message}</message></ACK>"));

        // Undertow HTTP endpoint restricted to POST; consumes any content-type but expects XML body
        from("undertow:"+ url +":"+ port + path + "51" + "?httpMethodRestrict=POST")
                .convertBodyTo(String.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String payload = exchange.getIn().getBody(String.class);
                        HapiContext hapiContext = new DefaultHapiContext();
                        Parser xmlParser = hapiContext.getXMLParser();
                        Message message = xmlParser.parse(payload);
                        String ackresponse = "";
                        switch (message) {
                            case QBP_Q11 receivedMessage -> ackresponse = hl7InboudHL7251.receiver(receivedMessage).toString();
                            case QBP_Q21 receivedMessage -> ackresponse = hl7InboudHL7251.receiver(receivedMessage).toString();
                            case MDM_T02 receivedMessage -> ackresponse = hl7InboudHL7251.receiver(receivedMessage).toString();
                            case OML_O21 receivedMessage -> ackresponse = hl7InboudHL7251.receiver(receivedMessage).toString();
                            case MDM_T10 receivedMessage -> ackresponse = hl7InboudHL7251.receiver(receivedMessage).toString();
                            default -> ackresponse = "<ACK><status>ERROR</status><message>${exception.message}</message></ACK>";
                        }
                        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 202);
                        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/xml");
                        exchange.getMessage().setBody(ackresponse);
                    }
                });
    }
}
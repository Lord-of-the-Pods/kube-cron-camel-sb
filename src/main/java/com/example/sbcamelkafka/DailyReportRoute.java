package com.example.sbcamelkafka;

import com.sun.istack.ByteArrayDataSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import java.util.Base64;

@Component
public class DailyReportRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        Processor processor = new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

                exchange.getIn().setBody(new String(Base64.getDecoder()
                        .decode(exchange.getIn().getBody(String.class))));

                String attchementContent =  exchange.getIn().getHeader("ics" , String.class);

                attchementContent = String.format(attchementContent ,
                        exchange.getIn().getHeader("meetingTimeFrom" , String.class) ,
                        exchange.getIn().getHeader("meetingTimeTo" , String.class) ,
                        exchange.getIn().getHeader("meetingLocation" , String.class) ,
                        exchange.getIn().getHeader("meetingLocation" , String.class));

                AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);

                attMsg.addAttachment("meeting-invite.ics",
                        new DataHandler( new ByteArrayDataSource(attchementContent.getBytes(),
                                "text/plain")));


            }
        };

        /**
         * A route for report generation , both Rest Request
         * and the Scheduler converges to this route
         */
        from("timer://dailyReportGenerationTimer?repeatCount=1")
                .routeId("startDailyReportGeneration")
                .log("Fetching Data from the Database");
                //.to("kafka:{{topic}}?brokers={{broker}}");
        
    }
}

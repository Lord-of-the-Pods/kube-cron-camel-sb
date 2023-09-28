package com.example.sbcamelkafka;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DailyReportRoute extends RouteBuilder {

    Processor processor = new ReportProcessor();

    @Override
    public void configure() throws Exception {


        /**
         * A route for report generation , both Rest Request
         * and the Scheduler converges to this route
         */
        from("timer://dailyReportGenerationTimer?repeatCount=1")
                .routeId("startDailyReportGeneration")
                .log("----Fetching Data from the Database")
                .to("sql:{{meeting.data.sql}}?outputClass=com.example.sbcamelkafka.Meeting")
                .process(processor)
                .setHeader("From").simple("abhishek.vishnoi@hotmail.com")
                .setHeader("To").simple("client@hotmail.com")
                .setHeader("Subject").simple("meeting report")
                .setBody(simple("hi please ifnd meeting report!"))
                .log("Sending Email Message to ${headers[To]} cc to ${headers[Cc]}")
                .to("smtp://{{smtp.server}}:{{smtp.port}}?contentType=text/html");;

    }
}

package com.example.sbcamelkafka;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DailyReportRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        /**
         * A route for report generation , both Rest Request
         * and the Scheduler converges to this route
         */
        from("timer://myTimer?repeatCount=1")
                .routeId("myTimer")
                .log("-- Running the Flow exactly once --");

    }
}

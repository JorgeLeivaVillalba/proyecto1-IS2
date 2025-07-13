package com.example.proyecto1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("activemq:queue:Leiva-ITAU-IN")
            .log("ITAU procesó: ${body}");

        from("activemq:queue:Leiva-ATLAS-IN")
            .log("ATLAS procesó: ${body}");

        from("activemq:queue:Leiva-FAMILIAR-IN")
            .log("FAMILIAR procesó: ${body}");
    }
}
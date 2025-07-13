package com.example.proyecto1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TransferRoute extends RouteBuilder {

    private final String[] bancos = {"ITAU", "ATLAS", "FAMILIAR"};
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure() {
        // Genera un mensaje cada 3 segundos
        from("timer:transferTimer?period=3000")
            .process(exchange -> {
                String bancoOrigen = bancos[random.nextInt(bancos.length)];
                String bancoDestino;
                do {
                    bancoDestino = bancos[random.nextInt(bancos.length)];
                } while (bancoDestino.equals(bancoOrigen));

                Transferencia transferencia = new Transferencia();
                transferencia.setCuenta(String.valueOf(1000 + random.nextInt(4001)));
                transferencia.setMonto(1000 + random.nextInt(4001));
                transferencia.setBanco_origen(bancoOrigen);
                transferencia.setBanco_destino(bancoDestino);

                String json = objectMapper.writeValueAsString(transferencia);
                exchange.getIn().setBody(json);
                exchange.getIn().setHeader("bancoDestino", bancoDestino);
            })
            .toD("activemq:queue:Leiva-${header.bancoDestino}-IN")
            .log("Enviado a Leiva-${header.bancoDestino}-IN: ${body}");
    }
}
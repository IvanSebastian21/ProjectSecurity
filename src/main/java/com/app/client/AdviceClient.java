package com.app.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class AdviceClient {
    private final RestTemplate restTemplate;
    private final String basePath;
    private final String confirmationPath;
    private final String cancellationPath;

    public AdviceClient(final RestTemplate restTemplate,
                        @Value("Http://localhost:9000") String basePath, //host donde pedimos el servicio
                        @Value("/confirmation") String confirmationPath,
                        @Value("/cancellation") String cancellationPath) {
        this.restTemplate = restTemplate;
        this.basePath = basePath;
        this.confirmationPath = confirmationPath;
        this.cancellationPath = cancellationPath;
    }

    public void sendConfirmation(final Object object, final Object objectTwo) {

        final Object send = new Object();
        /* Logica de creacion de datos a transferir por dto´s
         * E.g:
         * final sendAdviceConfirmRequestDto  request = new sendAdviceConfirmRequestDto();
         * request.setAny(object.getAny);
         * request.setAnyTwo(objectTwo.getAnyTwo);
         */

        final HttpEntity<Object> requestEntity = new HttpEntity<>(send, null);

        final ResponseEntity<Object> responseEntity = restTemplate.exchange(URI.create(basePath + confirmationPath),
                HttpMethod.POST, requestEntity,
                Object.class);
    }

    public void sendCancelation(final Object object, final Object objectTwo) {

        final Object send = new Object();
        /* Logica de creacion de datos a transferir por dto´s
         * E.g:
         * final sendAdviceCancelRequestDto  request = new sendAdviceCancelRequestDto();
         * request.setAny(object.getAny);
         * request.setAnyTwo(objectTwo.getAnyTwo);
         */

        final HttpEntity<Object> requestEntity = new HttpEntity<>(send, null);

        final ResponseEntity<Object> responseEntity = restTemplate.exchange(URI.create(basePath + cancellationPath),
                HttpMethod.POST, requestEntity,
                Object.class);
    }

}

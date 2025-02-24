package com.actividad.actividad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class SaludoController {

    private final MessageSource messageSource;

    @Autowired
    public SaludoController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/saludo")
    public Mono<String> obtenerSaludo(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return Mono.just(
                messageSource.getMessage("saludo", null, locale != null ? locale : Locale.ENGLISH)
        );
    }
}
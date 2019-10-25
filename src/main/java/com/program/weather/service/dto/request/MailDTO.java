package com.program.weather.service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * The type Mail dto.
 */
@Setter
@Getter
public class MailDTO {

    private String from;

    private String to;

    private String subject;

    private Map<String, Object> model;

    /**
     * Instantiates a new Mail dto.
     */
    public MailDTO() {

    }
}

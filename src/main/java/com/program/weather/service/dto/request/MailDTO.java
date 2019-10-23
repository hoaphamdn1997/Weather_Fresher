package com.program.weather.service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class MailDTO {

    private String from;

    private String to;

    private String subject;

    private Map<String, Object> model;

    public MailDTO() {

    }
}

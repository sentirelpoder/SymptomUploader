package com.gerimedica.ontimecoding.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {

    String message;

    String exceptionMessage;
}

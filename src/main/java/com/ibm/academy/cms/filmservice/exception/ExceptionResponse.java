package com.ibm.academy.cms.filmservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ExceptionResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7645574770781355007L;

    private String message;
}
package com.project.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum State {
    CONFIRMED,
    PENDING, //TODO:Pending?
    ANNULLED

}

package com.projects.learning.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum ShipStatus {
    OPERATIONAL,
    UNDER_REPAIR,
    DECOMMISSIONED;

    @JsonCreator
    public static ShipStatus from(String value) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "El estado debe ser uno de: " + Arrays.toString(values())
                ));
    }

}

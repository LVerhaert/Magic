package liza.stage.springtest.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Color {
    @JsonProperty("U")
    blue,
    @JsonProperty("G")
    green,
    @JsonProperty("W")
    white,
    @JsonProperty("R")
    red,
    @JsonProperty("B")
    black

}

package liza.stage.springtest.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Frame {
    @JsonProperty("1993")
    y1993,
    @JsonProperty("1997")
    y1997,
    @JsonProperty("2003")
    y2003,
    @JsonProperty("2015")
    y2015,
    future,
}

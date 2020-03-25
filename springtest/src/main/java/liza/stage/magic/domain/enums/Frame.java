package liza.stage.magic.domain.enums;

import java.util.stream.Stream;

public enum Frame {
    y1993("1993"),
    y1997("1997"),
    y2003("2003"),
    y2015("2015"),
    future("future"),
    ;

    private final String name;

    Frame(String name) {
        this.name = name;
    }

    public static Frame fromString(final String name) {
        return Stream.of(Frame.values())
                .filter(value -> value.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The Frame with name: " + name + " does not exist"));
    }
}

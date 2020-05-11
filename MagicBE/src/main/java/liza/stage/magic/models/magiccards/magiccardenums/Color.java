package liza.stage.magic.models.magiccards.magiccardenums;


import java.util.stream.Stream;

public enum Color {
    blue("U"),
    green("G"),
    white("W"),
    red("R"),
    black("B");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public static Color fromString(final String code) {
        return Stream.of(Color.values())
                .filter(value -> value.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The Color with code " + code + " does not exist"));
    }

}

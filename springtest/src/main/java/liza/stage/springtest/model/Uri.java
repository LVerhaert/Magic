package liza.stage.springtest.model;

import lombok.Data;

@Data
public class Uri {
    String uri;

    public Uri(String uri) {
        this.uri = uri;
    }
}

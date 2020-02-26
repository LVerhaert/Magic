
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import liza.stage.springtest.model.enums.Component;
import liza.stage.springtest.model.enums.ObjectType;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class RelatedCard {

    private Component component;
    private String id;
    private String name;
    private ObjectType object;
    @JsonProperty("type_line")
    private String typeLine;
    private String uri;

}

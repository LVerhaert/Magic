
package liza.stage.springtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import liza.stage.springtest.model.enums.Component;
import liza.stage.springtest.model.enums.ObjectType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RelatedCard {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    private String idString;
    private Component component;
    private String name;
    private ObjectType object;
    @JsonProperty("type_line")
    private String typeLine;
    private String uri;

}

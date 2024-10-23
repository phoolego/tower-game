package backend.poc.tower_game.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TowerFloor {

    @JsonProperty("operation")
    @Pattern(regexp = "^[+\\-*/!]$", message = "Invalid operation character must be [+, -, *, /, !]")
    @Size(max = 1, min = 1, message = "Input only 1 character")
    private String operation;

    @JsonProperty("power")
    private Integer power;
}

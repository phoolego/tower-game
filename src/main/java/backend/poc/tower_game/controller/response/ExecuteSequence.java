package backend.poc.tower_game.controller.response;

import backend.poc.tower_game.controller.request.TowerFloor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecuteSequence {

    @JsonProperty("floor_sequence")
    List<TowerFloor> floorSequence;

    @JsonProperty("power_output")
    Integer powerOutput;

}

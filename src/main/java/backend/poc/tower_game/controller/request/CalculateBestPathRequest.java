package backend.poc.tower_game.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculateBestPathRequest {

    @JsonProperty("initial_power")
    private Integer initialPower;

    @JsonProperty("towers")
    @Valid
    private ArrayList<@Valid List<@Valid TowerFloor>> towers;

}

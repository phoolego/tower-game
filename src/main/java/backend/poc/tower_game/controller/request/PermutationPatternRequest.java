package backend.poc.tower_game.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermutationPatternRequest {

    @JsonProperty("n")
    private Integer n;

    @JsonProperty("r")
    private Integer r;

    @JsonProperty("max_run")
    private Integer maxRun;
}

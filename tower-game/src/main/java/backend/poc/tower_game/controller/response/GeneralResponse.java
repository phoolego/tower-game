package backend.poc.tower_game.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneralResponse<T> {

    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private T data;

}

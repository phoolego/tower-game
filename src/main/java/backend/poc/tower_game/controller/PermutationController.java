package backend.poc.tower_game.controller;

import backend.poc.tower_game.controller.request.CalculateBestPathRequest;
import backend.poc.tower_game.controller.request.PermutationPatternRequest;
import backend.poc.tower_game.controller.response.CalculateBestPathResponse;
import backend.poc.tower_game.controller.response.GeneralResponse;
import backend.poc.tower_game.factory.ResponseFactory;
import backend.poc.tower_game.service.PermutationService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class PermutationController {
    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private PermutationService permutationService;

    @PostMapping("/permutation/pattern")
    public ResponseEntity<GeneralResponse<Integer[][]>> calculateBestPathBasic(@Valid @RequestBody PermutationPatternRequest request) {
        Integer[][] result = permutationService.getPermutationPattern(request.getN(), request.getR());
        return responseFactory.success(result);
    }
}

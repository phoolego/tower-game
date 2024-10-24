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
        try{
            log.info("=== Start generate permutation pattern ===");
            Integer[][] result;
            if (request.getMaxRun() == null){
                result = permutationService.getPermutationPattern(request.getN(), request.getR());
            } else {
                result = permutationService.getPermutationPattern(request.getN(), request.getR(), request.getMaxRun());
            }
            return responseFactory.success(result);
        } finally {
            log.info("=== End generate permutation pattern ===");
        }
    }
}

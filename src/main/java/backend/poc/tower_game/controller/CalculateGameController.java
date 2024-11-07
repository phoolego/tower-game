package backend.poc.tower_game.controller;

import backend.poc.tower_game.controller.request.CalculateBestPathRequest;
import backend.poc.tower_game.controller.request.TowerFloor;
import backend.poc.tower_game.controller.response.CalculateBestPathResponse;
import backend.poc.tower_game.controller.response.ExecuteSequence;
import backend.poc.tower_game.controller.response.GeneralResponse;
import backend.poc.tower_game.factory.ResponseFactory;
import backend.poc.tower_game.service.CalculateGameService;
import backend.poc.tower_game.util.StringFormatUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@RestController
public class CalculateGameController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private CalculateGameService calculateGameService;

    @PostMapping("/calculate-best-path/basic")
    public ResponseEntity<GeneralResponse<CalculateBestPathResponse>> calculateBestPathBasic(@Valid @RequestBody CalculateBestPathRequest request) {
        try {
            log.info("=== Start process calculate best path for tower ===");
            this.logRequestDetail(request);
            CalculateBestPathResponse response = calculateGameService.calculateBestPathBasic(request);
            return responseFactory.success(response);
        } finally {
            log.info("=== End process calculate best path for tower ===");
        }
    }

    private void logRequestDetail(CalculateBestPathRequest request) {
        String floors = "";
        for (List<TowerFloor> tower : request.getTowers()) {
            floors = floors.concat(String.format("[%d]", tower.size()));
        }
        log.info("request got [{}] tower(s) with floors {}", request.getTowers().size(), floors);
    }

    private TowerFloor[][] setUpTowerFloorPermutation(List<TowerFloor> tower) {
        int towerSize = tower.size();
        log.info("start build permutation choice of tower size[{}]", towerSize);
        TowerFloor[][] towerFloorsPerm = new TowerFloor[towerSize][towerSize];
        StringBuilder displayTowerFloorsPerm = new StringBuilder();
        for (int choice = 0; choice < towerSize; choice++) {
            for (int floorSequence = 0; floorSequence < towerSize; floorSequence++) {
                towerFloorsPerm[choice][floorSequence] = tower.get(floorSequence);
            }
            displayTowerFloorsPerm.append("\n").append(StringFormatUtil.arrayToString(towerFloorsPerm[choice]));
        }
        log.info("permutation choice of tower{}", displayTowerFloorsPerm.toString());
        return towerFloorsPerm;
    }

    public ExecuteSequence findMostPowerOutputSequence(TowerFloor[][] towerFloors) {
        ExecuteSequence maxPowerSequence = new ExecuteSequence();
        maxPowerSequence.setPowerOutput(0);

        ExecuteSequence tempSequence = new ExecuteSequence();
        tempSequence.setFloorSequence(new ArrayList<>());
        tempSequence.setPowerOutput(0);

        int maxRound = towerFloors.length * towerFloors[0].length;
        for (int round = 0; round < maxRound; round++) {
            log.info("find most power output sequence round[{}]", round);

            for (int choice = 0; choice < towerFloors.length; choice++) {
                Set<Integer> pickedFloor = new HashSet<>();
                for (int floor = 0; floor < towerFloors[choice].length; floor++) {
                    if (pickedFloor.contains(floor)){
                        continue;
                    }
                }
//                towerFloorsPerm[choice][floorSequence] = tower.get(floorSequence);
            }
//            displayTowerFloorsPerm.append("\n").append(arrayToString(towerFloorsPerm[choice]));
        }

        return maxPowerSequence;
    }

}

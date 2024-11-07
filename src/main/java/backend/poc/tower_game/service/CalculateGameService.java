package backend.poc.tower_game.service;

import backend.poc.tower_game.controller.request.CalculateBestPathRequest;
import backend.poc.tower_game.controller.request.TowerFloor;
import backend.poc.tower_game.controller.response.CalculateBestPathResponse;
import backend.poc.tower_game.controller.response.ExecuteSequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class CalculateGameService {

    @Autowired
    private FloorOperationService floorOperationService;

    public CalculateBestPathResponse calculateBestPathBasic(CalculateBestPathRequest request) {
        List<ExecuteSequence> bestExecuteSequence = new ArrayList<>();
        Integer currentPower = request.getInitialPower();

        for (int currentTower = 0; currentTower < request.getTowers().size(); currentTower++) {
            List<Integer> pickingPool = new ArrayList<>();
            for (int floor = 0; floor < request.getTowers().get(currentTower).size(); floor++) {
                pickingPool.add(floor);
            }
            log.info("process tower[{}]", currentTower);
            ExecuteSequence bestExecuteSequenceOfTower = findBestFloor(request.getTowers().get(currentTower), currentPower, pickingPool);
            currentPower = bestExecuteSequenceOfTower.getPowerOutput();
            bestExecuteSequence.add(currentTower, bestExecuteSequenceOfTower);
        }

        CalculateBestPathResponse response = new CalculateBestPathResponse();
        response.setExecuteSequenceList(bestExecuteSequence);
        return response;
    }

    private ExecuteSequence findBestFloor(List<TowerFloor> towerFloors, Integer initialPower, List<Integer> pickingPool) {
        ExecuteSequence maxOutputPower = new ExecuteSequence();
        maxOutputPower.setPowerOutput(0);
        maxOutputPower.setFloorSequence(new ArrayList<>());

        for (int currentFloor = 0; currentFloor < pickingPool.size(); currentFloor++) {
            TowerFloor processFloor = towerFloors.get(pickingPool.get(currentFloor));
            log.info("process floor[{}]", pickingPool.get(currentFloor));
            int power = floorOperationService.executeFloor(initialPower, processFloor);
            if (power > 0 && pickingPool.size() > 1) {
                final int finalCurrentFloor = currentFloor;
                List<Integer> newPickingPool = pickingPool.stream().filter(n -> n != finalCurrentFloor).toList();
                ExecuteSequence executeSequence = this.findBestFloor(towerFloors, power, newPickingPool);

                if (executeSequence.getPowerOutput() > maxOutputPower.getPowerOutput()) {
                    List<TowerFloor> newExecuteSequence = new ArrayList<>();
                    newExecuteSequence.add(processFloor);
                    newExecuteSequence.addAll(executeSequence.getFloorSequence());
                    maxOutputPower.setFloorSequence(newExecuteSequence);
                    maxOutputPower.setPowerOutput(executeSequence.getPowerOutput());
                }
            } else if (power > 0) {
                List<TowerFloor> newExecuteSequence = new ArrayList<>();
                newExecuteSequence.add(processFloor);
                maxOutputPower.setFloorSequence(newExecuteSequence);
                maxOutputPower.setPowerOutput(power);
            }
        }
        return maxOutputPower;
    }
}

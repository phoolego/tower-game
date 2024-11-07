package backend.poc.tower_game.service;

import backend.poc.tower_game.controller.request.TowerFloor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FloorOperationService {
    private Integer plus(Integer heroPower, Integer floorPower) {
        return heroPower + floorPower;
    }

    private Integer minus(Integer heroPower, Integer floorPower) {
        return heroPower - floorPower;
    }

    private Integer multiply(Integer heroPower, Integer floorPower) {
        return heroPower * floorPower;
    }

    private Integer divide(Integer heroPower, Integer floorPower) {
        return heroPower / floorPower;
    }

    private Integer battle(Integer heroPower, Integer floorPower) {
        return heroPower > floorPower ? plus(heroPower, floorPower) : minus(heroPower, floorPower);
    }

    public Integer executeFloor(Integer heroPower, TowerFloor towerFloor) {
        Integer resultPower = switch (towerFloor.getOperation()) {
            case "+" -> plus(heroPower, towerFloor.getPower());
            case "-" -> minus(heroPower, towerFloor.getPower());
            case "*" -> multiply(heroPower, towerFloor.getPower());
            case "/" -> divide(heroPower, towerFloor.getPower());
            case "!" -> battle(heroPower, towerFloor.getPower());
            default -> -1;
        };
        log.info("execute floor power result of power[{}] on floor setting operation[{}] power[{}], is [{}]", heroPower, towerFloor.getOperation(), towerFloor.getPower(), resultPower);
        return resultPower;
    }
}

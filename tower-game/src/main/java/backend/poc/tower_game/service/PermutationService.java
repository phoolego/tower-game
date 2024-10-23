package backend.poc.tower_game.service;

import backend.poc.tower_game.util.StringFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Service
public class PermutationService {

    public Integer calculatePermutation(Integer n, Integer r) {
        if (r > n) {
            return 0;
        }
        int start = n - r + 1;
        int result = 1;
        for (; start <= n; start++) {
            result *= start;
        }
        log.info("permutation of n={} r={} is {}", n, r, result);
        return result;
    }

    public Integer factorial(Integer n) {
        if (n < 1) {
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public Integer[][] getPermutationPattern(Integer n, Integer r) {
        log.info("start generate permutation pattern");
        Integer permutation = this.calculatePermutation(n, r);

        StringBuilder displayPermutationPattern = new StringBuilder();

        Integer[][] permutationPattern = new Integer[permutation][r];

        for (int choose = 0; choose < r; choose++) {
            int shiftNumber = 0;
            int groupRange = permutation * factorial(n - choose - 1) / factorial(n);
            int previousGroupRange = 0;
            if (choose != 0) {
                previousGroupRange = permutation * factorial(n - choose) / factorial(n);
            }
            boolean isSetShiftNumber = false;
            Queue<Integer> pickNumPool = new LinkedList<>();

            log.info("choose[{}] groupRange[{}] previousgroupRange[{}]", choose, groupRange, previousGroupRange);

            for (int p = 0; p < permutation; p++) {
                int baseNumber = (p / groupRange);
                int lastTwoNumber = 0;
                if (choose != 0) {
                    if (p % previousGroupRange == 0) {
                        shiftNumber = n - baseNumber + choose - 1;
                        isSetShiftNumber = false;
                    }
                    if (!isSetShiftNumber && p % groupRange == 0) {
                        if (permutationPattern[p][choose - 1] == (baseNumber + shiftNumber) % n) {
                            shiftNumber++;
                            isSetShiftNumber = true;
                        }
                    }
                    if (groupRange == 1 && isLastTwoPosition(choose, r)) {
                        if (p % previousGroupRange == 0) {

                            for (int pickNum = 0; pickNum < n || pickNumPool.size() == previousGroupRange; pickNum++) {

                                for (int searchIndex = 0; searchIndex < choose; searchIndex++) {
                                    if (permutationPattern[p][searchIndex] == pickNum) {
                                        break;
                                    } else if (searchIndex == choose - 1) {
                                        pickNumPool.add(pickNum);
                                    }
                                }
                            }
                        }
                        lastTwoNumber = pickNumPool.remove();
                    }
                }

                if (choose != 0 && groupRange == 1 && isLastTwoPosition(choose, r)) {
                    permutationPattern[p][choose] = lastTwoNumber;
                } else {
                    permutationPattern[p][choose] = (baseNumber + shiftNumber) % n;
                }
            }
        }
        for (Integer[] pattern : permutationPattern) {
            displayPermutationPattern.append("\n").append(StringFormatUtil.arrayToString(pattern));
        }
        log.info("permutation pattern {}", displayPermutationPattern);

        return permutationPattern;
    }

    private boolean isLastTwoPosition(int c, int r) {
        return c > 1 && (c == r - 1 || c == r - 2);
    }
}

package backend.poc.tower_game.service;

import backend.poc.tower_game.util.StringFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

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

    //max factorial for int is 12
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

    public Integer factorialWithLowerBound(Integer n, Integer l) {
        if (n < 1 || n <= l) {
            return 1;
        }
        int result = 1;
        for (int i = n; i > l; i--) {
            result *= i;
        }
        return result;
    }

    public Integer[][] getPermutationPattern(Integer n, Integer r) {
        return this.getPermutationPattern(n, r, Integer.MAX_VALUE);
    }

    public Integer[][] getPermutationPattern(Integer n, Integer r, int maxRun) {
        log.info("start generate permutation pattern");
        Integer permutation = this.calculatePermutation(n, r);

        StringBuilder displayPermutationPattern = new StringBuilder();

        Integer[][] permutationPattern = new Integer[Math.min(permutation, maxRun)][r];

        for (int choose = 0; choose < r; choose++) {
            // formula for finding repeating count in each deep of permutation patter[ permutation * (factorial(n - choose - 1) / factorial(n)) ]
            int groupRange = permutation / factorialWithLowerBound(n, n - choose - 1);
            int previousGroupRange = 0;
            if (choose != 0) {
                previousGroupRange = permutation / factorialWithLowerBound(n, n - choose);
            }
            Queue<Integer> pickNumPool = new LinkedList<>();

            log.info("choose[{}] groupRange[{}] previousGroupRange[{}]", choose, groupRange, previousGroupRange);

            for (int p = 0; p < permutation && p < maxRun; p++) {
                if (choose != 0) {

                    if (p % previousGroupRange == 0) {
                        pickNumPool.clear();
                        for (int i = 0; i < n; i++) {
                            pickNumPool.add(i);
                        }
                        for (int searchIndex = 0; searchIndex < choose; searchIndex++) {
                            pickNumPool.remove(permutationPattern[p][searchIndex]);
                        }

                    }

                    permutationPattern[p][choose] = pickNumPool.peek();
                    if (p % groupRange == groupRange - 1) {
                        pickNumPool.remove();
                    }

                } else {
                    permutationPattern[p][choose] = (p / groupRange) % n;
                }
            }
        }

        for (int i = 0; i < permutationPattern.length; i++) {
            displayPermutationPattern.append("\n").append(StringFormatUtil.arrayToString(permutationPattern[i])).append("p[").append(i).append("]");
        }
        log.info("permutation pattern {}", displayPermutationPattern);

        return permutationPattern;
    }

}

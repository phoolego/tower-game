package backend.poc.tower_game.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class StringFormatUtil {

    public static <T> String arrayToString(T[] array) {
        String strArray = "[";
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                strArray = strArray.concat("null");
            } else {
                strArray = strArray.concat(array[i].toString());
            }
            if(i!=array.length-1){
                strArray = strArray.concat(", ");
            }
        }
        strArray = strArray.concat(" ]");
        return strArray;
    }

}

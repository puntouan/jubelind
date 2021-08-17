package codewars;

import java.util.Arrays;
import java.util.List;

public class Multiply {

    public static String disemvowel(String str) {
        List<String> vocals = Arrays.asList("a","e","i","o","u","A","E","I","O","U");
        for (String vocal: vocals){
            str = str.replace(vocal,"");
        }
        return str;
    }
}

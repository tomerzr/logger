package utility;

import java.util.HashMap;
import java.util.Map;

public class Convert {
    public static Map<String, String> stringToMap(String mapString, String pairsSplitter, String kvSplitter) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = mapString.split(pairsSplitter);
        for (String pair : pairs) {
            String[] keyValue = pair.split(kvSplitter);
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}

package rh.study.jdk8.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by admin on 2018/7/20.
 */
public class ListTest {

    public static void main(String[] args) {
        List<Map<String, Object>> rateList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("comcode", "11");
        map.put("agency", "标普");
        map.put("rate", "AA");
        rateList.add(map);// add
        map = new HashMap<>();
        map.put("comcode", "22");
        map.put("agency", "标普");
        map.put("rate", "BB");
        rateList.add(map); // add
        map = new HashMap<>();
        map.put("comcode", "22");
        map.put("agency", "标普");
        map.put("rate", null);
        rateList.add(map);// add
        map = new HashMap<>();
        map.put("comcode", "22");
        map.put("agency", "穆迪");
        map.put("rate", "BB");
        rateList.add(map);// add
        map = new HashMap<>();
        map.put("comcode", "11");
        map.put("agency", "穆迪");
        map.put("rate", "");
        rateList.add(map);// add

        System.out.println(join(rateList));
    }

    /**
     * 以逗号分隔拼字符串
     * @param rateList
     * @return
     */
    private static String join(List<Map<String, Object>> rateList) {
        return rateList.stream().map(stringObjectMap -> {return stringObjectMap.get("comcode").toString();}).collect(Collectors.joining(","));
    }
}

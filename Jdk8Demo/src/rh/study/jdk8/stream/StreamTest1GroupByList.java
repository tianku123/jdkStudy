package rh.study.jdk8.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对List进行分组
 */
public class StreamTest1GroupByList {
    public static void main(String[] args) {
        groupByAgencyAndComcode();
    }

    /**
     * 多个字段分组
     */
    private static void groupByAgencyAndComcode() {
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

        List<Map<String, Object>> other = new ArrayList<>();
        map = new HashMap<>();
        map.put("comcode", "11");
        map.put("agency", "标普2");
        map.put("rate", "AA");
        other.add(map);// add
        map = new HashMap<>();
        map.put("comcode", "22");
        map.put("agency", "标普2");
        map.put("rate", "BB");
        other.add(map); // add
        rateList.addAll(0, other);
        Map<String, List<Map<String, Object>>> groupByAgency;
        /**
         * jdk1.8之前的处理方式
         */
//        groupByAgency = lessJdk8(rateList);
//        System.out.println(groupByAgency);
        /**
         * jdk1.8处理方式
         * Stream API的流操作
         */
        groupByAgency = rateList.stream()
                // 过滤
                .filter((Map mm) -> { Object rate = mm.get("rate"); return rate != null && !"".equals(rate);})
                // 根据机构分组
                .collect(Collectors.groupingBy((Map mm) -> mm.get("comcode").toString()  + mm.get("agency").toString()));
        System.out.println(groupByAgency);
    }

    /**
     * 根据单个字段分组
     */
    private static void groupByAgency() {
        List<Map<String, Object>> rateList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("agency", "标普");
        map.put("rate", "AA");
        rateList.add(map);// add
        map = new HashMap<>();
        map.put("agency", "标普");
        map.put("rate", "BB");
        rateList.add(map); // add
        map = new HashMap<>();
        map.put("agency", "标普");
        map.put("rate", null);
        rateList.add(map);// add
        map = new HashMap<>();
        map.put("agency", "穆迪");
        map.put("rate", "BB");
        rateList.add(map);// add
        map = new HashMap<>();
        map.put("agency", "穆迪");
        map.put("rate", "");
        rateList.add(map);// add
        Map<String, List<Map<String, Object>>> groupByAgency;
        /**
         * jdk1.8之前的处理方式
         */
        groupByAgency = lessJdk8(rateList);
        System.out.println(groupByAgency);
        /**
         * jdk1.8处理方式
         * Stream API的流操作
         */
        groupByAgency = rateList.stream()
                // 过滤
                .filter((Map mm) -> { Object rate = mm.get("rate"); return rate != null && !"".equals(rate);})
                // 根据机构分组
                .collect(Collectors.groupingBy((Map mm) -> mm.get("agency").toString()));
        System.out.println(groupByAgency);
    }

    /**
     * jdk1.8之前分组及过滤的方式
     * 通过外部迭代进行过滤及分组操作
     * @param rateList
     * @return
     */
    private static Map<String,List<Map<String,Object>>> lessJdk8(List<Map<String, Object>> rateList) {
        Map<String, List<Map<String, Object>>> groupByAgency = new HashMap<>();
        for (Map<String, Object> map : rateList) {
            Object rate = map.get("rate");
            /**
             * 过滤评级为空的情况
             */
            if (rate != null && !"".equals(rate)) {
                String agency = map.get("agency").toString();
                List<Map<String, Object>> list = groupByAgency.get(agency);
                if (list == null) {
                    list = new ArrayList<>();
                    groupByAgency.put(agency, list);
                }
                list.add(map);
            }
        }
        return groupByAgency;
    }
}

//package ru.badin.springbootf1webservice.HAL;
//
//import org.hibernate.engine.internal.Collections;
//
//import java.util.Collection;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//public class Hal {
//
//    public static Map<String, Object> paginateAsDictionary(String baseUrl, int index, int count, int total) {
//        Map<String, Object> links = new LinkedHashMap<>();
//        links.put("self", singletonMap("href", "/api/vehicles"));
//        if (index < total) {
//            links.put("next", Collections.singletonMap("href", "/api/vehicles?index=" + (index + count)));
//            links.put("final", Collections.singletonMap("href", baseUrl + "?index=" + (total - (total % count)) + "&count=" + count));
//        }
//        if (index > 0) {
//            links.put("prev", Collections.singletonMap("href", "/api/vehicles?index=" + (index - count)));
//            links.put("first", Collections.singletonMap("href", "/api/vehicles?index=0"));
//        }
//        return links;
//    }
//}
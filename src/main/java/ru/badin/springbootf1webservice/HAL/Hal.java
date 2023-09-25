package ru.badin.springbootf1webservice.HAL;

import java.util.Dictionary;

public class Hal {

//public static Dictionary<String, Object> PaginateAsDictionary(String baseUrl, int index, int count, int total) {
//            var links = new Dictionary<String, Object>();
//            links.put();
//            if (index < total) {
//                links["next"] = new { href = $"/api/vehicles?index={index + count}" };
//                links["final"] = new { href = $"{baseUrl}?index={total - (total % count)}&count={count}" };
//            }
//            if (index > 0) {
//                links["prev"] = new { href = $"/api/vehicles?index={index - count}" };
//                links["first"] = new { href = $"/api/vehicles?index=0" };
//            }
//            return links;
//        }
}
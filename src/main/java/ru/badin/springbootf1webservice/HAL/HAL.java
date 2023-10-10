package ru.badin.springbootf1webservice.HAL;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HAL {
    public static Map<String, Object> paginateAsDictionary(String baseUrl, int index, int count, int total) {
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl));

        if (index < total) {
            links.put("next", Collections.singletonMap("href", baseUrl + "?index=" + (index+1) + "&count=" + count));
            links.put("final", Collections.singletonMap("href", baseUrl + "?index=" + (total - (total % count)) + "&count=" + count));
        }

        if (index > 0) {
            links.put("prev", Collections.singletonMap("href", baseUrl + "?index=" + (index-1) + "&count=" + count));
            links.put("first", Collections.singletonMap("href", baseUrl + "?index=0&count=" + count));
        }

        return links;
    }

    public static Map<String, Object> addHypermediaLink(String href, String rel, String method, String title) {
        Map<String, Object> link = new LinkedHashMap<>();
        link.put("href", href);
        link.put("rel", rel);
        link.put("method", method);
        link.put("title", title);
        return link;
    }

    public static Map<String, Object> addEmbeddedResource(String key, Object resource) {
        Map<String, Object> embedded = new LinkedHashMap<>();
        embedded.put(key, resource);
        return embedded;
    }
}
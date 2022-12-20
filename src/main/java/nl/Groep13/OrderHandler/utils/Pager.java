package nl.Groep13.OrderHandler.utils;


import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

public class Pager {

    public static <T> List<T> getRequestedItems(List<T> fullList, int page) {
        List<T> requestedList = new ArrayList<>();
        for (int i = 25 * page; i < 25 * (page + 1); i++)
            if (fullList.size() > i)
                requestedList.add(fullList.get(i));

        return requestedList;
    }

    public static HttpHeaders addHeaders(int size) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Expose-Headers", "full_list_length");
        responseHeaders.set("full_list_length", String.valueOf(size));
        return  responseHeaders;
    }
}

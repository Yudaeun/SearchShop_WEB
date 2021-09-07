package com.day.week04.utils;

import com.day.week04.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component

public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "fjbFJ_UbBPfmdEbtMB5c");
        headers.add("X-Naver-Client-Secret", "eUu0dTxC1A");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);

        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);//result=문자열
        JSONArray items = rjson.getJSONArray("items");//rjson에서 JSONArray를 꺼낸다

        List<ItemDto> itemDtoList = new ArrayList<>();
        //모든 검색 결과가 나타나게 하려면 List를 사용해야 한다.
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = (JSONObject) items.get(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);


        }//jsonarray로 for문을 돈다.
        return itemDtoList;
    }
}


package com.example.demo.api;

import com.example.demo.entity.Movie;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ApiMovieParser {
    public List<Movie> movie() {
        // HTTP 통신을 해서 (url과 parameter(인증키, 검색어 등) 필요)
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
//https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.xml?key=6d67fa164c41539d5695302942e640be&targetDt=20120101
        // 요청에 필요한 매개변수 설정
        String apiKey = "6d67fa164c41539d5695302942e640be";
        String targetDt = "20250106";

        // URL에 파라미터 추가
        String requestUrl = url + "?key=" + apiKey + "&targetDt=" + targetDt;
        String response = restTemplate.getForObject(requestUrl, String.class);
        System.out.println(response);

        // JSON으로 인식
        JSONObject json = new JSONObject(response);
        System.out.println(json);

        JSONObject boxOfficeResult = json.getJSONObject("boxOfficeResult");
        System.out.println(boxOfficeResult);

        JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
        System.out.println(dailyBoxOfficeList);

        List<Movie> list = new ArrayList<>();

        for (int i = 0; i < dailyBoxOfficeList.length(); i++) {
            Movie movie = new Movie();
            JSONObject object = dailyBoxOfficeList.getJSONObject(i);

            movie.setRnum(object.getString("rnum"));
            movie.setMovieNm(object.getString("movieNm"));
            movie.setSalesAmt(object.getString("salesAmt"));
            movie.setRankOldAndNew(object.getString("rankOldAndNew"));
            movie.setAudiCnt(object.getString("audiCnt"));

            list.add(movie);

            System.out.println(movie);
            System.out.println("=================");
        }

        return list;
    }
}

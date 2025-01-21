package com.example.demo.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/4050000/apt/getApt"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=oYSVCwI%2F6e%2F574giNg1TuC057IOWdf%2B0cglsIFgJwJ26eJ2aZYGYUNdq1iHX%2BblURBMAi2RLMNINVre6nUKEhw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("cmplx_nm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*아파트 단지의 단지명*/
        urlBuilder.append("&" + URLEncoder.encode("gu","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*아파트가 위치한 구 (처인구/수지구/기흥구)*/
        urlBuilder.append("&" + URLEncoder.encode("dong","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*아파트가 위치한 동*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}

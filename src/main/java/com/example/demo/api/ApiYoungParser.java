package com.example.demo.api;

import com.example.demo.entity.Young;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ApiYoungParser {
    public List<Young> young() {
        // HTTP 통신을 해서 (url과 parameter(인증키, 검색어 등) 필요)
        String url = "https://www.youthcenter.go.kr/opi/empList.do";
        String paramter = "?openApiVlak=2853a1265426c8954d6b244d&pageIndex=1&display=10&query=청년취업";
        // 데이터를 가지고 올 예정(HTTP 통신용 라이브러리)
        RestTemplate restTemplate = new RestTemplate();
        // URL로 HTTP 통신을 하고 결과를 String으로 받겠다는 의미
        String response = restTemplate.getForObject(url + paramter, String.class);
        System.out.println(response);

        // API에서 XML형태로 된 String을 제공하기에  이를 JSON으로 변경
        JSONObject json = XML.toJSONObject(response);
        System.out.println(json);

        // JSON에서 필요한 것만 추출
        // JSON은 맨 위에서부터 하나씩 추출함
        // 추출한 값의 타입이 JSON이 될 수도 있고
        //               JSON 배열이 될 수도 있다.
        // empsInfo의 값의 타입은 JSON
        JSONObject empsInfo = json.getJSONObject("empsInfo");
        System.out.println(empsInfo);

        // emp의 값의 타입은 JSONArray
        JSONArray empArr = empsInfo.getJSONArray("emp");
        System.out.println(empArr);

        // DB에 넣어주기 위해서 VO로 만들어준다.
        // 반복문으로 List<VO>를 만들어야 한다.
        List<Young> list = new ArrayList<>();

        // JSON을 분석해서 추출한다.(parser, 파서)
        for (int i = 0; i < empArr.length(); i++) {
            Young young = new Young();
            JSONObject object = empArr.getJSONObject(i);

            String bizId = object.getString("bizId");
            String polyBizSjnm = object.getString("polyBizSjnm");
            String cnsgNmor = object.getString("cnsgNmor");
            String rqutProcCn = object.getString("rqutProcCn");
//            String ageInfo = object.getString("ageInfo");

            young.setBizId(bizId);
            young.setPolyBizSjnm(polyBizSjnm);
            young.setCnsgNmor(cnsgNmor);
            young.setRqutProcCn(rqutProcCn);

            list.add(young);

//            System.out.println("bizId>>> " + bizId);
//            System.out.println("polyBizSjnm>>> "+ polyBizSjnm);
//            System.out.println("cnsgNmor>>> " + cnsgNmor);
//            System.out.println("rqutProcCn>>> " + rqutProcCn);
//            System.out.println("ageInfo>>> " + ageInfo);
//            System.out.println("------------------------------------------------------------------------------------------------------------");
        }
//        System.out.println(list);
//        System.out.println(list.size());

        return list;
    }
}

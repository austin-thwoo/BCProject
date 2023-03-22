//package com.laonstory.poc_be_spring.global.application;
//
//import com.laonstory.brc.domain.user.domain.User;
//import com.laonstory.brc.global.config.SmsInterface;
//import com.laonstory.brc.global.dto.request.SmsRequest;
//import com.laonstory.brc.global.utils.Retrofit;
//import lombok.extern.slf4j.Slf4j;
//import org.json.simple.parser.ParseException;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Random;
//
//
//@Service
//@Slf4j
//public class SmsSendService {
//
//
//    private final String KEY = "hx5stwwd751y8ygg4tespvj6t19xq5lk";
//
//
//    public String send(User user,SmsRequest smsRequest) throws IOException, ParseException {
//
//
//        Random rand = new Random();
//        String numStr = ""; //난수가 저장될 변수
//
//        for(int i=0;i<6;i++) {
//
//            //0~9 까지 난수 생성
//            String ran = Integer.toString(rand.nextInt(10));
//
//                numStr += ran;
//
//        }
//
//        Retrofit<String> retrofit = new Retrofit();
//
//
//        retrofit.createService(SmsInterface.class)
//                .send("bsdp56vs6h16ieowfzg0g500fwsh7klf"
//                        , "phy0119"
//                        , "010-8305-9712"
//                        ,smsRequest.getPhone()
//                        , "브랜드케어 인증번호 입니다\n"+numStr,"Y")
//                .execute();
//
//
//        return numStr;
//
//    }
//
//
//}

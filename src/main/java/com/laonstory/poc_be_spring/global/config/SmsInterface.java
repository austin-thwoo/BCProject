//package com.laonstory.poc_be_spring.global.config;
//
//import com.laonstory.brc.global.dto.response.SmsResponse;
//import retrofit2.Call;
//import retrofit2.http.*;
//
//public interface SmsInterface {
//
//    @POST("send/")
//    @FormUrlEncoded
//    Call<SmsResponse> send(@Field("key") String key,
//                            @Field("user_id")String user_id,
//                            @Field("sender")String sender,
//                           @Field("receiver")String receiver,
//                           @Field("msg")String msg,
//                           @Field("testmode_yn") String testmode_yn
//    );
///*
//                           @Query(value = "testmode_yn") String testmode_yn
//*/
//
//
//
//}

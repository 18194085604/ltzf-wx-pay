package com.gjy.wxpay;

import com.alibaba.fastjson2.JSON;
import com.gjy.wxpay.payment.nativepay.INativePayApi;
import com.gjy.wxpay.utils.SignUtils;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WxPayApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(WxPayApplicationTests.class);

    public static void main(String[] args) {

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("mch_id", "1673424392");
        dataMap.put("out_trade_no", "xfg240413001");
        dataMap.put("total_fee", "0.01");
        dataMap.put("body", "QQ公仔");
        dataMap.put("timestamp", String.valueOf(timestamp));
        dataMap.put("notify_url", "https://www.baidu.com");

        System.out.println(SignUtils.createSign(dataMap, "6d3e889f359fcb83d150e9553a9217b9"));

    }
    @Test
    public void test_retrofit2() throws IOException {
        OkHttpClient client = new OkHttpClient();
        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl("")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("mch_id", "1673424392");
        dataMap.put("out_trade_no", "xfg240413002");
        dataMap.put("total_fee", "0.01");
        dataMap.put("body", "QQ公仔");
        dataMap.put("timestamp", String.valueOf(timestamp));
        dataMap.put("notify_url", "https://www.baidu.com");

        Call<Object> call = nativePayApi.prepay(
                dataMap.get("mch_id"),
                dataMap.get("out_trade_no"),
                dataMap.get("total_fee"),
                dataMap.get("body"),
                dataMap.get("timestamp"),
                dataMap.get("notify_url"),
                SignUtils.createSign(dataMap, "6d3e889f359fcb83d150e9553a9217b9"));

        Response<Object> execute = call.execute();
        Object body = execute.body();
        log.info("测试结果: {}", JSON.toJSONString(body));

    }
}

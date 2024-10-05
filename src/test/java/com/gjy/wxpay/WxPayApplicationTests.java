package com.gjy.wxpay;

import com.gjy.wxpay.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

public class WxPayApplicationTests {

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

}

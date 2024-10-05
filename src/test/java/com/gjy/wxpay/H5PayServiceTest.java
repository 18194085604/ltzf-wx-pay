package com.gjy.wxpay;

import com.alibaba.fastjson.JSON;
import com.gjy.wxpay.factory.Configuration;
import com.gjy.wxpay.factory.PayFactory;
import com.gjy.wxpay.factory.defaults.DefaultPayFactory;
import com.gjy.wxpay.payment.h5.H5PayService;
import com.gjy.wxpay.payment.h5.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class H5PayServiceTest {

    private H5PayService h5PayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1104268", "1673424392", "6d3e889f359fcb83d150e9553a9217b9"
        );

        PayFactory payFactory = new DefaultPayFactory(configuration);
        this.h5PayService = payFactory.h5PayService();
    }

    @Test
    public void test_prepay() throws Exception {
        // 1. 请求参数
        PrepayRequest request = new PrepayRequest();
        request.setMchid("1673424392");
        request.setOutTradeNo(RandomStringUtils.randomNumeric(8));
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("https://www.baidu.com");

        // 2. 创建支付订单
        PrepayResponse response = h5PayService.prepay(request);

        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }


    @Test
    public void test_queryOrderByOutTradeNo() throws Exception {
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid("1673424392");
        request.setOutTradeNo("04805636");
        QueryOrderByOutTradeNoResponse response = h5PayService.queryOrderByOutTradeNo(request);
        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }

    @Test
    public void test_refundOrder() throws Exception {
        RefundOrderRequest request = new RefundOrderRequest();
        request.setMchid("1673424392");
        request.setOutTradeNo("66226055");
        request.setOutRefundNo(RandomStringUtils.randomNumeric(8));
        request.setRefundFee("0.01");
        request.setRefundDesc("测试退款");
        request.setNotifyUrl("https://www.baidu.com");

        Object response = h5PayService.refundOrder(request);
        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }

    @Test
    public void test_getRefundOrder() throws Exception {
        GetRefundOrderRequest request = new GetRefundOrderRequest();
        request.setMchid("1673424392");
        request.setOutRefundNo("97173577");

        GetRefundOrderResponse response = h5PayService.getRefundOrder(request);
        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }

}

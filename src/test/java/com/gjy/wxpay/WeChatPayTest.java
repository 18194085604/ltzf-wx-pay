package com.gjy.wxpay;

import com.gjy.wxpay.payment.nativepay.INativePayApi;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 微信支付SDK使用
 */
public class WeChatPayTest {

    private NativePayService nativePayService;

    @Before
    public void init() {

        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId("").build();

        this.nativePayService = new NativePayService.Builder().config(config).build();
    }

    @Test
    public void test_prepay() {
        PrepayRequest request = new PrepayRequest();

        PrepayResponse prepay = nativePayService.prepay(request);

    }


}

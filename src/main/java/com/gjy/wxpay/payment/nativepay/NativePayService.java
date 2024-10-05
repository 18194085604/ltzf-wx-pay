package com.gjy.wxpay.payment.nativepay;

import com.gjy.wxpay.factory.Configuration;
import com.gjy.wxpay.payment.nativepay.model.PrepayRequest;
import com.gjy.wxpay.payment.nativepay.model.PrepayResponse;
import retrofit2.Call;
import retrofit2.Response;

public class NativePayService {
    private final INativePayApi nativePayApi;
    private final Configuration configuration;
    public NativePayService(final INativePayApi nativePayApi, final Configuration configuration) {
        this.nativePayApi = nativePayApi;
        this.configuration = configuration;
    }

    public PrepayResponse prepay(PrepayRequest request) throws Exception {
        // 1. 请求接口 & 签名
        Call<PrepayResponse> call = nativePayApi.prepay(
                request.getMchid(),
                request.getOutTradeNo(),
                request.getTotalFee(),
                request.getBody(),
                request.getTimestamp(),
                request.getNotifyUrl(),
                request.createSign(configuration.getPartnerKey()));

        // 2. 获取数据
        Response<PrepayResponse> execute = call.execute();

        // 3. 返回结果
        return execute.body();
    }

}

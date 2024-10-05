package com.gjy.wxpay.payment.nativepay;

import com.gjy.wxpay.payment.nativepay.model.GetRefundOrderResponse;
import com.gjy.wxpay.payment.nativepay.model.PrepayResponse;
import com.gjy.wxpay.payment.nativepay.model.QueryOrderByOutTradeNoResponse;
import com.gjy.wxpay.payment.nativepay.model.RefundOrderResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 扫码支付接口
 */
public interface INativePayApi {
    @POST("/api/wxpay/native")
    @FormUrlEncoded
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<PrepayResponse> prepay(
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("total_fee") String totalFee,
            @Field("body") String body,
            @Field("timestamp") String timestamp,
            @Field("notify_url") String notifyUrl,
            @Field("sign") String sign
    );

    @FormUrlEncoded
    @POST("api/wxpay/get_pay_order")
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<QueryOrderByOutTradeNoResponse> getPayOrder(
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign
    );

    @FormUrlEncoded
    @POST("api/wxpay/refund_order")
    Call<RefundOrderResponse> refundOrder(
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("out_refund_no") String outRefundNo,
            @Field("timestamp") String timestamp,
            @Field("refund_fee") String refundFee,
            @Field("refund_desc") String refundDesc,
            @Field("notify_url") String notifyUrl,
            @Field("sign") String sign
    );

    @FormUrlEncoded
    @POST("api/wxpay/get_refund_order")
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<GetRefundOrderResponse> getRefundOrder(
            @Field("mch_id") String mchId,
            @Field("out_refund_no") String outRefundNo,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign
    );
}

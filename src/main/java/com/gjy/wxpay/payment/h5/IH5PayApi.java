package com.gjy.wxpay.payment.h5;

import com.gjy.wxpay.payment.h5.model.GetRefundOrderResponse;
import com.gjy.wxpay.payment.h5.model.PrepayResponse;
import com.gjy.wxpay.payment.h5.model.QueryOrderByOutTradeNoResponse;
import com.gjy.wxpay.payment.h5.model.RefundOrderResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description H5支付
 * @create 2024-04-13 18:29
 */
public interface IH5PayApi {


    @FormUrlEncoded
    @POST("api/wxpay/h5")
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<PrepayResponse> prepay(
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("total_fee") String totalFee,
            @Field("body") String body,
            @Field("timestamp") String timestamp,
            @Field("notify_url") String notifyUrl,
            @Field("return_url") String returnUrl,
            @Field("attach") String attach,
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

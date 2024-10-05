package com.gjy.wxpay.factory;

import com.gjy.wxpay.payment.app.AppPayService;
import com.gjy.wxpay.payment.nativepay.NativePayService;

/**
 * 支付工厂
 */
public interface PayFactory {

    NativePayService nativePayService();

    AppPayService appPayService();

}

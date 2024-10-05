package com.gjy.wxpay.factory;

import com.gjy.wxpay.payment.app.AppPayService;
import com.gjy.wxpay.payment.h5.H5PayService;
import com.gjy.wxpay.payment.jsapi.JSPayService;
import com.gjy.wxpay.payment.jump_h5.JumpH5PayService;
import com.gjy.wxpay.payment.nativepay.NativePayService;

/**
 * 支付工厂
 */
public interface PayFactory {

    NativePayService nativePayService();

    AppPayService appPayService();

    H5PayService h5PayService();

    JSPayService jsPayService();

    JumpH5PayService jumpH5PayService();

}

package com.gjy.wxpay.factory.defaults;

import com.gjy.wxpay.factory.Configuration;
import com.gjy.wxpay.factory.PayFactory;
import com.gjy.wxpay.payment.app.AppPayService;
import com.gjy.wxpay.payment.app.IAppPayApi;
import com.gjy.wxpay.payment.h5.H5PayService;
import com.gjy.wxpay.payment.h5.IH5PayApi;
import com.gjy.wxpay.payment.jsapi.IJSPayApi;
import com.gjy.wxpay.payment.jsapi.JSPayService;
import com.gjy.wxpay.payment.jump_h5.IJumpH5PayApi;
import com.gjy.wxpay.payment.jump_h5.JumpH5PayService;
import com.gjy.wxpay.payment.nativepay.INativePayApi;
import com.gjy.wxpay.payment.nativepay.NativePayService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class DefaultPayFactory implements PayFactory {
    private final Configuration configuration;

    private final OkHttpClient httpClient;


    public DefaultPayFactory(Configuration configuration) {
        this.configuration = configuration;
        // 1. 日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());

        // 2. 开启 HTTP 客户端
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public NativePayService nativePayService() {
        INativePayApi nativePayApi = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(configuration.getApiHost())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);
        return new NativePayService(nativePayApi,configuration);
    }

    @Override
    public AppPayService appPayService() {
        // 构建API
        IAppPayApi appPayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IAppPayApi.class);
        // 创建支付服务
        return new AppPayService(configuration, appPayApi);
    }

    @Override
    public H5PayService h5PayService() {
        // 构建API
        IH5PayApi h5PayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IH5PayApi.class);
        // 创建支付服务
        return new H5PayService(configuration, h5PayApi);
    }

    @Override
    public JSPayService jsPayService() {
        // 构建API
        IJSPayApi jsPayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IJSPayApi.class);
        // 创建支付服务
        return new JSPayService(configuration, jsPayApi);
    }

    @Override
    public JumpH5PayService jumpH5PayService() {
        // 构建API
        IJumpH5PayApi jumpH5PayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IJumpH5PayApi.class);
        // 创建支付服务
        return new JumpH5PayService(configuration, jumpH5PayApi);
    }
}

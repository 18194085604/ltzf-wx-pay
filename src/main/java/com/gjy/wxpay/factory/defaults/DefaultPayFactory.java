package com.gjy.wxpay.factory.defaults;

import com.gjy.wxpay.factory.Configuration;
import com.gjy.wxpay.factory.PayFactory;
import com.gjy.wxpay.payment.app.AppPayService;
import com.gjy.wxpay.payment.app.IAppPayApi;
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
}

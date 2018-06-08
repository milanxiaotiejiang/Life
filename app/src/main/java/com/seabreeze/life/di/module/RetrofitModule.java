package com.seabreeze.life.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.common.okhttp.CacheInterceptor;
import com.seabreeze.life.common.okhttp.CookiesManager;
import com.seabreeze.life.common.okhttp.OkHttpUtils;
import com.seabreeze.life.common.okhttp.SafeHostnameVerifier;
import com.seabreeze.life.common.okhttp.SafeTrustManager;
import com.seabreeze.life.utils.AppConfig;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class RetrofitModule {

    private final Context context;

    public RetrofitModule(Context context) {
        this.context = context;
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    public OkHttpClient provideOKHttpClient(Cache cache, CacheInterceptor cacheInterceptor, CookiesManager cookiesManager) {

        //创建keyManagers
        KeyManager[] keyManagers = OkHttpUtils.prepareKeyManager(null, null);

        //创建TrustManager
        TrustManager[] trustManagers = OkHttpUtils.prepareTrustManager();

        //创建X509TrustManager
        X509TrustManager manager = new SafeTrustManager();

        SSLContext sslContext = null;
        try {
            // 创建TLS类型的SSLContext对象， that uses our TrustManager
            sslContext = SSLContext.getInstance("TLS");
            // 用上面得到的trustManagers初始化SSLContext，这样sslContext就会信任keyStore中的证书
            // 第一个参数是授权的密钥管理器，用来授权验证，比如授权自签名的证书验证。第二个是被授权的证书管理器，用来验证服务器端的证书
            sslContext.init(keyManagers, new TrustManager[]{manager}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpLoggingInterceptor.Logger logger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(message);
            }
        };
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor)
                .sslSocketFactory(sslContext.getSocketFactory(), manager)
                .hostnameVerifier(new SafeHostnameVerifier())
//                .cookieJar(cookiesManager)
                .build();
    }

    @Provides
    public CacheInterceptor providesCacheInterceptor() {
        return new CacheInterceptor(context);
    }


    @Provides
    public Cache provideCache() {
        return new Cache(context.getExternalFilesDir(AppConfig.DEFAULT_JOSN_CACHE), AppConfig.DEFAULT_CACHE_SIZE);
    }

    @Provides
    public CookiesManager providesCookies() {
        return new CookiesManager();
    }
}

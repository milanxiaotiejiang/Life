package com.seabreeze.life.common.okhttp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class SafeHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}

package com.seabreeze.life.common.api;

public class UrlManager {

    public static final String KAIYAN_HOST = "https://baobab.kaiyanapp.com/";

    //    public static final String WANGYI_HOST = "http://www.wawa.fm:9090/wawa/api.php/magazine/";
    public static final String WANGYI_HOST = "http://wawa.fm";

    public static final String YIREN_HOST = "http://v3.wufazhuce.com:8000/api/";

    public static final String VEER_HOST = "https://api.veervr.tv/";

    public static final String KUULA_HOST = "https://kuula.co/api/";

    public static String getKuulaCover(String uuid) {
        return "https://files.kuula.io/" + uuid + "/01-cover.jpg";
//        return "https://storage.kuula.co/" + uuid + "/01-cover.jpg";
    }
}

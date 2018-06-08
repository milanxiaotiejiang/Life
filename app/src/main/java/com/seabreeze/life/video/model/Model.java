package com.seabreeze.life.video.model;

import java.util.Map;

public class Model {

    private String url;
    private float speed = 1;
    private boolean looping;
    private Map<String, String> mapHeadData;

    public Model(String url, float speed, boolean looping, Map<String, String> mapHeadData) {
        this.url = url;
        this.speed = speed;
        this.looping = looping;
        this.mapHeadData = mapHeadData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public Map<String, String> getMapHeadData() {
        return mapHeadData;
    }

    public void setMapHeadData(Map<String, String> mapHeadData) {
        this.mapHeadData = mapHeadData;
    }
}

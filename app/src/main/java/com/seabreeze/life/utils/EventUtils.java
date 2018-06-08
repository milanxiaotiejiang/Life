package com.seabreeze.life.utils;

import org.greenrobot.eventbus.EventBus;

public class EventUtils {

    public static void register(Object o) {
        if (!EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().register(o);
        }
    }

    public static void unregister(Object o) {
        if (EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().unregister(o);
        }
    }

    public static void sendEvent(Object o) {
        EventBus.getDefault().post(o);
    }

}

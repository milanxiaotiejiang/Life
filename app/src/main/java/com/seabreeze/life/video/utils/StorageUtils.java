package com.seabreeze.life.video.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import static android.os.Environment.MEDIA_MOUNTED;

public class StorageUtils {

    private static final String INDIVIDUAL_DIR_NAME = "video-cache";

    public static File getIndividualCacheDirectory(Context context) {
        File cacheDir = getCacheDirectory(context, true);
        return new File(cacheDir, INDIVIDUAL_DIR_NAME);
    }

    private static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) { // (sh)it happens
            externalStorageState = "";
        }
        if (preferExternal && MEDIA_MOUNTED.equals(externalStorageState)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
            appCacheDir = new File(cacheDirPath);
        }
        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }
        }
        return appCacheDir;
    }
}

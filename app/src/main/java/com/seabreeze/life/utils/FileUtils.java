package com.seabreeze.life.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileUtils {

    public static final String NAME = "Video";

    //****系统缓存目录**********************************************************************************************

    /**
     * @return 程序系统缓存目录
     */
    public static String getCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
//        return String.valueOf(context.getCacheDir());
    }

    /**
     * @param context    上下文
     * @param customPath 自定义路径
     * @return 程序系统缓存目录
     */
    public static String getCacheDir(Context context, String customPath) {
        String path = context.getCacheDir() + formatPath(customPath);
        mkdir(path);
        return path;
    }

//****Sdcard文件目录**********************************************************************************************

    /**
     * @return 内存卡文件目录
     */
    public static String getExternalFileDir(Context context) {
        return String.valueOf(context.getExternalFilesDir(""));
    }

    /**
     * @param context    上下文
     * @param customPath 自定义路径
     * @return 内存卡文件目录
     */
    public static String getExternalFileDir(Context context, String customPath) {
        String path = context.getExternalFilesDir("") + formatPath(customPath);
        mkdir(path);
        return path;
    }

//****Sdcard缓存目录**********************************************************************************************

    /**
     * @return 内存卡缓存目录
     */
    public static String getExternalCacheDir(Context context) {
        return String.valueOf(context.getExternalCacheDir());
    }

    /**
     * @param context    上下文
     * @param customPath 自定义路径
     * @return 内存卡缓存目录
     */
    public static String getExternalCacheDir(Context context, String customPath) {
        String path = context.getExternalCacheDir() + formatPath(customPath);
        mkdir(path);
        return path;
    }

    //****公共文件夹**********************************************************************************************

    /**
     * @return 公共下载文件夹
     */
    public static String getPublicDownloadDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    //****相关工具**********************************************************************************************

    /**
     * 创建文件夹
     *
     * @param DirPath 文件夹路径
     */
    public static void mkdir(String DirPath) {
        File file = new File(DirPath);
        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
    }

    /**
     * 格式化文件路径
     * 示例：  传入 "sloop" "/sloop" "sloop/" "/sloop/"
     * 返回 "/sloop"
     */
    private static String formatPath(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;
        while (path.endsWith("/"))
            path = new String(path.toCharArray(), 0, path.length() - 1);
        return path;
    }

    public static void deleteFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory() && f.exists()) { // 判断是否存在
                    try {
                        f.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getPath(Context context) {
        return getCacheDir(context) + File.separator + NAME + File.separator;
    }

    public static void saveBitmap(Bitmap bitmap, File file) {
        if (bitmap != null) {
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                bitmap.recycle();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

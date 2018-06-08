package com.seabreeze.life.video.listener;

import java.io.File;

/**
 * Gif图创建的监听
 */

public interface VideoGifSaveListener {

    void process(int curPosition, int total);

    void result(boolean success, File file);
}

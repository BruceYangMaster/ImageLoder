package com.bruce.imageloder;

/**
 * Created by bruce on 2017/2/22.
 */

import android.graphics.Bitmap;

/**
 * 图片缓存的接口
 */
public interface ImageCache {
    Bitmap get(String imgUrl);

    void put(String imgUrl, Bitmap bitmap);
}

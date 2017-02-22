package com.bruce.imageloder;

import android.graphics.Bitmap;

/**
 * Created by bruce on 2017/2/22.
 */

public class DoubleCache implements ImageCache {
    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String imgUrl) {
        Bitmap bitmap = mMemoryCache.get(imgUrl);
        if (bitmap == null) {
            bitmap = mDiskCache.get(imgUrl);
        }
        return bitmap;
    }

    @Override
    public void put(String imgUrl, Bitmap bitmap) {
        mMemoryCache.put(imgUrl, bitmap);
        mDiskCache.put(imgUrl, bitmap);
    }
}

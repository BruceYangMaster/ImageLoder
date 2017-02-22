package com.bruce.imageloder;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by bruce on 2017/2/22.
 */

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        //初始化Lru缓存
        initImageLru();
    }

    private void initImageLru() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 10;
            }
        };
    }

    @Override
    public Bitmap get(String imgUrl) {
        return mMemoryCache.get(imgUrl);
    }

    @Override
    public void put(String imgUrl, Bitmap bitmap) {
        mMemoryCache.put(imgUrl, bitmap);
    }
}

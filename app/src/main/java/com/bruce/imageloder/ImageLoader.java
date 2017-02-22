package com.bruce.imageloder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bruce on 2017/2/22.
 */

public class ImageLoader {
    //图片缓存
    private ImageCache mImageCache = new MemoryCache();
    //线程池，线程数量为cpu的数量
    private ExecutorService mExecutorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 注入缓存实现
     */
    public void setImageCache(ImageCache imageCache) {
        this.mImageCache = imageCache;
    }

    public void displayImage(String imgUrl, ImageView imageView) {
        Bitmap bitmap = mImageCache.get(imgUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存就提交线程池下载
        submitLoadRequest(imgUrl, imageView);
    }

    private void submitLoadRequest(final String imgUrl, final ImageView imageView) {
        imageView.setTag(imgUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                //下载图片
                Bitmap bitmap = downLoadImage(imgUrl);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(imgUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imgUrl, bitmap);
            }
        });
    }

    private Bitmap downLoadImage(String imgUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}

package com.example.week8demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mySingleton;
    private static Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private MySingleton(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(4);

            @Override
            public Bitmap getBitmap(String url) {
                Bitmap bmp = cache.get(url);
                if (bmp == null) {
                    System.out.println("Image not in Cache");
                } else
                    System.out.println("Image in Cache");

                return bmp;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                System.out.println("put Bitmap");
                cache.put(url, bitmap);
            }
        });
    }

    //get Instance method for singleton class (one object)

    public static synchronized MySingleton getInstance(Context context) {
        if (mySingleton == null)
            mySingleton = new MySingleton(context);
        return mySingleton;

    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}

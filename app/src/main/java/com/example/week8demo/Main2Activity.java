package com.example.week8demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class Main2Activity extends AppCompatActivity {

    NetworkImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String firebaseimageURL = getIntent().getExtras().getString("firebaseImage");

        imageView = findViewById(R.id.image);

        ImageLoader imageLoader = MySingleton.getInstance(this).getImageLoader();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(1300, 1300);

        imageView.setImageUrl(firebaseimageURL, imageLoader);

        imageView.setLayoutParams(layoutParams);

    }

}

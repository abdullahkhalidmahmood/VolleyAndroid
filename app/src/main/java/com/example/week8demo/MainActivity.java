package com.example.week8demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TODO: change layout to grid layout
    // - add delete checkbox functionality
    // - store images on Firebase
    // - get images from Firebase
    // - remove icon functionality

    GridView gridView;
    ArrayList<String> firebaseImages = new ArrayList<>();
    ImageButton delete;
    GridViewAdapter gridViewAdapter;
    ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        delete = findViewById(R.id.delete);
        imagesfromFirebase();

        imageLoader = MySingleton.getInstance(this).getImageLoader();

        setUpAdapter();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteImages();
            }
        });
    }

    private void deleteImages() {
        firebaseImages.removeAll(Constants.deleteList);

        setUpAdapter();
    }

    private void setUpAdapter() {
        gridViewAdapter = new GridViewAdapter(this, firebaseImages, imageLoader);
        gridView.setAdapter(gridViewAdapter);
    }


    private void imagesfromFirebase() {
        firebaseImages.add("https://firebasestorage.googleapis.com/v0/b/images-6741e.appspot.com/o/switzerland.png?alt=media&token=8d3167e5-520c-418a-a124-45bd7f330f24");
        firebaseImages.add("https://firebasestorage.googleapis.com/v0/b/images-6741e.appspot.com/o/germany.png?alt=media&token=83ea8913-8c7d-4db1-a99b-9e058a042b00");
        firebaseImages.add("https://firebasestorage.googleapis.com/v0/b/images-6741e.appspot.com/o/indonesia.png?alt=media&token=e2931af0-1ea3-4714-8d8c-b2afce4162a8");
        firebaseImages.add("https://firebasestorage.googleapis.com/v0/b/images-6741e.appspot.com/o/japan.png?alt=media&token=ff8ee7cc-d3ff-487d-9a6d-381663db3ef0");
        firebaseImages.add("https://firebasestorage.googleapis.com/v0/b/images-6741e.appspot.com/o/sarawak.png?alt=media&token=9b7bfaff-ad8f-4285-83a9-d7c7a8c4f513");
        firebaseImages.add("https://firebasestorage.googleapis.com/v0/b/images-6741e.appspot.com/o/singapore.png?alt=media&token=30962354-cdbb-47a2-b6e5-f92c95f121cd");

    }
}

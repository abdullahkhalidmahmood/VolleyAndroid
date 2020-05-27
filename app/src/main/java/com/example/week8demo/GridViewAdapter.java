package com.example.week8demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private ArrayList<String> firebaseImages;
    private Context context;
    ImageLoader imageLoader;

    public GridViewAdapter(Context context, ArrayList<String> firebaseImages, ImageLoader imageLoader) {
        this.context = context;
        this.firebaseImages = firebaseImages;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return firebaseImages.size();
    }

    @Override
    public Object getItem(int position) {
        return firebaseImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setDefaultImageResId(R.drawable.ic_launcher_foreground);
        viewHolder.imageView.setErrorImageResId(R.drawable.ic_launcher_background);
        viewHolder.imageView.setAdjustViewBounds(true);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Constants.deleteList.add(firebaseImages.get(position));

                } else {
                    Constants.deleteList.remove(new String(firebaseImages.get(position)));
                }


            }
        });


        viewHolder.imageView.setImageUrl(firebaseImages.get(position), imageLoader);


        viewHolder.imageView.setImageUrl(firebaseImages.get(position), imageLoader);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("firebaseImage", firebaseImages.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        NetworkImageView imageView;
        CheckBox checkBox;


        ViewHolder(View view) {

            imageView = view.findViewById(R.id.image);
            checkBox = view.findViewById(R.id.checkbox);
        }
    }
}

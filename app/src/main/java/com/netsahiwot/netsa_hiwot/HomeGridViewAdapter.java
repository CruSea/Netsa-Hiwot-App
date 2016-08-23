package com.netsahiwot.netsa_hiwot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sammie on 8/5/2016.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private Context cntx;
    private ImageView imgv;
    private TextView txv;

    public HomeGridViewAdapter(Context c) {
        cntx = c;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        LayoutInflater linf = (LayoutInflater) cntx.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        view = linf.inflate(R.layout.idv, null);
        imgv = (ImageView) view.findViewById(R.id.imgb);
        txv = (TextView) view.findViewById(R.id.txv);
        imgv.setImageResource(imgs[position]);
        imgv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        txv.setText(Titles[position]);

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               starter(position + 1);
            }
        });
        txv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            starter(position + 1);
            }
        });

        return view;
    }

    void starter(int position) {
       // starter(true);
        Intent startArticleActivity = new Intent(cntx, ActivityArticles.class);
        startArticleActivity.putExtra("Image", "img" + (position));
        startArticleActivity.putExtra("Txt", "Amanstext" + (position));
        startArticleActivity.putExtra("Title",Titles[position -1]);
        cntx.startActivity(startArticleActivity);
    }
    private String str[] = {"Image 1", "Image 2", "Image 3", "Image 4",
            "Image 5", "Image 6", "Image 7", "Image 8", "Image 9"};

    private int[] imgs = {
            R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6,
            R.drawable.img7, R.drawable.img8,
            R.drawable.img9
    };
    private String [] Titles = {"What is Pornograpy?","Cause of Pornography.","Effects of Pornography.","Is Pornography adiction?"
            ,"Is Pornography a sin?","Article6","Article7","Article8","Article9"};
}

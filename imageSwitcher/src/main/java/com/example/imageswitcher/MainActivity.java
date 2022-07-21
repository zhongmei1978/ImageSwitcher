package com.example.imageswitcher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class MainActivity extends AppCompatActivity {

    List images = new ArrayList();
    ImageSwitcher mSwitcher;
    ViewGroup group;

    ImageView[] tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwitcher = findViewById(R.id.mSwitch);
        group = findViewById(R.id.viewGroup);

        initData();

        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        mSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        mSwitcher.setImageResource((Integer) images.get(0));
        initPointer();
    }



    private void initData() {
        images.add(R.drawable.t1);
        images.add(R.drawable.t2);
        images.add(R.drawable.t3);
        images.add(R.drawable.t1);
        images.add(R.drawable.t2);
        images.add(R.drawable.t3);
    }

    //初始化下面的小圆点的方法
    private void initPointer() {
        //有多少个界面就new多长的数组
        tips = new ImageView[images.size()];

        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置控件的宽高
            imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(255, 255));
            //设置控件的padding属性
            imageView.setPadding(20, 0, 20, 0);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource((Integer) images.get(i));
            imageView.setId(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSwitcher.setImageResource((Integer)images.get(view.getId()));
                }
            });

            tips[i] = imageView;
            group.addView(tips[i]);
        }
    }
}
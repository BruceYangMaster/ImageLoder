package com.bruce.imageloder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        ImageLoader imageLoader = new ImageLoader();
        String url = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1471955819,4035795467&fm=80&w=179&h=119&img.JPEG";
        imageLoader.displayImage(url, imageView);
    }
}

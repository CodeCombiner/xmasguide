package com.alexshcherbyna.communityxmasguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/*import com.xmas.xmas.R;
import com.xmas.xmas.adapters.FullScreenGalleryAdapter;
import com.xmas.xmas.application.Constants;
import com.xmas.xmas.view.HackyViewPager;*/

/**
 * Created by Сергей on 24.04.2015.
 */
public class ActivityFullScreenGallery extends Activity {
/*
    private HackyViewPager viewPager;
    private FullScreenGalleryAdapter adapter;
    private String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        items = getIntent().getExtras().getStringArray(Constants.GALLERY_IMAGE_LINKS);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = (HackyViewPager) findViewById(R.id.pager);
        adapter = new FullScreenGalleryAdapter(ActivityFullScreenGallery.this, items);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(calculatePosition(getIntent().getExtras().getString(Constants.GALLERY_IMAGE_POSITION)));
    }

    private int calculatePosition(String url) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(url)) {
                return i;
            }
        }
        return 0;
    }*/
}
package com.alexshcherbyna.communityxmasguide.hardwareinterfaces;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

/**
 * Created by al on 22.08.15.
 */
public class  CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
    ViewFlipper viewFlipper = null;
    public CustomGestureDetector( ViewFlipper viewFlipper ){
        super();
        this.viewFlipper=viewFlipper;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        viewFlipper.stopFlipping();
        // Swipe left (next)
        if (e1.getX() > e2.getX()) {
            viewFlipper.showNext();
        }

        // Swipe right (previous)
        if (e1.getX() < e2.getX()) {
            viewFlipper.showPrevious();
        }

        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
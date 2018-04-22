package com.alexshcherbyna.communityxmasguide.xmasstuff;

import android.content.Context;
import android.graphics.*;
import android.view.View;

/**
 * Created by al on 24.08.15.
 */
public class PathView extends View{

    Paint _paintSimple, _paintBlur = null;
    Path path = null;

    public PathView(Context context) {
        super(context);

        _paintSimple = new Paint();
        _paintSimple.setAntiAlias(true);
        _paintSimple.setDither(true);
        _paintSimple.setColor(Color.argb(80, 80, 180, 255));
        _paintSimple.setStrokeWidth(12f);
        _paintSimple.setStyle(Paint.Style.STROKE);
        _paintSimple.setStrokeJoin(Paint.Join.ROUND);
        _paintSimple.setStrokeCap(Paint.Cap.ROUND);

        _paintBlur = new Paint();
        _paintBlur.set(_paintSimple);
        _paintBlur.setColor(Color.argb(135, 110, 110, 255));
        _paintBlur.setStrokeWidth(24f);
        _paintBlur.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));

        // rect = new Rect((int)(width*.50),0,(int) x3, (int) y1+50);
        /* path = new Path();
       path.moveTo(0, 6);
       path.lineTo(1500, 6);
        	//path.lineTo(400, 1);
       // path.lineTo(0, 1);
        	path.close();*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // canvas.drawLine(0, 0, getMeasuredWidth(), 0, _paintBlur);

       // for ( int i =10 ; i<=30; i++){
       //     canvas.drawLine(0, i, getMeasuredWidth(), i, _paintBlur);
      //      canvas.drawLine(0, i, getMeasuredWidth(), i, _paintSimple);
      //  }
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), _paintBlur);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), _paintSimple);

      //  canvas.drawPath(path, _paintBlur);
     //   canvas.drawPath(path, _paintSimple);
    }

}

package com.alexshcherbyna.communityxmasguide.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;


public class FontChanger {

	public static FontChanger fontChanger;
	private static int page = 1;
	public FontChanger(){}
	
	public static FontChanger getFontChanger() {
		FontChanger fontChanger = new FontChanger();
        return fontChanger;
    }

	
	
	 public void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
             }
            } else if ((v instanceof TextView ) || (v instanceof EditText ) || (v instanceof Button ) || (v instanceof ViewPager)){

            		 ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/dinnextltarabic_light.ttf"));


            	    }
        } catch (Exception e) {
     }
     }
	 
	 public static void  setTypeface(Context context, TextView v){

    	v.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/dinnextltarabic_light.ttf"));



	 }
	public static TextView  setTVTypeface(Context context, TextView v){

			v.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/dinnextltarabic_light.ttf"));

	return v;
	}
	 
	 public static void  setTypeface(Context context, Paint p){




        p.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/dinnextltarabic_light.ttf"));


	
	 }

	public  static Typeface createTypeFace( String tfName, Context context){

		return Typeface.createFromAsset(context.getAssets(), tfName);
	}


	public static void setDefaultFont(Context context,
									  String staticTypefaceFieldName, String fontAssetName) {
		final Typeface regular = Typeface.createFromAsset(context.getAssets(),
				fontAssetName);
		replaceFont(staticTypefaceFieldName, regular);
	}

	public static void setDefaultFont(Context context,
									  String staticTypefaceFieldName, Typeface newTypeface) {

		replaceFont(staticTypefaceFieldName, newTypeface);
	}

	protected static void replaceFont(String staticTypefaceFieldName,
									  final Typeface newTypeface) {
		try {
			final Field staticField = Typeface.class
					.getDeclaredField(staticTypefaceFieldName);
			staticField.setAccessible(true);
			staticField.set(null, newTypeface);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}


}

package com.alexshcherbyna.communityxmasguide.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment.InstantiationException;
import android.util.DisplayMetrics;

import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;




public class Tools {

	private final String TAG = "Tools";

	public static Toast toast;
	private static String lastToastText;
	static ProgressDialog dialog;



	public static ProgressDialog getProgressDialog() {
		return dialog;
	}

	public static boolean isInteger(String input) {
		return input.matches("-?\\d+(\\.\\d+)?");
	}

	public static boolean checkConnection(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if ((netInfo != null) && (netInfo.isConnectedOrConnecting())) {
			return true;
		}

		return false;
	}
	
	private boolean isNetworkAvailable(Context ctx) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static String getMyAppVersion(Context context) {
		String version = "1.2";

		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;

		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return version;
	}

	// Progress elements
	public static void showToast(Context context, String text) {
		if (text == null || text.isEmpty() || text.equalsIgnoreCase("")) {
			text = "Data is sempty";
		}

		if (toast != null) {
			// toast.cancel();
		}

		if (text != null && text.equals(lastToastText)) {
			toast.show();
		} else {
			lastToastText = text;
			// toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			// toast.show();

			
			 // LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			 //TextView tvTextToast = (TextView) inflater.inflate(0, R.layout.custom_toast_notif); tvTextToast.setText(text);
			 

			// Toast.makeText(mixContext, message, Toast.LENGTH_SHORT).show();

			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			// toast.setView(tvTextToast);
			toast.show();
		}

	}
	
	public static void showToast(Context context, int id) {
		if(context!=null){
		String text = context.getResources().getString(id);
		if (text == null || text.isEmpty() || text.equalsIgnoreCase("")) {
			text = "Data is sempty";
		}

		if (toast != null) {
			// toast.cancel();
		}

		if (text != null && text.equals(lastToastText)) {
			toast.show();
		} else {
			lastToastText = text;
			// toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			// toast.show();

			/*
			 * LayoutInflater inflater = (LayoutInflater)
			 * context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			 * TextView tvTextToast = (TextView) inflater.inflate(0,
			 * R.layout.custom_toast_notif); tvTextToast.setText(text);
			 */

			// Toast.makeText(mixContext, message, Toast.LENGTH_SHORT).show();

			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			// toast.setView(tvTextToast);
			toast.show();
		}
		}
	}

	public static void showOKDialog(Context context, String title, String msg,
			DialogInterface.OnClickListener callbackDialogListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(msg);
		alertDialog.setCancelable(false);
		alertDialog.setButton("OK", callbackDialogListener);
		alertDialog.show();

	}

	/*public static void showOkAndNoDialog(Context context, String msg,
			DialogInterface.OnClickListener callbackOKDialogListener,
			DialogInterface.OnClickListener callbackNODialogListener) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.dialog_app_network_access, null);
		builder.setMessage(msg)
				.setPositiveButton("OK", callbackOKDialogListener)
				.setNegativeButton("Cancel", callbackNODialogListener);
builder.setView(layout);
		builder.create().show();
	}*/
	
	/*public static void showOkAndNoDialog(Context context, String msg,
			final OnClickListener callbackOKDialogListener,
			final OnClickListener callbackNODialogListener) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.dialog_app_network_access, null);
		builder.setCancelable(true);
		
		Button btnSavePassword = (Button) layout
				.findViewById(R.id.btnNetworkOk);
		Button btnDialogCancel = (Button) layout
				.findViewById(R.id.btnNetworkDialogCancel);
		
		builder.setView(layout);
		final AlertDialog alert = builder.create();
		btnSavePassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callbackOKDialogListener.onClick(v);
				alert.dismiss();
			}
		});
		// Setting Negative "NO" Button
		btnDialogCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callbackNODialogListener.onClick(v);
			}
		});
		alert.show();
		FontChanger.getFontChanger().overrideFonts(context,
				alert.getWindow().getDecorView());
		
		FontChanger.setTypeface(context, (TextView) layout.findViewById(R.id.tvInformation));
	}*/
	
	
	

	public static boolean isTurnOffGpsAndWifi(Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (!(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				&& !(locationManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {

			return true;
		}

		return false;
	}
	
	public static boolean isTurnOffNetwork(Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if ( !(locationManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {

			return true;
		}

		return false;
	}

	public static void hideKeyboard(Activity activity) {
		if (activity != null) {
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);

			if (activity.getCurrentFocus() != null) {
				imm.hideSoftInputFromWindow(activity.getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
	}

	public static void showKeyboard(Activity activity) {
		if (activity != null) {
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);

			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 1);
		}
	}



	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	Location sd;

	public static Location getLocation(Context context) {
		// Get the location manager
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String bestProvider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(bestProvider);
		LocationListener loc_listener = new LocationListener() {

			public void onLocationChanged(Location l) {
			}

			public void onProviderEnabled(String p) {
			}

			public void onProviderDisabled(String p) {
			}

			public void onStatusChanged(String p, int status, Bundle extras) {
			}
		};
		locationManager
				.requestLocationUpdates(bestProvider, 0, 0, loc_listener);
		location = locationManager.getLastKnownLocation(bestProvider);
		return location;
	}

	/*public static String getIdDevice(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				AppConstants.PREFS_FILE, 0);
		String dId = prefs.getString(AppConstants.DEVICE_ID, null);
		if (dId == null) {
			dId = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
			prefs.edit().putString(AppConstants.DEVICE_ID, dId).commit();
		}

		return dId;
	}*/

	/*
	 * private void scaleBackground(Bitmap b, View v){
	 * 
	 * if(b.getHeight()>height){ Bitmap newBg = Bitmap.createScaledBitmap(b,
	 * width, (int) (height - getStatusBarHeight()), false);// bmapHeight*hRatio
	 * Drawable newBgDrawable = new BitmapDrawable(getResources(), newBg);
	 * rlProfile.setBackgroundDrawable(newBgDrawable); } else{
	 * rlProfile.setBackgroundDrawable(new BitmapDrawable(getResources(),b)); }
	 * 
	 * 
	 * }
	 */

	@SuppressLint("NewApi")
	public static Bitmap scaleBackground(Bitmap b, Context context, boolean isDefaultImage) {
		/*DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getRealMetrics(metrics);*/
		Point  size    = new Point();
		((Activity) context).getWindowManager().getDefaultDisplay().getRealSize(size);


		//Log.e("Screen Height " + size.y, "Screen width "+ size.x)




		//int height = metrics.heightPixels;
		//int width = metrics.widthPixels;
		int height = size.y;
		int width = size.x;

		// BitmapDrawable bmap = (BitmapDrawable)
		// this.getResources().getDrawable(R.drawable.background_image);
		//float topPanel = getStatusBarHeight(context);
		float bmapWidth = b.getWidth();
		float bmapHeight = b.getHeight();

		float wRatio = width / bmapWidth;
		float hRatio = height / bmapHeight;

		float ratioMultiplier = hRatio;
		// Untested conditional though I expect this might work for landscape
		// mode
		if (hRatio < wRatio) {
			ratioMultiplier = wRatio;
		}

		int newBmapWidth = (int) (bmapWidth * ratioMultiplier);
		int newBmapHeight = (int) (bmapHeight * ratioMultiplier);
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, newBmapWidth, newBmapHeight, false);
		//iView.setLayoutParams(new LinearLayout.LayoutParams(newBmapWidth,newBmapHeight));
		if(!isDefaultImage){

			b.recycle();
		}
		return scaledBitmap;
		/*
		 * if(b.getHeight()>height){ Bitmap newBg = Bitmap.createScaledBitmap(b,
		 * width, (int) (height - getStatusBarHeight()), false);//
		 * bmapHeight*hRatio Drawable newBgDrawable = new
		 * BitmapDrawable(getResources(), newBg);
		 * rlProfile.setBackgroundDrawable(newBgDrawable); } else{
		 * rlProfile.setBackgroundDrawable(new
		 * BitmapDrawable(getResources(),b)); }
		 */

	}
	
	
	public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
	  
	    private int data = 0;
private Bitmap bitmap;
private Context context;
private boolean isDefaultImage;
private ImageView iv;
	    public BitmapWorkerTask(Bitmap bitmap,Context context, boolean isDefaultImage, ImageView iv) {
	       this.bitmap=bitmap;
	       this.context=context;
	       this.isDefaultImage=isDefaultImage;
	       this.iv=iv;
	    }

	    // Decode image in background.
	    @Override
	    protected Bitmap doInBackground(Integer... params) {

	     
	        return scaleBackground(bitmap, context,isDefaultImage);
			
	    }
	    
	    

	    // Once complete, see if ImageView is still around and set bitmap.
	    @Override
	    protected void onPostExecute(Bitmap bitmap) {
	    	
	    	iv.setLayoutParams(new LinearLayout.LayoutParams(bitmap.getWidth(),bitmap.getHeight()));
	    	iv.setImageBitmap(bitmap);
	    	System.gc();
				System.gc();
	    }
	}
	
	

	public static Bitmap scaleAndCut(Bitmap b, Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;

		// BitmapDrawable bmap = (BitmapDrawable)
		// this.getResources().getDrawable(R.drawable.background_image);
		float bmapWidth = b.getWidth();
		float bmapHeight = b.getHeight();

		float wRatio = width / bmapWidth;
		float hRatio = height / bmapHeight;

		float ratioMultiplier = hRatio;
		// Untested conditional though I expect this might work for landscape
		// mode
		if (hRatio < wRatio) {
			ratioMultiplier = wRatio;
		}

		int newBmapWidth = (int) (bmapWidth * ratioMultiplier);
		int newBmapHeight = (int) (bmapHeight * ratioMultiplier);

		// Bitmap newBg = Bitmap.createScaledBitmap(b, width, (int) (height -
		// getStatusBarHeight(context)), false);// bmapHeight*hRatio
		Bitmap newBitmap = Bitmap.createScaledBitmap(b, newBmapWidth,
				newBmapHeight, false);

		Bitmap resultBitmap = Bitmap.createBitmap(newBitmap, 0, 0, width,
				(height - getStatusBarHeight(context)));
		return resultBitmap;

		// iView.setLayoutParams(new LinearLayout.LayoutParams(newBmapWidth,
		// newBmapHeight));
		// iView.setImageBitmap(b);
		/*
		 * if(b.getHeight()>height){ Bitmap newBg = Bitmap.createScaledBitmap(b,
		 * width, (int) (height - getStatusBarHeight()), false);//
		 * bmapHeight*hRatio Drawable newBgDrawable = new
		 * BitmapDrawable(getResources(), newBg);
		 * rlProfile.setBackgroundDrawable(newBgDrawable); } else{
		 * rlProfile.setBackgroundDrawable(new
		 * BitmapDrawable(getResources(),b)); }
		 */

	}

	

	public static boolean hasGps(Context context) {
		PackageManager pm = context.getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
	}
	
	public static boolean isGpsEnabled(Context context) {
		LocationManager mlocManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		return cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}

	public static void turnGPSOn(Activity context) {
		String provider = Secure.getString(
				context.getContentResolver(),
				Secure.LOCATION_PROVIDERS_ALLOWED);

		if (!provider.contains("gps")) { // if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			context.sendBroadcast(poke);
		}
	/*	Another way
	 	Intent intent=new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", true);
		context.sendBroadcast(intent);*/
	}

	public static void turnGPSOff(Activity context) {
		String provider = Secure.getString(
				context.getContentResolver(),
				Secure.LOCATION_PROVIDERS_ALLOWED);
		if (provider.contains("gps")) { // if gps is enabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			context.sendBroadcast(poke);
		}
	}
/*
	public static void netwrorkServicesNotification(final Activity context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.dialog_app_network_access, null);
		builder.setCancelable(true);
		
		Button btnSavePassword = (Button) layout
				.findViewById(R.id.btnNetworkOk);
		Button btnDialogCancel = (Button) layout
				.findViewById(R.id.btnNetworkDialogCancel);
		
		builder.setView(layout);
		final AlertDialog alert = builder.create();
		btnSavePassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				WifiManager wifi = (WifiManager) context
						.getSystemService(Context.WIFI_SERVICE);
				if (!wifi.isWifiEnabled()) {
					wifi.setWifiEnabled(true);
				}
				if(hasGps(context)){
					if(!isGpsEnabled(context)){
				turnGPSOn(context);
					}
				}
				else{
					showToast(context, R.string.no_gps);
				}
				alert.cancel();
			}
		});
		// Setting Negative "NO" Button
		btnDialogCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Write your code here to execute after dialog
				alert.cancel();
				context.finish();
			}
		});
		alert.show();
		*//*FontChanger.getFontChanger().overrideFonts(context,
				alert.getWindow().getDecorView());
		
		*//*
		XMasApplication wapp = (XMasApplication) context.getApplication();
	//	((TextView) layout.findViewById(R.id.tvInformation)).setTypeface(wapp.getSourcesansproemibold());
	//	((TextView) layout.findViewById(R.id.tvAppWillUse)).setTypeface(wapp.getSourcesansprolight());
	}

	*/
	
	
	public static class ExifUtils {
		/*
		  @throws java.lang.InstantiationException
		  @see http://sylvana.net/jpegcrop/exif_orientation.html
		 */
		public static Bitmap rotateBitmap(String src, Bitmap bitmap, boolean isRotated) throws java.lang.InstantiationException {
		    try {
		        int orientation = getExifOrientation(src);

		        if (orientation == 1) {
		            return bitmap;
		        }

		        Matrix matrix = new Matrix();
		        switch (orientation) {
		        case 2:
		            matrix.setScale(-1, 1);
		            break;
		        case 3:
		            matrix.setRotate(180);
		            break;
		        case 4:
		            matrix.setRotate(180);
		            matrix.postScale(-1, 1);
		            break;
		        case 5:
		            matrix.setRotate(90);
		            matrix.postScale(-1, 1);
		            break;
		        case 6:
		            matrix.setRotate(90);
		            isRotated = true;
		            break;
		        case 7:
		            matrix.setRotate(-90);
		            matrix.postScale(-1, 1);
		            break;
		        case 8:
		            matrix.setRotate(-90);
		            break;
		        default:
		            return bitmap;
		        }

		        try {
		            Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0,
		                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		            bitmap.recycle();
		            return oriented;
		        } catch (OutOfMemoryError e) {
		            e.printStackTrace();
		            return bitmap;
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return bitmap;
		}

		private static int getExifOrientation(String src) throws IOException, java.lang.InstantiationException {
		    int orientation = 1;

		    try {
		        /**
		         * if your are targeting only api level >= 5 ExifInterface exif =
		         * new ExifInterface(src); orientation =
		         * exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
		         */
		        if (Build.VERSION.SDK_INT >= 5) {
		            Class<?> exifClass = Class
		                    .forName("android.media.ExifInterface");
		            Constructor<?> exifConstructor = exifClass
		                    .getConstructor(new Class[] { String.class });
		            Object exifInstance = exifConstructor
		                    .newInstance(new Object[] { src });
		            Method getAttributeInt = exifClass.getMethod("getAttributeInt",
		                    new Class[] { String.class, int.class });
		            Field tagOrientationField = exifClass
		                    .getField("TAG_ORIENTATION");
		            String tagOrientation = (String) tagOrientationField.get(null);
		            orientation = (Integer) getAttributeInt.invoke(exifInstance,
		                    new Object[] { tagOrientation, 1 });
		        }
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SecurityException e) {
		        e.printStackTrace();
		    } catch (NoSuchMethodException e) {
		        e.printStackTrace();
		    } catch (IllegalArgumentException e) {
		        e.printStackTrace();
		    } catch (InstantiationException e) {
		        e.printStackTrace();
		    } catch (IllegalAccessException e) {
		        e.printStackTrace();
		    } catch (InvocationTargetException e) {
		        e.printStackTrace();
		    } catch (NoSuchFieldException e) {
		        e.printStackTrace();
		    }

		    return orientation;
		}
	}
	



	public static Bitmap loadBitmapFromView(Context context, View v) {
	    DisplayMetrics dm = context.getResources().getDisplayMetrics(); 
	    v.measure(MeasureSpec.makeMeasureSpec(dm.widthPixels, MeasureSpec.EXACTLY),
	            MeasureSpec.makeMeasureSpec(dm.heightPixels, MeasureSpec.EXACTLY));
	    v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
	    Bitmap returnedBitmap = Bitmap.createBitmap(v.getMeasuredWidth(),
	            v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
	    Canvas c = new Canvas(returnedBitmap);
	    v.draw(c);

	    return returnedBitmap;
	}
	

	
	 public static long getFreeSpace()
		{
		    try
		    {
		        if (Environment.getExternalStorageDirectory() != null
		                && Environment.getExternalStorageDirectory().getPath() != null)
		        {
		            StatFs m_stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		            long m_blockSize = m_stat.getBlockSize();
		            long m_availableBlocks = m_stat.getAvailableBlocks();
		            return (m_availableBlocks * m_blockSize);
		        }
		        else
		        {
		            return 0;
		        }
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		        return 0;
		    }
		}
	 
	 
		public static Bitmap decodeUri(Uri selectedImage, Activity activity) throws FileNotFoundException {

	        // Decode image size
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream( activity.getContentResolver().openInputStream(selectedImage), null, o);

	        // The new size we want to scale to
	        final int REQUIRED_SIZE = 140;

	        // Find the correct scale value. It should be the power of 2.
	        int width_tmp = o.outWidth, height_tmp = o.outHeight;
	        int scale = 1;
	   

	        while(true) {
	            if(width_tmp / 2 < 2000 || height_tmp / 2 < 2000)
	                break;
	            width_tmp /= 2;
	            height_tmp /= 2;
	            scale *= 2;
	        }

	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	        o2.inSampleSize = scale;
	        o2.inPreferredConfig = Bitmap.Config.ARGB_8888;
	        o2.inDither = false;
	        
	        return BitmapFactory.decodeStream( activity.getContentResolver().openInputStream(selectedImage), null, o2);

	    }
		
		
		 @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
		    public static int sizeOf(Bitmap data) {
		        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
		            return data.getRowBytes() * data.getHeight();
		        } else {
		            return data.getByteCount();
		        }
		    }
		 
		 public static int getTotalHeightofListView(ListView listView) {

			    ListAdapter mAdapter = listView.getAdapter();

			    int totalHeight = 0;

			    for (int i = 0; i < mAdapter.getCount(); i++) {
			        View mView = mAdapter.getView(i, null, listView);

			        mView.measure(
			                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),

			                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

			        totalHeight += mView.getMeasuredHeight();
			      //  Log.w("HEIGHT" + i, String.valueOf(totalHeight));

			    }

			   /* ViewGroup.LayoutParams params = listView.getLayoutParams();
			    params.height = totalHeight
			            + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
			    listView.setLayoutParams(params);
			    listView.requestLayout();*/
return totalHeight
        + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
			}
		 
		 
	public static	 String returnNormalTime(String time){
		time = time.substring(0, time.length()-1);
			 DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			 String normalTime = "parsed wrong";
		/*	 final String date = "2010-10-02T12:23:23";
		        final String pattern = "yyyy-MM-dd'T'hh:mm:ss";
		        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);*/
			 try {
				Date date = m_ISO8601Local.parse(time);
				String temp = date.toString();
				temp = temp.substring(0, 16);
				//temp += date.toString().substring(27, 28);
				normalTime = temp +" '"+date.toString().substring(date.toString().length()-2, date.toString().length());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			 return normalTime;
		 }
	
	public static String thousandsToK(String number){
		String stringNumber = number;
		String fromatttedString = number;
		if(stringNumber.length()>3){
			if(stringNumber.length()==4){
				fromatttedString = stringNumber.charAt(0) + "." + stringNumber.charAt(1)+"K";
			}
			else {
				fromatttedString = stringNumber.substring(0, stringNumber.length()-3)+"K";
			}
		}
		
		return fromatttedString;
	}

	
	/*  public static void callFacebookLogoutNew(Context context) {
	        Session session = Session.getActiveSession();
	        if (session != null) {

	            if (!session.isClosed()) {
	                session.closeAndClearTokenInformation();
	                //clear your preferences if saved
	            }
	        } else {

	            session = new Session(context);
	            Session.setActiveSession(session);

	            session.closeAndClearTokenInformation();
	                //clear your preferences if saved

	        }

	    }
	  */
	/* public static Bitmap addWaterMark(Bitmap src, Context context) {
	        int w = src.getWidth();
	        int h = src.getHeight();
	        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
	        Canvas canvas = new Canvas(result);
	        canvas.drawBitmap(src, 0, 0, null);

	        Bitmap waterMark = BitmapFactory.decodeResource(context.getResources(), R.drawable.watermark_logo);
	        canvas.drawBitmap(waterMark, 50, 50, null);

	        return result;
	    }
	 public static int getRealPixels(int dps, Context context){
	int pixels = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, context.getResources().getDisplayMetrics()));
	return pixels;
	 }*/
	 
	 public static boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
		    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
		        if (serviceClass.getName().equals(service.service.getClassName())) {
		            return true;
		        }
		    }
		    return false;
		}
	
	 public static boolean isDownloadManagerAvailable(Context context) {
		    try {
		        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
		            return false;
		        }
		        Intent intent = new Intent(Intent.ACTION_MAIN);
		        intent.addCategory(Intent.CATEGORY_LAUNCHER);
		        intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
		        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
		                PackageManager.MATCH_DEFAULT_ONLY);
		        return list.size() > 0;
		    } catch (Exception e) {
		        return false;
		    }
		}


	public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp;
		if(bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;
		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
				sbmp.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xffa19774;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
				sbmp.getWidth() / 2+0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(sbmp, rect, rect, paint);


		return output;
	}

	public static Bitmap drawableToBitmap (Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable)drawable).getBitmap();
		}

		int width = drawable.getIntrinsicWidth();
		width = width > 0 ? width : 1;
		int height = drawable.getIntrinsicHeight();
		height = height > 0 ? height : 1;

		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}


}

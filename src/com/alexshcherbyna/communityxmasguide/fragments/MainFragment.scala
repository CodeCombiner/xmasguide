package com.alexshcherbyna.communityxmasguide.fragments

import android.graphics.drawable.{BitmapDrawable, GradientDrawable}
import android.graphics.{Bitmap, Color}
import android.os.Bundle
import android.view._
import android.widget.ImageView
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication}
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.imageaware.{ImageAware, ImageViewAware}
import com.nostra13.universalimageloader.core.process.BitmapProcessor
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.scaloid.common._
import org.scaloid.support.v4.SFragment;
/**
  * Created by al on 02.06.15.
  */

class MainFragment extends SFragment{

  var universalImageLoader : ImageLoader = _
 var  options : DisplayImageOptions = _
  var iView: SImageView = _

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    universalImageLoader = 	ImageLoader.getInstance()
    val bp: BitmapProcessor = new BitmapProcessor() {

      override def  process(bmp :Bitmap ) : Bitmap = {
        var newBmp = Tools.scaleBackground(bmp, getActivity, true)
        bmp.recycle()
        newBmp
      }
    }
    options = new DisplayImageOptions.Builder()
          .preProcessor(bp)
      .delayBeforeLoading(0)
          .imageScaleType(ImageScaleType.EXACTLY)
          .bitmapConfig(Bitmap.Config.ARGB_8888)
          .build();
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {


    lazy val xmassapp = getActivity.getApplication.asInstanceOf[XMasApplication]

    //  Log.e("Screen Height "+ size.y, "Screen width "+ size.x)

    val shapeWhite: GradientDrawable = new GradientDrawable()
    shapeWhite.setCornerRadius(2 dip)
    shapeWhite.setColor(Color.WHITE)

    val b : Bitmap = getResources.getDrawable(R.drawable.bg_start).asInstanceOf[BitmapDrawable].getBitmap
    val mainBackground : Bitmap = Tools.scaleBackground(b, getActivity, true)

val rootSrr = new SRelativeLayout{

  iView = new SImageView().adjustViewBounds(true).scaleType(ImageView.ScaleType.CENTER_CROP).<<(MATCH_PARENT, MATCH_PARENT).>>

  this += iView

  val stvGreeting: STextView = new STextView(R.string.welcome).<<.centerInParent.wrap.>>.textSize(40 sp).margin(20 dip).>>.tag("stvGreeting").textColor(Color.WHITE).clickable(false).typeface(xmassapp.getChopInScript)

/*  android:shadowColor="@color/text_shadow"
  android:shadowDx="1"
  android:shadowDy="1"
  android:shadowRadius="2"
  */
  stvGreeting.setShadowLayer(6,3,3,R.color.red_col)
  this += stvGreeting
}

    val imageAware : ImageAware  = new ImageViewAware(iView, false);
    iView.setImageBitmap(universalImageLoader.loadImageSync("drawable://"+R.drawable.bg_start, options))/*, imageAware, options, new ImageLoadingListener() {
      override def onLoadingStarted(imageUri :String , view :View ) { }
      override def onLoadingFailed(imageUri :String , view : View , failReason :FailReason ) { }
      override def onLoadingComplete(imageUri :String , view :View , bitm : Bitmap ) {
        var bm = bitm
        if(bitm==null){
          val  bmap : BitmapDrawable  =  getResources().getDrawable(R.drawable.bg_start).asInstanceOf[BitmapDrawable]
         val bmm = bmap.asInstanceOf[BitmapDrawable].getBitmap()
          bm = Tools.scaleBackground(bmm, getActivity(),true);
          bmm.recycle()
        }
        iView.setImageBitmap(bm);
        System.gc();
        System.gc();
      }
      override def onLoadingCancelled(imageUri :String , view :View ) { }
    });*/

    rootSrr
  }

def startFragment(selectorType: Int): Unit ={

  val bundle: Bundle  = new Bundle()
  bundle.putInt(Constants.FRAGMENT_TYPE, selectorType);

  //getActivity.asInstanceOf[MainActivity].startFragment(new InfoFragment(), "InfoFragment")
}




}

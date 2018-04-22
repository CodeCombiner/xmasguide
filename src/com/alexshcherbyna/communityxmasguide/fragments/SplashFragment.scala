package com.alexshcherbyna.communityxmasguide.fragments

import android.graphics.{Bitmap, Color}
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view._
import com.nostra13.universalimageloader.core.process.BitmapProcessor
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.scaloid.common._
import org.scaloid.support.v4.SFragment

/**
  * Created by al on 02.06.15.
  */

class SplashFragment extends SFragment{

  var universalImageLoader : ImageLoader = _
  var  options : DisplayImageOptions = _
  var iView: SImageView = _

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    universalImageLoader = ImageLoader.getInstance()
    val bp: BitmapProcessor = new BitmapProcessor() {

      override def process(bmp: Bitmap): Bitmap = {
        var newBmp = Tools.scaleBackground(bmp, getActivity, true)
        bmp.recycle()
        newBmp
      }
    }
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {




    //  Log.e("Screen Height "+ size.y, "Screen width "+ size.x)

    val shapeWhite: GradientDrawable = new GradientDrawable()
    shapeWhite.setCornerRadius(2 dip)
    shapeWhite.setColor(Color.WHITE)

new SLinearLayout{

}




  }

def startFragment(selectorType: String): Unit ={

  val bundle: Bundle  = new Bundle()
  bundle.putString(Constants.FRAGMENT_TYPE, selectorType);

  //getActivity.asInstanceOf[SplashScreenActivity].startFragment(new SelectorFragment(), "GeneralFragment")
}




}

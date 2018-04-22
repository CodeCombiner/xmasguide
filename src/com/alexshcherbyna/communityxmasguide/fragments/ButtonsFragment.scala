package com.alexshcherbyna.communityxmasguide.fragments

import android.graphics.drawable.GradientDrawable
import android.graphics.{Bitmap, Color}
import android.os.Bundle
import android.view.{Gravity, LayoutInflater, View, ViewGroup}
import android.widget.ImageView
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication, MainActivity}
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.imageaware.{ImageAware, ImageViewAware}
import com.nostra13.universalimageloader.core.process.BitmapProcessor
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.scaloid.common._
import org.scaloid.support.v4.SFragment

/**
  * Created by al on 02.06.15.
  */

class ButtonsFragment extends SFragment{

  var universalImageLoader : ImageLoader = _
  var  options : DisplayImageOptions = _
  var iView: SImageView = _
  var menuType : Int = 0

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
    options = new DisplayImageOptions.Builder()
      .preProcessor(bp)
      .delayBeforeLoading(0)
      .imageScaleType(ImageScaleType.EXACTLY)
      .bitmapConfig(Bitmap.Config.ARGB_8888)
      .build();
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    // inflater.inflate(R.layout.fragment_main, container, false)
menuType = getArguments.getInt(Constants.FRAGMENT_TYPE)
    lazy val xmassapp = getActivity.getApplication.asInstanceOf[XMasApplication]
    val shapeWhite: GradientDrawable = new GradientDrawable()
    shapeWhite.setCornerRadius(2 dip)
    shapeWhite.setColor(Color.WHITE)
var bitmapResource: Int = 0
  val rootSRL =  new SRelativeLayout {

      style {
        case b: SButton => b.textSize(30 sp).textColor(Color.WHITE).onClick(toast(R.string.in_dev)).background(R.drawable.red_button)
        case t: STextView => t.textSize(30 sp).textSize(10.dip).textColor(Color.WHITE).gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
    //    case e: SEditText => e.textSize(20 sp).textColor(Color.BLACK).hintTextColor(getResources.getColor(R.color.et_hint_grey)).background(shapeWhite).gravity = Gravity.CENTER
      }




      menuType match {
        case Constants.TYPE_ACTIVITY =>

          bitmapResource = R.drawable.activities_2

          iView = new SImageView().adjustViewBounds(true).scaleType(ImageView.ScaleType.CENTER_CROP).<<(MATCH_PARENT, MATCH_PARENT).>>
          this += iView
          this += new SVerticalLayout {
            /*   R.drawable.user*/  SImageView().scaleType(ImageView.ScaleType.FIT_CENTER).<<(30 dip, 30 dip).>>
            STextView(R.string.connect_to_community).<<.wrap.margin(0 , 0 ,0 , 0).>>.textSize(48 sp).typeface(xmassapp.getChristmasSnow)
          }.gravity(Gravity.CENTER).alignParentTop.margin(5 dip, 10 dip, 0 dip, 10 dip).>>;


          this += new SVerticalLayout {
            SButton (R.string.rateplace).<< (MATCH_PARENT, 48 dip).>>.typeface(xmassapp.getChristmasSnow)
            SButton (R.string.vote_place).<< (MATCH_PARENT, 48 dip).>>.marginTop(15 dip).>>.typeface(xmassapp.getChristmasSnow)
            SButton (R.string.findfriends).<< (MATCH_PARENT, 48 dip).>>.marginTop(15 dip).>>.typeface(xmassapp.getChristmasSnow)
            SButton (R.string.checkin).<< (MATCH_PARENT, 48 dip).>>.marginTop(15 dip).>>.typeface(xmassapp.getChristmasSnow)

          }.gravity (Gravity.CENTER).centerInParent.margin (0 dip, 30 dip, 0 dip, 30 dip).>>

        case Constants.TYPE_PLACES =>
          bitmapResource = R.drawable.places_3_small
         iView = new SImageView().adjustViewBounds(true).scaleType(ImageView.ScaleType.CENTER_CROP).<<(MATCH_PARENT, MATCH_PARENT).>>
          this += iView
          this += new SVerticalLayout {
            /*   R.drawable.user*/  SImageView().scaleType(ImageView.ScaleType.FIT_CENTER).<<(30 dip, 30 dip).>>
            STextView(R.string.find_your_place).<<.wrap.margin(0, 0 dip, 0 dip, 0 dip).>>.textSize(48 sp).typeface(xmassapp.getChristmasSnow)
          }.gravity(Gravity.CENTER).alignParentTop.margin(5 dip, 10 dip, 0, 10 dip).>>;

      this += new SVerticalLayout {



        SButton (R.string.allplaces).<< (MATCH_PARENT, 48 dip).>>.onClick(requestAllCountries).typeface(xmassapp.getChristmasSnow).backgroundResource(R.drawable.bgb_long_160)
      SButton (R.string.placesaround).<< (MATCH_PARENT, 48 dip).>>.marginTop(15 dip).>>.typeface(xmassapp.getChristmasSnow).backgroundResource(R.drawable.bgb_long_160)
      SButton (R.string.searchbyname).<< (MATCH_PARENT, 48 dip).>>.marginTop(15 dip).>>.typeface(xmassapp.getChristmasSnow).backgroundResource(R.drawable.bgb_long_160)

      }.gravity (Gravity.CENTER).centerInParent.margin (0 dip, 30 dip, 0 dip, 30 dip).>>


      }
    }.background(android.R.color.transparent)


    val imageAware : ImageAware  = new ImageViewAware(iView, false);
    iView.setImageBitmap(universalImageLoader.loadImageSync("drawable://"+bitmapResource, options))/*, imageAware, options, new ImageLoadingListener() {
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

rootSRL

  }

def getCurrentType : Int = {
  menuType
}

  def requestAllCountries = {

    getActivity.asInstanceOf[MainActivity].startFragment(new CountriesFragment())
  }
  def requestPlacesAround = {
    getActivity.asInstanceOf[MainActivity].startFragment(new CountriesFragment())
  }
  def openSearchFragment = {
    val b = new Bundle()
    b.putInt(Constants.FRAGMENT_TYPE, Constants.SEARCH_PLACES)
    getActivity.asInstanceOf[MainActivity].startFragment(new CountriesFragment(), b)
  }

}

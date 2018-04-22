package com.alexshcherbyna.communityxmasguide.fragments

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import android.graphics.drawable.GradientDrawable
import android.graphics.{Bitmap, Color}
import android.os.Bundle
import android.util.Log
import android.view.{Gravity, LayoutInflater, View, ViewGroup}
import android.widget._
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication}
import com.alexshcherbyna.communityxmasguide.json.{Protocol, Country}
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.utils.IO2014HeaderAnimator
import it.carlom.stikkyheader.core.StikkyHeaderBuilder
import org.scaloid.common._
import org.scaloid.support.v4.SFragment
import spray.json._

import scala.concurrent.{Await, ExecutionContext, Future}

/**
  * Created by al on 02.06.15.
  */

class CountryInfoFragment extends SFragment with SprayJsonSupport{

val s : String = "Lorem ipsum dolor sit amet, vel minimum iracundia ne, ipsum minim insolens nam te. Dico summo aliquip cum no. Cu eos invidunt iracundia, cetero rationibus ne per. Quo in nobis persius hendrerit, eu cum bonorum accusamus. Cum possit complectitur ex. Dicam adversarium no mea. Te mei solum nostrud, augue singulis praesent sit ne."
  var universalImageLoader : ImageLoader = _
  var  options : DisplayImageOptions = _
  var iView: SImageView = _
var country : Country = _
  var progressBar : SProgressBar = _
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    universalImageLoader = ImageLoader.getInstance()

    options = new DisplayImageOptions.Builder()
      .delayBeforeLoading(0)
      .imageScaleType(ImageScaleType.EXACTLY)
      .bitmapConfig(Bitmap.Config.ARGB_8888)
      .build();


    object XmasProtocol3 extends Protocol{}
    import XmasProtocol3._


  val bundle:   Bundle  = this.getArguments();
val countrystr: String = bundle.getString(Constants.COUNTRY,"")
    Log.d("COUNTRY DETAILS", countrystr+"")
    country = countrystr.parseJson.convertTo[Country]

  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    // inflater.inflate(R.layout.fragment_main, container, false)
lazy val xmassapp = getActivity.getApplication.asInstanceOf[XMasApplication]

    val shapeWhite: GradientDrawable = new GradientDrawable()
    shapeWhite.setCornerRadius(2 dip)
    shapeWhite.setColor(Color.WHITE)

    var  animator:  IO2014HeaderAnimator = null
    var ssv: SScrollView = null

    val buttonsPanel: SFrameLayout = new SFrameLayout {

      progressBar = new SProgressBar().marginBottom(60 dip).Gravity(Gravity.CENTER).fill.>>

      this += progressBar

        iView = new SImageView().adjustViewBounds(true).scaleType(ImageView.ScaleType.CENTER_CROP).tag("mapView").<<(MATCH_PARENT, WRAP_CONTENT).marginBottom(60 dip).>>
        this += iView
        // }
        this += new SFrameLayout {
          val llButtonsPanel = new SLinearLayout {
            //If button with grativty.Center wont go then use ll with image and text
            SButton(R.string.rateplace, toast(R.string.in_dev)).<<(0, 68 dip).Weight(1.0f).Gravity(Gravity.CENTER).margin(6 dip).>>.tag("sbtnNavigation").background(R.drawable.red_button).textSize(20 sp).textColor(getResources.getColor(R.color.white)).typeface(xmassapp.getChristmasSnow)
            // SView().background(R.color.drop_down_bg_grey).<<(1 dip, MATCH_PARENT).margin(10 dip, 0, 10 dip, 0 dip).>>

            SButton(R.string.vote_place_to_visit, toast(R.string.in_dev)).<<(0, 68 dip).Weight(2.0f).Gravity(Gravity.CENTER).margin(6 dip).>>.tag("sbtnRefresh").background(R.drawable.red_button).textSize(20 sp).textColor(getResources.getColor(R.color.white)).typeface(xmassapp.getChristmasSnow)
            // SView().background(R.color.drop_down_bg_grey).<<(1 dip, MATCH_PARENT).margin(10 dip, 0, 10 dip, 0 dip).>>

            // SButton(R.string.route, toast("Bang 3")).<<(MATCH_PARENT, 60 dip).Weight(1.0f).Gravity(Gravity.CENTER).>>.tag("sbtnRoute").drawableTop(R.drawable.route).background(R.color.button_bg_blue).typeface(wapp.getSourcesansproemibold).textSize(16 sp).textColor(Color.WHITE)

          }.gravity(Gravity.CENTER).<<(MATCH_PARENT, 80 dip).>>
          this += llButtonsPanel
        }.Gravity(Gravity.BOTTOM).>>.<<(MATCH_PARENT, 80 dip).>>.background(R.color.green)

    }.background(R.color.white).tag("buttonsPanel")

    val layout :  View = new SFrameLayout {



      style {
        case b: SButton => b.textColor(Color.WHITE).onClick(toast(R.string.in_dev)).background(R.color.button_bg_blue)
        case t: STextView => t.textSize(18.dip).textColor(Color.BLACK).gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
      }





      this += buttonsPanel.marginTop(40 dip).>>



    ssv =  new SScrollView {

       this += new SVerticalLayout{
         this += new  STextView(country.description).<<.wrap.margin(10 dip, 0 dip, 0 dip, 0 dip).>>textSize(18 sp)
        // this += new SButton (R.string.rateplace).<< (MATCH_PARENT, 48 dip).margin(10 dip).>>.onClick().backgroundResource(R.drawable.red_button).textColor(Color.WHITE)

       }
     }.marginTop(45 dip).>>

 this += ssv





      //lvRoute.addHeaderView(routesHeaderFull)

     // iView.setImageBitmap(bitm);
      iView.invalidate()
      animator  = new IO2014HeaderAnimator(activity)





      buttonsPanel.bringToFront()


      this += new SRelativeLayout {
        //SImageView(R.drawable.info_drawable).scaleType(ImageView.ScaleType.FIT_CENTER).onClick().<<(10 dip, 10 dip).centerInParent.alignParentRight.>> imageResource (R.drawable.close_dialog) margin(0 dip, 10 dip, 0 dip, 0 dip)
        STextView(country.name).gravity(Gravity.CENTER_VERTICAL | Gravity.LEFT).<<(WRAP_CONTENT, 26 sp).centerInParent.alignParentLeft.margin(0 dip, 0 dip, 0 dip, 10 dip).>>.textSize(20 sp).typeface(xmassapp.getChristmasCard)

      }.<<(MATCH_PARENT, 42 dip).>>.gravity(Gravity.CENTER | Gravity.LEFT).>>.background(R.color.white)


    }.background(R.color.white)


    val loadImageFuture = Future {
      universalImageLoader.loadImageSync(country.imageurl, options)
    }(ExecutionContext.global)

    import scala.concurrent.duration._
    val result = Await.result(loadImageFuture, 8 seconds)
    iView.imageBitmap( result )
   // val imageAware : ImageAware  = new ImageViewAware(iView, false);
   /*, imageAware, new ImageLoadingListener() {


      override def onLoadingStarted(imageUri :String , view :View ) { }
      override def onLoadingFailed(imageUri :String , view : View , failReason :FailReason ) { }
      override def onLoadingComplete(imageUri :String , view :View , bitm : Bitmap ) {


      }
      override def onLoadingCancelled(imageUri :String , view :View ) { }
    });*/
    StikkyHeaderBuilder.stickTo(ssv.asInstanceOf[ScrollView])
      .setHeader(buttonsPanel)
      .minHeightHeader(80 dip)
      .animator(animator)
      .build();



    layout
  }

  override def onResume(): Unit = {
    super.onResume()
  /*  if(!Tools.isOnline(getActivity) || !Tools.isGpsEnabled(getActivity)){
      //Tools.netwrorkServicesNotification(getActivity)
    }

    Tools.netwrorkServicesNotification(getActivity)*/
  }
}

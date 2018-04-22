package com.alexshcherbyna.communityxmasguide.fragments

import android.graphics.drawable.GradientDrawable
import android.graphics.{Bitmap, Color}
import android.os.Bundle
import android.text.InputType
import android.view.{Gravity, LayoutInflater, View, ViewGroup}
import android.widget._
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication}
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.imageaware.{ImageAware, ImageViewAware}
import com.nostra13.universalimageloader.core.process.BitmapProcessor
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.scaloid.common._
import org.scaloid.support.v4.SFragment;
/**
  * Created by al on 02.06.15.
  */

class AddPlaceFragment extends SFragment{

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
    options = new DisplayImageOptions.Builder()
      .preProcessor(bp)
      .delayBeforeLoading(0)
      .imageScaleType(ImageScaleType.EXACTLY)
      .bitmapConfig(Bitmap.Config.ARGB_8888)
      .build();
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    // inflater.inflate(R.layout.fragment_main, container, false)

    lazy val xmassapp = getActivity.getApplication.asInstanceOf[XMasApplication]
    val shapeWhite: GradientDrawable = new GradientDrawable()
    shapeWhite.setCornerRadius(2 dip)
    shapeWhite.setColor(Color.WHITE)





    val fl =
      new SScrollView {

          val srl = new SRelativeLayout {

            style {
              case b: SButton => b.textSize(30 sp).textColor(Color.WHITE).onClick(toast(R.string.in_dev)).background(R.drawable.red_button).typeface(xmassapp.getChristmasSnow)
              case t: STextView => t.textSize(30 sp).textSize(10.dip).typeface(xmassapp.getChristmasSnow).textColor(Color.WHITE).gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
              case e: SEditText => e.textSize(30 sp).textColor(Color.BLACK).hintTextColor(getResources.getColor(R.color.et_hint_grey)).typeface(xmassapp.getChristmasSnow).background(shapeWhite).gravity = Gravity.CENTER
            }
            iView = new SImageView().adjustViewBounds(true).scaleType(ImageView.ScaleType.CENTER_CROP).<<(MATCH_PARENT, MATCH_PARENT).>>
            this += iView

            this += new SVerticalLayout {
           //   SImageView(R.drawable.user).scaleType(ImageView.ScaleType.FIT_CENTER).<<(30 dip, 30 dip).>>
           //   STextView(R.string.personal).<<.wrap.margin(10 dip, 0 dip, 0 dip, 0 dip).>> textSize (22 sp)
            }.gravity(Gravity.CENTER).alignParentTop.margin(80 dip, 30 dip, 0 dip, 30 dip).>>;

            this += new SVerticalLayout {
              SEditText().hint(R.string.country).inputType(InputType.TYPE_CLASS_TEXT).tag("username").<<(MATCH_PARENT, 48 dip).>> textSize 20.sp gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
              SEditText().hint(R.string.city).inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS).tag("email").<<(MATCH_PARENT, 48 dip).>> textSize 20.sp margin(15 dip, 0 dip, 0 dip, 0 dip) gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
              SEditText().hint(R.string.village).inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD).tag("password").<<(MATCH_PARENT, 48 dip).>> textSize 20.sp margin(15 dip, 0 dip, 0 dip, 0 dip) gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
              SEditText().hint(R.string.house).inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD).tag("confirm_password").<<(MATCH_PARENT, 48 dip).>> textSize 20.sp margin(15 dip, 0 dip, 0 dip, 0 dip) gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
              SEditText().hint(R.string.title).inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD).tag("confirm_password").<<(MATCH_PARENT, 48 dip).>> textSize 20.sp margin(15 dip, 0 dip, 0 dip, 0 dip) gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
              SEditText().hint(R.string.description).inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD).tag("confirm_password").<<(MATCH_PARENT, 48 dip).>> textSize 20.sp margin(15 dip, 0 dip, 0 dip, 0 dip) gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)

              SButton(R.string.attach_picture).<<(MATCH_PARENT, 48 dip).>>.onClick(pictureSelector).margin(15 dip, 0 dip, 0 dip, 0 dip)
              SButton(R.string.add).<<(MATCH_PARENT, 48 dip).>>.onClick(addHouse).margin(15 dip, 0 dip, 0 dip, 0 dip)

              // getActivity.asInstanceOf[SplashScreenActivity].startFragment(new EditPersonalProfileFragment(), "EditBusinessProfileFragment")
            }.gravity(Gravity.CENTER).centerInParent.margin(0 dip, 30 dip, 0 dip, 30 dip).>>.padding(0, 80 dip, 0, 0)


          }.background(android.R.color.transparent)
        srl.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT))
this +=srl
      }.fillViewport(true)


    val imageAware : ImageAware  = new ImageViewAware(iView, false);
    iView.setImageBitmap(universalImageLoader.loadImageSync("drawable://"+R.drawable.bg_add_place, options))/*, imageAware, options, new ImageLoadingListener() {
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


    fl

  }

  def addHouse: Unit ={
    toast(R.string.in_dev)
   /*val flRoot : FrameLayout = getActivity.findViewById(R.id.flRoot).asInstanceOf[FrameLayout]
    val ur = new UserRegistration(flRoot.findViewWithTag("username").asInstanceOf[EditText].text.toString, flRoot.findViewWithTag("email").asInstanceOf[EditText].text.toString ,
      flRoot.findViewWithTag("password").asInstanceOf[EditText].text.toString, "personal",
     "", "", "", "")

    if(!Validator.validatePersonalRegistration(ur, flRoot.findViewWithTag("confirm_password").
      asInstanceOf[EditText].text.toString, getActivity)){
      return
    }

    Api.registerUserRequest(JSONFactory.toUserRegisterJson(ur),new RegisterPersonSuccessListener(getActivity.asInstanceOf[SplashScreenActivity]),new RegisterPersonErrorListener(getActivity),  getActivity)
 */
  }
  def  pictureSelector : Unit = {
    toast(R.string.in_dev)
  }
}

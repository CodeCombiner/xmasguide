package com.alexshcherbyna.communityxmasguide.fragments

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import android.graphics.drawable.{Drawable, GradientDrawable}
import android.graphics.{Bitmap, Color}
import android.os.Bundle
import android.view.View.OnTouchListener
import android.view._
import android.widget._
import com.alexshcherbyna.communityxmasguide.json.{Protocol, Country}
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication, MainActivity}
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.imageaware.{ImageAware, ImageViewAware}
import com.nostra13.universalimageloader.core.process.BitmapProcessor
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.adapters.CountriesAdapter
import com.alexshcherbyna.communityxmasguide.constants.Constants

import com.alexshcherbyna.communityxmasguide.listeners.{CitiesDataRequestSuccessListener, ListDataRequestSuccessListener, RegisterPersonErrorListener}
import com.alexshcherbyna.communityxmasguide.utils.{Api, Tools}

import org.scaloid.common._
import org.scaloid.support.v4.SFragment
import spray.json._
/**
  * Created by al on 02.06.15.
  */

class CountriesFragment extends SFragment with SprayJsonSupport{

  var lv : SListView = null
  var mAdapter :CountriesAdapter = null

  var universalImageLoader : ImageLoader = _
  var  options : DisplayImageOptions = _
  var iView: SImageView = _
  var etSearch : EditText = null
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
    lazy val wapp = getActivity.getApplication.asInstanceOf[XMasApplication]
var bitmapResource : Int = 0
    val shapeWhite: GradientDrawable = new GradientDrawable()
    shapeWhite.setCornerRadius(2 dip)
    shapeWhite.setColor(Color.WHITE)
    val menuType = getArguments.getInt(Constants.FRAGMENT_TYPE,0)



   val rootSRL =  new SRelativeLayout {

      style {
        case b: SButton => b.textColor(Color.WHITE)
       // case t: STextView => t.textSize(14.dip).textColor(Color.WHITE).gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
        case e: SEditText => e.textColor(Color.BLACK).hintTextColor(getResources.getColor(R.color.green)).background(shapeWhite).gravity = Gravity.CENTER
      }


      if(menuType == Constants.SEARCH_PLACES){

       etSearch = new EditText(getActivity)
        etSearch.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90 dip))
        etSearch.setHint(R.string.type_to_search)
        /*etSearch.setTypeface(wapp.getSourcesansprolight)
        val  drawableSearchIcon: Drawable = getResources().getDrawable(R.drawable.android_search)
        drawableSearchIcon.setBounds(0, 0, 18 dip, 18 dip)
        etSearch.compoundDrawablePadding(4 dip).setCompoundDrawables(null, null, drawableSearchIcon, null)*/
        etSearch.setBackground(android.R.color.transparent)
        etSearch.setTextSize(22);
        this += etSearch

        etSearch.setOnTouchListener(new OnTouchListener() {
          override def onTouch(v: View, event: MotionEvent): Boolean = {
            val DRAWABLE_LEFT: Int = 0
            val DRAWABLE_TOP: Int = 1
            val DRAWABLE_RIGHT: Int = 2
            val DRAWABLE_BOTTOM: Int = 3

            if (event.getAction == MotionEvent.ACTION_UP) {
              if (event.getRawX >= (etSearch.getRight - (32 dip) - etSearch.getCompoundDrawables.array(DRAWABLE_RIGHT).getBounds().width )) {
                //startFragment(new PlacesFragment(), "PlacesFragment")

                return true;
              }
            }
            return false;
          }
        })

      }
      iView = new SImageView().adjustViewBounds(true).scaleType(ImageView.ScaleType.CENTER_CROP).<<(MATCH_PARENT, MATCH_PARENT).>>

   //   this += bg


       mAdapter = new CountriesAdapter(getActivity, getInstance)

     /* val hardLocations:Vector[(String, String, Drawable)] = Vector[(String, String, Drawable)](("Italy", "FF", getResources.getDrawable(R.drawable.ex_list_loc_bg_1)),("Brazil", "CC", getResources.getDrawable(R.drawable.ex_list_loc_bg_2)),("Spain", "SS", getResources.getDrawable(R.drawable.ex_list_loc_bg_1)),("Title 4", "Address 4", getResources.getDrawable(R.drawable.ex_list_loc_bg_3)),
        ("Title 5", "Address 5", getResources.getDrawable(R.drawable.ex_list_loc_bg_1)),("Title 6", "Address 6", getResources.getDrawable(R.drawable.ex_list_loc_bg_2)),("Title 7", "Address 7",getResources.getDrawable(R.drawable.ex_list_loc_bg_3)),("Title 8", "Address 8", getResources.getDrawable(R.drawable.ex_list_loc_bg_2)),
        ("Title 9", "Address 9", getResources.getDrawable(R.drawable.ex_list_loc_bg_1)),("Title 10", "Address 10", getResources.getDrawable(R.drawable.ex_list_loc_bg_3)),("Title 11", "Address 11", getResources.getDrawable(R.drawable.ex_list_loc_bg_1)))
      //mAdapter.setLocations(hardLocations)*/

    lv = new SListView().adapter(mAdapter).background(android.R.color.black).margin(0 dip, 0 dip, 0 dip, 0 dip).>>.divider(android.R.color.transparent).dividerHeight(0).footerDividersEnabled(false)

      if(etSearch!=null) {
        lv.<<.below(etSearch)

      //  bitmapResource = R.drawable.bg_search_place


        iView.<<.below(etSearch)

      }else{
      //   bitmapResource = R.drawable.bg_places

      }



       lv.setClickable(false)
      lv.setFocusable(false)
      lv.setFocusableInTouchMode(false)

      //lv.setOnItemClickListener(null)
      lv.addHeaderView(new SView().>>.background(android.R.color.transparent).layoutParams(new  AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0 dip )))
      lv.addFooterView(new SView().>>.background(android.R.color.transparent).layoutParams(new  AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0 dip )))

      this += lv
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



def getListView() : ListView = {
  lv.asInstanceOf[ListView]
}

 /* def startFragment(selectorType: Int): Unit ={

    val bundle: Bundle  = new Bundle()
    bundle.putInt(Constants.FRAGMENT_TYPE, selectorType);

    getActivity.asInstanceOf[MainActivity].startFragment(new ListFragment(), bundle)
  }*/


  def getInstance = this


  def openCities(position : Int) = {
   // getActivity.asInstanceOf[MainActivity].startFragment(new ListFragment(), "ListFragment")


    Api.requestCities(new CitiesDataRequestSuccessListener(getActivity.asInstanceOf[MainActivity]),new RegisterPersonErrorListener(getActivity),  getActivity, mAdapter.getItemId(position))

  }

  def countryDetails(position : Int) = {
    val bundle: Bundle  = new Bundle()
    object XmasProtocol2 extends Protocol{}
    import  XmasProtocol2._
    bundle.putString(Constants.COUNTRY, mAdapter.getItem(position: Int).asInstanceOf[Country].toJson.toString);
   getActivity.asInstanceOf[MainActivity].startFragment(new CountryInfoFragment(), bundle)
  }

  override def onResume(): Unit = {
    super.onResume()
     Api.requestCountries(new ListDataRequestSuccessListener(getActivity.asInstanceOf[MainActivity], mAdapter),new RegisterPersonErrorListener(getActivity),  getActivity)

  }
}









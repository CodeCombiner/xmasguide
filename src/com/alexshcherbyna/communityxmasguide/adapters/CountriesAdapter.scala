package com.alexshcherbyna.communityxmasguide.adapters

import android.app.Activity
import android.content.Context
import android.graphics.{Bitmap, Color}
import android.graphics.drawable.{ColorDrawable, Drawable}
import android.util.Log
import android.view.View.OnClickListener
import android.view.{Gravity, View, ViewGroup}
import android.widget.RelativeLayout._
import android.widget._
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication}
import com.alexshcherbyna.communityxmasguide.json.Country
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.imageaware.{ImageViewAware, ImageAware}
import com.nostra13.universalimageloader.core.process.BitmapProcessor
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader}
import com.alexshcherbyna.communityxmasguide.xmasstuff.PathView
import com.alexshcherbyna.communityxmasguide.fragments.CountriesFragment
import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.scaloid.common.{SRelativeLayout, STextView, _}
import org.scaloid.common._
/**
 * Created by al on 11.08.15.
 */
class CountriesAdapter extends BaseAdapter {
var lf :CountriesFragment = _
  var universalImageLoader : ImageLoader = _
  var  options : DisplayImageOptions = _
  var iView: SImageView = _
  var xmassapp : XMasApplication = _
  def this(c: Context, lf : CountriesFragment) {
      this()
      context = c
this.lf = lf
    xmassapp = context.asInstanceOf[Activity].getApplication.asInstanceOf[XMasApplication]
    universalImageLoader = ImageLoader.getInstance()
    options = new DisplayImageOptions.Builder()
      .delayBeforeLoading(0)
      .imageScaleType(ImageScaleType.NONE)
      .bitmapConfig(Bitmap.Config.ARGB_8888)
      .build();

    }

    def getCount: Int = {
      return places.size
    }

    def getItem(position: Int): Country = {
      return places(position)
    }

    def getItemId(position: Int): Long = {
      return places(position).id
    }

    def getView(position: Int, convertView: View, parent: ViewGroup): View = {
      val i: ImageView = new ImageView(context)

      var row :View  = convertView

      if (row == null) {

        row = itemView


        holder = new PlacesHolder()


        holder.stvTitle = row.findViewWithTag("stvTitle").asInstanceOf[STextView]
      //  holder.stvAddress = row.findViewWithTag("stvAddress").asInstanceOf[STextView]

        holder.srlBaseRL = row.findViewWithTag("srlBaseRL").asInstanceOf[SRelativeLayout]
        holder.stvMore = row.findViewWithTag("stvMore").asInstanceOf[STextView]
        holder.stvCities = row.findViewWithTag("stvCities").asInstanceOf[STextView]
        holder.sivBG = row.findViewWithTag("sivBG").asInstanceOf[SImageView]


        row.setTag(holder)
      } else {
        holder =  row.getTag().asInstanceOf[PlacesHolder]
      }

   //   BlockedPerson blockedPerson = listBlockedPerson.get(position);
      holder.stvTitle.text = places(position).name
      //holder.stvAddress.text = places(position)._2
      row.clickable(false)
      holder.srlBaseRL.setOnClickListener(clickListener)
      holder.stvMore.setOnClickListener(clickListener)
      holder.stvCities.setOnClickListener(clickListener)
      //row.setBackground(places(position)._3)
      holder.sivBG.setImageDrawable(new ColorDrawable(R.color.red_col))
      if(places(position).imageurl!=null && !places(position).imageurl.equals("")){
        //holder.ivAvatar.setImageUrl(nearMe.getAvatarUrl(),mImageLoader);
       val imageAware:  ImageAware  = new ImageViewAware( holder.sivBG, false);
        universalImageLoader.displayImage(toSmallUrl(places(position).imageurl), imageAware, options);
      }


      return row

    }

  def toSmallUrl(url :String) : String = {
    val start = url.lastIndexOf("/") + 1
  val smallUrl : String = url.dropRight(url.length-start) + "small/" + url.drop(start)
    Log.d("SMALL URL", ""+smallUrl)
  return smallUrl
  }

    def clearPhotos {
      places =  Vector[Country]()
      notifyDataSetChanged
    }

    def addLocations(locs : Vector[Country]) {
      places = places++locs
      notifyDataSetChanged
    }
  def setLocations(locs : Vector[Country]) {
    places = locs
    notifyDataSetChanged
  }

   // private var mPhotoPool: Array[Integer] = Array(R.drawable.sample_thumb_0, R.drawable.sample_thumb_1, R.drawable.sample_thumb_2, R.drawable.sample_thumb_3, R.drawable.sample_thumb_4, R.drawable.sample_thumb_5, R.drawable.sample_thumb_6, R.drawable.sample_thumb_7)
    private var places: Vector[Country] =  Vector[Country]()
    implicit var context: Context = _
  private var layoutResourceId : Int = _
  private var layout : View = _
  private var holder :PlacesHolder  = _

   class PlacesHolder {

     var stvTitle: STextView = _
     var stvMore: STextView = _
     var stvCities: STextView = _
     var srlBaseRL: SRelativeLayout = _
     var sivBG: SImageView = _


  }

  def itemView: SRelativeLayout  = new SRelativeLayout {

    implicit val contextt: Context = context
    import org.scaloid.common._

    style {
      //   case b: SButton => b.textSize(30 sp).textColor(Color.WHITE).onClick(toast(R.string.in_dev)).background(R.drawable.red_button)
      case t: STextView => t.gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
      //    case e: SEditText => e.textSize(20 sp).textColor(Color.BLACK).hintTextColor(getResources.getColor(R.color.et_hint_grey)).background(shapeWhite).gravity = Gravity.CENTER
    }

    val sivBG: SImageView = new SImageView().scaleType(ImageView.ScaleType.CENTER_CROP).<<.fill.>>.tag("sivBG")

       this += sivBG

    val stvTitle: STextView = new STextView("Title of Location").<<.alignParentLeft.centerVertical.wrap.>>.textSize(42 sp).margin(0 dip, 0 dip, 0 dip, 20 dip).>>.tag("stvTitle").textColor(Color.WHITE).clickable(false).typeface(xmassapp.getChristmasCard)

   this += stvTitle
   // STextView("Address").<<.alignLeft(stvTitle).below(stvTitle).wrap.>>.textSize(14 sp).margin(4 dip, 0 dip, 0 dip, 0 dip).>>.tag("stvAddress").typeface(xmas.getSourcesansprolight).textSize(16 sp)
  this += new SVerticalLayout {
    this += new STextView(R.string.more).Weight(1.0f).>>.<<(100 dip, 0).>>.textSize(30 sp).margin(0 , 0 dip, 4 dip, 0 ).>>.tag("stvMore").textColor(getResources.getColor(R.color.white)).typeface(xmassapp.getChristmasCard).Gravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL).>>
    this += new STextView(R.string.citiesdots).Weight(1.0f).>>.<<(100 dip, 0).>>.textSize(30 sp).margin(4 dip, 0 dip, 0, 0 ).>>.tag("stvCities").textColor(getResources.getColor(R.color.white)).typeface(xmassapp.getChristmasCard).Gravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL).>>
  }.<<(WRAP_CONTENT, MATCH_PARENT).alignParentRight.centerVertical.Gravity(Gravity.CENTER | Gravity.LEFT).>>

    val pv = new PathView(context)
    pv.setBackground(new ColorDrawable(Color.TRANSPARENT))
    val pvlp : RelativeLayout.LayoutParams = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 4 dip )
    pvlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
    pv.setLayoutParams(pvlp)

    this += pv


    this.setLayoutParams(new  AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,220 dip ))
  }.tag("srlBaseRL")


  val clickListener: OnClickListener = new OnClickListener {
    override def onClick(v: View): Unit = {
      if (!Tools.isOnline(context)) {
        Tools.showToast(context, context.getString(R.string.unable_to_establish));
        return;
      }

     val lv: ListView  = lf.getListView()
      val position : Int = lv.getPositionForView(v) - 1;
      if (position != AdapterView.INVALID_POSITION) {

        v.getTag match {
          case "srlBaseRL" => lf.openCities(position)
          case  "stvMore" => lf.countryDetails(position)
          case "stvCities" => lf.openCities(position)
          case  _ => lf.openCities(position)
            Log.d("TAG CLICKED", "IS "+v.getTag)
            //Or add another filling layout inside parent SRL then should be clackable by tag
        }

      }

    }
  }



  }

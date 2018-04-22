package com.alexshcherbyna.communityxmasguide.utils

/**
 * Created by al on 10.06.15.
 */
class Dialogs {

}


object Dialogs {
/*
  def editInfoClassificationDialog(splashScreenActivity: SplashScreenActivity, layout: Int = 0) ={


  implicit val context2: android.content.Context = splashScreenActivity
    lazy val wapp = splashScreenActivity.getApplication.asInstanceOf[XMasApplication]


   // var verticalSpinner: SArrayAdapter  = null

    val layout :  View = new SVerticalLayout {

      val shapeGrey: GradientDrawable = new GradientDrawable()
      shapeGrey.setCornerRadius(2 dip)
      shapeGrey.setColor( splashScreenActivity.getResources.getColor(R.color.drop_down_bg_grey))

      val shapeGrey2: GradientDrawable = new GradientDrawable()
      shapeGrey.setCornerRadius(2 dip)
      shapeGrey.setColor( splashScreenActivity.getResources.getColor(R.color.drop_down_bg_grey))

      style {
        case b: SButton => b.textSize(22 sp).textColor(Color.WHITE).onClick(toast("Bang!")).background(R.drawable.red_button)
        case t: STextView => t.textSize(18.dip).textColor(Color.BLACK).gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
       }

      this += new SLinearLayout {
        SImageView(R.drawable.info_drawable).scaleType(ImageView.ScaleType.FIT_CENTER).<<(30 dip, 30 dip).>> imageResource (R.drawable.register_drawable)
        STextView(R.string.edit_info).gravity(Gravity.CENTER_VERTICAL | Gravity.LEFT).<<(WRAP_CONTENT, 30 dip).margin(0 dip, 10 dip, 0 dip, 10 dip).>>.textSize(18 dip).typeface(wapp.getSourcesansproemibold)

      }.gravity(Gravity.LEFT).margin(10 dip, 10 dip, 0 dip, 10 dip).>>

      val drawable :GradientDrawable  = new GradientDrawable();
      drawable.setColor(Color.TRANSPARENT)
      drawable.setShape(GradientDrawable.RECTANGLE)
      drawable.setStroke(2 dip,getResources.getColor(R.color.drop_down_bg_grey))
      drawable.setSize(MATCH_PARENT, MATCH_PARENT)


      SEditText(R.string.lorem_ipsum_big).gravity(Gravity.LEFT).background(drawable).margin(10 dip, 10 dip, 0 dip, 10 dip).>>.textSize(18 dip).typeface(wapp.getSourcesansprolight).padding(5 dip)

      val arrowDropDown : Drawable = getResources.getDrawable(R.drawable.arrow_drop_down)


val sspinnerAdapter =  SArrayAdapter("Lorem", "Ipsum", "is simply", "dummy" , "text", "of the printing" , "and typesetting").style(_.textColor(Color.BLACK).textSize(22 sp).background(shapeGrey2)).dropDownStyle(_.textSize(22 sp).height(48 dip).gravity(Gravity.CENTER_VERTICAL)).style(t => { t.setCompoundDrawablesWithIntrinsicBounds(null,null,arrowDropDown, null) ;  t }) //.<<(MATCH_PARENT, 48 dip).margin(10 dip, 10 dip, 0 dip, 10 dip).>>


      SSpinner().background(shapeGrey).adapter(sspinnerAdapter).<<(MATCH_PARENT, 48 dip).margin(10 dip, 10 dip, 0 dip, 10 dip).>>

      SButton(R.string.save).<<(MATCH_PARENT, 48 dip).>>.onClick(splashScreenActivity.asInstanceOf[SplashScreenActivity].startFragment(new RegisterAsFragment(), "RegisterAsFragment")).margin(10 dip, 10 dip, 10 dip, 10 dip)


    }

    val builder:  AlertDialog.Builder = new AlertDialog.Builder(splashScreenActivity);
      val inflater : LayoutInflater  = LayoutInflater.from(splashScreenActivity);

      builder.setCancelable(true);
      builder.setView(layout);
     val  alert: AlertDialog = builder.create();
      alert.setOnDismissListener(new OnDismissListener() {


        override def onDismiss( dialog : DialogInterface) {
          if(splashScreenActivity!=null && splashScreenActivity.getActionBar()!=null){
            splashScreenActivity.getActionBar().show();
          }

        }
      })





   val window: Window  = alert.getWindow()
    val wlp: WindowManager.LayoutParams  = window.getAttributes()

    wlp.gravity = Gravity.BOTTOM;

    window.setAttributes(wlp);





      alert.show();
      /*	FontChanger.getFontChanger().overrideFonts(context,
            alert.getWindow().getDecorView());
        tvHeader.setTypeface(Typeface.createFromAsset(context
                .getAssets(), "fonts/unisanslight.otf"));*/



    }

  def routeDialog(activity: Activity, layout: Int = 0) ={

    lazy val wapp = activity.getApplication.asInstanceOf[XMasApplication]
    implicit val context2: android.content.Context = activity

    implicit val activityw: Activity = activity


    val builder:  AlertDialog.Builder = new AlertDialog.Builder(activity);
    val inflater : LayoutInflater  = LayoutInflater.from(activity);

    var  alert: AlertDialog = null





    def itemView: SRelativeLayout  = new SRelativeLayout {

      val sivLogo: SImageView =  SImageView().scaleType(ImageView.ScaleType.FIT_CENTER).<<(16 dip, 16 dip).centerVertical.alignParentLeft.marginLeft(20 dip).>>. imageResource(R.drawable.eye).>>.tag("sivTurn")
      val stvTitle: STextView = STextView("Title of Location").textSize(18 sp).textColor(Color.BLACK).centerVertical.rightOf(sivLogo).margin(20 dip).>>.tag("stvTurnDescription")typeface(wapp.getSourcesansprolight)



       SView().background(R.color.drop_down_bg_grey).<<(MATCH_PARENT, 1 dip).margin(0, 10 dip, 0 , 10 dip).alignParentBottom.>>


      //this.<<.height = 96 dip
      //val params:AbsListView.LayoutParams = new  AbsListView.LayoutParams()
      // params.height = 96 dip
      //   params.width = ViewGroup.LayoutParams.MATCH_PARENT
      //this.setLayoutParams(new  AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,96 dip ))
    }



    val routesHeaderFull: SFrameLayout = new SFrameLayout {




   //  this += new SFrameLayout {
       SImageView(R.drawable.info_drawable).scaleType(ImageView.ScaleType.FIT_CENTER).tag("mapView").<<(MATCH_PARENT, 180 dip).>> imageResource (R.drawable.ex_map_1)
    // }

        this +=  new SFrameLayout {
          val llButtonsPanel = new SLinearLayout {
            //If button with grativty.Center wont go then use ll with image and text
            SButton(R.string.navigation, toast("Bang 1")).<<(MATCH_PARENT, 60 dip).Weight(1.0f).Gravity(Gravity.CENTER).>>.tag("sbtnNavigation").drawableTop(R.drawable.navigation).background(R.color.button_bg_blue).typeface(wapp.getSourcesansproemibold).textSize(16 sp).textColor(Color.WHITE) // try intrinsic bounds if not good
            SView().background(R.color.drop_down_bg_grey).<<(1 dip, MATCH_PARENT).margin(10 dip, 0, 10 dip, 0 dip).>>

            SButton(R.string.refresh, toast("Bang 2")).<<(MATCH_PARENT, 60 dip).Weight(1.0f).Gravity(Gravity.CENTER).>>.tag("sbtnRefresh").drawableTop(R.drawable.refresh).background(R.color.button_bg_blue).typeface(wapp.getSourcesansproemibold).textSize(16 sp).textColor(Color.WHITE)
            SView().background(R.color.drop_down_bg_grey).<<(1 dip, MATCH_PARENT).margin(10 dip, 0, 10 dip, 0 dip).>>

            SButton(R.string.route, toast("Bang 3")).<<(MATCH_PARENT, 60 dip).Weight(1.0f).Gravity(Gravity.CENTER).>>.tag("sbtnRoute").drawableTop(R.drawable.route).background(R.color.button_bg_blue).typeface(wapp.getSourcesansproemibold).textSize(16 sp).textColor(Color.WHITE)

          }.gravity(Gravity.TOP).<<(MATCH_PARENT, 60 dip).>>
          this += llButtonsPanel
        }.tag("buttonsPanel").Gravity(Gravity.BOTTOM).>>.<<(MATCH_PARENT, 60 dip).>>.background(R.color.button_bg_blue)



    }.background(R.color.white)



    val layout :  View = new SFrameLayout {



      style {
        case b: SButton => b.textColor(Color.WHITE).onClick(toast("Bang!")).background(R.color.button_bg_blue)
        case t: STextView => t.textSize(18.dip).textColor(Color.BLACK).gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL)
      }





      this += routesHeaderFull.marginTop(42 dip).>>


      val mAdapter : RouteAdapter = new RouteAdapter(activity, () => itemView)
      val hardLocations:Vector[(String, Drawable)] = Vector[(String, Drawable)](("Head north on Ronda de Sant Antoni toward Carrer de Casanova 350 m", getResources.getDrawable(R.drawable.route_head)),
        ("Continue onto Placa de la Universitat 140m", getResources.getDrawable(R.drawable.route_turn_right)),
          ("Turn right onto Carrer de la Universitat 150m", getResources.getDrawable(R.drawable.route_slight_right)),
            ("Slight left onto la Rambla 230m", getResources.getDrawable(R.drawable.route_slight_left)),
              ("Turn right onto Carrer del Pintor Fortuny 180m", getResources.getDrawable(R.drawable.route_turn_left)),
                ("Turn right onto Carrer del Notariat 97m", getResources.getDrawable(R.drawable.route_head)),
                  ("Description 7", getResources.getDrawable(R.drawable.route_slight_right)),
                    ("Description 8", getResources.getDrawable(R.drawable.route_head)),
                      ("Slight left onto la Rambla 230m", getResources.getDrawable(R.drawable.route_turn_left)),
                        ("Turn right onto Carrer de la Universitat 150m", getResources.getDrawable(R.drawable.route_head)),
                          ("Continue onto Placa de la Universitat 140m", getResources.getDrawable(R.drawable.route_slight_right)),
                            ("Description 12", getResources.getDrawable(R.drawable.route_slight_left)),
                              ("Description 13", getResources.getDrawable(R.drawable.route_slight_right)),
                                ("Turn right onto Carrer del Pintor Fortuny 180m", getResources.getDrawable(R.drawable.route_slight_left)))







val fl : FrameLayout = new FrameLayout(activity)
      fl.setLayoutParams(new android.widget.AbsListView.LayoutParams(MATCH_PARENT, WRAP_CONTENT))


      val lvRoute : SListView = new SListView().margin(42 dip, 0 dip, 0 dip, 0 dip).>>.divider(null)
      val btnSaveLoc: Button = new Button(activity)

      btnSaveLoc.setText(R.string.save_loc)
      btnSaveLoc.setBackground(R.drawable.red_button)

      //btnSaveLoc.setLayoutParams(new android.widget.AbsListView.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
      btnSaveLoc.margin(10 dip).>>.textSize(22 dip).textColor(Color.WHITE)
      fl.addView(btnSaveLoc)

      lvRoute.addFooterView(fl)


      this += lvRoute
      //lvRoute.addHeaderView(routesHeaderFull)
      val  animator:  IO2014HeaderAnimator  = new IO2014HeaderAnimator(activity)

      StikkyHeaderBuilder.stickTo(lvRoute.asInstanceOf[ListView])
        .setHeader(routesHeaderFull)
        .minHeightHeader(60 dip)
        .animator(animator)
        .build();



      lvRoute.adapter(mAdapter)
      mAdapter.setRoutes(hardLocations)
      mAdapter.notifyDataSetChanged()

      routesHeaderFull.bringToFront()


      this += new SRelativeLayout {
        SImageView(R.drawable.info_drawable).scaleType(ImageView.ScaleType.FIT_CENTER).onClick(alert.dismiss()).<<(10 dip, 10 dip).centerInParent.alignParentRight.>> imageResource (R.drawable.close_dialog) margin(0 dip, 10 dip, 0 dip, 0 dip)
        STextView("Directions to Los Padlos (1.4km)").gravity(Gravity.CENTER_VERTICAL | Gravity.LEFT).<<(WRAP_CONTENT, 30 dip).centerInParent.alignParentLeft.margin(0 dip, 0 dip, 0 dip, 10 dip).>>.textSize(18 dip).typeface(wapp.getSourcesansproemibold)

      }.<<(MATCH_PARENT, 42 dip).>>.gravity(Gravity.CENTER | Gravity.LEFT).>>.background(R.color.white)


    }.background(R.color.white)


    builder.setCancelable(true);
    builder.setView(layout);
    alert = builder.create();
    alert.setOnDismissListener(new OnDismissListener() {


      override def onDismiss( dialog : DialogInterface) {
        if(activity!=null && activity.getActionBar()!=null){
          activity.getActionBar().show();
        }

      }
    })





    val window: Window  = alert.getWindow()
    val wlp: WindowManager.LayoutParams  = window.getAttributes()

    wlp.gravity = Gravity.BOTTOM;
    // wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
    wind*//*ow.setAttributes(wlp);





    alert.show();*/
    /*	FontChanger.getFontChanger().overrideFonts(context,
          alert.getWindow().getDecorView());
      tvHeader.setTypeface(Typeface.createFromAsset(context
              .getAssets(), "fonts/unisanslight.otf"));



  }
*/
}

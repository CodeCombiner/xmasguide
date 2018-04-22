package com.alexshcherbyna.communityxmasguide

import android.graphics.Typeface
import com.alexshcherbyna.communityxmasguide.utils.FontChanger

/**
 * Created by al on 25.06.15.
 */
class XMasApplication extends android.app.Application {

  lazy val chopInScript  : Typeface = FontChanger.createTypeFace("fonts/chscr.ttf", this)
  lazy val christmasSnow  : Typeface = FontChanger.createTypeFace("fonts/chrsn.ttf", this)
  lazy val christmasCard  : Typeface = FontChanger.createTypeFace("fonts/chrca.ttf", this)


  override def onCreate(): Unit = {
    super.onCreate()




    //FontChanger.setDefaultFont(this, "MONOSPACE", franchiseBold);
   /* FontChanger.setDefaultFont(this, "MONOSPACE", "MyFontAsset2.ttf");
    FontChanger.setDefaultFont(this, "SERIF", "MyFontAsset3.ttf");
    FontChanger.setDefaultFont(this, "SANS_SERIF", "MyFontAsset4.ttf");*/


  }



  def getChopInScript = chopInScript
  def getChristmasSnow = christmasSnow
  def getChristmasCard = christmasCard

}

package com.alexshcherbyna.communityxmasguide.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.{View, ViewGroup}
import android.widget.{AbsListView, BaseAdapter}
import com.alexshcherbyna.communityxmasguide.{R, XMasApplication}
import org.scaloid.common._

/**
 * Created by al on 11.06.15.
 */
class DrawerAdapter extends BaseAdapter {
  //var getListItemView: () => View = () => new TextView(context).asInstanceOf[View]
  implicit lazy val xmasapp = context.asInstanceOf[Activity].getApplication.asInstanceOf[XMasApplication]
  var xmassapp : XMasApplication = _
  def this(c: Context) {  //, getListItemView: () => View
      this()
      context = c
    xmassapp = context.asInstanceOf[Activity].getApplication.asInstanceOf[XMasApplication]
    //  this.getListItemView =  getListItemView
    }

    def getCount: Int = {
      return menuItems.size
    }

    def getItem(position: Int): AnyRef = {
      return menuItems(position)
    }

    def getItemId(position: Int): Long = {
      return position.toLong
    }

    def getView(position: Int, convertView: View, parent: ViewGroup): View = {

      var row :View  = convertView

      if (row == null) {

        //row = getListItemView.apply()
          row = drawerView

        holder = new MenuItemHolder()


        holder.stvItemTitle = row.findViewWithTag("stvItemTitle").asInstanceOf[STextView]


        holder.sivIcon = row.findViewWithTag("sivIcon").asInstanceOf[SImageView]



        row.setTag(holder)
      } else {
        holder =  row.getTag().asInstanceOf[MenuItemHolder]
      }


     /* if(position==0){
        holder.stvTurnDescription.typeface(wapp.getSourcesansproemibold)
      }
      else{
        holder.stvTurnDescription.typeface(wapp.getSourcesansprolight)
      }*/

   //   BlockedPerson blockedPerson = listBlockedPerson.get(position);
      holder.stvItemTitle.text = menuItems(position)._1
    
      holder.sivIcon.imageResource(menuItems(position)._2)
    


      return row

    }




    def clearRoutes {
      menuItems =  List[(String, Int)]()
      notifyDataSetChanged
    }

    def addMenuItems(menuItemss : List[(String, Int)]) {
      menuItems = menuItems++menuItemss
      notifyDataSetChanged
    }
  def setMenuItems(locs : List[(String, Int)]) {
    menuItems = locs
    notifyDataSetChanged
  }

     private var menuItems: List[(String, Int)] =  List[(String, Int)]()
    private var context: Context = _
  private var holder :MenuItemHolder  = _

   class MenuItemHolder {

     var stvItemTitle: STextView = _
     var sivIcon: SImageView = _
   



  }





  def drawerView: SRelativeLayout  = new SRelativeLayout {


    val stvItemTitle: STextView = STextView("Title of Location").<<(MATCH_PARENT,WRAP_CONTENT).>>.textSize(38 sp).textColor(Color.WHITE).centerInParent.alignParentLeft.margin(0, 4 dip, 0 , 16 dip).>>.tag("stvItemTitle").typeface(xmassapp.getChristmasSnow)
    val sivIcon: SImageView = SImageView().<<(WRAP_CONTENT ,MATCH_PARENT).centerInParent.alignParentRight.margin(8 dip, 8 dip, 8 dip, 8 dip).>>.tag("sivIcon")
val line : SView = new SView().background(R.color.et_hint_grey).<<( MATCH_PARENT, 1 dip).alignParentBottom.margin(0,8 dip, 0, 8 dip).>>
    this.setLayoutParams(new  AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60 dip ))
  }

}

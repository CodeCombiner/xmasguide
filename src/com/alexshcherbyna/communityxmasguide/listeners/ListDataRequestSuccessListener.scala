package com.alexshcherbyna.communityxmasguide.listeners

/**
 * Created by al on 20.07.15.
 */;
//import android.util.Log;;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import android.util.Log
import android.widget.BaseAdapter
import com.alexshcherbyna.communityxmasguide.MainActivity
import com.alexshcherbyna.communityxmasguide.json.{Protocol, Country, Countries}

import com.android.volley.Response
import com.alexshcherbyna.communityxmasguide.adapters.CountriesAdapter
import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.json.JSONObject
import spray.json._


class ListDataRequestSuccessListener(activity  : MainActivity, adapter: BaseAdapter) extends Response.Listener[JSONObject] with SprayJsonSupport { //with Protocol


 object XmasProtocol extends Protocol {

   implicit object CountriesFormat extends RootJsonFormat[Countries] {
     def write(pch: Countries) = JsObject("countries" -> "countries".toJson)
     /*JsObject(
       "countries" -> JsString(pch.name),
       "description" -> JsString(pch.description),
       "imageurl" -> JsString(pch.imageurl),
       "index" -> JsString(pch.index))
*/

     def read(value: JsValue) =  //value.convertTo[Countries]

      value  match {
        /*case Seq(JsArray(countries)) =>
          new Countries(countries.map(_.convertTo[Country])(collection.breakOut).toVector)
        case JsArray(countries) =>
          new Countries(countries.map(_.convertTo[Country])(collection.breakOut).toVector)
          case Seq(JsObject(countries)) => Tools.showToast(activity, "OBJECT IN SEQ")
            new Countries(Vector[Country]())
        case JsObject(countries) => Tools.showToast(activity, "OBJECT")
          new Countries(Vector[Country]())
*/
        case JsString(countries) => countries.parseJson.asJsObject.getFields("countries")match {
                       case Seq(JsArray(names)) => new Countries(names.map(_.convertTo[Country])(collection.breakOut).toVector)

                       case _ => Tools.showToast(activity,""+"Not Array")
                         new Countries(Vector[Country]())
        }
          case _ => Tools.showToast(activity,""+value.getClass.toString)
          throw new DeserializationException("BaseError object expected")
      }

   }

  }



  override def onResponse(response: JSONObject): Unit = {

Log.d("Array response", ""+response)
  val stringOfJson = response.toString
    import XmasProtocol._
    val countries = stringOfJson.toJson
    Log.d("JsValue", ""+countries.toString)
   val vecCountries: Countries =  countries.convertTo[Countries]
    adapter.asInstanceOf[CountriesAdapter].setLocations(vecCountries.countries)



}

}

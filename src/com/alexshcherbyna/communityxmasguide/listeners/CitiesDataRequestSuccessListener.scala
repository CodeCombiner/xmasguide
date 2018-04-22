package com.alexshcherbyna.communityxmasguide.listeners

/**
 * Created by al on 20.07.15.
 */;
//import android.util.Log;;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import android.os.Bundle
import android.util.Log
import com.alexshcherbyna.communityxmasguide.MainActivity
import com.alexshcherbyna.communityxmasguide.json.{Protocol, City}
import com.android.volley.Response
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.fragments.CitiesFragment

import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.json.JSONObject
import spray.json._

case class Cities(cities: Vector[City])

class CitiesDataRequestSuccessListener(activity  : MainActivity) extends Response.Listener[JSONObject] with SprayJsonSupport { //with Protocol


 object XmasProtocol extends Protocol {
   implicit object CitiesFormat extends RootJsonFormat[Cities] {
     def write(pch: Cities) = JsObject("cities" -> "cities".toJson)
     def read(value: JsValue) =
      value  match {
        case JsString(countries) => countries.parseJson.asJsObject.getFields("cities")match {
                       case Seq(JsArray(names)) => new Cities(names.map(_.convertTo[City])(collection.breakOut).toVector)
                       case _ => Tools.showToast(activity,""+"Not Array")
                         new Cities(Vector[City]())
        }
          case _ => Tools.showToast(activity,""+value.getClass.toString)
          throw new DeserializationException("BaseError object expected")
      }
   }
  }



  override def onResponse(response: JSONObject): Unit = {
Log.d("Array response", ""+response)
    val bundle: Bundle  = new Bundle()
    bundle.putString(Constants.CITIES, response.toString);
   activity.asInstanceOf[MainActivity].startFragment(new CitiesFragment(), bundle)
}

}

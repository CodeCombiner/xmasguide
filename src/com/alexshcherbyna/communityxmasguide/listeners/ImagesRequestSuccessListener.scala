package com.alexshcherbyna.communityxmasguide.listeners

/**
 * Created by al on 20.07.15.
 */;
//import android.util.Log;;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import android.util.Log
import com.alexshcherbyna.communityxmasguide.MainActivity
import com.alexshcherbyna.communityxmasguide.json.{Protocol, City}
import com.android.volley.Response
import com.alexshcherbyna.communityxmasguide.fragments.CityInfoFragment

import com.alexshcherbyna.communityxmasguide.utils.Tools
import org.json.{JSONArray, JSONObject}
import spray.json._

import scala.collection.mutable


class ImagesRequestSuccessListener(activity  : MainActivity, fragment : CityInfoFragment) extends Response.Listener[JSONObject] with SprayJsonSupport {

  //with Protocol


  object XmasProtocol extends Protocol {

    implicit object CitiesFormat extends RootJsonFormat[Cities] {
      def write(pch: Cities) = JsObject("cities" -> "cities".toJson)

      def read(value: JsValue) =
        value match {
          case JsString(countries) => countries.parseJson.asJsObject.getFields("cities") match {
            case Seq(JsArray(names)) => new Cities(names.map(_.convertTo[City])(collection.breakOut).toVector)
            case _ => Tools.showToast(activity, "" + "Not Array")
              new Cities(Vector[City]())
          }
          case _ => Tools.showToast(activity, "" + value.getClass.toString)
            throw new DeserializationException("BaseError object expected")
        }
    }

  }


  override def onResponse(response: JSONObject): Unit = {
    Log.d("Array response", "" + response)


      val resultArray: JSONArray = response
        .getJSONArray("items");


      fragment.setSliderImages(getImageList(resultArray))


  }

  def  getImageList (resultArray :JSONArray ) : List[String] = {
		val listImages : mutable.MutableList[String]  = new mutable.MutableList[String]();

			for (i <- 0 to resultArray.length()-1) {
			val	obj : JSONObject = resultArray.getJSONObject(i);

				listImages += obj.getString("link")
			}
			return listImages.toList;



	}

   class GoogleImageBean
  {
   var thumbUrl :  String =_
   var  title :String=_

   def getThumbUrl() : String =
     {
      return thumbUrl;
    }

    def setThumbUrl(url :String )=
    {
      this.thumbUrl = url;
    }

    def getTitle():String=
    {
      return title;
    }

    def setTitle(title :String )=
    {
      this.title = title;
    }
  }

}

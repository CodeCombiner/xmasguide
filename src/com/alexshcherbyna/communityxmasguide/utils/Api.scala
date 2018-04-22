package com.alexshcherbyna.communityxmasguide.utils

import java.util

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.volley.toolbox.{JsonObjectRequest, Volley}
import com.android.volley.{NetworkResponse, Request, RequestQueue, Response}
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.listeners.RegisterPersonErrorListener
import org.json.JSONObject;
/**
 * Created by al on 20.07.15.
 */
object Api {
  def requestImages(keyword: String,successListener: Response.Listener[JSONObject], errorListener: RegisterPersonErrorListener, context: Activity): Unit = {
   val url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyBFFwemfBq7xqbMcmilP_pW8J6nXEF46jw&cx=000402806797667103819:w5kz3819q94&searchType=image&q=" + keyword + "&alt=json";


      if(queue == null){
        queue = Volley.newRequestQueue(context)
      }
    val getCountriesRequestURLService :  String = url
    val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(getCountriesRequestURLService,
      null, successListener, errorListener) {
      override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");
        return headers
      }
      override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        return super.parseNetworkResponse(response);
      }
    }

    queue.add(jsObjRequest);

  }


  // private final String TAG = "API";


  // private Http http = Http.getInstance();
  // private XMLParser xmlParser = XMLParser.getInstance();
  // private UtilDB db = null;
  private val serverURL: String  = Constants.SERVER_URL



  implicit var queue: RequestQueue = _


 def getInstance(context : Context)  = {
   clearQueue
    queue = Volley.newRequestQueue(context);
this
  }

  def getInstance  = {
    this

  }


  def clearQueue : Unit= {
    queue.cancelAll(new RequestQueue.RequestFilter() {


   override def apply(request: Request[_]):  Boolean = {
        // TODO Auto-generated method stub
        true
      }
    })
  }


  def getQueue(): RequestQueue = {
   queue
  }

def requestCountries (successListener: Response.Listener[JSONObject],errorListener: Response.ErrorListener,  context: Context)= {
   if(queue == null){
     queue = Volley.newRequestQueue(context)
   }
    val getCountriesRequestURLService :  String = serverURL + "countries"
   val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(getCountriesRequestURLService,
     null, successListener, errorListener) {
       override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");
        return headers
      }
     override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        return super.parseNetworkResponse(response);
      }
    }
 /* val requestBody =  new String( jsObjRequest.getBody.takeWhile(_ != 0), "UTF-8" )
   Log.d("requestBody", ""+requestBody)*/
    queue.add(jsObjRequest);
  }

  def requestCities (successListener: Response.Listener[JSONObject],errorListener: Response.ErrorListener,  context: Context, countryid: Long)= {
    if(queue == null){
      queue = Volley.newRequestQueue(context)
    }
    val getCountriesRequestURLService :  String = serverURL + "cities/"+countryid
    val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(getCountriesRequestURLService,
      null, successListener, errorListener) {
      override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");
        return headers
      }
      override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        return super.parseNetworkResponse(response);
      }
    }
    /* val requestBody =  new String( jsObjRequest.getBody.takeWhile(_ != 0), "UTF-8" )
      Log.d("requestBody", ""+requestBody)*/
    queue.add(jsObjRequest);
  }

  def requestCountry (successListener: Response.Listener[JSONObject],errorListener: Response.ErrorListener,  context: Context, countryid: Int)= {
    if(queue == null){
      queue = Volley.newRequestQueue(context)
    }
    val getCountriesRequestURLService :  String = serverURL + "country/"+countryid
    val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(getCountriesRequestURLService,
      null, successListener, errorListener) {
      override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");
        return headers
      }
      override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        return super.parseNetworkResponse(response);
      }
    }
    /* val requestBody =  new String( jsObjRequest.getBody.takeWhile(_ != 0), "UTF-8" )
      Log.d("requestBody", ""+requestBody)*/
    queue.add(jsObjRequest);
  }

  def requestCity (successListener: Response.Listener[JSONObject],errorListener: Response.ErrorListener,  context: Context, countryid: Int, cityid: Int)= {
    if(queue == null){
      queue = Volley.newRequestQueue(context)
    }
    val getCountriesRequestURLService :  String = serverURL + "city/"+countryid+"/"+cityid
    val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(getCountriesRequestURLService,
      null, successListener, errorListener) {
      override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");
        return headers
      }
      override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        return super.parseNetworkResponse(response);
      }
    }
    /* val requestBody =  new String( jsObjRequest.getBody.takeWhile(_ != 0), "UTF-8" )
      Log.d("requestBody", ""+requestBody)*/
    queue.add(jsObjRequest);
  }

  def requestAround = ???

  def requestSearch = ???

  def requestTopRated = ???
  def requestRate = ???
  def requestFriends = ???
  def requestCheckIn = ???
  def requestVote = ???
  def requestAddPlace = ???
  def registerUserRequest(urjsonobj : JSONObject,
     successListener: Response.Listener[JSONObject],
     errorListener: Response.ErrorListener,  context: Context)
 {



   if(queue == null){
     queue = Volley.newRequestQueue(context)
   }
    /*ProgressDialog progressDialog = Tools.getProgressDialog();
    if(progressDialog==null || !progressDialog.isShowing()){
      progressDialog = Tools.startProgressDialog(context);
    }*/

    val registerRequestURLService :  String = serverURL + "/user "
   val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, registerRequestURLService,
     urjsonobj, successListener, errorListener) {
       override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");
        return headers
      }

     override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        // TODO Auto-generated method stub
        return super.parseNetworkResponse(response);
      }

    };
    /*jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
      AppConstants.PROLONGED_TIMEOUT,
      AppConstants.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
  val requestBody =  new String( jsObjRequest.getBody.takeWhile(_ != 0), "UTF-8" )
   Log.d("requestBody", ""+requestBody)
    queue.add(jsObjRequest);
  }




  def getUserProfile(urjsonobj : JSONObject,
                          successListener: Response.Listener[JSONObject],
                          errorListener: Response.ErrorListener,  context: Context)
  {



    if(queue == null){
      queue = Volley.newRequestQueue(context)
    }
    /*ProgressDialog progressDialog = Tools.getProgressDialog();
    if(progressDialog==null || !progressDialog.isShowing()){
      progressDialog = Tools.startProgressDialog(context);
    }*/



    val getUserProfileURLService :  String = serverURL + "/user "





    val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(Request.Method.GET, getUserProfileURLService,
      urjsonobj, successListener, errorListener) {


      override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");

       /* val prefs : SharedPreferences  = context.getSharedPreferences(Constants.XMAS, Context.MODE_PRIVATE);
        val auth : String = prefs.getString(Constants.BASIC_AUTH, "AuthNotFound");
        headers.put("Authorization", auth);*/

        return headers
      }


      override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        // TODO Auto-generated method stub
        return super.parseNetworkResponse(response);
      }

    };

    /*jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
      AppConstants.PROLONGED_TIMEOUT,
      AppConstants.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

    queue.add(jsObjRequest);

  }


  def changePasswordRequest(urjsonobj : JSONObject,
                          successListener: Response.Listener[JSONObject],
                          errorListener: Response.ErrorListener,  context: Context)
  {



    if(queue == null){
      queue = Volley.newRequestQueue(context)
    }
    /*ProgressDialog progressDialog = Tools.getProgressDialog();
    if(progressDialog==null || !progressDialog.isShowing()){
      progressDialog = Tools.startProgressDialog(context);
    }*/

    /*val prefs : SharedPreferences  = context.getSharedPreferences(Constants.XMAS, Context.MODE_PRIVATE);
    val auth : String = prefs.getString(Constants.BASIC_AUTH, "AuthNotFound");
    val userID : String = prefs.getString(Constants.USER_ID, "UserIDNotFound");*/


    //val registerRequestURLService :  String = serverURL + "/user/"+userID





    /*val jsObjRequest: JsonObjectRequest  = new JsonObjectRequest(Request.Method.PATCH, registerRequestURLService,
      urjsonobj, successListener, errorListener) {


      override def getHeaders : util.HashMap[String, String]  = {
        val headers: util.HashMap[String, String]  = new util.HashMap[String, String]
        headers.put("Content-type", "application/json");

       // headers.put("Authorization", auth);
        return headers
      }


      override def parseNetworkResponse(response: NetworkResponse ): Response[JSONObject] = {
        // TODO Auto-generated method stub
        return super.parseNetworkResponse(response);
      }

    };*/

    /*jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
      AppConstants.PROLONGED_TIMEOUT,
      AppConstants.DEFAULT_MAX_RETRIES,
      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

   /* val requestBody =  new String( jsObjRequest.getBody.takeWhile(_ != 0), "UTF-8" )
    Log.d("requestBody", ""+requestBody)
    queue.add(jsObjRequest);*/

  }

}

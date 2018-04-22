package com.alexshcherbyna.communityxmasguide.listeners

import android.content.Context
import android.util.Log
import com.android.volley.{Response, VolleyError}
import com.alexshcherbyna.communityxmasguide.utils.Tools

/**
 * Created by al on 20.07.15.
 */
class RegisterPersonErrorListener(context  : Context) extends Response.ErrorListener {


  override def onErrorResponse(volleyError: VolleyError): Unit = {

    Tools.showToast(context, "Error")
    if (volleyError != null && !volleyError.equals("") && volleyError.networkResponse != null && !volleyError.networkResponse.equals("")) {
      val message = new String(volleyError.networkResponse.data.takeWhile(_ != 0), "UTF-8")
      if (message != null && !message.equals("")) {
        if (message != null) {
          Log.d("Error message", "" + message)
        }
        /* val baseError : BaseError = JSONFactory.fromBaseErrorJson(message)
    Tools.showToast(context, baseError.message);*/
      }
    }
  }

}

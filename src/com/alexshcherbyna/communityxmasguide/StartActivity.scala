package com.alexshcherbyna.communityxmasguide

import org.scaloid.common._

class StartActivity extends SActivity {



  onCreate {
  // startActivity(SIntent[SplashScreenActivity])
    startActivity(SIntent[MainActivity])
    finish
  }

}

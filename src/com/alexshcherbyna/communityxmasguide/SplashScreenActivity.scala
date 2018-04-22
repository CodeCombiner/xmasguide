package com.alexshcherbyna.communityxmasguide

import android.content.Context
import android.os.Bundle
import android.support.v4.app
import android.support.v4.app.{Fragment, FragmentTransaction}
import com.alexshcherbyna.communityxmasguide.fragments.SplashFragment
import org.scaloid.support.v4.SFragmentActivity
class SplashScreenActivity extends SFragmentActivity {

  lazy implicit  val context : Context = this

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
implicit def ddd= scala.language.postfixOps
    setContentView(R.layout.activity_empty);

    if (savedInstanceState == null) {



      startFragment(new SplashFragment(), "SplashFragment") //New Bundle
    }
    else{
      //Here when rotate screen
      /*if(getIntent!=null & !getIntent.getStringExtra(Constants.FRAGMENT_TO_START).equals("")){
        val fragClassName: String = getIntent.getStringExtra(Constants.FRAGMENT_TO_START)
        startFragment( Class.forName("com.example"+fragClassName).newInstance().asInstanceOf[Fragment], Constants.FRAGMENT_TO_START)
      }*/
    }
  }

  def startFragment( fragment:Fragment,  tag :String , bundle:  Bundle = new Bundle) {

val ft : app.FragmentTransaction = getSupportFragmentManager.beginTransaction()
    fragment.setArguments(bundle)
    ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    ft.addToBackStack(null);

    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right );

    ft.replace(R.id.flRoot,fragment, tag).commit()
    //if(getSupportFragmentManager.getFragments.get(0).isInstanceOf[SplashFragment]) {toast("Viola")} else {  toast(" No Viola")}
    /*if (savedInstanceState == null) {
      val details = new SplashFragment()
      details.setArguments(getIntent.getExtras)
      getFragmentManager.beginTransaction().add(android.R.id.content, details)
        .commit()
    }*/
  }


/*
  public static PlaceholderFragment newInstance(int id) {
    PlaceholderFragment myFragment = new PlaceholderFragment ();

    Bundle args = new Bundle();
    args.putInt("id", id);
    myFragment.setArguments(args);

    return myFragment;
  }
*/

  override def onBackPressed(): Unit ={

   val splashFragment: SplashFragment  = getSupportFragmentManager().findFragmentByTag("SplashFragment").asInstanceOf[SplashFragment]
    if (splashFragment != null && splashFragment.isVisible) {
      finish
    }
    else{


      super.onBackPressed()}



  }

def openLoginFragment: Unit ={
  //Check find fragment in backstack first
  startFragment(new SplashFragment(), "SigninOrRegisterFragment")
}

}
package com.alexshcherbyna.communityxmasguide

import android.app.Activity
import android.content.Context
import android.content.res.{Configuration, TypedArray}
import android.graphics.{Color, Point}
import android.os.Bundle
import android.support.v4.app.FragmentManager.OnBackStackChangedListener
import android.support.v4.app.{Fragment, FragmentManager, FragmentTransaction}
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view._
import android.widget.AdapterView.OnItemClickListener
import android.widget._
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.nostra13.universalimageloader.core.download.BaseImageDownloader
import com.nostra13.universalimageloader.core.{DisplayImageOptions, ImageLoader, ImageLoaderConfiguration}
import com.alexshcherbyna.communityxmasguide.adapters.DrawerAdapter
import com.alexshcherbyna.communityxmasguide.constants.Constants
import com.alexshcherbyna.communityxmasguide.fragments._
import com.alexshcherbyna.communityxmasguide.xmasstuff.SnowFallView
import org.scaloid.common._
import org.scaloid.support.v4.SFragmentActivity

import scala.collection.mutable

class MainActivity extends SFragmentActivity {
  var drawer: DrawerLayout = _
  var mDrawerToggle: ActionBarDrawerToggle = _
  var navList: ListView = _
  var v: SRelativeLayout = _
  //lazy implicit  val context : Context = this

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    implicit def ddd = scala.language.postfixOps
    implicit val context: Context = this
    lazy val xmassapp = getApplication.asInstanceOf[XMasApplication]
    // setContentView(R.layout.activity_empty);

    val size: Point = new Point
    (context.asInstanceOf[Activity]).getWindowManager.getDefaultDisplay.getRealSize(size)
    //int height = metrics.heightPixels;
    //int width = metrics.widthPixels;
    val height: Int = size.y
    val width: Int = size.x

    val config : ImageLoaderConfiguration  = new ImageLoaderConfiguration.Builder(this)
     .diskCacheExtraOptions(width, height, null)
	            .threadPoolSize(3) // default
	           // .threadPriority(Thread.NORM_PRIORITY - 2) // default
	            .tasksProcessingOrder(QueueProcessingType.FIFO) // default
	            .denyCacheImageMultipleSizesInMemory()
	            .memoryCache(new LruMemoryCache(2 * width* height))
	            .memoryCacheSize(2 * width* height)
	            .memoryCacheSizePercentage(8) // default
	            .diskCacheSize(50 * width * height)
	            .diskCacheFileCount(100)
	            .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
	            .imageDownloader(new BaseImageDownloader(context)) // default
	            .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
	            .writeDebugLogs()
	            // .memoryCache(new WeakMemoryCache())
	            .build()

    ImageLoader.getInstance().init(config);

    drawer = new android.support.v4.widget.DrawerLayout(this)
setTitle(R.string.open_guide_menu)
    // navList= new ListView (this)


    // navList.setBackground(R.color.red_col)
    drawer.addView(getLayoutInflater.inflate(R.layout.activity_empty, null), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    implicit val viewGroup: ViewGroup = drawer.asInstanceOf[ViewGroup]
    v = new SRelativeLayout {

      implicit val context2: Context = context

      this += new SVerticalLayout {



        this += new SRelativeLayout {
          this += new SImageView(R.drawable.b5e85099ce_o).<<.fill.>>.scaleType(ImageView.ScaleType.CENTER_CROP)
          this += new STextView(R.string.greetings).<<.alignParentBottom.alignParentLeft.wrap.margin(0, 10 dip, 0, 0).>>.textSize(38 sp).textColor(Color.WHITE).typeface(xmassapp.getChopInScript)

        }.Weight(0.35f).>>.<<(MATCH_PARENT, 0).>>
        this += new SVerticalLayout {
          navList = new SListView()
          this += navList
        }.Weight(0.4f).Gravity(Gravity.CENTER).>>.<<(MATCH_PARENT, 0).>>

        this += new SVerticalLayout {
          this += new SImageView(R.drawable.wreath1).<<.wrap.Gravity(Gravity.CENTER).>>
        }.Weight(0.25f).Gravity(Gravity.CENTER).>>.<<(MATCH_PARENT, 0).>>

      }.<<.fill.>>

      this += new SnowFallView(getContext)

    }.background(R.color.red_col)
    val lp: DrawerLayout.LayoutParams = new DrawerLayout.LayoutParams(300 dip, ViewGroup.LayoutParams.MATCH_PARENT)

    lp.gravity = Gravity.START;


    v.setLayoutParams(lp);

    drawer.addView(v);



    drawer.setBackground(R.color.white)
    setContentView(drawer);




    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
    getActionBar().setBackgroundDrawable(R.drawable.action_bar_bg) //new ColorDrawable(getResources.getColor(R.color.red_col)))



    var  titleId = getResources().getIdentifier("action_bar_title", "id", "android")
    val yourTextView : TextView  = findViewById(titleId).asInstanceOf[TextView]
    if(yourTextView!=null) {
      yourTextView.setTextColor(getResources().getColor(R.color.white));
      yourTextView.setTypeface(xmassapp.getChristmasSnow);
      yourTextView.setTextSize(12 sp)
    }
    else{

      //http://stackoverflow.com/questions/26991127/findviewbyid-returns-null-for-action-bar-title
    }


    val menuList = getResources().getStringArray(R.array.menuArray).toList



    mDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open_menu, R.string.close_menu) {
      override def onDrawerClosed(view: View) {
        super.onDrawerClosed(view);
        //getActionBar().setTitle(getResources.getString(R.string.open_menu));
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }

      /** Called when a drawer has settled in a completely open state. */
      override def onDrawerOpened(drawerView: View) {
        super.onDrawerOpened(drawerView);
       // getActionBar().setTitle(R.string.close_menu);
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }

    }


    drawer.setDrawerListener(mDrawerToggle);

    //Set the adapter for the list view
    val adapter = new DrawerAdapter(this)
    navList.setAdapter(adapter);

    val allImages: TypedArray = getResources().obtainTypedArray(R.array.imagesmenu)
    var imagesList: mutable.MutableList[Int] = new mutable.MutableList[Int]()
    for (i: Int <- 0 to allImages.length()) {
      imagesList += allImages.getResourceId(i, 0)
    }
    adapter.setMenuItems(menuList.zip(imagesList.toList))
    navList.setOnItemClickListener(new OnItemClickListener() {


      override def onItemClick(arg0: AdapterView[_], view: View, position: Int,
                               id: Long) {
        position match {
          case 0 =>


            // Api.registerUserRequest(JSONFactory.toUserRegisterJson(ur),new RegisterPersonSuccessListener(getActivity.asInstanceOf[SplashScreenActivity]),new RegisterPersonErrorListener(getActivity),  getActivity)

            val b = new Bundle()
            b.putInt(Constants.FRAGMENT_TYPE, Constants.TYPE_ACTIVITY)
            startFragment(new ButtonsFragment(), b)
            drawer.closeDrawer(v)
          case 1 =>
            val b = new Bundle()
            b.putInt(Constants.FRAGMENT_TYPE, Constants.TYPE_PLACES)
            startFragment(new ButtonsFragment(), b)
            drawer.closeDrawer(v)
          case 2 =>
            // val b = new Bundle()
            //b.putInt(Constants.FRAGMENT_TYPE, Constants.TYPE_PLACES)
            startFragment(new AddPlaceFragment())
            drawer.closeDrawer(v)
        }
      }
    });




/*

     val info :  PackageInfo = getPackageManager().getPackageInfo(
      "com.xmas.xmas", PackageManager.GET_SIGNATURES); for
    (signature <- info.signatures) {
      val md :MessageDigest  = MessageDigest.getInstance("SHA"); md.update(signature.toByteArray());
      Log.e("KeyHash:", Base64.encodeToString(md.digest(),
        Base64.DEFAULT)); }
*/





    if (savedInstanceState == null) {


      startFragment(new MainFragment()) //New Bundle
    }
    else {
      //Here when rotate screen
      /*if(getIntent!=null & !getIntent.getStringExtra(Constants.FRAGMENT_TO_START).equals("")){
         val fragClassName: String = getIntent.getStringExtra(Constants.FRAGMENT_TO_START)
         startFragment( Class.forName("com.example"+fragClassName).newInstance().asInstanceOf[Fragment], Constants.FRAGMENT_TO_START)
       }*/
    }


    //UPDATE DRAWER NAME WHEN FRAGMENT CHANGED
    getSupportFragmentManager().addOnBackStackChangedListener(new OnBackStackChangedListener() {

      override def onBackStackChanged() {
        val f: Fragment = getSupportFragmentManager().findFragmentById(R.id.flRoot);
        if (f != null) {
          updateTitleAndDrawer(f);
        }

      }
    });


  }


  override def onPrepareOptionsMenu(menu: Menu): Boolean = {
    // If the nav drawer is open, hide action items related to the content view
    val drawerOpen: Boolean = drawer.isDrawerOpen(v);
    // menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }

  override def onPostCreate(savedInstanceState: Bundle) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    mDrawerToggle.syncState();
  }

  override def onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    // Pass the event to ActionBarDrawerToggle, if it returns
    // true, then it has handled the app icon touch event
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    // Handle your other action bar items...

    return super.onOptionsItemSelected(item);
  }


  /*def startFragment(fragment: Fragment, tag: String, bundle: Bundle = new Bundle) {

    val ft: app.FragmentTransaction = getSupportFragmentManager.beginTransaction()
    fragment.setArguments(bundle)
    ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    ft.addToBackStack(null);

    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

    ft.replace(R.id.flRoot, fragment, tag).commit()

  }*/




  def startFragment (fragment : Fragment, bundle: Bundle = new Bundle ) = {
  val backStateName: String  =  fragment.getClass().getName();
  val fragmentTag : String = backStateName;

  val manager : FragmentManager  = getSupportFragmentManager();
   val fragmentPopped : Boolean  = manager.popBackStackImmediate (backStateName, 0);

  if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
   val ft : FragmentTransaction  = manager.beginTransaction();
    fragment.setArguments(bundle)
    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
   // ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    ft.replace(R.id.flRoot, fragment, fragmentTag);

    ft.addToBackStack(backStateName);
    ft.commit();
  }
    else{
    val backStateName: String  =  fragment.getClass().getName();
    val fragmentTag : String = backStateName;


    getSupportFragmentManager().beginTransaction().remove(fragment).commit();

    val fr = Class.forName(backStateName).newInstance().asInstanceOf[Fragment]
    val ft : FragmentTransaction  = manager.beginTransaction();
    fr.setArguments(bundle)
    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
    // ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    ft.replace(R.id.flRoot, fr, fragmentTag);

    ft.addToBackStack(backStateName);
    ft.commit();
  }
}



  override def onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
      //if (splashFragment != null && splashFragment.isVisible) {
      finish();
    }
    else {
      super.onBackPressed();
    }
  }


  def updateTitleAndDrawer(fragment: Fragment) = {
    val fragClassName: String = fragment.getClass().getName();

    if (fragment.isInstanceOf[AddPlaceFragment])
    {
      setTitle(R.string.addplace);
      //set selected item position, etc
    }
    else if (fragment.isInstanceOf[ButtonsFragment] )
    {
      val sel : Int = fragment.asInstanceOf[ButtonsFragment].getCurrentType
      sel match {
        case Constants.TYPE_ACTIVITY => setTitle(R.string.be_active)
        case Constants.TYPE_PLACES => setTitle(R.string.find_your_place_short)
        case _ =>
      }
    }
    else if (fragment.isInstanceOf[CountryInfoFragment])
    {
      setTitle(R.string.country_trad);
      //set selected item position, etc
    }
    else if (fragment.isInstanceOf[CityInfoFragment])
    {
      setTitle(R.string.city_celebration);
      //set selected item position, etc
    }
    else if (fragment.isInstanceOf[CountriesFragment])
    {
      setTitle(R.string.check_countries);
      //set selected item position, etc
    }
    else if (fragment.isInstanceOf[CitiesFragment])
    {
      setTitle(R.string.check_cities);
      //set selected item position, etc
    }
    else if (fragment.isInstanceOf[MainFragment])
    {
      setTitle(R.string.xmas_guide);
      //set selected item position, etc
    }

  }

  override def dispatchTouchEvent(ev : MotionEvent ) :  Boolean = {
    super.dispatchTouchEvent(ev);
    return onTouchEvent(ev);
  }

}
import java.io.File

import android.Keys._
 import android.{PromptStorepassSigningConfig, PromptPasswordsSigningConfig}

 //import scalariform.formatter.preferences._


 javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

android.Plugin.androidBuild

name := "Xmas"
organization := "com.xmas"


//resolvers += bintray.Opts.resolver.jcenter



resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
/* resolvers ++= Seq(
   Resolver.sonatypeRepo("releases"),
   "jcenter" at "http://jcenter.bintray.com"
 )*/




scalaVersion := "2.11.7"

proguardCache in Android ++= Seq("org.scaloid")
//, "-dontoptimize"
proguardOptions in Android ++= Seq("-dontobfuscate", "-keepattributes Signature", "-printseeds target/seeds.txt", "-printusage target/usage.txt"
  , "-dontwarn scala.collection.**" // required from Scala 2.11.4
  , "-dontwarn org.scaloid.**" //, "-zipalignPath" // this can be omitted if current Android Build target is android-16
)


/*,"-optimizationpasses 8", //optimizations commands
"-dontpreverify",
"-allowaccessmodification",
"-optimizations !code/simplification/arithmetic"*/



 proguardConfig in Android <<= baseDirectory map (b => IO.readLines(b/"proguard-sbt.txt"))

 proguardOptions in Android += "-keep interface android.support.v4.app.** { *; }"
proguardOptions in Android += "-keep class android.support.v7.** { *; }"
proguardOptions in Android += "-keep interface android.support.v7.** { *; }"
proguardOptions in Android += "-keep class android.support.v4.app.** { *; }"
 proguardOptions in Android += "-ignorewarnings { *; }"

libraryDependencies ++= {
  val akkaStreamV = "1.0"
  val scalazV          = "7.2.0-M1"
  val scalaTestV       = "3.0.0-M7"
  val scalaMockV       = "3.2.2"
  val scalazScalaTestV = "0.2.3"

  Seq("org.scaloid" %% "scaloid" % "4.0-RC1",
    aar("com.android.support" % "recyclerview-v7" % "21.0.3"),
  "com.android.support" % "multidex" % "1.0.0" ,
      aar( "com.android.support" % "support-v4" % "21.0.0"),
    aar("com.android.support" % "appcompat-v7" % "21.0.0"),
       "com.github.carlonzo.stikkyheader" % "core" % "0.0.2-SNAPSHOT",
    "com.mcxiaoke.volley" % "library" % "1.0.17",
    "com.nostra13.universalimageloader" % "universal-image-loader" % "1.9.3",
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"               % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-testkit-experimental"       % akkaStreamV,
    "org.scalaz"        %% "scalaz-core"                          % scalazV,
    "org.scalatest"     % "scalatest_2.11"                       % scalaTestV,
    "org.scalamock"     % "scalamock-scalatest-support_2.11"          % scalaMockV,
    "org.scalaz"        % "scalaz-scalacheck-binding_2.11"            % scalazV,
    "org.typelevel"     % "scalaz-scalatest_2.11"                     % scalazScalaTestV,
    "junit" % "junit" % "4.4",
    "org.jmock" % "jmock-junit4" % "2.8.1",
    "org.scala-lang" % "scala-swing" % "2.11.0-M7"
  )}
 //"com.daimajia.slider" % "library" % "1.1.5"
 //"com.squareup.picasso" % "picasso" % "2.5.2",
 //"com.nineoldandroids" % "library" % "2.4.0"
//debugIncludesTests := true
//useProguard in Android := true

/* unmanagedJars in Compile ~= { _ filterNot (_.data.getName startsWith Seq("android-support-v4",
 "picasso" )) }*/




scalacOptions in Compile += "-feature"

run <<= run in Android


install <<= install in Android

retrolambdaEnable in Android := false

useProguard in Android := true

useProguardInDebug in Android := true

proguardScala in Android := true

dexMulti in Android := true



dexMinimizeMainFile in Android := true

 transitiveAndroidLibs in Android := false

// keyalias in Android := "alexshcherbyna"

 dexMaxHeap in Android := "2048m"

dexMainFileClasses in Android := Seq(
  //"com/example/app/MultidexApplication.class",
  "android/support/multidex/BuildConfig.class",
  "android/support/multidex/MultiDex$V14.class",
  "android/support/multidex/MultiDex$V19.class",
  "android/support/multidex/MultiDex$V4.class",
  "android/support/multidex/MultiDex.class",
  "android/support/multidex/MultiDexApplication.class",
  "android/support/multidex/MultiDexExtractor$1.class",
  "android/support/multidex/MultiDexExtractor.class",
  "android/support/multidex/ZipUtil$CentralDirectory.class",
  "android/support/multidex/ZipUtil.class"
)



apkbuildExcludes in Android ++= Seq(
  "MANIFEST.MF",
  "LICENSE.txt",
  "LICENSE",
  "NOTICE.txt",
  "NOTICE",
  "AndroidManifest.xml",
  "DEPENDENCIES",
  "notice.txt",
  "license.txt",
  "LICENSE.txt",
  "NOTICE.txt",
  "MANIFEST.MF",
  "!META-INF/MANIFEST.MF",
  "META-INF/MANIFEST.MF",
  "services/javax.annotation.processing.Processor",
  "android.support.v4.graphics.drawable.DrawableWrapperLollipop"
).map(file => s"META-INF/$file")

/*
 scalariformSettings
 Revolver.settings


 configs(IntegrationTest)
 Defaults.itSettings
 ScalariformKeys.preferences := ScalariformKeys.preferences.value
   .setPreference(AlignSingleLineCaseStatements, true)
   .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
   .setPreference(DoubleIndentClassDeclaration, true)
 enablePlugins(JavaAppPackaging)



 initialCommands := """|import scalaz._
                      |import Scalaz._
                      |import akka.actor._
                      |import akka.pattern._
                      |import akka.util._
                      |import scala.concurrent._
                      |import scala.concurrent.duration._""".stripMargin

 parallelExecution in Global := false*/


 /*packageRelease <<= (packageRelease in Android)

 apkSigningConfig in Android := Option(
   PromptPasswordsSigningConfig(
     keystore = new File(Path.userHome.absolutePath + "~/.keystore"),
     alias = "alexfinallygotanapp"))
*/






signRelease <<= signRelease in Android

apkbuild <<= apkbuild in Android





packageRelease <<= (packageRelease in Android)

 apkSigningConfig in Android := Option(
   PromptPasswordsSigningConfig(
     //Ask for keystore password only.
     //To ask for both keystore and key paswords,
     //use PromptPasswordsSigningConfig instead
     keystore = new File(
      // Path.userHome.absolutePath +
         "~/.keystore"
     ),
     alias = "alexfinallygotanapp")
 )

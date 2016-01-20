# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/maik_vlcek/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Project default
-dontskipnonpubliclibraryclassmembers
-allowaccessmodification
-keepattributes SourceFile,LineNumberTable
-dontwarn
-ignorewarnings

-keepclassmembers class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Google Play Services
-keep class * extends java.util.ListResourceBundle {
   protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
   public static final *** NULL;
}

# Fabric Twitter Kit
-dontwarn com.squareup.okhttp.**
-dontwarn com.google.appengine.api.urlfetch.**
-dontwarn rx.**
-dontwarn retrofit.**
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

# nice progressbar
-keep class me.zhanghai.android.materialprogressbar.** { *; }

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Eventbus
-keepclassmembers class ** {
    public void onEvent*(**);
}

# Parse
-keep class com.parse.** { *; }
-dontwarn com.parse.**

# Some google stuff
-keep public class com.google.common.**

# Annotations
-dontwarn javax.annotation.*

# OkHttp
-dontwarn okio.**

# Javascript interface
-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
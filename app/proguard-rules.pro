# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/cdv/Dev/Tools/android-sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Remove all logs
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    #public static int e(...);
}

#keep Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

 #keep fragments transition
-keep class android.support.v4.app.FragmentTransitionCompat21 { *; }

-keep class com.android.graphics.drawable.** { *; }
# keep setters in VectorDrawables so that animations can still work.
-keepclassmembers class android.support.graphics.drawable.VectorDrawableCompat$* {
   void set*(***);
   *** get*();
}
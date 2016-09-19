#Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn okio.**

#EventBus
-keepclassmembers class * {
    @de.greenrobot.event.Subscribe <methods>;
}

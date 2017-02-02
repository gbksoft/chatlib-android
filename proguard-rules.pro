# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/user/Android/Sdk/tools/proguard/proguard-android.txt
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
#Retrolambda
-dontwarn java.lang.invoke.*
-keepattributes Exceptions

-keep class net.gbksoft.chatlibrary.ChatWebSocket { *; }
-keep class net.gbksoft.chatlibrary.ChatWebSocketBuilder { *; }

-keep class net.gbksoft.chatlibrary.chatdata.ChatDataListeners$* { *; }
-keep class net.gbksoft.chatlibrary.connection.ChatConnectionListener$* { *; }

-keep class net.gbksoft.chatlibrary.log.ChatChatLogAdapter { *; }
-keep class net.gbksoft.chatlibrary.log.ChatLogListener { *; }

-keep class net.gbksoft.chatlibrary.model.** { *; }
-keep class net.gbksoft.chatlibrary.utils.SSLUtils { *; }

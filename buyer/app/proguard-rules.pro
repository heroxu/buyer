# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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
-ignorewarnings

-dontoptimize
-dontusemixedcaseclassnames
-verbose
-dontshrink
-dontoptimize
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

-dontnote **
-dontwarn **

-dontwarn dalvik.**
# greenDAO 3.2
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# 数据库加密
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-keep class data.db.dao.*$Properties {
    public static <fields>;
}
-keepclassmembers class data.db.dao.** {
    public static final <fields>;
 }
-keep public class net.sqlcipher.**{*;}
-keep public class net.sqlcipher.database.**{*;}
#RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
#okio
-dontwarn okio.**
-keep class okio.**{*;}

-dontwarn android.support.**
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

-keep class com.andview.refreshview.** {*;}
#EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#------------------腾讯云通信----------------------------------

-keepclass com.tencent.**{*;}

-dontwarncom.tencent.**

-keepclass tencent.**{*;}

-dontwarntencent.**

-keepclass qalsdk.**{*;}

-dontwarnqalsdk.**

#------------------小米推送----------------------------------

-keepclass com.sinosoft.nanniwan.im.imutil.MiPushMessageReceiver {*;}

-dontwarncom.xiaomi.push.**

#------------------华为推送----------------------------------

-keepclass com.huawei.android.**{*;}

-dontwarncom.huawei.android.**

-keepclass com.baidu.mapapi.**{*;}

-dontwarncom.baidu.mapapi.**

#------------------java.nio------------------------

-dontwarnorg.codehaus.**

-dontwarnjava.nio.**

-dontwarnjava.lang.invoke.**

#--------腾讯直播--------------
-keep class com.tencent.** { *; }


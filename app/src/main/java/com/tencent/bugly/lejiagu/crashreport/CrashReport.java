package com.tencent.bugly.lejiagu.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.lejiagu.Bugly;
import com.tencent.bugly.lejiagu.BuglyStrategy;
import com.tencent.bugly.lejiagu.CrashModule;
import com.tencent.bugly.lejiagu.b;
import com.tencent.bugly.lejiagu.crashreport.common.info.a;
import com.tencent.bugly.lejiagu.crashreport.crash.c;
import com.tencent.bugly.lejiagu.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.lejiagu.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.lejiagu.proguard.l;
import com.tencent.bugly.lejiagu.proguard.m;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class CrashReport {
    private static Context a;

    /* JADX INFO: compiled from: BUGLY */
    public static class CrashHandleCallback extends BuglyStrategy.a {
    }

    public static void enableBugly(boolean z) {
        Bugly.enable = z;
    }

    public static synchronized void initCrashReport(Context context) {
        a = context;
        b.a(context);
    }

    public static synchronized void initCrashReport(Context context, UserStrategy userStrategy) {
        a = context;
        b.a(context, userStrategy);
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z) {
        initCrashReport(context, str, z, null);
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context != null) {
            a = context;
            b.a(context, str, z, userStrategy);
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            u.d("Please call with context.", new Object[0]);
            return "unknown";
        }
        a.a(context);
        return a.b();
    }

    public static synchronized void testJavaCrash() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not test Java crash because bugly is disable.");
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static synchronized void testNativeCrash() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not test native crash because bugly is disable.");
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            u.a("start to create a native crash for test!", new Object[0]);
            c.a().j();
        }
    }

    public static synchronized void testANRCrash() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not test ANR crash because bugly is disable.");
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            u.a("start to create a anr crash for test!", new Object[0]);
            c.a().k();
        }
    }

    public static synchronized void postCatchedException(Throwable th) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not post crash caught because bugly is disable.");
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            if (th == null) {
                u.d("throwable is null, just return", new Object[0]);
            } else {
                c.a().a(Thread.currentThread(), th, false, null, null);
            }
        }
    }

    public static synchronized void closeNativeReport() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not close native report because bugly is disable.");
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            c.a().f();
        }
    }

    public static synchronized void startCrashReport() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(u.b, "Report has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().c();
        }
    }

    public static synchronized void closeCrashReport() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(u.b, "Report has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().d();
        }
    }

    public static void closeBugly() {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not close bugly because bugly is disable.");
            return;
        }
        if (!CrashModule.hasInitialized()) {
            Log.w(u.b, "Report has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (a != null) {
            closeCrashReport();
            com.tencent.bugly.lejiagu.crashreport.biz.b.a(a);
            t tVarA = t.a();
            if (tVarA != null) {
                tVarA.b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set tag caught because bugly is disable.");
        } else {
            if (context == null) {
                throw new BuglyHintException("setTag args context should not be null");
            }
            if (i <= 0) {
                u.d("setTag args tagId should > 0", new Object[0]);
            }
            a.a(context).a(i);
            u.b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get user scene tag because bugly is disable.");
            return -1;
        }
        if (context == null) {
            throw new BuglyHintException("getUserSceneTagId args context should not be null");
        }
        return a.a(context).A();
    }

    public static String getUserData(Context context, String str) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            throw new BuglyHintException("getUserDataValue args context should not be null");
        }
        if (str == null || str.trim().length() <= 0) {
            return null;
        }
        return a.a(context).g(str);
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not put user data because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(u.b, "putUserData args context should not be null");
            return;
        }
        if (str == null) {
            String str3 = str;
            u.d("putUserData args key should not be null or empty", new Object[0]);
            return;
        }
        if (str2 == null) {
            String str4 = str2;
            u.d("putUserData args value should not be null", new Object[0]);
            return;
        }
        if (!str.matches("[a-zA-Z[0-9]]+")) {
            u.d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
            return;
        }
        if (str2.length() > 200) {
            u.d("user data value length over limit %d, it will be cutted!", 200);
            str2 = str2.substring(0, 200);
        }
        a aVarA = a.a(context);
        if (aVarA.y().contains(str)) {
            NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
            if (nativeCrashHandler != null) {
                nativeCrashHandler.putKeyValueToNative(str, str2);
            }
            a.a(context).a(str, str2);
            u.c("replace KV %s %s", str, str2);
            return;
        }
        if (aVarA.x() >= 10) {
            u.d("user data size is over limit %d, it will be cutted!", 10);
            return;
        }
        if (str.length() > 50) {
            u.d("user data key length over limit %d , will drop this new key %s", 50, str);
            str = str.substring(0, 50);
        }
        NativeCrashHandler nativeCrashHandler2 = NativeCrashHandler.getInstance();
        if (nativeCrashHandler2 != null) {
            nativeCrashHandler2.putKeyValueToNative(str, str2);
        }
        a.a(context).a(str, str2);
        u.b("[param] set user data: %s - %s", str, str2);
    }

    public static String removeUserData(Context context, String str) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not remove user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            throw new BuglyHintException("removeUserData args context should not be null");
        }
        if (str == null || str.trim().length() <= 0) {
            return null;
        }
        u.b("[param] remove user data: %s", str);
        return a.a(context).f(str);
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        }
        if (context == null) {
            throw new BuglyHintException("getAllUserDataKeys args context should not be null");
        }
        return a.a(context).y();
    }

    public static int getUserDatasSize(Context context) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get size of user data because bugly is disable.");
            return -1;
        }
        if (context == null) {
            throw new BuglyHintException("getUserDatasSize args context should not be null");
        }
        return a.a(context).x();
    }

    public static synchronized String getAppID() {
        String strE;
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get App ID because bugly is disable.");
            strE = "unknown";
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            strE = a.a(a).e();
        }
        return strE;
    }

    public static void setUserId(String str) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set user ID because bugly is disable.");
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            setUserId(a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set user ID because bugly is disable.");
            return;
        }
        if (context == null) {
            throw new BuglyHintException("Context should not be null when bugly has not been initialed!");
        }
        if (str == null) {
            u.d("userId should not be null", new Object[0]);
            return;
        }
        if (str.length() > 100) {
            String strSubstring = str.substring(0, 100);
            u.d("userId %s length is over limit %d substring to %s", str, 100, strSubstring);
            str = strSubstring;
        }
        if (!str.equals(a.a(context).f())) {
            a.a(context).b(str);
            u.b("[user] set userId : %s", str);
            if (CrashModule.hasInitialized()) {
                com.tencent.bugly.lejiagu.crashreport.biz.b.a();
            }
        }
    }

    public static synchronized String getUserId() {
        String strF;
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get user ID because bugly is disable.");
            strF = "unknown";
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            strF = a.a(a).f();
        }
        return strF;
    }

    public static synchronized String getAppVer() {
        String str;
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get app version because bugly is disable.");
            str = "unknown";
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            str = a.a(a).i;
        }
        return str;
    }

    public static synchronized String getAppChannel() {
        String str;
        if (!Bugly.enable) {
            Log.w(u.b, "Can not get App channel because bugly is disable.");
            str = "unknown";
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            str = a.a(a).j;
        }
        return str;
    }

    public static synchronized void setContext(Context context) {
        a = context;
    }

    public static synchronized boolean isLastSessionCrash() {
        boolean zB;
        if (!Bugly.enable) {
            Log.w(u.b, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            zB = false;
        } else {
            if (!CrashModule.hasInitialized()) {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            zB = c.a().b();
        }
        return zB;
    }

    public static synchronized void setSdkExtraData(Context context, String str, String str2) {
        synchronized (CrashReport.class) {
            if (!Bugly.enable) {
                Log.w(u.b, "Can not put SDK extra data because bugly is disable.");
            } else if (context != null) {
                if (!(str == null || str.trim().length() <= 0)) {
                    if (!(str2 == null || str2.trim().length() <= 0)) {
                        Map<String, String> map = b.b;
                        Map<String, String> map2 = map == null ? new HashMap() : map;
                        map2.put(str, str2);
                        if (map2.size() > 0) {
                            String str3 = "";
                            for (Map.Entry<String, String> entry : map2.entrySet()) {
                                str3 = str3 + "[" + entry.getKey() + "," + entry.getValue() + "] ";
                            }
                            putSdkData(context, "SDK_INFO", str3);
                        }
                        b.b = map2;
                    }
                }
            }
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (Bugly.enable) {
            return b.b;
        }
        Log.w(u.b, "Can not get SDK extra data because bugly is disable.");
        return new HashMap();
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null) {
            if (str == null || str.trim().length() <= 0) {
                return;
            }
            if (!(str2 == null || str2.trim().length() <= 0)) {
                String strReplace = str.replace("[a-zA-Z[0-9]]+", "");
                if (strReplace.length() > 100) {
                    Log.w(u.b, String.format("putSdkData key length over limit %d, will be cutted.", 50));
                    strReplace = strReplace.substring(0, 50);
                }
                if (str2.length() > 500) {
                    Log.w(u.b, String.format("putSdkData value length over limit %d, will be cutted!", 200));
                    str2 = str2.substring(0, 200);
                }
                a.a(context).b(strReplace, str2);
                Log.w(u.a, String.format("[param] putSdkData data: %s - %s", strReplace, str2));
            }
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set 'isAppForeground' because bugly is disable.");
            return;
        }
        if (context == null) {
            u.d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            u.c("App is in foreground.", new Object[0]);
        } else {
            u.c("App is in background.", new Object[0]);
        }
        a.a(context).n = z;
    }

    public static void setSessionIntervalMills(long j) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            com.tencent.bugly.lejiagu.crashreport.biz.b.a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set APP version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(u.b, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(u.b, "Version is null, will not set");
        } else {
            a.a(context).i = str;
        }
    }

    public static void setAppChannel(String str, boolean z) {
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set APP version because bugly is disable.");
            return;
        }
        if (!CrashModule.hasInitialized()) {
            throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
        }
        if (str == null) {
            Log.w(u.b, "channel is null, will not set");
            return;
        }
        m mVarA = m.a();
        if (mVarA == null) {
            Log.w(u.b, "DB Manager has not been initialed, can not replace App channel");
            return;
        }
        if (!z) {
            try {
                Map<String, byte[]> mapA = mVarA.a(556, (l) null, true);
                if (mapA != null && mapA.containsKey("app_channel")) {
                    str = new String(mapA.get("app_channel"));
                }
            } catch (Throwable th) {
                if (b.a) {
                    th.printStackTrace();
                    return;
                }
                return;
            }
        }
        a.a(a).j = str;
        mVarA.a(556, "app_channel", str.toString().getBytes(), (l) null, true);
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(u.b, "Webview is null.");
            return false;
        }
        if (!CrashModule.hasInitialized()) {
            u.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
        u.a("Set Javascript exception monitor of webview.", new Object[0]);
        if (!Bugly.enable) {
            Log.w(u.b, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        }
        u.c("URL of webview is %s", webView.getUrl());
        if (webView.getUrl() == null) {
            return false;
        }
        if (!z2 && Build.VERSION.SDK_INT < 19) {
            u.e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
        WebSettings settings = webView.getSettings();
        if (!settings.getJavaScriptEnabled()) {
            u.a("Enable the javascript needed by webview monitor.", new Object[0]);
            settings.setJavaScriptEnabled(true);
        }
        H5JavaScriptInterface h5JavaScriptInterface = H5JavaScriptInterface.getInstance(webView);
        if (h5JavaScriptInterface != null) {
            u.a("Add a secure javascript interface to the webview.", new Object[0]);
            webView.addJavascriptInterface(h5JavaScriptInterface, "exceptionUploader");
        }
        if (z) {
            u.a("Inject bugly.js(v%s) to the webview.", com.tencent.bugly.lejiagu.crashreport.crash.h5.c.b());
            String strA = com.tencent.bugly.lejiagu.crashreport.crash.h5.c.a();
            if (strA == null) {
                u.e("Failed to inject Bugly.js.", com.tencent.bugly.lejiagu.crashreport.crash.h5.c.b());
                return false;
            }
            webView.loadUrl("javascript:" + strA);
        }
        return true;
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {
        private CrashHandleCallback a;

        public UserStrategy(Context context) {
        }

        @Override // com.tencent.bugly.lejiagu.BuglyStrategy
        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.a = crashHandleCallback;
        }
    }
}

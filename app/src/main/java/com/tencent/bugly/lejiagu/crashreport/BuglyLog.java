package com.tencent.bugly.lejiagu.crashreport;

import android.util.Log;
import com.tencent.bugly.lejiagu.b;
import com.tencent.bugly.lejiagu.proguard.v;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class BuglyLog {
    public static void v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.a) {
            Log.v(str, str2);
        }
        v.a("V", str, str2);
    }

    public static void d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.a) {
            Log.d(str, str2);
        }
        v.a("D", str, str2);
    }

    public static void i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.a) {
            Log.i(str, str2);
        }
        v.a("I", str, str2);
    }

    public static void w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.a) {
            Log.w(str, str2);
        }
        v.a("W", str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.a) {
            Log.e(str, str2);
        }
        v.a("E", str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.a) {
            Log.e(str, str2, th);
        }
        v.a("E", str, th);
    }

    public static void setCache(int i) {
        v.a(i);
    }
}

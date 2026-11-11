package com.tencent.bugly.lejiagu.proguard;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class u {
    public static String a = "CrashReportInfo";
    public static String b = "CrashReport";
    public static boolean c = false;

    private static boolean a(int i, String str, Object... objArr) {
        if (!c) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        switch (i) {
            case 0:
                Log.i(b, str);
                return true;
            case 1:
                Log.d(b, str);
                return true;
            case 2:
                Log.w(b, str);
                return true;
            case 3:
                Log.e(b, str);
                return true;
            case 4:
            default:
                return false;
            case 5:
                Log.i(a, str);
                return true;
        }
    }

    public static boolean a(String str, Object... objArr) {
        return a(0, str, objArr);
    }

    public static boolean b(String str, Object... objArr) {
        return a(5, str, objArr);
    }

    public static boolean c(String str, Object... objArr) {
        return a(1, str, objArr);
    }

    public static boolean d(String str, Object... objArr) {
        return a(2, str, objArr);
    }

    public static boolean a(Throwable th) {
        if (c) {
            return a(2, a.a(th), new Object[0]);
        }
        return false;
    }

    public static boolean e(String str, Object... objArr) {
        return a(3, str, objArr);
    }

    public static boolean b(Throwable th) {
        if (c) {
            return a(3, a.a(th), new Object[0]);
        }
        return false;
    }
}

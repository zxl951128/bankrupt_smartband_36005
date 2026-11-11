package com.tencent.bugly.lejiagu.crashreport.common.info;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.tencent.bugly.lejiagu.proguard.u;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class AppInfo {
    private static ActivityManager a;

    static {
        "@buglyAllChannel@".split(",");
        "@buglyAllChannelPriority@".split(",");
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0);
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        try {
            return packageInfo.versionName;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        try {
            return Integer.toString(packageInfo.versionCode);
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (String str2 : strArr) {
                if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (u.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static String a(int i) throws Throwable {
        FileReader fileReader;
        String strValueOf;
        int i2 = 0;
        try {
            fileReader = new FileReader("/proc/" + i + "/cmdline");
            try {
                try {
                    char[] cArr = new char[512];
                    fileReader.read(cArr);
                    while (i2 < cArr.length && cArr[i2] != 0) {
                        i2++;
                    }
                    strValueOf = new String(cArr).substring(0, i2);
                    try {
                        fileReader.close();
                    } catch (Throwable th) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable th3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                strValueOf = String.valueOf(i);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable th5) {
                    }
                }
            }
        } catch (Throwable th6) {
            th = th6;
            fileReader = null;
        }
        return strValueOf;
    }

    public static String c(Context context) {
        try {
            return a(Process.myPid());
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> d(Context context) {
        HashMap map;
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                map = new HashMap();
                Object obj = applicationInfo.metaData.get("BUGLY_DISABLE");
                if (obj != null) {
                    map.put("BUGLY_DISABLE", obj.toString());
                }
                Object obj2 = applicationInfo.metaData.get("BUGLY_APPID");
                if (obj2 != null) {
                    map.put("BUGLY_APPID", obj2.toString());
                }
                Object obj3 = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
                if (obj3 != null) {
                    map.put("BUGLY_APP_CHANNEL", obj3.toString());
                }
                Object obj4 = applicationInfo.metaData.get("BUGLY_APP_VERSION");
                if (obj4 != null) {
                    map.put("BUGLY_APP_VERSION", obj4.toString());
                }
                Object obj5 = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
                if (obj5 != null) {
                    map.put("BUGLY_ENABLE_DEBUG", obj5.toString());
                }
                Object obj6 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
                if (obj6 != null) {
                    map.put("com.tencent.rdm.uuid", obj6.toString());
                }
            } else {
                map = null;
            }
            return map;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static List<String> a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = map.get("BUGLY_DISABLE");
            if (str == null || str.length() == 0) {
                return null;
            }
            String[] strArrSplit = str.split(",");
            for (int i = 0; i < strArrSplit.length; i++) {
                strArrSplit[i] = strArrSplit[i].trim();
            }
            return Arrays.asList(strArrSplit);
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        if (a == null) {
            a = (ActivityManager) context.getSystemService("activity");
        }
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            a.getMemoryInfo(memoryInfo);
            if (!memoryInfo.lowMemory) {
                return false;
            }
            u.c("Memory is low.", new Object[0]);
            return true;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }
}

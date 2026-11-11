package com.tencent.StubShell;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import com.tencent.bugly.legu.crashreport.CrashReport;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TxAppEntry extends Application {
    private static Context d = null;
    private static String e = null;
    private static String f = null;
    private static String mOldAPPName = null;
    private static String mPKName = null;
    private static String mSocPath = null;
    private static String mSrcPath = null;
    private static String mVerFilePath = null;
    private static boolean mbVerCheck = false;
    private static final String version = "a1394b34de5ec3c9b81e352c7d32319aa3ac50bcff77dead63f46e97bb7791d0";
    private Handler h = null;
    private static String g = "";
    public static String a = "";
    public static String b = "";
    static int c = 0;

    public static void a(Intent intent) {
        reciver(intent);
    }

    /* JADX WARN: Code duplicated, block: B:126:0x0598  */
    private void a(String str) {
        String[] strArr;
        String str2;
        String str3;
        boolean z;
        ApplicationInfo applicationInfo = getApplicationInfo();
        String str4 = applicationInfo.dataDir + "/tx_shell";
        b = applicationInfo.sourceDir;
        int i = Build.VERSION.SDK_INT;
        boolean z2 = false;
        if (i >= 19) {
            if (i > 19) {
                String[] strArr2 = Build.SUPPORTED_64_BIT_ABIS;
                if (strArr2 != null && strArr2.length > 1) {
                    z2 = true;
                }
            } else if (new File("/system/lib64").exists()) {
                z2 = true;
            }
        }
        String str5 = Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : null;
        if ((str5 == null || str5.length() < 2) && Build.VERSION.SDK_INT >= 21) {
            str5 = Build.SUPPORTED_ABIS[0];
        }
        boolean z3 = true;
        if (str5.toLowerCase(Locale.US).contains("x86")) {
            z3 = false;
        } else if (Build.VERSION.SDK_INT >= 21 && (strArr = Build.SUPPORTED_ABIS) != null) {
            for (String str6 : strArr) {
                if (str6.toLowerCase(Locale.US).contains("x86")) {
                    z3 = false;
                }
            }
        }
        boolean z4 = str5.toLowerCase(Locale.US).contains("mips");
        String str7 = Build.VERSION.SDK_INT > 8 ? applicationInfo.nativeLibraryDir : "/data/data/" + mPKName + "/lib";
        String str8 = "";
        if (z3) {
            str2 = str + d();
        } else {
            str2 = str + d();
            str8 = str + d();
        }
        String str9 = "lib" + str2 + ".so";
        String str10 = "lib" + str8 + ".so";
        File file = new File(str7 + "/" + str9);
        File file2 = new File(str4 + "/" + str10);
        if (!z3 && Build.VERSION.SDK_INT < 19) {
            if (!file2.exists()) {
                if (ZipUtil.exist(b, "lib/armeabi/" + str10) == 0) {
                }
            }
            try {
                Runtime.getRuntime().exec("chmod 700 " + str4 + "/" + str10);
                System.load(str4 + "/" + str10);
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (file.exists()) {
            System.loadLibrary(str2);
            return;
        }
        String str11 = str4 + "/" + str9;
        if (ZipUtil.exist(b, "lib/" + str5 + "/" + str9) == 0) {
            str3 = "lib/" + str5 + "/" + str9;
        } else if (z2) {
            if (z3) {
                if (ZipUtil.exist(b, "lib/arm64-v8a/" + str9) == 0) {
                    str3 = "lib/arm64-v8a/" + str9;
                } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                    str3 = "lib/armeabi/" + str9;
                } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                    str3 = "lib/armeabi-v7a/" + str9;
                } else {
                    str3 = "";
                }
            } else if (ZipUtil.exist(b, "lib/x86_64/" + str9) == 0) {
                str3 = "lib/x86_64/" + str9;
            } else if (ZipUtil.exist(b, "lib/arm64-v8a/" + str9) == 0) {
                str3 = "lib/arm64-v8a/" + str9;
            } else if (ZipUtil.exist(b, "lib/x86/" + str9) == 0) {
                str3 = "lib/x86/" + str9;
            } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                str3 = "lib/armeabi/" + str9;
            } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                str3 = "lib/armeabi-v7a/" + str9;
            } else {
                str3 = "";
            }
        } else if (z3) {
            if (z4 && ZipUtil.exist(b, "lib/mips/" + str9) == 0) {
                z = true;
                str3 = "lib/mips/" + str9;
            } else {
                z = false;
                str3 = "";
            }
            if (!z) {
                if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                    str3 = "lib/armeabi/" + str9;
                } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                    str3 = "lib/armeabi-v7a/" + str9;
                }
            }
        } else if (ZipUtil.exist(b, "lib/x86/" + str9) == 0) {
            str3 = "lib/x86/" + str9;
        } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
            str3 = "lib/armeabi/" + str9;
        } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
            str3 = "lib/armeabi-v7a/" + str9;
        } else {
            str3 = "";
        }
        if (ZipUtil.extract(b, str3, str11) == 0) {
            try {
                Runtime.getRuntime().exec("chmod 700 " + str11);
                System.load(str11);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    private boolean b(Context context) {
        if (context == null) {
            return false;
        }
        d = context;
        mPKName = getBaseContext().getPackageName();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(mPKName, 128);
        } catch (PackageManager.NameNotFoundException e2) {
        }
        if (applicationInfo == null) {
            return false;
        }
        mOldAPPName = applicationInfo.metaData.getString("TxAppEntry");
        mSrcPath = d.getApplicationInfo().sourceDir;
        try {
            g = d.getPackageManager().getPackageInfo(mPKName, 0).versionName;
        } catch (Exception e3) {
        }
        mVerFilePath = "/data/data/";
        mVerFilePath += mPKName;
        mVerFilePath += "/tx_shell/";
        a = mVerFilePath;
        File file = new File(mVerFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        e = mVerFilePath + "libshella.so";
        f = mVerFilePath + "libshellb.so";
        mSocPath = mVerFilePath + "libshellc.so";
        mVerFilePath += g();
        return true;
    }

    private String c() {
        return "2.9.0.2";
    }

    private void c(Context context) {
        String str = g;
        if ("10000".equals("user_bugly_appid")) {
            return;
        }
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(context);
        userStrategy.setAppVersion(str);
        userStrategy.setEnableUserInfo(false);
        userStrategy.setRecordUserInfoOnceADay(true);
        CrashReport.setSdkExtraData(context, "10000", str);
        CrashReport.initCrashReport(context, "10000", false, userStrategy);
    }

    private static native void changeEnv(Context context);

    private String d() {
        return "";
    }

    private void d(Context context) {
        AssetManager assets = context.getAssets();
        String str = context.getApplicationInfo().sourceDir;
        try {
            System.loadLibrary("nfix");
            fixNativeResource(assets, str);
        } catch (Throwable th) {
            try {
                a("nfix");
                fixNativeResource(assets, str);
            } catch (Throwable th2) {
            }
        }
        try {
            System.loadLibrary("ufix");
            fixUnityResource(assets, str);
        } catch (Throwable th3) {
            try {
                a("ufix");
                fixUnityResource(assets, str);
            } catch (Throwable th4) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:170:0x0770  */
    private void e() {
        String[] strArr;
        String str;
        boolean z;
        String str2;
        boolean z2;
        String str3;
        String[] strArr2;
        ApplicationInfo applicationInfo = getApplicationInfo();
        String str4 = applicationInfo.dataDir + "/tx_shell";
        b = applicationInfo.sourceDir;
        int i = Build.VERSION.SDK_INT;
        boolean z3 = false;
        if (i >= 19) {
            if (i > 19) {
                String[] strArr3 = Build.SUPPORTED_64_BIT_ABIS;
                if (strArr3 != null && strArr3.length > 1) {
                    z3 = true;
                }
            } else if (new File("/system/lib64").exists()) {
                z3 = true;
            }
        }
        String str5 = Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : null;
        if ((str5 == null || str5.length() < 2) && Build.VERSION.SDK_INT >= 21) {
            str5 = Build.SUPPORTED_ABIS[0];
        }
        boolean z4 = true;
        if (str5.toLowerCase(Locale.US).contains("x86")) {
            z4 = false;
        } else if (Build.VERSION.SDK_INT >= 21 && (strArr = Build.SUPPORTED_ABIS) != null) {
            for (String str6 : strArr) {
                if (str6.toLowerCase(Locale.US).contains("x86")) {
                    z4 = false;
                }
            }
        }
        boolean z5 = str5.toLowerCase(Locale.US).contains("mips");
        String str7 = Build.VERSION.SDK_INT > 8 ? applicationInfo.nativeLibraryDir : "/data/data/" + mPKName + "/lib";
        String str8 = "";
        if (z4) {
            str = f() + "-" + c();
        } else {
            str = "shellx-" + c();
            str8 = f() + "-" + c();
        }
        String str9 = "lib" + str + ".so";
        String str10 = "lib" + str8 + ".so";
        File file = new File(str7 + "/" + str9);
        File file2 = new File(str4 + "/" + str10);
        if (!z4 && Build.VERSION.SDK_INT < 19) {
            if (!file2.exists()) {
                if (ZipUtil.exist(b, "lib/armeabi/" + str10) == 0) {
                }
            }
            try {
                Runtime.getRuntime().exec("chmod 700 " + str4 + "/" + str10);
                System.load(str4 + "/" + str10);
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (file.exists()) {
            System.loadLibrary(str);
            return;
        }
        String str11 = "";
        String str12 = str4 + "/" + str9;
        if (ZipUtil.exist(b, "lib/" + str5 + "/" + str9) == 0) {
            str11 = "lib/" + str5 + "/" + str9;
        } else if (z3) {
            if (!z4) {
                if (Build.VERSION.SDK_INT < 21 || (strArr2 = Build.SUPPORTED_ABIS) == null) {
                    z2 = false;
                    str3 = "";
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= strArr2.length) {
                            z2 = false;
                            str3 = "";
                        } else if (ZipUtil.exist(b, "lib/" + strArr2[i2].toLowerCase(Locale.US) + "/" + str10) == 0 || ZipUtil.exist(b, "lib/" + strArr2[i2].toLowerCase(Locale.US) + "/" + str9) == 0) {
                            String lowerCase = strArr2[i2].toLowerCase(Locale.US);
                            z2 = true;
                            if (ZipUtil.exist(b, "lib/x86_64/" + str9) == 0) {
                                str3 = "lib/x86_64/" + str9;
                            } else if (lowerCase.compareTo("arm64-v8a") == 0) {
                                if (ZipUtil.exist(b, "lib/arm64-v8a/" + str9) == 0) {
                                    str3 = "lib/arm64-v8a/" + str9;
                                } else {
                                    str3 = "";
                                }
                            } else if (lowerCase.compareTo("x86") == 0) {
                                if (ZipUtil.exist(b, "lib/x86/" + str9) == 0) {
                                    str3 = "lib/x86/" + str9;
                                } else {
                                    str3 = "";
                                }
                            } else if (lowerCase.compareTo("armeabi-v7a") != 0 && lowerCase.compareTo("armeabi") != 0) {
                                str3 = "";
                            } else if (ZipUtil.exist(b, "lib/x86/" + str9) == 0) {
                                str3 = "lib/x86/" + str9;
                            } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                                str3 = "lib/armeabi/" + str9;
                            } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                                str3 = "lib/armeabi-v7a/" + str9;
                            } else {
                                str3 = "";
                            }
                        } else {
                            i2++;
                        }
                    }
                }
                if (Build.VERSION.SDK_INT < 21 || !z2) {
                    if (ZipUtil.exist(b, "lib/x86_64/" + str9) == 0) {
                        str3 = "lib/x86_64/" + str9;
                    } else if (ZipUtil.exist(b, "lib/arm64-v8a/" + str9) == 0) {
                        str3 = "lib/arm64-v8a/" + str9;
                    } else if (ZipUtil.exist(b, "lib/x86/" + str9) == 0) {
                        str3 = "lib/x86/" + str9;
                    } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                        str3 = "lib/armeabi/" + str9;
                    } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                        str3 = "lib/armeabi-v7a/" + str9;
                    }
                }
                str11 = str3;
            } else if (ZipUtil.exist(b, "lib/arm64-v8a/" + str9) == 0) {
                str11 = "lib/arm64-v8a/" + str9;
            } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                str11 = "lib/armeabi/" + str9;
            } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                str11 = "lib/armeabi-v7a/" + str9;
            }
        } else if (z4) {
            if (z5 && ZipUtil.exist(b, "lib/mips/" + str9) == 0) {
                z = true;
                str2 = "lib/mips/" + str9;
            } else {
                z = false;
                str2 = "";
            }
            if (!z) {
                if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
                    str2 = "lib/armeabi/" + str9;
                } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
                    str2 = "lib/armeabi-v7a/" + str9;
                }
            }
            str11 = str2;
        } else if (ZipUtil.exist(b, "lib/x86/" + str9) == 0) {
            str11 = "lib/x86/" + str9;
        } else if (ZipUtil.exist(b, "lib/armeabi/" + str9) == 0) {
            str11 = "lib/armeabi/" + str9;
        } else if (ZipUtil.exist(b, "lib/armeabi-v7a/" + str9) == 0) {
            str11 = "lib/armeabi-v7a/" + str9;
        }
        if (ZipUtil.extract(b, str11, str12) == 0) {
            try {
                Runtime.getRuntime().exec("chmod 700 " + str12);
                System.load(str12);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    private static String f() {
        return "shella";
    }

    private static native void fixNativeResource(AssetManager assetManager, String str);

    private static native void fixUnityResource(AssetManager assetManager, String str);

    private static String g() {
        return "shella_version";
    }

    private static native void load(Context context);

    private static native void reciver(Intent intent);

    private static native void runCreate(Context context);

    public static native Enumeration txEntries(DexFile dexFile);

    public void a(Context context) {
        e();
        load(d);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        if (b(this)) {
            d(context);
            this.h = new Handler(getMainLooper());
            CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(this);
            userStrategy.setAppVersion("2.9.0.2");
            CrashReport.setSdkExtraData(this, "900015015", "2.9.0.2");
            CrashReport.initCrashReport(this, "900015015", false, userStrategy);
            new Thread(new b(this)).start();
            a(this);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        stackTrace[0].getClassName();
        stackTrace[0].getMethodName();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        if (className.compareTo("android.app.ActivityThread") == 0 && methodName.compareTo("installProvider") == 0) {
            c++;
            if (c == 1) {
                return "";
            }
            if (c == 2) {
                changeEnv(this);
            }
        }
        return mPKName;
    }

    @Override // android.app.Application
    public void onCreate() {
        runCreate(this);
        c(d);
    }
}

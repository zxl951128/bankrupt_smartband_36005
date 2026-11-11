package com.tencent.bugly.lejiagu.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.lejiagu.crashreport.crash.c;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;
import java.io.File;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class NativeCrashHandler {
    private static NativeCrashHandler a;
    private static boolean l = false;
    private final Context b;
    private final com.tencent.bugly.lejiagu.crashreport.common.info.a c;
    private final t d;
    private NativeExceptionHandler e;
    private String f;
    private final boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private com.tencent.bugly.lejiagu.crashreport.crash.b m;

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void testCrash();

    protected native String unregist();

    static /* synthetic */ boolean a(NativeCrashHandler nativeCrashHandler, String str) {
        return a(str);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0033  */
    /* JADX WARN: Code duplicated, block: B:17:0x0035 A[Catch: Throwable -> 0x0041, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Throwable -> 0x0041, blocks: (B:7:0x0013, B:17:0x0035), top: B:21:0x0013 }] */
    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, com.tencent.bugly.lejiagu.crashreport.common.info.a aVar, com.tencent.bugly.lejiagu.crashreport.crash.b bVar, t tVar, boolean z, String str) {
        Context applicationContext;
        boolean z2;
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            applicationContext = context;
        }
        this.b = applicationContext;
        if (str == null) {
            z2 = true;
            if (z2) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } else {
            try {
                if (str.trim().length() > 0) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (z2) {
                    str = context.getDir("bugly", 0).getAbsolutePath();
                }
            } catch (Throwable th) {
                str = "/data/data/" + com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context).c + "/app_bugly";
            }
        }
        this.m = bVar;
        this.f = str;
        this.c = aVar;
        this.d = tVar;
        this.g = z;
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.lejiagu.crashreport.common.info.a aVar, com.tencent.bugly.lejiagu.crashreport.crash.b bVar, com.tencent.bugly.lejiagu.crashreport.common.strategy.a aVar2, t tVar, boolean z, String str) {
        if (a == null) {
            a = new NativeCrashHandler(context, aVar, bVar, tVar, z, str);
        }
        return a;
    }

    public static synchronized NativeCrashHandler getInstance() {
        return a;
    }

    public synchronized String getDumpFilePath() {
        return this.f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f = str;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x009a A[Catch: all -> 0x00a2, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0004, B:6:0x0008, B:9:0x0012, B:11:0x0029, B:13:0x0032, B:15:0x005d, B:16:0x0070, B:18:0x007a, B:19:0x007d, B:21:0x0081, B:22:0x0089, B:34:0x00bf, B:31:0x00a5, B:33:0x00ab, B:26:0x009a, B:36:0x00c8, B:38:0x00cc, B:40:0x011a, B:42:0x015b, B:45:0x0184, B:25:0x0092), top: B:52:0x0004, inners: #1 }] */
    protected final synchronized void a(boolean z) {
        if (this.j) {
            u.d("native already registed!", new Object[0]);
        } else {
            this.e = new a(this.b, this.c, this.m, com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a(), this.f);
            if (this.i) {
                try {
                    String strRegist = regist(this.f, z, 1);
                    if (strRegist != null) {
                        u.a("Native Crash Report enable!", new Object[0]);
                        u.c("Check extra jni for Bugly NDK v%s", strRegist);
                        String strReplace = "2.1.1".replace(".", "");
                        String strReplace2 = strRegist.replace(".", "");
                        if (strReplace2.length() == 2) {
                            strReplace2 = strReplace2 + "0";
                        } else if (strReplace2.length() == 1) {
                            strReplace2 = strReplace2 + "00";
                        }
                        try {
                            if (Integer.parseInt(strReplace2) >= Integer.parseInt(strReplace)) {
                                l = true;
                            }
                        } catch (Throwable th) {
                        }
                        if (l) {
                            u.a("Extra bugly jni can be accessed.", new Object[0]);
                        } else {
                            u.d("Extra bugly jni can not be accessed.", new Object[0]);
                        }
                        this.c.l = strRegist;
                        this.j = true;
                    } else {
                        this.i = false;
                        this.h = false;
                    }
                } catch (Throwable th2) {
                    u.c("load bugly so fail", new Object[0]);
                }
            } else if (this.h) {
                try {
                    String str = (String) com.tencent.bugly.lejiagu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", null, new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE}, new Object[]{this.f, com.tencent.bugly.lejiagu.crashreport.common.info.a.a().q(), Integer.valueOf(com.tencent.bugly.lejiagu.crashreport.common.info.a.a().E()), 1});
                    if (str == null) {
                        str = (String) com.tencent.bugly.lejiagu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, new Class[]{String.class, String.class, Integer.TYPE}, new Object[]{this.f, com.tencent.bugly.lejiagu.crashreport.common.info.a.a().q(), Integer.valueOf(com.tencent.bugly.lejiagu.crashreport.common.info.a.a().E())});
                    }
                    if (str != null) {
                        this.j = true;
                        com.tencent.bugly.lejiagu.crashreport.common.info.a.a().l = str;
                        com.tencent.bugly.lejiagu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{true});
                        com.tencent.bugly.lejiagu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(com.tencent.bugly.lejiagu.b.a ? 3 : 5)});
                    } else {
                        this.i = false;
                        this.h = false;
                    }
                } catch (Throwable th3) {
                }
            } else {
                this.i = false;
                this.h = false;
            }
        }
    }

    public synchronized void startNativeMonitor() {
        if (this.i || this.h) {
            a(this.g);
        } else {
            this.d.b(new Runnable() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.jni.NativeCrashHandler.1
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    boolean z = true;
                    String str = NativeCrashHandler.this.c.k;
                    if (!(str == null || str.trim().length() <= 0)) {
                        String str2 = NativeCrashHandler.this.c.k;
                    }
                    String str3 = NativeCrashHandler.this.c.k;
                    if (str3 != null && str3.trim().length() > 0) {
                        z = false;
                    }
                    NativeCrashHandler.this.i = NativeCrashHandler.a(NativeCrashHandler.this, z ? "Bugly" : NativeCrashHandler.this.c.k);
                    if (NativeCrashHandler.this.i || NativeCrashHandler.this.h) {
                        NativeCrashHandler.this.a(NativeCrashHandler.this.g);
                        if (com.tencent.bugly.lejiagu.proguard.a.a(NativeCrashHandler.this.b, "native_record_lock", 10000L)) {
                            CrashDetailBean crashDetailBeanA = b.a(NativeCrashHandler.this.b, NativeCrashHandler.this.f, NativeCrashHandler.this.e);
                            if (crashDetailBeanA != null) {
                                if (!NativeCrashHandler.this.m.a(crashDetailBeanA)) {
                                    NativeCrashHandler.this.m.a(crashDetailBeanA, 5000L, false);
                                }
                                b.b(NativeCrashHandler.this.f);
                                u.a("get crash from native record!", new Object[0]);
                            }
                            NativeCrashHandler.this.a();
                            com.tencent.bugly.lejiagu.proguard.a.b(NativeCrashHandler.this.b, "native_record_lock");
                            return;
                        }
                        u.a("Failed to lock file for handling native crash record.", new Object[0]);
                    }
                }
            });
        }
    }

    private static boolean a(String str) {
        try {
            System.loadLibrary(str);
            try {
                u.a("[native] load so success: %s", str);
                return true;
            } catch (Throwable th) {
                return true;
            }
        } catch (Throwable th2) {
            return false;
        }
    }

    private synchronized void b() {
        try {
            if (!this.j) {
                u.d("native already unregisted!", new Object[0]);
            } else {
                try {
                    if (unregist() != null) {
                        u.a("Native Crash Report close!", new Object[0]);
                        this.j = false;
                    } else {
                        u.c("unregist bugly so success", new Object[0]);
                        try {
                            com.tencent.bugly.lejiagu.proguard.a.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{false});
                            this.j = false;
                            u.c("unregist rqd so success", new Object[0]);
                        } catch (Throwable th) {
                            u.c("unregist rqd so fail", new Object[0]);
                            this.i = false;
                            this.h = false;
                        }
                    }
                } catch (Throwable th2) {
                    u.c("unregist bugly so fail", new Object[0]);
                }
            }
        } catch (Throwable th3) {
            throw th3;
        }
    }

    public void testNativeCrash() {
        if (!this.i) {
            u.d("libBugly.so has not been load! so fail!", new Object[0]);
        } else {
            testCrash();
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    protected final void a() {
        File[] fileArrListFiles;
        long jO = com.tencent.bugly.lejiagu.proguard.a.o() - c.f;
        File file = new File(this.f);
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length != 0) {
            int length = "tomb_".length();
            int i = 0;
            for (File file2 : fileArrListFiles) {
                String name = file2.getName();
                if (name.startsWith("tomb_")) {
                    try {
                        int iIndexOf = name.indexOf(".txt");
                        if (iIndexOf <= 0 || Long.parseLong(name.substring(length, iIndexOf)) < jO) {
                            if (file2.delete()) {
                                i++;
                            }
                        }
                    } catch (Throwable th) {
                        u.e("tomb format error delete %s", name);
                    }
                }
            }
            u.c("clean tombs %d", Integer.valueOf(i));
        }
    }

    private synchronized void b(boolean z) {
        try {
            if (z) {
                startNativeMonitor();
            } else {
                b();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            u.a("user change native %b", Boolean.valueOf(z));
            this.k = z;
        }
    }

    public void setUserOpened(boolean z) {
        c(z);
        boolean z2 = com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a().c().d && isUserOpened();
        if (z2 != this.j) {
            u.a("native changed to %b", Boolean.valueOf(z2));
            b(z2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0030 A[Catch: all -> 0x0046, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:7:0x000b, B:8:0x001c, B:10:0x0028, B:12:0x002c, B:14:0x0030), top: B:21:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:17:0x0044  */
    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.d != this.j) {
                    u.d("server native changed to %b", Boolean.valueOf(strategyBean.d));
                }
                z = !com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a().c().d && this.k;
                if (z != this.j) {
                    u.a("native changed to %b", Boolean.valueOf(z));
                    b(z);
                }
            } else {
                if (com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a().c().d) {
                }
                if (z != this.j) {
                    u.a("native changed to %b", Boolean.valueOf(z));
                    b(z);
                }
            }
            throw th;
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if (!this.i || !l || str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            return appendNativeLog(str, str2, str3);
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (u.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if (!this.i || !l || str == null || str2 == null) {
            return false;
        }
        try {
            return putNativeKeyValue(str, str2);
        } catch (UnsatisfiedLinkError e) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (u.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }
}

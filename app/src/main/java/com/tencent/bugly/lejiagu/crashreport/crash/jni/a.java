package com.tencent.bugly.lejiagu.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.lejiagu.crashreport.common.info.AppInfo;
import com.tencent.bugly.lejiagu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.lejiagu.crashreport.crash.c;
import com.tencent.bugly.lejiagu.proguard.u;
import com.tencent.bugly.lejiagu.proguard.v;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final com.tencent.bugly.lejiagu.crashreport.crash.b b;
    private final com.tencent.bugly.lejiagu.crashreport.common.info.a c;
    private final com.tencent.bugly.lejiagu.crashreport.common.strategy.a d;
    private final String e;

    public a(Context context, com.tencent.bugly.lejiagu.crashreport.common.info.a aVar, com.tencent.bugly.lejiagu.crashreport.crash.b bVar, com.tencent.bugly.lejiagu.crashreport.common.strategy.a aVar2, String str) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
        this.e = str;
    }

    @Override // com.tencent.bugly.lejiagu.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        boolean zL = c.a().l();
        String str10 = zL ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (zL) {
            u.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.g();
        crashDetailBean.f = this.c.i;
        crashDetailBean.g = this.c.t();
        crashDetailBean.m = this.c.f();
        crashDetailBean.n = str3;
        crashDetailBean.o = str10;
        crashDetailBean.p = str4;
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = com.tencent.bugly.lejiagu.proguard.a.b(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.v();
        crashDetailBean.h = this.c.s();
        crashDetailBean.i = this.c.D();
        crashDetailBean.v = str8;
        crashDetailBean.I = str7;
        crashDetailBean.J = str6;
        crashDetailBean.K = str9;
        crashDetailBean.E = this.c.o();
        crashDetailBean.F = this.c.n();
        crashDetailBean.G = this.c.p();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.lejiagu.proguard.a.i();
            crashDetailBean.C = com.tencent.bugly.lejiagu.proguard.a.g();
            crashDetailBean.D = com.tencent.bugly.lejiagu.proguard.a.k();
            crashDetailBean.w = com.tencent.bugly.lejiagu.proguard.a.a(this.a, c.d, (String) null);
            crashDetailBean.x = v.a(true);
            crashDetailBean.L = this.c.a;
            crashDetailBean.M = this.c.n;
            crashDetailBean.O = this.c.A();
            crashDetailBean.P = this.c.B();
            crashDetailBean.Q = this.c.w();
            crashDetailBean.R = this.c.z();
            crashDetailBean.y = com.tencent.bugly.lejiagu.proguard.a.a(c.e, false);
            if (str == null) {
                crashDetailBean.z = this.c.d;
            }
            this.b.b(crashDetailBean);
        } else {
            crashDetailBean.B = -1L;
            crashDetailBean.C = -1L;
            crashDetailBean.D = -1L;
            crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            crashDetailBean.L = -1L;
            crashDetailBean.O = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = map;
            crashDetailBean.R = null;
            crashDetailBean.y = null;
            if (str == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr == null) {
                crashDetailBean.x = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.".getBytes();
            } else {
                crashDetailBean.x = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.lejiagu.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        u.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    @Override // com.tencent.bugly.lejiagu.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        u.a("Native Crash Happen v2", new Object[0]);
        try {
            this.d.c();
            if (!this.d.b()) {
                u.e("waiting for remote sync", new Object[0]);
                int i7 = 0;
                while (!this.d.b()) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i7 += 500;
                    if (i7 >= 5000) {
                        break;
                    }
                }
            }
            long j3 = (1000 * j) + (j2 / 1000);
            String strA = b.a(str3);
            String strA2 = "UNKNOWN(" + i4 + ")";
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + "(" + str5 + ")";
            } else if (i4 > 0) {
                Context context = this.a;
                strA2 = AppInfo.a(i4);
                str8 = str5;
                str9 = str;
            } else {
                str8 = str5;
                str9 = str;
            }
            if (!this.d.b()) {
                u.d("no remote but still store!", new Object[0]);
            }
            if (!this.d.c().d && this.d.b()) {
                u.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                com.tencent.bugly.lejiagu.crashreport.crash.b.a("NATIVE_CRASH", com.tencent.bugly.lejiagu.proguard.a.n(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + strA, null);
                if (str4 != null) {
                    File file = new File(str4);
                    if (!file.isFile() || !file.exists() || !file.canWrite()) {
                        return;
                    }
                    file.delete();
                    return;
                }
                return;
            }
            String str10 = null;
            String name = null;
            if (strArr != null) {
                HashMap map = new HashMap();
                for (String str11 : strArr) {
                    String[] strArrSplit = str11.split("=");
                    if (strArrSplit.length == 2) {
                        map.put(strArrSplit[0], strArrSplit[1]);
                    } else {
                        u.d("bad extraMsg %s", str11);
                    }
                }
                String str12 = (String) map.get("ExceptionProcessName");
                name = (String) map.get("ExceptionThreadName");
                str10 = str12;
            } else {
                u.c("not found extraMsg", new Object[0]);
            }
            if (str10 == null || str10.length() == 0) {
                str10 = this.c.d;
            } else {
                u.c("crash process name change to %s", str10);
            }
            if (name == null || name.length() == 0) {
                name = Thread.currentThread().getName();
            } else {
                u.c("crash thread name change to %s", name);
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = packageCrashDatas(str10, name, j3, str9, str2, strA, str8, strA2, str4, str7, null, null, true);
            if (crashDetailBeanPackageCrashDatas == null) {
                u.e("pkg crash datas fail!", new Object[0]);
                return;
            }
            com.tencent.bugly.lejiagu.crashreport.crash.b.a("NATIVE_CRASH", com.tencent.bugly.lejiagu.proguard.a.n(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + strA, crashDetailBeanPackageCrashDatas);
            if (!this.b.a(crashDetailBeanPackageCrashDatas, i3)) {
                this.b.a(crashDetailBeanPackageCrashDatas, 5000L, true);
            }
            b.b(this.e);
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
        }
    }
}

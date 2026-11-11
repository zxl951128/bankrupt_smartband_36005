package com.tencent.bugly.legu.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.legu.crashreport.common.info.AppInfo;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.legu.crashreport.crash.c;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final com.tencent.bugly.legu.crashreport.crash.b b;
    private final com.tencent.bugly.legu.crashreport.common.info.a c;
    private final com.tencent.bugly.legu.crashreport.common.strategy.a d;
    private final String e;

    public a(Context context, com.tencent.bugly.legu.crashreport.common.info.a aVar, com.tencent.bugly.legu.crashreport.crash.b bVar, com.tencent.bugly.legu.crashreport.common.strategy.a aVar2, String str) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
        this.e = str;
    }

    @Override // com.tencent.bugly.legu.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        int iIndexOf;
        String str10;
        int iIndexOf2;
        boolean zL = c.a().l();
        String str11 = zL ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (zL) {
            w.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.g();
        crashDetailBean.f = this.c.i;
        crashDetailBean.g = this.c.t();
        crashDetailBean.m = this.c.f();
        crashDetailBean.n = str3;
        crashDetailBean.o = str11;
        crashDetailBean.p = str4;
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = com.tencent.bugly.legu.proguard.a.b(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.v();
        crashDetailBean.h = this.c.s();
        crashDetailBean.i = this.c.E();
        crashDetailBean.v = str8;
        crashDetailBean.I = str7;
        crashDetailBean.J = str6;
        crashDetailBean.K = str9;
        crashDetailBean.E = this.c.o();
        crashDetailBean.F = this.c.n();
        crashDetailBean.G = this.c.p();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.legu.proguard.a.i();
            crashDetailBean.C = com.tencent.bugly.legu.proguard.a.g();
            crashDetailBean.D = com.tencent.bugly.legu.proguard.a.k();
            crashDetailBean.w = com.tencent.bugly.legu.proguard.a.a(this.a, c.d, (String) null);
            crashDetailBean.x = x.a(true);
            crashDetailBean.L = this.c.a;
            crashDetailBean.M = this.c.n;
            crashDetailBean.O = this.c.B();
            crashDetailBean.P = this.c.C();
            crashDetailBean.Q = this.c.w();
            crashDetailBean.R = this.c.A();
            crashDetailBean.y = com.tencent.bugly.legu.proguard.a.a(c.e, false);
            if (crashDetailBean.q != null && (iIndexOf = crashDetailBean.q.indexOf("java:\n")) > 0) {
                int length = iIndexOf + "java:\n".length();
                String strSubstring = crashDetailBean.q.substring(length, crashDetailBean.q.length() - 1);
                if (strSubstring.length() > 0 && crashDetailBean.y.containsKey(crashDetailBean.A) && (iIndexOf2 = (str10 = crashDetailBean.y.get(crashDetailBean.A)).indexOf(strSubstring)) > 0) {
                    String strSubstring2 = str10.substring(iIndexOf2);
                    crashDetailBean.y.put(crashDetailBean.A, strSubstring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, length);
                    crashDetailBean.q += strSubstring2;
                }
            }
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

    @Override // com.tencent.bugly.legu.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        w.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    @Override // com.tencent.bugly.legu.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        w.a("Native Crash Happen v2", new Object[0]);
        try {
            this.d.c();
            if (!this.d.b()) {
                w.e("waiting for remote sync", new Object[0]);
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
                w.d("no remote but still store!", new Object[0]);
            }
            if (!this.d.c().d && this.d.b()) {
                w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                com.tencent.bugly.legu.crashreport.crash.b.a("NATIVE_CRASH", com.tencent.bugly.legu.proguard.a.n(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + strA, null);
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
            String str11 = null;
            if (strArr != null) {
                HashMap map = new HashMap();
                for (String str12 : strArr) {
                    String[] strArrSplit = str12.split("=");
                    if (strArrSplit.length == 2) {
                        map.put(strArrSplit[0], strArrSplit[1]);
                    } else {
                        w.d("bad extraMsg %s", str12);
                    }
                }
                String str13 = (String) map.get("ExceptionProcessName");
                str11 = (String) map.get("ExceptionThreadName");
                str10 = str13;
            } else {
                w.c("not found extraMsg", new Object[0]);
            }
            if (str10 == null || str10.length() == 0) {
                str10 = this.c.d;
            } else {
                w.c("crash process name change to %s", str10);
            }
            if (str11 == null || str11.length() == 0) {
                Thread threadCurrentThread = Thread.currentThread();
                str11 = threadCurrentThread.getName() + "(" + threadCurrentThread.getId() + ")";
            } else {
                w.c("crash thread name change to %s", str11);
                for (Thread thread : Thread.getAllStackTraces().keySet()) {
                    if (thread.getName().equals(str11)) {
                        str11 = str11 + "(" + thread.getId() + ")";
                        break;
                    }
                }
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = packageCrashDatas(str10, str11, j3, str9, str2, strA, str8, strA2, str4, str7, null, null, true);
            if (crashDetailBeanPackageCrashDatas == null) {
                w.e("pkg crash datas fail!", new Object[0]);
                return;
            }
            com.tencent.bugly.legu.crashreport.crash.b.a("NATIVE_CRASH", com.tencent.bugly.legu.proguard.a.n(), this.c.d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + strA, crashDetailBeanPackageCrashDatas);
            if (!this.b.a(crashDetailBeanPackageCrashDatas, i3)) {
                this.b.a(crashDetailBeanPackageCrashDatas, 5000L, true);
            }
            b.b(this.e);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }
}

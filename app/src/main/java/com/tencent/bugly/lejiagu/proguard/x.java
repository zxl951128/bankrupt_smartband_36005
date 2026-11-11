package com.tencent.bugly.lejiagu.proguard;

import android.content.Context;
import com.tencent.bugly.lejiagu.BuglyStrategy;
import com.tencent.bugly.lejiagu.crashreport.crash.CrashDetailBean;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class x {
    private Context a;
    private com.tencent.bugly.lejiagu.crashreport.crash.b b;
    private com.tencent.bugly.lejiagu.crashreport.common.strategy.a c;
    private com.tencent.bugly.lejiagu.crashreport.common.info.a d;

    public x(Context context, com.tencent.bugly.lejiagu.crashreport.crash.b bVar, com.tencent.bugly.lejiagu.crashreport.common.strategy.a aVar, com.tencent.bugly.lejiagu.crashreport.common.info.a aVar2, BuglyStrategy.a aVar3) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final void a(Thread thread, String str, String str2, String str3) {
        String[] strArrSplit;
        u.e("U3D Crash Happen", new Object[0]);
        try {
            try {
                this.c.c();
                if (!this.c.b()) {
                    u.e("waiting for remote sync", new Object[0]);
                    int i = 0;
                    while (!this.c.b()) {
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i += 500;
                        if (i >= 5000) {
                            break;
                        }
                    }
                }
                if (!this.c.b()) {
                    u.d("no remote but still store!", new Object[0]);
                }
                if (!this.c.c().d && this.c.b()) {
                    u.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                    com.tencent.bugly.lejiagu.crashreport.crash.b.a("U3D", a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, null);
                    u.e("handle end", new Object[0]);
                    return;
                }
                CrashDetailBean crashDetailBean = new CrashDetailBean();
                crashDetailBean.B = a.i();
                crashDetailBean.C = a.g();
                crashDetailBean.D = a.k();
                crashDetailBean.E = this.d.o();
                crashDetailBean.F = this.d.n();
                crashDetailBean.G = this.d.p();
                crashDetailBean.w = a.a(this.a, com.tencent.bugly.lejiagu.crashreport.crash.c.d, (String) null);
                crashDetailBean.x = v.a(false);
                crashDetailBean.b = 4;
                crashDetailBean.e = this.d.g();
                crashDetailBean.f = this.d.i;
                crashDetailBean.g = this.d.t();
                crashDetailBean.m = this.d.f();
                crashDetailBean.n = str;
                crashDetailBean.o = str2;
                String str4 = "";
                if (str3 != null && (strArrSplit = str3.split("\n")) != null && strArrSplit.length > 0) {
                    str4 = strArrSplit[0];
                }
                crashDetailBean.p = str4;
                crashDetailBean.q = str3;
                crashDetailBean.r = System.currentTimeMillis();
                crashDetailBean.u = a.b(crashDetailBean.q.getBytes());
                crashDetailBean.y = a.a(com.tencent.bugly.lejiagu.crashreport.crash.c.e, false);
                crashDetailBean.z = this.d.d;
                crashDetailBean.A = thread.getName();
                crashDetailBean.H = this.d.v();
                crashDetailBean.h = this.d.s();
                crashDetailBean.i = this.d.D();
                crashDetailBean.L = this.d.a;
                crashDetailBean.M = this.d.n;
                crashDetailBean.O = this.d.A();
                crashDetailBean.P = this.d.B();
                crashDetailBean.Q = this.d.w();
                crashDetailBean.R = this.d.z();
                this.b.b(crashDetailBean);
                if (crashDetailBean == null) {
                    u.e("pkg crash datas fail!", new Object[0]);
                    u.e("handle end", new Object[0]);
                } else {
                    com.tencent.bugly.lejiagu.crashreport.crash.b.a("U3D", a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, crashDetailBean);
                    if (!this.b.a(crashDetailBean)) {
                        this.b.a(crashDetailBean, 5000L, true);
                    }
                    u.e("handle end", new Object[0]);
                }
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                u.e("handle end", new Object[0]);
            }
        } catch (Throwable th2) {
            u.e("handle end", new Object[0]);
            throw th2;
        }
    }
}

package com.tencent.bugly.lejiagu.crashreport.crash.h5;

import android.content.Context;
import com.tencent.bugly.lejiagu.BuglyStrategy;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.lejiagu.proguard.u;
import com.tencent.bugly.lejiagu.proguard.v;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    private Context a;
    private com.tencent.bugly.lejiagu.crashreport.crash.b b;
    private com.tencent.bugly.lejiagu.crashreport.common.strategy.a c;
    private com.tencent.bugly.lejiagu.crashreport.common.info.a d;

    public b(Context context, com.tencent.bugly.lejiagu.crashreport.crash.b bVar, com.tencent.bugly.lejiagu.crashreport.common.strategy.a aVar, com.tencent.bugly.lejiagu.crashreport.common.info.a aVar2, BuglyStrategy.a aVar3) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final void a(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        u.e("H5 Crash Happen", new Object[0]);
        try {
            try {
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
                StrategyBean strategyBeanC = this.c.c();
                if (!strategyBeanC.d && this.c.b()) {
                    u.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                    com.tencent.bugly.lejiagu.crashreport.crash.b.a("H5", com.tencent.bugly.lejiagu.proguard.a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, null);
                    u.e("handle end", new Object[0]);
                    return;
                }
                if (!strategyBeanC.i) {
                    u.e("cocos report is disabled.", new Object[0]);
                    u.e("handle end", new Object[0]);
                    return;
                }
                CrashDetailBean crashDetailBean = new CrashDetailBean();
                crashDetailBean.B = com.tencent.bugly.lejiagu.proguard.a.i();
                crashDetailBean.C = com.tencent.bugly.lejiagu.proguard.a.g();
                crashDetailBean.D = com.tencent.bugly.lejiagu.proguard.a.k();
                crashDetailBean.E = this.d.o();
                crashDetailBean.F = this.d.n();
                crashDetailBean.G = this.d.p();
                crashDetailBean.w = com.tencent.bugly.lejiagu.proguard.a.a(this.a, com.tencent.bugly.lejiagu.crashreport.crash.c.d, (String) null);
                crashDetailBean.b = 5;
                crashDetailBean.e = this.d.g();
                crashDetailBean.f = this.d.i;
                crashDetailBean.g = this.d.t();
                crashDetailBean.m = this.d.f();
                crashDetailBean.n = str;
                crashDetailBean.o = str2;
                crashDetailBean.p = "0";
                crashDetailBean.q = str3;
                crashDetailBean.r = System.currentTimeMillis();
                crashDetailBean.u = com.tencent.bugly.lejiagu.proguard.a.b(crashDetailBean.q.getBytes());
                crashDetailBean.y = com.tencent.bugly.lejiagu.proguard.a.a(com.tencent.bugly.lejiagu.crashreport.crash.c.e, false);
                crashDetailBean.z = this.d.d;
                crashDetailBean.A = thread.getName();
                crashDetailBean.H = this.d.v();
                crashDetailBean.h = this.d.s();
                crashDetailBean.L = this.d.a;
                crashDetailBean.M = this.d.n;
                crashDetailBean.O = this.d.A();
                crashDetailBean.P = this.d.B();
                crashDetailBean.Q = this.d.w();
                crashDetailBean.R = this.d.z();
                this.b.b(crashDetailBean);
                crashDetailBean.x = v.a(false);
                if (crashDetailBean.N == null) {
                    crashDetailBean.N = new LinkedHashMap();
                }
                if (map != null) {
                    crashDetailBean.N.putAll(map);
                }
                if (crashDetailBean == null) {
                    u.e("pkg crash datas fail!", new Object[0]);
                    u.e("handle end", new Object[0]);
                } else {
                    com.tencent.bugly.lejiagu.crashreport.crash.b.a("H5", com.tencent.bugly.lejiagu.proguard.a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, crashDetailBean);
                    if (!this.b.a(crashDetailBean)) {
                        this.b.a(crashDetailBean, 5000L, false);
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

package com.tencent.bugly.legu.proguard;

import android.content.Context;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.crashreport.crash.CrashDetailBean;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class y {
    private Context a;
    private com.tencent.bugly.legu.crashreport.crash.b b;
    private com.tencent.bugly.legu.crashreport.common.strategy.a c;
    private com.tencent.bugly.legu.crashreport.common.info.a d;

    public y(Context context, com.tencent.bugly.legu.crashreport.crash.b bVar, com.tencent.bugly.legu.crashreport.common.strategy.a aVar, com.tencent.bugly.legu.crashreport.common.info.a aVar2, BuglyStrategy.a aVar3) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final void a(Thread thread, int i, String str, String str2, String str3) {
        String[] strArrSplit;
        w.e("Cocos2d-x Crash Happen", new Object[0]);
        try {
            try {
                this.c.c();
                if (!this.c.b()) {
                    w.e("waiting for remote sync", new Object[0]);
                    int i2 = 0;
                    while (!this.c.b()) {
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i2 += 500;
                        if (i2 >= 5000) {
                            break;
                        }
                    }
                }
                if (!this.c.b()) {
                    w.d("no remote but still store!", new Object[0]);
                }
                StrategyBean strategyBeanC = this.c.c();
                if (!strategyBeanC.d && this.c.b()) {
                    w.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                    com.tencent.bugly.legu.crashreport.crash.b.a("Cocos2dx", a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, null);
                    w.e("handle end", new Object[0]);
                    return;
                }
                if (!strategyBeanC.h) {
                    w.e("cocos report is disabled.", new Object[0]);
                    w.e("handle end", new Object[0]);
                    return;
                }
                CrashDetailBean crashDetailBean = new CrashDetailBean();
                crashDetailBean.B = a.i();
                crashDetailBean.C = a.g();
                crashDetailBean.D = a.k();
                crashDetailBean.E = this.d.o();
                crashDetailBean.F = this.d.n();
                crashDetailBean.G = this.d.p();
                crashDetailBean.w = a.a(this.a, com.tencent.bugly.legu.crashreport.crash.c.d, (String) null);
                crashDetailBean.x = x.a(false);
                crashDetailBean.b = i;
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
                crashDetailBean.y = a.a(com.tencent.bugly.legu.crashreport.crash.c.e, false);
                crashDetailBean.z = this.d.d;
                crashDetailBean.A = thread.getName() + "(" + thread.getId() + ")";
                crashDetailBean.H = this.d.v();
                crashDetailBean.h = this.d.s();
                crashDetailBean.i = this.d.E();
                crashDetailBean.L = this.d.a;
                crashDetailBean.M = this.d.n;
                crashDetailBean.O = this.d.B();
                crashDetailBean.P = this.d.C();
                crashDetailBean.Q = this.d.w();
                crashDetailBean.R = this.d.A();
                this.b.b(crashDetailBean);
                if (crashDetailBean == null) {
                    w.e("pkg crash datas fail!", new Object[0]);
                    w.e("handle end", new Object[0]);
                } else {
                    com.tencent.bugly.legu.crashreport.crash.b.a("Cocos2dx", a.n(), this.d.d, thread, str + "\n" + str2 + "\n" + str3, crashDetailBean);
                    if (!this.b.a(crashDetailBean)) {
                        this.b.a(crashDetailBean, 5000L, false);
                    }
                    w.e("handle end", new Object[0]);
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
                w.e("handle end", new Object[0]);
            }
        } catch (Throwable th2) {
            w.e("handle end", new Object[0]);
            throw th2;
        }
    }
}

package com.tencent.bugly.lejiagu.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.lejiagu.BuglyStrategy;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.lejiagu.proguard.l;
import com.tencent.bugly.lejiagu.proguard.m;
import com.tencent.bugly.lejiagu.proguard.o;
import com.tencent.bugly.lejiagu.proguard.r;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static int a = 0;
    public static boolean b = false;
    public static boolean c = true;
    public static int d = 20000;
    public static int e = 20000;
    public static long f = 604800000;
    public static String g = null;
    public static boolean h = false;
    public static String i = null;
    public static int j = 5000;
    private static c n;
    public final b k;
    public BuglyStrategy.a l;
    private final Context m;
    private final e o;
    private final NativeCrashHandler p;
    private com.tencent.bugly.lejiagu.crashreport.common.strategy.a q;
    private t r;
    private final com.tencent.bugly.lejiagu.crashreport.crash.anr.b s;
    private Boolean t;

    private c(int i2, Context context, t tVar, boolean z, BuglyStrategy.a aVar, l lVar, String str) {
        Context applicationContext;
        a = i2;
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            applicationContext = context;
        }
        this.m = applicationContext;
        this.q = com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a();
        r rVarA = r.a();
        m mVarA = m.a();
        com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a(applicationContext);
        this.r = tVar;
        this.l = aVar;
        this.k = new b(i2, applicationContext, rVarA, mVarA, this.q, aVar, lVar);
        this.o = new e(applicationContext, this.k, this.q, aVarA);
        this.p = NativeCrashHandler.getInstance(applicationContext, aVarA, this.k, this.q, tVar, z, str);
        this.s = new com.tencent.bugly.lejiagu.crashreport.crash.anr.b(applicationContext, this.q, aVarA, tVar, this.k);
        BuglyBroadcastRecevier buglyBroadcastRecevier = BuglyBroadcastRecevier.getInstance();
        buglyBroadcastRecevier.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
        buglyBroadcastRecevier.regist(applicationContext, this.k);
    }

    public static synchronized void a(int i2, Context context, boolean z, BuglyStrategy.a aVar, l lVar, String str) {
        if (n == null) {
            n = new c(1004, context, t.a(), z, aVar, null, null);
        }
    }

    public static c a() {
        return n;
    }

    public final void a(StrategyBean strategyBean) {
        this.o.a(strategyBean);
        this.p.onStrategyChanged(strategyBean);
        this.s.a(strategyBean);
    }

    public final boolean b() {
        Boolean bool = this.t;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = com.tencent.bugly.lejiagu.crashreport.common.info.a.a().d;
        List<o> listA = m.a().a(1);
        ArrayList arrayList = new ArrayList();
        if (listA != null && listA.size() > 0) {
            for (o oVar : listA) {
                if (str.equals(oVar.c)) {
                    this.t = true;
                    arrayList.add(oVar);
                }
            }
            if (arrayList.size() > 0) {
                m.a();
                m.a(arrayList);
            }
            return true;
        }
        this.t = false;
        return false;
    }

    public final synchronized void c() {
        this.o.a();
        this.p.setUserOpened(true);
        this.s.a(true);
    }

    public final synchronized void d() {
        this.o.b();
        this.p.setUserOpened(false);
        this.s.a(false);
    }

    public final void e() {
        this.o.a();
    }

    public final void f() {
        this.p.setUserOpened(false);
    }

    public final void g() {
        this.p.setUserOpened(true);
    }

    public final void h() {
        this.s.a(true);
    }

    public final void i() {
        this.s.a(false);
    }

    public final synchronized void j() {
        this.p.testNativeCrash();
    }

    public final synchronized void k() {
        int i2 = 0;
        synchronized (this) {
            com.tencent.bugly.lejiagu.crashreport.crash.anr.b bVar = this.s;
            while (true) {
                i2++;
                if (i2 >= 30) {
                    break;
                }
                try {
                    u.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } catch (Throwable th) {
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final boolean l() {
        return this.s.a();
    }

    public final void a(final Thread thread, final Throwable th, boolean z, String str, byte[] bArr) {
        final String str2 = null;
        t tVar = this.r;
        final boolean z2 = false;
        final byte b2 = 0 == true ? 1 : 0;
        tVar.b(new Runnable() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    u.c("post a throwable %b", Boolean.valueOf(z2));
                    c.this.o.a(thread, th, false, str2, b2);
                } catch (Throwable th2) {
                    if (!u.b(th2)) {
                        th2.printStackTrace();
                    }
                    u.e("java catch error: %s", th.toString());
                }
            }
        });
    }

    public final void a(CrashDetailBean crashDetailBean) {
        this.k.c(crashDetailBean);
    }

    public final void a(long j2) {
        t.a().a(new Thread() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.c.2
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() throws Throwable {
                List<CrashDetailBean> arrayList;
                if (com.tencent.bugly.lejiagu.proguard.a.a(c.this.m, "local_crash_lock", 10000L)) {
                    List<CrashDetailBean> listA = c.this.k.a();
                    if (listA != null && listA.size() > 0) {
                        int size = listA.size();
                        if (size > 100) {
                            arrayList = new ArrayList<>();
                            Collections.sort(listA);
                            for (int i2 = 0; i2 < 100; i2++) {
                                arrayList.add(listA.get((size - 1) - i2));
                            }
                        } else {
                            arrayList = listA;
                        }
                        c.this.k.a(arrayList, 0L, false);
                    }
                    com.tencent.bugly.lejiagu.proguard.a.b(c.this.m, "local_crash_lock");
                }
            }
        }, j2);
    }
}

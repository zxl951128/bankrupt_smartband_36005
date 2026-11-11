package com.tencent.bugly.lejiagu.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.lejiagu.proguard.r;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class BuglyBroadcastRecevier extends BroadcastReceiver {
    public static final long UPLOADLIMITED = 60000;
    private IntentFilter a = new IntentFilter();
    private Context b;
    private String c;
    public static String ACTION_PROCESS_LAUNCHED = "com.tencent.feedback.A01";
    public static String ACTION_PROCESS_CRASHED = "com.tencent.feedback.A02";
    private static BuglyBroadcastRecevier d = null;

    public static synchronized BuglyBroadcastRecevier getInstance() {
        if (d == null) {
            d = new BuglyBroadcastRecevier();
        }
        return d;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.b != null) {
            this.b.unregisterReceiver(this);
        }
    }

    public synchronized void addFilter(String str) {
        if (!this.a.hasAction(str)) {
            this.a.addAction(str);
        }
        u.c("add action %s", str);
    }

    public synchronized void regist(Context context, b bVar) {
        try {
            u.a("regis BC", new Object[0]);
            this.b = context;
            context.registerReceiver(this, this.a);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void unregist(Context context) {
        try {
            u.a("unregis BC", new Object[0]);
            context.unregisterReceiver(this);
            this.b = context;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    private synchronized boolean a(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            if (context == null || intent == null) {
                z = false;
            } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                String strE = com.tencent.bugly.lejiagu.proguard.a.e(this.b);
                u.c("is Connect BC " + strE, new Object[0]);
                u.a("network %s changed to %s", this.c, strE);
                if (strE == null) {
                    this.c = null;
                } else {
                    String str = this.c;
                    this.c = strE;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    com.tencent.bugly.lejiagu.crashreport.common.strategy.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a();
                    r rVarA = r.a();
                    com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA2 = com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context);
                    if (aVarA == null || rVarA == null || aVarA2 == null) {
                        u.d("not inited BC not work", new Object[0]);
                    } else if (!strE.equals(str)) {
                        if (jCurrentTimeMillis - rVarA.a(c.a) > 60000) {
                            u.a("try to upload crash on network changed.", new Object[0]);
                            c.a().a(0L);
                        }
                        if (jCurrentTimeMillis - rVarA.a(1001) > 60000) {
                            u.a("try to upload userinfo on network changed.", new Object[0]);
                            t.a().b(new Runnable(this) { // from class: com.tencent.bugly.lejiagu.crashreport.crash.BuglyBroadcastRecevier.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    com.tencent.bugly.lejiagu.crashreport.biz.b.a.b();
                                }
                            });
                        }
                    }
                }
            } else {
                z = false;
            }
        }
        return z;
    }
}

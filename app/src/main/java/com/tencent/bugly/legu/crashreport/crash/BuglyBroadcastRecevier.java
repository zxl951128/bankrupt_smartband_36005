package com.tencent.bugly.legu.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;

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
        w.c("add action %s", str);
    }

    public synchronized void regist(Context context, b bVar) {
        try {
            w.a("regis BC", new Object[0]);
            this.b = context;
            context.registerReceiver(this, this.a);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void unregist(Context context) {
        try {
            w.a("unregis BC", new Object[0]);
            context.unregisterReceiver(this);
            this.b = context;
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            if (!w.a(th)) {
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
                String strE = com.tencent.bugly.legu.proguard.a.e(this.b);
                w.c("is Connect BC " + strE, new Object[0]);
                w.a("network %s changed to %s", this.c, strE);
                if (strE == null) {
                    this.c = null;
                } else {
                    String str = this.c;
                    this.c = strE;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    com.tencent.bugly.legu.crashreport.common.strategy.a aVarA = com.tencent.bugly.legu.crashreport.common.strategy.a.a();
                    t tVarA = t.a();
                    com.tencent.bugly.legu.crashreport.common.info.a aVarA2 = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
                    if (aVarA == null || tVarA == null || aVarA2 == null) {
                        w.d("not inited BC not work", new Object[0]);
                    } else if (!strE.equals(str)) {
                        if (jCurrentTimeMillis - tVarA.a(c.a) > 60000) {
                            w.a("try to upload crash on network changed.", new Object[0]);
                            c.a().a(0L);
                        }
                        if (jCurrentTimeMillis - tVarA.a(1001) > 60000) {
                            w.a("try to upload userinfo on network changed.", new Object[0]);
                            v.a().b(new Runnable(this) { // from class: com.tencent.bugly.legu.crashreport.crash.BuglyBroadcastRecevier.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    com.tencent.bugly.legu.crashreport.biz.b.a.b();
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

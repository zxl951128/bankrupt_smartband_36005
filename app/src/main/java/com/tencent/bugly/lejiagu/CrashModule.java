package com.tencent.bugly.lejiagu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.tencent.bugly.lejiagu.crashreport.CrashReport;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.crashreport.crash.c;
import com.tencent.bugly.lejiagu.crashreport.crash.d;
import com.tencent.bugly.lejiagu.crashreport.inner.InnerAPI;
import com.tencent.bugly.lejiagu.proguard.u;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class CrashModule extends a {
    public static final int MODULE_ID = 1004;
    private static boolean c = false;
    private static CrashModule d = new CrashModule();
    private long a;
    private BuglyStrategy.a b;

    public static CrashModule getInstance() {
        return d;
    }

    public static boolean hasInitialized() {
        return c;
    }

    @Override // com.tencent.bugly.lejiagu.a
    public void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null && !c) {
            c = true;
            CrashReport.setContext(context);
            a(context, buglyStrategy);
            c.a(1004, context, z, this.b, null, null);
            c cVarA = c.a();
            cVarA.e();
            if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                cVarA.g();
            } else {
                u.a("[crash] Closed native crash monitor!", new Object[0]);
                cVarA.f();
            }
            if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                cVarA.h();
            } else {
                u.a("[crash] Closed ANR monitor!", new Object[0]);
                cVarA.i();
            }
            InnerAPI.context = context;
            d.a(context);
            c.a().a(this.a);
        }
    }

    private synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(libBuglySOFilePath)) {
                com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context).k = libBuglySOFilePath;
                u.a("setted libBugly.so file path :%s", libBuglySOFilePath);
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.b = buglyStrategy.getCrashHandleCallback();
                u.a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0) {
                this.a = buglyStrategy.getAppReportDelay();
                u.a("setted delay: %d", Long.valueOf(this.a));
            }
        }
    }

    @Override // com.tencent.bugly.lejiagu.a
    public void onServerStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            c cVarA = c.a();
            if (cVarA != null) {
                cVarA.a(strategyBean);
            }
            d.a(strategyBean);
        }
    }

    @Override // com.tencent.bugly.lejiagu.a
    public void onDbCreate(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder(64);
        sb.append(" CREATE TABLE t_cr").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tm").append(" int").append(" , _s1").append(" text").append(" , _up").append(" int").append(" , _me").append(" int").append(" , _uc").append(" int").append(" , _dt").append(" blob").append(" ) ");
        u.c("create %s", sb);
        sQLiteDatabase.execSQL(sb.toString());
    }
}

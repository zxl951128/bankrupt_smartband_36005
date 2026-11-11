package com.tencent.bugly.lejiagu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public int id;
    public String moduleName;

    public void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
    }

    public void onDbCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onDbUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onDbDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
    }
}

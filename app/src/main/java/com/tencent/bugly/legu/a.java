package com.tencent.bugly.legu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.proguard.w;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public int id;
    public String moduleName;

    public abstract String[] getTables();

    public abstract void init(Context context, boolean z, BuglyStrategy buglyStrategy);

    public void onDbCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onDbUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            if (getTables() != null) {
                for (String str : getTables()) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
                onDbCreate(sQLiteDatabase);
            }
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
        }
    }

    public void onDbDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            if (getTables() != null) {
                for (String str : getTables()) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
                onDbCreate(sQLiteDatabase);
            }
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
        }
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
    }
}

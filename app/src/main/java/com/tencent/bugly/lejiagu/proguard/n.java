package com.tencent.bugly.lejiagu.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class n extends SQLiteOpenHelper {
    private static int a = 9;
    private Context b;
    private List<com.tencent.bugly.lejiagu.a> c;

    public n(Context context, List<com.tencent.bugly.lejiagu.a> list) {
        StringBuilder sb = new StringBuilder("bugly_db_");
        com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context).getClass();
        super(context, sb.append("lejiagu").toString(), (SQLiteDatabase.CursorFactory) null, a);
        this.b = context;
        this.c = list;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE t_ui").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tm").append(" int").append(" , _ut").append(" int").append(" , _tp").append(" int").append(" , _dt").append(" blob").append(" , _pc").append(" text").append(" ) ");
            String string = sb.toString();
            u.c("create %s", string);
            sQLiteDatabase.execSQL(string);
            sb.setLength(0);
            sb.append(" CREATE TABLE t_lr").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tp").append(" int").append(" , _tm").append(" int").append(" , _pc").append(" text").append(" , _th").append(" text").append(" , _dt").append(" blob").append(" ) ");
            String string2 = sb.toString();
            u.c("create %s", string2);
            sQLiteDatabase.execSQL(string2);
            sb.setLength(0);
            sb.append(" CREATE TABLE t_pf").append(" ( _id").append(" integer").append(" , _tp").append(" text").append(" , _tm").append(" int").append(" , _dt").append(" blob").append(",primary key(_id").append(",_tp").append(" )) ");
            String string3 = sb.toString();
            u.c("create %s", string3);
            sQLiteDatabase.execSQL(string3);
        } catch (Throwable th) {
            if (!u.b(th)) {
                th.printStackTrace();
            }
        }
        if (this.c != null) {
            Iterator<com.tencent.bugly.lejiagu.a> it = this.c.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onDbCreate(sQLiteDatabase);
                } catch (Throwable th2) {
                    if (!u.b(th2)) {
                        th2.printStackTrace();
                    }
                }
            }
        }
    }

    private synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        boolean z = true;
        synchronized (this) {
            try {
                for (String str : new String[]{"t_cr", "t_lr", "t_ui", "t_pf"}) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
            } catch (Throwable th) {
                if (!u.b(th)) {
                    th.printStackTrace();
                }
                z = false;
            }
        }
        return z;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            u.d("upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.c != null) {
                Iterator<com.tencent.bugly.lejiagu.a> it = this.c.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onDbUpgrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!u.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                u.d("drop fail delete db", new Object[0]);
                File databasePath = this.b.getDatabasePath("bugly_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @TargetApi(11)
    public final synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (a.d() >= 11) {
            u.d("drowngrade %d to %d drop tables!}", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.c != null) {
                Iterator<com.tencent.bugly.lejiagu.a> it = this.c.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onDbDowngrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!u.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                u.d("drop fail delete db", new Object[0]);
                File databasePath = this.b.getDatabasePath("bugly_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase readableDatabase;
        int i = 0;
        synchronized (this) {
            readableDatabase = null;
            while (readableDatabase == null && i < 5) {
                i++;
                try {
                    readableDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    u.d("try db count %d", Integer.valueOf(i));
                    if (i == 5) {
                        u.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return readableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        int i = 0;
        synchronized (this) {
            writableDatabase = null;
            while (writableDatabase == null && i < 5) {
                i++;
                try {
                    writableDatabase = super.getWritableDatabase();
                } catch (Throwable th) {
                    u.d("try db %d", Integer.valueOf(i));
                    if (i == 5) {
                        u.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (writableDatabase == null) {
                u.d("db error delay error record 1min", new Object[0]);
            }
        }
        return writableDatabase;
    }
}

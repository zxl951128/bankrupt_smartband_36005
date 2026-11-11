package com.tencent.bugly.legu.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class p extends SQLiteOpenHelper {
    private static int a = 11;
    private Context b;
    private List<com.tencent.bugly.legu.a> c;

    public p(Context context, List<com.tencent.bugly.legu.a> list) {
        StringBuilder sb = new StringBuilder("bugly_db_");
        com.tencent.bugly.legu.crashreport.common.info.a.a(context).getClass();
        super(context, sb.append("legu").toString(), (SQLiteDatabase.CursorFactory) null, a);
        this.b = context;
        this.c = list;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_ui").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tm").append(" int").append(" , _ut").append(" int").append(" , _tp").append(" int").append(" , _dt").append(" blob").append(" , _pc").append(" text").append(" ) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_lr").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tp").append(" int").append(" , _tm").append(" int").append(" , _pc").append(" text").append(" , _th").append(" text").append(" , _dt").append(" blob").append(" ) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_pf").append(" ( _id").append(" integer").append(" , _tp").append(" text").append(" , _tm").append(" int").append(" , _dt").append(" blob").append(",primary key(_id").append(",_tp").append(" )) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_crd").append(" ( _id").append(" integer").append(" , _pc").append(" text").append(" , _tm").append(" int").append(" , _fl").append(" int").append(" , _sv").append(" text").append(" , _av").append(" text").append(" , _uid").append(" integer").append(",primary key(_id").append(",_pc").append(",_uid").append(" )) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_cr").append(" ( _id").append(" INTEGER PRIMARY KEY").append(" , _tm").append(" int").append(" , _s1").append(" text").append(" , _up").append(" int").append(" , _me").append(" int").append(" , _uc").append(" int").append(" , _dt").append(" blob").append(" ) ");
            w.c("create %s", sb);
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS dl_1002").append(" (_id").append(" integer primary key autoincrement, _dUrl").append(" varchar(100), _sFile").append(" varchar(100), _sLen").append(" INTEGER, _tLen").append(" INTEGER, _MD5").append(" varchar(100), _DLTIME").append(" INTEGER)");
            w.c("create %s", sb);
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append("CREATE TABLE IF NOT EXISTS ge_1002").append(" (_id").append(" integer primary key autoincrement, _time").append(" INTEGER, _datas").append(" blob)");
            w.c("create %s", sb);
            sQLiteDatabase.execSQL(sb.toString());
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS st_1002").append(" ( _id").append(" integer").append(" , _tp").append(" text").append(" , _tm").append(" int").append(" , _dt").append(" blob").append(",primary key(_id").append(",_tp").append(" )) ");
            w.c("create %s", sb.toString());
            sQLiteDatabase.execSQL(sb.toString());
        } catch (Throwable th) {
            if (!w.b(th)) {
                th.printStackTrace();
            }
        }
        if (this.c != null) {
            Iterator<com.tencent.bugly.legu.a> it = this.c.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onDbCreate(sQLiteDatabase);
                } catch (Throwable th2) {
                    if (!w.b(th2)) {
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
                for (String str : new String[]{"t_crd", "t_lr", "t_ui", "t_pf"}) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
            } catch (Throwable th) {
                if (!w.b(th)) {
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
            w.d("upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.c != null) {
                Iterator<com.tencent.bugly.legu.a> it = this.c.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onDbUpgrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!w.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                w.d("drop fail delete db", new Object[0]);
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
            w.d("drowngrade %d to %d drop tables!}", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.c != null) {
                Iterator<com.tencent.bugly.legu.a> it = this.c.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onDbDowngrade(sQLiteDatabase, i, i2);
                    } catch (Throwable th) {
                        if (!w.b(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                w.d("drop fail delete db", new Object[0]);
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
                    w.d("try db count %d", Integer.valueOf(i));
                    if (i == 5) {
                        w.e("get db fail!", new Object[0]);
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
                    w.d("try db %d", Integer.valueOf(i));
                    if (i == 5) {
                        w.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (writableDatabase == null) {
                w.d("db error delay error record 1min", new Object[0]);
            }
        }
        return writableDatabase;
    }
}

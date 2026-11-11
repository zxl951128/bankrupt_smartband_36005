package com.tencent.bugly.lejiagu.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    private static m a = null;
    private static n b = null;
    private static boolean c = false;

    private m(Context context, List<com.tencent.bugly.lejiagu.a> list) {
        b = new n(context, list);
    }

    public static synchronized m a(Context context, List<com.tencent.bugly.lejiagu.a> list) {
        if (a == null) {
            a = new m(context, list);
        }
        return a;
    }

    public static synchronized m a() {
        return a;
    }

    public final long a(String str, ContentValues contentValues, l lVar, boolean z) {
        return a(str, contentValues, (l) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, l lVar, boolean z) {
        return a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    public final int a(String str, String str2, String[] strArr, l lVar, boolean z) {
        return a(str, str2, (String[]) null, (l) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized long a(String str, ContentValues contentValues, l lVar) {
        long j = 0;
        synchronized (this) {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null && contentValues != null) {
                        long jReplace = writableDatabase.replace(str, "_id", contentValues);
                        if (jReplace >= 0) {
                            u.c("[db] insert %s success", str);
                        } else {
                            u.d("[db] replace %s error", str);
                        }
                        j = jReplace;
                    }
                    if (lVar != null) {
                        Long.valueOf(j);
                    }
                } catch (Throwable th) {
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                    if (lVar != null) {
                        Long.valueOf(0L);
                    }
                }
            } catch (Throwable th2) {
                if (lVar != null) {
                    Long.valueOf(0L);
                }
                throw th2;
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, l lVar) {
        Cursor cursorQuery;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            cursorQuery = writableDatabase != null ? writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6) : null;
            if (lVar != null) {
            }
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            cursorQuery = lVar != null ? null : null;
        }
        return cursorQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int a(String str, String str2, String[] strArr, l lVar) {
        int iDelete = 0;
        synchronized (this) {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    iDelete = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
                    if (lVar != null) {
                        Integer.valueOf(iDelete);
                    }
                } catch (Throwable th) {
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                    if (lVar != null) {
                        Integer.valueOf(0);
                    }
                }
            } catch (Throwable th2) {
                if (lVar != null) {
                    Integer.valueOf(0);
                }
                throw th2;
            }
        }
        return iDelete;
    }

    public final boolean a(int i, String str, byte[] bArr, l lVar, boolean z) {
        return a(i, str, bArr, (l) null);
    }

    public final Map<String, byte[]> a(int i, l lVar, boolean z) {
        return a(i, (l) null);
    }

    public final boolean a(int i, String str, l lVar, boolean z) {
        return a(555, str, (l) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, l lVar) {
        boolean zB = false;
        try {
            o oVar = new o();
            oVar.a = i;
            oVar.f = str;
            oVar.e = System.currentTimeMillis();
            oVar.g = bArr;
            zB = b(oVar);
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
        } finally {
            if (lVar != null) {
                Boolean.valueOf(false);
            }
        }
        return zB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, byte[]> a(int i, l lVar) {
        Throwable th;
        HashMap map;
        try {
            List<o> listC = c(i);
            HashMap map2 = new HashMap();
            try {
                for (o oVar : listC) {
                    byte[] bArr = oVar.g;
                    if (bArr != null) {
                        map2.put(oVar.f, bArr);
                    }
                }
                return lVar != null ? map2 : map2;
            } catch (Throwable th2) {
                map = map2;
                th = th2;
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                if (lVar != null) {
                }
                return map;
            }
        } catch (Throwable th3) {
            th = th3;
            map = null;
        }
    }

    public final boolean a(o oVar) {
        ContentValues contentValuesC;
        if (oVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesC = c(oVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_lr", "_id", contentValuesC);
            if (jReplace < 0) {
                return false;
            }
            u.c("insert %s success!", "t_lr");
            oVar.a = jReplace;
            return true;
        } catch (Throwable th) {
            if (u.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
        throw th;
    }

    private boolean b(o oVar) {
        ContentValues contentValuesD;
        if (oVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesD = d(oVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_pf", "_id", contentValuesD);
            if (jReplace < 0) {
                return false;
            }
            u.c("insert %s success!", "t_pf");
            oVar.a = jReplace;
            return true;
        } catch (Throwable th) {
            if (u.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
        throw th;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0058 A[Catch: all -> 0x00d4, TRY_LEAVE, TryCatch #1 {all -> 0x00d4, blocks: (B:24:0x0052, B:26:0x0058), top: B:57:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x008f  */
    public final List<o> a(int i) {
        String str;
        Cursor cursorQuery;
        Cursor cursor;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    th = th;
                    cursorQuery = null;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } else {
                str = null;
            }
            cursorQuery = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursorQuery == null) {
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
                return null;
            }
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    while (cursorQuery.moveToNext()) {
                        o oVarA = a(cursorQuery);
                        if (oVarA != null) {
                            arrayList.add(oVarA);
                        } else {
                            try {
                                sb.append(" or _id").append(" = ").append(cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
                            } catch (Throwable th2) {
                                u.d("unknown id!", new Object[0]);
                            }
                        }
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        u.d("deleted %s illegle data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string.substring(4), null)));
                    }
                    if (cursorQuery != null && !cursorQuery.isClosed()) {
                        cursorQuery.close();
                    }
                    return arrayList;
                } catch (Throwable th3) {
                    th = th3;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor = cursorQuery;
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return null;
            }
        }
        return null;
    }

    public static void a(List<o> list) {
        SQLiteDatabase writableDatabase;
        if (list != null && list.size() != 0 && (writableDatabase = b.getWritableDatabase()) != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<o> it = list.iterator();
            while (it.hasNext()) {
                sb.append(" or _id").append(" = ").append(it.next().a);
            }
            String string = sb.toString();
            if (string.length() > 0) {
                string = string.substring(4);
            }
            sb.setLength(0);
            try {
                u.c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string, null)));
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void b(int i) {
        String str = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!u.a(th)) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            u.c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    private static ContentValues c(o oVar) {
        if (oVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (oVar.a > 0) {
                contentValues.put("_id", Long.valueOf(oVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(oVar.b));
            contentValues.put("_pc", oVar.c);
            contentValues.put("_th", oVar.d);
            contentValues.put("_tm", Long.valueOf(oVar.e));
            if (oVar.g != null) {
                contentValues.put("_dt", oVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static o a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            o oVar = new o();
            oVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            oVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            oVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            oVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            oVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            oVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return oVar;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private List<o> c(int i) throws Throwable {
        Cursor cursor;
        Cursor cursorQuery;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                cursorQuery = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (cursorQuery == null) {
                    if (cursorQuery != null && !cursorQuery.isClosed()) {
                        cursorQuery.close();
                    }
                    return null;
                }
                try {
                    try {
                        StringBuilder sb = new StringBuilder();
                        ArrayList arrayList = new ArrayList();
                        while (cursorQuery.moveToNext()) {
                            o oVarB = b(cursorQuery);
                            if (oVarB != null) {
                                arrayList.add(oVarB);
                            } else {
                                try {
                                    sb.append(" or _tp").append(" = ").append(cursorQuery.getString(cursorQuery.getColumnIndex("_tp")));
                                } catch (Throwable th) {
                                    u.d("unknown id!", new Object[0]);
                                }
                            }
                        }
                        if (sb.length() > 0) {
                            sb.append(" and _id").append(" = ").append(i);
                            u.d("deleted %s illegle data %d", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), null)));
                        }
                        if (cursorQuery != null && !cursorQuery.isClosed()) {
                            cursorQuery.close();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursorQuery != null && !cursorQuery.isClosed()) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor = cursorQuery;
                    try {
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return null;
                    } catch (Throwable th4) {
                        th = th4;
                        cursorQuery = cursor;
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
        }
        return null;
    }

    public static boolean a(int i, String str, l lVar) {
        String str2;
        boolean z = false;
        try {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    if (str == null || str.trim().length() <= 0) {
                        str2 = "_id = " + i;
                    } else {
                        str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                    }
                    int iDelete = writableDatabase.delete("t_pf", str2, null);
                    u.c("deleted %s data %d", "t_pf", Integer.valueOf(iDelete));
                    z = iDelete > 0;
                }
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
            return z;
        } finally {
            if (lVar != null) {
                Boolean.valueOf(false);
            }
        }
    }

    private static ContentValues d(o oVar) {
        if (oVar != null) {
            String str = oVar.f;
            if (!(str == null || str.trim().length() <= 0)) {
                try {
                    ContentValues contentValues = new ContentValues();
                    if (oVar.a > 0) {
                        contentValues.put("_id", Long.valueOf(oVar.a));
                    }
                    contentValues.put("_tp", oVar.f);
                    contentValues.put("_tm", Long.valueOf(oVar.e));
                    if (oVar.g != null) {
                        contentValues.put("_dt", oVar.g);
                        return contentValues;
                    }
                    return contentValues;
                } catch (Throwable th) {
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                    return null;
                }
            }
        }
        return null;
    }

    private static o b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            o oVar = new o();
            oVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            oVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            oVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            oVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return oVar;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class a extends Thread {
        private int a;
        private l b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i, l lVar) {
            this.a = i;
            this.b = lVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.a) {
                case 1:
                    m.this.a(this.c, this.d, this.b);
                    break;
                case 2:
                    m.this.a(this.c, this.m, this.n, this.b);
                    break;
                case 3:
                    m.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    break;
                case 4:
                    m.this.a(this.o, this.p, this.q, this.b);
                    break;
                case 5:
                    m.this.a(this.o, this.b);
                    break;
                case 6:
                    m mVar = m.this;
                    m.a(this.o, this.p, this.b);
                    break;
            }
        }
    }
}

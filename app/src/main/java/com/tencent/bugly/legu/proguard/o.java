package com.tencent.bugly.legu.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class o {
    private static o a = null;
    private static p b = null;
    private static boolean c = false;

    private o(Context context, List<com.tencent.bugly.legu.a> list) {
        b = new p(context, list);
    }

    public static synchronized o a(Context context, List<com.tencent.bugly.legu.a> list) {
        if (a == null) {
            a = new o(context, list);
        }
        return a;
    }

    public static synchronized o a() {
        return a;
    }

    public final long a(String str, ContentValues contentValues, n nVar, boolean z) {
        return a(str, contentValues, (n) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, n nVar, boolean z) {
        return a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    public final int a(String str, String str2, String[] strArr, n nVar, boolean z) {
        return a(str, str2, (String[]) null, (n) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized long a(String str, ContentValues contentValues, n nVar) {
        long j = 0;
        synchronized (this) {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null && contentValues != null) {
                        long jReplace = writableDatabase.replace(str, "_id", contentValues);
                        if (jReplace >= 0) {
                            w.c("[db] insert %s success", str);
                        } else {
                            w.d("[db] replace %s error", str);
                        }
                        j = jReplace;
                    }
                    if (nVar != null) {
                        Long.valueOf(j);
                    }
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    if (nVar != null) {
                        Long.valueOf(0L);
                    }
                }
            } catch (Throwable th2) {
                if (nVar != null) {
                    Long.valueOf(0L);
                }
                throw th2;
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, n nVar) {
        Cursor cursorQuery;
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            cursorQuery = writableDatabase != null ? writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6) : null;
            if (nVar != null) {
            }
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
            cursorQuery = nVar != null ? null : null;
        }
        return cursorQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int a(String str, String str2, String[] strArr, n nVar) {
        int iDelete = 0;
        synchronized (this) {
            try {
                try {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    iDelete = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
                    if (nVar != null) {
                        Integer.valueOf(iDelete);
                    }
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    if (nVar != null) {
                        Integer.valueOf(0);
                    }
                }
            } catch (Throwable th2) {
                if (nVar != null) {
                    Integer.valueOf(0);
                }
                throw th2;
            }
        }
        return iDelete;
    }

    public final boolean a(int i, String str, byte[] bArr, n nVar, boolean z) {
        if (z) {
            return a(i, str, bArr, (n) null);
        }
        a aVar = new a(4, null);
        aVar.a(i, str, bArr);
        v.a().b(aVar);
        return true;
    }

    public final Map<String, byte[]> a(int i, n nVar, boolean z) {
        return a(i, (n) null);
    }

    public final boolean a(int i, String str, n nVar, boolean z) {
        return a(555, str, (n) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, n nVar) {
        boolean zB = false;
        try {
            q qVar = new q();
            qVar.a = i;
            qVar.f = str;
            qVar.e = System.currentTimeMillis();
            qVar.g = bArr;
            zB = b(qVar);
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        } finally {
            if (nVar != null) {
                Boolean.valueOf(false);
            }
        }
        return zB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, byte[]> a(int i, n nVar) {
        Throwable th;
        HashMap map;
        try {
            List<q> listC = c(i);
            HashMap map2 = new HashMap();
            try {
                for (q qVar : listC) {
                    byte[] bArr = qVar.g;
                    if (bArr != null) {
                        map2.put(qVar.f, bArr);
                    }
                }
                return nVar != null ? map2 : map2;
            } catch (Throwable th2) {
                map = map2;
                th = th2;
                if (!w.a(th)) {
                    th.printStackTrace();
                }
                if (nVar != null) {
                }
                return map;
            }
        } catch (Throwable th3) {
            th = th3;
            map = null;
        }
    }

    public final boolean a(q qVar) {
        ContentValues contentValuesC;
        if (qVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesC = c(qVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_lr", "_id", contentValuesC);
            if (jReplace < 0) {
                return false;
            }
            w.c("insert %s success!", "t_lr");
            qVar.a = jReplace;
            return true;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
        throw th;
    }

    private boolean b(q qVar) {
        ContentValues contentValuesD;
        if (qVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesD = d(qVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_pf", "_id", contentValuesD);
            if (jReplace < 0) {
                return false;
            }
            w.c("insert %s success!", "t_pf");
            qVar.a = jReplace;
            return true;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
        throw th;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0058 A[Catch: all -> 0x00d4, TRY_LEAVE, TryCatch #1 {all -> 0x00d4, blocks: (B:24:0x0052, B:26:0x0058), top: B:57:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x008f  */
    public final List<q> a(int i) {
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
                        q qVarA = a(cursorQuery);
                        if (qVarA != null) {
                            arrayList.add(qVarA);
                        } else {
                            try {
                                sb.append(" or _id").append(" = ").append(cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
                            } catch (Throwable th2) {
                                w.d("unknown id!", new Object[0]);
                            }
                        }
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        w.d("deleted %s illegle data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string.substring(4), null)));
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
                if (!w.a(th)) {
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

    public static void a(List<q> list) {
        SQLiteDatabase writableDatabase;
        if (list != null && list.size() != 0 && (writableDatabase = b.getWritableDatabase()) != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<q> it = list.iterator();
            while (it.hasNext()) {
                sb.append(" or _id").append(" = ").append(it.next().a);
            }
            String string = sb.toString();
            if (string.length() > 0) {
                string = string.substring(4);
            }
            sb.setLength(0);
            try {
                w.c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string, null)));
            } catch (Throwable th) {
                if (!w.a(th)) {
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
                    if (!w.a(th)) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            w.c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    private static ContentValues c(q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (qVar.a > 0) {
                contentValues.put("_id", Long.valueOf(qVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(qVar.b));
            contentValues.put("_pc", qVar.c);
            contentValues.put("_th", qVar.d);
            contentValues.put("_tm", Long.valueOf(qVar.e));
            if (qVar.g != null) {
                contentValues.put("_dt", qVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static q a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            q qVar = new q();
            qVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            qVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            qVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            qVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private List<q> c(int i) throws Throwable {
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
                            q qVarB = b(cursorQuery);
                            if (qVarB != null) {
                                arrayList.add(qVarB);
                            } else {
                                try {
                                    sb.append(" or _tp").append(" = ").append(cursorQuery.getString(cursorQuery.getColumnIndex("_tp")));
                                } catch (Throwable th) {
                                    w.d("unknown id!", new Object[0]);
                                }
                            }
                        }
                        if (sb.length() > 0) {
                            sb.append(" and _id").append(" = ").append(i);
                            w.d("deleted %s illegle data %d", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), null)));
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
                        if (!w.a(th)) {
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

    public static boolean a(int i, String str, n nVar) {
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
                    w.c("deleted %s data %d", "t_pf", Integer.valueOf(iDelete));
                    z = iDelete > 0;
                }
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
            return z;
        } finally {
            if (nVar != null) {
                Boolean.valueOf(false);
            }
        }
    }

    private static ContentValues d(q qVar) {
        if (qVar != null) {
            String str = qVar.f;
            if (!(str == null || str.trim().length() <= 0)) {
                try {
                    ContentValues contentValues = new ContentValues();
                    if (qVar.a > 0) {
                        contentValues.put("_id", Long.valueOf(qVar.a));
                    }
                    contentValues.put("_tp", qVar.f);
                    contentValues.put("_tm", Long.valueOf(qVar.e));
                    if (qVar.g != null) {
                        contentValues.put("_dt", qVar.g);
                        return contentValues;
                    }
                    return contentValues;
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    return null;
                }
            }
        }
        return null;
    }

    private static q b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            q qVar = new q();
            qVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            qVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class a extends Thread {
        private int a;
        private n b;
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

        public a(int i, n nVar) {
            this.a = i;
            this.b = nVar;
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

        public final void a(int i, String str, byte[] bArr) {
            this.o = i;
            this.p = str;
            this.q = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.a) {
                case 1:
                    o.this.a(this.c, this.d, this.b);
                    break;
                case 2:
                    o.this.a(this.c, this.m, this.n, this.b);
                    break;
                case 3:
                    o.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    break;
                case 4:
                    o.this.a(this.o, this.p, this.q, this.b);
                    break;
                case 5:
                    o.this.a(this.o, this.b);
                    break;
                case 6:
                    o oVar = o.this;
                    o.a(this.o, this.p, this.b);
                    break;
            }
        }
    }

    public final boolean a(l lVar) {
        ContentValues contentValuesB;
        if (lVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesB = b(lVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_crd", "_id", contentValuesB);
            if (jReplace < 0) {
                return false;
            }
            w.c("insert %s success!", "t_crd");
            lVar.a = jReplace;
            return true;
        } catch (Throwable th) {
            if (w.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
        throw th;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00c1: MOVE (r8 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:44:0x00c1 */
    public final List<l> a(int i, String str, long j, String str2) throws Throwable {
        Cursor cursor;
        Cursor cursorQuery;
        Cursor cursor2 = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        try {
            if (writableDatabase != null) {
                try {
                    cursorQuery = writableDatabase.query("t_crd", null, "_id = " + i + " and _pc = '" + str + "' and _fl > 0 and _sv = '" + str2 + "' " + (j != 0 ? " and _tm > " + j : ""), null, null, null, "_tm ASC");
                    if (cursorQuery == null) {
                        if (cursorQuery != null && !cursorQuery.isClosed()) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    try {
                        ArrayList arrayList = new ArrayList();
                        while (cursorQuery.moveToNext()) {
                            l lVarC = c(cursorQuery);
                            if (lVarC != null) {
                                arrayList.add(lVarC);
                            }
                        }
                        if (cursorQuery == null || cursorQuery.isClosed()) {
                            return arrayList;
                        }
                        cursorQuery.close();
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        if (!w.a(th)) {
                            th.printStackTrace();
                        }
                        if (cursorQuery != null && !cursorQuery.isClosed()) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0090  */
    public final l a(int i, String str) throws Throwable {
        Cursor cursorQuery;
        l lVarC;
        Cursor cursor = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase == null) {
            return null;
        }
        try {
            cursorQuery = writableDatabase.query("t_crd", null, "_id = " + i + " and _pc = '" + str + "' and _fl > 0", null, null, null, "_tm DESC");
            if (cursorQuery == null) {
                if (cursorQuery == null || cursorQuery.isClosed()) {
                    return null;
                }
                cursorQuery.close();
                return null;
            }
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            lVarC = c(cursorQuery);
                        } else {
                            lVarC = null;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursorQuery == null || cursorQuery.isClosed()) {
                        return null;
                    }
                    cursorQuery.close();
                    return null;
                }
            } else {
                lVarC = null;
            }
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
            return lVarC;
        } catch (Throwable th3) {
            th = th3;
            cursorQuery = null;
        }
    }

    public final int a(int i, String str, long j) {
        return a("t_crd", "_id = " + i + " and _pc = '" + str + "' " + (j == 0 ? "" : " and _tm < " + j), (String[]) null, (n) null);
    }

    public final int b() {
        StringBuilder sb = new StringBuilder("_fl = 0 or _sv <> '");
        com.tencent.bugly.legu.crashreport.common.info.a.a().getClass();
        return a("t_crd", sb.append("2.1.9'").toString(), (String[]) null, (n) null);
    }

    private static ContentValues b(l lVar) {
        if (lVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (lVar.a <= 0 || TextUtils.isEmpty(lVar.b)) {
                return null;
            }
            contentValues.put("_id", Long.valueOf(lVar.a));
            contentValues.put("_pc", lVar.b);
            contentValues.put("_tm", Long.valueOf(lVar.c));
            contentValues.put("_fl", Integer.valueOf(lVar.d));
            contentValues.put("_av", lVar.f);
            contentValues.put("_sv", lVar.e);
            contentValues.put("_uid", Long.valueOf(lVar.g));
            return contentValues;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static l c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            l lVar = new l();
            lVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            lVar.b = cursor.getString(cursor.getColumnIndex("_pc"));
            lVar.e = cursor.getString(cursor.getColumnIndex("_sv"));
            lVar.f = cursor.getString(cursor.getColumnIndex("_av"));
            lVar.c = cursor.getLong(cursor.getColumnIndex("_tm"));
            lVar.d = cursor.getInt(cursor.getColumnIndex("_fl"));
            lVar.g = cursor.getLong(cursor.getColumnIndex("_uid"));
            return lVar;
        } catch (Throwable th) {
            if (w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}

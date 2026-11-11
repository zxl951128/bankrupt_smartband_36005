package com.tencent.bugly.lejiagu.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.lejiagu.BuglyStrategy;
import com.tencent.bugly.lejiagu.crashreport.common.info.PlugInBean;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.proguard.af;
import com.tencent.bugly.lejiagu.proguard.ah;
import com.tencent.bugly.lejiagu.proguard.ai;
import com.tencent.bugly.lejiagu.proguard.aj;
import com.tencent.bugly.lejiagu.proguard.ak;
import com.tencent.bugly.lejiagu.proguard.l;
import com.tencent.bugly.lejiagu.proguard.m;
import com.tencent.bugly.lejiagu.proguard.o;
import com.tencent.bugly.lejiagu.proguard.q;
import com.tencent.bugly.lejiagu.proguard.r;
import com.tencent.bugly.lejiagu.proguard.u;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    private static int a = 0;
    private Context b;
    private m c;
    private com.tencent.bugly.lejiagu.crashreport.common.strategy.a d;
    private l e;
    private BuglyStrategy.a f;

    public b(int i, Context context, r rVar, m mVar, com.tencent.bugly.lejiagu.crashreport.common.strategy.a aVar, BuglyStrategy.a aVar2, l lVar) {
        a = i;
        this.b = context;
        this.c = mVar;
        this.d = aVar;
        this.f = aVar2;
        this.e = lVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long jO = com.tencent.bugly.lejiagu.proguard.a.o();
        ArrayList arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b < jO - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3;
        List<CrashDetailBean> listB;
        String[] strArrSplit;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean4 = null;
        ArrayList arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() <= 0 || (listB = b(arrayList)) == null || listB.size() <= 0) {
            crashDetailBean2 = null;
        } else {
            Collections.sort(listB);
            int i = 0;
            while (i < listB.size()) {
                CrashDetailBean crashDetailBean5 = listB.get(i);
                if (i != 0) {
                    if (crashDetailBean5.s != null && (strArrSplit = crashDetailBean5.s.split("\n")) != null) {
                        for (String str : strArrSplit) {
                            if (!crashDetailBean4.s.contains(str)) {
                                crashDetailBean4.t++;
                                crashDetailBean4.s += str + "\n";
                            }
                        }
                    }
                    crashDetailBean5 = crashDetailBean4;
                }
                i++;
                crashDetailBean4 = crashDetailBean5;
            }
            crashDetailBean2 = crashDetailBean4;
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean3 = crashDetailBean;
        } else {
            crashDetailBean3 = crashDetailBean2;
        }
        for (a aVar2 : list) {
            if (!aVar2.e && !aVar2.d && !crashDetailBean3.s.contains(new StringBuilder().append(aVar2.b).toString())) {
                crashDetailBean3.t++;
                crashDetailBean3.s += aVar2.b + "\n";
            }
        }
        if (crashDetailBean3.r != crashDetailBean.r && !crashDetailBean3.s.contains(new StringBuilder().append(crashDetailBean.r).toString())) {
            crashDetailBean3.t++;
            crashDetailBean3.s += crashDetailBean.r + "\n";
            return crashDetailBean3;
        }
        return crashDetailBean3;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return a(crashDetailBean, -123456789);
    }

    public final boolean a(CrashDetailBean crashDetailBean, int i) throws Throwable {
        int i2 = crashDetailBean.b;
        String str = crashDetailBean.n;
        String str2 = crashDetailBean.p;
        String str3 = crashDetailBean.q;
        long j = crashDetailBean.r;
        String str4 = crashDetailBean.m;
        String str5 = crashDetailBean.e;
        String str6 = crashDetailBean.c;
        if (this.e != null && !this.e.c()) {
            return true;
        }
        if (crashDetailBean.b != 2) {
            o oVar = new o();
            oVar.b = 1;
            oVar.c = crashDetailBean.z;
            oVar.d = crashDetailBean.A;
            oVar.e = crashDetailBean.r;
            m mVar = this.c;
            m.b(1);
            this.c.a(oVar);
            u.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            u.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> listB = b();
        ArrayList arrayList = null;
        if (listB != null && listB.size() > 0) {
            ArrayList arrayList2 = new ArrayList(10);
            ArrayList arrayList3 = new ArrayList(10);
            arrayList2.addAll(a(listB));
            listB.removeAll(arrayList2);
            if (!com.tencent.bugly.lejiagu.b.a && c.c) {
                boolean z = false;
                for (a aVar : listB) {
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            z = true;
                        }
                        arrayList3.add(aVar);
                    }
                    z = z;
                }
                if (z || arrayList3.size() + 1 >= 5) {
                    u.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean crashDetailBeanA = a(arrayList3, crashDetailBean);
                    crashDetailBeanA.a = -1L;
                    c(crashDetailBeanA);
                    arrayList2.addAll(arrayList3);
                    c(arrayList2);
                    u.b("[crash] save crash success. this device crash many times, won't upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            arrayList = arrayList2;
        }
        c(crashDetailBean);
        c(arrayList);
        u.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final List<CrashDetailBean> a() throws Throwable {
        StrategyBean strategyBeanC = com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a().c();
        if (strategyBeanC == null) {
            u.d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!strategyBeanC.d) {
            u.d("Crashreport remote closed, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            u.b("[init] WARNING! Crashreport closed by server, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jO = com.tencent.bugly.lejiagu.proguard.a.o();
        List<a> listB = b();
        if (listB == null || listB.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<a> it = listB.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.b < jO - c.f) {
                it.remove();
                arrayList.add(next);
            } else if (next.d) {
                if (next.b >= jCurrentTimeMillis - 86400000) {
                    it.remove();
                } else if (!next.e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f >= 3 && next.b < jCurrentTimeMillis - 86400000) {
                it.remove();
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            c(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        List<CrashDetailBean> listB2 = b(listB);
        if (listB2 != null && listB2.size() > 0) {
            String str = com.tencent.bugly.lejiagu.crashreport.common.info.a.a().i;
            Iterator<CrashDetailBean> it2 = listB2.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f)) {
                    it2.remove();
                    arrayList2.add(next2);
                }
            }
        }
        if (arrayList2.size() > 0) {
            d(arrayList2);
        }
        return listB2;
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        u.a("try to upload right now", new Object[0]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(crashDetailBean);
        a(arrayList, 5000L, z);
        int i = crashDetailBean.b;
        if (this.e != null) {
            l lVar = this.e;
        }
    }

    public final void a(final List<CrashDetailBean> list, long j, boolean z) {
        aj ajVar = null;
        if (!this.d.c().d) {
            u.d("remote report is disable!", new Object[0]);
            u.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            return;
        }
        if (list != null && list.size() != 0) {
            try {
                Context context = this.b;
                com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a();
                if (context == null || list == null || list.size() == 0 || aVarA == null) {
                    u.d("enEXPPkg args == null!", new Object[0]);
                } else {
                    aj ajVar2 = new aj();
                    ajVar2.a = new ArrayList<>();
                    Iterator<CrashDetailBean> it = list.iterator();
                    while (it.hasNext()) {
                        ajVar2.a.add(a(context, it.next(), aVarA));
                    }
                    ajVar = ajVar2;
                }
                if (ajVar == null) {
                    u.d("create eupPkg fail!", new Object[0]);
                    return;
                }
                byte[] bArrA = com.tencent.bugly.lejiagu.proguard.a.a(ajVar);
                if (bArrA == null) {
                    u.d("send encode fail!", new Object[0]);
                    return;
                }
                ak akVarA = com.tencent.bugly.lejiagu.proguard.a.a(this.b, 630, bArrA);
                if (akVarA == null) {
                    u.d("request package is null.", new Object[0]);
                    return;
                }
                q qVar = new q() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.b.1
                    @Override // com.tencent.bugly.lejiagu.proguard.q
                    public final void a(boolean z2) {
                        b bVar = b.this;
                        b.a(z2, (List<CrashDetailBean>) list);
                    }
                };
                if (!z) {
                    r.a().a(a, akVarA, null, qVar);
                } else {
                    r.a().a(a, akVarA, null, qVar, true, j);
                    u.a("wake up!", new Object[0]);
                }
            } catch (Throwable th) {
                u.e("req cr error %s", th.toString());
                if (!u.b(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            u.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                u.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.d = z;
                u.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                c.a().a(it.next());
            }
            u.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            u.b("[crash] upload fail.", new Object[0]);
        }
    }

    public final void b(CrashDetailBean crashDetailBean) {
        int i;
        String strSubstring;
        if (crashDetailBean != null) {
            if (this.f != null || this.e != null) {
                try {
                    u.a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
                    switch (crashDetailBean.b) {
                        case 0:
                            i = 0;
                            break;
                        case 1:
                            i = 2;
                            break;
                        case 2:
                            i = 1;
                            break;
                        case 3:
                            i = 4;
                            break;
                        case 4:
                            i = 3;
                            break;
                        case 5:
                            i = 5;
                            break;
                        case 6:
                            i = 6;
                            break;
                        default:
                            return;
                    }
                    int i2 = crashDetailBean.b;
                    String str = crashDetailBean.n;
                    String str2 = crashDetailBean.p;
                    String str3 = crashDetailBean.q;
                    long j = crashDetailBean.r;
                    Map<String, String> mapOnCrashHandleStart = null;
                    if (this.e != null) {
                        l lVar = this.e;
                        String strB = this.e.b();
                        if (strB != null) {
                            mapOnCrashHandleStart = new HashMap<>(1);
                            mapOnCrashHandleStart.put("userData", strB);
                        }
                    } else if (this.f != null) {
                        mapOnCrashHandleStart = this.f.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    if (mapOnCrashHandleStart != null && mapOnCrashHandleStart.size() > 0) {
                        crashDetailBean.N = new LinkedHashMap(mapOnCrashHandleStart.size());
                        for (Map.Entry<String, String> entry : mapOnCrashHandleStart.entrySet()) {
                            String key = entry.getKey();
                            if (!(key == null || key.trim().length() <= 0)) {
                                String key2 = entry.getKey();
                                if (key2.length() > 100) {
                                    key2 = key2.substring(0, 100);
                                    u.d("setted key length is over limit %d substring to %s", 100, key2);
                                }
                                String str4 = key2;
                                String value = entry.getValue();
                                if (!(value == null || value.trim().length() <= 0) && entry.getValue().length() > 30000) {
                                    strSubstring = entry.getValue().substring(entry.getValue().length() - 30000);
                                    u.d("setted %s value length is over limit %d substring", str4, 30000);
                                } else {
                                    strSubstring = entry.getValue();
                                }
                                crashDetailBean.N.put(str4, strSubstring);
                                u.a("add setted key %s value size:%d", str4, Integer.valueOf(strSubstring.length()));
                            }
                        }
                    }
                    u.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    byte[] bArrOnCrashHandleStart2GetExtraDatas = null;
                    if (this.e != null) {
                        bArrOnCrashHandleStart2GetExtraDatas = this.e.a();
                    } else if (this.f != null) {
                        bArrOnCrashHandleStart2GetExtraDatas = this.f.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    crashDetailBean.S = bArrOnCrashHandleStart2GetExtraDatas;
                    if (crashDetailBean.S != null) {
                        if (crashDetailBean.S.length > 30000) {
                            u.d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.S.length), 30000);
                            byte[] bArr = new byte[30000];
                            for (int i3 = 0; i3 < 30000; i3++) {
                                bArr[i3] = crashDetailBean.S[i3];
                            }
                        }
                        u.a("add extra bytes %d ", Integer.valueOf(crashDetailBean.S.length));
                    }
                } catch (Throwable th) {
                    u.d("crash handle callback somthing wrong! %s", th.getClass().getName());
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    private static ContentValues d(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            contentValues.put("_up", Integer.valueOf(crashDetailBean.d ? 1 : 0));
            contentValues.put("_me", Integer.valueOf(crashDetailBean.j ? 1 : 0));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            Parcel parcelObtain = Parcel.obtain();
            crashDetailBean.writeToParcel(parcelObtain, 0);
            byte[] bArrMarshall = parcelObtain.marshall();
            parcelObtain.recycle();
            contentValues.put("_dt", bArrMarshall);
            return contentValues;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) com.tencent.bugly.lejiagu.proguard.a.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.a = j;
                return crashDetailBean;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void c(CrashDetailBean crashDetailBean) {
        ContentValues contentValuesD;
        if (crashDetailBean != null && (contentValuesD = d(crashDetailBean)) != null) {
            long jA = m.a().a("t_cr", contentValuesD, (l) null, true);
            if (jA >= 0) {
                u.c("insert %s success!", "t_cr");
                crashDetailBean.a = jA;
            }
        }
    }

    private List<CrashDetailBean> b(List<a> list) throws Throwable {
        Cursor cursor;
        Cursor cursorA;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(" or _id").append(" = ").append(it.next().a);
        }
        String string = sb.toString();
        if (string.length() > 0) {
            string = string.substring(4);
        }
        sb.setLength(0);
        try {
            cursorA = m.a().a("t_cr", null, string, null, null, true);
            if (cursorA == null) {
                if (cursorA != null && !cursorA.isClosed()) {
                    cursorA.close();
                }
                return null;
            }
            try {
                try {
                    ArrayList arrayList = new ArrayList();
                    while (cursorA.moveToNext()) {
                        CrashDetailBean crashDetailBeanA = a(cursorA);
                        if (crashDetailBeanA != null) {
                            arrayList.add(crashDetailBeanA);
                        } else {
                            try {
                                sb.append(" or _id").append(" = ").append(cursorA.getLong(cursorA.getColumnIndex("_id")));
                            } catch (Throwable th) {
                                u.d("unknown id!", new Object[0]);
                            }
                        }
                    }
                    String string2 = sb.toString();
                    if (string2.length() > 0) {
                        u.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(m.a().a("t_cr", string2.substring(4), (String[]) null, (l) null, true)));
                    }
                    if (cursorA != null && !cursorA.isClosed()) {
                        cursorA.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorA;
                    try {
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        cursorA = cursor;
                        if (cursorA != null && !cursorA.isClosed()) {
                            cursorA.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                if (cursorA != null) {
                    cursorA.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
        }
    }

    private static a b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            aVar.e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private List<a> b() throws Throwable {
        Cursor cursorA;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        try {
            cursorA = m.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (cursorA == null) {
                if (cursorA != null && !cursorA.isClosed()) {
                    cursorA.close();
                }
                return null;
            }
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    while (cursorA.moveToNext()) {
                        a aVarB = b(cursorA);
                        if (aVarB != null) {
                            arrayList.add(aVarB);
                        } else {
                            try {
                                sb.append(" or _id").append(" = ").append(cursorA.getLong(cursorA.getColumnIndex("_id")));
                            } catch (Throwable th) {
                                u.d("unknown id!", new Object[0]);
                            }
                        }
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        u.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(m.a().a("t_cr", string.substring(4), (String[]) null, (l) null, true)));
                    }
                    if (cursorA != null && !cursorA.isClosed()) {
                        cursorA.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorA;
                    try {
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        th = th3;
                        cursorA = cursor;
                        if (cursorA != null && !cursorA.isClosed()) {
                            cursorA.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                if (cursorA != null) {
                    cursorA.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            cursorA = null;
        }
    }

    private static void c(List<a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<a> it = list.iterator();
            while (it.hasNext()) {
                sb.append(" or _id").append(" = ").append(it.next().a);
            }
            String string = sb.toString();
            if (string.length() > 0) {
                string = string.substring(4);
            }
            sb.setLength(0);
            try {
                u.c("deleted %s data %d", "t_cr", Integer.valueOf(m.a().a("t_cr", string, (String[]) null, (l) null, true)));
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    Iterator<CrashDetailBean> it = list.iterator();
                    while (it.hasNext()) {
                        sb.append(" or _id").append(" = ").append(it.next().a);
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        string = string.substring(4);
                    }
                    sb.setLength(0);
                    u.c("deleted %s data %d", "t_cr", Integer.valueOf(m.a().a("t_cr", string, (String[]) null, (l) null, true)));
                }
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ai a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.lejiagu.crashreport.common.info.a aVar) {
        ah ahVarA;
        ah ahVarA2;
        if (context == null || crashDetailBean == null || aVar == null) {
            u.d("enExp args == null", new Object[0]);
            return null;
        }
        ai aiVar = new ai();
        switch (crashDetailBean.b) {
            case 0:
                aiVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                aiVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case 2:
                aiVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case 3:
                aiVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                aiVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                aiVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case 6:
                aiVar.a = crashDetailBean.j ? "206" : "106";
                break;
            default:
                u.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        aiVar.b = crashDetailBean.r;
        aiVar.c = crashDetailBean.n;
        aiVar.d = crashDetailBean.o;
        aiVar.e = crashDetailBean.p;
        aiVar.g = crashDetailBean.q;
        aiVar.h = crashDetailBean.y;
        aiVar.i = crashDetailBean.c;
        aiVar.j = null;
        aiVar.l = crashDetailBean.m;
        aiVar.m = crashDetailBean.e;
        aiVar.f = crashDetailBean.A;
        aiVar.t = com.tencent.bugly.lejiagu.crashreport.common.info.a.a().h();
        aiVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            aiVar.o = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.i.entrySet()) {
                af afVar = new af();
                afVar.a = entry.getValue().a;
                afVar.c = entry.getValue().c;
                afVar.d = entry.getValue().b;
                afVar.b = aVar.q();
                aiVar.o.add(afVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            aiVar.p = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry2 : crashDetailBean.h.entrySet()) {
                af afVar2 = new af();
                afVar2.a = entry2.getValue().a;
                afVar2.c = entry2.getValue().c;
                afVar2.d = entry2.getValue().b;
                aiVar.p.add(afVar2);
            }
        }
        if (crashDetailBean.j) {
            aiVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (aiVar.q == null) {
                    aiVar.q = new ArrayList<>();
                }
                try {
                    aiVar.q.add(new ah((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    aiVar.q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(aiVar.k);
            objArr[1] = Integer.valueOf(aiVar.q != null ? aiVar.q.size() : 0);
            u.c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.w != null) {
            if (aiVar.q == null) {
                aiVar.q = new ArrayList<>();
            }
            try {
                aiVar.q.add(new ah((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                aiVar.q = null;
            }
        }
        if (crashDetailBean.x != null && crashDetailBean.x.length > 0) {
            ah ahVar = new ah((byte) 2, "buglylog.zip", crashDetailBean.x);
            u.c("attach user log", new Object[0]);
            if (aiVar.q == null) {
                aiVar.q = new ArrayList<>();
            }
            aiVar.q.add(ahVar);
        }
        if (crashDetailBean.b == 3) {
            if (aiVar.q == null) {
                aiVar.q = new ArrayList<>();
            }
            if (crashDetailBean.N != null && crashDetailBean.N.containsKey("BUGLY_CR_01")) {
                try {
                    aiVar.q.add(new ah((byte) 1, "anrMessage.txt", crashDetailBean.N.get("BUGLY_CR_01").getBytes("utf-8")));
                    u.c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                    aiVar.q = null;
                }
                crashDetailBean.N.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null && (ahVarA2 = a("trace.zip", context, crashDetailBean.v)) != null) {
                u.c("attach traces", new Object[0]);
                aiVar.q.add(ahVarA2);
            }
        }
        if (crashDetailBean.b == 1) {
            if (aiVar.q == null) {
                aiVar.q = new ArrayList<>();
            }
            if (crashDetailBean.v != null && (ahVarA = a("tomb.zip", context, crashDetailBean.v)) != null) {
                u.c("attach tombs", new Object[0]);
                aiVar.q.add(ahVarA);
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.length > 0) {
            if (aiVar.q == null) {
                aiVar.q = new ArrayList<>();
            }
            aiVar.q.add(new ah((byte) 1, "userExtraByteData", crashDetailBean.S));
            u.c("attach extraData", new Object[0]);
        }
        aiVar.r = new HashMap();
        aiVar.r.put("A9", new StringBuilder().append(crashDetailBean.B).toString());
        aiVar.r.put("A11", new StringBuilder().append(crashDetailBean.C).toString());
        aiVar.r.put("A10", new StringBuilder().append(crashDetailBean.D).toString());
        aiVar.r.put("A23", crashDetailBean.f);
        aiVar.r.put("A7", aVar.e);
        aiVar.r.put("A6", aVar.r());
        aiVar.r.put("A5", aVar.q());
        aiVar.r.put("A22", aVar.g());
        aiVar.r.put("A2", new StringBuilder().append(crashDetailBean.F).toString());
        aiVar.r.put("A1", new StringBuilder().append(crashDetailBean.E).toString());
        aiVar.r.put("A24", aVar.g);
        aiVar.r.put("A17", new StringBuilder().append(crashDetailBean.G).toString());
        aiVar.r.put("A3", aVar.j());
        aiVar.r.put("A16", aVar.l());
        aiVar.r.put("A25", aVar.m());
        aiVar.r.put("A14", aVar.k());
        aiVar.r.put("A15", aVar.t());
        aiVar.r.put("A13", new StringBuilder().append(aVar.u()).toString());
        aiVar.r.put("A34", crashDetailBean.z);
        if (aVar.w != null) {
            aiVar.r.put("productIdentify", aVar.w);
        }
        try {
            aiVar.r.put("A26", URLEncoder.encode(crashDetailBean.H, "utf-8"));
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            aiVar.r.put("A27", crashDetailBean.J);
            aiVar.r.put("A28", crashDetailBean.I);
            aiVar.r.put("A29", new StringBuilder().append(crashDetailBean.k).toString());
        }
        aiVar.r.put("A30", crashDetailBean.K);
        aiVar.r.put("A18", new StringBuilder().append(crashDetailBean.L).toString());
        aiVar.r.put("A36", new StringBuilder().append(!crashDetailBean.M).toString());
        aiVar.r.put("F02", new StringBuilder().append(aVar.p).toString());
        aiVar.r.put("F03", new StringBuilder().append(aVar.q).toString());
        aiVar.r.put("F04", aVar.d());
        aiVar.r.put("F05", new StringBuilder().append(aVar.r).toString());
        aiVar.r.put("F06", aVar.o);
        aiVar.r.put("F08", aVar.u);
        aiVar.r.put("F09", aVar.v);
        aiVar.r.put("F10", new StringBuilder().append(aVar.s).toString());
        if (crashDetailBean.O >= 0) {
            aiVar.r.put("C01", new StringBuilder().append(crashDetailBean.O).toString());
        }
        if (crashDetailBean.P >= 0) {
            aiVar.r.put("C02", new StringBuilder().append(crashDetailBean.P).toString());
        }
        if (crashDetailBean.Q != null && crashDetailBean.Q.size() > 0) {
            for (Map.Entry<String, String> entry3 : crashDetailBean.Q.entrySet()) {
                aiVar.r.put("C03_" + entry3.getKey(), entry3.getValue());
            }
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Map.Entry<String, String> entry4 : crashDetailBean.R.entrySet()) {
                aiVar.r.put("C04_" + entry4.getKey(), entry4.getValue());
            }
        }
        aiVar.s = null;
        if (crashDetailBean.N != null && crashDetailBean.N.size() > 0) {
            aiVar.s = crashDetailBean.N;
            u.a("setted message size %d", Integer.valueOf(aiVar.s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.d();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        objArr2[7] = Boolean.valueOf(crashDetailBean.b == 1);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(aiVar.r.size());
        u.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return aiVar;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.FileInputStream] */
    private static ah a(String str, Context context, String str2) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        if (str2 == null || context == null) {
            u.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        u.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        ?? r2 = 5000;
        if (!com.tencent.bugly.lejiagu.proguard.a.a(file, file2, 5000)) {
            u.d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    byte[] bArr = new byte[1000];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                        byteArrayOutputStream.flush();
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    u.c("read bytes :%d", Integer.valueOf(byteArray.length));
                    ah ahVar = new ah((byte) 2, file2.getName(), byteArray);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        if (!u.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    if (file2.exists()) {
                        u.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    return ahVar;
                } catch (Throwable th2) {
                    th = th2;
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            if (!u.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    if (!file2.exists()) {
                        return null;
                    }
                    u.c("del tmp", new Object[0]);
                    file2.delete();
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            if (r2 != 0) {
                r2.close();
            }
            if (file2.exists()) {
                u.c("del tmp", new Object[0]);
                file2.delete();
            }
            throw th;
        }
    }

    public static void a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a();
        if (aVarA != null) {
            u.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            u.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            u.e("# PKG NAME: %s", aVarA.c);
            u.e("# APP VER: %s", aVarA.i);
            u.e("# LAUNCH TIME: %s", com.tencent.bugly.lejiagu.proguard.a.a(new Date(com.tencent.bugly.lejiagu.crashreport.common.info.a.a().a)));
            u.e("# CRASH TYPE: %s", str);
            u.e("# CRASH TIME: %s", str2);
            u.e("# CRASH PROCESS: %s", str3);
            if (thread != null) {
                u.e("# CRASH THREAD: %s", thread.getName());
            }
            if (crashDetailBean != null) {
                u.e("# REPORT ID: %s", crashDetailBean.c);
                Object[] objArr = new Object[2];
                objArr[0] = aVarA.f;
                objArr[1] = aVarA.u().booleanValue() ? "ROOTED" : "UNROOT";
                u.e("# CRASH DEVICE: %s %s", objArr);
                u.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.B), Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D));
                u.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.E), Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G));
                String str5 = crashDetailBean.J;
                if (!(str5 == null || str5.trim().length() <= 0)) {
                    u.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.J, crashDetailBean.I);
                } else if (crashDetailBean.b == 3) {
                    Object[] objArr2 = new Object[1];
                    objArr2[0] = crashDetailBean.N == null ? "null" : crashDetailBean.N.get("BUGLY_CR_01");
                    u.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
                }
            }
            if (!(str4 == null || str4.trim().length() <= 0)) {
                u.e("# CRASH STACK: ", new Object[0]);
                u.e(str4, new Object[0]);
            }
            u.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}

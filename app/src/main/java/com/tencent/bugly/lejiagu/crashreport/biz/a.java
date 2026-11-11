package com.tencent.bugly.lejiagu.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.proguard.ak;
import com.tencent.bugly.lejiagu.proguard.ap;
import com.tencent.bugly.lejiagu.proguard.l;
import com.tencent.bugly.lejiagu.proguard.m;
import com.tencent.bugly.lejiagu.proguard.q;
import com.tencent.bugly.lejiagu.proguard.r;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    private Context a;
    private long b;
    private int c;

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            long jA = m.a().a("t_ui", a(userInfoBean), (l) null, true);
            if (jA >= 0) {
                u.c("insert %s success! %d", "t_ui", Long.valueOf(jA));
                userInfoBean.a = jA;
            }
        }
    }

    static /* synthetic */ int b(a aVar) {
        int i = aVar.c;
        aVar.c = i + 1;
        return i;
    }

    public a(Context context) {
        this.a = context;
    }

    public final void a(int i, boolean z, long j) {
        StrategyBean strategyBeanC = com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a().c();
        if (strategyBeanC != null && !strategyBeanC.e && i != 1 && i != 3) {
            u.e("UserInfo is disable", new Object[0]);
            return;
        }
        if (i == 1) {
            this.c++;
        }
        Context context = this.a;
        int i2 = i != 1 ? 0 : 1;
        com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.b = i;
        userInfoBean.c = aVarA.d;
        userInfoBean.d = aVarA.f();
        userInfoBean.e = System.currentTimeMillis();
        userInfoBean.f = -1L;
        userInfoBean.n = aVarA.i;
        userInfoBean.o = i2;
        userInfoBean.l = aVarA.n;
        userInfoBean.m = aVarA.o;
        userInfoBean.g = aVarA.p;
        userInfoBean.h = aVarA.q;
        userInfoBean.i = aVarA.r;
        userInfoBean.k = aVarA.s;
        userInfoBean.r = aVarA.w();
        userInfoBean.s = aVarA.z();
        userInfoBean.p = aVarA.A();
        userInfoBean.q = aVarA.B();
        t.a().a(new RunnableC0001a(userInfoBean, z), 0L);
    }

    public final void a() {
        this.b = com.tencent.bugly.lejiagu.proguard.a.o() + 86400000;
        t.a().a(new b(), (this.b - System.currentTimeMillis()) + 5000);
    }

    /* JADX INFO: renamed from: com.tencent.bugly.lejiagu.crashreport.biz.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BUGLY */
    class RunnableC0001a implements Runnable {
        private boolean a;
        private UserInfoBean b;

        public RunnableC0001a(UserInfoBean userInfoBean, boolean z) {
            this.b = userInfoBean;
            this.a = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA;
            try {
                if (this.b != null) {
                    UserInfoBean userInfoBean = this.b;
                    if (userInfoBean != null && (aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a()) != null) {
                        userInfoBean.j = aVarA.d();
                    }
                    u.c("record userinfo", new Object[0]);
                    a.a(a.this, this.b);
                }
                if (this.a) {
                    a.this.b();
                }
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public final synchronized void b() {
        final List<UserInfoBean> arrayList;
        boolean z;
        boolean z2;
        synchronized (this) {
            String str = com.tencent.bugly.lejiagu.crashreport.common.info.a.a(this.a).d;
            ArrayList arrayList2 = new ArrayList();
            List<UserInfoBean> listA = a(str);
            if (listA != null) {
                int size = listA.size() - 10;
                if (size > 0) {
                    for (int i = 0; i < listA.size() - 1; i++) {
                        for (int i2 = i + 1; i2 < listA.size(); i2++) {
                            if (listA.get(i).e > listA.get(i2).e) {
                                UserInfoBean userInfoBean = listA.get(i);
                                listA.set(i, listA.get(i2));
                                listA.set(i2, userInfoBean);
                            }
                        }
                    }
                    for (int i3 = 0; i3 < size; i3++) {
                        arrayList2.add(listA.get(i3));
                    }
                }
                Iterator<UserInfoBean> it = listA.iterator();
                int i4 = 0;
                while (it.hasNext()) {
                    UserInfoBean next = it.next();
                    if (next.f != -1) {
                        it.remove();
                        if (next.e < com.tencent.bugly.lejiagu.proguard.a.o()) {
                            arrayList2.add(next);
                        }
                    }
                    i4 = (next.e <= System.currentTimeMillis() - 600000 || !(next.b == 1 || next.b == 4)) ? i4 : i4 + 1;
                }
                if (i4 > 15) {
                    u.d("[userinfo] userinfo too many times in 10 min: %d", Integer.valueOf(i4));
                    z2 = false;
                } else {
                    z2 = true;
                }
                z = z2;
                arrayList = listA;
            } else {
                arrayList = new ArrayList<>();
                z = true;
            }
            if (arrayList2.size() > 0) {
                a(arrayList2);
            }
            if (z && arrayList != null && arrayList.size() != 0) {
                u.c("[userinfo] do userinfo, size: %d", Integer.valueOf(arrayList.size()));
                ap apVarA = com.tencent.bugly.lejiagu.proguard.a.a(arrayList, this.c != 1 ? 2 : 1);
                if (apVarA == null) {
                    u.d("[biz] create uPkg fail!", new Object[0]);
                } else {
                    byte[] bArrA = com.tencent.bugly.lejiagu.proguard.a.a(apVarA);
                    if (bArrA == null) {
                        u.d("[biz] send encode fail!", new Object[0]);
                    } else {
                        ak akVarA = com.tencent.bugly.lejiagu.proguard.a.a(this.a, 640, bArrA);
                        if (akVarA == null) {
                            u.d("request package is null.", new Object[0]);
                        } else {
                            r.a().a(1001, akVarA, null, new q() { // from class: com.tencent.bugly.lejiagu.crashreport.biz.a.1
                                @Override // com.tencent.bugly.lejiagu.proguard.q
                                public final void a(boolean z3) {
                                    if (z3) {
                                        u.c("up success do final", new Object[0]);
                                        long jCurrentTimeMillis = System.currentTimeMillis();
                                        for (UserInfoBean userInfoBean2 : arrayList) {
                                            userInfoBean2.f = jCurrentTimeMillis;
                                            a.a(a.this, userInfoBean2);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis < a.this.b) {
                t.a().a(a.this.new b(), (a.this.b - jCurrentTimeMillis) + 5000);
                return;
            }
            a.b(a.this);
            a.this.a(3, false, 0L);
            a.this.a();
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0022  */
    /* JADX WARN: Code duplicated, block: B:16:0x002f  */
    /* JADX WARN: Code duplicated, block: B:17:0x0031 A[Catch: all -> 0x00e8, Throwable -> 0x00ee, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x00e8, Throwable -> 0x00ee, blocks: (B:4:0x0005, B:8:0x0012, B:17:0x0031), top: B:67:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0057 A[Catch: Throwable -> 0x0061, all -> 0x009d, TryCatch #1 {Throwable -> 0x0061, blocks: (B:19:0x0047, B:20:0x0051, B:22:0x0057, B:24:0x005d, B:39:0x0094, B:47:0x00aa, B:49:0x00b4), top: B:61:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0069 A[Catch: all -> 0x00eb, TRY_LEAVE, TryCatch #4 {all -> 0x00eb, blocks: (B:28:0x0063, B:30:0x0069), top: B:65:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00b4 A[Catch: Throwable -> 0x0061, all -> 0x009d, TRY_LEAVE, TryCatch #1 {Throwable -> 0x0061, blocks: (B:19:0x0047, B:20:0x0051, B:22:0x0057, B:24:0x005d, B:39:0x0094, B:47:0x00aa, B:49:0x00b4), top: B:61:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x005d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0011  */
    public final List<UserInfoBean> a(String str) {
        Cursor cursor;
        Cursor cursorA;
        boolean z;
        String str2;
        StringBuilder sb;
        ArrayList arrayList;
        String string;
        UserInfoBean userInfoBeanA;
        if (str == null) {
            if (z) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursorA = m.a().a("t_ui", null, str2, null, null, true);
            if (cursorA == null) {
                if (cursorA != null) {
                    cursorA.close();
                }
                return null;
            }
            sb = new StringBuilder();
            arrayList = new ArrayList();
            while (cursorA.moveToNext()) {
                userInfoBeanA = a(cursorA);
                if (userInfoBeanA != null) {
                    arrayList.add(userInfoBeanA);
                } else {
                    sb.append(" or _id").append(" = ").append(cursorA.getLong(cursorA.getColumnIndex("_id")));
                }
            }
            string = sb.toString();
            if (string.length() > 0) {
                u.d("[session] deleted %s error data %d", "t_ui", Integer.valueOf(m.a().a("t_ui", string.substring(4), (String[]) null, (l) null, true)));
            }
            if (cursorA != null) {
                cursorA.close();
            }
            return arrayList;
        }
        try {
            z = str.trim().length() <= 0;
            if (z) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursorA = m.a().a("t_ui", null, str2, null, null, true);
            if (cursorA == null) {
                if (cursorA != null && !cursorA.isClosed()) {
                    cursorA.close();
                }
                return null;
            }
            try {
                try {
                    sb = new StringBuilder();
                    arrayList = new ArrayList();
                    while (cursorA.moveToNext()) {
                        userInfoBeanA = a(cursorA);
                        if (userInfoBeanA != null) {
                            arrayList.add(userInfoBeanA);
                        } else {
                            try {
                                sb.append(" or _id").append(" = ").append(cursorA.getLong(cursorA.getColumnIndex("_id")));
                            } catch (Throwable th) {
                                u.d("unknown id!", new Object[0]);
                            }
                        }
                    }
                    string = sb.toString();
                    if (string.length() > 0) {
                        u.d("[session] deleted %s error data %d", "t_ui", Integer.valueOf(m.a().a("t_ui", string.substring(4), (String[]) null, (l) null, true)));
                    }
                    if (cursorA != null && !cursorA.isClosed()) {
                        cursorA.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = cursorA;
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
                    cursorA = cursor;
                }
            }
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
            if (!u.a(th)) {
                th.printStackTrace();
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
        if (cursorA != null && !cursorA.isClosed()) {
            cursorA.close();
        }
        throw th;
    }

    private static void a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<UserInfoBean> it = list.iterator();
            while (it.hasNext()) {
                sb.append(" or _id").append(" = ").append(it.next().a);
            }
            String string = sb.toString();
            if (string.length() > 0) {
                string = string.substring(4);
            }
            sb.setLength(0);
            try {
                u.c("deleted %s data %d", "t_ui", Integer.valueOf(m.a().a("t_ui", string, (String[]) null, (l) null, true)));
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            Parcel parcelObtain = Parcel.obtain();
            userInfoBean.writeToParcel(parcelObtain, 0);
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

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) com.tencent.bugly.lejiagu.proguard.a.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.a = j;
                return userInfoBean;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}

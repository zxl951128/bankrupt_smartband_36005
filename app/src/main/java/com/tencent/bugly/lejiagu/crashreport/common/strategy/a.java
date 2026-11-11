package com.tencent.bugly.lejiagu.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcel;
import com.tencent.bugly.lejiagu.crashreport.biz.b;
import com.tencent.bugly.lejiagu.proguard.an;
import com.tencent.bugly.lejiagu.proguard.l;
import com.tencent.bugly.lejiagu.proguard.m;
import com.tencent.bugly.lejiagu.proguard.o;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static int a = 1000;
    private static a b = null;
    private final List<com.tencent.bugly.lejiagu.a> c;
    private Context g;
    private StrategyBean f = null;
    private final StrategyBean e = new StrategyBean();
    private final t d = t.a();

    private a(Context context, List<com.tencent.bugly.lejiagu.a> list) {
        this.g = context;
        this.c = list;
        this.d.b(new Thread() { // from class: com.tencent.bugly.lejiagu.crashreport.common.strategy.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                try {
                    Map<String, byte[]> mapA = m.a().a(a.a, (l) null, true);
                    if (mapA != null) {
                        byte[] bArr = mapA.get("key_imei");
                        byte[] bArr2 = mapA.get("key_ip");
                        if (bArr != null) {
                            com.tencent.bugly.lejiagu.crashreport.common.info.a.a(a.this.g).e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            com.tencent.bugly.lejiagu.crashreport.common.info.a.a(a.this.g).d(new String(bArr2));
                        }
                    }
                    a aVar = a.this;
                    a.this.f = a.d();
                    a.this.a(a.this.f);
                } catch (Throwable th) {
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        });
    }

    public static synchronized a a(Context context, List<com.tencent.bugly.lejiagu.a> list) {
        if (b == null) {
            b = new a(context, list);
        }
        return b;
    }

    public static synchronized a a() {
        return b;
    }

    public final synchronized boolean b() {
        return this.f != null;
    }

    public final StrategyBean c() {
        return this.f != null ? this.f : this.e;
    }

    protected final void a(StrategyBean strategyBean) {
        for (com.tencent.bugly.lejiagu.a aVar : this.c) {
            try {
                u.c("[strategy] Notify %s", aVar.getClass().getName());
                aVar.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        b.a(strategyBean);
    }

    public final void a(an anVar) {
        if (anVar != null) {
            if (this.f == null || anVar.h != this.f.l) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.d = anVar.a;
                strategyBean.f = anVar.c;
                strategyBean.e = anVar.b;
                String str = anVar.d;
                if (!(str == null || str.trim().length() <= 0)) {
                    u.c("upload url changes to %s", anVar.d);
                    strategyBean.n = anVar.d;
                }
                String str2 = anVar.e;
                if (!(str2 == null || str2.trim().length() <= 0)) {
                    u.c("exception upload url changes to %s", anVar.e);
                    strategyBean.o = anVar.e;
                }
                if (anVar.f != null) {
                    String str3 = anVar.f.a;
                    if (!(str3 == null || str3.trim().length() <= 0)) {
                        strategyBean.q = anVar.f.a;
                    }
                }
                if (anVar.h != 0) {
                    strategyBean.l = anVar.h;
                }
                if (anVar.g != null && anVar.g.size() > 0) {
                    strategyBean.r = anVar.g;
                    String str4 = anVar.g.get("B11");
                    if (str4 != null && str4.equals("1")) {
                        strategyBean.g = true;
                    } else {
                        strategyBean.g = false;
                    }
                    strategyBean.m = anVar.i;
                    strategyBean.t = anVar.i;
                    String str5 = anVar.g.get("B27");
                    if (str5 != null && str5.length() > 0) {
                        try {
                            int i = Integer.parseInt(str5);
                            if (i > 0) {
                                strategyBean.s = i;
                            }
                        } catch (Exception e) {
                            if (!u.a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    String str6 = anVar.g.get("B25");
                    if (str6 != null && str6.equals("1")) {
                        strategyBean.h = true;
                    } else {
                        strategyBean.h = false;
                    }
                }
                u.a("cr:%b,qu:%b,uin:%b,an:%b,ss:%b,ssT:%b,ssOT:%d,cos:%b,lstT:%d", Boolean.valueOf(strategyBean.d), Boolean.valueOf(strategyBean.f), Boolean.valueOf(strategyBean.e), Boolean.valueOf(strategyBean.g), Boolean.valueOf(strategyBean.j), Boolean.valueOf(strategyBean.k), Long.valueOf(strategyBean.m), Boolean.valueOf(strategyBean.h), Long.valueOf(strategyBean.l));
                this.f = strategyBean;
                m.a();
                m.b(2);
                o oVar = new o();
                oVar.b = 2;
                oVar.a = strategyBean.b;
                oVar.e = strategyBean.c;
                Parcel parcelObtain = Parcel.obtain();
                strategyBean.writeToParcel(parcelObtain, 0);
                byte[] bArrMarshall = parcelObtain.marshall();
                parcelObtain.recycle();
                oVar.g = bArrMarshall;
                m.a().a(oVar);
                a(strategyBean);
            }
        }
    }

    public static StrategyBean d() {
        List<o> listA = m.a().a(2);
        if (listA != null && listA.size() > 0) {
            o oVar = listA.get(0);
            if (oVar.g != null) {
                return (StrategyBean) com.tencent.bugly.lejiagu.proguard.a.a(oVar.g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
}

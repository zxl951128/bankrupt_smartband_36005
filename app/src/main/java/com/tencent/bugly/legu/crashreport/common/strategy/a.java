package com.tencent.bugly.legu.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcel;
import com.tencent.bugly.legu.crashreport.biz.b;
import com.tencent.bugly.legu.proguard.ap;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.q;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static int a = 1000;
    private static a b = null;
    private final List<com.tencent.bugly.legu.a> c;
    private Context g;
    private StrategyBean f = null;
    private final StrategyBean e = new StrategyBean();
    private final v d = v.a();

    private a(Context context, List<com.tencent.bugly.legu.a> list) {
        this.g = context;
        this.c = list;
        this.d.b(new Thread() { // from class: com.tencent.bugly.legu.crashreport.common.strategy.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                try {
                    Map<String, byte[]> mapA = o.a().a(a.a, (n) null, true);
                    if (mapA != null) {
                        byte[] bArr = mapA.get("key_imei");
                        byte[] bArr2 = mapA.get("key_ip");
                        if (bArr != null) {
                            com.tencent.bugly.legu.crashreport.common.info.a.a(a.this.g).e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            com.tencent.bugly.legu.crashreport.common.info.a.a(a.this.g).d(new String(bArr2));
                        }
                    }
                    a aVar = a.this;
                    a.this.f = a.d();
                    a.this.a(a.this.f);
                } catch (Throwable th) {
                    if (!w.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        });
    }

    public static synchronized a a(Context context, List<com.tencent.bugly.legu.a> list) {
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
        for (com.tencent.bugly.legu.a aVar : this.c) {
            try {
                w.c("[strategy] Notify %s", aVar.getClass().getName());
                aVar.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        b.a(strategyBean);
    }

    public final void a(ap apVar) {
        if (apVar != null) {
            if (this.f == null || apVar.h != this.f.l) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.d = apVar.a;
                strategyBean.f = apVar.c;
                strategyBean.e = apVar.b;
                String str = apVar.d;
                if (!(str == null || str.trim().length() <= 0)) {
                    w.c("upload url changes to %s", apVar.d);
                    strategyBean.n = apVar.d;
                }
                String str2 = apVar.e;
                if (!(str2 == null || str2.trim().length() <= 0)) {
                    w.c("exception upload url changes to %s", apVar.e);
                    strategyBean.o = apVar.e;
                }
                if (apVar.f != null) {
                    String str3 = apVar.f.a;
                    if (!(str3 == null || str3.trim().length() <= 0)) {
                        strategyBean.q = apVar.f.a;
                    }
                }
                if (apVar.h != 0) {
                    strategyBean.l = apVar.h;
                }
                if (apVar.g != null && apVar.g.size() > 0) {
                    strategyBean.r = apVar.g;
                    String str4 = apVar.g.get("B11");
                    if (str4 != null && str4.equals("1")) {
                        strategyBean.g = true;
                    } else {
                        strategyBean.g = false;
                    }
                    strategyBean.m = apVar.i;
                    strategyBean.t = apVar.i;
                    String str5 = apVar.g.get("B27");
                    if (str5 != null && str5.length() > 0) {
                        try {
                            int i = Integer.parseInt(str5);
                            if (i > 0) {
                                strategyBean.s = i;
                            }
                        } catch (Exception e) {
                            if (!w.a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    String str6 = apVar.g.get("B25");
                    if (str6 != null && str6.equals("1")) {
                        strategyBean.h = true;
                    } else {
                        strategyBean.h = false;
                    }
                }
                w.a("cr:%b,qu:%b,uin:%b,an:%b,ss:%b,ssT:%b,ssOT:%d,cos:%b,lstT:%d", Boolean.valueOf(strategyBean.d), Boolean.valueOf(strategyBean.f), Boolean.valueOf(strategyBean.e), Boolean.valueOf(strategyBean.g), Boolean.valueOf(strategyBean.j), Boolean.valueOf(strategyBean.k), Long.valueOf(strategyBean.m), Boolean.valueOf(strategyBean.h), Long.valueOf(strategyBean.l));
                this.f = strategyBean;
                o.a();
                o.b(2);
                q qVar = new q();
                qVar.b = 2;
                qVar.a = strategyBean.b;
                qVar.e = strategyBean.c;
                Parcel parcelObtain = Parcel.obtain();
                strategyBean.writeToParcel(parcelObtain, 0);
                byte[] bArrMarshall = parcelObtain.marshall();
                parcelObtain.recycle();
                qVar.g = bArrMarshall;
                o.a().a(qVar);
                a(strategyBean);
            }
        }
    }

    public static StrategyBean d() {
        List<q> listA = o.a().a(2);
        if (listA != null && listA.size() > 0) {
            q qVar = listA.get(0);
            if (qVar.g != null) {
                return (StrategyBean) com.tencent.bugly.legu.proguard.a.a(qVar.g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
}

package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class u implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final com.tencent.bugly.legu.crashreport.common.info.a f;
    private final com.tencent.bugly.legu.crashreport.common.strategy.a g;
    private final r h;
    private final t i;
    private final int j;
    private final s k;
    private final s l;
    private String m;
    private int n;
    private long o;
    private long p;
    private boolean q;

    public u(Context context, int i, am amVar, String str, s sVar, boolean z) {
        this(context, i, amVar.g, a.a(amVar), str, sVar, z);
    }

    public u(Context context, int i, int i2, byte[] bArr, String str, s sVar, boolean z) {
        this(context, i, i2, bArr, str, sVar, z, 5, 60000);
    }

    private u(Context context, int i, int i2, byte[] bArr, String str, s sVar, boolean z, int i3, int i4) {
        this.a = 3;
        this.b = 30000;
        this.m = "";
        this.n = 0;
        this.o = 0L;
        this.p = 0L;
        this.q = true;
        this.c = context;
        this.f = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.legu.crashreport.common.strategy.a.a();
        this.h = r.a(context);
        this.i = t.a();
        this.j = i;
        this.m = str;
        this.k = sVar;
        t tVar = this.i;
        this.l = null;
        this.q = z;
        if (z) {
            switch (i2) {
                case 510:
                case 640:
                    i2 = 840;
                    break;
                case 630:
                    i2 = 830;
                    break;
            }
        }
        this.d = i2;
        this.a = 5;
        this.b = 60000;
    }

    private void a(an anVar, boolean z, int i, String str, int i2) {
        String strValueOf;
        switch (this.d) {
            case 630:
            case 830:
                strValueOf = "crash";
                break;
            case 640:
            case 840:
                strValueOf = "userinfo";
                break;
            default:
                strValueOf = String.valueOf(this.d);
                break;
        }
        if (z) {
            w.a("[upload] success: %s", strValueOf);
        } else {
            w.e("[upload] fail! %s %d %s", strValueOf, Integer.valueOf(i), str);
            if (this.q) {
                this.i.a(i2, (an) null);
            }
        }
        if (this.o + this.p > 0) {
            this.i.a(this.i.b() + this.o + this.p);
        }
        if (this.k != null) {
            s sVar = this.k;
            int i3 = this.d;
            long j = this.o;
            long j2 = this.p;
            sVar.a(z);
        }
        if (this.l != null) {
            s sVar2 = this.l;
            int i4 = this.d;
            long j3 = this.o;
            long j4 = this.p;
            sVar2.a(z);
        }
    }

    private static boolean a(an anVar, com.tencent.bugly.legu.crashreport.common.info.a aVar, com.tencent.bugly.legu.crashreport.common.strategy.a aVar2) {
        if (anVar == null) {
            w.d("resp == null!", new Object[0]);
            return false;
        }
        if (anVar.a != 0) {
            w.e("resp result error %d", Byte.valueOf(anVar.a));
            return false;
        }
        try {
            String str = anVar.d;
            if (!(str == null || str.trim().length() <= 0) && com.tencent.bugly.legu.crashreport.common.info.a.a().h() != anVar.d) {
                o.a().a(com.tencent.bugly.legu.crashreport.common.strategy.a.a, "key_ip", anVar.d.getBytes("UTF-8"), (n) null, true);
                aVar.d(anVar.d);
            }
            String str2 = anVar.g;
            if (!(str2 == null || str2.trim().length() <= 0) && com.tencent.bugly.legu.crashreport.common.info.a.a().i() != anVar.g) {
                o.a().a(com.tencent.bugly.legu.crashreport.common.strategy.a.a, "key_imei", anVar.g.getBytes("UTF-8"), (n) null, true);
                aVar.e(anVar.g);
            }
        } catch (Throwable th) {
        }
        aVar.h = anVar.e;
        if (anVar.b == 510) {
            if (anVar.c == null) {
                w.e("remote data is miss! %d", Integer.valueOf(anVar.b));
                return false;
            }
            ap apVar = (ap) a.a(anVar.c, ap.class);
            if (apVar == null) {
                w.e("remote data is error! %d", Integer.valueOf(anVar.b));
                return false;
            }
            Object[] objArr = new Object[4];
            objArr[0] = Boolean.valueOf(apVar.a);
            objArr[1] = Boolean.valueOf(apVar.c);
            objArr[2] = Boolean.valueOf(apVar.b);
            objArr[3] = Integer.valueOf(apVar.g == null ? -1 : apVar.g.size());
            w.c("en:%b qu:%b uin:%b vm:%d", objArr);
            aVar2.a(apVar);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        byte[] bArrA;
        byte[] bArrB;
        int i = 1;
        try {
            byte[] bArrA2 = this.e;
            this.n = 0;
            this.o = 0L;
            this.p = 0L;
            this.i.a(this.j, System.currentTimeMillis());
            if (this.k != null) {
                s sVar = this.k;
                int i2 = this.d;
            }
            if (this.l != null) {
                s sVar2 = this.l;
                int i3 = this.d;
            }
            if (a.e(this.c) == null) {
                a(null, false, 0, "network is not availiable!", 0);
                return;
            }
            if (bArrA2 == null || bArrA2.length == 0) {
                a(null, false, 0, "[upload] fail, request package is empty!", 0);
                return;
            }
            long jB = this.i.b();
            if (((long) bArrA2.length) + jB >= 2097152) {
                w.e("up too much wait to next time ! %d %d ", Long.valueOf(jB), 2097152L);
                a(null, false, 0, "[upload] fail, over net consume: 2048K", 0);
                return;
            }
            w.c("do upload task %d", Integer.valueOf(this.d));
            if (this.c == null || bArrA2 == null || this.f == null || this.g == null || this.h == null) {
                a(null, false, 0, "[upload] fail, illegal access error!", 0);
                return;
            }
            StrategyBean strategyBeanC = this.g.c();
            if (strategyBeanC == null) {
                a(null, false, 0, "[upload] fail, illegal local strategy!", 0);
                return;
            }
            HashMap map = new HashMap();
            map.put("prodId", this.f.e());
            map.put("bundleId", this.f.c);
            map.put("appVer", this.f.i);
            if (this.q) {
                map.put("cmd", Integer.toString(this.d));
                map.put("platformId", Byte.toString((byte) 1));
                this.f.getClass();
                map.put("sdkVer", "2.1.9");
                map.put("strategylastUpdateTime", Long.toString(strategyBeanC.l));
                if (!this.i.a(map)) {
                    a(null, false, 0, "[upload] fail, failed to add security info to HTTP headers", 0);
                    return;
                }
                byte[] bArrA3 = a.a(bArrA2, 2);
                if (bArrA3 == null) {
                    a(null, false, 0, "[upload] fail, failed to zip request body", 0);
                    return;
                }
                bArrA2 = this.i.a(bArrA3);
                if (bArrA2 == null) {
                    a(null, false, 0, "[upload] fail, failed to encrypt request body", 0);
                    return;
                }
            }
            byte[] bArr = bArrA2;
            int i4 = -1;
            int i5 = 0;
            i = 0;
            while (true) {
                if (i5 < this.a) {
                    i5++;
                    if (i5 != 0) {
                        w.d("failed to upload last time, wait and try(%d) again", Integer.valueOf(i5));
                        try {
                            Thread.sleep(this.b);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    w.c("send %d", Integer.valueOf(bArr.length));
                    String str = this.m;
                    if (str == null || str.trim().length() <= 0) {
                        if (this.q) {
                            if (this.d == 830) {
                                this.m = strategyBeanC.o;
                            } else {
                                this.m = strategyBeanC.n;
                            }
                        } else {
                            this.m = strategyBeanC.p;
                        }
                    }
                    w.c("do upload to %s with cmd %d (pid=%d | tid=%d)", this.m, Integer.valueOf(this.d), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    bArrA = this.h.a(this.m, bArr, this, map);
                    if (bArrA == null) {
                        w.e("try upload fail! %d %s", Integer.valueOf(this.d), "upload fail, no response!");
                    } else {
                        Map<String, String> map2 = this.h.a;
                        if (!this.q) {
                            break;
                        }
                        if (map2 == null || map2.size() == 0 || !map2.containsKey("status")) {
                            w.c("no headers from server, just try again (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                            w.e("try upload fail! %d %s", Integer.valueOf(this.d), "upload fail, no status header");
                        } else {
                            try {
                                i4 = Integer.parseInt(map2.get("status"));
                                w.c("status from server is %d (pid=%d | tid=%d)", Integer.valueOf(i4), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                if (i4 == 0) {
                                    break;
                                }
                                if (i4 == 2) {
                                    if (this.o + this.p > 0) {
                                        this.i.a(this.i.b() + this.o + this.p);
                                    }
                                    this.i.a(i4, (an) null);
                                    w.a("Session ID is invalid, will try again immediately (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                    this.i.a(this.j, this.d, this.e, null, this.l);
                                    return;
                                }
                                a(null, false, 1, "upload fail, status: " + Integer.toString(i4), i4);
                                return;
                            } catch (Throwable th) {
                                w.e("try upload fail! %d %s", Integer.valueOf(this.d), "upload fail, format of status header is invalid: " + Integer.toString(i4));
                            }
                        }
                    }
                } else {
                    a(null, false, i, "try OT Fail!", 0);
                    return;
                }
            }
            w.c("recv %d", Integer.valueOf(bArrA.length));
            if (this.q) {
                byte[] bArrB2 = this.i.b(bArrA);
                if (bArrB2 == null) {
                    a(null, false, 1, "upload fail, failed to decrypt response from server", 0);
                    return;
                }
                bArrB = a.b(bArrB2, 2);
                if (bArrB == null) {
                    a(null, false, 1, "upload fail, failed to unzip(gzip) response from server", 0);
                    return;
                }
            } else {
                bArrB = bArrA;
            }
            an anVarA = a.a(bArrB, this.q);
            if (anVarA == null) {
                a(null, false, 1, "upload fail, resp null!", 0);
                return;
            }
            if (this.q) {
                this.i.a(i4, anVarA);
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(anVarA.b);
            objArr[1] = Integer.valueOf(anVarA.c == null ? 0 : anVarA.c.length);
            w.c("response %d %d", objArr);
            if (!a(anVarA, this.f, this.g)) {
                a(anVarA, false, 2, anVarA.f, 0);
            } else {
                a(anVarA, true, 2, null, 0);
            }
        } catch (Throwable th2) {
            if (!w.a(th2)) {
                th2.printStackTrace();
            }
        }
    }

    public final void a(long j) {
        this.n++;
        this.o += j;
    }

    public final void b(long j) {
        this.p += j;
    }
}

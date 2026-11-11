package com.tencent.bugly.legu.proguard;

import android.content.Context;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    private static m a = null;
    private static long b = System.currentTimeMillis();

    private m(Context context) {
    }

    public static synchronized m a(Context context) {
        if (a == null) {
            a = new m(context);
        }
        return a;
    }

    public static synchronized m a() {
        return a;
    }

    private synchronized boolean b(int i) {
        boolean z;
        try {
            String str = com.tencent.bugly.legu.crashreport.common.info.a.a().d;
            if (str == null) {
                w.a("process name is null", new Object[0]);
                z = false;
            } else {
                long jD = d(0);
                o oVarA = o.a();
                com.tencent.bugly.legu.crashreport.common.info.a.a().getClass();
                List<l> listA = oVarA.a(i, str, jD, "2.1.9");
                if (listA != null && listA.size() >= 2) {
                    int i2 = 0;
                    while (true) {
                        if (i2 + 1 >= listA.size()) {
                            z = false;
                            break;
                        }
                        if (listA.get(i2 + 1).c - listA.get(i2).c >= 86400000) {
                            i2++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
            }
        } catch (Exception e) {
            w.a("FrenquencyCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    private synchronized l c(int i) {
        l lVarA = null;
        synchronized (this) {
            try {
                String str = com.tencent.bugly.legu.crashreport.common.info.a.a().d;
                if (str == null) {
                    w.a("process name is null", new Object[0]);
                } else {
                    lVarA = o.a().a(i, str);
                }
            } catch (Exception e) {
                w.a("getLatestCrashRecord failed", new Object[0]);
            }
        }
        return lVarA;
    }

    public final synchronized boolean a(int i, int i2) {
        boolean zA = false;
        synchronized (this) {
            try {
                l lVar = new l();
                lVar.a = 1004L;
                lVar.b = com.tencent.bugly.legu.crashreport.common.info.a.a().d;
                lVar.f = com.tencent.bugly.legu.crashreport.common.info.a.a().i;
                com.tencent.bugly.legu.crashreport.common.info.a.a().getClass();
                lVar.e = "2.1.9";
                lVar.d = i2;
                lVar.c = System.currentTimeMillis();
                lVar.g = b;
                zA = o.a().a(lVar);
            } catch (Exception e) {
                w.a("saveCrashRecord failed", new Object[0]);
            }
        }
        return zA;
    }

    private synchronized int b(int i, int i2) {
        int iA = 0;
        synchronized (this) {
            try {
                iA = o.a().a(i, com.tencent.bugly.legu.crashreport.common.info.a.a().d, d(1));
            } catch (Exception e) {
                w.a("clearHistoryCrashRecord failed", new Object[0]);
            }
        }
        return iA;
    }

    private synchronized long d(int i) {
        long jCurrentTimeMillis = 0;
        synchronized (this) {
            switch (i) {
                case 1:
                    jCurrentTimeMillis = System.currentTimeMillis() - 86400000;
                    break;
            }
        }
        return jCurrentTimeMillis;
    }

    public final synchronized int b() {
        int iB = 0;
        synchronized (this) {
            try {
                o oVarA = o.a();
                com.tencent.bugly.legu.crashreport.common.info.a.a().getClass();
                iB = oVarA.b();
            } catch (Exception e) {
                w.a("clearInvalidCrashRecord failed", new Object[0]);
            }
        }
        return iB;
    }

    public final synchronized boolean a(int i) {
        boolean z = true;
        synchronized (this) {
            try {
                l lVarC = c(i);
                if (lVarC != null && System.currentTimeMillis() - lVarC.c <= 86400000 && b(i)) {
                    z = false;
                }
                if (z) {
                    b(i, 1);
                }
            } catch (Exception e) {
                w.a("canInit failed", new Object[0]);
            }
        }
        return z;
    }
}

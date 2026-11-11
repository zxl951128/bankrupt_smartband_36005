package com.tencent.bugly.lejiagu.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.lejiagu.proguard.t;
import com.tencent.bugly.lejiagu.proguard.u;
import com.tencent.bugly.lejiagu.proguard.w;
import com.tencent.bugly.lejiagu.proguard.x;
import java.lang.reflect.Field;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    private static x a;
    private static w b;
    private static com.tencent.bugly.lejiagu.crashreport.crash.h5.b c;

    static /* synthetic */ void a() {
        com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a();
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.unity.UnityAgent");
            aVarA.getClass();
            String str = "".equals("lejiagu") ? "com.tencent.bugly" : "com.tencent.bugly.lejiagu";
            try {
                Field declaredField = cls.getDeclaredField("sdkPackageName");
                declaredField.setAccessible(true);
                declaredField.set(null, str);
            } catch (Exception e) {
            }
        } catch (Throwable th) {
            u.a("no unity agent", new Object[0]);
        }
        try {
            Class<?> cls2 = Class.forName("com.tencent.bugly.cocos.Cocos2dxAgent");
            aVarA.getClass();
            String str2 = "".equals("lejiagu") ? "com.tencent.bugly" : "com.tencent.bugly.lejiagu";
            try {
                Field declaredField2 = cls2.getDeclaredField("sdkPackageName");
                declaredField2.setAccessible(true);
                declaredField2.set(null, str2);
            } catch (Exception e2) {
            }
        } catch (Throwable th2) {
            u.a("no cocos agent", new Object[0]);
        }
    }

    public static void a(Context context) {
        c cVarA = c.a();
        if (cVarA != null) {
            a = new x(context, cVarA.k, com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a(), com.tencent.bugly.lejiagu.crashreport.common.info.a.a(), cVarA.l);
            b = new w(context, cVarA.k, com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a(), com.tencent.bugly.lejiagu.crashreport.common.info.a.a(), cVarA.l);
            c = new com.tencent.bugly.lejiagu.crashreport.crash.h5.b(context, cVarA.k, com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a(), com.tencent.bugly.lejiagu.crashreport.common.info.a.a(), cVarA.l);
            t.a().b(new Runnable() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.d.1
                @Override // java.lang.Runnable
                public final void run() {
                    d.a();
                }
            });
        }
    }

    public static void a(StrategyBean strategyBean) {
        if (b != null) {
            w wVar = b;
            boolean z = strategyBean.h;
        }
    }

    public static void a(final Thread thread, final String str, final String str2, final String str3) {
        if (a != null) {
            t.a().b(new Runnable() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.d.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        d.a.a(thread, str, str2, str3);
                    } catch (Throwable th) {
                        if (!u.b(th)) {
                            th.printStackTrace();
                        }
                        u.e("u3d crash error %s %s %s", str, str2, str3);
                    }
                }
            });
        }
    }

    public static void a(final Thread thread, final int i, final String str, final String str2, final String str3) {
        if (b != null) {
            t.a().b(new Runnable() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.d.3
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        d.b.a(thread, i, str, str2, str3);
                    } catch (Throwable th) {
                        if (!u.b(th)) {
                            th.printStackTrace();
                        }
                        u.e("cocos2d-x crash error %s %s %s", str, str2, str3);
                    }
                }
            });
        }
    }

    public static void a(final Thread thread, final String str, final String str2, final String str3, final Map<String, String> map) {
        if (c != null) {
            t.a().b(new Runnable() { // from class: com.tencent.bugly.lejiagu.crashreport.crash.d.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        d.c.a(thread, str, str2, str3, map);
                    } catch (Throwable th) {
                        if (!u.b(th)) {
                            th.printStackTrace();
                        }
                        u.e("H5 crash error %s %s %s", str, str2, str3);
                    }
                }
            });
        }
    }
}

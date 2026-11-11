package com.tencent.bugly.legu.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.tencent.bugly.legu.BuglyStrategy;
import com.tencent.bugly.legu.crashreport.biz.a.RunnableC0000a;
import com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.v;
import com.tencent.bugly.legu.proguard.w;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static a a;
    private static boolean b;
    private static int g;
    private static long h;
    private static long i;
    private static int c = 10;
    private static long d = 300000;
    private static long e = 30000;
    private static long f = 0;
    private static long j = 0;
    private static Application.ActivityLifecycleCallbacks k = null;
    private static Class<?> l = null;

    static /* synthetic */ int g() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, BuglyStrategy buglyStrategy) {
        boolean zIsEnableUserInfo;
        boolean z;
        boolean z2;
        if (buglyStrategy == null) {
            zIsEnableUserInfo = true;
            z = false;
        } else {
            boolean zRecordUserInfoOnceADay = buglyStrategy.recordUserInfoOnceADay();
            zIsEnableUserInfo = buglyStrategy.isEnableUserInfo();
            z = zRecordUserInfoOnceADay;
        }
        if (z) {
            com.tencent.bugly.legu.crashreport.common.info.a aVarA = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
            List<UserInfoBean> listA = a.a(aVarA.d);
            if (listA == null) {
                z2 = true;
                break;
            }
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < listA.size()) {
                    UserInfoBean userInfoBean = listA.get(i3);
                    if (userInfoBean.n.equals(aVarA.i) && userInfoBean.b == 1) {
                        long jO = com.tencent.bugly.legu.proguard.a.o();
                        if (jO > 0) {
                            if (userInfoBean.e >= jO) {
                                if (userInfoBean.f <= 0) {
                                    a.b();
                                }
                                z2 = false;
                                break;
                            }
                        }
                    }
                    i2 = i3 + 1;
                }
                z2 = true;
                break;
            }
            if (z2) {
                zIsEnableUserInfo = false;
            } else {
                return;
            }
        }
        com.tencent.bugly.legu.crashreport.common.info.a aVarA2 = com.tencent.bugly.legu.crashreport.common.info.a.a();
        if (aVarA2 != null) {
            boolean z3 = false;
            String className = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    className = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    z3 = true;
                }
            }
            if (className == null) {
                className = "unknown";
            } else if (z3) {
                aVarA2.n = true;
            } else {
                className = "background";
            }
            aVarA2.o = className;
        }
        if (zIsEnableUserInfo) {
            if (Build.VERSION.SDK_INT >= 14) {
                Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
                if (application != null) {
                    try {
                        if (k == null) {
                            k = new Application.ActivityLifecycleCallbacks() { // from class: com.tencent.bugly.legu.crashreport.biz.b.2
                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivityStopped(Activity activity) {
                                }

                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivityStarted(Activity activity) {
                                }

                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                                }

                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivityResumed(Activity activity) {
                                    String name = "unknown";
                                    if (activity != null) {
                                        name = activity.getClass().getName();
                                    }
                                    if (b.l == null || b.l.getName().equals(name)) {
                                        w.c(">>> %s onResumed <<<", name);
                                        com.tencent.bugly.legu.crashreport.common.info.a aVarA3 = com.tencent.bugly.legu.crashreport.common.info.a.a();
                                        if (aVarA3 != null) {
                                            aVarA3.n = true;
                                            aVarA3.o = name;
                                            aVarA3.p = System.currentTimeMillis();
                                            aVarA3.s = aVarA3.p - b.i;
                                            long j2 = aVarA3.p - b.h;
                                            if (j2 > (b.f > 0 ? b.f : b.e)) {
                                                aVarA3.c();
                                                b.g();
                                                w.a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(j2 / 1000), Long.valueOf(b.e / 1000));
                                                if (b.g % b.c == 0) {
                                                    b.a.a(4, true, 0L);
                                                    return;
                                                }
                                                b.a.a(4, false, 0L);
                                                long jCurrentTimeMillis = System.currentTimeMillis();
                                                if (jCurrentTimeMillis - b.j > b.d) {
                                                    long unused = b.j = jCurrentTimeMillis;
                                                    w.a("add a timer to upload hot start user info", new Object[0]);
                                                    v.a().a(b.a.new RunnableC0000a(null, true), b.d);
                                                }
                                            }
                                        }
                                    }
                                }

                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivityPaused(Activity activity) {
                                    String name = "unknown";
                                    if (activity != null) {
                                        name = activity.getClass().getName();
                                    }
                                    if (b.l == null || b.l.getName().equals(name)) {
                                        w.c(">>> %s onPaused <<<", name);
                                        com.tencent.bugly.legu.crashreport.common.info.a aVarA3 = com.tencent.bugly.legu.crashreport.common.info.a.a();
                                        if (aVarA3 != null) {
                                            aVarA3.n = false;
                                            aVarA3.q = System.currentTimeMillis();
                                            aVarA3.r = aVarA3.q - aVarA3.p;
                                            long unused = b.h = aVarA3.q;
                                            if (aVarA3.r < 0) {
                                                aVarA3.r = 0L;
                                            }
                                            if (activity != null) {
                                                aVarA3.o = "background";
                                            } else {
                                                aVarA3.o = "unknown";
                                            }
                                        }
                                    }
                                }

                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivityDestroyed(Activity activity) {
                                }

                                @Override // android.app.Application.ActivityLifecycleCallbacks
                                public final void onActivityCreated(Activity activity, Bundle bundle) {
                                }
                            };
                        }
                        application.registerActivityLifecycleCallbacks(k);
                    } catch (Exception e2) {
                    }
                }
            }
        }
        i = System.currentTimeMillis();
        a.a(1, true, 0L);
        t.a().a(1001, System.currentTimeMillis());
        w.a("[session] launch app, new start", new Object[0]);
        a.a();
        v.a().a(a.new RunnableC0000a(null, true), 21600000L);
    }

    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        long appReportDelay;
        if (!b) {
            e = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().m;
            c = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().s;
            a = new a(context);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                appReportDelay = buglyStrategy.getAppReportDelay();
            } else {
                appReportDelay = 0;
            }
            if (appReportDelay <= 0) {
                c(context, buglyStrategy);
            } else {
                v.a().a(new Runnable() { // from class: com.tencent.bugly.legu.crashreport.biz.b.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.c(context, buglyStrategy);
                    }
                }, appReportDelay);
            }
        }
    }

    public static void a(long j2) {
        if (j2 < 0) {
            j2 = com.tencent.bugly.legu.crashreport.common.strategy.a.a().c().m;
        }
        f = j2;
    }

    public static void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.m > 0) {
                e = strategyBean.m;
            }
            if (strategyBean.s > 0) {
                c = strategyBean.s;
            }
            if (strategyBean.t > 0) {
                d = strategyBean.t;
            }
        }
    }

    public static void a() {
        if (a != null) {
            a.a(2, false, 0L);
        }
    }

    public static void a(Context context) {
        if (b && context != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
                if (application != null) {
                    try {
                        if (k != null) {
                            application.unregisterActivityLifecycleCallbacks(k);
                        }
                    } catch (Exception e2) {
                    }
                }
            }
            b = false;
        }
    }
}

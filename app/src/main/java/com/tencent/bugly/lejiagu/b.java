package com.tencent.bugly.lejiagu;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.lejiagu.crashreport.BuglyHintException;
import com.tencent.bugly.lejiagu.proguard.m;
import com.tencent.bugly.lejiagu.proguard.r;
import com.tencent.bugly.lejiagu.proguard.u;
import com.tencent.bugly.lejiagu.proguard.v;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static boolean a;
    public static Map<String, String> b;
    private static List<a> c = new ArrayList();
    private static final String d;
    private static final String e;
    private static final String f;
    private static final String g;
    private static String[] h;
    private static String[] i;
    private static Boolean j;
    private static boolean k;

    static {
        d = "com.tencent.bugly" + (("lejiagu".equals("") || "lejiagu".contains("@")) ? "" : ".lejiagu");
        e = d + ".CrashModule";
        f = d + ".beta.Beta";
        g = d + ".feedback.BFeedback";
        h = new String[]{e, "com.tencent.feedback.eup.CrashReport", f};
        i = new String[]{"com.tencent.feedback.eup.CrashReport", e, f, g};
        b = null;
    }

    private static boolean a(com.tencent.bugly.lejiagu.crashreport.common.info.a aVar) {
        String str;
        List<String> list = aVar.m;
        aVar.getClass();
        if ("lejiagu".equals("")) {
            str = "bugly";
        } else {
            aVar.getClass();
            str = "lejiagu";
        }
        if (list != null && list.contains(str)) {
            return true;
        }
        return false;
    }

    public static synchronized void a(Context context) {
        a(context, null);
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (k) {
            u.d("[init] initial Multi-times, ignore this.", new Object[0]);
        } else if (context == null) {
            Log.w(u.b, "[init] context of init() is null, check it.");
        } else {
            com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context);
            if (a(aVarA)) {
                Bugly.enable = false;
            } else {
                String strE = aVarA.e();
                if (strE == null) {
                    throw new BuglyHintException("[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                }
                a(context, strE, aVarA.t, buglyStrategy);
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        Context applicationContext;
        String strSubstring;
        String strSubstring2;
        String strSubstring3;
        String strSubstring4;
        synchronized (b.class) {
            if (k) {
                u.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(u.b, "[init] context of init() is null, check it.");
            } else {
                if (str == null) {
                    throw new BuglyHintException("init arg 'crashReportAppID' should not be null!");
                }
                k = true;
                if (z) {
                    a = true;
                    u.c = true;
                    u.d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    u.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    u.d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    u.d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    u.d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    u.d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                    u.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    u.b("[init] bugly in debug mode.", new Object[0]);
                }
                if (context != null && (applicationContext = context.getApplicationContext()) != null) {
                    context = applicationContext;
                }
                if (j == null) {
                    j = Boolean.valueOf(Boolean.parseBoolean(com.tencent.bugly.legu.Bugly.SDK_IS_DEV.replace("@", "")));
                }
                if (j.booleanValue()) {
                    h = i;
                }
                for (String str2 : h) {
                    try {
                        a aVar = (a) com.tencent.bugly.lejiagu.proguard.a.a(str2, "getInstance", null, null, null);
                        if (aVar != null) {
                            c.add(aVar);
                            u.c("[module] load: %s", str2);
                        }
                    } catch (Throwable th) {
                    }
                }
                v.a(context);
                m.a(context, c);
                r.a(context);
                com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a(context, c);
                com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context);
                if (a(aVarA)) {
                    Bugly.enable = false;
                } else {
                    StringBuilder sb = new StringBuilder();
                    aVarA.getClass();
                    u.a(sb.append("lejiagu crash report start init!").toString(), new Object[0]);
                    u.b("[init] bugly start init...", new Object[0]);
                    aVarA.a(str);
                    u.a("[param] setted APPID:%s", str);
                    if (buglyStrategy != null) {
                        String appVersion = buglyStrategy.getAppVersion();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                strSubstring4 = appVersion.substring(0, 100);
                                u.d("appVersion %s length is over limit %d substring to %s", appVersion, 100, strSubstring4);
                            } else {
                                strSubstring4 = appVersion;
                            }
                            aVarA.i = strSubstring4;
                            u.a("setted APPVERSION:%s", buglyStrategy.getAppVersion());
                        }
                        String appChannel = buglyStrategy.getAppChannel();
                        if (!TextUtils.isEmpty(appChannel)) {
                            if (appChannel.length() > 100) {
                                strSubstring3 = appChannel.substring(0, 100);
                                u.d("appChannel %s length is over limit %d substring to %s", appChannel, 100, strSubstring3);
                            } else {
                                strSubstring3 = appChannel;
                            }
                            aVarA.j = strSubstring3;
                            u.a("setted APPCHANNEL:%s", buglyStrategy.getAppChannel());
                        }
                        String appPackageName = buglyStrategy.getAppPackageName();
                        if (!TextUtils.isEmpty(appPackageName)) {
                            if (appPackageName.length() > 100) {
                                strSubstring2 = appPackageName.substring(0, 100);
                                u.d("appPackageName %s length is over limit %d substring to %s", appPackageName, 100, strSubstring2);
                            } else {
                                strSubstring2 = appPackageName;
                            }
                            aVarA.c = strSubstring2;
                            u.a("setted PACKAGENAME:%s", buglyStrategy.getAppPackageName());
                        }
                        String deviceID = buglyStrategy.getDeviceID();
                        if (deviceID != null) {
                            if (deviceID.length() > 100) {
                                strSubstring = deviceID.substring(0, 100);
                                u.d("deviceId %s length is over limit %d substring to %s", deviceID, 100, strSubstring);
                            } else {
                                strSubstring = deviceID;
                            }
                            aVarA.c(strSubstring);
                            u.a("setted deviceId :%s", strSubstring);
                        }
                        v.a = buglyStrategy.isBuglyLogUpload();
                    }
                    com.tencent.bugly.lejiagu.crashreport.biz.b.a(context, buglyStrategy);
                    for (int i2 = 0; i2 < c.size(); i2++) {
                        try {
                            c.get(i2).init(context, z, buglyStrategy);
                        } catch (Throwable th2) {
                            if (!u.a(th2)) {
                                th2.printStackTrace();
                            }
                        }
                    }
                    u.a("crash report inited!", new Object[0]);
                    u.b("[init] bugly init finished.", new Object[0]);
                }
            }
        }
    }
}

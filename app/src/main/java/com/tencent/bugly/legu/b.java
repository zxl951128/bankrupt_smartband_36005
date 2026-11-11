package com.tencent.bugly.legu;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.legu.proguard.m;
import com.tencent.bugly.legu.proguard.n;
import com.tencent.bugly.legu.proguard.o;
import com.tencent.bugly.legu.proguard.t;
import com.tencent.bugly.legu.proguard.w;
import com.tencent.bugly.legu.proguard.x;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static boolean b;
    private static o e;
    private static m f;
    private static boolean g;
    public static boolean a = true;
    private static List<a> d = new ArrayList();
    public static Map<String, String> c = null;

    private static boolean a(com.tencent.bugly.legu.crashreport.common.info.a aVar) {
        String str;
        List<String> list = aVar.m;
        aVar.getClass();
        if ("legu".equals("")) {
            str = "bugly";
        } else {
            aVar.getClass();
            str = "legu";
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
        if (g) {
            w.d("[init] initial Multi-times, ignore this.", new Object[0]);
        } else if (context == null) {
            Log.w(w.a, "[init] context of init() is null, check it.");
        } else {
            com.tencent.bugly.legu.crashreport.common.info.a aVarA = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
            if (a(aVarA)) {
                a = false;
            } else {
                String strE = aVarA.e();
                if (strE == null) {
                    Log.e(w.a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                } else {
                    a(context, strE, aVarA.t, buglyStrategy);
                }
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        Context applicationContext;
        String strSubstring;
        String strSubstring2;
        byte[] bArr;
        String str2;
        String strSubstring3;
        if (g) {
            w.d("[init] initial Multi-times, ignore this.", new Object[0]);
        } else if (context == null) {
            Log.w(w.a, "[init] context of init() is null, check it.");
        } else if (str == null) {
            Log.e(w.a, "init arg 'crashReportAppID' should not be null!");
        } else {
            g = true;
            if (z) {
                b = true;
                w.b = true;
                w.d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                w.e("--------------------------------------------------------------------------------------------", new Object[0]);
                w.d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                w.d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                w.d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                w.d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                w.e("--------------------------------------------------------------------------------------------", new Object[0]);
                w.b("[init] bugly in debug mode.", new Object[0]);
            }
            if (context != null && (applicationContext = context.getApplicationContext()) != null) {
                context = applicationContext;
            }
            x.a(context);
            com.tencent.bugly.legu.crashreport.common.info.a aVarA = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
            e = o.a(context, d);
            t.a(context);
            com.tencent.bugly.legu.crashreport.common.strategy.a.a(context, d);
            f = m.a(context);
            if (a(aVarA)) {
                a = false;
            } else {
                StringBuilder sb = new StringBuilder();
                aVarA.getClass();
                w.a(sb.append("legu crash report start init!").toString(), new Object[0]);
                w.b("[init] bugly start init...", new Object[0]);
                aVarA.a(str);
                w.a("[param] setted APPID:%s", str);
                if (buglyStrategy != null) {
                    String appVersion = buglyStrategy.getAppVersion();
                    if (!TextUtils.isEmpty(appVersion)) {
                        if (appVersion.length() > 100) {
                            strSubstring3 = appVersion.substring(0, 100);
                            w.d("appVersion %s length is over limit %d substring to %s", appVersion, 100, strSubstring3);
                        } else {
                            strSubstring3 = appVersion;
                        }
                        aVarA.i = strSubstring3;
                        w.a("setted APPVERSION:%s", buglyStrategy.getAppVersion());
                    }
                    try {
                        if (buglyStrategy.isReplaceOldChannel()) {
                            String appChannel = buglyStrategy.getAppChannel();
                            if (!TextUtils.isEmpty(appChannel)) {
                                if (appChannel.length() > 100) {
                                    String strSubstring4 = appChannel.substring(0, 100);
                                    w.d("appChannel %s length is over limit %d substring to %s", appChannel, 100, strSubstring4);
                                    str2 = strSubstring4;
                                } else {
                                    str2 = appChannel;
                                }
                                e.a(556, "app_channel", str2.getBytes(), (n) null, false);
                                aVarA.j = str2;
                            }
                        } else {
                            Map<String, byte[]> mapA = e.a(556, (n) null, true);
                            if (mapA != null && (bArr = mapA.get("app_channel")) != null) {
                                aVarA.j = new String(bArr);
                            }
                        }
                        w.a("setted APPCHANNEL:%s", aVarA.j);
                    } catch (Exception e2) {
                        if (b) {
                            e2.printStackTrace();
                        }
                    }
                    String appPackageName = buglyStrategy.getAppPackageName();
                    if (!TextUtils.isEmpty(appPackageName)) {
                        if (appPackageName.length() > 100) {
                            strSubstring2 = appPackageName.substring(0, 100);
                            w.d("appPackageName %s length is over limit %d substring to %s", appPackageName, 100, strSubstring2);
                        } else {
                            strSubstring2 = appPackageName;
                        }
                        aVarA.c = strSubstring2;
                        w.a("setted PACKAGENAME:%s", buglyStrategy.getAppPackageName());
                    }
                    String deviceID = buglyStrategy.getDeviceID();
                    if (deviceID != null) {
                        if (deviceID.length() > 100) {
                            strSubstring = deviceID.substring(0, 100);
                            w.d("deviceId %s length is over limit %d substring to %s", deviceID, 100, strSubstring);
                        } else {
                            strSubstring = deviceID;
                        }
                        aVarA.c(strSubstring);
                        w.a("setted deviceId :%s", strSubstring);
                    }
                    x.a = buglyStrategy.isBuglyLogUpload();
                }
                com.tencent.bugly.legu.crashreport.biz.b.a(context, buglyStrategy);
                f.b();
                for (int i = 0; i < d.size(); i++) {
                    try {
                        if (f.a(d.get(i).id)) {
                            d.get(i).init(context, z, buglyStrategy);
                        }
                    } catch (Throwable th) {
                        if (!w.a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
                w.a("crash report inited!", new Object[0]);
                w.b("[init] bugly init finished.", new Object[0]);
            }
        }
    }

    public static void a(a aVar) {
        if (!d.contains(aVar)) {
            d.add(aVar);
        }
    }
}

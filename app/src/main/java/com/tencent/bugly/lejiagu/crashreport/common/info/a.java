package com.tencent.bugly.lejiagu.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static a T = null;
    private String P;
    public final byte b;
    public String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public long h;
    public String i;
    public String j;
    public List<String> m;
    public boolean n;
    public boolean t;
    public String u;
    public String v;
    public String w;
    private final Context x;
    private String y;
    private String z;
    private String A = "unknown";
    private String B = "unknown";
    private String C = "";
    private String D = null;
    private String E = null;
    private String F = null;
    private String G = null;
    private long H = -1;
    private long I = -1;
    private long J = -1;
    private String K = null;
    private String L = null;
    private Map<String, PlugInBean> M = null;
    private boolean N = true;
    private String O = null;
    private Boolean Q = null;
    private String R = null;
    public String k = null;
    public String l = null;
    private Map<String, PlugInBean> S = null;
    private int U = -1;
    private int V = -1;
    private Map<String, String> W = new HashMap();
    private Map<String, String> X = new HashMap();
    public String o = "unknown";
    public long p = 0;
    public long q = 0;
    public long r = 0;
    public long s = 0;
    private final Object Y = new Object();
    private final Object Z = new Object();
    public final long a = System.currentTimeMillis();

    private a(Context context) {
        Context applicationContext;
        this.i = null;
        this.P = null;
        this.j = null;
        this.m = null;
        this.t = false;
        this.u = null;
        this.v = null;
        this.w = null;
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            applicationContext = context;
        }
        this.x = applicationContext;
        this.b = (byte) 1;
        PackageInfo packageInfoB = AppInfo.b(context);
        if (packageInfoB != null) {
            this.i = AppInfo.a(packageInfoB);
            this.u = this.i;
            this.v = AppInfo.b(packageInfoB);
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.c(context);
        this.e = com.tencent.bugly.lejiagu.proguard.a.m();
        this.f = com.tencent.bugly.lejiagu.proguard.a.b();
        this.g = "Android " + com.tencent.bugly.lejiagu.proguard.a.c() + ",level " + com.tencent.bugly.lejiagu.proguard.a.d();
        String str = this.f + ";" + this.g;
        Map<String, String> mapD = AppInfo.d(context);
        if (mapD != null) {
            try {
                this.m = AppInfo.a(mapD);
                String str2 = mapD.get("BUGLY_APPID");
                if (str2 != null) {
                    this.P = str2;
                }
                String str3 = mapD.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.i = str3;
                }
                String str4 = mapD.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.j = str4;
                }
                String str5 = mapD.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.t = str5.toLowerCase().equals("true");
                }
                String str6 = mapD.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.w = str6;
                }
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        u.c("com info create end", new Object[0]);
    }

    public static synchronized a a(Context context) {
        if (T == null) {
            T = new a(context);
        }
        return T;
    }

    public static synchronized a a() {
        return T;
    }

    public static String b() {
        return "2.1.6";
    }

    public final void c() {
        synchronized (this.Y) {
            this.y = UUID.randomUUID().toString();
        }
    }

    public final String d() {
        if (this.y == null) {
            synchronized (this.Y) {
                if (this.y == null) {
                    this.y = UUID.randomUUID().toString();
                }
            }
        }
        return this.y;
    }

    public final String e() {
        String str = null;
        if (0 == 0 || str.trim().length() <= 0) {
            return this.P;
        }
        return null;
    }

    public final void a(String str) {
        this.P = str;
    }

    public final synchronized String f() {
        return this.A;
    }

    public final synchronized void b(String str) {
        if (str == null) {
            str = "10000";
        }
        this.A = str;
    }

    public final synchronized String g() {
        String str;
        if (this.z != null) {
            str = this.z;
        } else {
            this.z = j() + "|" + l() + "|" + m();
            str = this.z;
        }
        return str;
    }

    public final synchronized void c(String str) {
        this.z = str;
    }

    public final synchronized String h() {
        return this.B;
    }

    public final synchronized void d(String str) {
        this.B = str;
    }

    public final synchronized String i() {
        return this.C;
    }

    public final synchronized void e(String str) {
        this.C = str;
    }

    public final synchronized String j() {
        String str;
        if (!this.N) {
            str = "";
        } else {
            if (this.D == null) {
                this.D = com.tencent.bugly.lejiagu.proguard.a.a(this.x);
            }
            str = this.D;
        }
        return str;
    }

    public final synchronized String k() {
        String str;
        if (!this.N) {
            str = "";
        } else {
            if (this.E == null) {
                this.E = com.tencent.bugly.lejiagu.proguard.a.d(this.x);
            }
            str = this.E;
        }
        return str;
    }

    public final synchronized String l() {
        String str;
        if (!this.N) {
            str = "";
        } else {
            if (this.F == null) {
                this.F = com.tencent.bugly.lejiagu.proguard.a.b(this.x);
            }
            str = this.F;
        }
        return str;
    }

    public final synchronized String m() {
        String str;
        if (!this.N) {
            str = "";
        } else {
            if (this.G == null) {
                this.G = com.tencent.bugly.lejiagu.proguard.a.c(this.x);
            }
            str = this.G;
        }
        return str;
    }

    public final synchronized long n() {
        if (this.H <= 0) {
            this.H = com.tencent.bugly.lejiagu.proguard.a.f();
        }
        return this.H;
    }

    public final synchronized long o() {
        if (this.I <= 0) {
            this.I = com.tencent.bugly.lejiagu.proguard.a.h();
        }
        return this.I;
    }

    public final synchronized long p() {
        if (this.J <= 0) {
            this.J = com.tencent.bugly.lejiagu.proguard.a.j();
        }
        return this.J;
    }

    public final synchronized String q() {
        if (this.K == null) {
            this.K = com.tencent.bugly.lejiagu.proguard.a.e();
        }
        return this.K;
    }

    public final String r() {
        if (this.L == null) {
            synchronized (this.Z) {
                if (this.L == null) {
                    this.L = com.tencent.bugly.lejiagu.proguard.a.a(this.x, "ro.board.platform");
                }
            }
        }
        return this.L;
    }

    public final synchronized Map<String, PlugInBean> s() {
        return null;
    }

    public final String t() {
        if (this.O == null) {
            this.O = com.tencent.bugly.lejiagu.proguard.a.l();
        }
        return this.O;
    }

    public final synchronized Boolean u() {
        if (this.Q == null) {
            this.Q = Boolean.valueOf(com.tencent.bugly.lejiagu.proguard.a.f(this.x));
        }
        return this.Q;
    }

    public final synchronized String v() {
        String str;
        String str2;
        synchronized (this) {
            if (this.R == null) {
                StringBuilder sb = new StringBuilder();
                Context context = this.x;
                String strA = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.miui.ui.version.name");
                if ((strA == null || strA.trim().length() <= 0) || strA.equals("fail")) {
                    String strA2 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.version.emui");
                    if ((strA2 == null || strA2.trim().length() <= 0) || strA2.equals("fail")) {
                        String strA3 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.lenovo.series");
                        if ((strA3 == null || strA3.trim().length() <= 0) || strA3.equals("fail")) {
                            String strA4 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.nubia.rom.name");
                            if ((strA4 == null || strA4.trim().length() <= 0) || strA4.equals("fail")) {
                                String strA5 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.meizu.product.model");
                                if ((strA5 == null || strA5.trim().length() <= 0) || strA5.equals("fail")) {
                                    String strA6 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.version.opporom");
                                    if ((strA6 == null || strA6.trim().length() <= 0) || strA6.equals("fail")) {
                                        String strA7 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.vivo.os.build.display.id");
                                        if ((strA7 == null || strA7.trim().length() <= 0) || strA7.equals("fail")) {
                                            String strA8 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.aa.romver");
                                            if ((strA8 == null || strA8.trim().length() <= 0) || strA8.equals("fail")) {
                                                String strA9 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.lewa.version");
                                                if ((strA9 == null || strA9.trim().length() <= 0) || strA9.equals("fail")) {
                                                    String strA10 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.gn.gnromvernumber");
                                                    if ((strA10 == null || strA10.trim().length() <= 0) || strA10.equals("fail")) {
                                                        String strA11 = com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.tyd.kbstyle_version");
                                                        str2 = ((strA11 == null || strA11.trim().length() <= 0) || strA11.equals("fail")) ? com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.fingerprint") + "/" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.rom.id") : "dido/" + strA11;
                                                    } else {
                                                        str2 = "amigo/" + strA10 + "/" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.display.id");
                                                    }
                                                } else {
                                                    str2 = "tcl/" + strA9 + "/" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.display.id");
                                                }
                                            } else {
                                                str2 = "htc/" + strA8 + "/" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.description");
                                            }
                                        } else {
                                            str2 = "vivo/FUNTOUCH/" + strA7;
                                        }
                                    } else {
                                        str2 = "Oppo/COLOROS/" + strA6;
                                    }
                                } else {
                                    str2 = "Meizu/FLYME/" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.display.id");
                                }
                            } else {
                                str2 = "Zte/NUBIA/" + strA4 + "_" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.nubia.rom.code");
                            }
                        } else {
                            str2 = "Lenovo/VIBE/" + com.tencent.bugly.lejiagu.proguard.a.a(context, "ro.build.version.incremental");
                        }
                    } else {
                        str2 = "HuaWei/EMOTION/" + strA2;
                    }
                } else {
                    str2 = "XiaoMi/MIUI/" + strA;
                }
                this.R = sb.append(str2).toString();
                u.a("rom:%s", this.R);
            }
            str = this.R;
        }
        return str;
    }

    public final synchronized Map<String, String> w() {
        return this.W.size() <= 0 ? null : new HashMap(this.W);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002c  */
    public final synchronized String f(String str) {
        boolean z;
        String strRemove;
        synchronized (this) {
            if (str != null) {
                z = str.trim().length() <= 0;
            }
            if (z) {
                u.d("key should not be empty %s", str);
                strRemove = null;
            } else {
                strRemove = this.W.remove(str);
            }
        }
        return strRemove;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002c  */
    public final synchronized String g(String str) {
        boolean z;
        String str2;
        synchronized (this) {
            if (str != null) {
                z = str.trim().length() <= 0;
            }
            if (z) {
                u.d("key should not be empty %s", str);
                str2 = null;
            } else {
                str2 = this.W.get(str);
            }
        }
        return str2;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0020 A[Catch: all -> 0x0054, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:10:0x0014, B:18:0x004e, B:13:0x0020), top: B:23:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:16:0x004a  */
    public final synchronized void a(String str, String str2) {
        boolean z;
        synchronized (this) {
            if (str == null) {
                z = true;
            } else if (str.trim().length() > 0) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                u.d("key&value should not be empty %s %s", str, str2);
            } else {
                if (str2 == null || str2.trim().length() <= 0) {
                    u.d("key&value should not be empty %s %s", str, str2);
                } else {
                    this.W.put(str, str2);
                }
            }
        }
    }

    public final synchronized int x() {
        return this.W.size();
    }

    public final synchronized Set<String> y() {
        return this.W.keySet();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0020 A[Catch: all -> 0x0054, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:10:0x0014, B:18:0x004e, B:13:0x0020), top: B:23:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:16:0x004a  */
    public final synchronized void b(String str, String str2) {
        boolean z;
        synchronized (this) {
            if (str == null) {
                z = true;
            } else if (str.trim().length() > 0) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                u.d("server key&value should not be empty %s %s", str, str2);
            } else {
                if (str2 == null || str2.trim().length() <= 0) {
                    u.d("server key&value should not be empty %s %s", str, str2);
                } else {
                    this.X.put(str, str2);
                }
            }
        }
    }

    public final synchronized Map<String, String> z() {
        return this.X.size() <= 0 ? null : new HashMap(this.X);
    }

    public final synchronized void a(int i) {
        int i2 = this.U;
        if (i2 != i) {
            this.U = i;
            u.a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.U));
        }
    }

    public final synchronized int A() {
        return this.U;
    }

    public final synchronized int B() {
        return this.V;
    }

    public final synchronized boolean C() {
        return AppInfo.e(this.x);
    }

    public final synchronized Map<String, PlugInBean> D() {
        return null;
    }

    public final synchronized int E() {
        return com.tencent.bugly.lejiagu.proguard.a.d();
    }
}

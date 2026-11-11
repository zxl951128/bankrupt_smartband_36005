package com.tencent.bugly.legu.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.tencent.bugly.legu.b;
import com.tencent.bugly.legu.proguard.w;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static a V = null;
    private String A;
    private String B;
    private String R;
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
    public boolean y;
    private final Context z;
    private String C = "unknown";
    private String D = "unknown";
    private String E = "";
    private String F = null;
    private String G = null;
    private String H = null;
    private String I = null;
    private long J = -1;
    private long K = -1;
    private long L = -1;
    private String M = null;
    private String N = null;
    private Map<String, PlugInBean> O = null;
    private boolean P = true;
    private String Q = null;
    private Boolean S = null;
    private String T = null;
    public String k = null;
    public String l = null;
    private Map<String, PlugInBean> U = null;
    private int W = -1;
    private int X = -1;
    private Map<String, String> Y = new HashMap();
    private Map<String, String> Z = new HashMap();
    public String o = "unknown";
    public long p = 0;
    public long q = 0;
    public long r = 0;
    public long s = 0;
    public boolean x = false;
    private final Object aa = new Object();
    private final Object ab = new Object();
    public final long a = System.currentTimeMillis();

    private a(Context context) {
        Context applicationContext;
        this.i = null;
        this.R = null;
        this.j = null;
        this.m = null;
        this.t = false;
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = false;
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            applicationContext = context;
        }
        this.z = applicationContext;
        this.b = (byte) 1;
        PackageInfo packageInfoB = AppInfo.b(context);
        if (packageInfoB != null) {
            this.i = AppInfo.a(packageInfoB);
            this.u = this.i;
            this.v = AppInfo.b(packageInfoB);
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.c(context);
        this.e = com.tencent.bugly.legu.proguard.a.m();
        this.f = com.tencent.bugly.legu.proguard.a.b();
        this.g = "Android " + com.tencent.bugly.legu.proguard.a.c() + ",level " + com.tencent.bugly.legu.proguard.a.d();
        String str = this.f + ";" + this.g;
        Map<String, String> mapD = AppInfo.d(context);
        if (mapD != null) {
            try {
                this.m = AppInfo.a(mapD);
                String str2 = mapD.get("BUGLY_APPID");
                if (str2 != null) {
                    this.R = str2;
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
                if (!w.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_legu").exists()) {
                this.y = true;
                w.c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th2) {
            if (b.b) {
                th2.printStackTrace();
            }
        }
        w.c("com info create end", new Object[0]);
    }

    public static synchronized a a(Context context) {
        if (V == null) {
            V = new a(context);
        }
        return V;
    }

    public static synchronized a a() {
        return V;
    }

    public static String b() {
        return "2.1.9";
    }

    public final void c() {
        synchronized (this.aa) {
            this.A = UUID.randomUUID().toString();
        }
    }

    public final String d() {
        if (this.A == null) {
            synchronized (this.aa) {
                if (this.A == null) {
                    this.A = UUID.randomUUID().toString();
                }
            }
        }
        return this.A;
    }

    public final String e() {
        String str = null;
        if (0 == 0 || str.trim().length() <= 0) {
            return this.R;
        }
        return null;
    }

    public final void a(String str) {
        this.R = str;
    }

    public final synchronized String f() {
        return this.C;
    }

    public final synchronized void b(String str) {
        if (str == null) {
            str = "10000";
        }
        this.C = str;
    }

    public final synchronized String g() {
        String str;
        if (this.B != null) {
            str = this.B;
        } else {
            this.B = j() + "|" + l() + "|" + m();
            str = this.B;
        }
        return str;
    }

    public final synchronized void c(String str) {
        this.B = str;
    }

    public final synchronized String h() {
        return this.D;
    }

    public final synchronized void d(String str) {
        this.D = str;
    }

    public final synchronized String i() {
        return this.E;
    }

    public final synchronized void e(String str) {
        this.E = str;
    }

    public final synchronized String j() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.F == null) {
                this.F = com.tencent.bugly.legu.proguard.a.a(this.z);
            }
            str = this.F;
        }
        return str;
    }

    public final synchronized String k() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.G == null) {
                this.G = com.tencent.bugly.legu.proguard.a.d(this.z);
            }
            str = this.G;
        }
        return str;
    }

    public final synchronized String l() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.H == null) {
                this.H = com.tencent.bugly.legu.proguard.a.b(this.z);
            }
            str = this.H;
        }
        return str;
    }

    public final synchronized String m() {
        String str;
        if (!this.P) {
            str = "";
        } else {
            if (this.I == null) {
                this.I = com.tencent.bugly.legu.proguard.a.c(this.z);
            }
            str = this.I;
        }
        return str;
    }

    public final synchronized long n() {
        if (this.J <= 0) {
            this.J = com.tencent.bugly.legu.proguard.a.f();
        }
        return this.J;
    }

    public final synchronized long o() {
        if (this.K <= 0) {
            this.K = com.tencent.bugly.legu.proguard.a.h();
        }
        return this.K;
    }

    public final synchronized long p() {
        if (this.L <= 0) {
            this.L = com.tencent.bugly.legu.proguard.a.j();
        }
        return this.L;
    }

    public final synchronized String q() {
        if (this.M == null) {
            this.M = com.tencent.bugly.legu.proguard.a.e();
        }
        return this.M;
    }

    public final String r() {
        if (this.N == null) {
            synchronized (this.ab) {
                if (this.N == null) {
                    this.N = com.tencent.bugly.legu.proguard.a.a(this.z, "ro.board.platform");
                }
            }
        }
        return this.N;
    }

    public final synchronized Map<String, PlugInBean> s() {
        return null;
    }

    public final String t() {
        if (this.Q == null) {
            this.Q = com.tencent.bugly.legu.proguard.a.l();
        }
        return this.Q;
    }

    public final synchronized Boolean u() {
        if (this.S == null) {
            this.S = Boolean.valueOf(com.tencent.bugly.legu.proguard.a.f(this.z));
        }
        return this.S;
    }

    public final synchronized String v() {
        String str;
        String str2;
        synchronized (this) {
            if (this.T == null) {
                StringBuilder sb = new StringBuilder();
                Context context = this.z;
                String strA = com.tencent.bugly.legu.proguard.a.a(context, "ro.miui.ui.version.name");
                if ((strA == null || strA.trim().length() <= 0) || strA.equals("fail")) {
                    String strA2 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.version.emui");
                    if ((strA2 == null || strA2.trim().length() <= 0) || strA2.equals("fail")) {
                        String strA3 = com.tencent.bugly.legu.proguard.a.a(context, "ro.lenovo.series");
                        if ((strA3 == null || strA3.trim().length() <= 0) || strA3.equals("fail")) {
                            String strA4 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.nubia.rom.name");
                            if ((strA4 == null || strA4.trim().length() <= 0) || strA4.equals("fail")) {
                                String strA5 = com.tencent.bugly.legu.proguard.a.a(context, "ro.meizu.product.model");
                                if ((strA5 == null || strA5.trim().length() <= 0) || strA5.equals("fail")) {
                                    String strA6 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.version.opporom");
                                    if ((strA6 == null || strA6.trim().length() <= 0) || strA6.equals("fail")) {
                                        String strA7 = com.tencent.bugly.legu.proguard.a.a(context, "ro.vivo.os.build.display.id");
                                        if ((strA7 == null || strA7.trim().length() <= 0) || strA7.equals("fail")) {
                                            String strA8 = com.tencent.bugly.legu.proguard.a.a(context, "ro.aa.romver");
                                            if ((strA8 == null || strA8.trim().length() <= 0) || strA8.equals("fail")) {
                                                String strA9 = com.tencent.bugly.legu.proguard.a.a(context, "ro.lewa.version");
                                                if ((strA9 == null || strA9.trim().length() <= 0) || strA9.equals("fail")) {
                                                    String strA10 = com.tencent.bugly.legu.proguard.a.a(context, "ro.gn.gnromvernumber");
                                                    if ((strA10 == null || strA10.trim().length() <= 0) || strA10.equals("fail")) {
                                                        String strA11 = com.tencent.bugly.legu.proguard.a.a(context, "ro.build.tyd.kbstyle_version");
                                                        str2 = ((strA11 == null || strA11.trim().length() <= 0) || strA11.equals("fail")) ? com.tencent.bugly.legu.proguard.a.a(context, "ro.build.fingerprint") + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.rom.id") : "dido/" + strA11;
                                                    } else {
                                                        str2 = "amigo/" + strA10 + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.display.id");
                                                    }
                                                } else {
                                                    str2 = "tcl/" + strA9 + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.display.id");
                                                }
                                            } else {
                                                str2 = "htc/" + strA8 + "/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.description");
                                            }
                                        } else {
                                            str2 = "vivo/FUNTOUCH/" + strA7;
                                        }
                                    } else {
                                        str2 = "Oppo/COLOROS/" + strA6;
                                    }
                                } else {
                                    str2 = "Meizu/FLYME/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.display.id");
                                }
                            } else {
                                str2 = "Zte/NUBIA/" + strA4 + "_" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.nubia.rom.code");
                            }
                        } else {
                            str2 = "Lenovo/VIBE/" + com.tencent.bugly.legu.proguard.a.a(context, "ro.build.version.incremental");
                        }
                    } else {
                        str2 = "HuaWei/EMOTION/" + strA2;
                    }
                } else {
                    str2 = "XiaoMi/MIUI/" + strA;
                }
                this.T = sb.append(str2).toString();
                w.a("rom:%s", this.T);
            }
            str = this.T;
        }
        return str;
    }

    public final synchronized Map<String, String> w() {
        return this.Y.size() <= 0 ? null : new HashMap(this.Y);
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
                w.d("key should not be empty %s", str);
                strRemove = null;
            } else {
                strRemove = this.Y.remove(str);
            }
        }
        return strRemove;
    }

    public final synchronized void x() {
        this.Y.clear();
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
                w.d("key should not be empty %s", str);
                str2 = null;
            } else {
                str2 = this.Y.get(str);
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
                w.d("key&value should not be empty %s %s", str, str2);
            } else {
                if (str2 == null || str2.trim().length() <= 0) {
                    w.d("key&value should not be empty %s %s", str, str2);
                } else {
                    this.Y.put(str, str2);
                }
            }
        }
    }

    public final synchronized int y() {
        return this.Y.size();
    }

    public final synchronized Set<String> z() {
        return this.Y.keySet();
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
                w.d("server key&value should not be empty %s %s", str, str2);
            } else {
                if (str2 == null || str2.trim().length() <= 0) {
                    w.d("server key&value should not be empty %s %s", str, str2);
                } else {
                    this.Z.put(str, str2);
                }
            }
        }
    }

    public final synchronized Map<String, String> A() {
        return this.Z.size() <= 0 ? null : new HashMap(this.Z);
    }

    public final synchronized void a(int i) {
        int i2 = this.W;
        if (i2 != i) {
            this.W = i;
            w.a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.W));
        }
    }

    public final synchronized int B() {
        return this.W;
    }

    public final synchronized int C() {
        return this.X;
    }

    public final synchronized boolean D() {
        return AppInfo.e(this.z);
    }

    public final synchronized Map<String, PlugInBean> E() {
        return null;
    }

    public final synchronized int F() {
        return com.tencent.bugly.legu.proguard.a.d();
    }
}

package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class x {
    private static SimpleDateFormat b;
    private static StringBuilder d;
    private static StringBuilder e;
    private static a f;
    private static String g;
    private static String h;
    private static Context i;
    private static String j;
    private static boolean k;
    private static int l;
    private static Method o;
    public static boolean a = true;
    private static int c = 5120;
    private static Object m = new Object();
    private static Object n = null;

    static {
        b = null;
        o = null;
        try {
            b = new SimpleDateFormat("MM-dd HH:mm:ss");
            o = Class.forName("com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler").getDeclaredMethod("appendLogToNative", String.class, String.class, String.class);
        } catch (Throwable th) {
        }
    }

    private static boolean b(String str, String str2, String str3) {
        if (o == null) {
            return false;
        }
        if (n == null) {
            Object objA = com.tencent.bugly.legu.proguard.a.a("com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler", "getInstance", null, null, null);
            n = objA;
            if (objA == null) {
                return false;
            }
        }
        try {
            return ((Boolean) o.invoke(n, str, str2, str3)).booleanValue();
        } catch (Throwable th) {
            Log.w(w.a, th.getMessage());
            return false;
        }
    }

    public static synchronized void a(Context context) {
        if (!k && context != null && a) {
            try {
                e = new StringBuilder(0);
                d = new StringBuilder(0);
                i = context;
                com.tencent.bugly.legu.crashreport.common.info.a aVarA = com.tencent.bugly.legu.crashreport.common.info.a.a(context);
                g = aVarA.d;
                aVarA.getClass();
                h = "legu";
                j = i.getFilesDir().getPath() + "/buglylog_" + g + "_" + h + ".txt";
                l = Process.myPid();
            } catch (Throwable th) {
            }
            k = true;
        }
    }

    public static void a(int i2) {
        synchronized (m) {
            c = i2;
            if (i2 < 0) {
                c = 0;
            } else if (i2 > 10240) {
                c = 10240;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        String string;
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            StringBuilder sbAppend = new StringBuilder().append(message).append('\n');
            if (th == null) {
                string = "";
            } else {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                printWriter.flush();
                string = stringWriter.toString();
            }
            a(str, str2, sbAppend.append(string).toString());
        }
    }

    public static synchronized void a(String str, String str2, String str3) {
        if (k && a) {
            b(str, str2, str3);
            int iMyTid = Process.myTid();
            d.setLength(0);
            if (str3.length() > 30720) {
                str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
            }
            Date date = new Date();
            d.append(b != null ? b.format(date) : date.toString()).append(" ").append(l).append(" ").append(iMyTid).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
            final String string = d.toString();
            synchronized (m) {
                e.append(string);
            }
            if (e.length() > c) {
                v.a().a(new Runnable() { // from class: com.tencent.bugly.legu.proguard.x.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        synchronized (x.m) {
                            try {
                                if (x.e.length() > x.c) {
                                    if (x.f == null) {
                                        a unused = x.f = new a(x.j);
                                    } else if (x.f.b.length() + ((long) x.e.length()) > x.f.e) {
                                        x.f.a();
                                    }
                                    if (x.f.a) {
                                        x.f.a(x.e.toString());
                                        x.e.setLength(0);
                                    } else {
                                        x.e.setLength(0);
                                        x.e.append(string);
                                    }
                                }
                            } catch (Throwable th) {
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x002c  */
    /* JADX WARN: Code duplicated, block: B:22:0x002e A[Catch: all -> 0x0029, Throwable -> 0x003a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Throwable -> 0x003a, blocks: (B:8:0x000b, B:10:0x000f, B:12:0x0017, B:13:0x001d, B:22:0x002e), top: B:28:0x000b }] */
    public static byte[] a(boolean z) {
        File file;
        byte[] bArrA = null;
        if (a) {
            synchronized (m) {
                if (!z) {
                    file = null;
                    if (e.length() == 0) {
                        bArrA = com.tencent.bugly.legu.proguard.a.a(file, e.toString());
                    } else {
                        bArrA = com.tencent.bugly.legu.proguard.a.a(file, e.toString());
                    }
                } else {
                    try {
                        if (f == null || !f.a) {
                            file = null;
                        } else {
                            file = f.b;
                        }
                        if (e.length() == 0 || file != null) {
                            bArrA = com.tencent.bugly.legu.proguard.a.a(file, e.toString());
                        }
                    } catch (Throwable th) {
                    }
                }
            }
        }
        return bArrA;
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {
        private boolean a;
        private File b;
        private String c;
        private long d;
        private long e = 30720;

        public a(String str) {
            if (str != null && !str.equals("")) {
                this.c = str;
                this.a = a();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x001e, code lost:
        
            r0 = true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized boolean a() {
            /*
                r3 = this;
                r0 = 0
                monitor-enter(r3)
                java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                java.lang.String r2 = r3.c     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                r3.b = r1     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                java.io.File r1 = r3.b     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                if (r1 == 0) goto L20
                java.io.File r1 = r3.b     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                boolean r1 = r1.delete()     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                if (r1 != 0) goto L20
                r1 = 0
                r3.a = r1     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
            L1e:
                monitor-exit(r3)
                return r0
            L20:
                java.io.File r1 = r3.b     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                boolean r1 = r1.createNewFile()     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                if (r1 != 0) goto L30
                r1 = 0
                r3.a = r1     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L32
                goto L1e
            L2c:
                r0 = move-exception
                r0 = 0
                r3.a = r0     // Catch: java.lang.Throwable -> L32
            L30:
                r0 = 1
                goto L1e
            L32:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.legu.proguard.x.a.a():boolean");
        }

        public final synchronized boolean a(String str) {
            boolean z = false;
            synchronized (this) {
                if (this.a) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(this.b, true);
                        byte[] bytes = str.getBytes("UTF-8");
                        fileOutputStream.write(bytes);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        this.d = ((long) bytes.length) + this.d;
                        z = true;
                    } catch (Throwable th) {
                        this.a = false;
                    }
                }
            }
            return z;
        }
    }
}

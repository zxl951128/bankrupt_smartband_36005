package com.tencent.bugly.legu.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class t {
    private static t a = null;
    private final Context c;
    private String g;
    private boolean q;
    private Map<Integer, Long> d = new HashMap();
    private LinkedBlockingQueue<Runnable> e = new LinkedBlockingQueue<>();
    private final Object f = new Object();
    private byte[] h = null;
    private long i = 0;
    private byte[] j = null;
    private long k = 0;
    private String l = null;
    private long m = 0;
    private final Object n = new Object();
    private boolean o = false;
    private final Object p = new Object();
    private final o b = o.a();

    static /* synthetic */ boolean a(t tVar, boolean z) {
        tVar.o = false;
        return false;
    }

    private t(Context context) {
        this.g = null;
        this.q = true;
        this.c = context;
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e) {
            w.a("[UploadManager] can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.q = false;
        }
        if (this.q) {
            StringBuilder sb = new StringBuilder();
            sb.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.g = sb.toString();
        }
    }

    public static synchronized t a(Context context) {
        if (a == null) {
            a = new t(context);
        }
        return a;
    }

    public static synchronized t a() {
        return a;
    }

    public final void a(int i, am amVar, String str, s sVar) {
        a(i, amVar, null, sVar, false, 0L);
    }

    public final void a(int i, int i2, byte[] bArr, String str, s sVar) {
        try {
            if (this.q) {
                a((Runnable) new u(this.c, i, i2, bArr, null, sVar, true), false, 0L);
            } else {
                v.a().b(new u(this.c, i, i2, bArr, null, sVar, false));
            }
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i, am amVar, String str, s sVar, boolean z, long j) {
        try {
            if (this.q) {
                a(new u(this.c, i, amVar, str, sVar, true), z, j);
            } else {
                u uVar = new u(this.c, i, amVar, str, sVar, false);
                if (z) {
                    a(uVar, j);
                } else {
                    v.a().b(uVar);
                }
            }
        } catch (Throwable th) {
            if (!w.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final long b() {
        long j;
        long jC = 0;
        long jO = com.tencent.bugly.legu.proguard.a.o();
        List<q> listA = this.b.a(3);
        if (listA == null || listA.size() <= 0) {
            j = 0;
        } else {
            try {
                q qVar = listA.get(0);
                if (qVar.e >= jO) {
                    jC = com.tencent.bugly.legu.proguard.a.c(qVar.g);
                    listA.remove(qVar);
                }
                j = jC;
            } catch (Throwable th) {
                j = jC;
                w.e("error local type %d", 3);
            }
            if (listA.size() > 0) {
                o oVar = this.b;
                o.a(listA);
            }
        }
        w.c("consume getted %d", Long.valueOf(j));
        return j;
    }

    protected final synchronized void a(long j) {
        q qVar = new q();
        qVar.b = 3;
        qVar.e = com.tencent.bugly.legu.proguard.a.o();
        qVar.c = "";
        qVar.d = "";
        qVar.g = com.tencent.bugly.legu.proguard.a.a(j);
        o oVar = this.b;
        o.b(3);
        this.b.a(qVar);
        w.c("consume update %d", Long.valueOf(j));
    }

    public final synchronized void a(int i, long j) {
        try {
            if (i >= 0) {
                this.d.put(Integer.valueOf(i), Long.valueOf(j));
                w.c("up %d %d", Integer.valueOf(i), Long.valueOf(j));
            } else {
                w.e("unknown up %d", Integer.valueOf(i));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized long a(int i) {
        long jLongValue;
        try {
            if (i >= 0) {
                Long l = this.d.get(Integer.valueOf(i));
                jLongValue = l == null ? -2L : l.longValue();
            } else {
                w.e("unknown up %d", Integer.valueOf(i));
                jLongValue = -2;
            }
        } catch (Throwable th) {
            throw th;
        }
        return jLongValue;
    }

    private static boolean d() {
        boolean zA = false;
        w.c("[UploadManager] drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            o oVarA = o.a();
            if (oVarA == null) {
                w.d("[UploadManager] failed to get Database", new Object[0]);
            } else {
                zA = oVarA.a(555, "security_info", (n) null, true);
            }
        } catch (Throwable th) {
            w.a(th);
        }
        return zA;
    }

    private boolean e() {
        w.c("[UploadManager] record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            o oVarA = o.a();
            if (oVarA == null) {
                w.d("[UploadManager] failed to get Database", new Object[0]);
                return false;
            }
            StringBuilder sb = new StringBuilder();
            if (this.j != null) {
                sb.append(Base64.encodeToString(this.j, 0));
                sb.append("#");
                if (this.m != 0) {
                    sb.append(Long.toString(this.k));
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.l != null) {
                    sb.append(this.l);
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.m != 0) {
                    sb.append(Long.toString(this.m));
                } else {
                    sb.append("null");
                }
                oVarA.a(555, "security_info", sb.toString().getBytes(), (n) null, true);
                return true;
            }
            w.c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            w.a(th);
            d();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        boolean z;
        w.c("[UploadManager] load security info from dataBase (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            o oVarA = o.a();
            if (oVarA == null) {
                w.d("[UploadManager] failed to get Database", new Object[0]);
                return false;
            }
            Map<String, byte[]> mapA = oVarA.a(555, (n) null, true);
            if (mapA != null && mapA.containsKey("security_info")) {
                String str = new String(mapA.get("security_info"));
                String[] strArrSplit = str.split("#");
                if (strArrSplit.length == 4) {
                    if (strArrSplit[0].isEmpty() || strArrSplit[0].equals("null")) {
                        z = false;
                    } else {
                        try {
                            this.j = Base64.decode(strArrSplit[0], 0);
                            z = false;
                        } catch (Throwable th) {
                            w.a(th);
                            z = true;
                        }
                    }
                    if (!z && !strArrSplit[1].isEmpty() && !strArrSplit[1].equals("null")) {
                        try {
                            this.k = Long.parseLong(strArrSplit[1]);
                        } catch (Throwable th2) {
                            w.a(th2);
                            z = true;
                        }
                    }
                    if (!z && !strArrSplit[2].isEmpty() && !strArrSplit[2].equals("null")) {
                        this.l = strArrSplit[2];
                    }
                    if (!z && !strArrSplit[3].isEmpty() && !strArrSplit[3].equals("null")) {
                        try {
                            this.m = Long.parseLong(strArrSplit[3]);
                        } catch (Throwable th3) {
                            w.a(th3);
                            z = true;
                        }
                    }
                } else {
                    w.a("securityInfo = %s, strings.length = %d", str, Integer.valueOf(strArrSplit.length));
                    z = true;
                }
                if (z) {
                    d();
                }
            }
            return true;
        } catch (Throwable th4) {
            w.a(th4);
            return false;
        }
    }

    protected final boolean c() {
        if (this.l == null || this.m == 0) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() + this.i;
        if (this.m >= jCurrentTimeMillis) {
            return true;
        }
        w.c("[UploadManager] session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.m), new Date(this.m).toString(), Long.valueOf(jCurrentTimeMillis), new Date(jCurrentTimeMillis).toString());
        return false;
    }

    protected final void a(boolean z) {
        synchronized (this.n) {
            w.c("[UploadManager] clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.j = null;
            this.l = null;
            this.m = 0L;
        }
        if (z) {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i) {
        if (i < 0) {
            w.a("[UploadManager] number of task to execute should >= 0", new Object[0]);
            return false;
        }
        synchronized (this.f) {
            if (this.e.isEmpty()) {
                return true;
            }
            w.c("[UploadManager] execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(this.e.size()), Integer.valueOf(Process.myPid()), Long.valueOf(Thread.currentThread().getId()));
            if (i == 0 || i > this.e.size()) {
                i = this.e.size();
            }
            v vVarA = v.a();
            if (vVarA == null) {
                w.d("[UploadManager] async task handler has not been initialized", new Object[0]);
                return false;
            }
            for (int i2 = 0; i2 < i; i2++) {
                try {
                    Runnable runnablePoll = this.e.poll();
                    if (runnablePoll != null) {
                        vVarA.c(runnablePoll);
                    } else {
                        w.d("[UploadManager] upload task poll from queue is null", new Object[0]);
                    }
                } catch (Throwable th) {
                    w.e("[UploadManager] failed to execute upload task with message: %s", th.getMessage());
                    return false;
                }
            }
            return true;
        }
    }

    private boolean a(Runnable runnable) {
        if (runnable == null) {
            w.d("[UploadManager] upload task should not be null", new Object[0]);
            return false;
        }
        try {
            w.c("[UploadManager] add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f) {
                try {
                    this.e.put(runnable);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        } catch (Throwable th2) {
            w.e("[UploadManager] failed to add upload task to queue: %s", th2.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Runnable runnable, long j) {
        if (runnable == null) {
            w.d("[UploadManager] upload task should not be null", new Object[0]);
            return;
        }
        w.c("[UploadManager] execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread thread = new Thread(runnable);
        thread.setName("BuglySyncUploadThread");
        thread.start();
        try {
            thread.join(j);
        } catch (Throwable th) {
            w.e("[UploadManager] failed to execute upload synchronized task with message: %s. Add it to queue", th.getMessage());
            a(runnable);
        }
    }

    private void a(Runnable runnable, boolean z, long j) {
        if (runnable == null) {
            w.d("[UploadManager] upload task should not be null", new Object[0]);
        }
        v vVarA = v.a();
        if (vVarA == null) {
            w.d("[UploadManager] async task handler has not been initialized", new Object[0]);
        }
        w.c("[UploadManager] add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.l != null) {
            if (c()) {
                w.c("[UploadManager] sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z) {
                    a(runnable, j);
                } else if (vVarA == null) {
                    a(runnable);
                } else {
                    vVarA.c(runnable);
                }
                b(0);
                return;
            }
            w.a("[UploadManager] session ID is expired, drop it (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            a(false);
        }
        synchronized (this.p) {
            if (!this.o) {
                this.o = true;
                w.c("[UploadManager] try to request session ID now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z) {
                    a(new a(this.c, runnable, j), 0L);
                } else {
                    a(runnable);
                    vVarA.c(new a(this, this.c));
                }
            } else {
                a(runnable);
            }
        }
    }

    public final void a(int i, an anVar) {
        boolean z;
        if (this.q) {
            if (i == 2) {
                w.c("[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                a(true);
            } else if (this.o) {
                if (anVar != null) {
                    w.c("[UploadManager] record security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    try {
                        Map<String, String> map = anVar.h;
                        if (map != null && map.containsKey("S1") && map.containsKey("S2")) {
                            this.i = anVar.e - System.currentTimeMillis();
                            w.c("[UploadManager] time lag of server is: %d", Long.valueOf(this.i));
                            this.l = map.get("S1");
                            w.c("[UploadManager] session ID from server is: %s", this.l);
                            if (this.l.length() > 0) {
                                try {
                                    this.m = Long.parseLong(map.get("S2"));
                                    w.c("[UploadManager] session expired time from server is: %d(%s)", Long.valueOf(this.m), new Date(this.m).toString());
                                    if (this.m < 1000) {
                                        w.d("[UploadManager] session expired time from server is less than 1 second, will set to default value", new Object[0]);
                                        this.m = 259200000L;
                                    }
                                } catch (NumberFormatException e) {
                                    w.d("[UploadManager] session expired time is invalid, will set to default value", new Object[0]);
                                    this.m = 259200000L;
                                }
                                b(0);
                                if (e()) {
                                    z = false;
                                } else {
                                    w.c("[UploadManager] failed to record database", new Object[0]);
                                    z = true;
                                }
                            } else {
                                w.c("[UploadManager] session ID from server is invalid, try next time", new Object[0]);
                                z = true;
                            }
                        } else {
                            z = true;
                        }
                    } catch (Throwable th) {
                        w.a(th);
                        z = true;
                    }
                    if (z) {
                        a(false);
                    }
                } else {
                    w.c("[UploadManager] fail to init security context and clear local info (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    a(false);
                }
            } else {
                return;
            }
            synchronized (this.p) {
                if (this.o) {
                    this.o = false;
                    com.tencent.bugly.legu.proguard.a.b(this.c, "security_info");
                }
            }
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class a implements Runnable {
        private final Context a;
        private final Runnable b;
        private final long c;

        public a(t tVar, Context context) {
            this(context, null, 0L);
        }

        public a(Context context, Runnable runnable, long j) {
            this.a = context;
            this.b = runnable;
            this.c = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (com.tencent.bugly.legu.proguard.a.a(this.a, "security_info", 30000L)) {
                if (!t.this.f()) {
                    w.d("[UploadManager] failed to load security info from database", new Object[0]);
                    t.this.a(false);
                }
                if (t.this.l != null) {
                    if (t.this.c()) {
                        w.c("[UploadManager] sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        if (this.b != null) {
                            t.this.a(this.b, this.c);
                        }
                        t.this.b(0);
                        com.tencent.bugly.legu.proguard.a.b(this.a, "security_info");
                        synchronized (t.this.p) {
                            t.a(t.this, false);
                        }
                        return;
                    }
                    w.a("[UploadManager] session ID is expired, drop it", new Object[0]);
                    t.this.a(true);
                }
                byte[] bArrA = com.tencent.bugly.legu.proguard.a.a(128);
                if (bArrA != null && (bArrA.length << 3) == 128) {
                    t.this.j = bArrA;
                    w.c("[UploadManager] execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    if (this.b != null) {
                        t.this.a(this.b, this.c);
                        return;
                    } else if (!t.this.b(1)) {
                        w.d("[UploadManager] failed to execute one upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    } else {
                        return;
                    }
                } else {
                    w.d("[UploadManager] failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                }
                t.this.a(false);
                com.tencent.bugly.legu.proguard.a.b(this.a, "security_info");
                synchronized (t.this.p) {
                    t.a(t.this, false);
                }
                return;
            }
            v vVarA = v.a();
            if (vVarA == null) {
                w.e("[UploadManager] async task handler has not been initialized", new Object[0]);
                return;
            }
            w.c("[UploadManager] sleep %d try to lock security file again", 5000);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vVarA.c(new a(t.this, t.this.c));
        }
    }

    public final byte[] a(byte[] bArr) {
        if (this.j != null && (this.j.length << 3) == 128) {
            return com.tencent.bugly.legu.proguard.a.a(1, bArr, this.j);
        }
        w.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final byte[] b(byte[] bArr) {
        if (this.j != null && (this.j.length << 3) == 128) {
            return com.tencent.bugly.legu.proguard.a.a(2, bArr, this.j);
        }
        w.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        w.c("[UploadManager] integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.l != null) {
            map.put("secureSessionId", this.l);
            return true;
        }
        if (this.j == null || (this.j.length << 3) != 128) {
            w.d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        }
        if (this.h == null) {
            this.h = Base64.decode(this.g, 0);
            if (this.h == null) {
                w.d("[UploadManager] failed to decode RSA public key", new Object[0]);
                return false;
            }
        }
        byte[] bArrB = com.tencent.bugly.legu.proguard.a.b(1, this.j, this.h);
        if (bArrB == null) {
            w.d("[UploadManager] failed to encrypt AES key", new Object[0]);
            return false;
        }
        String strEncodeToString = Base64.encodeToString(bArrB, 0);
        if (strEncodeToString == null) {
            w.d("[UploadManager] failed to encode AES key", new Object[0]);
            return false;
        }
        map.put("raKey", strEncodeToString);
        return true;
    }
}

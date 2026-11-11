package com.tencent.bugly.lejiagu.proguard;

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
public final class r {
    private static r a = null;
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
    private final m b = m.a();

    static /* synthetic */ boolean a(r rVar, boolean z) {
        rVar.o = false;
        return false;
    }

    private r(Context context) {
        this.g = null;
        this.q = true;
        this.c = context;
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e) {
            u.a("[UploadManager] can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.q = false;
        }
        if (this.q) {
            StringBuilder sb = new StringBuilder();
            sb.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.g = sb.toString();
        }
    }

    public static synchronized r a(Context context) {
        if (a == null) {
            a = new r(context);
        }
        return a;
    }

    public static synchronized r a() {
        return a;
    }

    public final void a(int i, ak akVar, String str, q qVar) {
        a(i, akVar, null, qVar, false, 0L);
    }

    public final void a(int i, int i2, byte[] bArr, String str, q qVar) {
        try {
            if (this.q) {
                a((Runnable) new s(this.c, i, i2, bArr, null, qVar, true), false, 0L);
            } else {
                t.a().b(new s(this.c, i, i2, bArr, null, qVar, false));
            }
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i, ak akVar, String str, q qVar, boolean z, long j) {
        try {
            if (this.q) {
                a(new s(this.c, i, akVar, str, qVar, true), z, j);
            } else {
                s sVar = new s(this.c, i, akVar, str, qVar, false);
                if (z) {
                    a(sVar, j);
                } else {
                    t.a().b(sVar);
                }
            }
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final long b() {
        long j;
        long jC = 0;
        long jO = com.tencent.bugly.lejiagu.proguard.a.o();
        List<o> listA = this.b.a(3);
        if (listA == null || listA.size() <= 0) {
            j = 0;
        } else {
            try {
                o oVar = listA.get(0);
                if (oVar.e >= jO) {
                    jC = com.tencent.bugly.lejiagu.proguard.a.c(oVar.g);
                    listA.remove(oVar);
                }
                j = jC;
            } catch (Throwable th) {
                j = jC;
                u.e("error local type %d", 3);
            }
            if (listA.size() > 0) {
                m mVar = this.b;
                m.a(listA);
            }
        }
        u.c("consume getted %d", Long.valueOf(j));
        return j;
    }

    protected final synchronized void a(long j) {
        o oVar = new o();
        oVar.b = 3;
        oVar.e = com.tencent.bugly.lejiagu.proguard.a.o();
        oVar.c = "";
        oVar.d = "";
        oVar.g = com.tencent.bugly.lejiagu.proguard.a.a(j);
        m mVar = this.b;
        m.b(3);
        this.b.a(oVar);
        u.c("consume update %d", Long.valueOf(j));
    }

    public final synchronized void a(int i, long j) {
        try {
            if (i >= 0) {
                this.d.put(Integer.valueOf(i), Long.valueOf(j));
                u.c("up %d %d", Integer.valueOf(i), Long.valueOf(j));
            } else {
                u.e("unknown up %d", Integer.valueOf(i));
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
                u.e("unknown up %d", Integer.valueOf(i));
                jLongValue = -2;
            }
        } catch (Throwable th) {
            throw th;
        }
        return jLongValue;
    }

    private static boolean d() {
        boolean zA = false;
        u.c("[UploadManager] drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            m mVarA = m.a();
            if (mVarA == null) {
                u.d("[UploadManager] failed to get Database", new Object[0]);
            } else {
                zA = mVarA.a(555, "security_info", (l) null, true);
            }
        } catch (Throwable th) {
            u.a(th);
        }
        return zA;
    }

    private boolean e() {
        u.c("[UploadManager] record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            m mVarA = m.a();
            if (mVarA == null) {
                u.d("[UploadManager] failed to get Database", new Object[0]);
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
                mVarA.a(555, "security_info", sb.toString().getBytes(), (l) null, true);
                return true;
            }
            u.c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            u.a(th);
            d();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        boolean z;
        u.c("[UploadManager] load security info from dataBase (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            m mVarA = m.a();
            if (mVarA == null) {
                u.d("[UploadManager] failed to get Database", new Object[0]);
                return false;
            }
            Map<String, byte[]> mapA = mVarA.a(555, (l) null, true);
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
                            u.a(th);
                            z = true;
                        }
                    }
                    if (!z && !strArrSplit[1].isEmpty() && !strArrSplit[1].equals("null")) {
                        try {
                            this.k = Long.parseLong(strArrSplit[1]);
                        } catch (Throwable th2) {
                            u.a(th2);
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
                            u.a(th3);
                            z = true;
                        }
                    }
                } else {
                    u.a("securityInfo = %s, strings.length = %d", str, Integer.valueOf(strArrSplit.length));
                    z = true;
                }
                if (z) {
                    d();
                }
            }
            return true;
        } catch (Throwable th4) {
            u.a(th4);
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
        u.c("[UploadManager] session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.m), new Date(this.m).toString(), Long.valueOf(jCurrentTimeMillis), new Date(jCurrentTimeMillis).toString());
        return false;
    }

    protected final void a(boolean z) {
        synchronized (this.n) {
            u.c("[UploadManager] clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
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
            u.a("[UploadManager] number of task to execute should >= 0", new Object[0]);
            return false;
        }
        synchronized (this.f) {
            if (this.e.isEmpty()) {
                return true;
            }
            u.c("[UploadManager] execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(this.e.size()), Integer.valueOf(Process.myPid()), Long.valueOf(Thread.currentThread().getId()));
            if (i == 0 || i > this.e.size()) {
                i = this.e.size();
            }
            t tVarA = t.a();
            if (tVarA == null) {
                u.d("[UploadManager] async task handler has not been initialized", new Object[0]);
                return false;
            }
            for (int i2 = 0; i2 < i; i2++) {
                try {
                    Runnable runnablePoll = this.e.poll();
                    if (runnablePoll != null) {
                        tVarA.c(runnablePoll);
                    } else {
                        u.d("[UploadManager] upload task poll from queue is null", new Object[0]);
                    }
                } catch (Throwable th) {
                    u.e("[UploadManager] failed to execute upload task with message: %s", th.getMessage());
                    return false;
                }
            }
            return true;
        }
    }

    private boolean a(Runnable runnable) {
        if (runnable == null) {
            u.d("[UploadManager] upload task should not be null", new Object[0]);
            return false;
        }
        try {
            u.c("[UploadManager] add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f) {
                try {
                    this.e.put(runnable);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        } catch (Throwable th2) {
            u.e("[UploadManager] failed to add upload task to queue: %s", th2.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Runnable runnable, long j) {
        if (runnable == null) {
            u.d("[UploadManager] upload task should not be null", new Object[0]);
            return;
        }
        u.c("[UploadManager] execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread thread = new Thread(runnable);
        thread.setName("BuglySyncUploadThread");
        thread.start();
        try {
            thread.join(j);
        } catch (Throwable th) {
            u.e("[UploadManager] failed to execute upload synchronized task with message: %s. Add it to queue", th.getMessage());
            a(runnable);
        }
    }

    private void a(Runnable runnable, boolean z, long j) {
        if (runnable == null) {
            u.d("[UploadManager] upload task should not be null", new Object[0]);
        }
        t tVarA = t.a();
        if (tVarA == null) {
            u.d("[UploadManager] async task handler has not been initialized", new Object[0]);
        }
        u.c("[UploadManager] add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.l != null) {
            if (c()) {
                u.c("[UploadManager] sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z) {
                    a(runnable, j);
                } else if (tVarA == null) {
                    a(runnable);
                } else {
                    tVarA.c(runnable);
                }
                b(0);
                return;
            }
            u.a("[UploadManager] session ID is expired, drop it (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            a(false);
        }
        synchronized (this.p) {
            if (!this.o) {
                this.o = true;
                u.c("[UploadManager] try to request session ID now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z) {
                    a(new a(this.c, runnable, j), 0L);
                } else {
                    a(runnable);
                    tVarA.c(new a(this, this.c));
                }
            } else {
                a(runnable);
            }
        }
    }

    public final void a(int i, al alVar) {
        boolean z;
        if (this.q) {
            if (i == 2) {
                u.c("[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                a(true);
            } else if (this.o) {
                if (alVar != null) {
                    u.c("[UploadManager] record security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    try {
                        Map<String, String> map = alVar.h;
                        if (map != null && map.containsKey("S1") && map.containsKey("S2")) {
                            this.i = alVar.e - System.currentTimeMillis();
                            u.c("[UploadManager] time lag of server is: %d", Long.valueOf(this.i));
                            this.l = map.get("S1");
                            u.c("[UploadManager] session ID from server is: %s", this.l);
                            if (this.l.length() > 0) {
                                try {
                                    this.m = Long.parseLong(map.get("S2"));
                                    u.c("[UploadManager] session expired time from server is: %d(%s)", Long.valueOf(this.m), new Date(this.m).toString());
                                    if (this.m < 1000) {
                                        u.d("[UploadManager] session expired time from server is less than 1 second, will set to default value", new Object[0]);
                                        this.m = 259200000L;
                                    }
                                } catch (NumberFormatException e) {
                                    u.d("[UploadManager] session expired time is invalid, will set to default value", new Object[0]);
                                    this.m = 259200000L;
                                }
                                b(0);
                                if (e()) {
                                    z = false;
                                } else {
                                    u.c("[UploadManager] failed to record database", new Object[0]);
                                    z = true;
                                }
                            } else {
                                u.c("[UploadManager] session ID from server is invalid, try next time", new Object[0]);
                                z = true;
                            }
                        } else {
                            z = true;
                        }
                    } catch (Throwable th) {
                        u.a(th);
                        z = true;
                    }
                    if (z) {
                        a(false);
                    }
                } else {
                    u.c("[UploadManager] fail to init security context and clear local info (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    a(false);
                }
            } else {
                return;
            }
            synchronized (this.p) {
                if (this.o) {
                    this.o = false;
                    com.tencent.bugly.lejiagu.proguard.a.b(this.c, "security_info");
                }
            }
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class a implements Runnable {
        private final Context a;
        private final Runnable b;
        private final long c;

        public a(r rVar, Context context) {
            this(context, null, 0L);
        }

        public a(Context context, Runnable runnable, long j) {
            this.a = context;
            this.b = runnable;
            this.c = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (com.tencent.bugly.lejiagu.proguard.a.a(this.a, "security_info", 30000L)) {
                if (!r.this.f()) {
                    u.d("[UploadManager] failed to load security info from database", new Object[0]);
                    r.this.a(false);
                }
                if (r.this.l != null) {
                    if (r.this.c()) {
                        u.c("[UploadManager] sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        if (this.b != null) {
                            r.this.a(this.b, this.c);
                        }
                        r.this.b(0);
                        com.tencent.bugly.lejiagu.proguard.a.b(this.a, "security_info");
                        synchronized (r.this.p) {
                            r.a(r.this, false);
                        }
                        return;
                    }
                    u.a("[UploadManager] session ID is expired, drop it", new Object[0]);
                    r.this.a(true);
                }
                byte[] bArrA = com.tencent.bugly.lejiagu.proguard.a.a(128);
                if (bArrA != null && (bArrA.length << 3) == 128) {
                    r.this.j = bArrA;
                    u.c("[UploadManager] execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    if (this.b != null) {
                        r.this.a(this.b, this.c);
                        return;
                    } else if (!r.this.b(1)) {
                        u.d("[UploadManager] failed to execute one upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    } else {
                        return;
                    }
                } else {
                    u.d("[UploadManager] failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                }
                r.this.a(false);
                com.tencent.bugly.lejiagu.proguard.a.b(this.a, "security_info");
                synchronized (r.this.p) {
                    r.a(r.this, false);
                }
                return;
            }
            t tVarA = t.a();
            if (tVarA == null) {
                u.e("[UploadManager] async task handler has not been initialized", new Object[0]);
                return;
            }
            u.c("[UploadManager] sleep %d try to lock security file again", 5000);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tVarA.c(new a(r.this, r.this.c));
        }
    }

    public final byte[] a(byte[] bArr) {
        if (this.j != null && (this.j.length << 3) == 128) {
            return com.tencent.bugly.lejiagu.proguard.a.a(1, bArr, this.j);
        }
        u.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final byte[] b(byte[] bArr) {
        if (this.j != null && (this.j.length << 3) == 128) {
            return com.tencent.bugly.lejiagu.proguard.a.a(2, bArr, this.j);
        }
        u.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    public final boolean a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        u.c("[UploadManager] integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.l != null) {
            map.put("secureSessionId", this.l);
            return true;
        }
        if (this.j == null || (this.j.length << 3) != 128) {
            u.d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        }
        if (this.h == null) {
            this.h = Base64.decode(this.g, 0);
            if (this.h == null) {
                u.d("[UploadManager] failed to decode RSA public key", new Object[0]);
                return false;
            }
        }
        byte[] bArrB = com.tencent.bugly.lejiagu.proguard.a.b(1, this.j, this.h);
        if (bArrB == null) {
            u.d("[UploadManager] failed to encrypt AES key", new Object[0]);
            return false;
        }
        String strEncodeToString = Base64.encodeToString(bArrB, 0);
        if (strEncodeToString == null) {
            u.d("[UploadManager] failed to encode AES key", new Object[0]);
            return false;
        }
        map.put("raKey", strEncodeToString);
        return true;
    }
}

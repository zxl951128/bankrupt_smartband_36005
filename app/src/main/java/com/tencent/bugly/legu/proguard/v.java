package com.tencent.bugly.legu.proguard;

import android.util.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class v {
    private static v a;
    private ScheduledExecutorService b;
    private ThreadPoolExecutor c;
    private ThreadPoolExecutor d;

    protected v() {
        this.b = null;
        this.c = null;
        this.d = null;
        ThreadFactory threadFactory = new ThreadFactory(this) { // from class: com.tencent.bugly.legu.proguard.v.1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BUGLY_THREAD");
                return thread;
            }
        };
        this.b = Executors.newScheduledThreadPool(3, threadFactory);
        this.c = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue(100), threadFactory);
        this.d = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        if (this.b == null || this.b.isShutdown()) {
            w.d("ScheduledExecutorService is not valiable!", new Object[0]);
        }
        if (this.c == null || this.c.isShutdown()) {
            w.d("QueueExecutorService is not valiable!", new Object[0]);
        }
        if (this.d == null || this.d.isShutdown()) {
            w.d("ploadExecutorService is not valiable!", new Object[0]);
        }
    }

    public static synchronized v a() {
        if (a == null) {
            a = new v();
        }
        return a;
    }

    public final synchronized boolean a(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                if (com.tencent.bugly.legu.b.b) {
                    Log.w(w.a, "queue handler was closed , should not post task!");
                }
            } else if (runnable == null) {
                if (com.tencent.bugly.legu.b.b) {
                    Log.w(w.a, "queue task is null");
                }
            } else {
                try {
                    this.c.submit(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (com.tencent.bugly.legu.b.b) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public final synchronized boolean a(Runnable runnable, long j) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                w.d("async handler was closed , should not post task!", new Object[0]);
            } else if (runnable == null) {
                w.d("async task == null", new Object[0]);
            } else {
                if (j <= 0) {
                    j = 0;
                }
                w.c("delay %d task %s", Long.valueOf(j), runnable.getClass().getName());
                try {
                    this.b.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    z = true;
                } catch (Throwable th) {
                    if (com.tencent.bugly.legu.b.b) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public final synchronized boolean b(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                w.d("async handler was closed , should not post task!", new Object[0]);
            } else if (runnable == null) {
                w.d("async task == null", new Object[0]);
            } else {
                w.c("normal task %s", runnable.getClass().getName());
                try {
                    this.b.execute(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (com.tencent.bugly.legu.b.b) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public final synchronized boolean c(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                if (com.tencent.bugly.legu.b.b) {
                    Log.w(w.a, "queue handler was closed , should not post task!");
                }
            } else if (runnable == null) {
                if (com.tencent.bugly.legu.b.b) {
                    Log.w(w.a, "queue task is null");
                }
            } else {
                try {
                    this.d.submit(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (com.tencent.bugly.legu.b.b) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public final synchronized void b() {
        if (this.b != null && !this.b.isShutdown()) {
            w.c("close async handler", new Object[0]);
            this.b.shutdownNow();
        }
        if (this.c != null && !this.c.isShutdown()) {
            w.c("close async queue handler", new Object[0]);
            this.c.shutdownNow();
        }
        if (this.d != null && !this.d.isShutdown()) {
            w.c("close async upload handler", new Object[0]);
            this.d.shutdownNow();
        }
    }

    private synchronized boolean c() {
        return (this.b == null || this.b.isShutdown() || this.c == null || this.c.isShutdown() || this.d == null || this.d.isShutdown()) ? false : true;
    }
}

package com.tencent.bugly.lejiagu;

import android.content.Context;
import com.tencent.bugly.lejiagu.crashreport.BuglyHintException;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class Bugly {
    private static boolean a;
    public static boolean enable = true;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        Context applicationContext;
        if (!a) {
            a = true;
            if (context != null && (applicationContext = context.getApplicationContext()) != null) {
                context = applicationContext;
            }
            if (context == null) {
                throw new BuglyHintException("init arg 'context' should not be null!");
            }
            b.a(context, str, z, buglyStrategy);
        }
    }
}

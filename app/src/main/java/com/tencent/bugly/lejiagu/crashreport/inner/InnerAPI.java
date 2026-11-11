package com.tencent.bugly.lejiagu.crashreport.inner;

import android.content.Context;
import com.tencent.bugly.lejiagu.crashreport.common.info.a;
import com.tencent.bugly.lejiagu.crashreport.crash.d;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class InnerAPI {
    public static Context context;

    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            u.e("post u3d fail args null", new Object[0]);
        }
        u.a("post u3d crash %s %s", str, str2);
        d.a(Thread.currentThread(), str, str2, str3);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            u.e("post cocos2d-x fail args null", new Object[0]);
        } else if (i != 5 && i != 6) {
            u.e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        } else {
            u.a("post cocos2d-x crash %s %s", str, str2);
            d.a(Thread.currentThread(), i, str, str2, str3);
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            u.e("post h5 fail args null", new Object[0]);
        } else {
            u.a("post h5 crash %s %s", str, str2);
            d.a(thread, str, str2, str3, map);
        }
    }

    public static void setOuterSdkVersion(Context context2, String str, String str2) {
        if (context2 == null) {
            u.d("context is null when putsdkdata", new Object[0]);
        }
        if (str == null || str.trim().length() <= 0) {
            return;
        }
        if (!(str2 == null || str2.trim().length() <= 0)) {
            String strReplace = str.replace("[a-zA-Z[0-9]]+", "");
            if (strReplace.length() > 50) {
                u.d("putSdkData key length over limit %d , will drop this new key %s", 50, strReplace);
                return;
            }
            if (str2.length() > 200) {
                u.d("putSdkData value length over limit %d , has been cutted!", 200);
                str2 = str2.substring(0, 200);
            }
            a.a(context2).b("SDK_" + strReplace, str2);
            u.b("[param] putSdkData data: %s - %s", strReplace, str2);
        }
    }
}

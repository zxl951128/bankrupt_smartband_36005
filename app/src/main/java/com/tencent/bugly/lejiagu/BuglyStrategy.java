package com.tencent.bugly.lejiagu;

import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class BuglyStrategy {
    private String a;
    private String b;
    private String c;
    private long d;
    private String e;
    private String f;
    private boolean g = true;
    private boolean h = true;
    private boolean i = true;
    private boolean j = true;
    private boolean k = false;
    private a l;

    public synchronized void setBuglyLogUpload(boolean z) {
        this.j = z;
    }

    public synchronized void setRecordUserInfoOnceADay(boolean z) {
        this.k = z;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.j;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.k;
    }

    public synchronized String getAppVersion() {
        return this.a == null ? com.tencent.bugly.lejiagu.crashreport.common.info.a.a().i : this.a;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.a = str;
        return this;
    }

    public synchronized String getAppChannel() {
        return this.b == null ? com.tencent.bugly.lejiagu.crashreport.common.info.a.a().j : this.b;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.b = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        return this.c == null ? com.tencent.bugly.lejiagu.crashreport.common.info.a.a().c : this.c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.c = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.d;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.d = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.e;
    }

    public synchronized void setLibBuglySOFilePath(String str) {
        this.e = str;
    }

    public synchronized String getDeviceID() {
        return this.f;
    }

    public synchronized void setDeviceID(String str) {
        this.f = str;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.g;
    }

    public synchronized void setEnableNativeCrashMonitor(boolean z) {
        this.g = z;
    }

    public void setEnableUserInfo(boolean z) {
        this.i = z;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.i;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.h;
    }

    public synchronized void setEnableANRCrashMonitor(boolean z) {
        this.h = z;
    }

    public synchronized a getCrashHandleCallback() {
        return this.l;
    }

    public synchronized void setCrashHandleCallback(a aVar) {
        this.l = aVar;
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }
}

package com.tencent.StubShell;

import com.tencent.bugly.legu.crashreport.CrashReport;

/* JADX INFO: loaded from: classes.dex */
class b implements Runnable {
    final /* synthetic */ TxAppEntry a;

    b(TxAppEntry txAppEntry) {
        this.a = txAppEntry;
    }

    @Override // java.lang.Runnable
    public void run() {
        CrashReport.postCatchedException(new SystemInfoException(TxAppEntry.mSrcPath, TxAppEntry.g));
    }
}

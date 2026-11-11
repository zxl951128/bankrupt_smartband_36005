package com.tencent.StubShell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class TxReceiver extends BroadcastReceiver {
    public static String TX_RECIEVER = "com.tencent.StubShell.TxReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        TxAppEntry.a(intent);
    }
}

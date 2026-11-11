package com.tencent.bugly.lejiagu.proguard;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class ad implements ae {
    private String a = null;

    @Override // com.tencent.bugly.lejiagu.proguard.ae
    public final byte[] a(byte[] bArr) throws Exception {
        if (this.a == null || bArr == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.a.getBytes("UTF-8"))), new IvParameterSpec(this.a.getBytes("UTF-8")));
        return cipher.doFinal(bArr);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.ae
    public final void a(String str) {
        if (str != null) {
            this.a = str;
        }
    }
}

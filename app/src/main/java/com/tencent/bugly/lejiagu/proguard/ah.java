package com.tencent.bugly.lejiagu.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class ah extends j implements Cloneable {
    private static byte[] d;
    private byte a;
    private String b;
    private byte[] c;

    public ah() {
        this.a = (byte) 0;
        this.b = "";
        this.c = null;
    }

    public ah(byte b, String str, byte[] bArr) {
        this.a = (byte) 0;
        this.b = "";
        this.c = null;
        this.a = b;
        this.b = str;
        this.c = bArr;
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        if (this.c != null) {
            iVar.a(this.c, 2);
        }
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(h hVar) {
        this.a = hVar.a(this.a, 0, true);
        this.b = hVar.b(1, true);
        if (d == null) {
            d = new byte[]{0};
        }
        byte[] bArr = d;
        this.c = hVar.c(2, false);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(StringBuilder sb, int i) {
    }
}

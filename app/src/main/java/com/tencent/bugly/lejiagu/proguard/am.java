package com.tencent.bugly.lejiagu.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class am extends j implements Cloneable {
    public String a = "";
    private String b = "";

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(h hVar) {
        this.a = hVar.b(0, true);
        this.b = hVar.b(1, true);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(StringBuilder sb, int i) {
    }
}

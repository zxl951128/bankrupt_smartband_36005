package com.tencent.bugly.legu.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class ah extends j implements Cloneable {
    public String a = "";
    public String b = "";
    public String c = "";
    private String e = "";
    public String d = "";

    @Override // com.tencent.bugly.legu.proguard.j
    public final void a(i iVar) {
        iVar.a(this.a, 0);
        if (this.b != null) {
            iVar.a(this.b, 1);
        }
        if (this.c != null) {
            iVar.a(this.c, 2);
        }
        if (this.e != null) {
            iVar.a(this.e, 3);
        }
        if (this.d != null) {
            iVar.a(this.d, 4);
        }
    }

    @Override // com.tencent.bugly.legu.proguard.j
    public final void a(h hVar) {
        this.a = hVar.b(0, true);
        this.b = hVar.b(1, false);
        this.c = hVar.b(2, false);
        this.e = hVar.b(3, false);
        this.d = hVar.b(4, false);
    }

    @Override // com.tencent.bugly.legu.proguard.j
    public final void a(StringBuilder sb, int i) {
    }
}

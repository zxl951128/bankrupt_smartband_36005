package com.tencent.bugly.lejiagu.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class aj extends j implements Cloneable {
    private static ArrayList<ai> b;
    public ArrayList<ai> a = null;

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(i iVar) {
        iVar.a((Collection) this.a, 0);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(h hVar) {
        if (b == null) {
            b = new ArrayList<>();
            b.add(new ai());
        }
        this.a = (ArrayList) hVar.a(b, 0, true);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(StringBuilder sb, int i) {
    }
}

package com.tencent.bugly.lejiagu.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class ap extends j implements Cloneable {
    private static ArrayList<ao> f;
    private static Map<String, String> g;
    public byte a = 0;
    public String b = "";
    public String c = "";
    public ArrayList<ao> d = null;
    public Map<String, String> e = null;

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(i iVar) {
        iVar.a(this.a, 0);
        if (this.b != null) {
            iVar.a(this.b, 1);
        }
        if (this.c != null) {
            iVar.a(this.c, 2);
        }
        if (this.d != null) {
            iVar.a((Collection) this.d, 3);
        }
        if (this.e != null) {
            iVar.a((Map) this.e, 4);
        }
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(h hVar) {
        this.a = hVar.a(this.a, 0, true);
        this.b = hVar.b(1, false);
        this.c = hVar.b(2, false);
        if (f == null) {
            f = new ArrayList<>();
            f.add(new ao());
        }
        this.d = (ArrayList) hVar.a(f, 3, false);
        if (g == null) {
            g = new HashMap();
            g.put("", "");
        }
        this.e = (Map) hVar.a(g, 4, false);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(StringBuilder sb, int i) {
    }
}

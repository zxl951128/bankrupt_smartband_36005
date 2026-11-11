package com.tencent.bugly.lejiagu.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class an extends j implements Cloneable {
    private static am m;
    private static Map<String, String> n;
    private static /* synthetic */ boolean o;
    public boolean a = true;
    public boolean b = true;
    public boolean c = true;
    public String d = "";
    public String e = "";
    public am f = null;
    public Map<String, String> g = null;
    public long h = 0;
    private String j = "";
    private String k = "";
    private int l = 0;
    public int i = 0;

    static {
        o = !an.class.desiredAssertionStatus();
        m = new am();
        n = new HashMap();
        n.put("", "");
    }

    public final boolean equals(Object o2) {
        if (o2 == null) {
            return false;
        }
        an anVar = (an) o2;
        return k.a(this.a, anVar.a) && k.a(this.b, anVar.b) && k.a(this.c, anVar.c) && k.a(this.d, anVar.d) && k.a(this.e, anVar.e) && k.a(this.f, anVar.f) && k.a(this.g, anVar.g) && k.a(this.h, anVar.h) && k.a(this.j, anVar.j) && k.a(this.k, anVar.k) && k.a(this.l, anVar.l) && k.a(this.i, anVar.i);
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            if (o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        iVar.a(this.c, 2);
        if (this.d != null) {
            iVar.a(this.d, 3);
        }
        if (this.e != null) {
            iVar.a(this.e, 4);
        }
        if (this.f != null) {
            iVar.a((j) this.f, 5);
        }
        if (this.g != null) {
            iVar.a((Map) this.g, 6);
        }
        iVar.a(this.h, 7);
        if (this.j != null) {
            iVar.a(this.j, 8);
        }
        if (this.k != null) {
            iVar.a(this.k, 9);
        }
        iVar.a(this.l, 10);
        iVar.a(this.i, 11);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(h hVar) {
        boolean z = this.a;
        this.a = hVar.a(0, true);
        boolean z2 = this.b;
        this.b = hVar.a(1, true);
        boolean z3 = this.c;
        this.c = hVar.a(2, true);
        this.d = hVar.b(3, false);
        this.e = hVar.b(4, false);
        this.f = (am) hVar.a((j) m, 5, false);
        this.g = (Map) hVar.a(n, 6, false);
        this.h = hVar.a(this.h, 7, false);
        this.j = hVar.b(8, false);
        this.k = hVar.b(9, false);
        this.l = hVar.a(this.l, 10, false);
        this.i = hVar.a(this.i, 11, false);
    }

    @Override // com.tencent.bugly.lejiagu.proguard.j
    public final void a(StringBuilder sb, int i) {
        com.tencent.bugly.lejiagu.crashreport.crash.jni.b bVar = new com.tencent.bugly.lejiagu.crashreport.crash.jni.b(sb, i);
        bVar.a(this.a, "enable");
        bVar.a(this.b, "enableUserInfo");
        bVar.a(this.c, "enableQuery");
        bVar.a(this.d, "url");
        bVar.a(this.e, "expUrl");
        bVar.a((j) this.f, "security");
        bVar.a((Map) this.g, "valueMap");
        bVar.a(this.h, "strategylastUpdateTime");
        bVar.a(this.j, "httpsUrl");
        bVar.a(this.k, "httpsExpUrl");
        bVar.a(this.l, "eventRecordCount");
        bVar.a(this.i, "eventTimeInterval");
    }
}

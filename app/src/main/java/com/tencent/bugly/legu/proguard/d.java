package com.tencent.bugly.legu.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class d extends c {
    private static HashMap<String, byte[]> f = null;
    private static HashMap<String, HashMap<String, byte[]>> g = null;
    private f e = new f();

    public d() {
        this.e.a = (short) 2;
    }

    @Override // com.tencent.bugly.legu.proguard.c, com.tencent.bugly.legu.proguard.a
    public final <T> void a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.a(str, t);
    }

    @Override // com.tencent.bugly.legu.proguard.c
    public final void p() {
        super.p();
        this.e.a = (short) 3;
    }

    @Override // com.tencent.bugly.legu.proguard.c, com.tencent.bugly.legu.proguard.a
    public final byte[] a() {
        if (this.e.a == 2) {
            if (this.e.c.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.e.d.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this.e.c == null) {
                this.e.c = "";
            }
            if (this.e.d == null) {
                this.e.d = "";
            }
        }
        i iVar = new i(0);
        iVar.a(this.b);
        if (this.e.a == 2) {
            iVar.a((Map) this.a, 0);
        } else {
            iVar.a((Map) this.d, 0);
        }
        this.e.e = k.a(iVar.a());
        i iVar2 = new i(0);
        iVar2.a(this.b);
        this.e.a(iVar2);
        byte[] bArrA = k.a(iVar2.a());
        int length = bArrA.length;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length + 4);
        byteBufferAllocate.putInt(length + 4).put(bArrA).flip();
        return byteBufferAllocate.array();
    }

    @Override // com.tencent.bugly.legu.proguard.c, com.tencent.bugly.legu.proguard.a
    public final void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            h hVar = new h(bArr, 4);
            hVar.a(this.b);
            this.e.a(hVar);
            if (this.e.a == 3) {
                h hVar2 = new h(this.e.e);
                hVar2.a(this.b);
                if (f == null) {
                    HashMap<String, byte[]> map = new HashMap<>();
                    f = map;
                    map.put("", new byte[0]);
                }
                this.d = hVar2.a((Map) f, 0, false);
                return;
            }
            h hVar3 = new h(this.e.e);
            hVar3.a(this.b);
            if (g == null) {
                g = new HashMap<>();
                HashMap<String, byte[]> map2 = new HashMap<>();
                map2.put("", new byte[0]);
                g.put("", map2);
            }
            this.a = hVar3.a((Map) g, 0, false);
            new HashMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final void b(String str) {
        this.e.c = str;
    }

    public final void c(String str) {
        this.e.d = str;
    }

    public final void b(int i) {
        this.e.b = 1;
    }
}

package com.tencent.bugly.legu.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class al extends j implements Cloneable {
    private static ArrayList<ak> b;
    public ArrayList<ak> a = null;

    @Override // com.tencent.bugly.legu.proguard.j
    public final void a(i iVar) {
        iVar.a((Collection) this.a, 0);
    }

    @Override // com.tencent.bugly.legu.proguard.j
    public final void a(h hVar) {
        if (b == null) {
            b = new ArrayList<>();
            b.add(new ak());
        }
        this.a = (ArrayList) hVar.a(b, 0, true);
    }

    @Override // com.tencent.bugly.legu.proguard.j
    public final void a(StringBuilder sb, int i) {
    }
}

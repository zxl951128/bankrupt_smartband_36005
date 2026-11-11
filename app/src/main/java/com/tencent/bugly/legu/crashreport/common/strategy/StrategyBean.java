package com.tencent.bugly.legu.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class StrategyBean implements Parcelable {
    public static String a;
    public long b;
    public long c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public long l;
    public long m;
    public String n;
    public String o;
    public String p;
    public String q;
    public Map<String, String> r;
    public int s;
    public long t;
    private static String u = "http://android.bugly.qq.com/rqd/async";
    private static String v = "http://android.bugly.qq.com/rqd/async";
    private static String w = "http://rqd.uu.qq.com/rqd/sync";
    public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator<StrategyBean>() { // from class: com.tencent.bugly.legu.crashreport.common.strategy.StrategyBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ StrategyBean createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ StrategyBean[] newArray(int i) {
            return new StrategyBean[i];
        }
    };

    public StrategyBean() {
        this.b = -1L;
        this.c = -1L;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = 30000L;
        this.n = u;
        this.o = v;
        this.p = w;
        this.s = 10;
        this.t = 300000L;
        this.c = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L").append("@)");
        a = sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K").append("@!");
        this.q = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        this.b = -1L;
        this.c = -1L;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = 30000L;
        this.n = u;
        this.o = v;
        this.p = w;
        this.s = 10;
        this.t = 300000L;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("S(@L@L").append("@)");
            a = sb.toString();
            this.c = parcel.readLong();
            this.d = parcel.readByte() == 1;
            this.e = parcel.readByte() == 1;
            this.f = parcel.readByte() == 1;
            this.n = parcel.readString();
            this.o = parcel.readString();
            this.q = parcel.readString();
            this.r = com.tencent.bugly.legu.proguard.a.b(parcel);
            this.g = parcel.readByte() == 1;
            this.j = parcel.readByte() == 1;
            this.k = parcel.readByte() == 1;
            this.m = parcel.readLong();
            this.h = parcel.readByte() == 1;
            this.i = parcel.readByte() == 1;
            this.l = parcel.readLong();
            this.s = parcel.readInt();
            this.t = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        parcel.writeByte((byte) (this.e ? 1 : 0));
        parcel.writeByte((byte) (this.f ? 1 : 0));
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.q);
        com.tencent.bugly.legu.proguard.a.b(parcel, this.r);
        parcel.writeByte((byte) (this.g ? 1 : 0));
        parcel.writeByte((byte) (this.j ? 1 : 0));
        parcel.writeByte((byte) (this.k ? 1 : 0));
        parcel.writeLong(this.m);
        parcel.writeByte((byte) (this.h ? 1 : 0));
        parcel.writeByte((byte) (this.i ? 1 : 0));
        parcel.writeLong(this.l);
        parcel.writeInt(this.s);
        parcel.writeLong(this.t);
    }
}

package com.tencent.bugly.lejiagu.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.lejiagu.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.lejiagu.proguard.j;
import com.tencent.bugly.lejiagu.proguard.u;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class b {
    private StringBuilder a;
    private int b;

    private void d(String str) {
        for (int i = 0; i < this.b; i++) {
            this.a.append('\t');
        }
        if (str != null) {
            this.a.append(str).append(": ");
        }
    }

    public b(StringBuilder sb, int i) {
        this.b = 0;
        this.a = sb;
        this.b = i;
    }

    private static Map<String, Integer> c(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap map = new HashMap();
            for (String str2 : str.split(",")) {
                String[] strArrSplit = str2.split(":");
                if (strArrSplit.length != 2) {
                    u.e("error format at %s", str2);
                    return null;
                }
                map.put(strArrSplit[0], Integer.valueOf(Integer.parseInt(strArrSplit[1])));
            }
            return map;
        } catch (Exception e) {
            u.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    public b a(boolean z, String str) {
        d(str);
        this.a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    public b a(byte b, String str) {
        d(str);
        this.a.append((int) b).append('\n');
        return this;
    }

    public b a(char c, String str) {
        d(str);
        this.a.append(c).append('\n');
        return this;
    }

    public b a(short s, String str) {
        d(str);
        this.a.append((int) s).append('\n');
        return this;
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split("\n");
        if (strArrSplit != null && strArrSplit.length != 0) {
            StringBuilder sb = new StringBuilder();
            for (String str2 : strArrSplit) {
                if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                    sb.append(str2).append("\n");
                }
            }
            return sb.toString();
        }
        return str;
    }

    public b a(int i, String str) {
        d(str);
        this.a.append(i).append('\n');
        return this;
    }

    public b a(long j, String str) {
        d(str);
        this.a.append(j).append('\n');
        return this;
    }

    public b a(float f, String str) {
        d(str);
        this.a.append(f).append('\n');
        return this;
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context) == null) {
            u.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            u.e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> mapC = c(str);
        if (mapC == null) {
            u.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            int iIntValue = mapC.get("ep").intValue();
            int iIntValue2 = mapC.get("et").intValue();
            mapC.get("sino").intValue();
            int iIntValue3 = mapC.get("sico").intValue();
            mapC.get("spd").intValue();
            mapC.get("sud").intValue();
            long jIntValue = mapC.get("ets").intValue();
            long jIntValue2 = mapC.get("etms").intValue();
            String str2 = map.get("soVersion");
            if (str2 == null) {
                u.e("error format at version", new Object[0]);
                return null;
            }
            String str3 = map.get("errorAddr");
            String str4 = str3 == null ? "unknown2" : str3;
            String str5 = map.get("codeMsg");
            String str6 = str5 == null ? "unknown2" : str5;
            String str7 = map.get("tombPath");
            String str8 = str7 == null ? "unknown2" : str7;
            String str9 = map.get("signalName");
            String str10 = str9 == null ? "unknown2" : str9;
            map.get("errnoMsg");
            String str11 = map.get("stack");
            String str12 = str11 == null ? "unknown2" : str11;
            String str13 = map.get("jstack");
            long j = (jIntValue * 1000) + (jIntValue2 / 1000);
            String strA = a(str13 != null ? str12 + "java:\n" + str13 : str12);
            String str14 = "UNKNOWN(" + iIntValue + ")";
            if (iIntValue3 > 0) {
                str10 = str10 + "(" + str6 + ")";
                str6 = "KERNEL";
            }
            String str15 = map.get("nativeLog");
            byte[] bArrA = null;
            if (str15 != null && !str15.isEmpty()) {
                bArrA = com.tencent.bugly.lejiagu.proguard.a.a((File) null, str15);
            }
            String str16 = map.get("processName");
            String str17 = str16 == null ? "unknown(" + iIntValue + ")" : str16;
            String str18 = map.get("threadName");
            String str19 = str18 == null ? "unknown(" + iIntValue2 + ")" : str18;
            HashMap map2 = null;
            String str20 = map.get("key-value");
            if (str20 != null) {
                map2 = new HashMap();
                String[] strArrSplit = str20.split("\n");
                for (String str21 : strArrSplit) {
                    String[] strArrSplit2 = str21.split("=");
                    if (strArrSplit2.length == 2) {
                        map2.put(strArrSplit2[0], strArrSplit2[1]);
                    }
                }
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = nativeExceptionHandler.packageCrashDatas(str17, str19, j, str10, str4, strA, str6, str14, str8, str2, bArrA, map2, false);
            if (crashDetailBeanPackageCrashDatas != null) {
                crashDetailBeanPackageCrashDatas.y = null;
                crashDetailBeanPackageCrashDatas.k = true;
                return crashDetailBeanPackageCrashDatas;
            }
            return crashDetailBeanPackageCrashDatas;
        } catch (Throwable th) {
            u.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    public b a(double d, String str) {
        d(str);
        this.a.append(d).append('\n');
        return this;
    }

    public b a(String str, String str2) {
        d(str2);
        if (str == null) {
            this.a.append("null\n");
        } else {
            this.a.append(str).append('\n');
        }
        return this;
    }

    public b a(byte[] bArr, String str) {
        d(str);
        if (bArr == null) {
            this.a.append("null\n");
        } else if (bArr.length == 0) {
            this.a.append(bArr.length).append(", []\n");
        } else {
            this.a.append(bArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (byte b : bArr) {
                bVar.a(b, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(short[] sArr, String str) {
        d(str);
        if (sArr == null) {
            this.a.append("null\n");
        } else if (sArr.length == 0) {
            this.a.append(sArr.length).append(", []\n");
        } else {
            this.a.append(sArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (short s : sArr) {
                bVar.a(s, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(int[] iArr, String str) {
        d(str);
        if (iArr == null) {
            this.a.append("null\n");
        } else if (iArr.length == 0) {
            this.a.append(iArr.length).append(", []\n");
        } else {
            this.a.append(iArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (int i : iArr) {
                bVar.a(i, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(long[] jArr, String str) {
        d(str);
        if (jArr == null) {
            this.a.append("null\n");
        } else if (jArr.length == 0) {
            this.a.append(jArr.length).append(", []\n");
        } else {
            this.a.append(jArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (long j : jArr) {
                bVar.a(j, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    public b a(float[] fArr, String str) {
        d(str);
        if (fArr == null) {
            this.a.append("null\n");
        } else if (fArr.length == 0) {
            this.a.append(fArr.length).append(", []\n");
        } else {
            this.a.append(fArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (float f : fArr) {
                bVar.a(f, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    private static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = inputStream.read();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return sb.toString();
            }
            sb.append((char) i);
        }
    }

    public b a(double[] dArr, String str) {
        d(str);
        if (dArr == null) {
            this.a.append("null\n");
        } else if (dArr.length == 0) {
            this.a.append(dArr.length).append(", []\n");
        } else {
            this.a.append(dArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (double d : dArr) {
                bVar.a(d, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v6 */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) throws Throwable {
        FileInputStream fileInputStream;
        CrashDetailBean crashDetailBeanA = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            u.e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists()) {
                ?? CanRead = file.canRead();
                try {
                    if (CanRead != 0) {
                        try {
                            fileInputStream = new FileInputStream(file);
                            try {
                                String strA = a(fileInputStream);
                                if (strA == null || !strA.equals("NATIVE_RQD_REPORT")) {
                                    u.e("record read fail! %s", strA);
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    HashMap map = new HashMap();
                                    String str2 = null;
                                    while (true) {
                                        String strA2 = a(fileInputStream);
                                        if (strA2 == null) {
                                            break;
                                        }
                                        if (str2 == null) {
                                            str2 = strA2;
                                        } else {
                                            map.put(str2, strA2);
                                            str2 = null;
                                        }
                                    }
                                    if (str2 != null) {
                                        u.e("record not pair! drop! %s", str2);
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    } else {
                                        crashDetailBeanA = a(context, map, nativeExceptionHandler);
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                }
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                            }
                        } catch (IOException e6) {
                            e = e6;
                            fileInputStream = null;
                        } catch (Throwable th) {
                            CanRead = 0;
                            th = th;
                            if (CanRead != 0) {
                                try {
                                    CanRead.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        return crashDetailBeanA;
    }

    public <K, V> b a(Map<K, V> map, String str) {
        d(str);
        if (map == null) {
            this.a.append("null\n");
        } else if (map.isEmpty()) {
            this.a.append(map.size()).append(", {}\n");
        } else {
            this.a.append(map.size()).append(", {\n");
            b bVar = new b(this.a, this.b + 1);
            b bVar2 = new b(this.a, this.b + 2);
            for (Map.Entry<K, V> entry : map.entrySet()) {
                bVar.a('(', (String) null);
                bVar2.a(entry.getKey(), (String) null);
                bVar2.a(entry.getValue(), (String) null);
                bVar.a(')', (String) null);
            }
            a('}', (String) null);
        }
        return this;
    }

    public <T> b a(T[] tArr, String str) {
        d(str);
        if (tArr == null) {
            this.a.append("null\n");
        } else if (tArr.length == 0) {
            this.a.append(tArr.length).append(", []\n");
        } else {
            this.a.append(tArr.length).append(", [\n");
            b bVar = new b(this.a, this.b + 1);
            for (T t : tArr) {
                bVar.a(t, (String) null);
            }
            a(']', (String) null);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> b a(Collection<T> collection, String str) {
        if (collection != null) {
            return a(collection.toArray(), str);
        }
        d(str);
        this.a.append("null\t");
        return this;
    }

    public static void b(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            u.c("delete record file %s", file.getAbsoluteFile());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> b a(T t, String str) {
        if (t == 0) {
            this.a.append("null\n");
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((Collection) t, str);
        } else if (t instanceof j) {
            a((j) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            a((Object[]) t, str);
        } else {
            throw new com.tencent.bugly.lejiagu.proguard.b("write object error: unsupport type.");
        }
        return this;
    }

    public b a(j jVar, String str) {
        a('{', str);
        if (jVar == null) {
            this.a.append('\t').append("null");
        } else {
            jVar.a(this.a, this.b + 1);
        }
        a('}', (String) null);
        return this;
    }
}

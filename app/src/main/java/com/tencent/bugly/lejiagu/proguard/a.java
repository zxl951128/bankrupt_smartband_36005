package com.tencent.bugly.lejiagu.proguard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.tencent.bugly.lejiagu.crashreport.biz.UserInfoBean;
import com.tencent.bugly.lejiagu.crashreport.common.info.AppInfo;
import com.tencent.bugly.lejiagu.crashreport.common.info.PlugInBean;
import com.tencent.bugly.lejiagu.crashreport.common.strategy.StrategyBean;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class a {
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    h c;
    private HashMap<String, Object> d;

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new h();
    }

    public static String b() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static ao a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        ao aoVar = new ao();
        aoVar.a = userInfoBean.e;
        aoVar.e = userInfoBean.j;
        aoVar.d = userInfoBean.c;
        aoVar.c = userInfoBean.d;
        aoVar.g = com.tencent.bugly.lejiagu.crashreport.common.info.a.a().h();
        aoVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case 1:
                aoVar.b = (byte) 1;
                break;
            case 2:
                aoVar.b = (byte) 4;
                break;
            case 3:
                aoVar.b = (byte) 2;
                break;
            case 4:
                aoVar.b = (byte) 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    aoVar.b = (byte) userInfoBean.b;
                } else {
                    u.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                    return null;
                }
                break;
        }
        aoVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            aoVar.f.put("C01", new StringBuilder().append(userInfoBean.p).toString());
        }
        if (userInfoBean.q >= 0) {
            aoVar.f.put("C02", new StringBuilder().append(userInfoBean.q).toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.r.entrySet()) {
                aoVar.f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.s.entrySet()) {
                aoVar.f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        aoVar.f.put("A36", new StringBuilder().append(!userInfoBean.l).toString());
        aoVar.f.put("F02", new StringBuilder().append(userInfoBean.g).toString());
        aoVar.f.put("F03", new StringBuilder().append(userInfoBean.h).toString());
        aoVar.f.put("F04", userInfoBean.j);
        aoVar.f.put("F05", new StringBuilder().append(userInfoBean.i).toString());
        aoVar.f.put("F06", userInfoBean.m);
        aoVar.f.put("F10", new StringBuilder().append(userInfoBean.k).toString());
        u.c("summary type %d vm:%d", Byte.valueOf(aoVar.b), Integer.valueOf(aoVar.f.size()));
        return aoVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public static String c() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static int d() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!u.a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    public static String a(Context context) {
        String deviceId;
        if (!AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            u.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return "null";
        }
        if (context == null) {
            return null;
        }
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (deviceId != null) {
                try {
                    return deviceId.toLowerCase();
                } catch (Throwable th) {
                    Log.i(u.b, "failed to get IMEI");
                    return deviceId;
                }
            }
            return deviceId;
        } catch (Throwable th2) {
            deviceId = null;
        }
    }

    public static String n() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals("long")) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else {
                if (str.equals("java.lang.Character")) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                }
                if (str.equals("java.lang.String")) {
                    str = "string";
                } else if (str.equals("java.util.List")) {
                    str = "list";
                } else if (str.equals("java.util.Map")) {
                    str = "map";
                }
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = arrayList.get(i2);
            if (str2.equals("list")) {
                arrayList.set(i2 - 1, "<" + arrayList.get(i2 - 1));
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("map")) {
                arrayList.set(i2 - 1, "<" + arrayList.get(i2 - 1) + ",");
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("Array")) {
                arrayList.set(i2 - 1, "<" + arrayList.get(i2 - 1));
                arrayList.set(0, arrayList.get(0) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        i iVar = new i();
        iVar.a(this.b);
        iVar.a(t, 0);
        byte[] bArrA = k.a(iVar.a());
        HashMap<String, byte[]> map = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        a(arrayList, t);
        map.put(a(arrayList), bArrA);
        this.d.remove(str);
        this.a.put(str, map);
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    public static String b(Context context) {
        String subscriberId;
        if (!AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            u.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return "null";
        }
        if (context == null) {
            return null;
        }
        try {
            subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (subscriberId != null) {
                try {
                    return subscriberId.toLowerCase();
                } catch (Throwable th) {
                    Log.i(u.b, "failed to get IMSI");
                    return subscriberId;
                }
            }
            return subscriberId;
        } catch (Throwable th2) {
            subscriberId = null;
        }
    }

    public static String c(Context context) {
        String str = "fail";
        if (context == null) {
            return "fail";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "null";
            }
            try {
                return string.toLowerCase();
            } catch (Throwable th) {
                str = string;
                th = th;
                if (!u.a(th)) {
                    Log.i(u.b, "failed to get Android ID");
                    return str;
                }
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static ap a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a();
        ap apVar = new ap();
        apVar.b = aVarA.d;
        apVar.c = aVarA.g();
        ArrayList<ao> arrayList = new ArrayList<>();
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            ao aoVarA = a(it.next());
            if (aoVarA != null) {
                arrayList.add(aoVarA);
            }
        }
        apVar.d = arrayList;
        apVar.e = new HashMap();
        apVar.e.put("A7", aVarA.e);
        apVar.e.put("A6", aVarA.r());
        apVar.e.put("A5", aVarA.q());
        apVar.e.put("A2", new StringBuilder().append(aVarA.o()).toString());
        apVar.e.put("A1", new StringBuilder().append(aVarA.o()).toString());
        apVar.e.put("A24", aVarA.g);
        apVar.e.put("A17", new StringBuilder().append(aVarA.p()).toString());
        apVar.e.put("A15", aVarA.t());
        apVar.e.put("A13", new StringBuilder().append(aVarA.u()).toString());
        apVar.e.put("F08", aVarA.u);
        apVar.e.put("F09", aVarA.v);
        Map<String, String> mapZ = aVarA.z();
        if (mapZ != null && mapZ.size() > 0) {
            for (Map.Entry<String, String> entry : mapZ.entrySet()) {
                apVar.e.put("C04_" + entry.getKey(), entry.getValue());
            }
        }
        switch (i) {
            case 1:
                apVar.a = (byte) 1;
                break;
            case 2:
                apVar.a = (byte) 2;
                break;
            default:
                u.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return apVar;
    }

    public static byte[] a(byte[] bArr, int i, String str) {
        ae acVar;
        if (bArr != null && i != -1) {
            try {
                if (i == 1) {
                    acVar = new ad();
                } else {
                    acVar = i == 3 ? new ac() : null;
                }
                if (acVar == null) {
                    return null;
                }
                acVar.a(str);
                return acVar.a(bArr);
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                u.d("encrytype %d %s", Integer.valueOf(i), str);
                return null;
            }
        }
        return bArr;
    }

    public static String d(Context context) {
        String macAddress = "fail";
        if (context == null) {
            return "fail";
        }
        try {
            macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null) {
                return "null";
            }
            return macAddress.toLowerCase();
        } catch (Throwable th) {
            String str = macAddress;
            if (!u.a(th)) {
                th.printStackTrace();
                return str;
            }
            return str;
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x004b A[Catch: Throwable -> 0x0056, all -> 0x007d, LOOP:0: B:15:0x004b->B:17:0x0051, LOOP_START, TryCatch #10 {all -> 0x007d, blocks: (B:13:0x003a, B:15:0x004b, B:17:0x0051, B:29:0x0072, B:31:0x0078, B:40:0x0090, B:20:0x0057, B:22:0x005d), top: B:80:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:17:0x0051 A[Catch: Throwable -> 0x0056, all -> 0x007d, LOOP:0: B:15:0x004b->B:17:0x0051, LOOP_END, TRY_LEAVE, TryCatch #10 {all -> 0x007d, blocks: (B:13:0x003a, B:15:0x004b, B:17:0x0051, B:29:0x0072, B:31:0x0078, B:40:0x0090, B:20:0x0057, B:22:0x005d), top: B:80:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:22:0x005d A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #10 {all -> 0x007d, blocks: (B:13:0x003a, B:15:0x004b, B:17:0x0051, B:29:0x0072, B:31:0x0078, B:40:0x0090, B:20:0x0057, B:22:0x005d), top: B:80:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0078 A[Catch: Throwable -> 0x0056, all -> 0x007d, LOOP:1: B:29:0x0072->B:31:0x0078, LOOP_END, TRY_LEAVE, TryCatch #10 {all -> 0x007d, blocks: (B:13:0x003a, B:15:0x004b, B:17:0x0051, B:29:0x0072, B:31:0x0078, B:40:0x0090, B:20:0x0057, B:22:0x005d), top: B:80:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:69:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:70:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0072 A[EDGE_INSN: B:91:0x0072->B:93:? BREAK  A[LOOP:0: B:15:0x004b->B:17:0x0051], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x0090 A[EDGE_INSN: B:92:0x0090->B:40:0x0090 BREAK  A[LOOP:1: B:29:0x0072->B:31:0x0078], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static byte[] a(File file, String str) throws Throwable {
        FileInputStream fileInputStream;
        ZipOutputStream zipOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        byte[] bArr;
        int i;
        int i2;
        byte[] byteArray = null;
        ?? r2 = new Object[0];
        u.c("rqdp{  ZF start}", r2);
        String name = "buglyCacheLog.txt";
        try {
            if (file != null) {
                try {
                    if (file.exists() && file.canRead()) {
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        try {
                            name = file.getName();
                            fileInputStream = fileInputStream2;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            zipOutputStream = null;
                            if (!u.a(th)) {
                                th.printStackTrace();
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (zipOutputStream != null) {
                                try {
                                    zipOutputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            u.c("rqdp{  ZF end}", new Object[0]);
                            return byteArray;
                        }
                    } else {
                        fileInputStream = null;
                    }
                    try {
                        byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                        try {
                            zipOutputStream.setMethod(8);
                            zipOutputStream.putNextEntry(new ZipEntry(name));
                            bArr = new byte[1024];
                            if (fileInputStream != null) {
                                while (true) {
                                    i2 = fileInputStream.read(bArr);
                                    if (i2 > 0) {
                                        break;
                                    }
                                    zipOutputStream.write(bArr, 0, i2);
                                }
                            }
                            while (true) {
                                i = byteArrayInputStream.read(bArr);
                                if (i > 0) {
                                    break;
                                }
                                zipOutputStream.write(bArr, 0, i);
                            }
                            zipOutputStream.closeEntry();
                            zipOutputStream.flush();
                            zipOutputStream.finish();
                            byteArray = byteArrayOutputStream.toByteArray();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            try {
                                zipOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            u.c("rqdp{  ZF end}", new Object[0]);
                        } catch (Throwable th2) {
                            th = th2;
                            if (!u.a(th)) {
                                th.printStackTrace();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (zipOutputStream != null) {
                                zipOutputStream.close();
                            }
                            u.c("rqdp{  ZF end}", new Object[0]);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        zipOutputStream = null;
                    }
                } catch (Throwable th4) {
                    r2 = 0;
                    fileInputStream = null;
                    th = th4;
                }
            } else {
                fileInputStream = null;
                byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                zipOutputStream = new ZipOutputStream(byteArrayOutputStream2);
                zipOutputStream.setMethod(8);
                zipOutputStream.putNextEntry(new ZipEntry(name));
                bArr = new byte[1024];
                if (fileInputStream != null) {
                    while (true) {
                        i2 = fileInputStream.read(bArr);
                        if (i2 > 0) {
                            break;
                            break;
                        }
                        zipOutputStream.write(bArr, 0, i2);
                    }
                }
                while (true) {
                    i = byteArrayInputStream.read(bArr);
                    if (i > 0) {
                        break;
                        break;
                    }
                    zipOutputStream.write(bArr, 0, i);
                }
                zipOutputStream.closeEntry();
                zipOutputStream.flush();
                zipOutputStream.finish();
                byteArray = byteArrayOutputStream2.toByteArray();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                zipOutputStream.close();
                u.c("rqdp{  ZF end}", new Object[0]);
            }
            return byteArray;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    /* JADX WARN: Code duplicated, block: B:78:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x009d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private static String p() throws Throwable {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String str;
        try {
            fileReader = new FileReader("/system/build.prop");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                while (true) {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                str = null;
                                break;
                            }
                            String[] strArrSplit = line.split("=", 2);
                            if (strArrSplit.length == 2) {
                                if (strArrSplit[0].equals("ro.product.cpu.abilist")) {
                                    str = strArrSplit[1];
                                    break;
                                }
                                if (strArrSplit[0].equals("ro.product.cpu.abi")) {
                                    str = strArrSplit[1];
                                    break;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e) {
                                    if (!u.a(e)) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (fileReader != null) {
                                try {
                                    fileReader.close();
                                } catch (IOException e2) {
                                    if (!u.a(e2)) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                if (!u.a(e3)) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e4) {
                                if (!u.a(e4)) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                        return null;
                    }
                }
                if (str != null) {
                    str = str.split(",")[0];
                }
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    if (!u.a(e5)) {
                        e5.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                    return str;
                } catch (IOException e6) {
                    if (!u.a(e6)) {
                        e6.printStackTrace();
                        return str;
                    }
                    return str;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
        }
    }

    public static <T extends j> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T tNewInstance = cls.newInstance();
            h hVar = new h(bArr);
            hVar.a("utf-8");
            tNewInstance.a(hVar);
            return tNewInstance;
        } catch (Throwable th) {
            if (!u.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static ak a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.lejiagu.crashreport.common.info.a aVarA = com.tencent.bugly.lejiagu.crashreport.common.info.a.a();
        StrategyBean strategyBeanC = com.tencent.bugly.lejiagu.crashreport.common.strategy.a.a().c();
        if (aVarA == null || strategyBeanC == null) {
            u.e("illigle access to create req pkg!", new Object[0]);
            return null;
        }
        try {
            ak akVar = new ak();
            synchronized (aVarA) {
                akVar.a = 1;
                akVar.b = aVarA.e();
                akVar.c = aVarA.c;
                akVar.d = aVarA.i;
                akVar.e = aVarA.j;
                aVarA.getClass();
                akVar.f = "2.1.6";
                akVar.g = i;
                if (bArr == null) {
                    bArr = "".getBytes();
                }
                akVar.h = bArr;
                akVar.i = aVarA.f;
                akVar.j = aVarA.g;
                akVar.k = new HashMap();
                akVar.l = aVarA.d();
                akVar.m = strategyBeanC.l;
                akVar.o = aVarA.g();
                akVar.p = e(context);
                akVar.q = System.currentTimeMillis();
                akVar.r = aVarA.j();
                akVar.s = aVarA.i();
                akVar.t = aVarA.l();
                akVar.u = aVarA.k();
                akVar.v = aVarA.m();
                akVar.w = akVar.p;
                aVarA.getClass();
                akVar.n = "com.tencent.bugly";
            }
            return akVar;
        } catch (Throwable th) {
            if (!u.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr != null) {
            u.c("rqdp{  zp:} %s rqdp{  len:} %s", 2, Integer.valueOf(bArr.length));
            try {
                z zVarA = y.a(2);
                if (zVarA == null) {
                    return null;
                }
                return zVarA.a(bArr);
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                return null;
            }
        }
        return bArr;
    }

    public static String e() {
        try {
            String strP = p();
            if (strP == null) {
                strP = System.getProperty("os.arch");
            }
            return strP;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static long f() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (u.a(th)) {
                return -1L;
            }
            th.printStackTrace();
            return -1L;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr != null && i != -1) {
            u.c("rqdp{  unzp:} %s rqdp{  len:} %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
            try {
                z zVarA = y.a(i);
                if (zVarA == null) {
                    return null;
                }
                return zVarA.b(bArr);
            } catch (Throwable th) {
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                return null;
            }
        }
        return bArr;
    }

    public static long g() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (u.a(th)) {
                return -1L;
            }
            th.printStackTrace();
            return -1L;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    public static byte[] a(ak akVar) {
        try {
            d dVar = new d();
            dVar.p();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", akVar);
            return dVar.a();
        } catch (Throwable th) {
            if (!u.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static long h() throws Throwable {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader2 = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader2, 2048);
                try {
                    long j = Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        if (!u.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileReader2.close();
                        return j;
                    } catch (IOException e2) {
                        if (!u.a(e2)) {
                            e2.printStackTrace();
                            return j;
                        }
                        return j;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    fileReader = fileReader2;
                    try {
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e3) {
                                if (!u.a(e3)) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e4) {
                                if (!u.a(e4)) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                        return -2L;
                    } catch (Throwable th2) {
                        th = th2;
                        fileReader2 = fileReader;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fileReader = fileReader2;
            }
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
        }
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        try {
            return b(a(bArr, 1, str), 2);
        } catch (Exception e) {
            if (!u.a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static al a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.p();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object objB = dVar.b("detail", new al());
                al alVar = al.class.isInstance(objB) ? (al) al.class.cast(objB) : null;
                if (!z && alVar != null && alVar.c != null && alVar.c.length > 0) {
                    u.c("resp buf %d", Integer.valueOf(alVar.c.length));
                    alVar.c = a(alVar.c, 2, 1, StrategyBean.a);
                    if (alVar.c == null) {
                        u.e("resp sbuffer error!", new Object[0]);
                        return null;
                    }
                    return alVar;
                }
                return alVar;
            } catch (Throwable th) {
                if (!u.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static long o() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    public byte[] a() {
        i iVar = new i(0);
        iVar.a(this.b);
        iVar.a((Map) this.a, 0);
        return k.a(iVar.a());
    }

    /* JADX WARN: Code duplicated, block: B:63:0x00cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x00d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static long i() throws Throwable {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader2 = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader2, 2048);
                try {
                    bufferedReader.readLine();
                    long j = (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        if (!u.a(e)) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        fileReader2.close();
                        return j;
                    } catch (IOException e2) {
                        if (u.a(e2)) {
                            return j;
                        }
                        e2.printStackTrace();
                        return j;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    fileReader = fileReader2;
                    try {
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e3) {
                                if (!u.a(e3)) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e4) {
                                if (!u.a(e4)) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                        return -2L;
                    } catch (Throwable th2) {
                        th = th2;
                        fileReader2 = fileReader;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fileReader = fileReader2;
            }
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
        }
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap map = new HashMap(1);
        HashMap map2 = new HashMap(1);
        map2.put("", new byte[0]);
        map.put("", map2);
        this.a = this.c.a((Map) map, 0, false);
    }

    public static byte[] a(j jVar) {
        try {
            i iVar = new i();
            iVar.a("utf-8");
            jVar.a(iVar);
            return iVar.b();
        } catch (Throwable th) {
            if (!u.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            if (bArrDigest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static long j() {
        if (!(Environment.getExternalStorageState().equals("mounted"))) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return -2L;
        }
    }

    public static long k() {
        if (!(Environment.getExternalStorageState().equals("mounted"))) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return -2L;
        }
    }

    /* JADX WARN: Code duplicated, block: B:84:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static boolean a(File file, File file2, int i) throws Throwable {
        ZipOutputStream zipOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        u.c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            u.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        }
        if (!file.exists() || !file.canRead()) {
            u.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
        try {
            if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
        }
        if (!file2.exists() || !file2.canRead()) {
            return false;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
                try {
                    zipOutputStream.setMethod(8);
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    byte[] bArr = new byte[5000];
                    while (true) {
                        int i2 = fileInputStream.read(bArr);
                        if (i2 <= 0) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, i2);
                    }
                    zipOutputStream.flush();
                    zipOutputStream.closeEntry();
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    u.c("rqdp{  ZF end}", new Object[0]);
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    try {
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        u.c("rqdp{  ZF end}", new Object[0]);
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        u.c("rqdp{  ZF end}", new Object[0]);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                zipOutputStream = null;
                fileInputStream2 = fileInputStream;
            }
        } catch (Throwable th5) {
            th = th5;
            zipOutputStream = null;
        }
    }

    public static String l() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (u.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String m() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (u.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String e(Context context) {
        String str;
        TelephonyManager telephonyManager;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                str = "unknown";
            } else {
                int networkType = telephonyManager.getNetworkType();
                switch (networkType) {
                    case 1:
                        return "GPRS";
                    case 2:
                        return "EDGE";
                    case 3:
                        return "UMTS";
                    case 4:
                        return "CDMA";
                    case 5:
                        return "EVDO_0";
                    case 6:
                        return "EVDO_A";
                    case 7:
                        return "1xRTT";
                    case 8:
                        return "HSDPA";
                    case 9:
                        return "HSUPA";
                    case 10:
                        return "HSPA";
                    case 11:
                        return "iDen";
                    case 12:
                        return "EVDO_B";
                    case 13:
                        return "LTE";
                    case 14:
                        return "eHRPD";
                    case 15:
                        return "HSPA+";
                    default:
                        str = "MOBILE(" + networkType + ")";
                        break;
                }
            }
            return str;
        } catch (Exception e) {
            if (u.a(e)) {
                return "unknown";
            }
            e.printStackTrace();
            return "unknown";
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0048 A[Catch: all -> 0x00a5, TRY_LEAVE, TryCatch #5 {all -> 0x00a5, blocks: (B:22:0x0065, B:24:0x006b, B:13:0x0042, B:15:0x0048), top: B:62:0x0065 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static ArrayList<String> a(Context context, String[] strArr) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        ArrayList<String> arrayList = new ArrayList<>();
        if (com.tencent.bugly.lejiagu.crashreport.common.info.a.a(context).C()) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            arrayList2.add(new String("unknown(low memory)"));
            return arrayList2;
        }
        try {
            Process processExec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    arrayList.add(line);
                } catch (Throwable th) {
                    th = th;
                }
                if (!u.a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            }
            bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
            while (true) {
                try {
                    try {
                        String line2 = bufferedReader2.readLine();
                        if (line2 != null) {
                            arrayList.add(line2);
                        } else {
                            try {
                                break;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader3 = bufferedReader2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return null;
                }
                th = th2;
                bufferedReader3 = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
            bufferedReader.close();
            try {
                bufferedReader2.close();
                return arrayList;
            } catch (IOException e6) {
                e6.printStackTrace();
                return arrayList;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    public static String a(Context context, String str) throws Throwable {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        ArrayList<String> arrayListA = a(context, new String[]{"/system/bin/sh", "-c", "getprop " + str});
        if (arrayListA != null && arrayListA.size() > 0) {
            return arrayListA.get(0);
        }
        return "fail";
    }

    public static boolean f(Context context) throws Throwable {
        boolean zExists;
        boolean z = Build.TAGS != null && Build.TAGS.contains("test-keys");
        try {
            zExists = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            zExists = false;
        }
        Boolean bool = null;
        ArrayList<String> arrayListA = a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (arrayListA != null && arrayListA.size() > 0) {
            for (String str : arrayListA) {
                u.c(str, new Object[0]);
                bool = str.contains("not found") ? false : bool;
            }
            if (bool == null) {
                bool = true;
            }
        }
        return z || zExists || Boolean.valueOf(bool == null ? false : bool.booleanValue()).booleanValue();
    }

    public static byte[] a(long j) {
        try {
            return new StringBuilder().append(j).toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long c(byte[] bArr) {
        if (bArr == null) {
            return -1L;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static Object a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry<String, PlugInBean> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).b);
        }
        parcel.writeBundle(bundle);
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        HashMap map;
        Bundle bundle = parcel.readBundle();
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iIntValue = ((Integer) bundle.get("pluginNum")).intValue();
        for (int i = 0; i < iIntValue; i++) {
            arrayList.add(bundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < iIntValue; i2++) {
            arrayList2.add(new PlugInBean(bundle.getString("pluginVal" + i2 + "plugInId"), bundle.getString("pluginVal" + i2 + "plugInVersion"), bundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            HashMap map2 = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                map2.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
            map = map2;
        } else {
            u.e("map plugin parcel error!", new Object[0]);
            map = null;
        }
        return map;
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        ArrayList<String> arrayList2 = new ArrayList<>(size);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static Map<String, String> b(Parcel parcel) {
        HashMap map;
        Bundle bundle = parcel.readBundle();
        if (bundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("values");
        if (stringArrayList != null && stringArrayList2 != null && stringArrayList.size() == stringArrayList2.size()) {
            HashMap map2 = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                map2.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
            map = map2;
        } else {
            u.e("map parcel error!", new Object[0]);
            map = null;
        }
        return map;
    }

    public static <T> T a(byte[] bArr, Parcelable.Creator<T> creator) {
        T tCreateFromParcel;
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        try {
            try {
                tCreateFromParcel = creator.createFromParcel(parcelObtain);
                if (parcelObtain != null) {
                    parcelObtain.recycle();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                if (parcelObtain != null) {
                    parcelObtain.recycle();
                }
                tCreateFromParcel = null;
            }
            return tCreateFromParcel;
        } catch (Throwable th2) {
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            throw th2;
        }
    }

    public static String a(Context context, int i, String str) throws Throwable {
        if (!AppInfo.a(context, "android.permission.READ_LOGS")) {
            u.d("no read_log permission!", new Object[0]);
            return null;
        }
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        Process process = null;
        StringBuilder sb = new StringBuilder();
        try {
            try {
                Process processExec = Runtime.getRuntime().exec(strArr);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line).append("\n");
                        if (i > 0 && sb.length() > i) {
                            sb.delete(0, sb.length() - i);
                        }
                    }
                    String string = sb.toString();
                    if (processExec == null) {
                        return string;
                    }
                    try {
                        processExec.getOutputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        processExec.getInputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        processExec.getErrorStream().close();
                        return string;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    process = processExec;
                    if (!u.a(th)) {
                        th.printStackTrace();
                    }
                    String string2 = sb.append("\n[error:" + th.toString() + "]").toString();
                    if (process == null) {
                        return string2;
                    }
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                        return string2;
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        return string2;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static Map<String, String> a(int i, boolean z) {
        HashMap map = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            sb.setLength(0);
            if (entry.getValue() != null && entry.getValue().length != 0) {
                for (StackTraceElement stackTraceElement : entry.getValue()) {
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        break;
                    }
                    sb.append(stackTraceElement.toString()).append("\n");
                }
                map.put(entry.getKey().getName() + "(" + entry.getKey().getId() + ")", sb.toString());
            }
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.DataInputStream] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static synchronized byte[] a(int i) {
        ?? r2;
        byte[] encoded;
        DataInputStream dataInputStream;
        try {
            try {
                try {
                    encoded = new byte[16];
                    dataInputStream = new DataInputStream(new FileInputStream(new File("/dev/urandom")));
                    try {
                        dataInputStream.readFully(encoded);
                        dataInputStream.close();
                        r2 = dataInputStream;
                    } catch (Exception e) {
                        e = e;
                        u.e("Failed to read from /dev/urandom : %s", e);
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                        r2 = 128;
                        keyGenerator.init(128, new SecureRandom());
                        encoded = keyGenerator.generateKey().getEncoded();
                    }
                } catch (Exception e2) {
                    e = e2;
                    dataInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    r2 = 0;
                    if (r2 != 0) {
                        r2.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                if (!u.b(e3)) {
                    e3.printStackTrace();
                }
                encoded = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return encoded;
    }

    public static byte[] a(int i, byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            if (!u.b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(int i, byte[] bArr, byte[] bArr2) {
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKeyGeneratePublic);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            if (!u.b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static boolean a(Context context, String str, long j) {
        u.c("[Util] try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < j) {
                    return false;
                }
                u.c("[Util] lock file(%s) is expired, unlock it", str);
                b(context, str);
            }
            if (!file.createNewFile()) {
                return false;
            }
            u.c("[Util] successfully locked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            u.a(th);
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        u.c("[Util] try to unlock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            u.c("[Util] successfully unlocked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            u.a(th);
            return false;
        }
    }
}

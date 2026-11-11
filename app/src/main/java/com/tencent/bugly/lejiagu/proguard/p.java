package com.tencent.bugly.lejiagu.proguard;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public final class p {
    private static p b;
    public Map<String, String> a = null;
    private Context c;

    private p(Context context) {
        this.c = context;
    }

    public static p a(Context context) {
        if (b == null) {
            b = new p(context);
        }
        return b;
    }

    /* JADX WARN: Code duplicated, block: B:60:0x0135  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [long] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [byte[]] */
    public final byte[] a(String str, byte[] bArr, s sVar, Map<String, String> map) throws Throwable {
        boolean z;
        IOException e;
        if (str == null) {
            u.e("rqdp{  no destUrl!}", new Object[0]);
            return null;
        }
        int i = 0;
        long length = bArr == null ? 0 : bArr.length;
        u.c("req %s sz:%d (pid=%d | tid=%d)", str, Long.valueOf(length), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        boolean z2 = false;
        int i2 = 0;
        String str2 = str;
        while (true) {
            int i3 = i2 + 1;
            if (i2 >= 3 || i >= 2) {
                break;
            }
            if (z2) {
                z2 = false;
            } else if (i3 > 1) {
                u.c("try time " + i3, new Object[0]);
                SystemClock.sleep(10000L);
            }
            String strE = a.e(this.c);
            if (strE == null) {
                u.d("req but network not avail,so drop!", new Object[0]);
                i2 = i3;
            } else {
                sVar.a(length);
                HttpURLConnection httpURLConnectionA = a(str2, bArr, strE, map);
                if (httpURLConnectionA != null) {
                    try {
                        int responseCode = httpURLConnectionA.getResponseCode();
                        if (responseCode == 200) {
                            this.a = a(httpURLConnectionA);
                            byte[] bArrB = b(httpURLConnectionA);
                            sVar.b(bArrB == null ? 0L : bArrB.length);
                            length = bArrB;
                            return length;
                        }
                        if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) {
                            z = true;
                            try {
                                String headerField = httpURLConnectionA.getHeaderField("Location");
                                if (headerField == null) {
                                    u.e("rqdp{  redirect code:}" + responseCode + "rqdp{   Location is null! return}", new Object[0]);
                                    return null;
                                }
                                i++;
                                i3 = 0;
                                try {
                                    u.c("redirect code: %d ,to:%s", Integer.valueOf(responseCode), headerField);
                                    str2 = headerField;
                                } catch (IOException e2) {
                                    e = e2;
                                    str2 = headerField;
                                    if (!u.a(e)) {
                                        e.printStackTrace();
                                    }
                                    z2 = z;
                                    i2 = i3;
                                }
                            } catch (IOException e3) {
                                e = e3;
                            }
                        } else {
                            z = z2;
                        }
                        u.d("response code " + responseCode, new Object[0]);
                        long contentLength = httpURLConnectionA.getContentLength();
                        if (contentLength < 0) {
                            contentLength = 0;
                        }
                        sVar.b(contentLength);
                        z2 = z;
                        i2 = i3;
                    } catch (IOException e4) {
                        z = z2;
                        e = e4;
                    }
                    if (!u.a(e)) {
                        e.printStackTrace();
                    }
                    z2 = z;
                    i2 = i3;
                } else {
                    u.c("Failed to execute post.", new Object[0]);
                    sVar.b(0L);
                    i2 = i3;
                }
            }
        }
        return null;
    }

    private static Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap map = new HashMap();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List<String> list = headerFields.get(str);
            if (list.size() > 0) {
                map.put(str, list.get(0));
            }
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v6, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    private static byte[] b(HttpURLConnection httpURLConnection) throws Throwable {
        BufferedInputStream bufferedInputStream;
        byte[] byteArray = 0;
        byteArray = 0;
        byteArray = 0;
        byteArray = 0;
        try {
            if (httpURLConnection != null) {
                try {
                    bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = bufferedInputStream.read(bArr);
                            if (i <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                        byteArrayOutputStream.flush();
                        byteArray = byteArrayOutputStream.toByteArray();
                        try {
                            bufferedInputStream.close();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (!u.a(th)) {
                            th.printStackTrace();
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (0 != 0) {
                        try {
                            byteArray.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
            return byteArray;
        } catch (Throwable th6) {
            th = th6;
        }
    }

    private HttpURLConnection a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            u.e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection httpURLConnectionA = a(str2, str);
        if (httpURLConnectionA == null) {
            u.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            httpURLConnectionA.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpURLConnectionA.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
                }
            }
            httpURLConnectionA.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            httpURLConnectionA.setRequestProperty("A38", URLEncoder.encode(a.e(this.c), "utf-8"));
            httpURLConnectionA.connect();
            OutputStream outputStream = httpURLConnectionA.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return httpURLConnectionA;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            u.e("Failed to upload crash, please check your network.", new Object[0]);
            return null;
        }
    }

    private static HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (str != null && str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (!u.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}

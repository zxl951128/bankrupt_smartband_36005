package com.tencent.bugly.legu.proguard;

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
public final class r {
    private static r b;
    public Map<String, String> a = null;
    private Context c;

    private r(Context context) {
        this.c = context;
    }

    public static r a(Context context) {
        if (b == null) {
            b = new r(context);
        }
        return b;
    }

    /* JADX WARN: Code duplicated, block: B:75:0x015c A[Catch: all -> 0x016e, TRY_LEAVE, TryCatch #8 {all -> 0x016e, blocks: (B:24:0x008d, B:26:0x0095, B:29:0x00a3, B:32:0x00ae, B:49:0x00d0, B:51:0x00d8, B:60:0x0109, B:73:0x0156, B:75:0x015c, B:62:0x011d, B:65:0x013f), top: B:99:0x008d }] */
    public final byte[] a(String str, byte[] bArr, u uVar, Map<String, String> map) {
        if (str == null) {
            w.e("rqdp{  no destUrl!}", new Object[0]);
            return null;
        }
        int i = 0;
        long length = bArr == null ? 0L : bArr.length;
        w.c("req %s sz:%d (pid=%d | tid=%d)", str, Long.valueOf(length), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        boolean z = false;
        int i2 = 0;
        String str2 = str;
        while (true) {
            int i3 = i2 + 1;
            if (i2 >= 3 || i >= 2) {
                break;
            }
            if (z) {
                z = false;
            } else if (i3 > 1) {
                w.c("try time " + i3, new Object[0]);
                SystemClock.sleep(10000L);
            }
            String strE = a.e(this.c);
            if (strE == null) {
                w.d("req but network not avail,so drop!", new Object[0]);
                i2 = i3;
            } else {
                uVar.a(length);
                HttpURLConnection httpURLConnectionA = a(str2, bArr, strE, map);
                if (httpURLConnectionA != null) {
                    try {
                        try {
                            int responseCode = httpURLConnectionA.getResponseCode();
                            if (responseCode == 200) {
                                this.a = a(httpURLConnectionA);
                                byte[] bArrB = b(httpURLConnectionA);
                                uVar.b(bArrB == null ? 0L : bArrB.length);
                                return bArrB;
                            }
                            if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) {
                                try {
                                    String headerField = httpURLConnectionA.getHeaderField("Location");
                                    if (headerField == null) {
                                        w.e("rqdp{  redirect code:}" + responseCode + "rqdp{   Location is null! return}", new Object[0]);
                                        try {
                                            httpURLConnectionA.disconnect();
                                        } catch (Throwable th) {
                                            if (!w.a(th)) {
                                                th.printStackTrace();
                                            }
                                        }
                                        return null;
                                    }
                                    i++;
                                    i3 = 0;
                                    try {
                                        w.c("redirect code: %d ,to:%s", Integer.valueOf(responseCode), headerField);
                                        z = true;
                                        str2 = headerField;
                                    } catch (IOException e) {
                                        str2 = headerField;
                                        e = e;
                                        z = true;
                                        if (!w.a(e)) {
                                            e.printStackTrace();
                                        }
                                        httpURLConnectionA.disconnect();
                                    }
                                } catch (IOException e2) {
                                    z = true;
                                    e = e2;
                                }
                            }
                            w.d("response code " + responseCode, new Object[0]);
                            long contentLength = httpURLConnectionA.getContentLength();
                            if (contentLength < 0) {
                                contentLength = 0;
                            }
                            uVar.b(contentLength);
                            try {
                                httpURLConnectionA.disconnect();
                            } catch (Throwable th2) {
                                if (!w.a(th2)) {
                                    th2.printStackTrace();
                                }
                            }
                        } finally {
                            try {
                                httpURLConnectionA.disconnect();
                            } catch (Throwable th3) {
                                if (!w.a(th3)) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                    if (!w.a(e)) {
                        e.printStackTrace();
                    }
                    try {
                        httpURLConnectionA.disconnect();
                    } catch (Throwable th4) {
                        if (!w.a(th4)) {
                            th4.printStackTrace();
                        }
                    }
                } else {
                    w.c("Failed to execute post.", new Object[0]);
                    uVar.b(0L);
                }
                i2 = i3;
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
                        if (!w.a(th)) {
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
            w.e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection httpURLConnectionA = a(str2, str);
        if (httpURLConnectionA == null) {
            w.e("Failed to get HttpURLConnection object.", new Object[0]);
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
            if (!w.a(th)) {
                th.printStackTrace();
            }
            w.e("Failed to upload crash, please check your network.", new Object[0]);
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
            if (!w.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}

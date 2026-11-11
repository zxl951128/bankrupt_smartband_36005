package com.tencent.bugly.lejiagu.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.tencent.bugly.lejiagu.crashreport.inner.InnerAPI;
import com.tencent.bugly.lejiagu.proguard.u;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes.dex */
public class H5JavaScriptInterface {
    private static HashMap<H5JavaScriptInterface, Integer> a = new HashMap<>();
    private String b = null;
    private Thread c = null;
    private String d = null;
    private Map<String, String> e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(WebView webView) {
        String string = null;
        if (webView == null || a.values().contains(Integer.valueOf(webView.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        a.put(h5JavaScriptInterface, Integer.valueOf(webView.hashCode()));
        h5JavaScriptInterface.c = Thread.currentThread();
        Thread thread = h5JavaScriptInterface.c;
        if (thread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 2; i < thread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = thread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString()).append("\n");
                }
            }
            string = sb.toString();
        }
        h5JavaScriptInterface.d = string;
        HashMap map = new HashMap();
        map.put("[WebView] ContentDescription", new StringBuilder().append((Object) webView.getContentDescription()).toString());
        h5JavaScriptInterface.e = map;
        return h5JavaScriptInterface;
    }

    private static a a(String str) {
        String string;
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = new a();
            aVar.a = jSONObject.getString("projectRoot");
            if (aVar.a == null) {
                return null;
            }
            aVar.b = jSONObject.getString("context");
            if (aVar.b == null) {
                return null;
            }
            aVar.c = jSONObject.getString("url");
            if (aVar.c == null) {
                return null;
            }
            aVar.d = jSONObject.getString("userAgent");
            if (aVar.d == null) {
                return null;
            }
            aVar.e = jSONObject.getString("language");
            if (aVar.e == null) {
                return null;
            }
            aVar.f = jSONObject.getString("name");
            if (aVar.f == null || aVar.f.equals("null") || (string = jSONObject.getString("stacktrace")) == null) {
                return null;
            }
            int iIndexOf = string.indexOf("\n");
            if (iIndexOf < 0) {
                u.d("H5 crash stack's format is wrong!", new Object[0]);
                return null;
            }
            aVar.h = string.substring(iIndexOf + 1);
            aVar.g = string.substring(0, iIndexOf);
            int iIndexOf2 = aVar.g.indexOf(":");
            if (iIndexOf2 > 0) {
                aVar.g = aVar.g.substring(iIndexOf2 + 1);
            }
            aVar.i = jSONObject.getString("file");
            if (aVar.f == null) {
                return null;
            }
            aVar.j = jSONObject.getLong("lineNumber");
            if (aVar.j < 0) {
                return null;
            }
            aVar.k = jSONObject.getLong("columnNumber");
            if (aVar.k < 0) {
                return null;
            }
            u.a("H5 crash information is following: ", new Object[0]);
            u.a("[projectRoot]: " + aVar.a, new Object[0]);
            u.a("[context]: " + aVar.b, new Object[0]);
            u.a("[url]: " + aVar.c, new Object[0]);
            u.a("[userAgent]: " + aVar.d, new Object[0]);
            u.a("[language]: " + aVar.e, new Object[0]);
            u.a("[name]: " + aVar.f, new Object[0]);
            u.a("[message]: " + aVar.g, new Object[0]);
            u.a("[stacktrace]: \n" + aVar.h, new Object[0]);
            u.a("[file]: " + aVar.i, new Object[0]);
            u.a("[lineNumber]: " + aVar.j, new Object[0]);
            u.a("[columnNumber]: " + aVar.k, new Object[0]);
            return aVar;
        } catch (Throwable th) {
            if (u.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public void printLog(String str) {
        u.d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            u.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String strB = com.tencent.bugly.lejiagu.proguard.a.b(str.getBytes());
        if (this.b != null && this.b.equals(strB)) {
            u.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.b = strB;
        u.d("Handling JS exception ...", new Object[0]);
        a aVarA = a(str);
        if (aVarA == null) {
            u.d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (aVarA.a != null) {
            linkedHashMap2.put("[JS] projectRoot", aVarA.a);
        }
        if (aVarA.b != null) {
            linkedHashMap2.put("[JS] context", aVarA.b);
        }
        if (aVarA.c != null) {
            linkedHashMap2.put("[JS] url", aVarA.c);
        }
        if (aVarA.d != null) {
            linkedHashMap2.put("[JS] userAgent", aVarA.d);
        }
        if (aVarA.i != null) {
            linkedHashMap2.put("[JS] file", aVarA.i);
        }
        if (aVarA.j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(aVarA.j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.e);
        linkedHashMap.put("Java Stack", this.d);
        Thread thread = this.c;
        if (aVarA != null) {
            InnerAPI.postH5CrashAsync(thread, aVarA.f, aVarA.g, aVarA.h, linkedHashMap);
        }
    }
}

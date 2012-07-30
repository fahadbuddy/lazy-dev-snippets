package com.examples;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class TrustTheWorldClient {

  public static void main(String[] args) {
    final HttpClient client = trustEveryoneSslHttpClient();

    Map<String, String> data = new HashMap<String, String>() {
      {
        put("q", "search");
      }
    };
    SimpleClient.post(client, "https://httpbin.org/post", data);
    SimpleClient.get(client, "https://httpbin.org/get");
  }

  private static HttpClient trustEveryoneSslHttpClient() {
    try {
      SchemeRegistry registry = new SchemeRegistry();
      SSLSocketFactory socketFactory = new SSLSocketFactory(new TrustStrategy() {

        public boolean isTrusted(final X509Certificate[] chain, String authType) throws CertificateException {
          return true;
        }

      }, org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

      registry.register(new Scheme("https", 443, socketFactory));
      ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
      mgr.setMaxTotal(1000);
      mgr.setDefaultMaxPerRoute(1000);
      DefaultHttpClient client = new DefaultHttpClient(mgr, new DefaultHttpClient().getParams());
      return client;
    }
    catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }
}

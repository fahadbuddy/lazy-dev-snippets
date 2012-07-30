package com.examples;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class MultiThreadedClient {
  public static void main(String[] args) {
    final HttpClient client = trustEveryoneSslHttpClient();

    Map<String, String> data = new HashMap<String, String>() {
      {
        put("q", "search");
      }
    };
    SimpleClient.post(client, "http://httpbin.org/post", data);
    SimpleClient.get(client, "http://httpbin.org/get");
  }

  private static HttpClient trustEveryoneSslHttpClient() {
    SchemeRegistry registry = new SchemeRegistry();
    ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
    mgr.setMaxTotal(1000);
    mgr.setDefaultMaxPerRoute(1000);
    DefaultHttpClient client = new DefaultHttpClient(mgr, new DefaultHttpClient().getParams());
    return client;
  }
}

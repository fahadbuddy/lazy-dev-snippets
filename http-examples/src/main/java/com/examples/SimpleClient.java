package com.examples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SimpleClient {

  public static void main(String[] args) {
    final HttpClient client = new DefaultHttpClient();
    Map<String, String> data = new HashMap<String, String>() {
      {
        put("q", "search");
      }
    };

    post(client, "http://httpbin.org/post", data);
    get(client, "http://httpbin.org/get");
  }

  public static String post(HttpClient client, String myUrl, Map<String, String> data) {
    HttpPost post = new HttpPost(myUrl);
    try {

      List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(data.size());

      for (Iterator<String> ite = data.keySet().iterator(); ite.hasNext();) {
        String key = (String) ite.next();
        nameValuePair.add(new BasicNameValuePair(key, data.get(key)));
      }
      post.setEntity(new UrlEncodedFormEntity(nameValuePair));

      HttpResponse response = client.execute(post);
      String message = response.toString() + EntityUtils.toString(response.getEntity());
      System.out.println(message);
      return message;
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String get(HttpClient client, String myUrl) {
    HttpGet get = new HttpGet(myUrl);
    try {
      HttpResponse response = client.execute(get);
      String content = EntityUtils.toString(response.getEntity());
      String message = response.toString() + content;
      System.out.println(message);
      return content;
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}

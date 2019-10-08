/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zxy97.blog.util.json;


import java.util.Iterator;

public class CookieList
{
  public static JSONObject toJSONObject(String string)
    throws JSONException
  {
    JSONObject jo = new JSONObject();
    JSONTokener x = new JSONTokener(string);
    while (x.more())
    {
      String name = Cookie.unescape(x.nextTo('='));
      x.next('=');
      jo.put(name, Cookie.unescape(x.nextTo(';')));
      x.next();
    }
    return jo;
  }
  
  public static String toString(JSONObject jo)
    throws JSONException
  {
    boolean b = false;
    Iterator<String> keys = jo.keys();
    
    StringBuilder sb = new StringBuilder();
    while (keys.hasNext())
    {
      String string = (String)keys.next();
      if (!jo.isNull(string))
      {
        if (b) {
          sb.append(';');
        }
        sb.append(Cookie.escape(string));
        sb.append("=");
        sb.append(Cookie.escape(jo.getString(string)));
        b = true;
      }
    }
    return sb.toString();
  }
}

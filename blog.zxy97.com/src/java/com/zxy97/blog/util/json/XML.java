/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zxy97.blog.util.json;

import java.util.Iterator;

public class XML
{
  public static final Character AMP = Character.valueOf('&');
  public static final Character APOS = Character.valueOf('\'');
  public static final Character BANG = Character.valueOf('!');
  public static final Character EQ = Character.valueOf('=');
  public static final Character GT = Character.valueOf('>');
  public static final Character LT = Character.valueOf('<');
  public static final Character QUEST = Character.valueOf('?');
  public static final Character QUOT = Character.valueOf('"');
  public static final Character SLASH = Character.valueOf('/');
  
  public static String escape(String string)
  {
    StringBuilder sb = new StringBuilder(string.length());
    int i = 0;
    for (int length = string.length(); i < length; i++)
    {
      char c = string.charAt(i);
      switch (c)
      {
      case '&': 
        sb.append("&amp;");
        break;
      case '<': 
        sb.append("&lt;");
        break;
      case '>': 
        sb.append("&gt;");
        break;
      case '"': 
        sb.append("&quot;");
        break;
      case '\'': 
        sb.append("&apos;");
        break;
      default: 
        sb.append(c);
      }
    }
    return sb.toString();
  }
  
  public static void noSpace(String string)
    throws JSONException
  {
    int length = string.length();
    if (length == 0) {
      throw new JSONException("Empty string.");
    }
    for (int i = 0; i < length; i++) {
      if (Character.isWhitespace(string.charAt(i))) {
        throw new JSONException("'" + string + "' contains a space character.");
      }
    }
  }
  
  private static boolean parse(XMLTokener x, JSONObject context, String name, boolean keepStrings)
    throws JSONException
  {
    JSONObject jsonobject = null;
    













    Object token = x.nextToken();
    if (token == BANG)
    {
      char c = x.next();
      if (c == '-')
      {
        if (x.next() == '-')
        {
          x.skipPast("-->");
          return false;
        }
        x.back();
      }
      else if (c == '[')
      {
        token = x.nextToken();
        if (("CDATA".equals(token)) && 
          (x.next() == '['))
        {
          String string = x.nextCDATA();
          if (string.length() > 0) {
            context.accumulate("content", string);
          }
          return false;
        }
        throw x.syntaxError("Expected 'CDATA['");
      }
      int i = 1;
      do
      {
        token = x.nextMeta();
        if (token == null) {
          throw x.syntaxError("Missing '>' after '<!'.");
        }
        if (token == LT) {
          i++;
        } else if (token == GT) {
          i--;
        }
      } while (i > 0);
      return false;
    }
    if (token == QUEST)
    {
      x.skipPast("?>");
      return false;
    }
    if (token == SLASH)
    {
      token = x.nextToken();
      if (name == null) {
        throw x.syntaxError("Mismatched close tag " + token);
      }
      if (!token.equals(name)) {
        throw x.syntaxError("Mismatched " + name + " and " + token);
      }
      if (x.nextToken() != GT) {
        throw x.syntaxError("Misshaped close tag");
      }
      return true;
    }
    if ((token instanceof Character)) {
      throw x.syntaxError("Misshaped tag");
    }
    String tagName = (String)token;
    token = null;
    jsonobject = new JSONObject();
    for (;;)
    {
      if (token == null) {
        token = x.nextToken();
      }
      if (!(token instanceof String)) {
        break;
      }
      String string = (String)token;
      token = x.nextToken();
      if (token == EQ)
      {
        token = x.nextToken();
        if (!(token instanceof String)) {
          throw x.syntaxError("Missing value");
        }
        jsonobject.accumulate(string, keepStrings ? token : 
          JSONObject.stringToValue((String)token));
        token = null;
      }
      else
      {
        jsonobject.accumulate(string, "");
      }
    }
    if (token == SLASH)
    {
      if (x.nextToken() != GT) {
        throw x.syntaxError("Misshaped tag");
      }
      if (jsonobject.length() > 0) {
        context.accumulate(tagName, jsonobject);
      } else {
        context.accumulate(tagName, "");
      }
      return false;
    }
    if (token == GT)
    {
      do
      {
        for (;;)
        {
          token = x.nextContent();
          if (token == null)
          {
            if (tagName != null) {
              throw x.syntaxError("Unclosed tag " + tagName);
            }
            return false;
          }
          if (!(token instanceof String)) {
            break;
          }
          String string = (String)token;
          if (string.length() > 0) {
            jsonobject.accumulate("content", keepStrings ? token : 
              JSONObject.stringToValue(string));
          }
        }
      } while ((token != LT) || 
      
        (!parse(x, jsonobject, tagName, keepStrings)));
      if (jsonobject.length() == 0) {
        context.accumulate(tagName, "");
      } else if ((jsonobject.length() == 1) && 
        (jsonobject.opt("content") != null)) {
        context.accumulate(tagName, jsonobject
          .opt("content"));
      } else {
        context.accumulate(tagName, jsonobject);
      }
      return false;
    }
    throw x.syntaxError("Misshaped tag");
  }
  
  @Deprecated
  public static Object stringToValue(String string)
  {
    return JSONObject.stringToValue(string);
  }
  
  public static JSONObject toJSONObject(String string)
    throws JSONException
  {
    return toJSONObject(string, false);
  }
  
  public static JSONObject toJSONObject(String string, boolean keepStrings)
    throws JSONException
  {
    JSONObject jo = new JSONObject();
    XMLTokener x = new XMLTokener(string);
    while ((x.more()) && (x.skipPast("<"))) {
      parse(x, jo, null, keepStrings);
    }
    return jo;
  }
  
  public static String toString(Object object)
    throws JSONException
  {
    return toString(object, null);
  }
  
  public static String toString(Object object, String tagName)
    throws JSONException
  {
    StringBuilder sb = new StringBuilder();
    int i;
    if ((object instanceof JSONObject))
    {
      if (tagName != null)
      {
        sb.append('<');
        sb.append(tagName);
        sb.append('>');
      }
      JSONObject jo = (JSONObject)object;
      Iterator<String> keys = jo.keys();
      while (keys.hasNext())
      {
        String key = (String)keys.next();
        Object value = jo.opt(key);
        if (value == null) {
          value = "";
        } else if (value.getClass().isArray()) {
          value = new JSONArray(value);
        }
        String string = (value instanceof String) ? (String)value : null;
        if ("content".equals(key))
        {
          if ((value instanceof JSONArray))
          {
            JSONArray ja = (JSONArray)value;
            i = 0;
            for (Object val : ja)
            {
              if (i > 0) {
                sb.append('\n');
              }
              sb.append(escape(val.toString()));
              i++;
            }
          }
          else
          {
            sb.append(escape(value.toString()));
          }
        }
        else if ((value instanceof JSONArray))
        {
          JSONArray ja = (JSONArray)value;
          for (Object val : ja) {
            if ((val instanceof JSONArray))
            {
              sb.append('<');
              sb.append(key);
              sb.append('>');
              sb.append(toString(val));
              sb.append("</");
              sb.append(key);
              sb.append('>');
            }
            else
            {
              sb.append(toString(val, key));
            }
          }
        }
        else if ("".equals(value))
        {
          sb.append('<');
          sb.append(key);
          sb.append("/>");
        }
        else
        {
          sb.append(toString(value, key));
        }
      }
      if (tagName != null)
      {
        sb.append("</");
        sb.append(tagName);
        sb.append('>');
      }
      return sb.toString();
    }
    if (object != null)
    {
      if (object.getClass().isArray()) {
        object = new JSONArray(object);
      }
      if ((object instanceof JSONArray))
      {
        JSONArray ja = (JSONArray)object;
        for (Object val : ja) {
          sb.append(toString(val, tagName == null ? "array" : tagName));
        }
        return sb.toString();
      }
    }
    String string = object == null ? "null" : escape(object.toString());
    
    return "<" + tagName + ">" + string + "</" + tagName + ">";
  }
}

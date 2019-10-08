/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zxy97.blog.util.json;


import java.util.HashMap;

public class XMLTokener
  extends JSONTokener
{
  public static final HashMap<String, Character> entity = new HashMap(8);
  
  static
  {
    entity.put("amp", XML.AMP);
    entity.put("apos", XML.APOS);
    entity.put("gt", XML.GT);
    entity.put("lt", XML.LT);
    entity.put("quot", XML.QUOT);
  }
  
  public XMLTokener(String s)
  {
    super(s);
  }
  
  public String nextCDATA()
    throws JSONException
  {
    StringBuilder sb = new StringBuilder();
    int i;
    do
    {
      char c = next();
      if (end()) {
        throw syntaxError("Unclosed CDATA");
      }
      sb.append(c);
      i = sb.length() - 3;
    } while ((i < 0) || (sb.charAt(i) != ']') || 
      (sb.charAt(i + 1) != ']') || (sb.charAt(i + 2) != '>'));
    sb.setLength(i);
    return sb.toString();
  }
  
  public Object nextContent()
    throws JSONException
  {
    char c;
    do
    {
      c = next();
    } while (Character.isWhitespace(c));
    if (c == 0) {
      return null;
    }
    if (c == '<') {
      return XML.LT;
    }
    StringBuilder sb = new StringBuilder();
    for (;;)
    {
      if ((c == '<') || (c == 0))
      {
        back();
        return sb.toString().trim();
      }
      if (c == '&') {
        sb.append(nextEntity(c));
      } else {
        sb.append(c);
      }
      c = next();
    }
  }
  
  public Object nextEntity(char ampersand)
    throws JSONException
  {
    StringBuilder sb = new StringBuilder();
    for (;;)
    {
      char c = next();
      if ((Character.isLetterOrDigit(c)) || (c == '#'))
      {
        sb.append(Character.toLowerCase(c));
      }
      else
      {
        if (c == ';') {
          break;
        }
        throw syntaxError("Missing ';' in XML entity: &" + sb);
      }
    }
    String string = sb.toString();
    Object object = entity.get(string);
    return ampersand + string + ";";
  }
  
  public Object nextMeta()
    throws JSONException
  {
    char c;
    do
    {
      c = next();
    } while (Character.isWhitespace(c));
    switch (c)
    {
    case '\000': 
      throw syntaxError("Misshaped meta tag");
    case '<': 
      return XML.LT;
    case '>': 
      return XML.GT;
    case '/': 
      return XML.SLASH;
    case '=': 
      return XML.EQ;
    case '!': 
      return XML.BANG;
    case '?': 
      return XML.QUEST;
    case '"': 
    case '\'': 
      char q = c;
      do
      {
        c = next();
        if (c == 0) {
          throw syntaxError("Unterminated string");
        }
      } while (c != q);
      return Boolean.TRUE;
    }
    for (;;)
    {
      c = next();
      if (Character.isWhitespace(c)) {
        return Boolean.TRUE;
      }
      switch (c)
      {
      case '\000': 
      case '!': 
      case '"': 
      case '\'': 
      case '/': 
      case '<': 
      case '=': 
      case '>': 
      case '?': 
        back();
        return Boolean.TRUE;
      }
    }
  }
  
  public Object nextToken()
    throws JSONException
  {
    char c;
    do
    {
      c = next();
    } while (Character.isWhitespace(c));
    switch (c)
    {
    case '\000': 
      throw syntaxError("Misshaped element");
    case '<': 
      throw syntaxError("Misplaced '<'");
    case '>': 
      return XML.GT;
    case '/': 
      return XML.SLASH;
    case '=': 
      return XML.EQ;
    case '!': 
      return XML.BANG;
    case '?': 
      return XML.QUEST;
    case '"': 
    case '\'': 
      char q = c;
      StringBuilder sb = new StringBuilder();
      for (;;)
      {
        c = next();
        if (c == 0) {
          throw syntaxError("Unterminated string");
        }
        if (c == q) {
          return sb.toString();
        }
        if (c == '&') {
          sb.append(nextEntity(c));
        } else {
          sb.append(c);
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (;;)
    {
      sb.append(c);
      c = next();
      if (Character.isWhitespace(c)) {
        return sb.toString();
      }
      switch (c)
      {
      case '\000': 
        return sb.toString();
      case '!': 
      case '/': 
      case '=': 
      case '>': 
      case '?': 
      case '[': 
      case ']': 
        back();
        return sb.toString();
      case '"': 
      case '\'': 
      case '<': 
        throw syntaxError("Bad character in a name");
      }
    }
  }
  
  public boolean skipPast(String to)
    throws JSONException
  {
    int offset = 0;
    int length = to.length();
    char[] circle = new char[length];
    for (int i = 0; i < length; i++)
    {
      char c = next();
      if (c == 0) {
        return false;
      }
      circle[i] = c;
    }
    for (;;)
    {
      int j = offset;
      boolean b = true;
      for (int i = 0; i < length; i++)
      {
        if (circle[j] != to.charAt(i))
        {
          b = false;
          break;
        }
        j++;
        if (j >= length) {
          j -= length;
        }
      }
      if (b) {
        return true;
      }
      char c = next();
      if (c == 0) {
        return false;
      }
      circle[offset] = c;
      offset++;
      if (offset >= length) {
        offset -= length;
      }
    }
  }
}

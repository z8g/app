/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zxy97.blog.util.json;


public class JSONPointerException
  extends JSONException
{
  private static final long serialVersionUID = 8872944667561856751L;
  
  public JSONPointerException(String message)
  {
    super(message);
  }
  
  public JSONPointerException(String message, Throwable cause)
  {
    super(message, cause);
  }
}

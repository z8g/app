/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zxy97.blog.util.json;


public class JSONException
  extends RuntimeException
{
  private static final long serialVersionUID = 0L;
  
  public JSONException(String message)
  {
    super(message);
  }
  
  public JSONException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public JSONException(Throwable cause)
  {
    super(cause.getMessage(), cause);
  }
}

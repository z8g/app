/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zxy97.blog.util.json;

import java.io.StringWriter;

public class JSONStringer
  extends JSONWriter
{
  public JSONStringer()
  {
    super(new StringWriter());
  }
  
  public String toString()
  {
    return this.mode == 'd' ? this.writer.toString() : null;
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jalaxy.util;

/**
 *
 * @author zhaoxuyang
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author ceshi
 * @Title: JunitTestJS
 * @ProjectName ceshi
 * @Description: java 运行js
 * @date 2018/7/1016:35
 */
public class JsUtilTest {

    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 21);

        String key = "json";
        engine.put(key, map);
        String script = String.format("JSON.stringify(%s);"
                + "print(json);"
                + "print(json.get(\"name\"));"
                + "print(json.name);",
                 key);
        String json = (String) engine.eval(script);
        //System.out.println(json);

    }

    @Test
    public void toJson() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        //try {
        engine.eval("student = new Object();"
                + "student.name = \"zhangsan\";"
                + "student.id = \"2015014117\";"
                + "student.age = 21;"
                + "print('Hello');"
                + "print(JSON.stringify(student));"
        );
//            if (engine instanceof Invocable) {
//                Invocable in = (Invocable) engine;
//                System.out.println(in.invokeFunction("add"));
//            }
//        } catch (NoSuchMethodException | ScriptException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void evalFile() throws FileNotFoundException, ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String jsName = "test.js";
        FileReader fileReader = new FileReader(jsName);//读取js
        engine.eval(fileReader);//执行指定脚本  
    }

}

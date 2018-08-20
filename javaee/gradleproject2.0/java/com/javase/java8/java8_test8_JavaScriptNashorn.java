package com.javase.java8;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by zjm on 2017/7/23.
 *  JavaScript引擎Nashorn
 *
 *  Nashorn，一个新的JavaScript引擎随着Java 8一起公诸于世，
 *  它允许在JVM上开发运行某些JavaScript应用。
 *  Nashorn就是javax.script.ScriptEngine的另一种实现，并且它们俩遵循相同的规则，
 *  允许Java与JavaScript相互调用。下面看一个例子：
 */
public class java8_test8_JavaScriptNashorn {

    @Test
    public  void test(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        try {
            System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}

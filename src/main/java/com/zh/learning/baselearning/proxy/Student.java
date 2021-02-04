package com.zh.learning.baselearning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/25 14:16
 */
public class Student implements Person{

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String giveMoney() {
        return name +"同学上交班费";
    }

    @Override
    public String aa(String aa) {
        return aa;
    }

    public static void main(String[] args) {
        // 第一个参数是为了获得类加载器 应该是 第二个是获得接口类 第三个就是执行方法
        Person o =(Person) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("第一步进入proxy");
                String xiao =(String) method.invoke(new Student("xiao"), args);
                System.out.println("执行了student的方法及结果："+xiao);
                return new String("value".getBytes("iso8859-1"),"UTF-8");
            }
        });
        System.out.println("执行被代理类的方法："+o.giveMoney());
        String string = o.toString();
        System.out.println("打印结果"+string);
        System.out.println("打印结果方法名 aa: "+o.aa("zzz"));
    }
}

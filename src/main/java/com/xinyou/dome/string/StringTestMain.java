package com.xinyou.dome.string;

import java.lang.reflect.Field;
import java.net.ServerSocket;

/**
 * @ Author     ：chenxinyou.
 * @ Title      :
 * @ Date       ：Created in 2018/11/59:12
 * @ Description:
 */
public class StringTestMain {
    public static void main(String[] args) throws Exception {
        String a = "abc";
        Field valueFieldString = String.class.getDeclaredField("value");
        valueFieldString.setAccessible(true);
        char[] value = (char[]) valueFieldString.get(a);
        value[2] = '@';
        String b = "abc";
//        a.intern();
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
        System.out.println("abc" == b);
        System.out.println("ab@" == a);
        System.out.println(a.equals("ab@"));
        System.out.println(a.equals("abc"));
        System.out.println("abc".equals("ab@"));

        int port = 80;
        int size = 20;
        ServerSocket serverSocket = new ServerSocket(port, size);
    }
}

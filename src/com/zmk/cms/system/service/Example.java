package com.zmk.cms.system.service;

public class Example {  
    
    String str = new String("good");  
    Integer a = 12;
    char[] ch = { 'a', 'b', 'c' };  
  
    public static void main(String args[]) {  
  
        Example ex = new Example();  
  
        ex.change(ex.str, ex.ch,ex.a);  
  
        System.out.print(ex.str + " and "+ex.a+" and ");  
  
        System.out.print(ex.ch);  
  
    }  
  
    public void change(String str, char ch[],Integer a) {  
  
        str = "test ok";  
  
        ch[0] = 'g';  
        
        a=122;
  
    }  
}  

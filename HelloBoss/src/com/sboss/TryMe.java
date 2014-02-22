package com.sboss;

class A {
    public void doA() {
        B b = new B();
        b.dobB();
        System.out.print("doA");
    }
}

class B {
    public void dobB() {
        C c = new C();
        c.doC();
        System.out.print("doB");
    }
}

class C {
    public void doC() {
        if (true)
            throw new NullPointerException();
        System.out.print("doC");
    }
}

public class TryMe {

    public static void main(String args[]) {
    	String input="txt/plain";       //"html/plain";
    	String input1="";
   
        /*try {
            A a = new A();
            a.doA();
        } catch (Exception ex) {
            System.out.print("error");
        }*/
    	
    		/*     Object myObj = new String[]{"one", "two", "three"}; {
    	         for (String s : (String[])myObj) System.out.print(s + ".");
    	   }*/
//    	 for (int i = 1; i < args.length; i++) {
//             System.out.print(i + " ");
//      }
    	
    	
    	if((input.contains("html")||input.contains("plain"))&& input.length()==0){ 
    		
    		
    		System.out.println("Success...");
    	}
    		else
    		{System.out.println("FLOP");}
    	
   
}
}
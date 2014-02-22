package org.com.gopal;
//import test.hey.boss.*;
import java.util.UUID;
public class Judge {
	
	public static void main(String[] args)throws Exception {
		
		System.out.println("UUID=-=-=-=-=-=->"+UUID.randomUUID().toString());
		System.out.println("UUID22222222=-=->"+UUID.randomUUID().toString());
		String s=null;
		Judge jd =new Judge();
		jd.uiop();
		jd.math();
		jd.arry();
		try{
			
		System.out.println(s.length());
		
		
		}	
		
		catch (NullPointerException e) {
			// TODO: handle exception
//			System.out.println("NullPointerException===>"+e.getMessage());
			System.out.println(e);
				
		}
		finally{System.out.println("----finally-----");
		}
		
		System.out.println("---------");
		try {
			String K="zxc";
			int u=Integer.parseInt(K);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("---getMessage-----"+e.getMessage());
			System.out.println("---getCause-----"+e.getCause());
		}
	}
	
	void math(){
		try{
			int i=1/0;			
		}
		catch (ArithmeticException e) {
			System.out.println("Arith-=->"+e.getMessage());
		}
	}

	void arry(){
		
		try{
			int k[]= new int[5];
			k[9]=100;	
			
		}
		catch (ArrayIndexOutOfBoundsException e) {
			//e.printStackTrace();
			System.out.println("ArrayI=getCause-=->"+e.getCause());
			System.out.println("ArrayI=getMessage-=->"+e.getMessage());
		}throw new ArithmeticException("GOA");
	}
	
	void uiop(){
		try {
			byte k=99;
			int kj= k;
			System.out.println(kj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
}
		
		


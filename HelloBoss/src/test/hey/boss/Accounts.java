package test.hey.boss;

import java.util.Scanner;

public class Accounts {
	protected int stock = 50;
	protected int dailyCount = 12;
	static final int need = 20;
	int vals = 190;

	public  Accounts() {
		System.out.println(++vals);
	}
	
	void valv(){System.out.println("ACCounts===>valv()");}
	public static void main(String gop[]) {
		Accounts rest = new Rest();
		//rest.cick();
//		rest.valv();

	}
}

class FoodDepartment extends Accounts {

	FoodDepartment() {
		super.valv();
		truck();
		//System.out.println("Old stock=" + --stock);
	}
	FoodDepartment(int a){
		System.out.println(a);
				
	}
	
	void valv(){
		
		System.out.println("F00dDepartment===>valv()");}
	void truck() {
		System.out.println("TRUCK()");

		System.out.println("stock=" + ++stock);
		System.out.println("NEED=" + need);
		System.out.println("dailyCount=" + dailyCount);
		System.out.println("/TRUCK()");
	}

	// Scanner in = new Scanner(System.in);
}

class Rest extends FoodDepartment {
	Rest(){
		this.dailyCount=67;
		this.truck();
	}
	
	/*void cick() {
		super.dailyCount=67;
		this.stock=600;
		truck();
	}*/
}
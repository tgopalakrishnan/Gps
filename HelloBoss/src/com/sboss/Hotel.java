package com.sboss;

import java.util.Arrays;

public class Hotel {
    private int roomNr;
    
    public Hotel(int roomNr) {
        this.roomNr = roomNr;
    }
    
    public int getRoomNr() {
        return this.roomNr;
    }
    
    static Hotel doStuff(Hotel hotel) {
        hotel = new Hotel(1);
        return hotel;
    }
    
    public static void main(String args[]) {
    	int a [] = new int[5];
    	int [] a1 = {23,22,21,20,19};
        Hotel h1 = new Hotel(100);
        System.out.print(h1.getRoomNr() + " ");
        Hotel h2 = doStuff(h1);
        System.out.print(h1.getRoomNr() + " ");
        System.out.print(h2.getRoomNr() + " ");
        h1 = doStuff(h2);
        System.out.print(h1.getRoomNr() + " ");
        System.out.print(h2.getRoomNr() + " ");
    }
}
class Small {
    public Small() {
  System.out.print("1a ");  }
}

class Small2 extends Small {
    public  Small2() {
        System.out.print("b ");
        
    }
}

class Small3 extends Small2 {
    public Small3() {
        System.out.print("c1 ");
        
    }
}

 class Hotel1 {     
    public static void main(String args[]) {
        new Small3();
        System.out.println();
        new Small2();
        System.out.println();
        new Small();
    }
}
 class Hotel11 {     
	    public static void main(String args[]) {
	    	System.out.println( "Hello" + args[0] );
	     }
	     
	    }
 
/* class Creature {
    private int legCount;
    private int wingCount;
    
    public Creature(int legCount) {
        this.legCount = this.legCount;
        this.wingCount = 0;
    }
    
    public String toString() {
        return "legs=" + this.legCount + " wings=" + wingCount;
    }
}

public class Animal extends Creature {
    public Animal(int legCount) {
        this.wingCount = 0;
    }

}}*/

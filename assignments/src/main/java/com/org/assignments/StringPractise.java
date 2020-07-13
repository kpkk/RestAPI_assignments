package com.org.assignments;

import java.util.concurrent.SynchronousQueue;

public class StringPractise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s="pradeep"; //a-97 z-123
		String s1="PRADEEP"; //A-65 Z-90
		/*String s1=new String("pradeep");
		
		System.out.println("Content comparision-->"+s.equals(s1));
		
		System.out.println("address comparision-->" +s==s1);
		
		int a=10;
		System.out.println(a);
		a=a+5;
		System.out.println(a);
		System.out.println(s);
		
		String s2=s.concat("abc"); //pradeep+abc=pradeepabc
		System.out.println(s2);*/
		
		System.out.println(s.charAt(3));
		System.out.println(s.concat("xyz"));
		System.out.println(s.compareToIgnoreCase(s1));
		System.out.println(s.contains("xy"));
		
		System.out.println(s.endsWith("s"));
		System.out.println(s.startsWith("d"));
		
		System.out.println(s.equals(s1));
		System.out.println(s.equalsIgnoreCase(s1));
		System.out.println(s.indexOf("r"));
		
		System.out.println(s.join("-", "p","r","a","d","e","e","p"));
		
		//System.out.println(s.join("-", "25","jan","2020"));
		
		String s2=s.join("-", "25","jan","2020");
		System.out.println(s2);
		String [] split=s2.split("-");
		
		for (String string : split) {
			System.out.println(string);
			
		}
		
		System.out.println(s.substring(2)); 
		
		String empty="";
		System.out.println(empty.isEmpty());
		
		System.out.println(s.lastIndexOf("p"));
		
		System.out.println(s.length());
		
		System.out.println(s.replace("p", "d"));
		
		String trim="                             xyz               ";
		System.out.println(trim);
		
		System.out.println(trim.trim());
		System.out.println(s.toUpperCase());
		
		System.out.println(s1.toLowerCase());
		
		char[] ch=s.toCharArray();
		
		
		for (char eachCh : ch) {
			
			System.out.println(eachCh);
			
		}
		
		for(int i=0;i<ch.length;i++) {
			System.out.println(ch[i]);
		}
		
		
		
		
		
		
		

	}
	
	

}



 

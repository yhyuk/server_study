package com.test.etc.lambda;

import java.util.ArrayList;
import java.util.Comparator;

public class Ex01 {

	public static void main(String[] args) {
		

		
		// 전화기 인터페이스를 구현한 메소드
		MyClass a1 = new MyClass();
		OtherClass a2 = new OtherClass();
		
		
		
		
		
		// 클래스 객체를 클래스 변수에 담아서 사용하기
		MyClass m1 = new MyClass();
		m1.test();
		
		// 업캐스팅 - 인터페이스를 구현한 객체를 인퍼페이스 변수에 담아서 사용하기
		MyInterface m2 = new MyClass();
		m2.test();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		list.add(3);
		list.add(2);
		list.add(4);
		
		
		list.sort(new MyComparator());
		

		//list.sort(new Comparator<Integer>{
		 
		//@Override public int compare(Integer o1, Integer o2) { return o1 - o2; } });

		
		list.sort((Integer o1, Integer o2) -> { return o2 - o1 });
		
		System.out.println(list);
		
	}

}

class MyComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}

}

interface MyInterface {
	void test();
}

class MyClass implements MyInterface {

	@Override
	public void test() {
		System.out.println("실명 클래스의 객체가 구현한 메소드");
	}

	public void other() {
		System.out.println("자체 구현한 메소드");
	}
}

class OtherClass implements MyInterface {

	@Override
	public void test() {
		System.out.println("구현");
	}

	public void other() {
		System.out.println("자체 구현한 메소드");
	}
}
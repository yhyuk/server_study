package com.test.etc.lambda;

import java.util.Calendar;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Ex04 {
	
	public static void main(String[] args) {
		
		// Ex04 - 람다식
		
		/*
		 * 	함수형 인터페이스, Functional Interface
		 * 	- 람다식과 함께 자바 8부터 지원(JDK 1.8)
		 * 	- 개발자가 직접 정의 -> Ex01, Ex02, Ex03 이전 까지 했던 수업
		 * 	- JDK에서도 많은 수의 함수형 인터페이스를 제공(****) -> 여기서 배울 것
		 * 
		 * 	표준 API 함수형 인터페이스
		 * 	- 추상 메소드 1개짜리 인터페이스
		 * 	1. Consumer	: 매개변수O, 반환값X
		 * 		-> Consumer<T>
		 * 		-> BiConsumer<T, U>
		 * 		-> 우선 2가지 정도만 머릿속에 꼭 기억하면 된다.
		 * 	2. Supplier	: 매개변수X, 반환값O
		 * 		-> Supplier<T> 
		 * 		-> 기억할 것!
		 * 	3. Function	: 매개변수O, 반환값O -> 매개변수를 반환값으로 변환 후 반환
		 * 	4. Operator	: 매개변수O, 반환값O -> 매개변수를 연산 후 결과 반환 (Function 하위 버전)
		 * 	5. Predicate: 매개변수O, 반환값O -> 매개변수를 논리 연산 후 결과 반환 (Function 하위 버전)
		 * 
		 */
		
		//m1(); // Consumer
		m2(); // Supplier
		
	}

	private static void m2() {
		
		// 2. Supplier	: 매개변수X, 반환값O
		// - 서플라이어
		// - 반환값을 제공(공급)하는 역할
		// - getXXX() 메소드를 제공한다.
		
		// String get(); > 람다식으로 구현 > 반환만 해주므로 매개변수(인자)가 있으면 안됨.
		Supplier<String> s1 = () -> { return "홍길동"; };
		System.out.println(s1.get());
		
		Supplier<Integer> s2 = () -> {
			Calendar c = Calendar.getInstance();
			return c.get(Calendar.DATE);
		};
		System.out.println(s2.get());
		
		// 실행문에 return문만 존재하면 {} 생략
		Supplier<Double> s3 = () -> Math.random();
		System.out.println(s3.get());
		
		Supplier<User> s4 = () -> new User("홍길동", 20);
		System.out.println(s4.get());
		
		// IntSupplier는 Supplier<Integer>랑 동일하다고 생각하면됨.
		IntSupplier s5 = () -> 100;
		System.out.println(s5.getAsInt());
		
	}

	private static void m1() {
		
		// 1. Consumer	: 매개변수O, 반환값X
		// - 컨슈머
		// - 매개변수를 받아서 소비하는 일을 구현한다.
		// - acceptXXX() 메소드를 제공한다.
		
		// 1) 직접 만들기
		MyConsumer c1 = (String str) -> { System.out.println("문자수: " + str.length()); };
		MyConsumer c2 = str -> System.out.println("문자수: " + str.length());
		c1.accept("홍길동");
		c2.accept("컨슈머입니당.");
		
		
		// 2) consumer 인터페이스 사용
		// 종류가 많다. 
		// -> 제네릭<T> 타입이 많이 쓰인다.
		// -> 다양한 자료형을 쓸 수 있다.
		Consumer<String> c3 = str -> System.out.println("문자수: " + str.length());
		c3.accept("홍길동");
		
		Consumer<Integer> c4 = n -> {
			for(int i=0; i<n; i++) {
				System.out.println(i);
			} 
		};
		c4.accept(10);
		
		Consumer<User> c5 = user -> {
			System.out.println("[ 유저 정보 ]");
			System.out.println("이름: " + user.getName());
			System.out.println("나이: " + user.getAge());
		};
		c5.accept(new User("홍길동", 27));
		
		BiConsumer<String, Integer> bc1 = (name, age) -> System.out.printf("이름: %s, 나이: %d\n" , name, age);
		bc1.accept("홍길동", 20);
		
		BiConsumer<Integer, Integer> bc2 = (n1, n2) -> System.out.println(n1 > n2 ? n1 : n2);
		bc2.accept(100, 200);
		
		Consumer<Integer> c6 = n -> System.out.println(n * n);
		IntConsumer c7 = n -> System.out.println(n * n );
		
	}

}


// 직접
interface MyConsumer {
	void accept(String str);
}
package com.test.etc.lambda;

import java.util.Calendar;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
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
		 * 	3. Function	: 매개변수O, 반환값O -> 매개변수를 반환값으로 변환 후 반환
		 * 		-> Function<T>
		 * 		-> Bifunction<T,R>
		 * 	4. Operator	: 매개변수O, 반환값O -> 매개변수를 연산 후 결과 반환 (Function 하위 버전)
		 * 		-> BinaryOperator<T>
		 * 	5. Predicate: 매개변수O, 반환값O -> 매개변수를 논리 연산 후 결과 반환 (Function 하위 버전)
		 * 		-> Predicate<T>
		 */
		
		//m1(); // Consumer
		//m2(); // Supplier
		//m3(); // Function
		//m4();	// Operator
		//m5(); // Predicate
		
		//m6();
		m7();
		
	}

	private static void m7() {
		
		Predicate<Integer> p1 = num -> num > 0;
		Function<Boolean, String> f1 = flag -> flag ? "참" : "거짓";
		Consumer<String> c1 = str -> System.out.println(str.length());
		
		
	}

	private static void m6() {
		
		// 함수형 인터페이스
		// - 추상 메소드 1개 제공한다.
		// - 디폴트 메소드 & 정적 메소드 제공한다.
		
		// Consumer, Function, Operator
		// 1. andThen()
		//		- 이미 존재하는 람다식을 순서에 따라 연속적으로 호출할 수 있도록 결합해주는 역할
		//		- A.andthen(B) : A -> B
		// 2. compose()
		
		int n = 10;
		
		Consumer<Integer> c1 = num -> System.out.println(num > 0 ? "양수" : "음수 or 0");
		c1.accept(n);
		
		Consumer<Integer> c2 = num -> System.out.println(num % 2 == 0 ? "짝수" : "홀수");
		c2.accept(n);
		
		n = -3;
		c1.accept(n);
		c2.accept(n);
		
		// 업무(***********) -> c1과 c2를 같이 실행해야 한다 ??!
		Consumer<Integer> c3 = c1.andThen(c2); // c1 -> c2
		c3.accept(100);
		System.out.println();
		
		Function<Integer, Boolean> f1 = num -> num > 0;
		Function<Boolean, String> f2 = flag -> flag ? "양수" : "음수";
		
		Function<Integer, String> f3 = f1.andThen(f2);
		
		System.out.println(f3.apply(10));
		System.out.println();
		
		
		Function<Integer, String> f4 = f2.compose(f1);
		System.out.println(f4.apply(10));
		
		Predicate<Integer> p1 = num -> num % 2 == 0; // 2의 배수 
		Predicate<Integer> p2 = num -> num % 3 == 0; // 3의 배수
		
		System.out.println(p1.test(2));
		System.out.println(p1.test(3));
		System.out.println();
		
		System.out.println(p2.test(2));
		System.out.println(p2.test(3));
		System.out.println();
		
		Predicate<Integer> p3 = p1.and(p2); // p1의 결과 && p2의 결과
		System.out.println(p3.test(6)); // 2의 배수 & 3의 배수 -> true
		System.out.println(p3.test(5)); // 2의 배수 & 3의 배수 -> false
		System.out.println();
		
		Predicate<Integer> p4 = p1.or(p2); // p1의 결과 || p2의 결과
		System.out.println(p4.test(2)); // 2의 배수 or 3의배수 -> true
		System.out.println(p4.test(3)); // 2의 배수 or 3의배수 -> true
		System.out.println(p4.test(5)); // 2의 배수 or 3의배수 -> false
		System.out.println();
		
		Predicate<Integer> p5 = p1.negate(); // !p1
		System.out.println(p5.test(2));
		System.out.println(p5.test(3));
		System.out.println();
	}

	private static void m5() {
		
		// 5. Predicate: 매개변수O, 반환값O 
		// - 매개변수의 값을 받아 무언가 조사한 후 논리값을 반환한다.
		// - testXXX()
		
		Function<Integer, Boolean> f1 = n -> n > 0;
		Predicate<Integer> p1 = n -> n > 0;
		
		if (p1.test(10)) { 
			System.out.println("양수");
		} else {
			System.out.println("음수 or 0");
		}
		
		BiPredicate<Integer, Integer> bp1 = (a, b) -> a > b;
		System.out.println(bp1.test(20, 10));
		
	}

	private static void m4() {
		
		// 4. Operator	: 매개변수O, 반환값O 
		// - 매개변수를 연산 후 결과 반환 (Function 하위 버전)
		// - applyXXX()
		// - Operator는 Function과 다르게 매개변수의 타입과 반환값의 타입이 동일하다.(연산자들이 보통 이러한 성질을 가지기 때문에.. 흉내)
		
		BiFunction<Integer,	Integer, Integer> bf1 = (a, b) -> a * b;
		BinaryOperator<Integer> bo1 = (a, b) -> a * b;
		System.out.println(bf1.apply(10, 20));
		System.out.println(bo1.apply(10, 20));
		
	}

	private static void m3() {
		
		// 3. Function	: 매개변수O, 반환값O 
		// - 매개변수를 반환값으로 변환 후 반환
		// - applyXXX() 메소드를 제공한다.
		
		
		// <T> the type of the input to the function
		// <R> the type of the result of the function
		
		// - Integer apply(String s) -> 람다식
		Function<String, Integer> f1 = str -> str.length();
		System.out.println(f1.apply("홍길동"));
		System.out.println(f1.apply("안녕하세요. 홍길동입니다."));
		
		Function<Integer, String> f2 = num -> num > 0 ? "양수" : "음수 or 0";
		System.out.println(f2.apply(100));
		System.out.println(f2.apply(-100));
		
		BiFunction<Integer, Integer, Integer> f3 = (a, b) -> a + b;
		System.out.println(f3.apply(20, 30));
		
		BiFunction<Integer, Integer, Boolean> f4 = (a, b) -> a > b;
		System.out.println(f4.apply(20, 10));
		System.out.println(f4.apply(10, 20));
		
		BiFunction<String, Integer, String> f5 = (str, index) -> str.substring(index, index + 1);
		System.out.println(f5.apply("홍길동입니다.", 3));
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

// 직접 인터페이스 만들기
interface MyConsumer {
	void accept(String str);
}

















// 인터페이스의 멤버
// - 구현 멤버를 가질 수 없다.
// 1. 추상 메소드만 가진다.
// 2. 정적 메소드를 가진다.
// 3. 디폴트 메소드를 가진다.
interface ITest {
	
	// 추상 멤버
	void test();
	void aaa();
	void bbb(int a);
	
	// 구현(일반) 멤버 -> 에러
	// private int a;
	// public void ccc() {}
	
	// 정적 멤버 변수
	public static final int a = 10;
	
	// 디폴트 메소드
	public default void ccc() {
		
	}
	
	// 정적 메소드
	public static void bbb() {
		
	}
	
}









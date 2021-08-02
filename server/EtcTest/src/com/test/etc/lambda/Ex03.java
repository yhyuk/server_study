package com.test.etc.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Ex03 {
	
	public static void main(String[] args) {
		
		/**
		 * Ex03.java
		 * 람다식
		 * - 1회식 메소드를 만드는 기법
		 * - 익명 객체의 추삭 메소드를 간편하게 표기하는 기법
		 * 
		 * 목적!!
		 * - 람다식은 목적이 아닌 도구로써 사용한다고 생각하자.
		 * - RedInterface를 구현 + 딱 1번만 > 그 안의 추상 메소드 color() 구현하는 것. > 목적
		 * - RedInterface를 구현 + 여러번 > 실명 클래스 구현
		 * 
		 */
		
		// 익명 클래스 생성
		RedInterface r1 = new RedInterface() {
			
			// 추상메소드 구현(Override)
			@Override
			public void color() {
				System.out.println("익명 객체를 통한 추상메소드 구현");
			}
		};
		
		r1.color();
		// 익명 클래스를 생성할 때 누구나 다 똑같은 보일러플레이트 코드를 쓰지 않기 위해 태어난 것이 람다식!
		
		
		// [ 익명 클래스 -> 람다식 ]
		// -> 코드 비용 절감 + 가독성 향상
		
		// why? 추상메소드를 1개 더 추가할 경우
		// -> Error:The target type of this expression must be a functional interface
		
		// 방법1
		RedInterface r2 = () -> {
			System.out.println("익명 객체를 통한 추상메소드 구현");
		};
		r2.color();
		
		// 방법2 - 실행 코드가 한개의 문장일 경우...
		RedInterface r3 = () -> System.out.println("익명 객체를 통한 추상메소드 구현");
		r3.color();
		
		// 매개변수 ======================================================================
		YellowInterface y1 = (String c) -> System.out.println(c);
		y1.color("Yellow ~_~");
		
		YellowInterface y2 = c -> {
			if(c.equals("Yellow")) {
				System.out.println("노란색");
			} else if (c.equals("Red")) {
				System.out.println("빨간색");
			}
		};
		y2.color("Yellow");
		
		// 반환값 + 매개변수 ======================================================
		BlueInterface b1 = (c1, c2) -> { return c1+c2; };
		BlueInterface b2 = (c1, c2) -> c1+c2;
		
		System.out.println(b1.color("sky", "blue"));
		System.out.println(b2.color("cornflower", "blue"));
		
		// 여태까지의 수업 내용에서 람다식을 사용할 만한 곳??
		// 1. 배열의 정렬
		// 2. 컬렉션의 정렬
		// 3. 익명 객체 구현 시
		
		// 순수 배열
		Integer[] nums = { 10, 40, 20, 50, 30 };
		
		// ArrayList<String>
		ArrayList<String> colors = new ArrayList<String>();
		colors.add("P");
		colors.add("A");
		colors.add("Z");
		colors.add("E");
		colors.add("C");
		colors.add("Y");
		
		// ArrayList<User>
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("김사장", 50));
		users.add(new User("고대리", 50));
		users.add(new User("김신입", 50));
		users.add(new User("윤차장", 50));
		users.add(new User("정사원", 50));
		users.add(new User("홍과장", 50));
		
		// 순수 배열 정렬 하기 =====================================  
		// 방법1
		Arrays.sort(nums);
		
		// 방법2
		Arrays.sort(nums, new Comparator<Integer>() {
		
			@Override
			public int compare(Integer o1, Integer o2) {
			return 0;
			}
		});
		
		// 방법3 - 람다식
		Arrays.sort(nums, (o1, o2) -> o1 - o2);
		
		// 방법1,2,3 다 똑같다. ->  람다식이 제일 간결하고 편하단걸 알 수 있음.
		System.out.println(Arrays.toString(nums));
		
		// ArrayList 배열 정렬 하기 =====================================  
		colors.sort((n1, n2) -> n1.compareTo(n2));
		colors.sort((n1, n2) -> n2.compareTo(n1));
		System.out.println(colors);
		
		users.sort((u1, u2) -> u1.getAge() - u2.getAge());
		users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
		System.out.println(users);
		
		
		
	}
}

// 람다식을 저장할 인터페이스
// - Target Type
// - Functional Interface = 물리적(추상 메소드 1개 보유) + 용도(람다식을 저장)
// - 반드시 추상 메소드를 딱 1개만 가질 수 있다.


@FunctionalInterface
interface RedInterface {
	void color();
	//void test(); -> @FunctionalInterface가 있으므로 2개 불가
}

@FunctionalInterface
interface YellowInterface {
	void color(String c);
}

@FunctionalInterface
interface BlueInterface {
	String color(String c1, String c2);
}

class User {
	private String name;
	private int age;
	
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		return String.format("%s[%d]", this.name, this.age);
	}
	
}
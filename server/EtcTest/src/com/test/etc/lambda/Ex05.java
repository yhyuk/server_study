package com.test.etc.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex05 {

	public static void main(String[] args) {
		
		// 람다식
		// 1. 배열, 컬렉션 > sort()
		// 2. 스트림(************)
		
		// 자바의 스트림
		// 1. 입출력 스트림
		// 		- 파일 입출력, 콘솔 입출력, 네트워크 입출력
		// 2. 스트림
		// 		- Java 8(JDK.18)
		// 		- 배열, 컬렉션의 탐색을 지원(***********)
		
		// 스트림
		// - 배열, 컬렉션을 탐색하는 방법
		// - 람다식 사용
		
		// 배열, 컬렉션 탐색
		// 1. for문
		// 2. 향상된 for문
		// 3. iterator
		// 4. 스트림
		
		m1();
		
	}

	private static void m1() {
		
		int[] nums = { 1, 8, 2, 3, 5, 9, 6, 4, 7 };
		ArrayList<Integer> nums2 = new ArrayList<Integer>();
		
		// 탐색
		// 1. for문
		for (int i=0; i<nums.length; i++) {
			System.out.printf("%4d", nums[i]);
			nums2.add(nums[i]);
		}
		System.out.println();
		
		// 2. 향상된 for문
		for (int n : nums) {
			System.out.printf("%4d", n);
		}
		System.out.println();
		
		// 3. iterator
		Iterator<Integer> iter = nums2.iterator();
		
		while (iter.hasNext()) {
			System.out.printf("%4d", iter.next());
		}
		System.out.println();
		
		// 4. Stream
		// - stream() 메소드 제공 > Stream 탐색 가능
		// - Arrays.stream(배열)
		// - 컬렉션.stream()
		// - 함수 지향형 프로그램
		
		IntStream istream = Arrays.stream(nums);
		Stream<Integer> stream = nums2.stream();
		
		// 스트림 지원 메소드는 다양하다.
		// - 대표적으로 많이 쓰는것은 forEach()
		Consumer<Integer> c1 = n -> System.out.println(n);
		// c1.accept(10);
		
		nums2.stream().forEach(c1); // c1을 9번 호출
		System.out.println();
		
		// 최종 형태 > 탐색
		nums2.stream().forEach(n -> System.out.println(n));
		
		/**
		 * 	기시감
		 * 	- JavaScript
		 * 
		 *	let list = [ 10, 20, 30, 40, 50 ];
		 *
		 *	list.forEach(function(index, item) {
		 *		// 배열의 요소를 하나 넘겨준 뒤 함수 한번 호출 x 5번
		 *	});
		 */
		
		System.out.println();
		Arrays.stream(nums).forEach(n -> System.out.printf("%4d", n));
		System.out.println();
		
		
		//=========================================================
		// 이름이 3글자 이상과 미만을 구분해서 출력하세요.
		List<String> names = new ArrayList<String>(); 
		
		names.add("홍길동");
		names.add("홍길동동동");
		names.add("길동");
		names.add("홍");
		names.add("홍동동동동오동도오동길동");
		
		
		// 방법1 - for문 안에서 if 조건문 하기
		// for() { if() }
		
		// 방법2 - stream.forEach(람다식)
		names.stream().forEach(name -> {
			if (name.length() >= 3) {
				System.out.println("긴이름: " + name);
			} else {
				System.out.println("짧은이름: " + name);
			}
		});
		
		// 코드가 헷갈리지만... 그 이유는
		// 객체 지향 프로그래밍 방식 -> 함수형 프로그래밍 방식으로 변했기 때문에 낯설어서 그렇다!!
		
	}
}

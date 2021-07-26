package com.test.etc.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex02 {
	
	public static void main(String[] args) throws Exception {
		
		
		// Ex02.java
		
		System.out.println("네이버 영화 랭킹");
		
		String url="https://movie.naver.com/movie/sdb/rank/rmovie.naver";
		
		// doc -> 해당 페이지의 모든 내용을 담고 있는 문서 객체(JavaScript의 document 객체같은 역할)
		Document doc = Jsoup.connect(url).get();
		
		// 제목 찾기
		System.out.println(doc.title()); 
		
		// 선택자 찾기 -> 우리가 찾을 선택자를 찾으면됨. (개발자의 재량에 따라 달라짐..)
		// .title > tit3 > a
		Elements list = doc.select(".title > .tit3 > a");
		
		System.out.println(list.size());
		
		for (Element item : list) {
			// System.out.println(item);
			System.out.println(item.text());		// PCDATA
			System.out.println(item.attr("href"));  // 속성
		}
		
		/**
		 * 크롤링이 어려운 이유?
		 * 예제 네이버영화 페이지는 간단한 소스코드로 되있어서 가능했지만,
		 * 왠만하면 페이지 선택자 패턴 찾기가 어렵다. 그래서 잘 나오기 어려운데
		 * 패턴 찾기가 어려운 페이지는 크롤링을 포기하는 경우도 많다.
		 */
		
		
	}

}

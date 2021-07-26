package com.test.etc.crawling;

import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex03 {
	
	public static void main(String[] args) throws Exception {
		
		// 네이버 현재 상영 영화
		String url = "https://movie.naver.com/movie/running/current.naver";
		
		Document doc = Jsoup.connect(url).get();

		// 소스 분석 (개발자 재량에 따라..)
		
		
		// div.lst_wrap > ul.lst_detail_t1 > li : 문서 전체에서 검색
		Elements list = doc.select("div.lst_wrap > ul.lst_detail_t1 > li");
		
		MovieDAO dao = new MovieDAO();
		
		
		for ( Element item : list ) {
			// <li> 태그?
			
			// 내가 원하는 정보의 <li>태그가 맞는지 테스트 확인
			// System.out.println(item);
			// System.out.println("=================================================================");
		
			
			// item.select(".tit > a"): <li> 내부에서만 검색
			String title = item.select(".tit > a").text();
			// System.out.println(title);
			
			
			// 오!! 제목 찾았다! 이제 다른 데이터도 찾아보자.
			// String outline = item.select("dd:nth-child(2) .link_txt").text(); // <span> <a>
			// System.out.println(outline);
			
			// 선택자 찾는 꿀팁
			// 찾고자하는 문구 인스트럭터 찍고 개발자모드 > 코드 > 우클릭 > selector copy 
			String temp = item.select(".info_txt1 dd:nth-child(2)").text();
			// 애니메이션, 코미디, 모험 | 107분 | 2021.07.21 개봉
			// 94분 | 1955.04.06
			
			temp = temp.replace(" ", "").replace("개봉", "");
			
			String[] templist = temp.split("\\|"); // "|"는 예약어로 인식되기때문에 이스케이프 \\ 꼭 시킬것
			
			
			String outline = "";
			String time = "";
			String rdate = "";
			
			//System.out.println(Arrays.toString(templist));
			
			if (templist.length == 2) {
				time = templist[0].replace("분", "");
				rdate = templist[1];
			} else {
				outline = templist[0];
				time = templist[1].replace("분", "");
				rdate = templist[2];
			}
			
			
			 System.out.println(outline); System.out.println(time);
			 System.out.println(rdate);
			 System.out.println("===========================================");
					
			String director = item.select("dd:nth-child(2) .link_txt").text();
			//System.out.println(directer);
			
			String actor = item.select("dd:nth-child(6) .link_txt").text();
			//System.out.println(actor);
			
			String poster = item.select(".thumb > a > img").attr("src");
			//System.out.println(poster);
			
			// DB 작업
			MovieDTO dto = new MovieDTO();
			
			if (title == null || title.equals("")) title = "제목없음";
			if (outline == null || outline.equals("")) outline = "제목없음";
			if (time == null || time.equals("")) time = "0";
			if (rdate == null || rdate.equals("")) rdate = "제목없음";
			if (director == null || director.equals("")) director = "제목없음";
			if (actor == null || actor.equals("")) actor = "제목없음";
			if (poster == null || poster.equals("")) poster = "제목없음";
			
			dto.setTitle(title);
			dto.setOutline(outline);
			dto.setTime(time);
			dto.setRdate(rdate);
			dto.setDirector(director);
			dto.setActor(actor);
			dto.setPoster(poster);
			
			dao.add(dto);
		
		} // forEach
		
		System.out.println("완료되었습니다.");
	} // main

}

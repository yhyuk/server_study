package com.test.etc.crawling;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ex04 {
	
	public static void main(String[] args) throws Exception {
		
		// Ex04.java
		
		/*
		 * Jsoup 사용방식
		 * - 인증이 불필요한 사이트만 사용 가능
		 * - 인증이 필요한 페이지에서는 사용 불가능
		 * - JavaScript 실행이 필요한 페이지에서는 사용 불가능(Ajax가 대부분을 차지한다.) 
		 * 
		 * 
		 * 웹 응용 프로그램(웹 사이트) 자동화 프로그램
		 * - 셀레니움
		 * - 사람 대신에 프로그램이 사람처럼 웹 사이트에 방문해서 여러가지 행동(테스트)를 하는 프로그램
		 * - 크롤링 작업이 가능하다.
		 * 			-> 크롬 버전 확인: 버전 92.0.4515.107(공식 빌드) (64비트
		 * 			-> 구글에 크롬 웹 드라이버, 엣지 웹 드라이버 버전에 맞게 다운로드
		 * 
		 * 
		 */
		
		// m1(); // 쌍용교육센터 페이지 크롤링 안되는 이유 확인하기
		// m2(); // 브라우저 vs Jsoup
		// m3();	// 웹 응용 프로그램(웹 사이트) 자동화 프로그램. 1
	    //m4();		// 2
		m5(); 		// 3
		
		
	}

	private static void m5() {
		
		// 웹 테스트 > 목적에 맞는 시나리오 구성(******)
		
		String webDriverID = "webdriver.chrome.driver";
		String path = "D:\\webdriver\\chromedriver.exe";
		
		System.setProperty(webDriverID, path);
		
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		
		WebDriver driver = new ChromeDriver(options);
		
		String url = "http://localhost:8090/myapp/member/login.do";
		
		driver.get(url);
		
		
		// 아무개 - 로그인
		WebElement btn = driver.findElement(By.cssSelector("body > section > div > button:nth-child(2)"));
		btn.click();
		
		// 글쓰기 페이지
		url = "http://localhost:8090/myapp/board/add.do?reply=0";
		driver.get(url);
		
		WebElement subject = driver.findElement(By.id("subject"));
		subject.sendKeys("셀리니움을 통해서 작성중입니다.");
		WebElement content = driver.findElement(By.id("content"));
		content.sendKeys("내용입니다. ");
		
		btn = driver.findElement(By.cssSelector("body > section > form > div > button.btn.btn-primary"));
		btn.click();
		
	}

	private static void m4() {
		
		String webDriverID = "webdriver.chrome.driver";
		String path = "D:\\webdriver\\chromedriver.exe";
		
		System.setProperty(webDriverID, path);
		
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		
		WebDriver driver = new ChromeDriver(options);
		
		String url = "https://www.banapresso.com/menu";
		
		driver.get(url);
		
		List<WebElement> list = driver.findElements(By.className("menu_box"));
		
		for (WebElement item : list) {
			String name = item.findElement(By.tagName("strong")).getText();
			String price = item.findElement(By.className("price")).getText();
			System.out.println(name + "," + price);
		}
		

		
	}

	private static void m3() {
		
		// 준비 작업
		//	- 크롬 버전 확인: 버전 92.0.4515.107(공식 빌드) (64비트)
		//	- 구글 검색 > 크롬 웹 드라이버, 엣지 웹 드라이버 버전에 맞게 다운로드
		//	- 셀레니움 다운로드 > https://www.selenium.dev/downloads/ > Java버전
		//	- jar파일 6개 lib폴더에 넣기 > build path에 add하기
		
		// 웹 드라이버 경로
		String webDriverID = "webdriver.chrome.driver";
		String path = "D:\\webdriver\\chromedriver.exe";
		System.setProperty(webDriverID, path);
		
		// 웹 드라이브 설치
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		
		WebDriver driver = new ChromeDriver(options);
		
		
		// 여기서부터 업무 시작
		String url = "http://lms1.sist.co.kr/worknet/SLogin.asp";
		
		driver.get(url);
		
		// 아이디 텍스트 박스(<input 태그>)
		WebElement id = driver.findElement(By.id("strLoginID"));
		id.sendKeys("김영혁");
		WebElement pw = driver.findElement(By.id("strLoginPwd"));
		pw.sendKeys("2841");
		
		WebElement btn = driver.findElement(By.cssSelector("#content > div > form > table > tbody > tr > td > div > div.login-form > div.login-btn > input"));
		btn.click();
		
		// 페이지 전환시 트랙픽
		try {
			driver.wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private static void m2() throws IOException {
		
		String url = "https://www.banapresso.com/menu";
		
		/*
		 * 브라우저 vs Jsoup 
		 * - 공통점
		 *  	: 웹서버에 접근해서 소스를 받아온다.
		 *  
		 * - 차이점
		 * 		: 브라우저는 받아온 소스의 HTML, CSS, JavaScript를 실행하는 능력이 있다. > 실행한다.
		 * 		: Jsoup는 받아온 소스의 HTML를 분석하는 능력이 있다.
		 * 
		 */
		
		
		// Jsoup <> 브라우저
		Document doc = Jsoup.connect(url).get();
		
		// 문서 전체 소스
		System.out.println(doc.html());
		
	}

	private static void m1() throws IOException {
		String url = "http://lms1.sist.co.kr/worknet/SMember/index.asp?strCode=I202103260006";
		String selector = "#content > div > div > div > div.panel-body > form > table > thead > tr:nth-child(5) > td:nth-child(2)";
		
		Document doc = Jsoup.connect(url).get();
		
		Elements list = doc.select(selector);
		
		// 2021-03-29 ~ 2021-09-03
		System.out.println(list.get(9).text()); // ERROR
		
		
		// 현재 위의 URL은 접속이 불가능하다. (인증 받아야함)
	}

}

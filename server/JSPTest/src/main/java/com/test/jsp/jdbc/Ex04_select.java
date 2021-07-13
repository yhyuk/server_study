package com.test.jsp.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex04_select {
	
	public static void main(String[] args) {
		
		// Ex04_select.java
		
		// 반환값이 있는 쿼리 -> select문
		//m1(); // 인원수
		//m2(); // 이름
		//m3(); // 다중컬럼   결과셋: 1개의 레코드 + 여러 컬럼
		//m4(); // 다중레코드 결과셋: N개의 레코드 + 1개 컬럼
		m5();	// N개의 레코드 + N개의 컬럼
	}

	private static void m5() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress order by seq asc";
			
			rs = stat.executeQuery(sql);
			
			while ( rs.next() ) {
				
				String seq = rs.getString("seq");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				
				System.out.println("seq: " + seq);
				System.out.println("name: " + name);
				System.out.println("age: " + age);
				System.out.println("gender: " + gender);
				System.out.println("address: " + address);
				System.out.println();
				
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m4() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select name from tblAddress order by name asc";
			
			rs = stat.executeQuery(sql);
			
			// rs.next() 은 몇번? -> 몇개가 있을지 모른다. -> 루프
			
			while( rs.next() ) {
				
				String name = rs.getString("name");
				System.out.println("name: " + name);
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m3() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress where seq = 5";
			
			rs = stat.executeQuery(sql);
			
			if ( rs.next() ) {
				
				String seq = rs.getString("seq");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				
				System.out.println("seq: " + seq);
				System.out.println("name: " + name);
				System.out.println("age: " + age);
				System.out.println("gender: " + gender);
				System.out.println("address: " + address);
			} 
			
			
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m2() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; 
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select name from tblAddress where seq = 5";
			
			rs = stat.executeQuery(sql); // 실행
			
			
			// rs.next()
			// 	-> 존재하면 true
			//  -> 존재하지 않으면 false
			if ( rs.next() ) {
				 
				 // getString() 오버로딩
				 // - getString(int index)
				 //		-> 인덱스 번호가 헷갈림.. 가독성 저하
				 
				 // - getString(String label)
				 // 	-> 해당 컬럼의 label을 입력하면 가독성 향상
				 
				 // String name = rs.getString(1);
				 String name = rs.getString("name");
				 
				 System.out.println("이름: " + name);
				 
			 } else {
				 System.out.println("5번 레코드가 없습니다.");
			 }
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m1() {
		
		// 단일값 반환 select
		// -> select -> 1행 1열
		// -> select -> N행 N열
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null; // ResultSet -> 결과 테이블
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select count(*) from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			// 커서를 다음 레코드로 전진시킨다.
			rs.next();
			
			// 커서가 가리키고 있는 레코드의 특정 컬럼값을 가져온다.
			// rs.getXXX();
			int count = rs.getInt(1);
			System.out.println("인원수: " + count);
			
			String strCount = rs.getString(1);
			System.out.println("인원수: " + strCount);
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

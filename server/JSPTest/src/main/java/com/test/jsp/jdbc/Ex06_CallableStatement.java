package com.test.jsp.jdbc;

import java.sql.*;

import oracle.jdbc.OracleTypes;

public class Ex06_CallableStatement {
	
	public static void main(String[] args) {
		
		// Ex06_CollableStatement.java
		
		/*
		 * 	1. Statement: 정적 쿼리 실행
		 * 	2. PreparedStatement: 동적 쿼리 실행
		 * 	3. CallableStatement: 프로시저 실행. ( PreparedStatement 유사하다. )
		 */
		
		//m1(); 	// 프로시저 ( 매개변수 X ) 호출
		///m2(); 	// 프로시저 ( 매개변수 O ) 호출 
		//m3();	// 프로시저의 아웃 파라미터 받기
		//m4();
		m5();
	
	} // main
	
	private static void m5() {
		
		// 프로시저
		// - select -> 다중 레코드 반환(= 커서반환)
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "{ call proc_m5(?,?) }";
			
			conn = DBUtil.open();
			stat = conn.prepareCall(sql);
			
			// in
			stat.setString(1, "m");
			
			// out
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			// 실행
			stat.executeQuery();
			
			// 이게 포인트 **** 프로시저에서 반환할 커서는 자바에서 ResultSet으로 받는다.
			rs = (ResultSet)stat.getObject(2);
			
			while ( rs.next() ) {
				System.out.println(rs.getString("name") + 
									", " + rs.getString("age"));
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m4() {
		
		// 프로시저 out 매개변수
		// - insert, update, delete -> 매개변수 , 결과 (성공유무)
		// - select -> 단일 레코드 반환
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			// select문 실행 -> 단일 레코드
			String sql = "{ call proc_m4(?,?,?,?,?) }";
			
			conn = DBUtil.open();
			stat = conn.prepareCall(sql);
			
			// in (값 넣기)
			stat.setString(1, "45");
			
			// out (등록하기)
			stat.registerOutParameter(2, OracleTypes.VARCHAR);
			stat.registerOutParameter(3, OracleTypes.NUMBER);
			stat.registerOutParameter(4, OracleTypes.VARCHAR);
			stat.registerOutParameter(5, OracleTypes.VARCHAR);
			
			// 실행하기
			stat.executeQuery();
			
			// 값 접근
			String name = stat.getString(2);
			int age = stat.getInt(3);
			String gender = stat.getString(4);
			String address = stat.getString(5);
			
			System.out.println(name);
			System.out.println(age);
			System.out.println(gender);
			System.out.println(address);
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m3() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			String sql = "{ call proc_m3(?,?,?,?,?) }";
			
			conn = DBUtil.open();
			stat = conn.prepareCall(sql);
			
			// in 매개변수 x 4개
			// stat.setString(1, "고양이고양이고양이고양이고양이고양이고양이고양이고양이고양이고양이고양이고양이");
			stat.setString(1, "고양이");
			stat.setString(2, "25");
			stat.setString(3, "f");
			stat.setString(4, "서울시 강남구 대치동");
			
			// out 매개변수 x 1개
			stat.registerOutParameter(5, OracleTypes.NUMBER); // ***********
			stat.executeUpdate();
			
			// 결과값
			String result = stat.getString(5);
			System.out.println("결과: " + result); // *****************
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m2() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			// String sql = "{ call proc_m2('홍길동', 20, 'm', '서울시..') }";
			String sql = "{ call proc_m2(?,?,?,?) }";
			
			
			conn = DBUtil.open();
			stat = conn.prepareCall(sql);
			
			stat.setString(1, "홍길동");
			stat.setString(2, "20"); // number로 들어갈지 varchar2로 들어갈지는 jdbc가 알아서 해줌
			stat.setString(3, "m");
			stat.setString(4, "서울시 강남구 역삼동");
			
			stat.executeUpdate();
			
			System.out.println("완료");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void template() {
		
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "{ call }";
			
			conn = DBUtil.open();
			stat = conn.prepareCall(sql);
			
			//rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m1() {
		
		Connection conn = null;
		CallableStatement stat = null;
		
		try {
			
			String sql = "{ call proc_m1 }";
			
			conn = DBUtil.open();
			stat = conn.prepareCall(sql);
			
			System.out.println(stat.executeUpdate());
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

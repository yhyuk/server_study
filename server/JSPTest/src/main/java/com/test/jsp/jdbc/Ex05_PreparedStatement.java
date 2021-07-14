package com.test.jsp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Ex05_PreparedStatement {
	
	public static void main(String[] args) {
		
		// Ex05_PreparedStatement.java
		
		/*
		 * 	[ 어제 JDBC 1일차 배운것 ]
		 *	1. Connection
		 *		- open()
		 *		- close()
		 * 	
		 * 	2. Statement
		 * 		- executeUpdate(sql)
		 * 		- executeQuery(sql)
		 * 
		 *  3. ResultSet
		 *  	- next()
		 *  	- getXXX()
		 * 
		 */
		
		// insert 실행
		// "insert into tblAddress(seq, name, age, gender, address) values(seqAddress.nextval, '김영혁', 20, 'm', '인천시 남동구 논현동')";
		
		// Statement vs PreparedStatement
		//	- 정적 쿼리(매개변수가 없는 쿼리) 
		// 		-> Statement 사용 권장(간단함)
		// 	- 동적 쿼리(매개변수가 있는 쿼리)
		//		-> PreparedStatement 사용 권장(안정성)
		
		
		// m1(); // Statement
		// m2(); // PreparedStatement
		m3();
		
	}
	
	private static void m3() {
		
		// 정적 or 동적 ? 
		// -> 정적 쿼리 -> Statement
		// String sql = "select * from tblAddress";
		
		// 정적 or 동적 ?
		// '홍길동' 부분이 사용자 입력?
		// -> 동적 쿼리 -> PreparedStatement
		String sql = "select * from tblAddress where name = '홍길동'"; // '홍길동': 사용자 입력값
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.prepareStatement(sql);
			
			Scanner scan = new Scanner(System.in);
			System.out.print("이름: ");		
			String name = scan.nextLine();
			
			stat.setString(1, name);
			
			rs = stat.executeQuery();
			
			while ( rs.next() ) {
				System.out.println(rs.getString("name") + "," + rs.getString("address"));
			}
			
			rs.close();
			stat.close();
			stat.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m2() {
		
		// 사용자 입력
		String name = "호'랑이";
		int age = 25;
		String gender = "f";
		String address = "서울시 강남'구 대치동";
		
		
		Connection conn = null;
		PreparedStatement stat = null;
		
		try {
			
			// SQL > '?' > 오라클 매개변수(오라클 문법) == String.format()과 유사한 역할
			// 주의사항! 물음표(?) 입력할때 따옴표 넣지말 것. -> 우리가 자바 변수에서 따옴표 붙이는거랑 똑같다.
			String sql = "insert into tblAddress(seq, name, age, gender, address) "
					+ "values(seqAddress.nextval, ?, ?, ?, ?)";
			
			conn = DBUtil.open();
			
			// prepareStatement 메소드는 인자가 없는것이 없다.
			// prepareStatement 메소드는 sql쿼리를 먼저 만들어야함.
			stat = conn.prepareStatement(sql);
			
			// 매개 변수를 대입하는 과정에서 유효하지 않은 문자를 예외처리 한다.
			// -> 안정성 향상
			// -> Statement와 다르게 문자에 호따옴표를 입력하려할 때, replace를 안해도 된다.
			stat.setString(1, name); // 1번째 ?(매개변수)에 name 변수값 넣기
			stat.setInt(2, age);
			stat.setString(3, gender);
			stat.setString(4, address);
			
			System.out.println(stat.executeUpdate()); // 이미 sql문구가 stat에 들어가있기 때문에 sql이 들어가지않음 
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m1() {
		// 사용자 입력
		String name = "홍길동";
		int age = 20;
		String gender = "m";
		String address = "서울시 강남구 역삼'동";
		
		// 만약 데이터에 호따옴표가 필요로 할때는?
		address = address.replace("'", "''");
		
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
					
			String sql = String.format("insert into tblAddress(seq, name, age, gender, address) "
					+ "values(seqAddress.nextval, '%s', %d, '%s', '%s')", name, age, gender, address);
			
			System.out.println(sql);
			System.out.println(stat.executeUpdate(sql));
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

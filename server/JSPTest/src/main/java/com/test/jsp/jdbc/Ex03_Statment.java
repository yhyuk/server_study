package com.test.jsp.jdbc;

import java.sql.Connection;
import java.sql.Statement;

public class Ex03_Statment {

	public static void main(String[] args) {

		// Ex03_Statment.java

		/*
		 * Statement 클래스 - 모든 SQL 구문을 실행하는 역할
		 * 
		 * 
		 * Statement 종류 
		 * 1. Statement - 기본
		 * 
		 * 2. PreparedStatement - 인자값과 관련된 작업을 특화 - 코드 안정성 높음, 가독성 높음 - 코드량이 많음
		 * ------------------------------------------------------ 텍스트 쿼리 실행용 
		 * 3. CallableStatement - 프로시저 전용 Statement
		 * ------------------------------------------------------ 프로시저 실행용
		 */

		Connection conn = null;

		try {

			conn = DBUtil.open();
			System.out.println(conn.isClosed() ? "연결 실패" : "연결 성공");

			// 질의
			// - SQL 언어 사용 > 문자열 취급
			// - 문자열로 SQL을 미리 준비한다.
			// String sql = "insert into tblAddress(seq, name, age, gender, address) values(seqAddress.nextval, '김영혁', 20, 'm', '인천시 남동구 논현동')";
			// String sql = "update tblAddress set age = age + 1 where name '김영혁'";
			String sql = "delete from tblAddress";
			
			// Statement
			// - Connection을 통해서 생성한다.
			// - Statement 객체가 실행할 SQL을 어느 서버에서 실행할지 모르기 때문에 그 정보를 가지고 있는 Connection과 연관되어 있다.
			Statement stat = conn.createStatement();
			
			// SQL 실행
			// 1. 반환값이 없는 쿼리
			// - (int) stat.executeUpdate()
			// - 적용된 행의 갯수
			
			// 2. 반환값이 있는 쿼리
			// - stat.executeQuery()
			
			int result = stat.executeUpdate(sql); // SQL-Developer에서 Ctrl+Enter와 동일한 행동 (쿼리 실행 ***)
			
			System.out.println("result: " + result);
			
			
			
			// 종료
			stat.close();
			conn.close();
			
			System.out.println("완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

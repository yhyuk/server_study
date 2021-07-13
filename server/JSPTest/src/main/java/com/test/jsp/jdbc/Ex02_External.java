package com.test.jsp.jdbc;

import java.sql.Connection;

public class Ex02_External {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.open();
			
			System.out.println(conn.isClosed()); // false
			
			conn.close();
			
			System.out.println(conn.isClosed()); // true
			
		} catch (Exception e) {
			System.out.println("Ex02_External.main()");
			e.printStackTrace();
		}
		
		
		
	}

}

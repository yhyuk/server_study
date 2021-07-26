package com.test.etc.crawling;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MovieDAO {
	
	private Connection conn;
	private PreparedStatement stat;
	
	public MovieDAO() {
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(MovieDTO dto) {
		
		try {
			String sql = "insert into tblMovie (seq, title, outline, time, rdate, director, actor, poster) "
					+ "values (seqMovie.nextVal, ?, ?, ?, ?, ?, ?, ?)";
			
			stat = conn.prepareStatement(sql);
			
			stat.setString(1, dto.getTime());
			stat.setString(2, dto.getOutline());
			stat.setString(3, dto.getTime());
			stat.setString(4, dto.getRdate());
			stat.setString(5, dto.getDirector());
			stat.setString(6, dto.getActor());
			stat.setString(7, dto.getPoster());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

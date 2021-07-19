package com.test.myapp.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.myapp.DBUtil;

public class BoardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public BoardDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// AddOk 서블릿이 DTO를 줄테니, insert를 해주세요.
	public int add(BoardDTO dto) {
		
		try {
			
			String sql = "insert into tblBoards (seq, id, subject, content, regdate, readcount, tag)"
					+ " values (seqBoards.nextVal, ?, ?, ?, default, default, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getSubject());
			pstat.setString(3, dto.getContent());
			pstat.setString(4, dto.getTag());
			
			return pstat.executeUpdate(); // 성공시 1 실패시 0
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	// List 서블릿 - 게시판 목록을 보여주세요..
	public ArrayList<BoardDTO> list() {
		
		try {
			
			String sql = "select * from vwBoard order by seq desc";
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			// 옮겨 담을 큰상자
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
					
			while ( rs.next() ) {
				
				// 레코드 1줄 -> BoardDTO 1개
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				// dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setIsnew(rs.getString("isnew")); // 글쓰고 난뒤 며칠이 지났는지 시간
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// View 서블릿이 글번호를 줄테니 레코드 내용 전부를 DTO에 담아서 돌려주세요!
	public BoardDTO get(String seq) {
		
		try {
			
			String sql = "select b.*, (select name from tblUsers where id = b.id ) as name "
					+ "from tblBoards b where seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if ( rs.next() ) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setTag(rs.getString("tag"));
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	// View 서블릿이 글번호를 줄테니 조회수를 +1 해주세요!
	public void updateReadCount(String seq) {
		
		try {
			
			String sql = "update tblBoards set readcount = readcount + 1 where seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// EditOk 서블릿이 수정할 DTO를 줄테니 update 해주세요!
	public int edit(BoardDTO dto) {
		
		try {
			
			String sql = "update tblBoards set subject=?, content=?, tag=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getTag());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate(); // 성공시 1 실패시 0
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	// DelOk 서블릿이 글번호를 줄테니 글을 삭제해주세요!
	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblBoards where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate(); // 성공시 1 실패시 0
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}

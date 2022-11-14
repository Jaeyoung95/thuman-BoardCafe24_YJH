package com.company.biz.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.jdbc.JDBCConnection;
import com.company.sql.MySQLQuery;
import com.company.vo.BoardVO;


@WebServlet("/GetBoardListCtrl")
public class GetBoardListCtrl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/GetBoardListCtrl");
//		처음 검색서블릿 방문 시 가장 최근 페이지를 보여주기 위해 1페이지부터 시작.
//		이후에는 page값을 받아서 처리.
		int page;
		if(request.getParameter("page")==null)
			page=1;
		else
			page=Integer.parseInt(request.getParameter("page"));
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			conn=JDBCConnection.getConnection();
			String sql=MySQLQuery.BOARD_LIST_SELECT;

			
			
			
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, page*10-10);   // 1페이지 -> 0, 2페이지 -> 10, 3페이지 -> 20
			stmt.setInt(2, 10);
			rs=stmt.executeQuery();
			
			ArrayList<BoardVO> boardList=new ArrayList<BoardVO>();
			
			while(rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setNickname(rs.getString("nickname"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setUserid(rs.getString("userid"));
				
				boardList.add(vo);
			}
			
			
			
			// PrepareStatement와 Resultset을 재활용하기 위해 자원을 닫고 다시 사용.
			stmt.close();
			rs.close();
			
			sql=MySQLQuery.BOARD_COUNT_SELECT;
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			
			int totalRows=0;//전체 게시글 수 담는 변수
			if(rs.next()) {
				totalRows=rs.getInt(1);
			}
			System.out.println(totalRows);
			
			request.setAttribute("boardList", boardList);
			request.setAttribute("totalRows", totalRows);
			
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("getBoardList.jsp");
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCConnection.close(rs, stmt, conn);
		}
	}

}

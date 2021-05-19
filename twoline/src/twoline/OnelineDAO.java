package twoline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OnelineDAO { //date access object
	private Connection con;
	private String url; 
	private String user;
	private String passwd; 
	
	public OnelineDAO() { //생성자
		this.url ="jdbc:mysql://localhost/world?characterEncoding=UTF-8&serverTimezone=UTC";
		this.user="root";
		this.passwd="woehddb5555!";
	}
	
	private Connection connect() {
		
		Connection con = null;
		String sql = "select * from oneline order by no desc";
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public ArrayList<OnelineDTO> getlist() {
		String sql = "select * from oneline order by no desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<OnelineDTO> dtos = new ArrayList<OnelineDTO>();
		
		con = connect();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("no");
				String memo = rs.getString("memo");
				String wdate = rs.getString("wdate");
				
				OnelineDTO dto = new OnelineDTO(no, memo, wdate);
				dtos.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ //오류가 걸리든 안걸리든 무조건 실행해라
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
}

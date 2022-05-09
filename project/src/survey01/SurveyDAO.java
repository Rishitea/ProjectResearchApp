package survey01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDAO {
	private JdbcTemplate jdbcTemplate;
	public SurveyDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	
	public boolean insertCat(String name) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"SURVEY\" values (\"SEQ_SURVEY\".nextval,?,1)";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int result = pstmt.executeUpdate(); //실행
			System.out.println(result+"행이 삽입되었습니다.");
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if(conn != null) { 
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	public boolean checkCat(int number) { //true반환시 main:number로 updateCat연결, false 반환시 main:메시지 출력
		ArrayList<SurveyVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from \"SURVEY\" where \"NUMBER\"=?";//count -> int반환
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql); //실행
			rs = pstmt.executeQuery();
			pstmt.setInt(1, number);
			
			while(rs.next()) {
				SurveyVO tmp = new SurveyVO(
						rs.getInt("NUMBER"),
						rs.getString("NAME"),
						rs.getInt("COUNT")
						);
				ls.add(tmp);
			}
		} catch (SQLException e) {
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls.isEmpty()? false:true;		
	}
	
	public boolean updateCat(int number) { //n 입력했을때! (0빼고)
		//update “SURVEY” set “COUNT”=count+1 where “NUMBER”=?
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update \"SURVEY\" set \"COUNT\"=count+1 where \"NUMBER\"=?";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			int result = pstmt.executeUpdate(); //실행
			
			System.out.println("입력한 정보가 저장되었습니다.");
			ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
	public List<SurveyVO> selectAll() {
		ArrayList<SurveyVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"NUMBER\", \"NAME\", \"COUNT\" from \"SURVEY\" ORDER BY \"NUMBER\"";	
		try {
			conn = jdbcTemplate.getConnection(); //계정연결
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();//실행
			
			while(rs.next()) {
				SurveyVO tmp = new SurveyVO(
						rs.getInt("NUMBER"),
						rs.getString("NAME"),
						rs.getInt("COUNT")
						);
				ls.add(tmp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return (ls.size()==0) ? null : ls;
	}

}

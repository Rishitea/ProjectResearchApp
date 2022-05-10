package survey2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReasonDAO {
	private JdbcTemplate jdbcTemplate;
	public ReasonDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	
	public boolean updateReason(int number, int coffee_number) { //프랜차이즈커피 선택시 이유
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update \"REASON\" set \"COUNT\"=count+1 where \"NUMBER\"=? and \"COFFEE_NUMBER\"=?";
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, coffee_number);
			int result = pstmt.executeUpdate();
			System.out.println("저장");
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
	
	public boolean checkReason(int number) {
		ArrayList<ReasonVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from \"REASON\" where \"NUMBER\"=?";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql); //실행
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReasonVO tmp = new ReasonVO (
						rs.getInt("NUMBER"),
						rs.getString("REASON"),
						rs.getInt("COUNT"),
						rs.getInt("COFFEE_NUMBER")
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
		return ls.isEmpty()? false:true;
	}
	
	
	public List<ReasonVO> selectAll(int coffee_number) {
		ArrayList<ReasonVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select \"NUMBER\", \"REASON\", \"COUNT\", \"COFFEE_NUMBER\" from \"REASON\" where \"COFFEE_NUMBER\"=?";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coffee_number);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				ReasonVO tmp = new ReasonVO(
						rs.getInt("NUMBER"),
						rs.getString("REASON"),
						rs.getInt("COUNT"),
						rs.getInt("COFFEE_NUMBER")
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

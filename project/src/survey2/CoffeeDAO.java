package survey2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeDAO {
	private JdbcTemplate jdbcTemplate;
	public CoffeeDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	
	public boolean updateC(int number) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update \"COFFEE\" set \"COUNT\"=count+1 where \"NUMBER\"=?";
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			int result = pstmt.executeUpdate();
			System.out.println("입력완료");
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
	public List<CoffeeVO> selectAll() {
		ArrayList<CoffeeVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"NUMBER\", \"CATEGORY\", \"COUNT\" from \"COFFEE\" ORDER BY \"NUMBER\"";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CoffeeVO tmp = new CoffeeVO(
						rs.getInt("NUMBER"),
						rs.getString("CATEGORY"),
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

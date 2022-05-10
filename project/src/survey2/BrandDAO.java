package survey2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
	private JdbcTemplate jdbcTemplate;
	public BrandDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	
	public boolean insertBrand(String name) { //프랜차이즈 기타항목 추가..
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"BRAND\" values (\"SEQ_BRAND\".nextval, ?, 1)";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int result = pstmt.executeUpdate();//실행
			System.out.println("입력한 Brand가 설문조사 항목에 추가되었습니다.");
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
	
	public boolean updateCount(int number) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update \"BRAND\" set \"COUNT\"=count+1 where \"NUMBER\"=?";
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
	
	public boolean checkBrand(int number) { //입력값이 Brand테이블에 존재하는지 확인
		ArrayList<BrandVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from \"BRAND\" where \"NUMBER\"=?";
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql); //실행
			
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BrandVO tmp = new BrandVO(
						rs.getInt("NUMBER"),
						rs.getString("BRAND"),
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
		return ls.isEmpty()? false:true;
	}
	
	
	public List<BrandVO> selectAll(){
		ArrayList<BrandVO> ls = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"NUMBER\", \"BRAND\", \"COUNT\" from \"BRAND\" ORDER BY \"NUMBER\"";
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BrandVO tmp = new BrandVO(
						rs.getInt("NUMBER"),
						rs.getString("BRAND"),
						rs.getInt("COUNT")
						);
				ls.add(tmp);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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

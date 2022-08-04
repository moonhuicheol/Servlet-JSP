package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j2;



@Log4j2
public class MemberDAO {
	
	private static final String driverclass = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@db202204131245_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";
	private static final String user = "ADMIN";
	private static final String pwd = "Oracle123456";
	
	//MemberDAO의 메소드
	public List<MemberDTO> listMembers() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			
			Class.forName(driverclass);
			Connection conn = DriverManager.getConnection(url, user, pwd);
			log.debug("conn: "+conn);
			
			
			String query = "SELECT * FROM t_member ";
			
			log.info("query:{}", query);
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs;){
				
				while (rs.next()) {
					String id = rs.getString("id");
					String pwd = rs.getString("pwd");
					String name = rs.getString("name");
					String email = rs.getString("email");
					Date joinDate = rs.getDate("joinDate");
					
					MemberDTO vo = new MemberDTO();
					
					vo.setId(id);
					vo.setPwd(pwd);
					vo.setName(name);
					vo.setEmail(email);
					vo.setJoinDate(joinDate);
					
					list.add(vo);
					
				} //while
				
			} //try-with-resource
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
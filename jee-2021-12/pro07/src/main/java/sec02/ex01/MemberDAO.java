package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sec01.ex01.MemberDTO;


@Log4j2
@NoArgsConstructor
public class MemberDAO {
	/*
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	*/
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;


	public List<MemberDTO> listMembers() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			//connDB();
			
			Context ctx = new InitialContext();
			Object obj =ctx.lookup("java:/comp/env/jdbc/OracleCloudATP");
			
			Objects.requireNonNull(obj);
			dataFactory = (DataSource) obj;
			log.info("dataFactory: {}",dataFactory);
				
			
			con = dataFactory.getConnection();
			log.info("con: {}", con);
			
			String query = "select * from t_member ";
			
			log.info("prepareStatememt: " + query);
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberDTO dto = new MemberDTO();
				
				dto.setId(id);
				dto.setPwd(pwd);
				dto.setName(name);
				dto.setEmail(email);
				dto.setJoinDate(joinDate);
				
				list.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
}
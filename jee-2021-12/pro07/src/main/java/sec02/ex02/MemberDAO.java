package sec02.ex02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sec01.ex01.MemberDTO;


@Log4j2
@NoArgsConstructor
public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;


	public List<MemberDTO> listMembers() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		
		try {
			Context ctx = new InitialContext();
			Object obj =ctx.lookup("java:/comp/env/jdbc/OracleCloudATP");
			
			Objects.requireNonNull(obj);
			dataFactory = (DataSource) obj;
			// connDB();
		
			log.info("dataFactory: {}",dataFactory);
			
			con = dataFactory.getConnection();
			
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
			}//while
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} //try-catch
		return list;
	} // list member

	public void addMember(MemberDTO dto) {
		try {
			Context ctx = new InitialContext();
			Object obj =ctx.lookup("java:/comp/env/jdbc/OracleCloudATP");
			
			Objects.requireNonNull(obj);
			dataFactory = (DataSource) obj;
			
			con = dataFactory.getConnection();
			
			String id = dto.getId();
			String pwd = dto.getPwd();
			String name = dto.getName();
			String email = dto.getEmail();
			
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			
			log.info("prepareStatememt: " + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			Context ctx = new InitialContext();
			Object obj =ctx.lookup("java:/comp/env/jdbc/OracleCloudATP");
			
			Objects.requireNonNull(obj);
			dataFactory = (DataSource) obj;
			
			con = dataFactory.getConnection();
			
			String query = "delete from t_member" + " where id=?";
			log.info("prepareStatememt:" + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

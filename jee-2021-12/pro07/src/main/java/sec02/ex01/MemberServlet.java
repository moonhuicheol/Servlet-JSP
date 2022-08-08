package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sec01.ex01.MemberDTO;


@Log4j2
@NoArgsConstructor
@WebServlet("/Member2")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doGet()invoked");
		
		request.setCharacterEncoding("utf8");
		
		MemberDAO dao = new MemberDAO();
		
		List<MemberDTO> list = dao.listMembers();
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println ("<html><body>");
		out.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");

		for (int i=0; i<list.size(); i++){
			
			MemberDTO memberDTO = (MemberDTO) list.get(i);
			String id= memberDTO.getId();
			String pwd = memberDTO.getPwd();
			String name = memberDTO.getName();
			String email = memberDTO.getEmail();
			Date joinDate = memberDTO.getJoinDate();
			
			out.print("<tr><td>"+id+"</td><td>"+
	                pwd+"</td><td>"+
	                name+"</td><td>"+
	                email+"</td><td>"+
	                joinDate+"</td></tr>");	
			
			}
	     	
		out.println ("</table>"); 
		out.println ("</body><html>");
		
	} // do get

	
} // end class

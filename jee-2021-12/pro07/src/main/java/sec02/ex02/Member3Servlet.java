package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sec01.ex01.MemberDTO;



@Log4j2
@NoArgsConstructor
@WebServlet("/member3")
public class Member3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		MemberDAO dao = new MemberDAO();
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		 if(command!= null && command.equals("addMember")){
			 String _id=request.getParameter("id");
			 String _pwd=request.getParameter("pwd");
			 String _name=request.getParameter("name");
			 String _email=request.getParameter("email");
			 
			 MemberDTO dto = new MemberDTO();
			 
			 dto.setId(_id);
			 dto.setPwd(_pwd);
			 dto.setName(_name);
			 dto.setEmail(_email);
		     dao.addMember(dto);
		     
	      }else if(command!= null && command.equals("delMember")) {
	    	  String id = request.getParameter("id");
	    	  dao.delMember(id);
	      }
	       List list=dao.listMembers();
	     out.print("<html><body>");
	     out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
	     out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td >삭제</td></tr>");
	    
	     for (int i=0; i<list.size();i++){
	 		MemberDTO dto=(MemberDTO) list.get(i);
	 		
	 		String id = dto.getId();
	 		String pwd = dto.getPwd();
	 		String name = dto.getName();
	 		String email = dto.getEmail();
	 		Date joinDate = dto.getJoinDate();
	 		out.print("<tr><td>"+id+"</td><td>"
	 			                +pwd+"</td><td>"
	 			                +name+"</td><td>"
	 			                +email+"</td><td>"
	 			                +joinDate+"</td><td>"
	 		                    +"<a href='/pro07/member3?command=delMember&id="+id+"'>삭제 </a></td></tr>");

	 	 }
	 	 out.print("</table></body></html>");
	     out.print("<a href='/pro07/memberForm.html'>새 회원 등록하기</a");
	   
		
		
	} //service

} // end class

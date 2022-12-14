package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
@WebServlet("/Login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() invoked");
		log.trace("init()invoked");
	} //init

	
	public void destroy() {
		System.out.println("destroy() invoked");
		log.trace("destroy()invoked");
	} //destroy

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.println("아이디 : "+user_id);
		out.println("비밀번호: "+user_pw);
		out.print("</body></html>");
		
		out.flush();
	} // doGet

} // end class

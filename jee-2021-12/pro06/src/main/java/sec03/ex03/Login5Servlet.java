package sec03.ex03;

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
@WebServlet("/Login5")
public class Login5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init()invoked");
	} //init

	@Override
	public void destroy() {
		log.trace("destroy()invoked");
	} //destroy

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.trace("doPost()invoked");
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		
		log.info("user_id:{}",user_id);
		log.info("user_pw:{}",user_pw);
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("아이디: "+user_id);
		out.println("비밀번호: "+user_pw);
		out.println("주소: "+user_address);
		out.println("</body><html>");
		
		out.flush();
		
	} // dopost

} // end class

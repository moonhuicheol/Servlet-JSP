package sec01.ex01;

import java.io.IOException;

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
@WebServlet("/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	public void init(ServletConfig config) throws ServletException {
		log.trace("init()invoked");
	} //init


	public void destroy() {
		log.trace("destroy()invoked");
	}//destroy

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		log.info("아이디:{}",user_id);
		log.info("비밀번호:{}", user_pw);
		String[] subject = request.getParameterValues("subject");
		
		for(String str: subject){
			log.info("선택한과목:{}",str);
		} //for
		
	}//doget

} // end class

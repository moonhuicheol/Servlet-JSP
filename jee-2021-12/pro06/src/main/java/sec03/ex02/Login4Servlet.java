package sec03.ex02;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
@WebServlet("/Login4")
public class Login4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init()invoked");
	} // init
	
	@Override
	public void destroy() {
		log.trace("destroy()invoked");
	} // destroy

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doGet()invoked");
		doHandle(request,response);
		
	} // doget

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("doPost()invoked");
		doHandle(request,response);
		
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		log.trace("doHandle()invoked");
		
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		log.info("user_id:{}", user_id);
		log.info("user_pw:{}", user_pw);
		
		
	} //doHandle
	
	

} // end class

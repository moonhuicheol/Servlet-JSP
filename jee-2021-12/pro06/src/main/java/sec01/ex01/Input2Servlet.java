package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

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
@WebServlet("/input2")
public class Input2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	public void init(ServletConfig config) throws ServletException {
		log.trace("init()invoked");
	}
	
	public void destroy() {
		log.trace("destroy()invoked");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		log.trace("doget()invoked");
		request.setCharacterEncoding("utf-8");
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			
			for(String str : values) {
				log.info("name:{}, value:{}",name, values);
			} // for
			
		} //while
		
	} // doget

} // end class

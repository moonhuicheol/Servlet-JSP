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
@WebServlet("/Calc")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static float USD_RATE = 1124.70F;
	private static float JPY_RATE = 10.113F;
	private static float CNY_RATE = 163.30F;
	private static float GBP_RATE = 1444.35F;
	private static float EUR_RATE = 1295.97F;
   
	public void init(ServletConfig config) throws ServletException {
		log.trace("init()invoked");
	}


	public void destroy() {
		log.trace("destroy()invoked");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		String won = request.getParameter("won");
		String operator = request.getParameter("operator");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		

		if (command != null && command.equals("calculate")) {
			
			String result = calculate(Float.parseFloat(won), operator);
			
			pw.print("<html><font size=10>변환결과</font><br>");
			pw.print("<html><font size=10>" + result + "</font><br>");
			pw.print("<a href='Calc'>환율 계산기 </a>");
			
			return;
		}

		pw.print("<html><title>환율계산기</title>");
		pw.print("<font size=5>환율 계산기</font><br>");
		pw.print("<form  name='frmCalc' method='get'  action='Calc'  />  ");
		pw.print("원화: <input type='text' name='won' size=10  />  ");
		pw.print("<select name='operator' >");
		pw.print("<option value='dollar'>달러</option>");
		pw.print("<option value='en'>엔화</option>");
		pw.print("<option value='wian'>위안</option>");
		pw.print("<option value='pound'>파운드</option>");
		pw.print("<option value='euro'>유로</option>");
		pw.print("</select>");
		pw.print("<input type='hidden' name='command' value='calculate'  />  ");
		pw.println("<input type='submit' value='변환'  />");
		pw.println("</form>");
		pw.print("</html>");
		pw.close();
		
		} // doget
	

		private static String calculate(float won, String operator) {
			
			String result = null;
			
			if (operator.equals("dollar")) {
				result = String.format("%.6f", won / USD_RATE);
				
			} else if (operator.equals("en")) {
				result = String.format("%.6f", won / JPY_RATE);
				
			} else if (operator.equals("wian")) {
				result = String.format("%.6f", won / CNY_RATE);
				
			} else if (operator.equals("pound")) {
				result = String.format("%.6f", won / GBP_RATE);
				
			} else if (operator.equals("euro")) {
				result = String.format("%.6f", won / EUR_RATE);
			}
			
			return result;
		} // calculate
		


} //end class

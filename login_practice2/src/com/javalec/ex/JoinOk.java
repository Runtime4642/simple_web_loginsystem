package com.javalec.ex;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinOk
 */
@WebServlet("/JoinOk")
public class JoinOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private java.sql.Connection con;
	private java.sql.Statement stmt;
	private String name,id,pw,phone1,phone2,phone3,gender;
    /**
     * Default constructor. 
     */
    public JoinOk() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		actionDo(request,response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("EUC-KR");
		name = request.getParameter("name");
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		String query = 	"insert into member values('"+name+"','"+id+"','"+pw+"','"+phone1+"','"+phone2+"','"+phone3+"','"+gender+"')";
		
		 String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "runtime";
			String password = "123420";
			try {
				Class.forName(driver);
				System.out.println("jdbc driver 로딩 성공");
				con=DriverManager.getConnection(url, user, password);
				System.out.println("오라클 연결 성공");
				stmt=con.createStatement();
				int i=stmt.executeUpdate(query);
				if(i==1)
				{
					System.out.println("insert success");
					response.sendRedirect("JoinResult.jsp");
				}
				else {
					System.out.println("insert fail");
					response.sendRedirect("join.html");
			}} catch (ClassNotFoundException e) {
				System.out.println("jdbc driver 로딩 실패");
			} catch (SQLException e) {
				System.out.println("오라클 연결 실패");
			}
			
//		try {
////			Class .forName("oracle.jdbc.driver.OracleDriver");
////			connection = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe", "runtime", "123420");
//			
//		}
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("insert fail2");
//			
//		}finally {
//				try {
//					if(stmt !=null) stmt.close();
//					if(con !=null) con.close();
//					
//				}catch(Exception e)
//				{
//					
//				}
//		}
		
	}

}

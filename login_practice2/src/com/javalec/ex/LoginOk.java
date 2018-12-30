package com.javalec.ex;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOk
 */
@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {
	private String id;
	private String pw;
	private String name;
	private java.sql.Connection con;
	private java.sql.Statement stmt;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//doGet(request, response);
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "runtime";
		String password = "123420";
		id=request.getParameter("id");
		pw=request.getParameter("pw");
		
		 String check=null;
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con=DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			stmt=con.createStatement();
			  String query = "select * from member where id='"+id+"' and pw='"+pw+"'";
			int i=stmt.executeUpdate(query);
			if(i==1)
				System.out.println("select success");
			else
			{
				System.out.println("select fail");
				response.sendRedirect("Login.html");
			}
			 ResultSet rs = stmt.executeQuery(query);
	          while (rs.next()) {  
	                check = rs.getString("id");
	                id = check;
	               pw = rs.getString("pw");
	               name = rs.getString("pw");
	         }
			if(id.equals(check))
			{
				System.out.println("로그인성공");
				HttpSession httpSession=request.getSession();
				httpSession.setAttribute("name",this.name);
				httpSession.setAttribute("id",id);
				httpSession.setAttribute("pw",pw);
				
				
				response.sendRedirect("loginResult.jsp");
				
			}
			else {
				System.out.println("select fail");
				response.sendRedirect("join.html");
		}} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}

}

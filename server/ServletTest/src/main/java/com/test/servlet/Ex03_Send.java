package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03_Send extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "	   <style>"
				+ "         label { display: block; margin-bottom: 10px; }"
				+ "    </style>"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>데이터 입력</h1>\r\n"
				+ "\r\n"
				+ "    <form method=\"POST\" action=\"/ServletTest/ex03_receive.do\">\r\n"
				+ "        <label>이름: <input type=\"text\" name=\"name\"></label>\r\n"
				+ "        <label>나이: <input type=\"number\" name=\"age\"></label>\r\n"
				+ "\r\n"
				+ "        <div><input type=\"submit\" value=\"서버로 전송하기\"></div>\r\n"
				+ "    </form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
		writer.close();
	}

}

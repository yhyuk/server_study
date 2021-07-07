package com.test.servlet;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
						throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		
		Calendar now= Calendar.getInstance();
		
		// HTML CSS JS 다 여기서함
		writer.write("<html>\n");
		writer.write("<head>\n");
		writer.write("<meta charset='utf-8'>\n");
		writer.write("</head>\n");
		writer.write("<body>\n");
		writer.write("<h1>Hello - KIM</h1>\n");
		writer.write(String.format("%tF %tT\n", now, now));
		writer.write("</body>\n");
		writer.write("</html>");
		
	}
}
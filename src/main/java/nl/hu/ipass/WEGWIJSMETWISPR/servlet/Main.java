package nl.hu.ipass.WEGWIJSMETWISPR.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/IpassProject.do")
public class Main extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String naam = req.getParameter(getServletName());
		String ip = req.getRemoteAddr();
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println(naam + ip + "jemoeder");
	}
}

package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<String> listName = new ArrayList<String>();
		
		listName.add("홍길동");
		listName.add("신짱구");
		listName.add("신형만");
		listName.add("신짱아");
		listName.add("둘리");
		listName.add("고희동");
		listName.add("고길동");
		
		String name = req.getParameter("inputName");
		
		String message = "";
		
		if(listName.contains(name)) {
			message = name + "은/는" + listName.indexOf(name) + "번쨰 인덱스에 존재합니다";
			
			req.setAttribute("message", message);
			
			String path = "/WEB-INF/views/practice/search_result.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} else {
			message = name + "은/는 존재하지 않습니다";
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			resp.sendRedirect("/error");
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

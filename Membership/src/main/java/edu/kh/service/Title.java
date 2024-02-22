package edu.kh.service;

import java.io.IOException;
import java.util.List;

import edu.kh.todoList.model.dto.Todo.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo-search")
public class Title extends HttpServlet {
	
	private TitleService service = new TitleService(); 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Todo> todoList = service.Select();
		
		String message = "";
		
		for(Todo todo : todoList) {
			if(todo.getTitle().equals(req.getParameter("titleName"))){
				message = todo.toString();
				
				req.setAttribute("message", message);
				
				String path =  "/WEB-INF/views/todo/todo_result.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				break;
			} 
		}
		
		message = req.getParameter("titleName") + "은/는 존재하지 않습니다.";
		HttpSession session = req.getSession();
		session.setAttribute("message", message);
		
		resp.sendRedirect("/todo/error");
		
	}
	
	
}

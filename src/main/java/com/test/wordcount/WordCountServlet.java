package com.test.wordcount;

//Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

//Extend HttpServlet class
public class WordCountServlet extends HttpServlet {

	private String message;
	

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String queryString = request.getParameter("query");
		WordCounter wordCounter =  new WordCounter();
		int wordCount = 0;
		JSONObject obj = new JSONObject();
		try {
			wordCount = wordCounter.getWordCount(queryString);
			obj.put("count", wordCount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(obj.toString());
	}

	public void destroy() {
		// do nothing.
	}
}
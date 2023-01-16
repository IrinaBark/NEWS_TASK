package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

//	private static final String MESSAGE_PARAM = "message";
	private static final String NEWS_PARAM = "news";
	private static final String PRESENTATION_PARAM = "presentation";
	private static final String PRESENTATION_VALUE_FOR_NEWS_LIST = "newsList";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> latestNews;

		try {
			latestNews = newsService.latestList(5);
//			if (request.getParameter(MESSAGE_PARAM) != null) {
//				request.getSession().setAttribute(MESSAGE_PARAM, request.getParameter(MESSAGE_PARAM));
//			}
			request.getSession().setAttribute("local", request.getParameter("local"));
			request.setAttribute(NEWS_PARAM, latestNews);
			request.setAttribute(PRESENTATION_PARAM, PRESENTATION_VALUE_FOR_NEWS_LIST);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
		}
	}
}

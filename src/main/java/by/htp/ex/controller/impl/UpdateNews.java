package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.AccessValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();
	private final AccessValidation accessValidation = ValidationProvider.getInstance().getAccessValidation();

	private static final String JSP_TITLE_PARAM = "title";
	private static final String JSP_DATE_PARAM = "date";
	private static final String JSP_BRIEF_PARAM = "brief";
	private static final String JSP_CONTENT_PARAM = "content";
	private static final String JSP_ID_PARAM = "idNews";

	private static final String HAVE_NO_ACCESS_ERROR_LOCAL_KEY = "local.error.name.no_access";
	private static final String ERROR_MESSAGE_PARAM = "errorMessage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (accessValidation.canExecuteAdminCommand(session)) {

			String title;
			String date;
			String brief;
			String content;
			Integer idNews;

			News newsToUpdate;

			try {
				idNews = Integer.parseInt(request.getParameter(JSP_ID_PARAM));
			} catch (NumberFormatException e) {
				throw new ServletException(e);
			}

			title = request.getParameter(JSP_TITLE_PARAM);
			date = request.getParameter(JSP_DATE_PARAM);
			brief = request.getParameter(JSP_BRIEF_PARAM);
			content = request.getParameter(JSP_CONTENT_PARAM);

			newsToUpdate = new News(idNews, title, brief, content, date);

			try {
				service.update(newsToUpdate);
				response.sendRedirect("controller?command=go_to_base_page");

			} catch (ServiceException e) {
				response.sendRedirect("controller?command=go_to_error_page");
			}
		} else {
			request.getSession().setAttribute(ERROR_MESSAGE_PARAM, HAVE_NO_ACCESS_ERROR_LOCAL_KEY);
			response.sendRedirect("controller?command=go_to_error_page");
		}
	}
}

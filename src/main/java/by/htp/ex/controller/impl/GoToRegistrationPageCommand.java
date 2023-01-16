package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.util.validation.AccessValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistrationPageCommand implements Command {

	private static final String HAVE_ALREADY_AUTHORIZED_ERROR_MESSAGE = "You are already signed in";
	private static final String ERROR_MESSAGE_PARAM = "errorMessage";
	
	private final AccessValidation accessValidation = ValidationProvider.getInstance().getAccessValidation();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (!accessValidation.notAuthorizedUser(session)) {

			request.setAttribute("presentation", "registration");
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} else {
			request.getSession().setAttribute(ERROR_MESSAGE_PARAM, HAVE_ALREADY_AUTHORIZED_ERROR_MESSAGE);
			response.sendRedirect("controller?command=go_to_error_page");
		}
	}
}

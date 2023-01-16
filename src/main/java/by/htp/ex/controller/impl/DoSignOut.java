package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.util.validation.AccessValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignOut implements Command {

	private static final String HAVE_NOT_AUTHORIZED_ERROR_MESSAGE = "You are not signed in";
	private static final String ERROR_MESSAGE_PARAM = "errorMessage";

	private final AccessValidation accessValidation = ValidationProvider.getInstance().getAccessValidation();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (accessValidation.notAuthorizedUser(session)) {

			request.getSession().setAttribute(ERROR_MESSAGE_PARAM, HAVE_NOT_AUTHORIZED_ERROR_MESSAGE);
			response.sendRedirect("controller?command=go_to_error_page");
			
		} else {
			request.getSession(true).setAttribute("user", "not active");
			response.sendRedirect("controller?command=go_to_base_page");
		}
	}
}

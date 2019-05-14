package by.training.finaltask.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;


import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginAction extends Action {
	private static Logger logger = LogManager.getLogger(LoginAction.class);

	private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();


	@Override
	public Set<Role> getAllowRoles() {
		return null;
	}

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(login != null && password != null) {
			if(session.getAttribute("authorizedUser") == null)
			{
				UserService service = (UserService) factory.createService(DAOEnum.USER);
				User user = service.findByUserNameAndPassword(login, password);
				if(user != null) {
					session.setAttribute("authorizedUser", user);
					session.setAttribute("username",user.getUsername());
					session.setAttribute("menu", menu.get(user.getUserRole()));
					logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
					return new Forward("/index.html");
				}else {

					request.setAttribute("message", "Couldn't find login and password");
					logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
				}
			} else {
				request.setAttribute("message","You are already logged in");
			}
		}
		return null;
	}
}
package org.goodie.servlet;

import org.goodie.dao.UserDB;
import org.goodie.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/core/user")
public class UserServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UUID userId = (UUID) req.getSession().getAttribute("token");
    req.setAttribute("currentUser", UserDB.findById(userId));

    String id = req.getParameter("id");
    if (id != null) {
      userId = UUID.fromString(req.getParameter("id"));
    } else if (req.getSession().getAttribute("userId") != null){
      userId = (UUID) req.getSession().getAttribute("userId");
    }

    req.setAttribute("user", UserDB.findById(userId));
    req.getRequestDispatcher("/jsp/v2/user.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = buildUserFromRequest(req);
    if (user.getId() == null) {
      if (user.getLogin() != null && !user.getLogin().isBlank()) {
        UserDB.addUser(user);
      }
      resp.sendRedirect(req.getContextPath() + "/core/users");
    } else {
      UserDB.updateUser(user);

      req.getSession().setAttribute("userId", user.getId());
      resp.sendRedirect(req.getContextPath() + "/core/user");
    }
  }

  private User buildUserFromRequest(HttpServletRequest request) throws ServletException {
    User user = new User();
    String name = request.getParameter("name");
    user.setName(name);
    String id = request.getParameter("id");
    if (id == null) {
      String login = request.getParameter("login");
      if (login != null && !login.isBlank()) {
        user.setLogin(login);
        user.setPassword(login);
      }
      return user;
    }

    user = UserDB.findById(UUID.fromString(id));

    name = request.getParameter("name");
    user.setName(name);

    String password = request.getParameter("password");
    user.setPassword(password);

    String restrictions = request.getParameter("restrictionFlag");
    user.setRestrictionFlag(Boolean.parseBoolean(restrictions));

    return user;
  }
}

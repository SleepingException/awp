package org.goodie.servlet;

import org.goodie.dao.UserDB;
import org.goodie.dto.User;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("jsp/v2/auth.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");

    User user = UserDB.findAll().stream()
            .filter(item -> login.equals(item.getLogin()) && password.equals(item.getPassword()))
            .findAny()
            .orElse(new User());

    req.getSession().setAttribute("token", user.getId());
    resp.sendRedirect(req.getContextPath() + "/core");
  }

}

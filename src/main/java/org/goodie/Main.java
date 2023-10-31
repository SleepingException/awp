package org.goodie;

import org.goodie.dao.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/core")
public class Main extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("user", UserDB.findById((UUID) req.getSession().getAttribute("token")));
    req.getRequestDispatcher("/jsp/v2/index.jsp").forward(req, resp);
  }
}
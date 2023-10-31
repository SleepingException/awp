package org.goodie.servlet;

import org.goodie.dao.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/core/user/delete")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
          UUID userId = UUID.fromString(req.getParameter("id"));
          UserDB.deleteUser(userId);
        }
        resp.sendRedirect(req.getContextPath() + "/core/users");
    }
}

package org.goodie;

import org.goodie.dao.UserDB;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = {"/core/*"})
public class AuthFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    UUID token = (UUID) req.getSession().getAttribute("token");

    if (token == null || UserDB.findById(token) == null) {
      req.setAttribute("notRegistered", true);
      req.getRequestDispatcher("/jsp/v2/auth.jsp").forward(req, resp);
      return;
    }

    filterChain.doFilter(req, resp);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}

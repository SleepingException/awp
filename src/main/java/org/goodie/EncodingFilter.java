package org.goodie;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter {

  private static final String UTF = "UTF-8";

  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    request.setCharacterEncoding(UTF);
    response.setCharacterEncoding(UTF);

    chain.doFilter(request, response);
  }
}
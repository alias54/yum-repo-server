package de.is24.infrastructure.gridfs.http.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessLogFilter implements Filter {

  public static final Logger LOG = LoggerFactory.getLogger(AccessLogFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    chain.doFilter(request, response);
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      LOG.debug("|{} |{}", httpRequest.getMethod(), httpRequest.getRequestURL());
    }
  }

  @Override
  public void destroy() {
  }
}

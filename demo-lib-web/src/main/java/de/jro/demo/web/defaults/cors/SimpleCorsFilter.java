package de.jro.demo.web.defaults.cors;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

public class SimpleCorsFilter extends GenericFilterBean {
  public final Set<String> allowedOrigins;
  private static final Logger log = LoggerFactory.getLogger(SimpleCorsFilter.class);

  @Autowired
  public SimpleCorsFilter(String allowedOriginsFromConfig) {
    log.info("Create {} with allowed origins: '{}' from configuration",
        SimpleCorsFilter.class.getName(), allowedOriginsFromConfig);
    allowedOrigins = new HashSet<String>(Arrays.asList(allowedOriginsFromConfig.split(",")));
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    String originHeader = request.getHeader("Origin");
    if (allowedOrigins.contains(originHeader) && !isOriginAlreadyAllowed(response, originHeader)) {
      response.addHeader("Access-Control-Allow-Origin", originHeader);
      log.debug("Set Access-Control-Allow-Origin to {}", originHeader);
    }

    // OPTIONS request CORS preflight
    if (request.getHeader("Access-Control-Request-Method") != null
        && "OPTIONS".equals(request.getMethod())) {
      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
      response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept");
      response.addHeader("Access-Control-Max-Age", "1800");
      log.debug("Received CORS preflight request");
    }

    // all other requests
    response.setHeader("Access-Control-Allow-Credentials", "true");
    log.debug("Set Access-Control-Allow-Credentials to true");

    filterChain.doFilter(request, response);
  }


  private boolean isOriginAlreadyAllowed(HttpServletResponse response, String origin) {
    String alreadyAllowedOrigins = response.getHeader("Access-Control-Allow-Origin");
    if (response != null && alreadyAllowedOrigins != null) {
      Set<String> originsAsSet = new HashSet<String>(
          Arrays.asList(alreadyAllowedOrigins.split(",")));
      if (originsAsSet.contains(origin)) {
        return true;
      }
    }
    return false;
  }
}

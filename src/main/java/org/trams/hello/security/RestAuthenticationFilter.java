package org.trams.hello.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.trams.hello.bean.UserItem;
import org.trams.hello.rest.common.AuthorizationToken;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.utils.JsonUtils;

@Service
public class RestAuthenticationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest http_request = ((HttpServletRequest) request);
		HttpServletResponse http_response = ((HttpServletResponse) response);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		String path = ((HttpServletRequest) request).getRequestURI();
		 if (httpRequest.isRequestedSessionIdFromURL()) {
	            if (session != null) {
	                session.invalidate();
	            }
	        }
		 
		if (path.startsWith("/admin")) {
			if (!Login.checkAdminLogin(session)) {
				((HttpServletResponse) response).sendRedirect("/");
				return;
			}
		}else if (path.startsWith("/company")) {
			if (!Login.checkCompanyLogin(session)) {
				((HttpServletResponse) response).sendRedirect("/");
				return;
			}
		}else if (path.startsWith("/counselingCenter")) {
			if (!Login.checkCounselingCenterLogin(session)) {
				((HttpServletResponse) response).sendRedirect("/");
				return;
			}
		}else if(path.equals("")){
			if (Login.checkAdminLogin(session)) {
				((HttpServletResponse) response).sendRedirect("/admin/index");
				return;
			}
			
		}
		else if (path.matches("^/api/v\\d+/auth.*") && !path.startsWith("/api/v1/auth/user/identity")) {
			String token = http_request.getHeader("token");
			System.out.println("Token: " + token);
			UserItem user = AuthorizationToken.convertToObject(token);
			
			Date date = new Date();

			System.out.println(JsonUtils.toString(user));
			System.out.println(date.getTime());

			if ( user == null || user.getId() == null || date.getTime() > user.getExpiration()  ) {
				http_response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				http_response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				JSONObject js = new JSONObject();
				js.put("status", 100);
				js.put("mesage", "You have to login !");
				js.put("data", null);

				PrintWriter writer = response.getWriter();
				writer.write(js.toString());
				writer.close();
				return;
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}

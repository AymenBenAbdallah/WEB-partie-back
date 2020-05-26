package pack;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

/**
 * Servlet Filter implementation class InvalidUrl
 */
@WebFilter("/*")
public class InvalidUrl implements Filter {

    /**
     * Default constructor. 
     */
    public InvalidUrl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestUrl = request.getRequestURL().toString();
        if (validUrl(requestUrl)) {
            //allowed, continue navigation
            chain.doFilter(req, res);
        } else {
            //invalid URL, send back to index.html
            String invaludUrl = retrieveInvalidPart(requestUrl);
            response.sendRedirect(request.getContextPath() + "/index.html" + invaludUrl);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

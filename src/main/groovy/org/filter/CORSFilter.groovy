package org.filter

import groovy.transform.CompileStatic

import javax.servlet.*
import javax.servlet.http.HttpServletResponse

/**
 * Created by ravitej on 5/5/14.
 */
@CompileStatic
class CORSFilter implements Filter {

    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {


        HttpServletResponse response = (HttpServletResponse) resp

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        chain.doFilter(req, resp);


    }

    @Override
    void destroy() {

    }
}

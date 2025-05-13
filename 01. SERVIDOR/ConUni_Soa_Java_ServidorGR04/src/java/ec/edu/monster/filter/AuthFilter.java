/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.filter;

/**
 *
 * @author sebas
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter{
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("usuario") != null;

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("login.jsp");
        }
    }
    
}

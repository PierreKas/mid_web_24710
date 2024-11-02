//package util;
//
//
//
//import java.io.IOException;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import model.Role;
//import model.User;
//
//@WebFilter("/*")
//public class AuthenticationFilter implements Filter {
//    
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//
//        String uri = httpRequest.getRequestURI();
//        
//        // Allow access to login page and static resources
//        if (isPublicResource(uri)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Check if user is logged in
//        if (session == null || session.getAttribute("user") == null) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
//            return;
//        }
//
//        // Check role-based access
//        User user = (User) session.getAttribute("user");
//        if (!hasAccess(user.getRole(), uri)) {
//            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    private boolean isPublicResource(String uri) {
//        return uri.endsWith("login") || 
//               uri.endsWith("register") || 
//               uri.contains("/css/") || 
//               uri.contains("/js/") || 
//               uri.contains("/images/");
//    }
//
//    private boolean hasAccess(Role role, String uri) {
//        // Librarian has access to everything
//        if (role == Role.LIBRARIAN) {
//            return true;
//        }
//
//        // Administrators can view but not modify
//        if ((role == Role.HOD || role == Role.DEAN || role == Role.MANAGER) 
//            && !uri.contains("/add") && !uri.contains("/edit") && !uri.contains("/delete")) {
//            return true;
//        }
//
//        // Students and teachers can access membership and borrowing features
//        if ((role == Role.STUDENT || role == Role.TEACHER) 
//            && (uri.contains("/membership") || uri.contains("/borrow"))) {
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public void destroy() {
//    }
//}

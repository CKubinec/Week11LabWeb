/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.AccountService;

/**
 *
 * @author Craig
 */
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        System.out.println("This is the uuid ="+uuid);
        if (request.getParameter("action") != null) {
            request.setAttribute("clicked", "Email Sent! Please check your email for instructions for resetting password");
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
            return;
        }
        if (uuid != null) {
            HttpSession session = request.getSession();
            session.setAttribute("uuid", uuid); 
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uuid = (String) session.getAttribute("uuid");
        System.out.println("This is the uuid ="+uuid);
        if (uuid != null) {
            try {
                String password = (String) request.getParameter("password");
                AccountService accountService = new AccountService();
                if(accountService.changePassword(uuid, password)){
                    request.setAttribute("message", "Password Reset!");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "ERROR!");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, "Error in doPost");
            }
        } else {
            request.setAttribute("clicked", "Email Sent! Please check your email for instructions for resetting password");
            String url = request.getRequestURL().toString();
            String path = getServletContext().getRealPath("/WEB-INF");
            String email = request.getParameter("email");
            AccountService accountService = new AccountService();
            accountService.resetPassword(email, path, url);
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
    }

}

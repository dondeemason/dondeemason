package com.oms.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.service.DBQueries;

public class ForgotPasswordController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("pages/forgotPassword.jsp");
		dispatcher.forward(req, resp);
	}




	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String newPassword = "";
		
		SecureRandom random = new SecureRandom();
		
		newPassword = IntStream.range(0, 9)
        .map(i -> random.nextInt(chars.length()))
        .mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
        .collect(Collectors.joining());
		
		RequestDispatcher dispatcher = null;
		
		DBQueries dbQueries = new DBQueries();
		
		if(dbQueries.isCombinationCorrect(username, email)) {
			dbQueries.forgotPassword(username, email, newPassword);
			
			String to = email; 
	        String from = "finalprojectcpi@gmail.com";    
	        
	        Properties properties = System.getProperties();
	  
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.socketFactory.port", "465");
	        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.port", "465");


	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("finalprojectcpi@gmail.com", "ptbxealfeoygfemm");
	            }
	        });
	      
	        session.setDebug(true);
	        try {         
	            MimeMessage message = new MimeMessage(session);            
	            message.setFrom(new InternetAddress(from));            
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));            
	            message.setSubject("New Password");           
	            message.setText("Your new password is: " + newPassword);
	            System.out.println("sending...");           
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }

			request.setAttribute("message", "Your password is successfully updated. Check your email for password details");
			dispatcher = request.getRequestDispatcher("message.jsp");
		} else {
			request.setAttribute("message", "Wrong credentials. Please try again");
			dispatcher = request.getRequestDispatcher("message.jsp");
		}
		

		dispatcher.forward(request, response);
	}
	
	

	
}

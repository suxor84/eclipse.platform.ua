<%--
 Copyright (c) 2000, 2003 IBM Corporation and others.
 All rights reserved. This program and the accompanying materials 
 are made available under the terms of the Common Public License v1.0
 which accompanies this distribution, and is available at
 http://www.eclipse.org/legal/cpl-v10.html
 
 Contributors:
     IBM Corporation - initial API and implementation
--%>
<%@ page import="org.eclipse.help.internal.webapp.data.*" errorPage="/advanced/err.jsp" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println();
	boolean cookiesEnabled = false;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
		System.out.println(cookies[i].getName()+"="+cookies[i].getValue());
			if ("cookiesEnabled".equals(cookies[i].getName()) && "yes".equals(cookies[i].getValue())) {
				cookiesEnabled = true;
			}
		}
	}
	if(cookiesEnabled || (new RequestData(application, request)).getMode() == RequestData.MODE_INFOCENTER){
		request.getRequestDispatcher("/advanced/searchScoped.jsp").forward(request, response);
	}else{
		request.getRequestDispatcher("/advanced/searchInfocenter.jsp").forward(request, response);
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<%@ page import="twoline.OnelineDTO" %>
<%@ page import="twoline.OnelineDAO" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	int no = Integer.parseInt(request.getParameter("no"));
	OnelineDAO dao = new OnelineDAO();
	OnelineDTO dto = new OnelineDTO();
	
	dto.setNo(no);
	dao.delete(dto);
	
	response.sendRedirect("memo_list.jsp");
%>
</html>
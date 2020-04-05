<%@ page errorPage="JSP_Error.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.microstrategy.web.app.beans.PageComponent"%>
<%@ page import="com.microstrategy.web.platform.*"%>
<%@ page import="com.microstrategy.web.app.WebAppSessionManager"%>
<%@ page import="java.io.*"%> <%@ page import="java.util.*"%>
<%@ page import="com.microstrategy.web.app.ProjectInformation"%>
<%@ taglib uri="/webUtilTL.tld" prefix="web"%>
<% PageComponent mstrPage = (PageComponent)request.getAttribute("mstrPage"); %>
<%
WebAppSessionManager wasm = mstrPage.getAppContext().getAppSessionManager();
ArrayList projects = wasm.getProjectsList(true);
ProjectInformation projectInformation = projects.get(0);
response.sendRedirect("?evt=3010&server=" + projectInformation.getServerName() + "&project=" + projectInformation.getProjectName() + "&port=" + projectInformation.getPortNumber());
%>

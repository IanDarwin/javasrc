package network.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class MyRequest implements HttpServletRequest {
	/* Construct a dummy MyRequest */
	public MyRequest() {
	}
	/** Construct a MyRequest for the given input */
	public MyRequest(InputStream s) {
	}

	public String getAuthType() { return null; }
	public String getContextPath() { return null; }
	public javax.servlet.http.Cookie[] getCookies() { return null; }
	public long getDateHeader(String name) { return 0; }
	public String getHeader(String name) { return null; }
	public Enumeration<String> getHeaderNames() { return null; }
	public Enumeration<String> getHeaders(String name) { return null; }
	public int getIntHeader(String name) {
		return Integer.parseInt(getHeader(name));
	}
	public String getMethod() { return null; }
	public String getPathInfo() { return null; }
	public String getPathTranslated() { return null; }
	public String getQueryString() { return null; }
	public String getRemoteUser() { return null; }
	public StringBuffer getRequestURL() { 
		return null;
	}
	public String getRequestURI() { 
		return getRequestURL().toString();
	}
	public String getRequestedSessionId() { return null; }
	public String getServletPath() { return null; }
	public javax.servlet.http.HttpSession getSession() { return null; }
	public javax.servlet.http.HttpSession getSession(boolean create) { return null; }
	public java.security.Principal getUserPrincipal() { return null; }
	public boolean isRequestedSessionIdFromCookie() { return false; }
	public boolean isRequestedSessionIdFromURL() { return false; }
	public boolean isRequestedSessionIdFromUrl() { return false; }
	public boolean isRequestedSessionIdValid() { return false; }
	public boolean isUserInRole(String user) { return false; }
	protected String enc = "iso-8859-1";
	public String getCharacterEncoding() { return enc; }
	public void setCharacterEncoding(String nenc) { enc = nenc; }
	public int getContentLength() { return 0; }
	public String getContentType() { return "text/html"; }
	public javax.servlet.ServletInputStream getInputStream() { return null; }
	public java.util.Locale getLocale() { return null; }
	public Enumeration<Locale> getLocales() { return null; }
	public String getParameter(String s1) { return null; }
	public Enumeration<String> getParameterNames() { return null; }
	public Map<String,String[]> getParameterMap() { return null; }
	public String[] getParameterValues(String s1) { return null; }
	public String getProtocol() { return "http"; }
	public java.io.BufferedReader getReader() { return null; }
	public String getRealPath(String s1) { return null; }
	public String getRemoteAddr() { return null; }
	public String getRemoteHost() { return null; }
	public javax.servlet.RequestDispatcher getRequestDispatcher(String req) {
		return null;
	}
	public String getScheme() { return "http"; }
	public String getServerName() { return "localhost"; }
	public int getServerPort() { return 80; }
	public boolean isSecure() { return false; }

	public void setAttribute(String s1, Object s2) { }
	public void removeAttribute(String s1) { }
	public Object getAttribute(String s1) { return null; }
	public Enumeration<String> getAttributeNames() { return null; }
	public String getLocalAddr() {
		return null;
	}
	public String getLocalName() {
		return null;
	}
	public int getLocalPort() {
		return 0;
	}
	public int getRemotePort() {
		return 0;
	}
	@Override
	public long getContentLengthLong() {
		return 0;
	}
	@Override
	public ServletContext getServletContext() {
		return null;
	}
	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return null;
	}
	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		return null;
	}
	@Override
	public boolean isAsyncStarted() {
		return false;
	}
	@Override
	public boolean isAsyncSupported() {
		return false;
	}
	@Override
	public AsyncContext getAsyncContext() {
		return null;
	}
	@Override
	public DispatcherType getDispatcherType() {
		return null;
	}
	@Override
	public String changeSessionId() {
		return null;
	}
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return false;
	}
	@Override
	public void login(String username, String password) throws ServletException {
		
	}
	@Override
	public void logout() throws ServletException {
		
	}
	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return null;
	}
	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return null;
	}
	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
		return null;
	}
}

import javax.servlet.*;
import javax.servlet.http.*;

public class MyRequest implements HttpServletRequest {
	public MyRequest() {
	}
    public abstract String getAuthType();
    public abstract String getContextPath();
    public abstract javax.servlet.http.Cookie getCookies()[];
    public abstract long getDateHeader(String name);
    public abstract String getHeader(String name);
    public abstract java.util.Enumeration getHeaderNames();
    public abstract java.util.Enumeration getHeaders(String name);
    public abstract int getIntHeader(String name);
    public abstract String getMethod();
    public abstract String getPathInfo();
    public abstract String getPathTranslated();
    public abstract String getQueryString();
    public abstract String getRemoteUser();
    public abstract String getRequestURI();
    public abstract String getRequestedSessionId();
    public abstract String getServletPath();
    public abstract javax.servlet.http.HttpSession getSession();
    public abstract javax.servlet.http.HttpSession getSession(boolean);
    public abstract java.security.Principal getUserPrincipal();
    public abstract boolean isRequestedSessionIdFromCookie();
    public abstract boolean isRequestedSessionIdFromURL();
    public abstract boolean isRequestedSessionIdFromUrl();
    public abstract boolean isRequestedSessionIdValid();
    public abstract boolean isUserInRole(String user);
}

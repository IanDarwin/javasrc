import javax.servlet.*;
import javax.servlet.http.*;

public abstract class MyResponse implements HttpServletResponse {
	public MyResponse() {
	}
    public abstract void addCookie(javax.servlet.http.Cookie);
    public abstract void addDateHeader(String name, long val);
    public abstract void addHeader(String name, String val);
    public abstract void addIntHeader(String name, int val);
    public abstract boolean containsHeader(String hdrName);
    public abstract String encodeRedirectURL(String url);
    public abstract String encodeRedirectUrl(String url);
    public abstract String encodeURL(String url);
    public abstract String encodeUrl(String url);
    public abstract void sendError(int code) throws java.io.IOException;
    public abstract void sendError(int code, String val) throws java.io.IOException;
    public abstract void sendRedirect(String url) throws java.io.IOException;
    public abstract void setDateHeader(String val, long dval);
    public abstract void setHeader(String name, String val);
    public abstract void setIntHeader(String name, int ival);
    public abstract void setStatus(int status);
    public abstract void setStatus(int status, String mesg);
}

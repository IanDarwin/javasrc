import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MyResponse implements HttpServletResponse {
	public MyResponse() {
	}
    public void addCookie(javax.servlet.http.Cookie cookie) { return; }
    public void addDateHeader(String name, long val) { return; }
    public void addHeader(String name, String val) { return; }
    public void addIntHeader(String name, int val) { return; }
    public boolean containsHeader(String hdrName) { return false; }
    public String encodeRedirectURL(String url) { return null; }
    public String encodeRedirectUrl(String url) { return null; }
    public String encodeURL(String url) { return null; }
    public String encodeUrl(String url) { return null; }
    public void sendError(int code) throws java.io.IOException { return; }
    public void sendError(int code, String val) throws java.io.IOException { return; }
    public void sendRedirect(String url) throws java.io.IOException { return; }
    public void setDateHeader(String val, long dval) { return; }
    public void setHeader(String name, String val) { return; }
    public void setIntHeader(String name, int ival) { return; }
    public void setStatus(int status) { return; }
    public void setStatus(int status, String mesg) { return; }
	public void flushBuffer() { }
	public int getBufferSize() { return 42; }
	public String getCharacterEncoding() { return "iso-8859-1"; }
	public javax.servlet.ServletOutputStream getOutputStream() { return null; }
	public java.io.PrintWriter getWriter() { return null; }
	public boolean isCommitted() { return true; }
	public void reset() { }
	public void setBufferSize(int size) { }
	public void setContentLength(int len) { }
	public void setContentType(String type) { }
	public void setLocale(Locale loc) { }
	public Locale getLocale() { return Locale.US; }
}

package webserver;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MyResponse implements HttpServletResponse {
	/** Construct a dummy Response */
	public MyResponse() {
	}
	/** Construct a Response to the given Socket */
	public MyResponse(OutputStream out) {
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
    public void setDateHeader(String val, long dval) {
		setHeader("Date", new java.util.Date(dval).toString());
	}
    public void setHeader(String name, String val) { return; }
    public void setIntHeader(String name, int ival) {
		setHeader(name, Integer.toString(ival));
	}
	public void setContentLength(int len) { }
	public void setContentType(String type) { }
    public void setStatus(int status) { return; }
    public void setStatus(int status, String mesg) { return; }
	public void setLocale(Locale loc) { }
	public Locale getLocale() { return Locale.US; }

	public void flushBuffer() { }
	public void resetBuffer() { }
	public int getBufferSize() { return 42; }
	public String getCharacterEncoding() { return "iso-8859-1"; }
	public javax.servlet.ServletOutputStream getOutputStream() { return null; }
	public java.io.PrintWriter getWriter() { return null; }
	public boolean isCommitted() { return true; }
	public void reset() { }
	public void setBufferSize(int size) { }

}

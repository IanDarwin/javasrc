package network.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class MyResponse implements HttpServletResponse {
	private String encoding = "iso-8859-1";
	private String contentType = "text/html";
	private Locale locale = Locale.US;

	/** Construct a Response */
	public MyResponse() {
	}
	
	/** Construct a Response to the given Socket */
	public MyResponse(OutputStream out) {
	}

    public void addCookie(Cookie cookie) {
		return;
	}

	public void addDateHeader(String name, long val) {
		return;
	}

	public void addHeader(String name, String val) {
		return;
	}

	public void addIntHeader(String name, int val) {
		return;
	}

	public boolean containsHeader(String hdrName) {
		return false;
	}

	public String encodeRedirectURL(String url) {
		return null;
	}

	public String encodeRedirectUrl(String url) {
		return null;
	}

	public String encodeURL(String url) {
		return null;
	}

	public String encodeUrl(String url) {
		return null;
	}

	public void sendError(int code) throws java.io.IOException {
		return;
	}

	public void sendError(int code, String val) throws IOException {
		return;
	}

	public void sendRedirect(String url) throws IOException {
		return;
	}

	public void setDateHeader(String val, long dval) {
		setHeader(val, new Date(dval).toString());
	}

	public void setHeader(String name, String val) {
		return;
	}

	public void setIntHeader(String name, int ival) {
		setHeader(name, Integer.toString(ival));
	}

	public void setContentLength(int len) {
	}

	public void setContentType(String type) {
		this.contentType = type;
	}

	public String getContentType() {
		return contentType;
	}

	public void setStatus(int status) {
		return;
	}

	public void setStatus(int status, String mesg) {
		return;
	}

	public void setLocale(Locale loc) {
	}

	public Locale getLocale() {
		return locale;
	}

	public void flushBuffer() {
	}

	public void resetBuffer() {
	}

	public int getBufferSize() {
		return 42;
	}

	public String getCharacterEncoding() {
		return encoding;
	}

	public void setCharacterEncoding(String enc) {
		this.encoding = enc;
	}

	public ServletOutputStream getOutputStream() {
		return null;
	}

	public PrintWriter getWriter() {
		return null;
	}

	public boolean isCommitted() {
		return true;
	}

	public void reset() {
	}

	public void setBufferSize(int size) {
	}

	@Override
	public void setContentLengthLong(long len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}


}

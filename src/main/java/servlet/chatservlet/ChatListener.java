/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 2002.
 * All rights reserved. Software written by Ian F. Darwin.
 * $Id$
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *        This product includes software developed by Ian F. Darwin.
 * 4. Neither the name of the author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating and standardizing the Java 
 * language and environment is gratefully acknowledged.
 */

package chatservlet;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Package darwinsys.chat implements a simple servlet-based chat application.
 * This class does much of the work of the Chat application,
 * including registering/deregistering users in the List
 */
public class ChatListener 
	implements ChatConstants, HttpSessionListener, ServletContextListener {


	/** Called when a new user comes along. Create a null
	 * UserState object and store it in the session.
	 */
	public void sessionCreated(HttpSessionEvent e) {
		HttpSession sess = e.getSession();
		sess.setAttribute(USER_STATE, new UserState());
		// XXX Get the ServletContext and add the user to it.
		System.out.println("Chat User Set Up");
	}

	public void sessionDestroyed(HttpSessionEvent e) {
		// Log this, but the Session is already destroyed.
		System.out.println("Chat User Removed");
	}

	/**
	 * The Chat Application is starting up. Create all of its global data!
 	 */
	public void contextInitialized(ServletContextEvent e) {
		ServletContext ctx = e.getServletContext();
		ctx.setAttribute(APP_STATE, new ChatState());
		System.out.println("Chat Application Initialized");
	}

	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("Chat Application Destroyed");
	}
}

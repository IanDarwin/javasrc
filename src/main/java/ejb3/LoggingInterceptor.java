package ejb3;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {

	@AroundInvoke
	public Object foo(InvocationContext ctx) throws Exception {
		
		// Prepare the log message
		Method method = ctx.getMethod();
		String clazzName = method.getDeclaringClass().getName();
		String methodName = method.getName();
		StringBuilder sb = 
			new StringBuilder("Calling " + clazzName + '.' + methodName);
		Object[] args = ctx.getParameters();
		if (args == null) {
			sb.append("()");
		} else {
			sb.append("(");
			boolean doCommas = false;
			for (Object o : args) {
				// Args in [] since some have toString()
				// whose output includes commas...
				sb.append('[').append(o).append("]");
				if (doCommas) 
					sb.append(", ");
				doCommas = true;
			}
		}
		// Do the logging:
		System.out.println(sb);
		
		// Do the invocation
		Object results = ctx.proceed();
		
		// Return the results
		return results;
	}
}

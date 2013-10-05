package cn.seu.cose.filter;

public class SecurityContextHolder {
	private static final ThreadLocal<SecurityContext> SECURITY_CONTEXT_HOLDER = new ThreadLocal<SecurityContext>();

	public static SecurityContext getSecurityContext() {
		return SECURITY_CONTEXT_HOLDER.get();
	}

	public static void setSecurityContext(SecurityContext securityContext) {
		SECURITY_CONTEXT_HOLDER.set(securityContext);
	}

	public static void clearSecurityContext() {
		SECURITY_CONTEXT_HOLDER.set(null);
	}
}

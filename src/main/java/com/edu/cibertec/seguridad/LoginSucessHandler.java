package com.edu.cibertec.seguridad;


import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class LoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler{

//	private Log log = LogFactory.getLog(LoginSucessHandler.class);
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
//        // This is actually not an error, but an OK message. It is sent to avoid redirects.
//        response.sendError(HttpServletResponse.SC_OK, "kevincho mensaje");
//    }
	
}

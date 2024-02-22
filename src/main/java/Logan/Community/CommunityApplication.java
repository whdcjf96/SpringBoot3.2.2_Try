package Logan.Community;



import Logan.Community.controller.HomeController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class CommunityApplication {

	public static void main(String[] args) {
//		서블릿 띄워보기 start
		TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
			HomeController homeController = new HomeController();
			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						String ret = homeController.hello(name);

						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else if (req.getRequestURI().equals("/user")) {
						resp.setStatus(HttpStatus.OK.value());
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			}).addMapping("/*");
        });
		webServer.start();
//		서블릿 띄워보기 end

	}

}

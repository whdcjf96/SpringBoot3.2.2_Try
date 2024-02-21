package Logan.Community;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.IOException;

//@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
		WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					resp.setStatus(200);
					resp.setHeader("Content-Type", "text/plain");
					resp.getWriter().println("Hello Servlet");
				}
			}).addMapping("/hello");
        });
		webServer.start();
//		SpringApplication.run(CommunityApplication.class, args);
	}

}

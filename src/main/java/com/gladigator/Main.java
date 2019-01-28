package com.gladigator;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

public class Main {

	public static void main(String[] args) throws LifecycleException, ServletException {
		prepareAndStartEmbeddedTomcat();
	}

	private static void prepareAndStartEmbeddedTomcat() throws ServletException, LifecycleException {
		String webappDirLocation = "src/main/webapp";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        ((StandardJarScanner) ctx.getJarScanner()).setScanManifest(false);

        tomcat.start();
        tomcat.getServer().await();
	}

}

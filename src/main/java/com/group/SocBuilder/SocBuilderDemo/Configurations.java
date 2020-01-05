package com.group.SocBuilder.SocBuilderDemo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.interactions.InputSource;

import bsh.classpath.BshClassPath.GeneratedClassSource;

public class Configurations {

	private static String url;
	private static String userId;
	private static String password;
	public static Properties propFile=new Properties();
	public static String projectPath = System.getProperty("user.dir");

	public static void setUp() {
		loadPropFile();
		setUserId();
		setPassword();
		setURL();

	}

	public static void loadPropFile() {

		try {
			InputStream file = new FileInputStream(projectPath+ "//PropertiesFiles//config.properties");
//			InputStream file=getClass().getClassLoader().getResourceAsStream(projectPath+ "//PropertiesFiles//Details.properties")
			propFile.load(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void setUserId(){
		userId=propFile.getProperty("USERID");
	}
	public static String getUserID(){
		return userId;
	}
	public static void setPassword(){
		password=propFile.getProperty("PASSWORD");
	}
	public static String getPassword(){
		return password;
	}
	public static void setURL(){
		url=propFile.getProperty("URL");
	}
	public static String getURL(){
		return url;
	}
	
	
}

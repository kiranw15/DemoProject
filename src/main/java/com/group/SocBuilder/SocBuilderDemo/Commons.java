package com.group.SocBuilder.SocBuilderDemo;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;



public class Commons {
	
		public static File getLastModifiedFile(String string) {
		    
			File folder=new File(string);
			File[] files = folder.listFiles();
		   if (files.length == 0) return null;
		    Arrays.sort(files, new Comparator<File>() {
		        public int compare(File o1, File o2) {
		            return new Long(o2.lastModified()).compareTo(o1.lastModified()); 
		        }});
		    return files[0];
		}
		
		public static void copyFile(String path){
			try{
				
				File srcFile = getLastModifiedFile(path);
				
				File destFile=new File(path+"//course_list.xlsx");
				if(!srcFile.getAbsolutePath().equals(destFile.getAbsolutePath())){
					FileUtils.copyFile(srcFile, destFile);
				}
				
//				Files.copy(srcFile.toPath(), destFile.toPath());
//				srcFile.delete();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
		public static void clearFolder(String path){
			
			try{
				File destFile=new File(path);
				FileUtils.cleanDirectory(destFile); 
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
	
}

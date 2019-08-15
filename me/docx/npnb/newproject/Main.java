package me.docx.npnb.newproject;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.github.junrar.extract.ExtractArchive;

import me.docx.npnb.newproject.*;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("]=NPNB-NEWPROJECT----------------------------------------------------------=[");
		
		System.out.println("What episode is this? (Be sure to include a 0 if needed)");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		String n = reader.next(); // Scans the next token of the input as an int.
		//once finished
		reader.close();
		System.out.println("]=-------------------------------------------------------------------------=[");
		System.out.println("[>>>] Creating folder for Episode " + n);
		File dir = new File("Episode " + n);
		dir.mkdir();
		System.out.println("[>>>] Copying template files to " + "E:/NPNB/Episode " + n);
		
		File source = new File("E://NPNB/NPNB-NewProject Files");
		File dest = new File("E://NPNB/Episode " + n);
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		    int i;
		    for (i = 0; i < 50; i++)
		    	System.out.println(" ");
		    System.out.println("[!!!] Could not copy required template files to project folder. Perhaps the template files are missing?");
		}
		System.out.println("[***] Done copying template files to " + "E:/NPNB/Episode " + n);
		File source2 = new File("E://Google Drive/Before Editing/Vocals/" + n);
		File dest2 = new File("E://NPNB/Episode " + n);
		try {
		    FileUtils.copyDirectory(source2, dest2);
		} catch (IOException e) {
		    e.printStackTrace();
		    Toolkit.getDefaultToolkit().beep();
		    int i;
		    for (i = 0; i < 50; i++)
		    	System.out.println(" ");
		    System.out.println("[!!!] This episode was not found on Google Drive. Perhaps the file name in drive is wrong? Exiting.");
		    
		    FileUtils.forceDelete(dir);
		    Runtime.getRuntime().exit(0);
		    
		}
		System.out.println("[***] Done copying vocal files to " + "E:/NPNB/Episode " + n);
		System.out.println("[>>>] Extracting Vocals vocals...");
		
		File dir2 = new File("dir");
		String[] extensions = new String[] { "rar"};
		//System.out.println("Getting all .RAR files in " + dir2.getCanonicalPath()
				//+ " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		
		
		for (File file : files) {
			int i;
		    for (i = 0; i < 50; i++)
		    	System.out.println(" ");
			String command = "7z.exe e -y -o\"Episode " + n + "\"" + " \"" + file.getCanonicalPath() + "\"";
			System.out.println("[>>>] Extacting vocals from: " + file.getCanonicalPath());
			System.out.println("[***] Finished extracting vocals from: " + file.getCanonicalPath());
			
		    Process child = Runtime.getRuntime().exec(command);
		    InputStream in = child.getInputStream();
		    int c;
		    while ((c = in.read()) != -1) {
		      System.out.print((char) c);
		    }
		    in.close();
		    
			
			//execCmd("7z.exe e -o\"Episode " + n + " \" \" " + file.getCanonicalPath().toString()+" \" ");
			//System.out.println(command);
			//Runtime.getRuntime().exec("7z e -o\"Episode\""+ n + " " + "\"Episode " + n + "\\" + file +"\"");
			//Runtime.getRuntime().exec("move " + file + "E:/NPNB/Episode " + n);
			//7z.exe e -o"Episode 0999" "Episode 0999\Josh Vocals Ep 0999.rar"
		}
		
		System.out.println("[***] Script finished. Launching Premiere.");
		Runtime.getRuntime().exec("G:\\Program Files\\Adobe\\Adobe Premiere Pro CC 2014\\Adobe Premiere Pro.exe " + "E:\\NPNB\\Episode " + n + "\\EDITING_TEMPLATE.prproj");
		System.out.println("[<*>] Premiere launched. Enjoy the podcast.");
		
		
	}
	
	public static String execCmd(String cmd) throws java.io.IOException {
	    @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	

}

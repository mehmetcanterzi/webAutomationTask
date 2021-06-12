/**
 * 
 */
package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author can.terzi
 *
 */
public class UtilityMethods {

	public void writeToTxt(String path, String value) throws IOException {
		File info = new File(path);
		PrintWriter writer = new PrintWriter(new FileWriter(info,true));
		writer.println(value);
		writer.close();
	}
	
	public void clearTxt(String path) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(path);
		writer.close();
	}
	
	public ArrayList<String> getProductInformations(String path) throws FileNotFoundException {
		File file = new File(path);
		ArrayList<String> information = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext()) {
			String info = scanner.nextLine();
			information.add(info);
		}
		scanner.close();	
		return information;
	}
	
}

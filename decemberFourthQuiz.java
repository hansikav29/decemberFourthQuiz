import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
//Adhithi Uppalapati, Hansika Ventrapragada, December Fourth
public class decemberFourthQuiz{

	public static void main(String[] args) {
	    String[] files = {"willyShakesThatTooFastTooFurious.txt", "willyShakesThat.txt"};
	    long startTime = System.currentTimeMillis();

	    for (String fileName : files) {
	        try {
	            // Read entire file
	            BufferedReader reader = new BufferedReader(new FileReader(fileName));
	            String line;
	            String allText = ""; // inefficient concatenation

	            while ((line = reader.readLine()) != null) {
	                // Convert line to uppercase inefficiently
	                String upperLine = "";
	                for (int i = 0; i < line.length(); i++) {
	                    char c = line.charAt(i);
	                    // manually convert lowercase letters
	                    if (c >= 'a' && c <= 'z') {
	                        c = (char) (c - 32);
	                    }
	                    // append to new string each time
	                    upperLine = upperLine + c;
	                }
	                // append to total string inefficiently
	                allText = allText + upperLine + "\n";
	            }

	            reader.close();

	            // Optionally write back to new file
	            String outputFileName = "CAP_" + fileName;
	            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
	            writer.write(allText);
	            writer.close();

	            System.out.println(fileName + " processed, saved as " + outputFileName);

	        } catch (IOException e) {
	            System.out.println("Error processing file: " + fileName);
	            e.printStackTrace();
	        }
	    }

	    long endTime = System.currentTimeMillis();
	    System.out.println("Total processing time: " + (endTime - startTime) + " milliseconds");
}
}
import java.io.*;
import java.util.concurrent.*;

//Adhithi Uppalapati, Hansika Ventrapragada, December Fourth
//Hansika forked it
public class decemberFourthQuiz{

public static void main(String[] args) {
    String[] files = {"willyShakesThatTooFastTooFurious.txt", "willyShakesThat.txt"};
    long startTime = System.currentTimeMillis();

    // Create a fixed thread pool with 2 threads (one for each file)
    ExecutorService executor = Executors.newFixedThreadPool(2);

    for (int i = 0; i < files.length; i++) {
        final String fileName = files[i]; // must be final for anonymous class
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Read entire file
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String line;
                    String allText = ""; // inefficient concatenation

                    while ((line = reader.readLine()) != null) {
                        // Convert line to uppercase inefficiently
                        String upperLine = "";
                        for (int j = 0; j < line.length(); j++) {
                            char c = line.charAt(j);
                            if (c >= 'a' && c <= 'z') {
                                c = (char) (c - 32);
                            }
                            upperLine = upperLine + c;
                        }
                        allText = allText + upperLine + "\n";
                    }

                    reader.close();

                    // Write back to new file
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
        });
    }

    // Shutdown executor and wait for tasks to finish
    executor.shutdown();
    try {
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Total processing time: " + (endTime - startTime) + " milliseconds");
}
}


import javax.swing.*;
import java.io.*;

public class FileInspector {
    public static void main(String[] args) {
        File chosenFile = selectFile();
        if (chosenFile != null) {
            displayFileStats(chosenFile);
        } else {
            System.out.println("No file selected. Exiting...");
        }
    }

    private static File selectFile() {
        JFileChooser filePicker = new JFileChooser(new File("src"));
        filePicker.setDialogTitle("Pick a text file to inspect");

        int choice = filePicker.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            return filePicker.getSelectedFile();
        }
        return null;
    }

    private static void displayFileStats(File file) {
        int lines = 0;
        int words = 0;
        int characters = 0;

        System.out.println("=== File Content Preview ===");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
                lines++;
                if (!currentLine.trim().isEmpty()) {
                    words += currentLine.trim().split("\\s+").length;
                }
                characters += currentLine.length();
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("\n=== File Summary Report ===");
        System.out.printf("File Name       : %s%n", file.getName());
        System.out.printf("Total Lines     : %d%n", lines);
        System.out.printf("Total Words     : %d%n", words);
        System.out.printf("Total Characters: %d%n", characters);
    }
}

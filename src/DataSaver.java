import java.io.*;
import java.util.ArrayList;

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> userDataList = new ArrayList<>();
        int recordID = 1;

        System.out.println("=== CSV Data Entry ===");

        boolean moreRecords;
        do {
            String first = SafeInput.getNonZeroLenString("Enter First Name: ");
            String last = SafeInput.getNonZeroLenString("Enter Last Name: ");
            String email = SafeInput.getNonZeroLenString("Enter Email Address: ");
            int birthYear = SafeInput.getRangedInt("Enter Year of Birth (1900 - 2025): ", 1900, 2025);
            String formattedID = String.format("%06d", recordID++);

            String csvRow = String.join(",", first, last, formattedID, email, String.valueOf(birthYear));
            userDataList.add(csvRow);

            moreRecords = SafeInput.getYNConfirm("Would you like to enter another record?");
        } while (moreRecords);

        String outputFile = SafeInput.getNonZeroLenString("Enter the CSV file name (without extension): ") + ".csv";

        writeCSV("src/" + outputFile, userDataList);
    }

    private static void writeCSV(String path, ArrayList<String> data) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            for (String entry : data) {
                out.println(entry);
            }
            System.out.println("✅ Records successfully saved to: " + path);
        } catch (IOException ex) {
            System.err.println("Error saving data: " + ex.getMessage());
        }
    }
}

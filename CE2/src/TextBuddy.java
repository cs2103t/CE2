import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TextBuddy {

	private static String fileName = null;
	private static Scanner scanner = new Scanner(System.in);

	// categorize TextBuddy's functions into methods

	// a function that adds a line of content into the file
	private static void writeLine(String content) {
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(content);
			bufferedWriter.newLine();

			bufferedWriter.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	} 
	
	private static void add(String content) {
		//int numberOfLines = countLines();
		//int index = numberOfLines + 1;
		String contentToAdd = /*Integer.toString(index) + ". " + */content;

		writeLine(contentToAdd);

		System.out.println("added to " + fileName + ": " + "\"" + content
				+ "\"");
	}

	// a function that prints everything in a file
	private static void display() {
		int numberOfLines = countLines();
		if (numberOfLines == 0) {
			System.out.println(fileName + " is empty");
		} else {
			try {
				int line = 1;
				String content = null;
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((content = bufferedReader.readLine()) != null) {
					System.out.println(line + ". " + content);
					line++;
				}
				bufferedReader.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	/*
	 * a function that deleted a certain line in a file by reading other lines
	 * in an array and overwrite the file using the content of the array
	 */
	private static void delete(int indexDeleted) {
		int numberOfLines = countLines();
		String[] lineContent = new String[numberOfLines - 1];
		String contentDeleted = null;
		String contentTemp = null;

		int currentLine = 0;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((contentTemp = bufferedReader.readLine()) != null) {
				if (currentLine == indexDeleted - 1) {
					//String deleted[] = contentTemp.split(" ", 2);
					//contentDeleted = deleted[1];
					contentDeleted = contentTemp;
				} else {
					lineContent[currentLine] = contentTemp;
					currentLine++;
				}
			}
			bufferedReader.close();

			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < numberOfLines - 1; i++) {
				bufferedWriter.write(lineContent[i]);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}

		System.out.println("deleted from " + fileName + ": " + "\""
				+ contentDeleted + "\"");
	}

	// a function that remove everything in the file
	private static void clear() {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}

		System.out.println("all content deleted from " + fileName);
	}
	
	private static void search(String keyword) {
		int numberOfLines = countLines();
		if (numberOfLines == 0) {
			System.out.println(fileName + " is empty");
		} else {
			boolean found = false;
			try {
				String content = null;
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((content = bufferedReader.readLine()) != null) {
					if(Arrays.asList(content.split(" ")).contains(keyword)) {
						found = true;
						System.out.println(content);
					}
				}
				bufferedReader.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
			if(found == false) {
				System.out.println("No match found!");
			}
		}
	}
	
	private static void sort() {
		int numberOfLines = countLines();
		if (numberOfLines == 0) {
			System.out.println(fileName + " is empty");
		} else {
			ArrayList<String> list = new ArrayList<String>();
			try {
				String content = null;
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((content = bufferedReader.readLine()) != null) {
					list.add(content);
				}
				bufferedReader.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
			
			Collections.sort(list);
			
			/*
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}*/
			
			try {
				FileWriter fileWriter = new FileWriter(fileName);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				for (int i = 0; i < list.size(); i++) {
					bufferedWriter.write(list.get(i));
					bufferedWriter.newLine();
				}
				
				bufferedWriter.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
			
		}
	}

	// a function that counts the number of lines in a file
	private static int countLines() {
		int numberOfLines = 0;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.readLine() != null) {
				numberOfLines++;
			}
			bufferedReader.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return numberOfLines;
	}

	// a function that take in command and executes it
	private static void executeCommand(String command) {
		try {
			if (command.equals("exit")) { // break the loop if user inputs exit
				System.exit(0);
			} else if (command.equals("display")) { // simple string comparison
				// for short command without
				// content attched
				display();
			} else if (command.equals("clear")) {
				clear();
			} else if (command.equals("sort")) {
				sort();
			} else {
				String sentence[] = command.split(" ", 2); // use split to get
				// the content part
				// of the command
				String action = sentence[0];
				String content = sentence[1];
				if (action.equals("add")) { // process the content according to
					// the command
					add(content);
				} else if (action.equals("delete")) {
					delete(Integer.parseInt(content));
				} else if (action.equals("search")) {
					search(content);
				} else {
					System.out.println("Input Error!"); // if the input is not
					// in any of the above
					// category, print error
					// message
				}
			}
		} catch (Exception e) {
			System.out.println("Input Error!");
			// e.printStackTrace();
		}
	}

	public static void startOperation() {
		while (true) { // use loops to accept user input, assume user may input
			// wrongly sometimes
			String command = scanner.nextLine();
			executeCommand(command);
		}
	}

	public static void main(String[] args) {
		fileName = "mytextfile.txt";
		System.out.println("Welcome to TextBuddy. " + fileName
				+ " is ready for use");
		startOperation();
	}
}

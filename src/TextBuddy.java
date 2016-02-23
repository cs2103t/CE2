import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
 * Made some corrections with the help from  Tutor's suggestions.
 * 
 * 1. made the whole class non-static
 * 2. moved loop from main to executeCommand()
 * 3. use switch instead of if else to parse the command and their args separately
 * 4. isolated file operations.
 */

public class TextBuddy {

	private String fileName = null;
	private Scanner scanner = new Scanner(System.in);

	// categorize TextBuddy's functions into methods

	public TextBuddy(String fileName) {
		this.fileName = fileName;
	}

	// a function that counts the number of lines in a file
	private int countLines() {
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

	// a function that adds a line of content into the file
	private void writeLine(String content) {
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

	// read all list and return an ArrayList
	private ArrayList<String> readAllList() {
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
		return list;
	}

	private void writeAllList(ArrayList<String> list) {
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

	// a function that remove everything in the file
	private String clear() {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return "all content deleted from " + fileName;
	}

	private String add(String content) {
		writeLine(content);

		return "added to " + fileName + ": " + "\"" + content + "\"";
	}

	// a function that prints everything in a file
	private String display() {
		int numberOfLines = countLines();
		if (numberOfLines == 0) {
			return fileName + " is empty";
		} else {
			String contentToDisplay = "";

			ArrayList<String> list = readAllList();

			contentToDisplay = "1. " + list.get(0);

			for (int i = 2; i <= list.size(); i++) {
				contentToDisplay = contentToDisplay + "\n" + i + ". " + list.get(i - 1);
			}

			return contentToDisplay;
		}
	}

	/*
	 * a function that deleted a certain line in a file by reading other lines
	 * in an array and overwrite the file using the content of the array
	 */
	private String delete(int indexDeleted) {
		ArrayList<String> list = readAllList();
		String contentDeleted = list.get(indexDeleted - 1);
		list.remove(indexDeleted - 1);
		writeAllList(list);
		return "deleted from " + fileName + ": " + "\"" + contentDeleted + "\"";
	}

	private String search(String keyword) {
		int numberOfLines = countLines();
		if (numberOfLines == 0) {
			return fileName + " is empty";
		} else {
			boolean found = false;
			String contentToDisplay = "";
			ArrayList<String> list = readAllList();

			for (int i = 0; i < list.size(); i++) {
				if (Arrays.asList(list.get(i).split(" ")).contains(keyword)) {
					if (found == true) {
						contentToDisplay = "\n" + list.get(i);
					} else {
						contentToDisplay = list.get(i);
					}
					found = true;
				}
			}

			if (found == true) {
				return contentToDisplay;
			} else {
				return "No match found!";
			}
		}
	}

	private String sort() {
		int numberOfLines = countLines();
		if (numberOfLines == 0) {
			return fileName + " is empty";
		} else {
			ArrayList<String> list = readAllList();

			Collections.sort(list);

			writeAllList(list);

			return "It is sorted successfully!";
		}
	}

	// a function that take in command and executes it
	public String executeCommand(String command) {
		try {
			switch (command) {
			case "exit":
				System.exit(0);
			case "display":
				return display();
			case "clear":
				return clear();
			case "sort":
				return sort();
			default:
				String sentence[] = command.split(" ", 2);
				String action = sentence[0];
				String content = sentence[1];

				switch (action) {
				case "add":
					return add(content);
				case "delete":
					return delete(Integer.parseInt(content));
				case "search":
					return search(content);
				default:
					return "Input Error!";
				}
			}
		} catch (Exception e) {
			return "Input Error!";
			// e.printStackTrace();
		}
	}

	public void startOperation() {
		System.out.println("Welcome to TextBuddy. " + fileName + " is ready for use");

		while (true) { // use loops to accept user input, assume user may input
						// wrongly sometimes
			String command = scanner.nextLine();
			System.out.println(executeCommand(command));
		}
	}

	public static void main(String[] args) {
		TextBuddy myTextBuddy = new TextBuddy(args[0]);
		myTextBuddy.startOperation();
	}
}

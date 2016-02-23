import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {

	@Test
	public void testExecuteCommand() {
		TextBuddy myTextBuddy = new TextBuddy("mytextfile.txt");

		// check if the “clear” command returns the right status message
		assertEquals("all content deleted from mytextfile.txt", myTextBuddy.executeCommand("clear"));

		// check if the “add” command returns the right status message
		assertEquals("added to mytextfile.txt: \"little brown fox\"",
				myTextBuddy.executeCommand("add little brown fox"));

		// check if the “add” command returns the right status message
		assertEquals("added to mytextfile.txt: \"jumped over the moon\"",
				myTextBuddy.executeCommand("add jumped over the moon"));

		// check if the “display” command returns the right status message
		assertEquals("1. little brown fox\n2. jumped over the moon", myTextBuddy.executeCommand("display"));

		// check if the “search” command returns the right status message
		assertEquals("little brown fox", myTextBuddy.executeCommand("search fox"));

		// check if the “sort” command returns the right status message
		assertEquals("It is sorted successfully!", myTextBuddy.executeCommand("sort"));

		// check if the “sort” command returns the right status message
		assertEquals("1. jumped over the moon\n2. little brown fox", myTextBuddy.executeCommand("display"));

		// check if the “delete” command returns the right status message
		assertEquals("deleted from mytextfile.txt: \"little brown fox\"", myTextBuddy.executeCommand("delete 2"));

		// check if the “display” command returns the right status message
		assertEquals("1. jumped over the moon", myTextBuddy.executeCommand("display"));

		// check if the “clear” command returns the right status message
		assertEquals("all content deleted from mytextfile.txt", myTextBuddy.executeCommand("clear"));
	}

}

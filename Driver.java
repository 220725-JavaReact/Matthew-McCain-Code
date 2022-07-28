import java.util.Scanner;

import model.*;

/**
 * 
 */

/**
 * @author Matthew MCain
 * A CLI Storefront Application
 */
public class Driver {

	/**
	 * Does not handle any arguments
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Awesome ASCI logo
		System.out.println("  ________                                  .__   \n"
				+ " /  _____/  ____   ____   ________________  |  |  \n"
				+ "/   \\  ____/ __ \\ /    \\_/ __ \\_  __ \\__  \\ |  |  \n"
				+ "\\    \\_\\  \\  ___/|   |  \\  ___/|  | \\// __ \\|  |__\n"
				+ " \\______  /\\___  >___|  /\\___  >__|  (____  /____/\n"
				+ "        \\/     \\/     \\/     \\/           \\/      \n"
				+ "  _________ __                                  .___                  \n"
				+ " /   _____//  |_  ___________   ____   ______   |   | ____   ____     \n"
				+ " \\_____  \\\\   __\\/  _ \\_  __ \\_/ __ \\ /  ___/   |   |/    \\_/ ___\\    \n"
				+ " /        \\|  | (  <_> )  | \\/\\  ___/ \\___ \\    |   |   |  \\  \\___    \n"
				+ "/_______  /|__|  \\____/|__|    \\___  >____  >   |___|___|  /\\___  > /\\\n"
				+ "        \\/                         \\/     \\/             \\/     \\/  \\/");
		
		String userInput = "";
		Scanner in = new Scanner(System.in);
		
		// Interactive menu
		System.out.println("Here's a list of all the available commands:")
		do {
			switch (userInput) {
			case "h":
				displayMenu();
				break;
			case "1":
				// Add a customer
				
				break;
			case "2":
				// Search for a customer
				
				break;
			case "3":
				// View store inventory
				
				break;
			case "4":
				// Place an order
				
				break;
			case "5":
				// View order history
				
				break;
			case "6":
				// Replenish inventory
				
				break;
			case "x":
				System.out.printLine("Goodbye you incredible person!");
				break;
			default:
				System.out.println("Sorry, I didn't understand that last command."
						+ "Here are your available options:");
				displayMenu();
			}
			System.out.print("Enter next command: ");
			userInput = in.nextLine();
		} while (userInput != "x");
		
	}
	
	/**
	 * Displays the available menu options to the user.
	 */
	static void displayMenu() {
		System.out.println("[h] See this menu again\n"
				+ "[1] Add a customer\n"
				+ "[2] Search for a customer\n"
				+ "[3] View store inventory\n"
				+ "[4] Place an order\n"
				+ "[5] View order history\n"
				+ "[6] Replenish inventory\n"
				+ "[x] Exit");
	}

}

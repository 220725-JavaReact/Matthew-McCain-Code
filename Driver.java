import java.util.Scanner;

import controller.StoreDAO;
import model.*;

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

        String userInput = "";
        Scanner in = new Scanner(System.in);
        StoreDAO dao = new StoreDAO();

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

        // Interactive menu
        do {
            switch (userInput) {
                case "h":
                    displayOptions();
                    break;
                case "1":
                    // Add a customer
                    dao.addCustomer(captureCustomer(in));
                    break;
                case "2":
                    // Search for a customer
                    System.out.println("Enter a name to search for:");
                    String searchString = in.nextLine();
                    try {
                        Customer customer = dao.findCustomer(searchString);
                        System.out.println(customer.getName());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
                    System.out.println("Goodbye you incredible person!");
                    break;
                default:
                    System.out.println("Here's a list of the available commands:");
                    displayOptions();
            }
            System.out.println("Enter next command: ");
            userInput = in.nextLine();
        } while (!userInput.equals("x"));
        in.close();
    }

    /**
     * Displays the available menu options to the user.
     */
    static void displayOptions() {
        System.out.println("[h] See this menu again\n"
                + "[1] Add a customer\n"
                + "[2] Search for a customer\n"
                + "[3] View store inventory\n"
                + "[4] Place an order\n"
                + "[5] View order history\n"
                + "[6] Replenish inventory\n"
                + "[x] Exit");
    }

    static Customer captureCustomer(Scanner in) {
        System.out.println("Full Name: ");
        String name = in.nextLine();
        Address address = captureAddress(in);
        String email = captureEmail(in);
        String phone = capturePhone(in);
        return new Customer(name, address, email, phone);
    }

    static Address captureAddress(Scanner in) {
        System.out.println("Street Number: ");
        int streetNumber = in.nextInt();
        in.nextLine();
        System.out.println("Street Name: ");
        String streetName = in.nextLine();
        System.out.println("City: ");
        String city = in.nextLine();
        System.out.println("State [Abbreviation]: ");
        String state = in.nextLine();
//		Class stateClass = STATE.class;
//		boolean stateSet = false;
//		while (!stateSet) {
//			System.out.println("State [Abbreviation]: ");
//			String inState = in.nextLine();
//			for (STATE st : (STATE[]) stateClass.getEnumConstants()) {
//				if (st.equals(inState.substring(0,1).toUpperCase())) {
//					state = st;
//				}
//			}
//		}
//		in.nextLine();
        System.out.println("Zip code: ");
        int zip = in.nextInt();
        in.nextLine();
        return new Address(streetNumber, streetName, city, state, zip);
    }

    static String captureEmail(Scanner in) {
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        boolean emailSet = false;
        String email = "";
        while(!emailSet) {
            System.out.println("Email address: ");
            email = in.nextLine();
            if (email.matches(pattern)) {
                emailSet = true;
            }
            if (!emailSet) { System.out.println("INVALID EMAIL"); }
        }
        return email;
    }

    static String capturePhone(Scanner in) {
//        String pattern = "^/d(?:-/d{3}){3}/d$";
//        boolean phoneSet = false;
//        String phone = "";
//        while(!phoneSet) {
//            System.out.println("Enter phone number:");
//            phone = in.nextLine();
//            if (phone.matches(pattern)) {
//                phoneSet = true;
//            }
//        }
        System.out.println("Enter phone number:");
        String phone = in.nextLine();
        return phone;
    }
}
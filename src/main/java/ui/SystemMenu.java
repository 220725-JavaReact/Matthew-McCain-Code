package ui;

import dao.storage.StoreData;
import services.Client;
import model.StoreFront;

import java.util.Scanner;

public class SystemMenu {

    Client client;
    Scanner in;
    StoreFront store;

    public SystemMenu(Client client, Scanner in) {
        this.client = client;
        this.in = in;
    }

    public void run() {
        String userInput = "";
        do {
            switch (userInput) {
                case "1":
                    if (client.getStores().size() == 0) {
                        System.out.println("No Store Fronts available, please add a store.");
                        break;
                    }
                    this.store = client.getStore(in);
                    StoreMenu storeMenu = new StoreMenu(store, client, in);
                    storeMenu.run();
                    break;
                case "2":
                    client.addStore(in);
                    break;
                case "h":
                    System.out.println("Always happy to help!");
                    displayOptions();
                    break;
                default:
                    displayOptions();
            }
            System.out.println("Enter Command:");
            userInput = in.nextLine();
        } while(!(userInput.toLowerCase().equals("x")));
    }

    private void displayOptions() {
        System.out.println("Available Options:\n" +
                "[h[ Display Options\n" +
                "[1] Choose a Store Front\n" +
                "[2] Add a Store Front\n" +
                "[x] Exit");
    }
}

package ui;

import bl.Client;
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

        displayOptions();
        do {
            switch (userInput) {
                case "1":
                    client.addStore(in);
                    break;
                case "2":
                    this.store = client.getStore(in);
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
        } while(!(userInput.equals("x")));
    }

    private void displayOptions() {
        System.out.println("Available Options:\n" +
                "[1] Add a Store Front\n" +
                "[2] Choose a Store Front");
    }

    public StoreFront getStore() {
        return store;
    }
}

package ui;

import bl.Client;
import model.StoreFront;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Client client = new Client();
        StoreFront store;
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
        SystemMenu systemMenu = new SystemMenu(client, in);
        systemMenu.run();
        store = systemMenu.getStore();
        StoreMenu storeMenu = new StoreMenu(store, client, in);
        storeMenu.run();
    }
}
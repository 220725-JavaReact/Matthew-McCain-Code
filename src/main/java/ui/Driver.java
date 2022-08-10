package ui;

import services.Client;
import services.util.Logger;

import java.util.Scanner;

import static services.util.Logger.LogLevel.*;

public class Driver {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Logger.getLogger().log(info, "Program started");
            Client client = new Client();
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
            Logger.getLogger().log(info, "Program ended");
        } catch(IllegalStateException e) {
            Logger.getLogger().log(error, e);
        } catch (Exception e) {
            Logger.getLogger().log(fatal, e);
        }
    }
}
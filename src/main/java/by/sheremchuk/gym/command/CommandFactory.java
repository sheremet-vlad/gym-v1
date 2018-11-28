package by.sheremchuk.gym.command;

import java.util.Optional;

public class CommandFactory {
    private static final String FIND_ALL_CLIENTS = "findAllClients";

    public static Command create(String commandName) {

        Command command = null;

        switch (commandName) {
            case FIND_ALL_CLIENTS: {
                command = new FindClientCommand();
            }
            break;
            default: {
                command = new EmptyCommand();
            }
            break;
        }

        return command;
    }

}

package by.sheremchuk.gym.command;

public class CommandFactory {
    private static final String FIND_CLIENT_BY_CARD_NUMBER_OR_SURNAME = "Find";

    public static Command create(String commandName) {

        Command command = null;

        switch (commandName) {
            case FIND_CLIENT_BY_CARD_NUMBER_OR_SURNAME: {
                command = new FindClientByIdOrSurnameCommand();
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

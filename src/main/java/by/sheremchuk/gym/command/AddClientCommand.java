package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AddClientCommand implements Command {
    private final static String NAME_ATTRIBUTE = "nameAdd";
    private final static String SURNAME_ATTRIBUTE = "surnameAdd";
    private final static String MIDDLE_NAME_ATTRIBUTE = "middleNameAdd";
    private final static String GENDER_ATTRIBUTE = "genderAdd";
    private final static String BIRTHDAY_ATTRIBUTE = "birthdayAdd";
    private final static String COMMENTS_ATTRIBUTE = "commentsAdd";
    private final static String PHONE_ATTRIBUTE = "phoneAdd";
    private final static String CART_ATTRIBUTE = "cartAdd";
    private final static String RESULT_MESSAGE_ATTRIBUTE = "message";
    private final static String ERROR_MESSAGE_ATTRIBUTE = "error";
    private final static String CLIENTS_ATTRIBUTE = "clients";
    private final static String EMPTY_RAPAM_VALUE = "";
    private final static String CLEAR_PARAMETERS_ATTRIBUTE = "isClearParameter";

    private final static String DATE_REGEX = "yyyy-MM-dd";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            List clients = (List) request.getAttribute(CLIENTS_ATTRIBUTE);

            String name = request.getParameter(NAME_ATTRIBUTE);
            String surname = request.getParameter(SURNAME_ATTRIBUTE);
            String middleName = request.getParameter(MIDDLE_NAME_ATTRIBUTE);
            String stringBirthday = request.getParameter(BIRTHDAY_ATTRIBUTE);
            String comments = request.getParameter(COMMENTS_ATTRIBUTE);
            String phone = request.getParameter(PHONE_ATTRIBUTE);
            String cartNumber = request.getParameter(CART_ATTRIBUTE);
            String genderString = request.getParameter(GENDER_ATTRIBUTE);

            if (isParamNotValid(name, surname, middleName, stringBirthday, genderString, phone, cartNumber)) {
                throw new IllegalArgumentException("one of entry fields is empty");
            }

            int id = clients.size() + 1;
            GenderEnum gender = GenderEnum.valueOf(genderString.replace("Add","").toUpperCase());

            DateFormat dateFormat = new SimpleDateFormat(DATE_REGEX);
            Date birthday = dateFormat.parse(stringBirthday);

            ClientService clientService = ClientService.getInstance();
            Optional<Client> optionalClient = clientService.addClient(name, surname, middleName, birthday, gender, comments, phone, cartNumber, id);
            if (optionalClient.isPresent()) {
                request.setAttribute(RESULT_MESSAGE_ATTRIBUTE, "client add successfully");

                clients.add(optionalClient.get());
                request.setAttribute(CLIENTS_ATTRIBUTE, clients);

                request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);
            } else {
                request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "error, check entry fields and try again or phone Igor 80291259076");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "error, check entry fields and try again or phone Igor 80291259076");
        }

    }

    private boolean isParamNotValid(String name,
                                String surname,
                                String middleName,
                                String birthday,
                                String gender,
                                String phone,
                                String cartNumber) {

        return name.equals(EMPTY_RAPAM_VALUE)
                || surname.equals(EMPTY_RAPAM_VALUE)
                || middleName.equals(EMPTY_RAPAM_VALUE)
                || birthday.equals(EMPTY_RAPAM_VALUE)
                || gender.equals(EMPTY_RAPAM_VALUE)
                || phone.equals(EMPTY_RAPAM_VALUE)
                || cartNumber.equals(EMPTY_RAPAM_VALUE);
    }

}

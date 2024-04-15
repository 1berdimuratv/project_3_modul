package uz.pdp.front;

import uz.pdp.beck.controller.ChatControllerImpl;
import uz.pdp.beck.controller.MessageControllerImpl;
import uz.pdp.beck.controller.contracts.AuthController;
import uz.pdp.beck.controller.AuthControllerImpl;
import uz.pdp.beck.controller.contracts.ChatController;
import uz.pdp.beck.controller.contracts.MessageController;
import uz.pdp.beck.payload.*;

import java.util.List;
import java.util.Scanner;

public class MainDisplay {

    private final static AuthController authController = AuthControllerImpl.getInstance();
    private final static ChatController chatController = ChatControllerImpl.getInstance();
    private final static MessageController messageController = MessageControllerImpl.getInstance();

    private final static Scanner scannerInt = new Scanner(System.in);
    private final static Scanner scannerStr = new Scanner(System.in);
    private static boolean islogin = false;
    private static UserDTO currentUser = null;

    public static void main(String[] args) {

        System.out.println("----------DehChat.com ga xush kelibsiz------------");


        while (true) {

            while (!islogin) {
                showRegistration();
                int auth = getInputAsInt("Choose => ");

                islogin = switch (auth) {
                    case 1 -> signIn();
                    case 2 -> signUp();
                    default -> false;
                };

            }
            System.out.println("You're succecfully entered");
            showMenu();
            int menu = getInputAsInt("Choose => ");

            switch (menu) {
                case 1 -> search();
                case 2 -> myChats();
                case 3 -> settings();
                case 0 -> exit();
            }

        }

    }



    private static void search() {
        try {
            System.out.println("----------Searching-------------");
            String username = getInputAsString("username : ");

            ChatDTO chatDTO = chatController.findOrCreateUserByUsername(username, currentUser.id());

            UserDTO contact = chatDTO.contact();

            List<MessageDTO> messages = messageController.findAllMessagesByChatId(chatDTO.id());

            displayMessages(messages, contact);

            String sms = getInputAsString(" => ");

            MessageAddDTO messageAddDTO = new MessageAddDTO(sms, chatDTO.id(), currentUser.id());

            messageController.add(messageAddDTO);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void settings() {
        int choose = settingsPanel();
    }

    private static int settingsPanel() {
        /*System.out.println("------Settings-------");
        System.out.println("1) About my profile");
        System.out.println("2) Change my username");
        System.out.println("2) Change password");
        System.out.println("------Settings-------");*/
        return 1;
    }

    private static void myChats() {
        try {
        System.out.println("--------Chats------");
        List<ChatDTO> myChats = chatController.findMyChats(currentUser.id());
        for (ChatDTO myChat : myChats) {
            System.out.println(myChat.contact().username());
        }
        String username = getInputAsString("Choose : ");
        ChatDTO chatDTO = chatController.findOrCreateUserByUsername(username, currentUser.id());

        UserDTO contact = chatDTO.contact();

        List<MessageDTO> messages = messageController.findAllMessagesByChatId(chatDTO.id());

        displayMessages(messages, contact);

        String sms = getInputAsString(" => ");

        MessageAddDTO messageAddDTO = new MessageAddDTO(sms, chatDTO.id(), currentUser.id());

        messageController.add(messageAddDTO);

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }

    private static void exit() {
        islogin = false;
        currentUser = null;
        System.out.println("---------Yoq bo'ling 😊");
    }

    private static boolean signUp() {
        try {
            System.out.println("---------------Sign Up ------------------");
            String name = getInputAsString("Name : ");
            String username = getInputAsString("Username : ");
            String phone = getInputAsString("Phone : ");
            String password = getInputAsString("Password : ");

            SignUpDTO signUpDTO = new SignUpDTO(name, username, phone, password);

            currentUser = authController.signUp(signUpDTO);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static boolean signIn() {
        try {
            System.out.println("---------------Sign In ------------------");
            String username = getInputAsString("Username : ");
            String password = getInputAsString("Password : ");

            SignInDTO signInDTO = new SignInDTO(username, password);

            UserDTO userDTO = authController.signIn(signInDTO);

            System.out.println("User muvaffaqiyatli Kirdi.");

            currentUser = userDTO;
            return true;
        }catch (Exception e) {
            System.out.println("userda xatolik : " + e.getMessage());
            return false;
        }
    }


    public static void showRegistration() {
        System.out.println("Registration");
        System.out.println("1 ==> signIn");
        System.out.println("2 ==> signUP");
    }

    public static void showMenu() {
        System.out.println("Menu");
        System.out.println("1 => Search");
        System.out.println("2 => My chats");
        System.out.println("3 => Settings");
        System.out.println("0 => exit");
    }

    private static String getInputAsString(String message) {
        System.out.print(message);
        return scannerStr.nextLine();
    }

    private static int getInputAsInt(String message) {
        System.out.print(message);
        return scannerInt.nextInt();
    }

    private static void displayMessages(List<MessageDTO> messages, UserDTO contact) {
        System.out.println("----------" + contact.name() + "------------");
        for (MessageDTO message : messages) {

            if (message.senderId().equals(currentUser.id())) {
                System.out.println("\t\t\t\t " + message.body());
            } else {
                System.out.println(message.body());
            }
        }
        System.out.println();
    }

}

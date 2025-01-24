//package emrs;
//
//public class SuperAdminHandler {
//
//    public static void displayMenu() {
//        InputHandler.displayMessage("""
//
//
//            SUPER ADMIN MENU
//           =================
//           1 -> Register Front Desk
//           2 -> Logout
//           """);
//
//        switchForDisplayMenu();
//    }
//
//    public static void switchForDisplayMenu() {
//        String option = InputHandler.input("Enter your option: ");
//        switch (option) {
//            case "1" -> registerFrontDesk();
//            case "2" -> logout();
//            default -> {
//                InputHandler.displayMessage("Invalid option\n");
//                switchForDisplayMenu();
//            }
//        }
//    }
//
//    private static void registerFrontDesk() {
//        String name = InputHandler.collectAndValidateName("Enter Front Desk Name: ");
//        String password = InputHandler.collectAndValidatePassword("Enter Password: ");
//
//            try {
//               boolean isRegistered =  EMRS.getSuperAdmin().registerFrontDesk(name, password);
//                displayMenu();
//            }catch(IllegalArgumentException err) {
//                InputHandler.displayMessage(err.getMessage());
//            }
//        }
//
//
//
//
//    private static void logout() {
//        InputHandler.displayMessage("Super Admin logged out.\n\n");
//        EMRS.displayMenu();
//    }
//}
//
//

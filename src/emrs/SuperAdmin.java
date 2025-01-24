//package emrs;
//
//public class SuperAdmin {
//    private final String adminID;
//    private final String adminPassword;
//
//    public SuperAdmin(String adminID, String adminPassword) {
//        this.adminID = adminID;
//        this.adminPassword = adminPassword;
//    }
//
//    public boolean login(String id, String password) {
//        return this.adminID.equals(id) && this.adminPassword.equals(password);
//    }
//
//    public boolean registerFrontDesk(String name, String password) {
//        FrontDesk newFrontDesk = new FrontDesk(name, password);
//        Database.getFrontDesks().add(newFrontDesk);
//        System.out.printf("Front Desk officer with ID %s successfully registered.%n", newFrontDesk.getID());
//        return true;
//    }
//}
//
//

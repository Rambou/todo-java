import java.text.ParseException;
import java.util.*;

public class Main {
    private static Scanner input;

    public static void main(String[] args) throws ParseException {
        // Αρχικοποίηση λίστας εργασιών και task manager
        ArrayList<Task> todoList = new ArrayList<>();
        TaskManager manager = new TaskManager(todoList);

        // Εργασίες για εισαγωγή και αρχικοποίηση του task manager
        manager.insertTask("Α, Εργασία, «Συνάντηση με τον λογιστή της εταιρείας μου», 20/10/2016 17:00");
        manager.insertTask("Β, Προσωπικά, «Καφές με την κολλητή μου», 22/12/2016 13:00 ");
        manager.insertTask("Α, Προσωπικά, «Εξόφληση στεγαστικού δανείου», 14/09/2018 12:00 ");
        manager.insertTask("Γ, Επαγγεκματικά, «Κατάθεση χρημάτων», 15/01/2017 15:00");
        manager.insertTask("Γ, Εργασία, «Ανανέωση Προηπολογισμού», 15/01/2017 09:00");

        // Αρχικοποίηση scanner
        input = new Scanner(System.in);

        // Μενού διαχείρησης προγράμματος
        // Τελείωνει όταν exit = true
        boolean exit = false;
        while (!exit) {
            // τιμή επιλογής χρήστη από menu
            int selection = selectionMenu();
            switch (selection) {
                case 1:
                    try {
                        // εισαγωγή εκκρεμμότητας με έλεγχος εγκυρότητας
                        System.out.println("Εισήγαγε εκκρεμότητα με την μορφή <Προτεραιότητα>,<Κατηγορία>,<Περιγραφή>,<μέρα/μήνας/χρονιά ώρα:λεπτά>:");
                        String task = input.next();
                        manager.insertTask(task);
                    } catch (ParseException e) {
                        System.out.println("Λάθος εισαγωγή.");
                    }
                    break;

                case 2:
                    manager.showIncompleteTasks();
                    break;

                case 3:
                    // εισαγωγή προτεραιότητας με έλεγχος εγκυρότητας
                    System.out.println("Εισήγαγε προτεραιότητα:");
                    String priority = input.next();
                    manager.searchByPriority(priority);
                    break;

                case 4:
                    // εισαγωγή κατηγορίας με έλεγχος εγκυρότητας
                    System.out.println("Εισήγαγε κατηγορία:");
                    String category = input.next();
                    manager.searchByCategory(category);
                    break;

                case 5:
                    // εισαγωγή ημέρας με έλεγχος εγκυρότητας
                    System.out.println("Εισήγαγε ημέρα:");
                    String day = input.next();
                    try {
                        manager.searchByDate(day);
                    } catch (ParseException e) {
                        System.out.println("Λάθος εισαγωγή μέρας.");
                    }
                    break;

                case 6:
                    System.out.println("Εισήγαγε χρονικό διάστημα, ημέρα:");
                    String _day = input.next();
                    System.out.println("Εισήγαγε χρονικό διάστημα, ώρα:");
                    String time = input.next();
                    try {
                        manager.searchByDate(_day, time);
                    } catch (ParseException e) {
                        System.out.println("Λάθος εισαγωγή μέρας.");
                    }
                    break;

                case 7:
                    manager.done(todoList.get(1));
                    break;

                case 8:
                    System.out.println("Εισήγαγε χρονικό διάστημα:");
                    // κατανάλωση της νέας γραμμής από πάνω, πρόβλημα σε συστήματα με χρήση CRLF
                    input.nextLine();
                    String date = input.nextLine();
                    try {
                        manager.checkCompleted(date);
                    } catch (ParseException e) {
                        System.out.println("Λάθος εισαγωγή μέρας.");
                    }
                    break;

                case 9:
                    manager.showCompleteTasks();
                    break;

                default:
                    exit = true;
                    break;
            }
        }

    }

    private static int selectionMenu() {
        System.out.println("Επέλεξε λειτουργεία" +
                "\n1. Εισαγωγή νέας εκκρεμότητας:" +
                "\n2. Εμφάνιση ολόκληρης της λίστας εκκρεμοτήτων (όσες δεν έχουν ολοκληρωθεί)." +
                "\n3. Αναζήτηση των εργασιών που εκκρεμούν με βάση την προτεραιότητα. " +
                "\n4. Αναζήτηση τωνεργασιών που εκκρεμούνμε βάση την κατηγορία." +
                "\n5. Αναζήτηση εκκρεμοτήτων για συγκεκριμένη ημέρα." +
                "\n6. Αναζήτηση των εργασιών που εκκρεμούν μέχρι κάποιο συγκεκριμένο χρονικό διάστημα(ημέρα και ώρα)." +
                "\n7. Κατάλληλος χαρακτηρισμός μιας εργασίας όταν έχει περατωθεί." +
                "\n8. Έλεγχος και αλλαγή της κατάστασης των εργασιών στις οποίες έχει παρέλθει η ημερομηνία τους (θα πρέπει να χαρακτηρισθούν ως ολοκληρωμένες)." +
                "\n9. Εμφάνιση ιστορικού με όλες τις εργασίες που έχουν ολοκληρωθεί." +
                "\n0. Έξοδος");
        while (!input.hasNextInt()) input.next();
        return input.nextInt();
    }

}

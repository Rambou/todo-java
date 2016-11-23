import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskManager {

    private ArrayList<Task> todoList;

    // Αρχικοποίηση constructor με εισαγωγή της λίστας εργασιών
    public TaskManager(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    public void insertTask(String task) throws ParseException, ArrayIndexOutOfBoundsException {
        // parse δεδομένων
        String Priority = task.split(",")[0].trim();
        String Category = task.split(",")[1].trim();
        String Description = task.split(",")[2].trim();
        // pattern για ώρα/ημερομηνία
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date DateTime = format.parse(task.split(",")[3].trim());

        // προσθήκη εργασίας στην λίστα
        todoList.add(new Task(Priority, Category, Description, DateTime));
    }

    public void showIncompleteTasks() {
        System.out.println("Ανολοκλήρωτες εκκρεμότητες:");
        // Επανάληψη και εκτύπωση εργασιών που είναι ανολοκλήρωτες
        for (Task task : todoList) {
            if (task.getStatus().equals(Task.status.INCOMPLETE))
                System.out.println(task);
        }
    }

    public void searchByPriority(String Priority) {
        // Επανάληψη και εκτύπωση εργασιών που με συγκεκριμένη προτεραιότητα
        for (Task task : todoList) {
            if (task.getPriority().equals(Priority)) {
                System.out.println(task);
            }
        }
    }

    public void searchByCategory(String Category) {
        // Επανάληψη και εκτύπωση εργασιών που με συγκεκριμένη κατηγορία
        for (Task task : todoList) {
            if (task.getCategory().equals(Category)) {
                System.out.println(task);
            }
        }
    }

    public void searchByDate(String day) throws ParseException {
        // parse ημέρας
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(day);
        // αρχικοποίηση ημερολογίου
        Calendar cal1 = Calendar.getInstance(),
                cal2 = Calendar.getInstance();
        // θέτει την ημερομηνία που αναζητούμε
        cal1.setTime(date);

        // Επανάληψη για κάθε εγασία στην λίστα
        for (Task task : todoList) {
            // θέτει την ημερομηνία κάθε εργασίας που αναζητούμε
            cal2.setTime(task.getDate());
            // κάνει την σύγκριση
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

            // ελέγχει αν πρόκειται για την ίδια μέρα
            // και την εκτυπώνει
            if (sameDay) {
                System.out.println(task);
            }
        }
    }

    public void searchByDate(String day, String time) throws ParseException {
        // parse ημερομηνία/ώρα
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = format.parse(day + " " + time);

        // επαναλάψη σε όλες τις εργασίες και έλεγχος αν η εργασία
        // είναι πριν από την ημερομηνία που δώσαμε
        for (Task task : todoList) {
            if (task.getDate().before(date)) {
                System.out.println(task);
            }
        }
    }

    public void done(Task task) {
        task.setStatus(Task.status.COMPLETED);
    }

    public void checkCompleted(String Date) throws ParseException {
        // parse ημερομηνία/ώρα
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date DateTime = format.parse(Date.trim());

        // επαναλάψη σε όλες τις εργασίες και έλεγχος αν η εργασία
        // είναι πριν από την ημερομηνία που δώσαμε
        // την θέτει ώς ολοκληρωμένη
        for (Task task : todoList) {
            if (task.getDate().before(DateTime)) {
                done(task);
            }
        }
    }

    public void showCompleteTasks() {
        System.out.println("Ολοκλήρωμενες εκκρεμότητες:");
        for (Task task : todoList) {
            if (task.getStatus().equals(Task.status.COMPLETED))
                System.out.println(task);
        }
    }

}

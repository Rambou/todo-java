import java.util.Date;

public class Task {
    private String Priority;
    private String Category;
    private String Description;
    private Date DateTime;
    private status Status;

    // enum για μεατβλητή με δυο τιμές ολκληρωμένης και αναλοκλήρωτης εργασίας
    public enum status {INCOMPLETE, COMPLETED}

    // Αρχικοποίηση constructor
    public Task(String priority, String category, String description, Date date) {
        this.Priority = priority;
        this.Category = category;
        this.Description = description;
        this.DateTime = date;
        this.Status = status.INCOMPLETE;
    }

    // Δημιουργίας getter
    public String getPriority() {
        return Priority;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDate() {
        return DateTime;
    }

    public status getStatus() {
        return Status;
    }

    public void setStatus(status status) {
        this.Status = status;
    }

    // Override συνάρτησης toString για εκτύπωση αντικειμένου
    @Override
    public String toString() {
        return "Προτεραιότητα: " + Priority +
                ", Κατηγορία: " + Category +
                ", Περιγραφή: " + Description +
                ", Ημερομηνία: " + DateTime +
                ", Κατάσταση: " + (Status.equals(status.COMPLETED) ? "Ολοκληρωμένη" : "Aνολοκλήρωτη");
    }
}

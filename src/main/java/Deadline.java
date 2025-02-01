import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) throws ElmachoExceptions {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLO!!!!!!! Your deadline is empty.");
        }
        if (by == null) {
            throw new ElmachoExceptions("HELLO!!!!!!! When is your task due by??");
        }
        this.by = by;
    }

    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (isDone) {
            return "D|" + 1 + "|" + description + "/by" + by.format(formatter);
        }
        return "D|" + 0 + "|" + description + "/by" + by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}

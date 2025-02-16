package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        assert by != null : "Date should not be null";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dueTime = LocalDateTime.parse(by, formatter);
        this.by = dueTime;
    }

    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (isDone) {
            return "D " + 1 + "/" + description + "/By" + by.format(formatter);
        }
        return "D " + 0 + "/" + description + "/By" + by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "[D]" + super.toString() + " (By: " + by.format(formatter) + ")";
    }
}

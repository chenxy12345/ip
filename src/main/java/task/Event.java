package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime fromTime = LocalDateTime.parse(from, formatter);
        LocalDateTime toTime = LocalDateTime.parse(to, formatter);
        this.from = fromTime;
        this.to = toTime;
    }


    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (isDone) {
            return "E " + 1 + "/" + description + "/From" + from.format(formatter)
                    + " To" + to.format(formatter);
        }
        return "E " + 0 + "/" + description + "/From" + from.format(formatter)
                + " To" + to.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "[E]" + super.toString() + " \n    (From: " + from.format(formatter)
                + "\n     To: " + to.format(formatter) + ")";
    }
}

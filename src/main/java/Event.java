import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws ElmachoExceptions {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLOOOO!!! Your event is empty??!??!");
        }
        if (from == null) {
            throw new ElmachoExceptions("HELLOOO! When does your event start??");
        }
        if (to == null) {
            throw new ElmachoExceptions("HELLOOO! When does your event end??");
        }
        this.from = from;
        this.to = to;
    }

    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (isDone) {
            return "E|" + 1 + "|" + description + "/from" + from.format(formatter) + "/to" + to.format(formatter);
        }
        return "E|" + 0 + "|" + description + "/from" + from.format(formatter) + "/to" + to.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}

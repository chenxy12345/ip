public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws ElmachoExceptions {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLOOOO!!! Your event is empty??!??!");
        }
        if (from == null || from.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLOOO! When does your event start??");
        }
        if (to == null || to.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLOOO! When does your event end??");
        }
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

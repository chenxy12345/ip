public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws ElmachoExceptions {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLO!!!!!!! Your deadline is empty.");
        }
        if (by == null || by.trim().isEmpty()) {
            throw new ElmachoExceptions("HELLO!!!!!!! When is your task due by??");
        }
        this.by = by;
    }

    public String getInfo() {
        if (isDone) {
            return "D|" + 1 + "|" + description + "/by" + by;
        }
        return "D|" + 0 + "|" + description + "/by" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

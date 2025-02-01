public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
    }

    public String getInfo() {
        if (super.isDone) {
            return "T|" + 1 + "|" + description;
        }
        return "T|" + 0 + "|" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

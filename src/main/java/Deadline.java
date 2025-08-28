public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskTypes.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        int status;
        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }
        return "D | " + status + " | " + this.description + " | " + this.by;
    }
}

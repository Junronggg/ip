public class Todo extends Task{
    public Todo(String description) {
        super(description, TaskTypes.EVENT);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


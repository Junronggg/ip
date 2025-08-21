public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskTypes type;

    public Task(String description, TaskTypes type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public TaskTypes getType() {
        return type;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

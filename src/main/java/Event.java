import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(description, TaskTypes.EVENT);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
        return "[E]" + super.toString() + " (from: " + start.format(outputFormat) + " to: " + end.format(outputFormat) + ")";
    }

    @Override
    public String toFileString() {
        int status;
        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }
       return "E | " + status + " | " + this.description + " | " + this.start + " to " + this.end ;
    }
}
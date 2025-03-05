import java.time.LocalDateTime;

public class Task {
    private String task_name;
    private String task_text;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime end_at;

    public Task(String name, String task_text, LocalDateTime end_at) {
        this.task_name = name;
        this.task_text = task_text;
        this.end_at = end_at;
        this.created_at = LocalDateTime.now();
        this.updated_at = null;
    }

    public String getName() {
        return this.task_name;
    }

    public void updateName(String new_name) {
        this.task_name = new_name;
        this.updated_at = LocalDateTime.now();
    }

    public void updateText(String task_text) {
        this.task_text = task_text;
        this.updated_at = LocalDateTime.now();
    }

    public void updateEndTime(LocalDateTime date) {
        this.end_at = date;
        this.updated_at = LocalDateTime.now();
    }

    public String toString()
    {
        if (updated_at == null) {
            return String.format("Created:%s Name%s \n" +
                                 "%s \n" +
                                 "Ends at:%s\n", created_at, task_name, task_text, end_at);
        }
        else
        {
            return String.format("Created:%s Updated at:%s Name%s \n" +
                    "%s \n" +
                    "Ends at:%s\n", created_at, updated_at, task_name, task_text, end_at);
        }
    }
}

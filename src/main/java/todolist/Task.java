package src.main.java.todolist;

import java.time.LocalDate;

public class Task {
    private String name;
    private LocalDate dueDate;

    public Task(String name, LocalDate dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return name + " - Due: " + dueDate;
    }
}

package src.main.java.todolist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDoListGUI {
    private TaskManager taskManager = new TaskManager();

    public void showGUI() {
        JFrame frame = new JFrame("To-Do List Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLabel taskLabel = new JLabel("Task Name:");
        JTextField taskField = new JTextField(15);
        JLabel dueDateLabel = new JLabel("Due Date (YYYY-MM-DD):");
        JTextField dueDateField = new JTextField(10);
        JButton addButton = new JButton("Add Task");
        JTextArea taskListArea = new JTextArea(10, 30);
        taskListArea.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(taskListArea);

       
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(dueDateLabel);
        inputPanel.add(dueDateField);
        inputPanel.add(addButton);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskField.getText();
                String dueDateText = dueDateField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                try {
                    LocalDate dueDate = LocalDate.parse(dueDateText, formatter);
                    Task task = new Task(taskName, dueDate);
                    taskManager.addTask(task);
                    taskField.setText("");
                    dueDateField.setText("");
                    updateTaskList(taskListArea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.");
                }
            }
        });

        
        frame.getContentPane().add(BorderLayout.NORTH, inputPanel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

       
        frame.setVisible(true);
    }

    private void updateTaskList(JTextArea taskListArea) {
        taskListArea.setText(""); 
        for (Task task : taskManager.getTasks()) {
            taskListArea.append(task + "\n"); 
        }
    }

    public static void main(String[] args) {
        ToDoListGUI gui = new ToDoListGUI();
        gui.showGUI();
    }
}

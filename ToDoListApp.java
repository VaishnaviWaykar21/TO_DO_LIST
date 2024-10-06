package Level3_Task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToDoListApp extends JFrame {

    private DefaultListModel<String> taskList;
    private JTextField Input;

    public ToDoListApp() {
        setTitle("To-Do List");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Input = new JTextField();
        Input.setFont(new Font("Arial", Font.PLAIN, 28));
        Input.addActionListener(e -> add());
        add(Input, BorderLayout.NORTH);

        taskList = new DefaultListModel<>();
        JList<String> taskView = new JList<>(taskList);
        taskView.setFont(new Font("Arial", Font.PLAIN, 28));
        taskView.setForeground(Color.BLUE); 
        add(new JScrollPane(taskView), BorderLayout.CENTER);


        JPanel panel = new JPanel(new FlowLayout());
        panel.add(createButton("Delete Task", e -> delete(taskView)));
        panel.add(createButton("Mark Complete", e -> Complete(taskView)));

        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        return button;
    }

    private void add() {
        String task = Input.getText().trim();
        if(!task.isEmpty()) {
            taskList.addElement(task);
            Input.setText("");
        }
    }

    private void delete(JList<String> taskView) {
        int index = taskView.getSelectedIndex();
        if(index != -1) {
            taskList.remove(index);
        } 
        else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void Complete(JList<String> taskView) {
        int index = taskView.getSelectedIndex();
        if(index != -1) {
            String t = taskList.getElementAt(index);
            taskList.set(index, t + " (Completed)");
        } 
        else {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as complete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ToDoListApp();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ClinicAppointmentSystem extends JFrame {

    private JTextField nameField, ageField;
    private JComboBox<String> doctorBox, timeBox;
    private JList<String> appointmentList;
    private DefaultListModel<String> appointmentModel;
    private JSpinner dateSpinner;

    public ClinicAppointmentSystem() {
        // Frame setup
        setTitle("Clinic Appointment System");
        setSize(600, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 255, 250));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        // Heading
        JLabel heading = new JLabel("Book a Doctor's Appointment");
        heading.setFont(new Font("Verdana", Font.BOLD, 20));
        heading.setBounds(130, 20, 400, 30);
        add(heading);

        // Patient name
        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(50, 80, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 80, 200, 25);
        add(nameField);

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 120, 100, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(180, 120, 50, 25);
        add(ageField);

        // Doctor selection
        JLabel doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(50, 160, 100, 25);
        add(doctorLabel);

        String[] doctors = {"Dr. Sharma - Dentist", "Dr. Mehta - Cardiologist", "Dr. Roy - Pediatrician"};
        doctorBox = new JComboBox<>(doctors);
        doctorBox.setBounds(180, 160, 250, 25);
        add(doctorBox);

        // Date selection
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 200, 100, 25);
        add(dateLabel);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        dateSpinner.setBounds(180, 200, 120, 25);
        add(dateSpinner);

        // Time selection
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(50, 240, 100, 25);
        add(timeLabel);

        String[] times = {"10:00 AM", "11:30 AM", "2:00 PM", "4:00 PM"};
        timeBox = new JComboBox<>(times);
        timeBox.setBounds(180, 240, 120, 25);
        add(timeBox);

        // Book button
        JButton bookBtn = new JButton("Book Appointment");
        bookBtn.setBounds(180, 290, 200, 35);
        bookBtn.setBackground(new Color(102, 205, 170));
        bookBtn.setForeground(Color.BLACK);
        bookBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(bookBtn);

        // Appointment list
        appointmentModel = new DefaultListModel<>();
        appointmentList = new JList<>(appointmentModel);
        JScrollPane scrollPane = new JScrollPane(appointmentList);
        scrollPane.setBounds(50, 340, 480, 100);
        add(scrollPane);

        // Action Listener for booking
        bookBtn.addActionListener(e -> {
            String patient = nameField.getText().trim();
            String age = ageField.getText().trim();
            String doctor = doctorBox.getSelectedItem().toString();
            Date date = (Date) dateSpinner.getValue();
            String time = timeBox.getSelectedItem().toString();

            if (patient.isEmpty() || age.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String appointment = patient + " (" + age + " yrs) with " + doctor +
                    " on " + new java.text.SimpleDateFormat("dd/MM/yyyy").format(date) + " at " + time;
            appointmentModel.addElement(appointment);
            JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear input fields
            nameField.setText("");
            ageField.setText("");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClinicAppointmentSystem::new);
    }
}

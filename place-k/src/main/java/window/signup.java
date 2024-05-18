package window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signup extends JFrame {
        public signup() {
            setTitle("Sign Up Page");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null); // 화면 중앙에 표시

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(7, 1));

            JLabel titleLabel = new JLabel("Create an Account");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(titleLabel);

            JLabel subtitleLabel = new JLabel("PlaceK : KMU Seat Reservation System");
            panel.add(subtitleLabel);

            JTextField usernameField = new JTextField();
            usernameField.setPreferredSize(new Dimension(200, 30));
            usernameField.setBorder(BorderFactory.createTitledBorder("Enter your ID"));
            panel.add(usernameField);

            String[] majors = {"Select your major", "Computer Science", "Electrical Engineering", "Mechanical Engineering", "Civil Engineering", "Chemical Engineering"};
            JComboBox<String> majorComboBox = new JComboBox<>(majors);
            majorComboBox.setPreferredSize(new Dimension(200, 30));
            panel.add(majorComboBox);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(200, 30));
            passwordField.setBorder(BorderFactory.createTitledBorder("Enter your password"));
            panel.add(passwordField);

            JPasswordField passwordCheckField = new JPasswordField();
            passwordCheckField.setPreferredSize(new Dimension(200, 30));
            passwordCheckField.setBorder(BorderFactory.createTitledBorder("Check your password again"));
            panel.add(passwordCheckField);

            JButton signUpButton = new JButton("Sign up");


            //signUpButton event 추가
//            signUpButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    String username = usernameField.getText(); // usernameField의 값을 가져옴
//                    UserController.createUser(username);
//                }
//            });
            panel.add(signUpButton);

            JLabel moveLabel = new JLabel("Already have an account?");
            JButton signInButton = new JButton("Sign in");
            panel.add(moveLabel);
            panel.add(signInButton);

            setContentPane(panel);
            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(signup::new);
        }
}

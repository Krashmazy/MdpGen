// SECURE LIB
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

//UI FRAME
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordGeneratorGUI extends JFrame implements ActionListener {

    // Définition des caractères possibles pour le mot de passe
    private static final String ALPHA_NUM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

    private JTextField lengthField;
    private JLabel passwordLabel;

    public PasswordGeneratorGUI() {
        //Configuration de l'UI
        super("Générateur de mot de passe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);

        //éléments sur l'interface
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lengthLabel = new JLabel("Longueur du mot de passe :");
        lengthField = new JTextField(10);
        JButton generateButton = new JButton("Générer mon mot de passe sécurisé");
        passwordLabel = new JLabel("");

        // élément + bouton
        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(generateButton);
        panel.add(passwordLabel);

        // Ajout d'un panneau à la fenêtre
        add(panel);

        // évenement
        generateButton.addActionListener(this);

        // Afficher la fenêtre
        setVisible(true);
    }

    // Algo pour générer le mot de passe sécurisé
    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder(length);

        // Ajout des caractères aléatoire
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHA_NUM_CHARS.length());
            passwordBuilder.append(ALPHA_NUM_CHARS.charAt(randomIndex));
        }

        return passwordBuilder.toString();
    }

    // ajout de l'interface ActionListener pour la gestion des événements
    @Override
    public void actionPerformed(ActionEvent e) {
        // Récupération de la longueur demandé par l'utilisateur
        String lengthText = lengthField.getText();
        int passwordLength = 0;

        try {
            passwordLength = Integer.parseInt(lengthText);
        } catch (NumberFormatException ex) {
            passwordLabel.setText("La longueur doit être 12 ou 18.");
            return;
        }

        // Check de la longueur du mdp
        if (passwordLength != 12 && passwordLength != 18) {
            passwordLabel.setText("La longueur doit être soit 12 soit 18.");
            return;
        }

        // Génération du mdp suite
        String password = generateRandomPassword(passwordLength);
        passwordLabel.setText("Votre mot de passe : " + password);
    }

    public static void main(String[] args) {
        new PasswordGeneratorGUI();
    }
}

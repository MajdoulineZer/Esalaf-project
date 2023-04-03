package ma.fstt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreditTEST {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        // Connexion à la base de données
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esalaf", "root", "");
        } catch (SQLException e) {
            System.out.println("La connexion à la base de données a échoué: " + e.getMessage());
            System.exit(1);
        }

        CreditDAO creditDAO = new CreditDAO();

        try {
            // Récupération de l'ID du client à partir de l'entrée utilisateur
            System.out.print("Entrez l'ID du client: ");
            long idClient = scanner.nextLong();

            // Récupération de la date à partir de l'entrée utilisateur
            System.out.print("Entrez la date (format : jj/mm/aaaa) : ");
            String dateString = scanner.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Format de date invalide: " + e.getMessage());
                System.exit(1);
            }

            // Calcul du total des crédits du client
            double totalCredits = creditDAO.getTotalByClient(idClient);

            // Création d'un objet crédit avec les données saisies
            Credit credit = new Credit(0, idClient, totalCredits, date);

            // Enregistrement du crédit dans la base de données
            creditDAO.save(credit);

            // Affichage d'un message de confirmation
            System.out.println("Le crédit a été enregistré avec succès.");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'enregistrement du crédit: " + e.getMessage());
        } finally {
            // Fermeture de la connexion à la base de données
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("La fermeture de la connexion à la base de données a échoué: " + e.getMessage());
                }
            }
        }
    }
}

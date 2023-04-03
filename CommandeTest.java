package ma.fstt.model;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.Scanner;
import ma.fstt.model.CommandeDAO;

public class CommandeTest {

    public static void main(String[] args) {

        CommandeDAO commandeDAO = null;
        try {
            commandeDAO = new CommandeDAO();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de l'objet CommandeDAO : " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du client : ");
        long idClient = scanner.nextLong();
        scanner.nextLine(); // consomme la fin de la ligne
        System.out.print("Entrez l'ID du produit : ");
        long idProduit = scanner.nextLong();
        scanner.nextLine(); // consomme la fin de la ligne
        System.out.print("Entrez la date de la commande (format AAAAMMJJ) : ");
        long dateCmd = scanner.nextLong();

        Commande commande = new Commande(1L, idClient, idProduit, dateCmd);
        try {
            commandeDAO.save(commande);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la commande : " + e.getMessage());
        }
    }
}
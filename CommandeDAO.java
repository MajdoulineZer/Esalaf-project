package ma.fstt.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {

    public CommandeDAO() throws SQLException {

        super();
    }
    private Long generateNewId() throws SQLException {
        String query = "SELECT MAX(id_commande) FROM commandes";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    Long maxId = result.getLong(1);
                    if (maxId != null) {
                        return maxId + 1L;
                    }
                }
                return 1L;
            }
        }
    }
    @Override

    public void save(Commande commande) throws SQLException {
        String queryClient = "SELECT * FROM client WHERE id_client=?";
        String queryProduit = "SELECT * FROM produits WHERE id_produit=?";

        // Vérification que l'ID de la commande a été initialisé
        if (commande.getIdCmd() == null) {
            commande.setIdCmd(generateNewId()); // générez un nouvel ID pour la commande
            System.out.println("L'ID de la commande a été initialisé à " + commande.getIdCmd());
        }

        try (PreparedStatement stmtClient = connection.prepareStatement(queryClient)) {
            stmtClient.setLong(1, commande.getIdClient());
            try (ResultSet resultClient = stmtClient.executeQuery()) {
                if (!resultClient.next()) {
                    System.out.println("Le client n'existe pas dans la base de données");
                    return;
                }
            }
        }

        try (PreparedStatement stmtProduit = connection.prepareStatement(queryProduit)) {
            stmtProduit.setLong(1, commande.getIdProduit());
            try (ResultSet resultProduit = stmtProduit.executeQuery()) {
                if (!resultProduit.next()) {
                    System.out.println("Le produit n'existe pas dans la base de données");
                    return;
                }
            }
        }

        String query = "INSERT INTO commandes(id_commande, id_client, id_produit, date_cmd) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, commande.getIdCmd());
            stmt.setLong(2, commande.getIdClient());
            stmt.setLong(3, commande.getIdProduit());
            stmt.setLong(4, commande.getDateCmd());
            stmt.executeUpdate();
        }
    }



    @Override

    public void update(Commande commande) throws SQLException {
        String query = "UPDATE commandes SET id_client=?, id_produit=?, date_cmd=? WHERE id_commande=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, commande.getIdCmd());
            stmt.setLong(2, commande.getIdClient());
            stmt.setLong(3, commande.getIdProduit());
            stmt.setLong(4, commande.getDateCmd());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("La mise à jour de la commande a échoué : la commande n'existe pas dans la base de données");
            }
        }
    }


    @Override
    public void delete(Commande commande) throws SQLException {
        String query = "DELETE FROM commandes WHERE id_commande=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, commande.getIdCmd());
            stmt.executeUpdate();
        }
    }


    public Commande getOne(Long id) throws SQLException {
        String query = "SELECT * FROM commandes WHERE id_commande=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    long id_commande = result.getLong("id_commande");
                    long id_client = result.getLong("id_client");
                    long id_produit = result.getLong("id_produit");
                    long date_cmd = result.getLong("date_cmd");
                    return new Commande(id_commande, id_client, id_produit, date_cmd);
                }
                System.out.println("La commande n'existe pas dans la base de données");
                return null;
            }
        }
    }
    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM commandes";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    long id_commande = result.getLong("id_commande");
                    long id_client = result.getLong("id_client");
                    long id_produit = result.getLong("id_produit");
                    long date_cmd = result.getLong("date_cmd");
                    commandes.add(new Commande(id_commande, id_client, id_produit, date_cmd));
                }
            }
            return commandes;
        }
    }
}
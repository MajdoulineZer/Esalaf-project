package ma.fstt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.fstt.model.Credit;

public class CreditDAO extends BaseDAO<Credit> {


    public CreditDAO() throws SQLException {

        super();
    }

    public void save(Credit credit) throws SQLException {
        String query = "INSERT INTO credit (id_credit, id_client, date_credit, total) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, credit.getIdCredit());
        ps.setLong(2, credit.getIdClient());
        ps.setDate(3, new java.sql.Date(credit.getDateC().getTime()));
        ps.setDouble(4, credit.getTotal());
        ps.executeUpdate();
        ps.close();
    }

    public void update(Credit credit) throws SQLException {
        // Mettre à jour l'objet Credit avec le nouveau total
        double newTotal = getTotalByClient(credit.getIdClient());
        credit.setTotal(newTotal);

        // Exécuter la requête d'update
        String query = "UPDATE credit SET id_client = ?, date_credit = ?, total = ? WHERE id_credit = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, credit.getIdClient());
        ps.setDate(2, new java.sql.Date(credit.getDateC().getTime()));
        ps.setDouble(3, credit.getTotal());
        ps.setLong(4, credit.getIdCredit());
        ps.executeUpdate();
        ps.close();
    }


    public void delete(Credit credit) throws SQLException {
        String query = "DELETE FROM credit WHERE id_credit = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, credit.getIdCredit());
        ps.executeUpdate();
        ps.close();
    }


    public List<Credit> getAll() throws SQLException {
        String query = "SELECT * FROM credit";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Credit> credits = new ArrayList<>();
        while (rs.next()) {
            long idCredit = rs.getLong("id_credit");
            long idClient = rs.getLong("id_client");
            Date dateC = rs.getDate("date_credit");
            double total = rs.getDouble("total");
            Credit credit = new Credit(idCredit, idClient, total, dateC);
            credits.add(credit);
        }


        rs.close();
        ps.close();
        return credits;
    }

    @Override
    public Credit getOne(Long idCredit) throws SQLException {
        String query = "SELECT * FROM credit WHERE id_credit = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, idCredit);
        ResultSet rs = ps.executeQuery();
        Credit credit = null;
        if (rs.next()) {
            long idClient = rs.getLong("id_client");
            Date dateC = rs.getDate("date_credit");
            double total = rs.getDouble("total");
            long idCredits = rs.getLong("id_credit");
            credit = new Credit(idCredits, idClient, total, dateC);
        }
        rs.close();
        ps.close();
        return credit;
    }

    public double getTotalByClient(long idClient) throws SQLException {
        String query = "SELECT SUM(prix) as total FROM commandes c JOIN produits p ON c.id_produit = p.id_produit WHERE c.id_client = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, idClient);
        ResultSet rs = ps.executeQuery();
        double total = 0.0;
        if (rs.next()) {
            total = rs.getDouble("total");
        }
        rs.close();
        ps.close();
        return total;
    }













}


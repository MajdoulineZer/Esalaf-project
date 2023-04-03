package ma.fstt.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit> {


    public ProduitDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Produit produit) throws SQLException {
        String req = "INSERT INTO produits (reference, designation, prix) VALUES (?, ?, ?);";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, produit.getReference());
        this.preparedStatement.setString(2, produit.getDesignation());
        this.preparedStatement.setDouble(3, produit.getPrix());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Produit produit) throws SQLException {
        String req = "UPDATE produits SET reference=?, designation=?, prix=? WHERE id_produit=?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, produit.getReference());
        this.preparedStatement.setString(2, produit.getDesignation());
        this.preparedStatement.setDouble(3, produit.getPrix());
        this.preparedStatement.setLong(4, produit.getId_produit());

        this.preparedStatement.execute();
    }

    @Override
    public void delete(Produit produit) throws SQLException {
        String req = "DELETE FROM produits WHERE id_produit=?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, produit.getId_produit());

        this.preparedStatement.execute();
    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        String re = "SELECT * FROM produits WHERE id_produit=?;";

        this.preparedStatement = this.connection.prepareStatement(re);
        this.preparedStatement.setLong(1, id);

        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            return new Produit(
                    this.resultSet.getLong("id_produit"),
                    this.resultSet.getString("reference"),
                    this.resultSet.getString("designation"),
                    this.resultSet.getDouble("prix")
            );
        } else {
            return null;
        }
    }

    @Override
    public List<Produit> getAll() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String re = "SELECT * FROM produits;";

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(re);

        while (this.resultSet.next()) {
            Produit produit = new Produit(
                    this.resultSet.getLong("id_produit"),
                    this.resultSet.getString("reference"),
                    this.resultSet.getString("designation"),
                    this.resultSet.getDouble("prix")
            );

            produits.add(produit);
        }

        return produits;
    }




}





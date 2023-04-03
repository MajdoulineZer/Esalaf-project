package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class ProduitTest {

    public static void main(String[] args) {

        // création d'un produit
        Produit prod = new Produit(0l,"REF01", "Produit 1", 100.0);


        try {
            // création d'une instance de ProduitDAO
            ProduitDAO prodDao = new ProduitDAO();

            // ajout du produit à la base de données
            prodDao.save(prod);

            // récupération de la liste des produits
            List<Produit> produits = prodDao.getAll();

            // affichage des produits
            for (Produit p : produits) {
                System.out.println(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


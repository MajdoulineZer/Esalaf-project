package ma.fstt.model;

public class Produit {
    private Long id_produit;
    private String reference;
    private String designation;
    private Double prix;

    public Produit() {
    }

    public Produit(Long id_produit, String reference, String designation, Double prix) {
        this.id_produit = id_produit;
        this.reference = reference;
        this.designation = designation;
        this.prix = prix;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return Long.toString(this.id_produit);
    }
}


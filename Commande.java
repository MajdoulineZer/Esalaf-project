package ma.fstt.model;

public class Commande {
    private Long idCmd;
    private Long idClient;
    private Long idProduit;
    private Long dateCmd;

    // Constructeur par défaut
    public Commande() {}

    // Constructeur avec paramètres
    public Commande(Long idCmd, Long idClient, Long idProduit, Long dateCmd) {
        this.idCmd = idCmd;
        this.idClient = idClient;
        this.idProduit = idProduit;
        this.dateCmd = dateCmd;
    }

    // Getters
    public Long getIdCmd() {
        return idCmd;
    }

    public Long getIdClient() {
        return idClient;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public Long getDateCmd() {
        return dateCmd;
    }

    // Setters
    public void setIdCmd(Long idCmd) {
        this.idCmd = idCmd;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public void setDateCmd(Long dateCmd) {
        this.dateCmd = dateCmd;
    }
}



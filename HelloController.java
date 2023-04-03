package ma.fstt.eslaf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.fstt.model.Credit;
import java.io.IOException;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;
import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ma.fstt.model.Client;
import ma.fstt.model.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import ma.fstt.model.CreditDAO;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import ma.fstt.model.Produit;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //Client DAO-------------------------------------------------------------------------------------------
    @FXML
    private TextField nom;

    @FXML
    private TextField tele;


    @FXML
    private TableView<Client> mytab;

    @FXML
    private TableColumn<Client, Long> col_id;

    @FXML
    private TableColumn<Client, String> col_nom;

    @FXML
    private TableColumn<Client, String> col_tele;

    @FXML
    protected void onSaveButtonClick() {

        Client cli = new Client(0l, nom.getText(), tele.getText());

        try {
            ClientDAO clidao = new ClientDAO();

            clidao.save(cli);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }

    @FXML
    protected void onDeleteButtonClick() {
        Client selectedClient = mytab.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            try {
                ClientDAO clidao = new ClientDAO();

                clidao.delete(selectedClient);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            UpdateTable();
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        Client selectedClient = mytab.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            try {
                ClientDAO clidao = new ClientDAO();

                // Mettre à jour les champs de l'objet Client sélectionné
                selectedClient.setNom(nom.getText());
                selectedClient.setTelepehone(tele.getText());

                clidao.update(selectedClient);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Mettre à jour la ligne sélectionnée dans la table
            int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
            mytab.getItems().set(selectedIndex, selectedClient);
        }
    }

    @FXML
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Client, Long>("id_client"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<Client, String>("telepehone"));


        mytab.setItems(getDataClients());
        col_id2.setCellValueFactory(new PropertyValueFactory<Produit, Long>("id_produit"));
        col_ref.setCellValueFactory(new PropertyValueFactory<Produit, String>("reference"));
        col_des.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));

        mytab2.setItems(getDataproduits());
    }

    public static ObservableList<Client> getDataClients() {

        ClientDAO clidao = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clidao = new ClientDAO();
            for (Client ettemp : clidao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        updateTable();
        modifierTable();
        modifiertable();


    }

    //Produit DAO-------------------------------------------------------------------------------------
    @FXML
    private TextField ref;

    @FXML
    private TextField des;
    @FXML
    private TextField prix;

    @FXML
    private TableView<Produit> mytab2;

    @FXML
    private TableColumn<Produit, Long> col_id2;

    @FXML
    private TableColumn<Produit, String> col_ref;

    @FXML
    private TableColumn<Produit, String> col_des;

    @FXML
    private TableColumn<Produit, Double> col_prix;

    @FXML
    protected void onSavButtonClick() {
        Produit prod = new Produit(0L, ref.getText(), des.getText(), Double.parseDouble(prix.getText()));

        try {
            ProduitDAO proddao = new ProduitDAO();

            proddao.save(prod);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }

    @FXML
    protected void ondeleteButtonClick() {
        Produit selectedProduit = mytab2.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            try {
                ProduitDAO proddao = new ProduitDAO();

                proddao.delete(selectedProduit);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            UpdateTable();
        }
    }

    @FXML
    protected void onupdateButtonClick() {
        Produit selectedProduit = mytab2.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            try {
                ProduitDAO proddao = new ProduitDAO();

                // Mettre à jour les champs de l'objet Produit sélectionné
                selectedProduit.setReference(ref.getText());
                selectedProduit.setDesignation(des.getText());
                selectedProduit.setPrix(Double.parseDouble(prix.getText()));

                proddao.update(selectedProduit);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Mettre à jour la ligne sélectionnée dans la table
            int selectedIndex = mytab2.getSelectionModel().getSelectedIndex();
            mytab2.getItems().set(selectedIndex, selectedProduit);
        }
    }

    @FXML
    public void updateTable() {
        col_id2.setCellValueFactory(new PropertyValueFactory<Produit, Long>("id_produit"));
        col_ref.setCellValueFactory(new PropertyValueFactory<Produit, String>("reference"));
        col_des.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));

        mytab2.setItems(getDataproduits());
    }

    public static ObservableList<Produit> getDataproduits() {
        ProduitDAO proddao = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            proddao = new ProduitDAO();
            for (Produit ettemp : proddao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }

    //Commande-------------------------------------------------------------------------------------------------
    @FXML
    private TableView<Commande> mytab3;
    @FXML
    private TableColumn<Commande, Long> col_id3;
    @FXML
    private TableColumn<Commande, Long> col_idcl;
    @FXML
    private TableColumn<Commande, Long> col_idpr;
    @FXML
    private TableColumn<Commande, Long> col_date;
    @FXML
    private TextField DATE;
    @FXML
    private TextField IDC;
    @FXML
    private TextField IDP;

    private CommandeDAO commandeDAO;

    public HelloController() {
        try {
            commandeDAO = new CommandeDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public static ObservableList<Commande> getDataCommande() {
        CommandeDAO cmddao = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            cmddao = new CommandeDAO();
            for (Commande cmdtemp : cmddao.getAll())
                listfx.add(cmdtemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }






    @FXML
    public void onsaVeButtonClick(ActionEvent event) throws SQLException {
        Commande commande = new Commande();
        try {
            commande.setIdClient(Long.parseLong(IDC.getText()));
            commande.setIdProduit(Long.parseLong(IDP.getText()));
            commande.setDateCmd(Long.parseLong(DATE.getText()));
            commandeDAO.save(commande);
            mytab3.getItems().add(commande);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Valeur incorrecte");
            alert.setContentText("Veuillez entrer un nombre entier pour les champs ID client et ID produit.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onUpdateButton(ActionEvent event) throws SQLException {
        Credit selectedCredit = creditTableView.getSelectionModel().getSelectedItem();
        if (selectedCredit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Sélectionner un crédit");
            alert.setContentText("Veuillez sélectionner un crédit à modifier.");
            alert.showAndWait();
            return;
        }

        // Récupération du nouvel ID client à partir du champ de texte
        Long newIdClient = null;
        if (!idClientTextField.getText().isEmpty()) {
            newIdClient = Long.parseLong(idClientTextField.getText());
        }

        // Récupération du total mis à jour pour le nouveau client
        double newTotal = 0;
        CreditDAO creditDAO = new CreditDAO();
        newTotal = creditDAO.getTotalByClient(newIdClient);

        // Mise à jour des données du crédit sélectionné avec le nouvel ID client et le nouveau total
        selectedCredit.setIdClient(newIdClient);
        selectedCredit.setTotal(newTotal);

        // Appel de la méthode update pour mettre à jour le crédit dans la base de données
        creditDAO.update(selectedCredit);

        // Mise à jour de la TableView pour afficher les modifications
        modifiertable();
    }


    @FXML
    public void onDeleteButton(ActionEvent event) throws SQLException {
        Credit selectedCredit = creditTableView.getSelectionModel().getSelectedItem();
        if (selectedCredit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Sélectionner un crédit");
            alert.setContentText("Veuillez sélectionner un crédit à supprimer.");
            alert.showAndWait();
            return;
        }

        CreditDAO creditDAO = new CreditDAO();
        creditDAO.delete(selectedCredit);
        modifiertable();
    }


    @FXML
    public void modifierTable() {
        col_idpr.setCellValueFactory(new PropertyValueFactory<Commande, Long>("idProduit"));
        col_idcl.setCellValueFactory(new PropertyValueFactory<Commande, Long>("idClient"));
        col_date.setCellValueFactory(new PropertyValueFactory<Commande, Long>("dateCmd"));
        col_id3.setCellValueFactory(new PropertyValueFactory<Commande, Long>("idCmd"));

        mytab3.setItems(getDataCommande());
    }
    @FXML
    public void onDeletebuttonClick(ActionEvent event) {
        // Récupérer la commande sélectionnée dans le tableau
        Commande commande = mytab3.getSelectionModel().getSelectedItem();
        if (commande != null) {
            try {
                // Supprimer la commande de la base de données
                commandeDAO.delete(commande);
                // Supprimer la commande de la liste affichée dans le tableau
                mytab3.getItems().remove(commande);
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Suppression impossible");
                alert.setContentText("Une erreur s'est produite lors de la suppression de la commande.");
                alert.showAndWait();
            }
        } else {
            // Aucune commande sélectionnée dans le tableau
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Aucune commande sélectionnée");
            alert.setContentText("Veuillez sélectionner une commande à supprimer dans le tableau.");
            alert.showAndWait();
        }
    }
    //Credit----------------------------------------------------------------------------------------------
    @FXML
    private TextField idClientTextField;
    @FXML
    private TextField dateCreditTextField;
    @FXML
    private TableView<Credit> creditTableView;
    @FXML
    private TableColumn<Credit, Long> idClientColumn;
    @FXML
    private TableColumn<Credit, Long> idCreditColumn;
    @FXML
    private TableColumn<Credit, Date> creditDateColumn;
    @FXML
    private TableColumn<Credit, Double> TotalDateColumn;
    @FXML
    public void ButtonClick(ActionEvent event) throws SQLException {
        // Récupération de l'ID client et de la date à partir des textfields
        Long idClient = null;
        if (!idClientTextField.getText().isEmpty()) {
            idClient = Long.parseLong(idClientTextField.getText());
        }
        Date dateCredit = null;
        if (!dateCreditTextField.getText().isEmpty()) {
            try {
                dateCredit = new SimpleDateFormat("yyyy-MM-dd").parse(dateCreditTextField.getText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Appel de la méthode getTotalByClient pour récupérer le total pour ce client
        double total = 0;
        CreditDAO creditDAO = new CreditDAO();
        total = creditDAO.getTotalByClient(idClient);

        // Création d'un nouvel objet Credit avec les données récupérées et le total calculé
        Credit credit = new Credit(0, idClient, total, dateCredit);

        // Appel de la méthode save du CreditDAO pour enregistrer le crédit dans la base de données
        creditDAO.save(credit);

        // Mise à jour de la TableView pour afficher le nouveau crédit
        modifiertable();
    }



    @FXML
    public void modifiertable() {
        idCreditColumn.setCellValueFactory(new PropertyValueFactory<Credit, Long>("idClient"));
        idClientColumn.setCellValueFactory(new PropertyValueFactory<Credit, Long>("idCredit"));
        creditDateColumn.setCellValueFactory(new PropertyValueFactory<Credit, Date>("dateC"));
        TotalDateColumn.setCellValueFactory(new PropertyValueFactory<Credit, Double>("total"));
        creditTableView.setItems(getCreditData());
    }

    @FXML
    public static ObservableList<Credit> getCreditData() {

        CreditDAO creditDAO = null;

        ObservableList<Credit> creditList = FXCollections.observableArrayList();

        try {
            creditDAO = new CreditDAO();
            for (Credit credit : creditDAO.getAll())
                creditList.add(credit);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return creditList;
    }







}






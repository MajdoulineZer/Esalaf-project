package ma.fstt.model;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends  BaseDAO<Client> {
    public ClientDAO() throws SQLException {

        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Client object) throws SQLException {

        String req = "insert into client (nom , telephone) values (? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelepehone());


        this.preparedStatement.execute();

    }

    @Override
    public void update(Client object) throws SQLException {
        String req = "UPDATE client SET nom=?, telephone=? WHERE id_client=?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelepehone());
        this.preparedStatement.setLong(3, object.getId_client());

        this.preparedStatement.execute();
    }


    @Override
    public void delete(Client object) throws SQLException {
        String re = "DELETE FROM client WHERE nom = ? AND telephone = ?;";
        this.preparedStatement = this.connection.prepareStatement(re);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelepehone());


        this.preparedStatement.execute();

    }


    @Override
    public Client getOne(Long id) throws SQLException {
        String query = "SELECT * FROM client WHERE id_client = ?;";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setLong(1, id);
        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            return new Client(
                    this.resultSet.getLong("id_client"),
                    this.resultSet.getString("nom"),
                    this.resultSet.getString("telephone")

            );
        } else {
            return null;
        }
    }



    // mapping relation --> objet
    @Override
    public List<Client> getAll() throws SQLException{

        List<Client> mylist = new ArrayList<Client>();
        String req = " select * from client" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Client(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getString(3)));


        }

        return mylist;
    }




}


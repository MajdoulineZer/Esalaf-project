package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {

      //Client----------------------------------------------------
        try {
            // entity
            Client cli  = new Client(1L,"root","1234");

            //Transacatio
            ClientDAO clidao = new ClientDAO();

            // save trasanction
            clidao.save(cli);


            List<Client> mylist =  clidao.getAll();

            for (Client temp :mylist) {

                System.out.println(temp.toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

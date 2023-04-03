package ma.fstt.model;

import java.util.Date;


public class Credit {
    private Long idCredit;
    private Long idClient;
    private double total;
    private Date dateC;

    public Credit(long idCredit, long idClient, double total, Date dateC) {
        this.idCredit = idCredit;
        this.idClient = idClient;
        this.total = total;
        this.dateC = dateC;
    }

    public long getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(long idCredit) {
        this.idCredit = idCredit;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDateC() {
        return dateC;
    }

    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "idCredit=" + idCredit +
                ", idClient=" + idClient +
                ", total=" + total +
                ", date=" + dateC +
                '}';
    }
}

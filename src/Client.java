import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client extends Entite {
    private String nom;
    private String prenom;
    private int age;

    public Client(int id, String nom, String prenom, int age) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public void incrementAge() {
        age++;
    }

    protected boolean ajoutBD(String nomBD, String nomTable, int id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = CreationBD.seConnecter(nomBD);
            stmt = conn.createStatement();
            String sql = "INSERT INTO " + nomTable + " (id, nom, prenom, age) VALUES (" + id + ", " + nom + ", " + prenom + ", " + age + ")";
            ResultSet res = stmt.executeQuery(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (stmt!= null) stmt.close();
                if (conn!= null) conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {

        return "Client{" +
               "nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               ", age=" + age +
               '}';
    }

}

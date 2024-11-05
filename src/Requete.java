import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Requete {

    /**
     * nosClients qui affiche les informations relatives `a tous les clients
     */
    public static void nosClients() throws SQLException {
        String request = "SELECT * FROM CLIENT ORDER BY id";
        try (Connection conn = CreationBD.seConnecter("bd_prets")) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Liste des clients : ");
            while (res.next()) {
                System.out.println(
                        " " + res.getInt("Id") + ", " + res.getString("Nom")
                        + ", " + res.getString("Prenom"));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }
    /**
     * nosClientsAdultes qui affiche les clients ayant plus de 18 ans (l’affichage devra être par ordre croissant sur l’âge)
     */
    public static void nosClientsAdultes(){
        String request = "SELECT * FROM CLIENT WHERE age>=18 ORDER BY age";
        try (Connection conn = CreationBD.seConnecter("bd_prets")) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Liste des clients majeurs : ");
            while (res.next()) {
                System.out.println(
                        " " + res.getInt("Id") + ", " + res.getString("Nom")
                        + ", " + res.getString("Prenom"));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }
    /**
     * nosEmprunteurs qui affiche les clients ayant emprunter au moins un article
     */
    public static void nosEmprunteurs(){
        //TODO
        String request = "SELECT 0";
        try (Connection conn = CreationBD.seConnecter("bd_prets")) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Liste des clients majeurs : ");
            while (res.next()) {
                System.out.println(
                        " " + res.getInt("Id") + ", " + res.getString("Nom")
                        + ", " + res.getString("Prenom"));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }
    /**
     * nombrePrets, nombreClients et nombreProprios qui retournent respectivement le nombre de prêts,
     * le nombre de clients et le nombre de propri´etaires ;
     */
    public static int nombrePrets(){
        int resInt = -1;
        String request = "SELECT COUNT(*) FROM PRET";
        try (Connection conn = CreationBD.seConnecter("bd_prets")) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(request);
            res.next();
            resInt = res.getInt(1);
            System.out.println("Nombre de prêts : " + resInt);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
        return resInt;

    }
    public static int nombreProprios(){
        int resInt = -1;
        String request = "SELECT COUNT(*) FROM PROPRIETAIRE";
        try (Connection conn = CreationBD.seConnecter("bd_prets")) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(request);
            res.next();
            resInt = res.getInt(1);
            System.out.println("Nombre de propriétaires : " + resInt);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
        return resInt;

    }
    public static int nombreClients(){
        String request = "SELECT COUNT(*) FROM CLIENT";
        try (Connection conn = CreationBD.seConnecter("bd_prets")) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(request);
            if (res.next()) {
                int resInt = res.getInt(1);
                System.out.println("Nombre de clients : " + resInt);
                return resInt;
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return -1;

    }

    public static void main(String[] args) {
        try {
            nosClients();
            nosClientsAdultes();
            nosEmprunteurs();
            nombrePrets();
            nombreProprios();
            nombreClients();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


 /*

5. ageMax qui retourne l’ˆage du client le plus ˆag´e ;
6. clientsPlusAges qui affiche les clients poss´edant comme ˆage la valeur maximale.
            7. rapportCV qui retourne vrai si le nombre de clients et sup´erieur au nombre de propri´etaires, faux
    sinon.
8. augmenteCout qui augmente les couts de tous les articles de 10%*/
}

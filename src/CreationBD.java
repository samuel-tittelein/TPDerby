import java.sql.*;

public class CreationBD {


    public static void fermerConnexion(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode statique pour créer une base de données
    public static void creerBD(String nomBD) {
        Connection conn = seConnecter(nomBD);
        System.out.println("Base de données " + nomBD + " créée.");
        fermerConnexion(conn);
    }

    // Méthode statique pour se connecter à une base de données
    public static Connection seConnecter(String nomBD) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:derby:db/" + nomBD + ";create=true");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Méthode statique pour créer une table dans la BD
    public static void creerTable(String nomBD, String instructionCreation, String nomTable) {
        try (Connection conn = seConnecter(nomBD); Statement stmt = conn.createStatement()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, nomTable.toUpperCase(), null);
            if (!tables.next()) {
                stmt.execute(instructionCreation);
                System.out.println("Table " + nomTable + " créée.");
            } else {
                System.out.println("La table " + nomTable + " existe déjà.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void dropTables(String nomBD) {
        try (Connection conn = seConnecter(nomBD); Statement stmt = conn.createStatement()){
            stmt.executeUpdate("DROP TABLE Pret");
            stmt.executeUpdate("DROP TABLE Article");
            stmt.executeUpdate("DROP TABLE Proprietaire");
            stmt.executeUpdate("DROP TABLE Client");

            System.out.println("Tables supprimées.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        String nomBD;
        if (args.length != 0) {
            nomBD = args[0];
        } else {
            nomBD = "bd_prets";
        }
        creerBD(nomBD);
        dropTables(nomBD);

        String client = "Client";
        String creationTableClient = "CREATE TABLE " + client + " (" +
                                     "id INT PRIMARY KEY, " +
                                     "nom VARCHAR(50), " +
                                     "prenom VARCHAR(50), " +
                                     "age INT)";
        creerTable(nomBD, creationTableClient, client);

        String proprietaire = "Proprietaire";
        String creationTableProprietaire = "CREATE TABLE " + proprietaire + " (" +
                                           "id INT PRIMARY KEY, " +
                                           "nom VARCHAR(50), " +
                                           "prenom VARCHAR(50))";
        creerTable(nomBD, creationTableProprietaire, proprietaire);

        String article = "Article";
        String creationTableArticle = "CREATE TABLE " + article + " (" +
                                      "id INT PRIMARY KEY, " +
                                      "nomA VARCHAR(50), " +
                                      "cout DECIMAL(10, 2)," +
                                      "idProprietaire INT," +
                                      "FOREIGN KEY (idProprietaire) REFERENCES Proprietaire(id)) ";
        creerTable(nomBD, creationTableArticle, article);

        String pret = "Pret";
        String creationTablePret = "CREATE TABLE " + pret + " (" +
                                   "idClient INT, " +
                                   "idArticle INT, " +
                                   "nb_jours INT, " +
                                   "FOREIGN KEY (idClient) REFERENCES Client(id), " +
                                   "FOREIGN KEY (idArticle) REFERENCES Article(id), " +
                                   "PRIMARY KEY (idClient, idArticle))";
        creerTable(nomBD, creationTablePret, pret);

    }
}


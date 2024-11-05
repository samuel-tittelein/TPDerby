import java.sql.*;

public class RemplissageBD {
    public static int remplir(String nomBD, String nomTable, String[] infos){
        try (Connection conn = CreationBD.seConnecter(nomBD); Statement stmt = conn.createStatement()) {
            StringBuffer sb = new StringBuffer("INSERT INTO " + nomTable + " VALUES (");
            for (int i = 0; i < infos.length-1; i++) {
                sb.append(infos[i]).append(",");
            }
            sb.append(infos[infos.length - 1]).append(")");
            return stmt.executeUpdate(sb.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    public static int remplir(String nomBD, String nomTable, String[][] infos) {
        try (Connection conn = CreationBD.seConnecter(nomBD); Statement stmt = conn.createStatement()) {
            for (String[] info : infos) {
                StringBuffer sb = new StringBuffer("INSERT INTO " + nomTable + " VALUES (");
                for (int i = 0; i < info.length-1; i++) {
                    sb.append(info[i]).append(",");
                }
                sb.append(info[info.length - 1]).append(")");
                stmt.addBatch(sb.toString());
            }
            return stmt.executeBatch().length;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


        public static void main(String[] args) {
        String nomBD = "bd_prets";
        String[] nomsTable = {"Client", "Proprietaire", "Article", "Pret"};
        String[][] clients = {
                {"1", "'TITTELEIN'", "'Samuel'", "18"},
                {"2", "'ROUSSEAU'", "'Rayanne'", "19"},
                {"3", "'STALINE'", "'Joseph'", "140" },
                {"4", "'KHALIFA'", "'Mia'", "32"}
        };
        String[][] articles = {
                {"1", "'Article 1'", "100", "1"},
                {"2", "'Article 2'", "200", "2"},
                {"3", "'Article 3'", "300", "3"},
                {"4", "'Article 4'", "400", "2"}
        };
        String[][] proprietaire = {
                {"1", "'SINS'", "'Johnny'"},
                {"2", "'ELFIE'", "'Eva'"},
                {"3", "'FOX'", "'Sweetie'"},
                {"4", "'FORGER'", "'Anya'"}
        };
        String[][] prets = {
                {"1", "2", "60"},
                {"2", "3", "15"},
                {"3", "3", "10"},
                {"3", "1", "90"}
        };

        for (String nomTable : nomsTable) {

            switch (nomTable) {
                case "Client":
                    System.out.println("Remplissage de la table "+nomTable+" : "+remplir(nomBD, nomTable, clients));
                    break;
                case "Proprietaire":
                    System.out.println("Remplissage de la table "+nomTable+" : "+remplir(nomBD, nomTable, proprietaire));
                    break;
                case "Article":
                    System.out.println("Remplissage de la table "+nomTable+" : "+remplir(nomBD, nomTable, articles));
                    break;
                case "Pret":
                    System.out.println("Remplissage de la table "+nomTable+" : "+remplir(nomBD, nomTable, prets));
                    break;
                default:
                    System.out.println("Table non trouvée : "+nomTable);
            }
        }
        CreationBD.fermerConnexion(CreationBD.seConnecter(nomBD));
    }
}

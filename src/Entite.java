import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Entite {
    protected int id;

    protected Entite(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean existe(String nomBD, String nomTable) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = CreationBD.seConnecter(nomBD);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM " + nomTable + " WHERE id = " + id;
            ResultSet res = stmt.executeQuery(sql);

            /* // autre version :
            String sql = "SELECT * FROM " + nomTable;
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                if (res.getString("id").equals(id)) {
                    return true;
                }
            }
            */
            return res.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (stmt!= null) stmt.close();
                if (conn!= null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected abstract boolean ajoutBD(String nomBD, String nomTable, int id);

    public boolean ajoutBD(String nomBD, String nomTable) {
        if (existe(nomBD, nomTable)) {
            System.out.println("L'entité existe déjà.");
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = CreationBD.seConnecter(nomBD);
            stmt = conn.createStatement();
            String sql = "SELECT MAX(id) FROM " + nomTable;
            ResultSet res = stmt.executeQuery(sql);
            int maxId = 0;
            if (res.next()) {
                maxId = res.getInt(1);
            }
            maxId++;
            this.id = maxId;
            return ajoutBD(nomBD, nomTable, maxId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (stmt!= null) stmt.close();
                if (conn!= null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

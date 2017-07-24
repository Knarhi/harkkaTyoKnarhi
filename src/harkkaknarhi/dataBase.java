/*
Lähteet: tutorialspoint.com, w3schools.com, stackoverflow.com, luentovideot, esimerkkirepository
Tekijä: Kuisma Närhi 0453757
 */
package harkkaknarhi;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Qnaerhi
 */
public class dataBase {
    Connection c = null;
    Statement stmt = null;
    
    public void dataBase (){
        }
    
    
    public void close() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.close();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void create () throws IOException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            IOfilehandler filer = new IOfilehandler("DBschema.sql");
            String sql = filer.readText();


            stmt.executeUpdate(sql);
            IOfilehandler filerer = new IOfilehandler("DBbuild.sql");
            String build = filerer.readText();
            stmt.executeUpdate(build);
            stmt.close();
            c.close();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
    }
      

   System.out.println("Operation done successfully");
  }
  
    public void insertUser (String name, String pw) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO kayttajat (name, password) VALUES (?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,pw);
            pstmt.executeUpdate();

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    
    public boolean insertPoints (Automaatti au) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO automaatti (cityID,city,address,name,lat,lng,aukiolo) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1,au.cityID);
            pstmt.setString(2,au.city);
            pstmt.setString(3,au.address);
            pstmt.setString(4,au.name);
            pstmt.setFloat(5,au.lat);
            pstmt.setFloat(6,au.lng);
            pstmt.setString(7,au.openinghrs);
            pstmt.executeUpdate();

            stmt.close();
            c.commit();
            c.close();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        return true;
    }
    
    public boolean deleteAutomaatti(String city, String add, int id, String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "DELETE FROM automaatti WHERE city='"+city+"' AND address='"+add+"' AND cityID='"+id+"' AND name='"+name+"';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            return true;
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return false;
        }
    }
    
    public void outputAll () {
    Connection c = null;
    Statement stmt = null;
    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM kayttajat;" );
      
        while ( rs.next() ) {
           String name = rs.getString("name");
           int id  = rs.getInt("userid");
           System.out.println( "userID = " + id + " | name = " + name );
         
        }
        rs.close();
        stmt.close();
        c.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
    System.out.println("Operation done successfully");
    }
    
    public ArrayList<Automaatti> returnAutomaatti(String term1, String term2) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ArrayList<Automaatti> lista = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM automaatti WHERE " + term1 +  " = '" + term2 + "';");
                while (rs.next()) {
                    int a = rs.getInt("cityID");
                    String b = rs.getString("city");
                    String c = rs.getString("address");
                    String d = rs.getString("name");
                    float e = rs.getFloat("lat");
                    float f = rs.getFloat("lng");
                    String g = rs.getString("aukiolo");
                    Automaatti at = new Automaatti(a,b,c,d,e,f,g);
                    lista.add(at);
                }
        stmt.close();
        c.close();
        System.out.println("Operation done successfully");
        return lista;
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        return null;
    }
    }
    
    public boolean duplicateCheck(String table, String value) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+table+" WHERE name ='"+value+"';");
            int i = 0;
            while (rs.next()) {
                if(!rs.getString("name").equals(value)) {
                    i = 0;
                } else {
                    i = 1;
                    break;
                }
            } 
            stmt.close();
            c.close();
            return i == 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean pwcheck (String name, String pw) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM kayttajat WHERE name ='"+name+"';");
            boolean a = rs.getString("password").equals(pw);
            stmt.close();
            c.close();
            return a;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    public void updateUserActivity() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE kayttajat SET active = '0';");
            c.commit();
            System.out.println("aktiivisuus nollattu");
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setActiveUser(String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE kayttajat SET active = '1' WHERE name='"+name+"';");
            c.commit();
            System.out.println("aktiivinen käyttäjä:"+name);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM kayttajat WHERE name='"+name+"';");
            Boolean s = rs.getBoolean("active");
            System.out.println(s);
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public activeUser getActiveUser() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM kayttajat WHERE active='1';");
            String name = rs.getString("name");
            int i = rs.getInt("userID");
            activeUser au = new activeUser(i, name);
            stmt.close();
            c.close();
            return au;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }
    public ArrayList<opjekt> getOpjekts() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ArrayList<opjekt> lista = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM esine;");
            while(rs.next()) {
                int a = rs.getInt("esineID");
                String b = rs.getString("name");
                int cc = rs.getInt("paino");
                int d = rs.getInt("korkeus");
                int e = rs.getInt("leveys");
                int f = rs.getInt("syvyys");
                int g = rs.getInt("sarkyvyys");
                opjekt o = new opjekt(a,b,cc,d,e,f,g);
                lista.add(o);
            }
            stmt.close();
            c.close();
            System.out.println("Operation done successfully");
            return lista;
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }   
    }
    public opjekt getOpjekt(String label, String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM esine WHERE "+label+"='"+name+"';");
            int a = rs.getInt("esineID");
            String b = rs.getString("name");
            int cc = rs.getInt("paino");
            int d = rs.getInt("korkeus");
            int e = rs.getInt("leveys");
            int f = rs.getInt("syvyys");
            int g = rs.getInt("sarkyvyys");
            opjekt o = new opjekt(a,b,cc,d,e,f,g);
            stmt.close();
            c.close();
            return o;
        }  catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }   
    }
    public ArrayList<luokka> getLuokkas() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ArrayList<luokka> luokkas = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM luokka;)");
            while(rs.next()) {
                int a = rs.getInt("luokkalabel");
                int b = rs.getInt("sarkyvyys");
                int cc = rs.getInt("paino");
                float d = rs.getFloat("etaisyys");
                int e = rs.getInt("nopeus");
                int f = rs.getInt("korkeus");
                int g = rs.getInt("leveys");
                int h = rs.getInt("syvyys");
                luokka l = new luokka(a,b,cc,d,e,f,g,h);
                luokkas.add(l);       
            }
            stmt.close();
            c.close();
            return luokkas;
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return null;
        }   
    
    }
    public luokka getLuokka(int lab) {
        int a,b,d,e,f,g;
        float cc;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM luokka WHERE luokkalabel='"+lab+"';");
            a = rs.getInt("sarkyvyys");
            b = rs.getInt("paino");
            cc = rs.getFloat("etaisyys");
            d = rs.getInt("nopeus");
            e = rs.getInt("korkeus");
            f = rs.getInt("leveys");
            g = rs.getInt("syvyys");
            luokka l = new luokka(lab,a,b,cc,d,e,f,g);
            stmt.close();
            c.close();
            return l;
        } catch ( ClassNotFoundException | SQLException ex ) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
            return null;
        }
    }
    public void createItem(opjekt o) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO esine (name,paino,korkeus,leveys,syvyys,sarkyvyys) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,o.name);
            pstmt.setInt(2,o.wgt);
            pstmt.setInt(3,o.hgt);
            pstmt.setInt(4,o.wdt);
            pstmt.setInt(5,o.dpt);
            pstmt.setInt(6,o.breakability);
            pstmt.executeUpdate();
            stmt.close();
            c.commit();
            c.close();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    
    public void varastoon(int o, int l, int a) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();      
            String sql = "INSERT INTO varasto (esineID, luokkalabel, userID) VALUES (?,?,?)";            
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1,o);
            pstmt.setInt(2,l);
            pstmt.setInt(3,a);
            pstmt.executeUpdate();
            stmt.close();
            c.commit();
            c.close();
            
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    public int varastostaListaan(int userID) {
        //palauttaa viimeisimmän lisäyksen varastoon
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();      
            String sql = "SELECT MAX(varastoID) FROM varasto WHERE userID='"+userID+"';";            
            ResultSet rs = stmt.executeQuery(sql);
            int x = rs.getInt("varastoID");
            System.out.println(x);
            stmt.close();
            c.close();
            return x;
            
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return 0;
        
    }
    public int varastosta(String label, int varastoID) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();      
            String sql = "SELECT * FROM varasto WHERE varastoID='"+varastoID+";";            
            ResultSet rs = stmt.executeQuery(sql);
            int i = rs.getInt(label);
            stmt.close();
            c.close();
            return i;
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records read.");
        return 0;
    }
    public void pakettiin(paketti a) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();      
            String sql = "INSERT INTO paketti (userID, varastoID, lahto, paate) VALUES (?,?,?,?)";            
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1,a.userID);
            pstmt.setInt(2,a.varastoID);
            pstmt.setInt(3,a.lahto.id);
            pstmt.setInt(4,a.paate.id);
            pstmt.executeUpdate();
            stmt.close();
            c.commit();
            c.close();
            a.setTuote(varastosta("varastoID",varastostaListaan(a.userID)));
            
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}



import model.Database;

public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running DB test");
        Database db=new Database();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.disconnect();
    }
}

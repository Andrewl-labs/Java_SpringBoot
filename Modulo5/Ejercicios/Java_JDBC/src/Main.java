import database.ConfigDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConfigDB.openConnection();

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}
package createTable;

import java.sql.*;

public class CreateTable_defaultTable {
    public Statement statement;
    public Connection connection;
    private PreparedStatement preparedStatement;

    public void CreatePizzaDefaultTable() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/Aydar/IdeaProjects/DBandFX/src/database/pizzaDataBase");
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS defaultTable;");
        statement.execute("CREATE TABLE defaultTable\n" +
                "(\n" +
                "  restaurant TEXT PRIMARY KEY,\n" +
                "  quantity INT,\n" +
                "  pizzaName TEXT[],\n" +
                "  districts TEXT[],\n" +
                "  offices JSON\n" +
                ");");
        statement.execute("INSERT INTO defaultTable VALUES\n" +
                "                            ('Папа Джонс',\n" +
                "                             3,\n" +
                "                             '{[Гавайская, 400], [4 Сыра, 370], [Мясная, 420]}',\n" +
                "                             '{Советский, Приволжский}',\n" +
                "                             '{\"ул. Абсалямова, 14\": 16, \"ул. Галактионова, 6\": 12, \"ул. Рихарда Зорге, 102\": 10}'),\n" +
                "\n" +
                "                            ('Додо пицца',\n" +
                "                             2,\n" +
                "                             '{[4 Сыра, 370], [Мясная, 420]}',\n" +
                "                             '{Советский, Приволжский, Авиастроительный}',\n" +
                "                             '{\"просп. Победы, 141\": 23, \"ул. Николая Ершова, 4/2\": 17, \"ул. Декабристов, 85\": 19, \"ул. Адоратского, 33А\": 7, \"просп. Победы, 91\": 21}'),\n" +
                "\n" +
                "                            ('Домино пицца',\n" +
                "                             2,\n" +
                "                             '{[Пепперони, 500], [Мясная, 420]}',\n" +
                "                             '{Советский}',\n" +
                "                             '{\"ул. Пушкина, 29В\": 25, \"ул. Кул Гали, 8А\": 21}'),\n" +
                "\n" +
                "                            ('А Рома',\n" +
                "                             3,\n" +
                "                             '{[Гавайская, 400], [Карбонара, 350], [Маргарита, 410]}',\n" +
                "                             '{Авиастроительный}',\n" +
                "                             '{\"Театральная ул., 3\": 31, \"ул. Кави Наджми, 8\": 27}');");
    }
}

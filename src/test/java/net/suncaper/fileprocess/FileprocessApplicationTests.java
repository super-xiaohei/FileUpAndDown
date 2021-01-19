package net.suncaper.fileprocess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class FileprocessApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }
    @Test
    public void connectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}

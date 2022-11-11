package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@ComponentScan("main")
public class Controller {
        @Autowired
        private JdbcTemplate jt;
        public void myAction() {
            jt.execute("create table elephant (id int, name varchar)");
            jt.execute("insert into elephant (id, name) values (1, 'elephant_1')");
            jt.execute("insert into elephant (id, name) values (2, 'elephant_2')");
    }
}

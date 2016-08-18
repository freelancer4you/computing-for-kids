package de.goldmann.map.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.test.utils.TestUtils;
import de.goldmann.map.UiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class PrepareUserMailTest {
    private PrepareUserMail prepare;

    @Autowired
    private CourseRepository courseRepo;

    @Before
    public void setup() {
        this.prepare = new PrepareUserMail();
    }

    @Test
    @Sql("testListCourses.sql")
    public void testPrepare() throws IOException {
        final User user = new User(TestUtils.buildUserDto());
        final Course course = this.courseRepo.findOne("Lego Programmierung");
        final String result = this.prepare.prepare(user, course);
        System.out.println(result);

        final FileWriter fstream = new FileWriter("result.html");
        final BufferedWriter out = new BufferedWriter(fstream);
        out.write(result);
        out.close();
    }

}

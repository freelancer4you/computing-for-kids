package de.goldmann.map.domain.course.repository;

import static de.goldmann.apps.root.test.utils.TestUtils.buildUserDto;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import de.goldmann.apps.root.dao.CourseParticipantRepository;
import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dao.DefaultAccountRepository;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.map.UiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseParticipantRepository courseParticipantRepository;

    @Autowired
    private DefaultAccountRepository userRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    @Transactional
    public void test() {

        final TransactionTemplate tr = new TransactionTemplate(transactionManager);

        tr.execute(s -> {
            final Course storedCourse = this.courseRepository.save(new Course("Englisch"));
            final DefaultAccount storedUser = this.userRepository
                    .save(new DefaultAccount(buildUserDto()));

            this.courseParticipantRepository.save(new CourseParticipant(storedCourse, storedUser));

            return null;
        });

        final List<CourseParticipant> all = this.courseParticipantRepository.findAll();
        assertEquals("Genau eine Zugehörigkeit sollte vorhanden sein:", 1, all.size());
    }

}

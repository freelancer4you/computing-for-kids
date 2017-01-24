package de.goldmann.apps.root.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.apps.root.config.InfrastructureConfig;
import de.goldmann.apps.root.dao.CourseParticipantRepository;
import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.DefaultAccountDTO;
import de.goldmann.apps.root.services.UserActivityReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { InfrastructureConfig.class, AdditionalConfig.class, TestConfig.class })
public class DefaultAccountControllerTest {

    private DefaultAccountController cut;

    @Autowired
    private UserRepository              userRepository;

    @Autowired
    private UserActivityReport          activityReport;

    @Autowired
    private CourseRepository            courseRepo;

    @Autowired
    private CourseParticipantRepository courseParticipantRepository;

    @Before
    public void setup() {
        cut = new DefaultAccountController(userRepository, activityReport, courseRepo, courseParticipantRepository);
    }

    @Test
    @Sql("testDefaultAccountController.sql")
    public void defaultRegistration() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final DefaultAccountDTO acc = new DefaultAccountDTO("salutation",
                "title",
                "firstName",
                "lastName",
                "email",
                "password",
                "phoneNumber",
                new Adress("street", "zipcode", "city", "12"),
                "registration",
                "childName",
                "childAge");
        final String json = mapper.writeValueAsString(acc);
        cut.defaultRegistration(json, "LegoOct2016");
    }


}

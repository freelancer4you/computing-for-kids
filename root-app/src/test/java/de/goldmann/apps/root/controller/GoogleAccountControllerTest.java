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
import de.goldmann.apps.root.dao.GoogleAccountRepository;
import de.goldmann.apps.root.dto.GoogleAccountDTO;
import de.goldmann.apps.root.services.UserActivityReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { InfrastructureConfig.class, AdditionalConfig.class, TestConfig.class })
public class GoogleAccountControllerTest {

    private GoogleAccountController cut;

    @Autowired
    private CourseParticipantRepository courseParticipantRepository;

    @Autowired
    private GoogleAccountRepository     accountRepository;

    @Autowired
    private UserActivityReport          activityReport;

    @Autowired
    private CourseRepository            courseRepo;

    @Before
    public void setup() {
        cut = new GoogleAccountController(courseParticipantRepository, accountRepository, activityReport, courseRepo);
    }

    @Test
    @Sql("testGogleAccountController.sql")
    public void googleRegistration() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final GoogleAccountDTO acc = new GoogleAccountDTO("familyName",
                "gender",
                "givenName",
                "language",
                "displayName",
                "email",
                "imageUrl");
        final String json = mapper.writeValueAsString(acc);
        cut.googleRegistration(json, "LegoOct2016");
    }

}

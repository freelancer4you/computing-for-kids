package de.goldmann.apps.root.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = { "de.goldmann.apps.root.services" })
public class TestConfig {

}

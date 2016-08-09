package de.goldmann.apps.tests.helpers;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class HelperUtils
{
    private static final Logger LOGGER = LogManager.getLogger(HelperUtils.class);

    public static void analyzeLog(WebDriver driver)
    {
        final LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (final LogEntry entry : logEntries)
        {
            LOGGER.error(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    public static void setInputValue(WebElement modalSingupDialog, String inputId, String value)
    {
        WebElement lastNameInput = modalSingupDialog.findElement(By.id(inputId));
        lastNameInput.sendKeys(value);
    }
}

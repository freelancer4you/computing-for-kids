package de.goldmann.apps.tests.helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitDefinition
{
    private WebDriver driver;

    public WaitDefinition(WebDriver driver)
    {
        this.driver = driver;
    }

    public FluentWait<WebDriver> getDefinition()
    {
        return new FluentWait<WebDriver>(driver)

        .withTimeout(30, TimeUnit.SECONDS)

        .pollingEvery(5, TimeUnit.SECONDS);
    }

}

package de.goldmann.apps.tests.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Function;

public class VisibilityFunction implements Function<WebDriver, WebElement>
{
    private String id;

    public VisibilityFunction(String id)
    {
        this.id = id;
    }

    public WebElement apply(WebDriver driver)
    {
        return driver.findElement(By.id(id));
    }

}

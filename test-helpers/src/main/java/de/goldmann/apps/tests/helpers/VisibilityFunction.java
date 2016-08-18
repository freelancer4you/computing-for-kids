package de.goldmann.apps.tests.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Function;

public class VisibilityFunction implements Function<WebDriver, WebElement>
{

    private final By searchedElement;

    public VisibilityFunction(final By searchedElement)
    {
        this.searchedElement = searchedElement;
    }

    @Override
    public WebElement apply(final WebDriver driver)
    {
        return driver.findElement(searchedElement);
    }

}

package LanguageTests;

import org.example.Config;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
public class LanguageTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    @Order(1)
    public void testDefault(){
        String message = applicationContext.getMessage("editDaysOpen", null, Locale.getDefault());
        Assertions.assertEquals("Edit Days Open", message);
    }

    @Test
    @Order(2)
    public void testGerman(){
        String message = applicationContext.getMessage("editDaysOpen", null, Locale.GERMAN);
        Assertions.assertEquals("Offene Tage bearbeiten", message);
    }

    @Test
    @Order(3)
    public void testGermanToDefaultIfEmpty(){
        String message = applicationContext.getMessage("stylist", null, Locale.GERMAN);
        Assertions.assertEquals("*****Stylist*****", message);
    }
}

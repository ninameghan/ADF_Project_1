package ServiceTests;

import org.example.Config;
import org.example.doa.dto.SalonOverview;
import org.example.entities.Stylist;
import org.example.service.IStylistService;
import org.example.service.exceptions.SalonNotFoundException;
import org.example.service.exceptions.StylistIdAlreadyExistsException;
import org.example.service.exceptions.StylistMalformedException;
import org.example.service.exceptions.StylistNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
public class StylistServiceTests {

    @Autowired
    IStylistService stylistService;

    @Test
    @Order(1)
    public void testStylistCount() {
        Assertions.assertEquals(3, stylistService.count());
    }

    @Test
    @Order(2)
    public void testStylistFindById() {
        Stylist stylist = stylistService.findById(3);
        Assertions.assertNotNull(stylist);
        Assertions.assertEquals("Stylist 3", stylist.getName());
    }

    @Test
    @Order(3)
    public void testStylistFindByIdNotFound() {
        Assertions.assertThrows(StylistNotFoundException.class, () -> stylistService.findById(11111));
    }

    @Test
    @Order(4)
    public void testStylistFindAllBySalon() {
        Assertions.assertEquals(2, stylistService.findAllBySalon(1).size());
    }

    @Test
    @Order(5)
    public void testFindAverageSalaryForSalon() {
        Assertions.assertEquals(25000, stylistService.findAverageSalaryForSalon(1));
    }

    @Test
    @Order(6)
    public void testAddStylist() {
        int oldCount = stylistService.count();
        Stylist stylist = new Stylist(11, "Stylist 11", "0123456789", 20000, 3);
        stylistService.add(stylist);
        Assertions.assertEquals(oldCount + 1, stylistService.count());
        Assertions.assertNotNull(stylistService.findById(11));
        Assertions.assertEquals("Stylist 11", stylistService.findById(11).getName());
    }

    @Test
    @Order(7)
    public void testAddStylistSalonNotFound() {
        Stylist stylist = new Stylist(11, "Stylist 11", "0123456789", 20000, 11111);
        Assertions.assertThrows(SalonNotFoundException.class, () -> stylistService.add(stylist));
    }

    @Test
    @Order(8)
    public void testStylistAddClashPk() {
        Stylist stylist = new Stylist(2, "Stylist 11", "0123456789", 20000, 3);
        Assertions.assertThrows(StylistIdAlreadyExistsException.class, () -> stylistService.add(stylist));
    }

    @Test
    @Order(9)
    public void testChangeStylistSalon() {
        int oldSalonId = stylistService.findById(1).getSalonId();
        stylistService.changeSalon(2, 1);
        Assertions.assertEquals(2, stylistService.findById(1).getSalonId());
        Assertions.assertNotEquals(oldSalonId, stylistService.findById(1).getSalonId());
    }

    @Test
    @Order(10)
    public void testChangeStylistSalonInvalidSalonId() {
        Assertions.assertThrows(StylistMalformedException.class, () -> stylistService.changeSalon(0, 1));
    }

    @Test
    @Order(11)
    public void testChangeStylistSalonStylistNotFound() {
        Assertions.assertThrows(StylistNotFoundException.class, () -> stylistService.changeSalon(1, 11111));
    }

    @Test
    @Order(12)
    public void testStylistDeleteById() {
        Assertions.assertNotNull(stylistService.findById(1));
        stylistService.deleteById(1);
        Assertions.assertThrows(StylistNotFoundException.class, () -> stylistService.findById(1));
    }

    @Test
    @Order(13)
    public void testStylistDeleteByIdNotFound() {
        Assertions.assertThrows(StylistNotFoundException.class, () -> stylistService.deleteById(11111));
    }

    @Test
    @Order(14)
    public void testSalonOverview(){
        List<SalonOverview> salonOverviews = stylistService.getSalonOverview();
        Assertions.assertEquals(3, salonOverviews.size());
    }
}

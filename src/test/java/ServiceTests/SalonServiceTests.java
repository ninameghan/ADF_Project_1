package ServiceTests;

import org.example.Config;
import org.example.entities.Salon;
import org.example.service.ISalonService;
import org.example.service.exceptions.SalonNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
public class SalonServiceTests {
    @Autowired
    ISalonService salonService;
    @Test
    @Order(1)
    public void testSalonCount(){
        Assertions.assertEquals(3, salonService.count());
    }
    @Test
    @Order(2)
    public void testSalonFindAll(){
        Assertions.assertEquals(3, salonService.findAll().size());
    }

    @Test
    @Order(3)
    public void testSalonFindById(){
        Salon salon = salonService.findById(3);
        Assertions.assertNotNull(salon);
        Assertions.assertEquals("Salon 3", salon.getName());
    }

    @Test
    @Order(4)
    public void testSalonFindByIdNotFound(){
        Assertions.assertThrows(SalonNotFoundException.class, () -> {salonService.findById(11111);});
    }

    @Test
    @Order(5)
    public void testSalonDeleteById(){
        Assertions.assertNotNull(salonService.findById(1));
        salonService.deleteById(1);
        Assertions.assertThrows(SalonNotFoundException.class, () -> {salonService.findById(1);});
    }

    @Test
    @Order(6)
    public void testSalonDeleteByIdNotFound(){
        Assertions.assertThrows(SalonNotFoundException.class, () -> {salonService.deleteById(11111);});
    }
}

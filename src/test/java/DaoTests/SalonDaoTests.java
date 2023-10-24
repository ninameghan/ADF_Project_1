package DaoTests;

import org.example.Config;
import org.example.doa.ISalonDao;
import org.example.entities.Salon;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
public class SalonDaoTests {
    @Autowired
    ISalonDao salonDao;

    @Test
    @Order(1)
    public void testSalonCount(){
        Assertions.assertEquals(3, salonDao.count());
    }
    @Test
    @Order(2)
    public void testSalonFindAll(){
        Assertions.assertEquals(3, salonDao.findAll().size());
    }

    @Test
    @Order(3)
    public void testSalonFindById(){
        Optional<Salon> salon = salonDao.findById(3);
        Assertions.assertTrue(salon.isPresent());
        Assertions.assertEquals("Salon 3", salon.get().getName());
    }

    @Test
    @Order(4)
    public void testSalonFindByIdNotFound(){
        Optional<Salon> salon = salonDao.findById(11111);
        Assertions.assertFalse(salon.isPresent());
    }

    @Test
    @Order(5)
    public void testSalonDeleteById(){
        Assertions.assertTrue(salonDao.deleteById(1));
    }

    @Test
    @Order(6)
    public void testSalonDeleteByIdNotFound(){
        Assertions.assertFalse(salonDao.deleteById(11111));
    }

    @Test
    @Order(7)
    public void testSalonSave(){
        int oldCount = salonDao.count();
        Salon salon = new Salon(11, "Salon 11", "Address 11", "0123456789", "1000000");
        salonDao.save(salon);
        Assertions.assertEquals(oldCount + 1, salonDao.count());
        Assertions.assertTrue(salonDao.findById(11).isPresent());
        Assertions.assertEquals("Salon 11", salonDao.findById(11).get().getName());
    }

    @Test
    @Order(8)
    public void testSalonSaveClashPk(){
        Salon salon = new Salon(2, "Salon 11", "Address 11", "0123456789", "1000000");
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            salonDao.save(salon);
        });
    }
}

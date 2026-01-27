package com.travel.backpacker.repository;

import com.travel.backpacker.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

@DataJpaTest
public class AdminDaoTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AdminDao adminDao;

    @Test
    void testFindByUsername()
    {
        //Arrange String username, String email, char[] password, boolean active
        Admin admin = new Admin("ethan_brown", "ethan.brown@example.com", "test@123".toCharArray(), true );
        entityManager.persist(admin);
        entityManager.flush();

        //Act
        Optional<Admin> adminOptional = adminDao.findByUsername("ethan_brown");

        //Assert
        assertTrue(adminOptional.isPresent());
        assertEquals("ethan.brown@example.com", adminOptional.get().getEmail());
    }

    @Test
    void save()
    {
        //create the admin instance
        Admin admin = new Admin("ethan_brown", "ethan.brown@example.com", "test@123".toCharArray(), true );

        //persist the user and flush the persistence to H2 database
        Admin savedAdmin = entityManager.persistAndFlush(admin);

        Optional<Admin> adminOptional = adminDao.findById(savedAdmin.getId());
        assertTrue(adminOptional.isPresent(), "User should be found");
        Admin foundAdmin = adminOptional.get();
        assertEquals("ethan.brown@example.com", foundAdmin.getEmail());
        assertEquals("ethan_brown", foundAdmin.getUsername());

    }
}

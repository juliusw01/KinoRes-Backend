package de.wi2020sebgruppe4.KinoTicketRes.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import de.wi2020sebgruppe4.KinoTicketRes.model.Movie;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class MovieTest {
    
    @Test
    @DisplayName("constructor test")
    public void constructorTest() {
        Date d = new Date(2015-03-31);
        Movie m = new Movie("Star Wars", "German", 120, "George Lucas", "Weltraum ist cool", d, "https://picute.link", "https://trailer-link", false, 12);
        assertEquals("Star Wars", m.getTitel());
        assertEquals("German", m.getLanguage());
        assertEquals(120, m.getDuration());
        assertEquals("George Lucas", m.getDirector());
        assertEquals("Weltraum ist cool", m.getDescription());
        assertEquals(d, m.getRelease());
        assertEquals("https://picute.link", m.getPictureLink());
        assertEquals("https://trailer-link", m.getTrailerLink());
        assertEquals(false, m.isAvailableIn3D());
        assertEquals(12, m.getFSK());
    }

    @Test
    @DisplayName("getter/setter Test")
    public void getterSetterTest() {
        Movie m = new Movie();
        Date d = new Date(2015-03-31);
        m.setTitel("Star Wars");
        m.setLanguage("German");
        m.setDuration(120);
        m.setDirector("George Lucas");
        m.setDescription("Weltraum ist cool");
        m.setRelease(d);
        m.setPictureLink("https://picute.link");
        m.setTrailerLink("https://trailer-link");
        m.setAvailableIn3D(true);
        m.setFSK(12);

        assertEquals("Star Wars", m.getTitel());
        assertEquals("German", m.getLanguage());
        assertEquals(120, m.getDuration());
        assertEquals("George Lucas", m.getDirector());
        assertEquals("Weltraum ist cool", m.getDescription());
        assertEquals(d, m.getRelease());
        assertEquals("https://picute.link", m.getPictureLink());
        assertEquals("https://trailer-link", m.getTrailerLink());
        assertEquals(true, m.isAvailableIn3D());
        assertEquals(12, m.getFSK());
    }

    @Test
    @DisplayName("Test hashCode")
    public void hashCodeTest() {
        Date d = new Date(2015-03-31);
        Movie m = new Movie("Star Wars", "German", 120, "George Lucas", "Weltraum ist cool", d, "https://picute.link", "https://trailer-link", false, 12);
        Movie m2 = new Movie("Star Wars", "German", 120, "George Lucas", "Weltraum ist cool", d, "https://picute.link", "https://trailer-link", false, 12);
        assertEquals(m.hashCode(), m2.hashCode());
        assertEquals(m.equals(m2), true);

        // Test equals method
    }
}


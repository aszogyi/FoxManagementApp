package hu.szabolcs.fox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoxResourceTest {

    @Mock
    private FoxService foxService;

    @InjectMocks
    private FoxResource foxResource;

    @Test
    void findAllShouldReturnFoxes() {
        Fox fox = new Fox();
        fox.setName("Vuk");
        fox.setSpecies("red fox");
        fox.setGender(Gender.MALE);

        when(foxService.findAll()).thenReturn(List.of(fox));

        List<Fox> result = foxResource.findAll();

        assertEquals(1, result.size());
        assertEquals("Vuk", result.get(0).getName());
    }

    @Test
    void findByIdShouldReturnFox() {
        Fox fox = new Fox();
        fox.setName("Vuk");

        when(foxService.findById(1L)).thenReturn(fox);

        Fox result = foxResource.findById(1L);

        assertEquals("Vuk", result.getName());
    }

    @Test
    void findByIdShouldThrowNotFoundWhenFoxDoesNotExist() {
        when(foxService.findById(999L))
                .thenReturn(null);

        try {
            foxResource.findById(999L);

            fail("NotFoundException expected");

        } catch (NotFoundException e) {

            assertEquals(
                    "Fox not found with id: 999",
                    e.getMessage()
            );
        }
    }

    @Test
    void createShouldReturnCreatedStatus() {
        Fox fox = new Fox();
        fox.setName("Vuk");
        fox.setSpecies("red fox");
        fox.setGender(Gender.MALE);

        Response response = foxResource.create(fox);

        assertEquals(201, response.getStatus());
        verify(foxService).save(fox);
    }

    @Test
    void deleteAllShouldReturnNoContent() {
        Response response = foxResource.deleteAll();

        assertEquals(204, response.getStatus());
        verify(foxService).deleteAll();
    }
}
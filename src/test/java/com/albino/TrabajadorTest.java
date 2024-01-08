package com.albino;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.albino.model.Trabajador;
import com.albino.model.Trabajo;

public class TrabajadorTest {

@Test
    public void testAsociacionConTrabajo() {
        Trabajador trabajador = new Trabajador();
        Trabajo trabajo = new Trabajo(); 
        trabajo.setId(1L); // 

        trabajador.setTrabajo(trabajo);

        assertEquals(1L, trabajador.getTrabajo().getId());
    }

}

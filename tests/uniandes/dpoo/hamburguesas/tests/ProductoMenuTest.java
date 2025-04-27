package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoMenuTest {
	
	ProductoMenu productoMenu;
	
	@BeforeEach
	void setUp() throws Exception {
		productoMenu = new ProductoMenu("hamburguesa", 2000);
	}
	
	@AfterEach
    void tearDown( ) throws Exception {
    }
	
	@Test
    void testGetNombre( ) {
        assertEquals( "hamburguesa", productoMenu.getNombre( ), "El nombre del ingrediente no es el esperado." );
    }

    @Test
    void testGetPrecio( ) {
        assertEquals( 2000, productoMenu.getPrecio( ), "El costo adicional del ingrediente no es el esperado." );
    }
    
    @Test
    void testGenerarTextoFactura( ) {
    	assertEquals( "hamburguesa\n"
    				+ "            2000\n", productoMenu.generarTextoFactura(), "El texto generado para la factura no fue el esperado.");
    }
}

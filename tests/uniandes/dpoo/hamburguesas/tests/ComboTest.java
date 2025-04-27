package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ComboTest {
	
	Combo combo1;
	Combo combo2;
	Combo combo3;
	Combo combo100;
	Combo combo0;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		ProductoMenu productoMenu1 = new ProductoMenu("hamburguesa", 20000);
		ProductoMenu productoMenu2 = new ProductoMenu("papas", 7000);
		ProductoMenu productoMenu3 = new ProductoMenu("gaseosa", 3000);
		
		ArrayList<ProductoMenu> items1 = new ArrayList<ProductoMenu>();
		items1.addLast(productoMenu1);
		items1.addLast(productoMenu2);
		items1.addLast(productoMenu3);
		
		combo1 = new Combo("mediano", 0.1, items1);
		
		ArrayList<ProductoMenu> items2 = new ArrayList<ProductoMenu>();
		items2.addLast(productoMenu1);
		items2.addLast(productoMenu1);
		items2.addLast(productoMenu2);
		items2.addLast(productoMenu3);
		items2.addLast(productoMenu3);
		
		combo2 = new Combo("parejas", 0.1, items2);
		combo3 = new Combo("nerf", 0.075, items1);
		combo100 = new Combo("gratis", 1.0, items1);
		combo0 = new Combo("ladron", 0.0, items1);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre( )
    {
        assertEquals( "mediano", combo1.getNombre( ), "El nombre del combo no es el esperado." );
        assertEquals( "parejas", combo2.getNombre( ), "El nombre del combo no es el esperado." );
        assertEquals( "nerf", combo3.getNombre( ), "El nombre del combo no es el esperado." );
        assertEquals( "gratis", combo100.getNombre( ), "El nombre del combo no es el esperado." );
        assertEquals( "ladron", combo0.getNombre( ), "El nombre del combo no es el esperado." );
    }
    
    @Test
    void testGetPrecio() {
    	assertEquals(27000, combo1.getPrecio(), "El precio del combo no es el esperado");
    	assertEquals(47700, combo2.getPrecio(), "El precio del combo no es el esperado para el combo más grande");
    	assertEquals(27750, combo3.getPrecio(), "El precio del combo no es el esperado con un cambio en el decuento");
    	assertEquals(0, combo100.getPrecio(), "El precio del combo no es el esperado con un 100% de descuento");
    	assertEquals(30000, combo0.getPrecio(), "El precio del combo no es el esperado sin descuento");
    }
    
    @Test
    void testGenerarTextoFactura() {
    	assertEquals("Combo mediano\n"
    			+ " Descuento: 0.1\n"
    			+ "            27000\n", combo1.generarTextoFactura(), "No se ha generado el texto esperado");
    	assertEquals("Combo parejas\n"
    			+ " Descuento: 0.1\n"
    			+ "            47700\n", combo2.generarTextoFactura(), "No se ha generado el texto esperado");
    	assertEquals("Combo nerf\n"
    			+ " Descuento: 0.075\n"
    			+ "            27750\n", combo3.generarTextoFactura(), "No se ha generado el texto esperado");
    	assertEquals("Combo gratis\n"
    			+ " Descuento: 1.0\n"
    			+ "            0\n", combo100.generarTextoFactura(), "No se ha generado el texto esperado");
    	assertEquals("Combo ladron\n"
    			+ " Descuento: 0.0\n"
    			+ "            30000\n", combo0.generarTextoFactura(), "No se ha generado el texto esperado");
    
    }
    
}

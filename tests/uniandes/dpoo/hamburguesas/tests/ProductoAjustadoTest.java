package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.util.ArrayList;

class ProductoAjustadoTest {
	
	ProductoAjustado productoAjustado;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		ProductoMenu productoMenu = new ProductoMenu("hamburguesa", 20000);
		productoAjustado = new ProductoAjustado( productoMenu );
		//Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
		//Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre() {
    	assertEquals("hamburguesa", productoAjustado.getNombre(), "El nombre desplegado no fue el esperado.");
    }
    
    @Test
    void testGetAgreegados() {
    	Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
    	Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    	
    	ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
    	
    	assertEquals(agregados, productoAjustado.getAgregados(), "El producto se ha generado con ingredientes agregados por defecto.");
    	
    	productoAjustado.getAgregados().addLast(ingrediente1);
    	agregados.addLast(ingrediente1);
    	productoAjustado.getAgregados().addLast(ingrediente2);
    	agregados.addLast(ingrediente2);
    	
    	assertEquals(agregados, productoAjustado.getAgregados(), "La lista de ingredientes arreglados no presenta los elementos esperados");
    	
    	assertEquals(ingrediente1, productoAjustado.getAgregados().getFirst(), "El primer producto no es el esperado.");
    	assertEquals(ingrediente2, productoAjustado.getAgregados().getLast(), "El último producto no es el esperado.");
    }
    
    @Test
    void testGetEliminados() {
    	Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
    	Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    	
    	ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();
    	
    	assertEquals(eliminados, productoAjustado.getAgregados(), "El producto se ha generado con ingredientes agregados por defecto.");
    	
    	productoAjustado.getEliminados().addLast(ingrediente1);
    	eliminados.addLast(ingrediente1);
    	productoAjustado.getEliminados().addLast(ingrediente2);
    	eliminados.addLast(ingrediente2);
    	
    	assertEquals(eliminados, productoAjustado.getEliminados(), "La lista de ingredientes arreglados no presenta los elementos esperados");
    	
    	assertEquals(ingrediente1, productoAjustado.getEliminados().getFirst(), "El primer producto no es el esperado.");
    	assertEquals(ingrediente2, productoAjustado.getEliminados().getLast(), "El último producto no es el esperado.");
    }
    
    @Test
    void testAgregarIngrediente() {
    	Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
    	Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    	
    	assertEquals(0, productoAjustado.getAgregados().size(), "La lista se ha inicializado con elementos.");
    	
    	productoAjustado.agregarIngrediente(ingrediente1);
    	
    	assertEquals(1, productoAjustado.getAgregados().size(), "No se ha insertado el ingrediente correctamente.");
    	assertEquals(ingrediente1, productoAjustado.getAgregados().get(0), "No se ha insertado el ingrediente correcto.");
    	
    	productoAjustado.agregarIngrediente(ingrediente2);
    	
    	assertEquals(2, productoAjustado.getAgregados().size(), "No se ha insertado el ingrediente correctamente.");
    	assertEquals(ingrediente1, productoAjustado.getAgregados().get(0), "No se ha insertado el ingrediente en el orden correcto o fue el ingrediente equivocado.");
    	assertEquals(ingrediente2, productoAjustado.getAgregados().get(1), "No se ha insertado el ingrediente correcto.");
    }
    
    @Test
    void testEliminarIngrediente() {
    	Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
    	Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    	
    	assertEquals(0, productoAjustado.getEliminados().size(), "La lista se ha inicializado con elementos.");
    	
    	productoAjustado.eliminarIngrediente(ingrediente1);
    	
    	assertEquals(1, productoAjustado.getEliminados().size(), "No se ha insertado el ingrediente correctamente.");
    	assertEquals(ingrediente1, productoAjustado.getEliminados().get(0), "No se ha insertado el ingrediente correcto.");
    	
    	productoAjustado.eliminarIngrediente(ingrediente2);
    	
    	assertEquals(2, productoAjustado.getEliminados().size(), "No se ha insertado el ingrediente correctamente.");
    	assertEquals(ingrediente1, productoAjustado.getEliminados().get(0), "No se ha insertado el ingrediente en el orden correcto o fue el ingrediente equivocado.");
    	assertEquals(ingrediente2, productoAjustado.getEliminados().get(1), "No se ha insertado el ingrediente correcto.");
    }
    
    @Test
    void testGetPrecio() {
    	Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
    	Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    	
    	assertEquals(20000, productoAjustado.getPrecio(), "El precio cuando no hay ingredientes agregados ha cambiado.");
    	
    	productoAjustado.agregarIngrediente(ingrediente1);
    	
    	assertEquals(21000, productoAjustado.getPrecio(), "El costo extra de los ingredientes no se ha sumado correctamente.");
    	
    	productoAjustado.agregarIngrediente(ingrediente2);
    	
    	assertEquals(22000, productoAjustado.getPrecio(), "El costo extra de los ingredientes no se ha sumado correctamente.");
    }
    
    @Test
    void testGenerarTextoFactura() {
    	Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
    	Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
    	Ingrediente ingrediente3 = new Ingrediente( "tocineta", 5000 );
    	assertEquals("hamburguesa\n"
    			+ "            20000\n", productoAjustado.generarTextoFactura(), "No se genera el texto correctamente para un producto ajustado sin cambios.");
    	
    	productoAjustado.agregarIngrediente(ingrediente1);
    	
    	assertEquals("hamburguesa\n"
    			+ "    +tomate\n"
    			+ "                1000\n"
    			+ "            21000\n", productoAjustado.generarTextoFactura(), "No se genera correctamente el texto con un elemento agregado");
    	
    	productoAjustado.eliminarIngrediente(ingrediente2);
    	
    	assertEquals("hamburguesa\n"
    			+ "    +tomate\n"
    			+ "                1000\n"
    			+ "    -mostaza\n"
    			+ "            21000\n", productoAjustado.generarTextoFactura(), "No se genera correctamente el texto con un elemento eliminado");
    	
    	productoAjustado.agregarIngrediente(ingrediente3);
    	
    	assertEquals("hamburguesa\n"
    			+ "    +tomate\n"
    			+ "                1000\n"
    			+ "    +tocineta\n"
    			+ "                5000\n"
    			+ "    -mostaza\n"
    			+ "            26000\n", productoAjustado.generarTextoFactura(), "No se genera correctamente el texto con varios ingredientes agregados");
    }
}

package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.*;

class RestauranteTest {
	
	Restaurante restaurante;
	Pedido pedido;
	ProductoMenu productoMenu1;
	ProductoMenu productoMenu2;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		restaurante = new Restaurante();
		pedido = new Pedido("Ignacio", "Boyaca");
		
		productoMenu1 = new ProductoMenu("hamburguesa", 20000);
		productoMenu2 = new ProductoMenu("papas", 5000);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
    	restaurante.iniciarPedido("Ignacio", "Boyaca");
    	assertEquals(pedido.getNombreCliente(), restaurante.getPedidoEnCurso().getNombreCliente(), "No se ha creado el pedido correctamente");
    	assertEquals(pedido.getDireccionCliente(), restaurante.getPedidoEnCurso().getDireccionCliente(), "No se ha creado el pedido correctamente");
    }
    
    @Test
    void testIniciarPedidoError() throws YaHayUnPedidoEnCursoException {
    	restaurante.setPedidoEnCurso(pedido);
    	YaHayUnPedidoEnCursoException excepcionPedido = assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante.iniciarPedido("Ignacio", "Boyaca");});
    	
    	assertEquals("Ya existe un pedido en curso, para el cliente Ignacio así que no se puede crear un pedido para Ignacio", excepcionPedido.getMessage(), "No se ha generado el error correcto.");
    }
    
    @Test
    void testCerrarYGuardarPedido() throws NoHayPedidoEnCursoException, IOException {
    	pedido.agregarProducto(productoMenu1);
    	restaurante.setPedidoEnCurso(pedido);
    	restaurante.cerrarYGuardarPedido();
    	assertNull(restaurante.getPedidoEnCurso(), "No se ha concluido el pedido correctamente");
    	Path ruta = Paths.get("facturas", "factura_"+ pedido.getIdPedido() + ".txt");
    	assertTrue(Files.exists(ruta), "El archivo de la factura no se ha creado");
    	
    	String facturaTexto = Files.readString(ruta);
    	assertEquals(pedido.generarTextoFactura(), facturaTexto, "El texto de la factura no es el esperado");
    	
    	Files.deleteIfExists(ruta);
    }
    
    @Test
    void testCerrarYGuardarPedidoError() throws NoHayPedidoEnCursoException, IOException {
    	restaurante.setPedidoEnCurso(null);
    	NoHayPedidoEnCursoException excepcionPedido = assertThrows(NoHayPedidoEnCursoException.class, () -> {restaurante.cerrarYGuardarPedido();});
    	
    	assertEquals("Actualmente no hay un pedido en curso", excepcionPedido.getMessage(), "No se ha generado el error correcto.");
    }
    
    @Test 
    void testCargarInformacionRestaurante() throws IOException, NumberFormatException, HamburguesaException {
    	ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
    	ingredientes.addLast(new Ingrediente("lechuga", 1000));
    	ingredientes.addLast(new Ingrediente("tomate", 1000));
    	ingredientes.addLast(new Ingrediente("cebolla", 1000));
    	ingredientes.addLast(new Ingrediente("queso mozzarella", 2500));
    	ingredientes.addLast(new Ingrediente("huevo", 2500));
    	ingredientes.addLast(new Ingrediente("queso americano", 2500));
    	ingredientes.addLast(new Ingrediente("tocineta express", 2500));
    	ingredientes.addLast(new Ingrediente("papa callejera", 2000));
    	ingredientes.addLast(new Ingrediente("pepinillos", 2500));
    	ingredientes.addLast(new Ingrediente("cebolla grille", 2500));
    	ingredientes.addLast(new Ingrediente("suero costeño", 3000));
    	ingredientes.addLast(new Ingrediente("frijol refrito", 4500));
    	ingredientes.addLast(new Ingrediente("queso fundido", 4500));
    	ingredientes.addLast(new Ingrediente("tocineta picada", 6000));
    	ingredientes.addLast(new Ingrediente("piña", 2500));
    	
    	ArrayList<ProductoMenu> menu = new ArrayList<ProductoMenu>();
    	menu.addLast(new ProductoMenu("corral", 14000));
    	menu.addLast(new ProductoMenu("corral queso", 16000));
    	menu.addLast(new ProductoMenu("corral pollo", 15000));
    	menu.addLast(new ProductoMenu("corralita", 13000));
    	menu.addLast(new ProductoMenu("todoterreno", 25000));
    	menu.addLast(new ProductoMenu("1/2 libra", 25000));
    	menu.addLast(new ProductoMenu("especial", 24000));
    	menu.addLast(new ProductoMenu("casera", 23000));
    	menu.addLast(new ProductoMenu("mexicana", 22000));
    	menu.addLast(new ProductoMenu("criolla", 22000));
    	menu.addLast(new ProductoMenu("costeña", 20000));
    	menu.addLast(new ProductoMenu("hawaiana", 20000));
    	menu.addLast(new ProductoMenu("wrap de pollo", 15000));
    	menu.addLast(new ProductoMenu("wrap de lomo", 22000));
    	menu.addLast(new ProductoMenu("ensalada mexicana", 20900));
    	menu.addLast(new ProductoMenu("papas medianas", 5500));
    	menu.addLast(new ProductoMenu("papas grandes", 6900));
    	menu.addLast(new ProductoMenu("papas en casco medianas", 5500));
    	menu.addLast(new ProductoMenu("papas en casco grandes", 6900));
    	menu.addLast(new ProductoMenu("agua cristal sin gas", 5000));
    	menu.addLast(new ProductoMenu("agua cristal con gas", 5000));
    	menu.addLast(new ProductoMenu("gaseosa", 5000));
    	
    	ArrayList<Combo> combos = new ArrayList<Combo>();
    	
    	ArrayList<ProductoMenu> productosCombo1 = new ArrayList<ProductoMenu>();
    	productosCombo1.addLast(new ProductoMenu("corral", 14000));
    	productosCombo1.addLast(new ProductoMenu("papas medianas", 5500));
    	productosCombo1.addLast(new ProductoMenu("gaseosa", 5000));
    	combos.addLast(new Combo("combo corral", 0.1, productosCombo1));
    	
    	ArrayList<ProductoMenu> productosCombo2 = new ArrayList<ProductoMenu>();
    	productosCombo2.addLast(new ProductoMenu("corral queso", 16000));
    	productosCombo2.addLast(new ProductoMenu("papas medianas", 5500));
    	productosCombo2.addLast(new ProductoMenu("gaseosa", 5000));
    	combos.addLast(new Combo("combo corral queso", 0.1, productosCombo2));
    	
    	ArrayList<ProductoMenu> productosCombo3 = new ArrayList<ProductoMenu>();
    	productosCombo3.addLast(new ProductoMenu("todoterreno", 25000));
    	productosCombo3.addLast(new ProductoMenu("papas grandes", 6900));
    	productosCombo3.addLast(new ProductoMenu("gaseosa", 5000));
    	combos.addLast(new Combo("combo todoterreno", 0.07, productosCombo3));
    	
    	ArrayList<ProductoMenu> productosCombo4 = new ArrayList<ProductoMenu>();
    	productosCombo4.addLast(new ProductoMenu("especial", 24000));
    	productosCombo4.addLast(new ProductoMenu("papas medianas", 5500));
    	productosCombo4.addLast(new ProductoMenu("gaseosa", 5000));
    	combos.addLast(new Combo("combo especial", 0.095, productosCombo4));
    	
    	File archivo1 = Paths.get("data", "ingredientes.txt").toFile();
    	File archivo2 = Paths.get("data", "menu.txt").toFile();
    	File archivo3 = Paths.get("data", "combos.txt").toFile();
    	
    	restaurante.cargarInformacionRestaurante(archivo1, archivo2, archivo3);
    	
    	assertEquals(ingredientes.size(), restaurante.getIngredientes().size(), "No se han generado la cantidad de ingredientes correctos");
    	for(int i = 0; i < ingredientes.size(); i++) {
    		assertEquals(ingredientes.get(i).getNombre(), restaurante.getIngredientes().get(i).getNombre(), "No se han generado los ingredientes correctos");
    		assertEquals(ingredientes.get(i).getCostoAdicional(), restaurante.getIngredientes().get(i).getCostoAdicional(), "No se han generado los ingredientes correctos");
    	}
    	assertEquals(menu.size(), restaurante.getMenuBase().size(), "No se han generado la cantidad de productos de menu correctos");
    	for(int i = 0; i < menu.size(); i++) {
    		assertEquals(menu.get(i).getNombre(), restaurante.getMenuBase().get(i).getNombre(), "No se han generado los productos de menu correctos");
    		assertEquals(menu.get(i).getPrecio(), restaurante.getMenuBase().get(i).getPrecio(), "No se han generado los productos de menu correctos");
    	}
    	assertEquals(combos.size(), restaurante.getMenuCombos().size(), "No se han generado la cantidad de combos correctos");
    	for(int i = 0; i < combos.size(); i++) {
    		assertEquals(combos.get(i).getNombre(), restaurante.getMenuCombos().get(i).getNombre(), "No se han generado los combos correctos");
    		assertEquals(combos.get(i).getPrecio(), restaurante.getMenuCombos().get(i).getPrecio(), "No se han generado los combos correctos");
    		assertEquals(combos.get(i).getItemsCombo().size(), restaurante.getMenuCombos().get(i).getItemsCombo().size(), "No se han generado los combos correctos");
    		for(int j = 0; j<3; j++) {
    			assertEquals(combos.get(i).getItemsCombo().get(j).getNombre(), restaurante.getMenuCombos().get(i).getItemsCombo().get(j).getNombre(), "No se han generado correctamente los items de los combos");
    			assertEquals(combos.get(i).getItemsCombo().get(j).getPrecio(), restaurante.getMenuCombos().get(i).getItemsCombo().get(j).getPrecio(), "No se han generado correctamente los items de los combos");
    		}
    	}
    }
}

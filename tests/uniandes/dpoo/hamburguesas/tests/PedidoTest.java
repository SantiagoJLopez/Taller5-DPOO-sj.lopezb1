package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class PedidoTest {

Pedido pedido1;
Pedido pedido2;
Pedido pedido3;

ProductoMenu productoMenu1;
ProductoMenu productoMenu2;
ProductoAjustado productoAjustado;
Combo combo;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		productoMenu1 = new ProductoMenu("hamburguesa", 20000);
		
		productoMenu2 = new ProductoMenu("papas", 5000);
		
		Ingrediente ingrediente1 = new Ingrediente( "tomate", 1000 );
		Ingrediente ingrediente2 = new Ingrediente( "mostaza", 1000 );
		
		productoAjustado = new ProductoAjustado(productoMenu1);
		productoAjustado.agregarIngrediente(ingrediente1);
		productoAjustado.eliminarIngrediente(ingrediente2);
		
		ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>();
		items.addLast(productoMenu1);
		items.addLast(productoMenu2);
		
		combo = new Combo("marujas", 0.1, items);
		
		pedido1 = new Pedido("Ignacio", "Boyaca");
		pedido2 = new Pedido("Ortencio", "Peru");
		pedido3 = new Pedido("Eustaquio", "Rusia");
		
		
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetIdPedido() {
    	int numero = pedido1.getIdPedido();
    	assertEquals(numero + 1, pedido2.getIdPedido(), "No se ha generado o leido el ID correctamente");
    	assertEquals(numero + 2, pedido3.getIdPedido(), "No se ha generado o leido el ID correctamente");
    }
    
    @Test
    void testGetNombreCliente() {
    	assertEquals("Ignacio", pedido1.getNombreCliente(), "No se ha encontrado el nombre esperado");
    	assertEquals("Ortencio", pedido2.getNombreCliente(), "No se ha encontrado el nombre esperado");
    	assertEquals("Eustaquio", pedido3.getNombreCliente(), "No se ha encontrado el nombre esperado");
    }
    
    @Test
    void testGetProductos() {
    	ArrayList<Producto> productos = new ArrayList<Producto>();
    	assertEquals(productos, pedido1.getProductos(), "El pedido se ha inicializado incorrectamente o con datos por defecto");
    	
    	productos.addLast(productoMenu1);
    	pedido1.getProductos().addLast(productoMenu1);
    	assertEquals(productos, pedido1.getProductos(), "No se ha obtenido la lista de productos correcta");
    	
    	productos.addLast(productoAjustado);
    	pedido1.getProductos().addLast(productoAjustado);
    	assertEquals(productos, pedido1.getProductos(), "No se ha obtenido la lista de productos correcta");
    	
    	productos.addLast(combo);
    	pedido1.getProductos().addLast(combo);
    	assertEquals(productos, pedido1.getProductos(), "No se ha obtenido la lista de productos correcta");
    }
    
    @Test
    void testAgregarProducto() {
    	assertEquals(0, pedido1.getProductos().size(), "La lista se ha inicializado con algún elemento");
    	
    	pedido1.agregarProducto(productoMenu1);
    	assertEquals(1, pedido1.getProductos().size(), "No se han insertado la cantidad correcta de elementos");
    	assertEquals(productoMenu1, pedido1.getProductos().getFirst(), "Se ha insertado un producto equivocado");
    	
    	pedido1.agregarProducto(productoAjustado);
    	assertEquals(2, pedido1.getProductos().size(), "No se han insertado la cantidad correcta de elementos");
    	assertEquals(productoMenu1, pedido1.getProductos().get(0), "Se ha insertado un producto equivocado");
    	assertEquals(productoAjustado, pedido1.getProductos().get(1), "Se ha insertado un producto equivocado");
    	
    	pedido1.agregarProducto(combo);
    	assertEquals(3, pedido1.getProductos().size(), "No se han insertado la cantidad correcta de elementos");
    	assertEquals(productoMenu1, pedido1.getProductos().get(0), "Se ha insertado un producto equivocado");
    	assertEquals(productoAjustado, pedido1.getProductos().get(1), "Se ha insertado un producto equivocado");
    	assertEquals(combo, pedido1.getProductos().get(2), "Se ha insertado un producto equivocado");
    }
    
    @Test
    void testGetPrecioNetoPedido() {
    	assertEquals(0, pedido1.getPrecioNetoPedido(), "La lista se ha inicializado con algún elemento");
    	
    	pedido1.agregarProducto(productoMenu1);
    	assertEquals(20000, pedido1.getPrecioNetoPedido(), "Ha habido un problema con el valor del elemento singular");
    	
    	pedido1.agregarProducto(productoAjustado);
    	assertEquals(41000, pedido1.getPrecioNetoPedido(), "Ha habido un problema con la suma de los precios");
    	
    	pedido1.agregarProducto(combo);
    	assertEquals(63500, pedido1.getPrecioNetoPedido(), "Ha habido un problema con la suma de los precios");
    }
    
    @Test
    void testGetPrecioIVAPedido() {
    	assertEquals(0, pedido1.getPrecioIVAPedido(), "La lista se ha inicializado con algún elemento");
    	
    	pedido1.agregarProducto(productoMenu1);
    	assertEquals(3800, pedido1.getPrecioIVAPedido(), "Ha habido un problema con el valor del elemento singular");
    	
    	pedido1.agregarProducto(productoAjustado);
    	assertEquals(7790, pedido1.getPrecioIVAPedido(), "Ha habido un problema con la suma de los precios");
    	
    	pedido1.agregarProducto(combo);
    	assertEquals(12065, pedido1.getPrecioIVAPedido(), "Ha habido un problema con la suma de los precios");
    }
    
    @Test
    void testGetPrecioTotalPedido() {
    	assertEquals(0, pedido1.getPrecioTotalPedido(), "La lista se ha inicializado con algún elemento");
    	
    	pedido1.agregarProducto(productoMenu1);
    	assertEquals(23800, pedido1.getPrecioTotalPedido(), "Ha habido un problema con el valor del elemento singular");
    	
    	pedido1.agregarProducto(productoAjustado);
    	assertEquals(48790, pedido1.getPrecioTotalPedido(), "Ha habido un problema con la suma de los precios");
    	
    	pedido1.agregarProducto(combo);
    	assertEquals(75565, pedido1.getPrecioTotalPedido(), "Ha habido un problema con la suma de los precios");
    }
    
    @Test
    void testGenerarTextoFactura() {
    	assertEquals("Cliente: Ignacio\n"
    			   + "Dirección: Boyaca\n"
    			   + "----------------\n"
    			   + "----------------\n"
    			   + "Precio Neto:  0\n"
    			   + "IVA:          0\n"
    			   + "Precio Total: 0\n", pedido1.generarTextoFactura(), "No se ha podido generar un texto para un pedido vacio");
    
    	pedido1.agregarProducto(productoMenu1);
    	assertEquals("Cliente: Ignacio\n"
 			   + "Dirección: Boyaca\n"
 			   + "----------------\n"
 			   + "hamburguesa\n"
 			   + "            20000\n"
 			   + "----------------\n"
 			   + "Precio Neto:  20000\n"
 			   + "IVA:          3800\n"
 			   + "Precio Total: 23800\n", pedido1.generarTextoFactura(), "No se ha podido generar un texto para un pedido vacio");
    	
    	pedido1.agregarProducto(productoAjustado);
    	assertEquals("Cliente: Ignacio\n"
 			   + "Dirección: Boyaca\n"
 			   + "----------------\n"
 			   + "hamburguesa\n"
 			   + "            20000\n"
 			   + "hamburguesa\n"
 			   + "    +tomate\n"
 			   + "                1000\n"
 			   + "    -mostaza\n"
 			   + "            21000\n"
 			   + "----------------\n"
 			   + "Precio Neto:  41000\n"
 			   + "IVA:          7790\n"
 			   + "Precio Total: 48790\n", pedido1.generarTextoFactura(), "No se ha podido generar un texto para un pedido vacio");
    	
    	pedido1.agregarProducto(combo);
    	assertEquals("Cliente: Ignacio\n"
 			   + "Dirección: Boyaca\n"
 			   + "----------------\n"
 			   + "hamburguesa\n"
 			   + "            20000\n"
 			   + "hamburguesa\n"
 			   + "    +tomate\n"
 			   + "                1000\n"
 			   + "    -mostaza\n"
 			   + "            21000\n"
 			   + "Combo marujas\n"
 			   + " Descuento: 0.1\n"
 			   + "            22500\n"
 			   + "----------------\n"
 			   + "Precio Neto:  63500\n"
 			   + "IVA:          12065\n"
 			   + "Precio Total: 75565\n", pedido1.generarTextoFactura(), "No se ha podido generar un texto para un pedido vacio");
    }
    
    void setGuardarFactura() throws FileNotFoundException  {
    	pedido1.agregarProducto(productoMenu1);
    	pedido2.agregarProducto(productoMenu1);
    	pedido2.agregarProducto(productoAjustado);
    	pedido3.agregarProducto(productoMenu1);
    	pedido3.agregarProducto(productoAjustado);
    	pedido3.agregarProducto(combo);
    	
    	pedido1.guardarFactura(new File("factura_1.txt"));
    	pedido2.guardarFactura(new File("factura_2.txt"));
    	pedido3.guardarFactura(new File("factura_3.txt"));
    }
    
    void cleanGuardarFactura() throws IOException  {
    	Files.deleteIfExists(Path.of("factura_1.txt"));
    	Files.deleteIfExists(Path.of("factura_2.txt"));
    	Files.deleteIfExists(Path.of("factura_3.txt"));
    }
    
    @Test
    void testGuardarFactura() throws IOException {
    	setGuardarFactura();
    	
    	String contenido1 = Files.readString(Path.of("factura_1.txt"));
    	assertEquals("Cliente: Ignacio\n"
  			   + "Dirección: Boyaca\n"
  			   + "----------------\n"
  			   + "hamburguesa\n"
  			   + "            20000\n"
  			   + "----------------\n"
  			   + "Precio Neto:  20000\n"
  			   + "IVA:          3800\n"
  			   + "Precio Total: 23800\n", contenido1, "No...");
    	
    	String contenido2 = Files.readString(Path.of("factura_2.txt"));
    	assertEquals("Cliente: Ortencio\n"
  			   + "Dirección: Peru\n"
  			   + "----------------\n"
  			   + "hamburguesa\n"
  			   + "            20000\n"
  			   + "hamburguesa\n"
  			   + "    +tomate\n"
  			   + "                1000\n"
  			   + "    -mostaza\n"
  			   + "            21000\n"
  			   + "----------------\n"
  			   + "Precio Neto:  41000\n"
  			   + "IVA:          7790\n"
  			   + "Precio Total: 48790\n", contenido2, "No...");
    	
    	String contenido3 = Files.readString(Path.of("factura_3.txt"));
    	assertEquals("Cliente: Eustaquio\n"
  			   + "Dirección: Rusia\n"
  			   + "----------------\n"
  			   + "hamburguesa\n"
  			   + "            20000\n"
  			   + "hamburguesa\n"
  			   + "    +tomate\n"
  			   + "                1000\n"
  			   + "    -mostaza\n"
  			   + "            21000\n"
  			   + "Combo marujas\n"
  			   + " Descuento: 0.1\n"
  			   + "            22500\n"
  			   + "----------------\n"
  			   + "Precio Neto:  63500\n"
  			   + "IVA:          12065\n"
  			   + "Precio Total: 75565\n", contenido3, "No...");
    	
    	cleanGuardarFactura();
    }
    
}

package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
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
		
		//pedido.agregarProducto(productoMenu1);
		//pedido.agregarProducto(productoMenu2);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
    	if(restaurante.getPedidoEnCurso() != null) {
    		assertThrows(new YaHayUnPedidoEnCursoException( restaurante.getPedidoEnCurso().getNombreCliente( ), "ignacio" ));
    	}
    	restaurante.iniciarPedido("Ignacio", "Boyaca");
    	assertEquals(pedido.getNombreCliente(), restaurante.getPedidoEnCurso().getNombreCliente());
    }

}

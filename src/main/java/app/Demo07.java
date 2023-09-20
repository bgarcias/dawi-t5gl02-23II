package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

///GUI !!
public class Demo07 {
		//Listado de TODOS los productos, mostrando ademas la informacion del proveedor (nombre y telefono)
	    //asi como su categoria
		public static void main(String[] args) {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
			EntityManager manager = fabrica.createEntityManager();
			
			
			//select * from nombretabla -- Guardar en una lista
			String jpql = "select p from Producto p";
			List<Producto> lstProductos = manager.createQuery(jpql, Producto.class).getResultList();
			
			//Mostrar el listado
			System.out.println("Listado de Productos");
			for (Producto p : lstProductos) {
				System.out.println("Codigo Producto...." + p.getId_prod());
				System.out.println("Producto......." + p.getDes_prod());
				System.out.println("Stock.........." + p.getStk_prod());
				System.out.println("Precio........." + p.getPre_prod());
				System.out.println("Categoria......" + p.getIdcategoria() + " - Categoria:" + p.getObjcategoria().getDescripcion());
				System.out.println("Estado........." + p.getEst_prod());
				System.out.println("Proveedor...." + p.getIdproveedor() + " - Nombre:" + p.getObjproveedor().getNombre_rs()
						+ " - Telefono:" + p.getObjproveedor().getTelefono());
				System.out.println("-----------------------------");
			}
			
			
			manager.close();
			
		}
}

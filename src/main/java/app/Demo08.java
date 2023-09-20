package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

///GUI !!
public class Demo08 {
		//Listado de los usuarios segun filtro o condicion
		
		public static void main(String[] args) {
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
			EntityManager manager = fabrica.createEntityManager();
			
			
			//select * from tb_usuarios where idtipo = ? -- Guardar en una lista
			int tipo = 1;  //Tipo ingresado por la GUI
			String jpql = "select u from Usuario u where u.idtipo = :xtipo";
			List<Usuario> lstUsuarios = manager.createQuery(jpql, Usuario.class).setParameter("xtipo", tipo).getResultList();
			
			//Mostrar el listado
			System.out.println("Listado de Usuarios");
			for (Usuario u : lstUsuarios) {
				System.out.println("Codigo...." + u.getCod_usua());
				System.out.println("Nombre...." + u.getNom_usua() + " " + u.getApe_usua());
				System.out.println("Tipo......" + u.getIdtipo() + " - " + u.getObjTipo().getDescripcion());
				System.out.println("-----------------------------");
			}
			
			
			manager.close();
			
		}
}

package com.proyecto.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.dao.impl.HibernateGenericDaoImpl;
import com.common.util.model.Persistence;

/**
 * La clase que va a implementar el DAO base para todos los DAO del sistema de ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a manejar dentro del DAO.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase manejada.
 */
public abstract class ProyectoDaoImpl<E extends Persistence<PK>, PK extends Serializable> extends HibernateGenericDaoImpl<E, PK> implements
		ProyectoDao<E, PK> {

	@Override
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	protected Session getSession() {
		Session mySession = super.getSession();
		mySession.clear();
		return mySession;
	}
}

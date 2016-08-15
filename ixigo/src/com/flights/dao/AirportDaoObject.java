package com.flights.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.flights.beans.Airport;

/**
 * Contains methods having DB calls using Hibernate framework
 *
 */
public class AirportDaoObject {

	private static SessionFactory factory;

	// Returns session factory object
	private static SessionFactory getSessionFactory() {
		try {
			if (factory == null) {
				System.out.println("Hibernate Annotation Configuration loaded");
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
				System.out.println("Hibernate Annotation serviceRegistry created");
				factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			}
			return factory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// returns airport by airport id
	public static Airport getAirportById(String id) {
		factory = getSessionFactory();
		Session session = factory.openSession();
		Airport airport = session.get(Airport.class, id);
		return airport;
	}

	/*
	 * returns default airports as options for users. Right now, returning the
	 * airports having traffic as 1 meaning they are the prominent airports.
	 * Prominent airports for a user may vary based on his location, his
	 * country, most frequently traveled from his location, etc
	 */
	public static List<Airport> getDefaultAirports() {
		factory = getSessionFactory();
		Session session = factory.openSession();
		List<Airport> airports = session.createQuery("FROM Airport where traffic = 1").getResultList();
		return airports;
	}

	// search airports by city, id, name, etc
	public static List<Airport> searchAirports(String name) {
		factory = getSessionFactory();
		Session session = factory.openSession();
		Criteria cr = session.createCriteria(Airport.class);
		cr.add(Restrictions.or(Restrictions.ilike("id", name), Restrictions.ilike("name", name),
				Restrictions.ilike("city", name)));
		List<Airport> airports = cr.list();
		return airports;
	}

}

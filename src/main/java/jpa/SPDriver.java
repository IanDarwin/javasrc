package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Duration;
import domain.Track;

/**
 * Simple example of using Stored Procedure from within JPA,
 * using the "Native Query" escape route.
 */
public class SPDriver {

	public static void main(String[] args) {

		EntityManagerFactory emf;
		EntityManager em;
		EntityTransaction entityTransaction;

		emf = Persistence.createEntityManagerFactory("ex71_solution");
		em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		// Set up fake data
		Track t1 = new Track("Hop til ya stop", new Duration(240));
		em.persist(t1);
		Track t2 = new Track("Why not tonight?", new Duration(123));
		em.persist(t2);
		
		// A way to call SP from JPA
		Query query = em.createNativeQuery("call getAllTracks()", Track.class);
		
		List<Track> list = query.getResultList();
		System.out.println("Got results, size " + list.size());
		for (Track t : list) {
			System.out.println(t.getTitle());
			System.out.println(t.getDuration());
		}
		transaction.commit();
	}

}

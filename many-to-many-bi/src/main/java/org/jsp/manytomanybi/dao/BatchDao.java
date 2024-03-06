package org.jsp.manytomanybi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.manytomanybi.dto.Batch;

public class BatchDao {

	private Batch batch;
	static EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Batch save(Batch batch) {

		EntityTransaction transaction = manager.getTransaction();
		manager.persist(batch);
		transaction.begin();
		transaction.commit();

		return batch;
	}

	public Batch update(Batch batch) {

		Batch dbBatch = manager.find(Batch.class, batch.getId());

		if (dbBatch != null) {
			EntityTransaction transaction = manager.getTransaction();

			dbBatch.setBatch_code(batch.getBatch_code());
			dbBatch.setTrainer(batch.getTrainer());
			dbBatch.setSubjects(batch.getSubjects());

			transaction.begin();
			transaction.commit();
			return dbBatch;
		}
		return null;
	}

	public Batch findById(int id) {

		return manager.find(Batch.class, id);
	}

	public Batch findByBatchCode(String batchCode) {

		Query query = manager.createQuery("select b from Batch b where b.batch_code = ?1");
		query.setParameter(1, batchCode);

		try {

			return (Batch) query.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}
	}

	public List<Batch> findByTrainerName(String name) {

		Query query = manager.createQuery("select b from Batch b where b.trainer = ?1");
		query.setParameter(1, name);

		return query.getResultList();
	}

	public List<Batch> findBySubject(String subjects) {

		Query query = manager.createQuery("select b from Batch b where b.subjects = ?1");
		query.setParameter(1, subjects);

		return query.getResultList();
	}

	public List<Batch> findByStudentId(int id, String password) {

		Query query = manager.createQuery("select s.batches from Student s where s.id = ?1 and s.password=?2");
		query.setParameter(1, id);
		query.setParameter(2, password);

		return query.getResultList();
	}

	public List<Batch> findByStudentEmail(String email, String password) {

		Query query = manager.createQuery("select s.batches from Student s where s.email = ?1 and s.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);

		return query.getResultList();
	}

	public List<Batch> findByStudentPhone(long phone, String password) {

		Query query = manager.createQuery("select s.batches from Student s where s.phone= ?1 and s.password=?2");
		query.setParameter(1, phone);
		query.setParameter(2, password);

		return query.getResultList();
	}

}

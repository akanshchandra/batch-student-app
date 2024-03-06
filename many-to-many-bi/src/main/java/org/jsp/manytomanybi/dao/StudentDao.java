package org.jsp.manytomanybi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.manytomanybi.dto.Batch;
import org.jsp.manytomanybi.dto.Student;

public class StudentDao {

	private Student Student;
	static EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Student save(Student student, int batch_id) {

		Batch batch = manager.find(Batch.class, batch_id);

		if (batch != null) {

			EntityTransaction transaction = manager.getTransaction();

			batch.getStudents().add(student);
			student.getBatches().add(batch);

			manager.persist(student);
			transaction.begin();
			transaction.commit();

			return student;
		}
		return null;
	}

	public Student update(Student student) {

		Student dbStudent = manager.find(Student.class, student.getId());

		if (dbStudent != null) {

			EntityTransaction transaction = manager.getTransaction();
			dbStudent.setName(student.getName());
			dbStudent.setPerc(student.getPerc());
			dbStudent.setPhone(student.getPhone());
			dbStudent.setEmail(student.getEmail());
			dbStudent.setPassword(student.getPassword());
			return dbStudent;
		}
		return null;
	}

	public Student findById(int id) {

		return manager.find(Student.class, id);
	}

	public Student verifyByStudentId(int id, String password) {

		Query query = manager.createQuery("select s from Student s where s.id = ?1 and s.password=?2");
		query.setParameter(1, id);
		query.setParameter(2, password);

		try {

			return (Student) query.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}
	}

	public Student verifyByStudentEmail(String email, String password) {

		Query query = manager.createQuery("select s from Student s where s.email = ?1 and s.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);

		try {

			return (Student) query.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}
	}

	public Student verifyByStudentPhone(long phone, String password) {

		Query query = manager.createQuery("select s from Student s where s.phone = ?1 and s.password=?2");
		query.setParameter(1, phone);
		query.setParameter(2, password);

		try {

			return (Student) query.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}
	}

	public List<Student> findByBatchCode(String batchCode) {

		Query query = manager.createQuery("select b.students from Batch b where b.batch_code = ?1");
		query.setParameter(1, batchCode);

		return query.getResultList();
	}
	
	public List<Student> findBySubjectName(String subjects) {

		Query query = manager.createQuery("select b.students from Batch b where b.subjects = ?1");
		query.setParameter(1, subjects);

		return query.getResultList();
	}
	
	 
}


package org.jsp.manytomanybi.controller;

import java.util.Scanner;

import org.jsp.manytomanybi.dao.StudentDao;
import org.jsp.manytomanybi.dto.Student;

public class StudentController {
	static Scanner s = new Scanner(System.in);
	static StudentDao studentDao = new StudentDao();

	public static void main(String[] args) {

		int prefer;

		do {

			System.out.println("1). Save Student data \t 2). Update Batch data \t  3) Find Batch by batch_code");
			System.out.println(
					"4). Find batch details by trainer name \t 5). Find batch by subject name \t 6). find batch by student id and password \t 7). find batch by student email and password");
			System.out.println("8). find batch by student phone and password ");
			System.out.println("Enter preferences");
			prefer = s.nextInt();

			switch (prefer) {

			case 1:
				saveStudentDetails();
				break;
//
//			case 2:
//				updateBatchDetails();
//				break;
//
//			case 3:
//				findByBatchId();
//				break;
//
//			case 4:
//				findBatchByTrainerName();
//				break;

//			case 5:
//				findBatchBySubjectName();
//				break;
//
//			case 6:
//				findBatchByStudentId();
//				break;
//
//			case 7:
//				findBatchByStudentEmail();
//				break;
//
//			case 8:
//				findBatchByStudentPhone();
//				break;

			default:
				System.out.println("Invalid preference!!");
			}

		} while (prefer != 8);
	}

	private static void saveStudentDetails() {

		Student student = new Student();
        System.out.println("Enter Batch id to save details");
        int batch_id = s.nextInt();
		
        
        System.out.println("Enter phone");
        student.setPhone(s.nextLong());
        System.out.println("Enter email: ");
        student.setEmail(s.next());
        System.out.println("Enter password");
        student.setPassword(s.next());
        System.out.println("Enter percentage ");
        student.setPerc(s.nextDouble());
        System.out.println("Enter Name ");
		student.setName(s.nextLine());
		student.setName(s.nextLine());
		

		 student  = studentDao.save(student, batch_id);

		System.out.println("Student data saved with id " + student.getId());
	}

//	private static void updateBatchDetails() {
//
//		Batch batch = new Batch();
//		System.out.println("Enter id to update batch: ");
//		int id = s.nextInt();
//
//		batch.setId(id);
//		System.out.println("Enter batchcode ");
//		batch.setBatch_code(s.next());
//
//		System.out.println("Enter trainer name");
//		batch.setTrainer(s.nextLine());
//		batch.setTrainer(s.nextLine());
//
//		System.out.println("Enter subject name");
//		batch.setSubjects(s.nextLine());
//		batch.setSubjects(s.nextLine());
//
//		batch = studentDao.update(batch);
//
//		if (batch != null) {
//
//			System.out.println("Question Data with id " + id + " updated!!");
//		} else
//			System.out.println("invalid id");
//	}
//
//	private static void findByBatchId() {
//
//		System.out.println("Enter batch id to get batch details ");
//		int id = s.nextInt();
//
//		Batch batch = studentDao.findById(id);
//
//		if (batch != null) {
//
//			System.out.println("Batch id: " + batch.getId());
//			System.out.println("Batch code: " + batch.getBatch_code());
//			System.out.println("Trainer name: " + batch.getTrainer());
//			System.out.println("Subject name: " + batch.getSubjects());
//		} else
//			System.out.println("Invalid batch id");
//	}
//
//	private static void findBatchByTrainerName() {
//
//		System.out.println("Enter trainer name to get batch details ");
//		String trainer = s.nextLine();
//		trainer = s.nextLine();
//		List<Batch> batches = studentDao.findByTrainerName(trainer);
//
//		if (batches.size() > 0) {
//
//			for (Batch batch : batches) {
//
//				System.out.println("Batch id: " + batch.getId());
//				System.out.println("Batch code: " + batch.getBatch_code());
//				System.out.println("Trainer name: " + batch.getTrainer());
//				System.out.println("Subject name: " + batch.getSubjects());
//			}
//		} else
//			System.out.println("Invalid trainer name");
//	}

}
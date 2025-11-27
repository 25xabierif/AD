package model;

import java.util.ArrayList;

import gui.StudentsView;

public class AppStudents {
	private ManageStudents manager;
	private StudentsView view;

	/**
	 * Constructor de la clase AppStudents
	 */
	public AppStudents() {
		this.manager = new ManageStudents();
		this.view = new StudentsView(this);
		this.view.setVisible(true);
	}

	/**
	 * Método que recibe una serie de parámetros y registra el estudiante en la BD
	 * @param id
	 * @param name
	 * @param surname
	 * @param age
	 */
	public void enrollStudent(String id, String name, String surname, int age, String email) {
		Student student = new Student(id, name, surname, age, email);
		boolean inserted = manager.addStudent(student);
		if (inserted) {
			view.showMessage("ESTUDIANTE MATRICULADO CORRECTAMENTE.");
			view.clear();
			view.addStudent(id, name, surname, age, email);

		} else {
			view.showMessage("NO SE HA PODIDO MATRICULAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Método que recibe un id y elimina al Student asociado a ese id de la BD
	 * @param id
	 */
	public void dropStudent(String id) {
		boolean deleted = manager.deleteStudent(id);
		if (deleted) {
			view.showMessage("SE HA BORRADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO DESMATRICULAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Método que recibe una serie de parámetros y actualiza al estudiante con el que coincide en la bd.
	 * @param id
	 * @param name
	 * @param surname
	 * @param age
	 */
	public void updateStudent(String id, String name, String surname, int age, String email) {
		Student student = new Student(id, name, surname, age, email);
		boolean modified = manager.modifyStudent(student);
		if (modified) {
			view.showMessage("SE HA ACTUALIZADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO ACTUALIZAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Método que nos muestra una lista de todos los Students que hemos ido registrando en la BD (listados en un ArrayList)
	 */
	public void showAllStudents() {
		ArrayList<Student> students = manager.getStudentsList();
		for (Student student : students) {
			view.addStudent(student.getId(), student.getName(), student.getSurname(), student.getAge(), student.getEmail());
		}
	}
}

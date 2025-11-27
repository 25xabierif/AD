package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.MySQLConnection;

public class ManageStudents {
	
	private MySQLConnection conexion = new MySQLConnection();

	/**
	 * Este método recibe un objeto de la clase @param Student y lo inserta en la BD
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		String sql = "INSERT INTO estudiante VALUES (?,?,?,?,?)";
		try (Connection conn = conexion.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getSurname());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getEmail());
			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			System.err.println("Insertion into table 'estudiante' failed: "+e.getMessage());
			return false;
		}
	}

	/**
	 * Este método recibe un @param id y devuelve los datos del Student asociado a
	 * @param id
	 * @return
	 */
	public Student getStudent(String id) {
		String sql = "SELECT * FROM estudiante WHERE id LIKE ?";
		try (Connection conn = conexion.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			Student student = new Student();
			while (result.next()) {
				student.setId(result.getString("id"));
				student.setName(result.getString("nombre"));
				student.setSurname(result.getString("apellidos"));
				student.setAge(result.getInt("edad"));
				student.setEmail(result.getString("email"));
			}
			return student;
		} catch (SQLException e) {
			System.err.println("Couldn't get requested student: "+e.getMessage());
			return null;
		}
	}

	/**
	 * Método para eliminar de la bd al Student asociado al @param id
	 * @param id
	 * @return
	 */
	public boolean deleteStudent(String id) {
		String sql = "DELETE FROM estudiante WHERE id=?";
		try (Connection conn = conexion.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, id);
			int deletedRow = ps.executeUpdate();
			return deletedRow == 1;
		} catch (SQLException e) {
			System.err.println("Couldn't delete requested student: "+e.getMessage());
			return false;
		}
	}

	/**
	 * Método para modificar al Student. Recibe un objeto @param student y altera sus datos a los nuevos recibidos
	 * @param student
	 * @return
	 */
	public boolean modifyStudent(Student student) {
		String sql = "UPDATE estudiante SET nombre=?, apellidos=?, edad=?, email=?" + " WHERE id=?";
		try (Connection conn = conexion.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, student.getName());
			ps.setString(2, student.getSurname());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getEmail());
			ps.setString(5, student.getId());
			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated == 1;
		} catch (SQLException e) {
			System.err.println("Couldn't modify requeste student: "+e.getMessage());
			return false;
		}
	}

	/**
	 * Método que genera un ArrayList de Students y nos lo devuelve
	 * @return
	 */
	public ArrayList<Student> getStudentsList() {
		try (Connection conn = conexion.getConnection();
		Statement stmt = conn.createStatement()){
			String sql = "SELECT * FROM estudiante";
			ResultSet result = stmt.executeQuery(sql);
			ArrayList<Student> students = new ArrayList<Student>();
			while (result.next()) {
				Student student = new Student();
				student.setId(result.getString("id"));
				student.setName(result.getString("nombre"));
				student.setSurname(result.getString("apellidos"));
				student.setAge(result.getInt("edad"));
				student.setEmail(result.getString("email"));
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			System.err.println("Couldn't get the student list: "+e.getMessage());
			return null;
		}
	}
}

package model;

public class Student {
	private String id;
	private String name;
	private String surname;
	private int age;
	private String email;

	/**
	 * Constructor de la clase Student
	 * @param id
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 */
	public Student(String id, String name, String surname, int age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
	}

	/**
	 * Constructor vacío de la clase Student
	 */
	public Student() {

	}

	/**
	 * Método para obtener el valor de la variable id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método para cambiar el valor de la variable id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Método para obtener el valor de la variable name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método para cambiar el valor de la variable name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Método para obtener el valor de la variable surname
	 * @return
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Método para cambiar el valor de la variable surname
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Método para obtener el valor de la variable age
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Método para cambiar el valor de la variable age
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Método para obtener el valor de la variable email
	 * @return
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * Método para cambiar el valor de la variable email
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Método para obtener un String a partir de los parámetros del objeto
	 */
	@Override
	public String toString() {
		return "Student id: " + id + "\n" + "Student name: " + name + "\n" + "Student surname: " + surname + "\n"
				+ "Student age: " + age + "\n" + "Student email: "+ email + "\n";
	}
}

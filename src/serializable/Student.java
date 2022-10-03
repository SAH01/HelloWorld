package serializable;

/**
 * @author Administrator
 * @date 2022-09-26 21:47
 */
// 实现Serializable接口的学生类
import java.io.Serializable;
public class Student implements Serializable {
	private static final long serialVersionUID = -575577087488357438L;
	private int age;
	private String name;
	public Student(int age, String name) {
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student{" +
				"age=" + age +
				", name='" + name + '\'' +
				'}';
	}
}
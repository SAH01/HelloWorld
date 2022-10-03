package serializable;

import java.io.*;

/**
 * @author Administrator
 * @date 2022-09-26 21:48
 */
public class STest {
	public static void main(String[] args) {
		serial();
		deserial();
	}
	// 序列化方法
	private static void serial(){
		Student student = new Student(21, "John");
		try {
			// FileOutputStream流是指文件字节输出流，
			// 专用于输出原始字节流如图像数据等，其继承OutputStream类，拥有输出流的基本特性
			FileOutputStream fileOutputStream = new FileOutputStream("Student.txt");
			// 对象的序列化流，作用：把对象转成字节数据的输出到文件中保存，
			// 对象的输出过程称为序列化，可实现对象的持久存储。
			ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(student);
			objectOutputStream.flush();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	// 反序列化方法
	private static void deserial() {
		try {
			FileInputStream fis = new FileInputStream("Student.txt");
			// ObjectInputStream 反序列化流，
			// 将之前使用 ObjectOutputStream 序列化的原始数据恢复为对象，以流的方式读取对象。
			// 构造方法 ObjectInputStream(InputStream in) 创建从指定 InputStream 读取的 ObjectInputStream。
			ObjectInputStream ois = new ObjectInputStream(fis);
			Student student = (Student) ois.readObject();
			ois.close();
			System.out.println(student.toString());
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
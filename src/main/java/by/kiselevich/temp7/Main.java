package by.kiselevich.temp7;

import java.io.*;

public class Main {
//    public static void main(String[] args) throws Exception {
//        Person p1 = new Person("Ivan", 24);
//        Person p2 = new Person("Kate", 23);
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println("---");
//        serialize(p1, "p1.txt");
//        serialize(p2, "p2.txt");
//        System.out.println("---");
//        p1 = (Person) deserialize("p1.txt");
//        p2 = (Person) deserialize("p2.txt");
//        System.out.println("---");
//        System.out.println(p1);
//        System.out.println(p2);
//    }

    public static void main(String[] args) throws Exception {
        CustomSerialization customSerialization = new CustomSerialization();
        // Function that serializes and deserializes object back and forth
        serialize(customSerialization, "xc");
        customSerialization = (CustomSerialization) deserialize("xc");
        System.out.println(customSerialization);
    }

    /**
     * Serialize the given object to the file
     */
    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}

class Person implements Serializable {
    private String name;
    private int age;

    public Person() {
        System.out.println("Constructor without args called");
    }

    public Person(String name, int age) {
        System.out.println("Constructor with args called");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        System.out.println("WRITE OBJECT: " + this);
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        System.out.println("READ OBJECT: " + this);
    }
}

class CustomSerialization implements Serializable {

    private static final long serialVersionUID = -8385655899811016412L;

    private final int a;
    private final int b;

    public CustomSerialization() {
        a = 10;
        b = 20;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String toString() {
        return Integer.toString(a) + " " + Integer.toString(b);
    }
}
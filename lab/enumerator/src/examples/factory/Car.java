package examples.factory;

import tom.library.factory.Enumerate;
import tom.library.factory.EnumerateGenerator;

public class Car {
    private int no;
    private Student driver;
//    private Color color;
//
//    // @EnumerateGenerator(canBeNull = true)
//    public Car(@Enumerate(maxSize = 8) int no, @Enumerate Color color) {
//        this.no = no;
//        this.color = color;
//    }
//
//    @EnumerateGenerator(canBeNull = true)
//    public Car(@Enumerate(maxSize = 8) int no, @Enumerate Color color,
//            Student driver) {
//        this.no = no;
//        this.color = color;
//        this.driver = driver;
//    }

    @EnumerateGenerator(canBeNull = true)
    public Car(@Enumerate(maxSize = 8) int no, @Enumerate(maxSize = 4) Student driver) {
        this.no = no;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car [no=" + no + ", driver=" + driver + "]";
    }

}

package ra;

import ra.itf.Chicken;
import ra.itf.DemoIpml;
import ra.itf.IAnimals;
import ra.itf.IOData;
import ra.model.Student;
import ra.model.StudentInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        IOData.sum(1,2);
        IOData ioData = new DemoIpml();
        ioData.displayData();

        // tạo đối tượng từ functional interface
        IAnimals chicken = new Chicken();
        // sử dụng lớp nặc danh
        IAnimals dog = new IAnimals() {
            @Override
            public void makeSound() {
                System.out.println("go go");
            }
        };
        // biểu thức lamda : ()->{}
        IAnimals cat =()->{
            System.out.println("mèo méo meo");
        };
        IOData cal = (a,b)-> a*b;
        System.out.println(cal.multi(3,4));

        // Stream - dòng
        int[] arrInt = {23,1,6,4,8,3,8,4,7};
        // yêu cầu tính tổng của các phần tử trong mảng
        int sum = Arrays.stream(arrInt).sum();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        Integer total = list.stream().reduce(0,(result, currentValue) ->result+currentValue );
        Integer total = list.stream().reduce(0,IOData::sum);
        System.out.println(total);
         Stream<String> stream =list.stream().map(Object::toString);

         //  4 functional interface phổ biến
        // Function
        Function<String, Integer> function = String::length;
        List<String> names = Arrays.asList("hùng","nam","son","khánh");
        Stream<Integer> integerStream =names.stream().map(function);
        double avg = (double) (integerStream.mapToInt(value -> value).sum()) /names.size();
        System.out.println(avg);
        // Predicate
        Predicate<String> predicate = s -> s.length()>=4;
        names.stream().filter(predicate).forEach(System.out::println);

        // Consumer
        Consumer<Integer> consumer = System.out::println;
        // Supplier
        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(10000);
        List<Integer> listInterger = Stream.generate(supplier).distinct().limit(100).toList();
        System.out.println(listInterger);
        System.out.println(listInterger.size());

        // tham chiếu cua phương thức
        List<Student> studentList = Arrays.asList(
                new Student(),
                new Student()
        );
        List<StudentInfo> studentInfos = studentList.stream().map(StudentInfo::new).toList();

    }
}

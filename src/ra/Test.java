package ra;

import ra.itf.Chicken;
import ra.itf.DemoIpml;
import ra.itf.IAnimals;
import ra.itf.IOData;
import ra.model.Student;
import ra.model.StudentInfo;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        IOData.sum(1, 2);
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
        IAnimals cat = () -> {
            System.out.println("mèo méo meo");
        };
        IOData cal = (a, b) -> a * b;
        System.out.println(cal.multi(3, 4));

        // Stream - dòng
        int[] arrInt = {23, 1, 6, 4, 8, 3, 8, 4, 7};
        // yêu cầu tính tổng của các phần tử trong mảng
        int sum = Arrays.stream(arrInt).sum();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        Integer total = list.stream().reduce(0,(result, currentValue) ->result+currentValue );
        Integer total = list.stream().reduce(0, IOData::sum);
        System.out.println(total);
        Stream<String> stream = list.stream().map(Object::toString);

        //  4 functional interface phổ biến
        // Function
        Function<String, Integer> function = String::length;
        List<String> names = Arrays.asList("hùng", "nam", "son", "khánh");
        Stream<Integer> integerStream = names.stream().map(function);
        double avg = (double) (integerStream.mapToInt(value -> value).sum()) / names.size();
        System.out.println(avg);
        // Predicate
        Predicate<String> predicate = s -> s.length() >= 4;
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
//        List<StudentInfo> studentInfos = studentList.stream().map(StudentInfo::new).toList();

        // DateTimeApi
        LocalDate localDate = LocalDate.now(); // ngày tháng năm trong java
        System.out.println(localDate);
        LocalTime localTime = LocalTime.now(); // thời gian giờ phút giây
        System.out.println(localTime);
        LocalDateTime localDateTime = LocalDateTime.now(); // cả date lẫn time
        System.out.println(localDateTime);
        // lấy theo múi giờ
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);

        LocalDate future = localDate.plus(200, ChronoUnit.DAYS);
        System.out.println(future);
        LocalDate birthDay = LocalDate.of(2000, 5, 5);

        Period period = Period.between(birthDay, localDate);
        int year = period.getYears();
        int month = period.getMonths();
        int day = period.getDays();
        System.out.println("age = " + year);

        Duration duration = Duration.between(LocalDateTime.of(2000, 5, 5, 0, 0, 0), localDateTime);
        long seconds = duration.getSeconds();
        System.out.println(seconds);

        // Optional
        Student student = null;
        Optional<Student> optionalStudent = Optional.ofNullable(student);
        // lấy ra ngày sinh nếu student khác null còn   = null n student mang giá trị null
        Date birth = optionalStudent.orElse(new Student()).getBirthday(); // sử dụng giá trị thay thế nếu null
//        Student s = optionalStudent.orElseThrow(); // nếu khác null thì lấy ra, ngợc lại, ném ngoại lệ

        if (optionalStudent.isPresent()) { //kiểm tra tồn tại giá trị
            System.out.println(optionalStudent.get());
        }

        // ứng dụng
        List<Integer> listInt = Arrays.asList(1,2,4,8,10,14,16);
        // yeu cau tim ra giá trị chia ết cho 3 lớn nhất
        Optional<Integer> max = listInt.stream().filter(a->a%3==0).sorted((a,b)->b-a).findFirst();
        Integer number = max.orElseThrow(() -> new RuntimeException("không tìm thấy giá trị chia heet cho 3"));
        System.out.println(number);

    }
}

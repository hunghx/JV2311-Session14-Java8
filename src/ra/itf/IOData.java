package ra.itf;
@FunctionalInterface
public interface IOData {
    double Pi = 3.14;
    // phương thức mặc định
    default void displayData(){
        System.out.println("helllo");
    };
    // phương thức tĩnh
    static  int sum(int a, int b){
        return  a+b;
    }
    int multi(int a, int b);
}

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        int t1=8,t2=9,t3=7,t4,t5;
        int t6 = 15;
        t4=t1<t2?t1:t2; // t4 == 8
        t5=t4<t3?t4:t6; // t5 == 15
        System.out.println(t5);
    }
}

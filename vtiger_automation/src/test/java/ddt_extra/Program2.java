package ddt_extra;

public class Program2 {
public static void main(String[] args) {
	Program1 p1 = new Program1();
	p1.add(13, 35);
	int x=p1.add1(24, 13);
	int y=p1.add1(24, 24, 24);
	System.out.println(x);
	System.out.println(y);
	System.out.println(x*y);
}
}

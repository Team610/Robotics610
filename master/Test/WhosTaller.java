package Gill_William;

import java.util.ArrayList;
import java.util.Scanner;

public class WhosTaller {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		ArrayList<Student> students = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			students.add(new Student(i + 1));
		}
		for (int i = 0; i < m; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			students.get(x-1).taller.add(students.get(y-1));
		}

		int x = scan.nextInt();
		int y = scan.nextInt();
		Student tall = null, small = null;
		for (Student s : students) {
			if (s.num == x)
				tall = s;
			else if (s.num == y)
				small = s;
			if (tall != null && small != null)
				break;
		}
		if (tall != null && tall.isTaller(small)) {
			System.out.println("yes");
		} else if (small != null && small.isTaller(tall)) {
			System.out.println("no");
		} else {
			System.out.println("unknown");
		}

	}
}

class Student {
	int num;
	ArrayList<Student> taller = new ArrayList<>();

	public Student(int i) {
		num = i;
	}

	public boolean isTaller(Student s) {
		if (taller.contains(s))
			return true;
		boolean is = false;
		for (Student student : taller) {
			if (student.isTaller(s)) {
				is = true;
				break;
			}
		}
		return is;
	}
}
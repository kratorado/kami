package kami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class Main {

	//public static char[] colors = new char[] { '1', '2', '3', '4', '5' };

	public static void main(String... args) throws Exception {
		File file = null;
		if (args == null || args.length == 0) {
			file = new File("/path/level.txt");
		} else {
			file = new File(args[0]);
		}
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		Status status = new Status(sb.toString().toCharArray());
		System.out.println(new Date());
		new Solver().solve(status);
		System.out.println(new Date());

	}

}

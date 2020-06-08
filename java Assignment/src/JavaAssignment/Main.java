package JavaAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		List<String> list = null;
		List<Integer> indexno = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("park.txt"));
		String line = "";
		int len = 0;
		while ((line = br.readLine()) != null) {

			if (line.contains("create")) {
				String size[] = line.split(" ");
				len = Integer.parseInt(size[1]);
				list = new ArrayList<>(Integer.parseInt(size[1]));
				System.out.println("Created parking lot with " + size[1] + " slots");
			}

			if (line.contains("park") && !line.contains("_")) {

				String park[] = line.split(" ");

				if (list.size() < len) {

					if (indexno.size() > 0) {

						int indno = indexno.get(0);

						list.add(indno, park[1]);
						int no = list.indexOf(park[1]) + 1;
						System.out.println("Allocated slot number: " + no);
						indexno.remove(0);

					} else {

						list.add(park[1]);
						int no = list.indexOf(park[1]) + 1;
						System.out.println("Allocated slot number: " + no);
					}

				} else {
					System.out.println("Sorry, parking lot is full");
				}
			}

			if (line.contains("leave")) {

				String leave[] = line.split(" ");

				if (list.contains(leave[1])) {
					int no = list.indexOf(leave[1]);
					if (indexno.size() == 0) {
						System.out.println("Registration number " + leave[1] + " with Slot Number " + (no + 1)
								+ " is free with Charge " + charge(leave[2]));
						indexno.add(list.indexOf(leave[1]));
						list.remove(leave[1]);
					} else {
						System.out.println("Registration number " + leave[1] + " with Slot Number " + (no + 2)
								+ " is free with Charge " + charge(leave[2]));
						indexno.add(no + 1);
						list.remove(leave[1]);
					}

				} else {
					System.out.println("Registration number " + leave[1] + " not found");
				}
			}

			if (line.contains("status")) {
				status(list);
			}
		}

	}

	public static void status(List<String> list) {
		System.out.println("Slot No\tRegistration No");
		for (int i = 0; i < list.size(); i++) {

			System.out.println((i + 1) + "\t" + list.get(i));

		}
	}

	public static int charge(String hour) {
		int hr = Integer.parseInt(hour);

		int remaining = hr - 2;

		int total = 10 + (remaining * 10);

		return total;
	}
}
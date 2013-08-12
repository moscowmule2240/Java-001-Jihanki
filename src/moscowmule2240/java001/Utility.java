package moscowmule2240.java001;

import java.io.BufferedReader;
import java.io.IOException;

public class Utility {

	public static Object[] getProductNumber(BufferedReader reader)
			throws IOException {
		System.out.println("商品を選んでください。");
		System.out.println("終了する場合はEnterを押してください。");

		Jihanki.showProducts();

		Object[] returnValues = new Object[2];

		String line = reader.readLine();

		if ("".equals(line)) {
			returnValues[0] = true;
			return returnValues;
		}

		try {
			int inputNumber = Integer.parseInt(line);
			if (inputNumber < 1 || Jihanki.getProductsCount() < inputNumber) {
				throw new NumberFormatException();
			}
			returnValues[1] = inputNumber;
		} catch (NumberFormatException e) {
			System.out.println("正確な商品番号を入力してください。");
			System.out.println();
			returnValues[0] = false;
		}
		return returnValues;
	}

	public static int getNumber(BufferedReader reader, String name,
			int threshold, String unit) throws IOException {
		while (true) {
			try {
				System.out.println(name + "を入力してください。");
				int inputNumber = Integer.parseInt(reader.readLine());
				if (inputNumber < threshold) {
					System.out.println(threshold + unit + "以上を入力してください。");
					continue;
				}
				return inputNumber;
			} catch (NumberFormatException e) {
				System.out.println("数値を入力してください。");
				continue;
			}
		}
	}

	public static boolean getYes(BufferedReader reader) throws IOException {
		boolean bool;
		System.out.println("Y/N");
		String line;
		while (true) {
			line = reader.readLine();
			if ("Y".equalsIgnoreCase(line)) {
				bool = true;
				break;
			} else if ("N".equalsIgnoreCase(line)) {
				bool = false;
				break;
			}
			System.out.println("YかNで入力してください。");
		}
		return bool;
	}
}

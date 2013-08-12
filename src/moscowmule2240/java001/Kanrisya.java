package moscowmule2240.java001;

import java.io.BufferedReader;
import java.io.IOException;

public class Kanrisya {

	private static String PASSWORD = "axiz";

	public static boolean Authentication(BufferedReader reader)
			throws IOException {

		System.out.println("管理者の場合はパスワードを入力してください(PASS \"" + PASSWORD + "\")");
		System.out.println("購入者は enter を押してください");

		while (true) {
			String line = reader.readLine();

			if (PASSWORD.equals(line)) {
				return true;
			} else if ("".equals(line)) {
				return false;
			}

			System.out.println("パスワードが違います。再入力してください。");
		}
	}

	public static void customize(BufferedReader reader) throws IOException {

		while (true) {
			System.out.println("操作内容を選択してください。");
			System.out.println("終了する場合はEnterを押してください。");
			System.out.println("1.商品補充");
			System.out.println("2.商品入替");

			String line = reader.readLine();

			switch (line) {
			case "":
				break;
			case "1":
				supplementation(reader);
				continue;
			case "2":
				replacement(reader);
				continue;
			default:
				System.out.println("正確な操作番号を選んでください。");
				System.out.println();
				continue;
			}

			Jihanki.sort();
			break;
		}

		System.out.println("商品内容を表示します。");
		Jihanki.showProducts();
		System.out.println();
	}

	private static void supplementation(BufferedReader reader)
			throws IOException {

		while (true) {
			Object[] returnValues = Utility.getProductNumber(reader);
			if (returnValues[0] != null && (boolean) returnValues[0]) {
				break;
			} else if (returnValues[0] != null && !(boolean) returnValues[0]) {
				continue;
			}
			int inputNumber = (int) returnValues[1];
			System.out.println(inputNumber + "番目の商品を補充します。");

			int inputStock = Utility.getNumber(reader, "補充数", 1, "個");
			System.out.println(inputStock + "個 でよろしいですか？");

			if (!Utility.getYes(reader)) {
				System.out.println("最初からやり直してください。");
				System.out.println();
				continue;
			}

			System.out.println("商品を補充します。");
			Jihanki.addProductsStock(inputNumber - 1, inputStock);
		}
	}

	private static void replacement(BufferedReader reader) throws IOException {

		while (true) {
			Object[] returnValues = Utility.getProductNumber(reader);
			if (returnValues[0] != null && (boolean) returnValues[0]) {
				break;
			} else if (returnValues[0] != null && !(boolean) returnValues[0]) {
				continue;
			}
			int inputNumber = (int) returnValues[1];
			System.out.println(inputNumber + "番目の商品を入れ替えます。");

			System.out.println("新しい商品名を入力してください。");
			String productName = reader.readLine();

			int productPrice = Utility.getNumber(reader, "新しい価格", 1, "円");

			int productStock = Utility.getNumber(reader, "在庫", 1, "個");

			System.out.println("商品名：" + productName + " 価格：" + productPrice
					+ "円 在庫：" + productStock + "個 でよろしいですか？");

			if (!Utility.getYes(reader)) {
				System.out.println("最初からやり直してください。");
				System.out.println();
				continue;
			}

			System.out.println("商品を入れ替えます。");
			Jihanki.setProduct(inputNumber - 1, productName, productPrice,
					productStock);
		}
	}
}

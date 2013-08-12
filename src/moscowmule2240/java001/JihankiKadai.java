package moscowmule2240.java001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JihankiKadai {

	public static void main(String[] args) {

		Object[][] products = new Object[][] { { "缶ジュース", 120 },
				{ "カップラーメン", 170 }, { "タバスコ", 320 } };

		BufferedReader reader;

		try {
			reader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("いらっしゃいませ。");
			System.out.println("お金を入れてください。");

			String inputMoneyString = reader.readLine();
			int inputMoney = Integer.parseInt(inputMoneyString);

			System.out.format("現在%d円です。%n%n", inputMoney);

			int number;

			while (true) {
				System.out.println("商品を選んで下さい。");
				for (int i = 0; i < products.length; i++) {
					System.out.format("%d:%s%4d円%n", i + 1,
							padding((String) products[i][0], 20),
							(int) products[i][1]);
				}

				String numberString = reader.readLine();
				number = Integer.parseInt(numberString);

				if (number < 1 || products.length < number) {
					System.out.format("商品は%d種類しかありません！%n%n", products.length);
				} else {
					break;
				}
			}

			String productName = (String) products[number - 1][0];
			int productPrice = (int) products[number - 1][1];

			if (inputMoney < productPrice) {
				System.out.println("お金が足りませんね、さようなら。");
				return;
			}

			System.out.format("%sのお買い上げありがとうございました。%n", productName);
			System.out.format("%d円のおつりです。%n", inputMoney - productPrice);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("数字の形式が正しくありません。");
		}
	}

	private static String padding(String baseString, int length) {

		StringBuilder builder = new StringBuilder();

		int stringLength;
		if (baseString == null) {
			stringLength = 0;
		} else {
			stringLength = baseString.getBytes().length;
			builder.append(baseString);
		}

		for (int i = stringLength; i < length; i++) {
			builder.append(' ');
		}

		return builder.toString();
	}

}

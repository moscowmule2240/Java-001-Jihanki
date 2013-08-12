package moscowmule2240.java001;

import java.io.BufferedReader;
import java.io.IOException;

public class User {

	public static void buy(BufferedReader reader) throws IOException {

		System.out.println("いらっしゃいませ。");

		int money = Utility.getNumber(reader, "金額", 1, "円");
		System.out.format("現在%d円です。%n%n", money);

		while (true) {
			Object[] returnValues = Utility.getProductNumber(reader);
			if (returnValues[0] != null && (boolean) returnValues[0]) {
				break;
			} else if (returnValues[0] != null && !(boolean) returnValues[0]) {
				continue;
			}
			int inputNumber = (int) returnValues[1];

			if (Jihanki.getProductStock(inputNumber - 1) == 0) {
				System.out.println("その商品は品切れです。");
				continue;
			}

			System.out.println(inputNumber + "番目の商品を購入します。よろしいですか？");
			if (!Utility.getYes(reader)) {
				continue;
			}

			while (money < Jihanki.getProductPrice(inputNumber - 1)) {
				System.out.println("商品価格が残金を超えています。");
				System.out.println("再度入金しますか？");
				if (Utility.getYes(reader)) {
					money = money + Utility.getNumber(reader, "金額", 1, "円");
					System.out.println("残金" + money + "円です。");
					System.out.println();
				} else {
					break;
				}
			}
			if (Jihanki.getProductPrice(inputNumber - 1) <= money) {
				money = money - Jihanki.getProductPrice(inputNumber - 1);
				Jihanki.addProductsStock(inputNumber - 1, -1);
				Jihanki.addPurchase(inputNumber - 1);
				System.out.println(Jihanki.getProductName(inputNumber - 1)
						+ "を購入しました。");
				System.out.println("残金" + money + "円です。");
				System.out.println();
			}
		}

		System.out.println("買い物を終了します。");

		System.out.println("購入品");
		Jihanki.showPurchaseProducts();

		System.out.println("合計購入金額 " + Jihanki.totalPrice() + "円");
		System.out.println("おつり " + money + "円");
	}
}

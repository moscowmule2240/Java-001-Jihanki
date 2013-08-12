package moscowmule2240.java001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jihanki {

	private static Object[][] products = new Object[][] { { "レモン", 85, 3 },
			{ "みかん", 100, 3 }, { "キウィ", 100, 3 }, { "りんご", 150, 3 },
			{ "ぶどう", 150, 3 }, { "バナナ", 198, 3 }, { "もも", 200, 3 },
			{ "いちご", 398, 3 }, { "メロン", 1000, 3 }, { "すいか", 1500, 3 } };

	private static int[] cart = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static void showProducts() {
		System.out.println("商品番号 商品名 価格 在庫");
		for (int i = 0; i < products.length; i++) {
			System.out.println((i + 1) + " " + products[i][0] + " "
					+ products[i][1] + "円 " + products[i][2] + "個");
		}
	}

	public static int getProductsCount() {
		return products.length;
	}

	public static String getProductName(int index) {
		return (String) products[index][0];
	}

	public static int getProductPrice(int index) {
		return (int) products[index][1];
	}

	public static int getProductStock(int index) {
		return (int) products[index][2];
	}

	public static void setProduct(int index, String name, int price, int stock) {
		int tempIndex = 0;
		products[index][tempIndex++] = name;
		products[index][tempIndex++] = price;
		products[index][tempIndex++] = stock;
	}

	public static void addProductsStock(int index, int stock) {
		products[index][2] = (int) products[index][2] + stock;
	}

	public static void sort() {
		for (int i = 0; i < products.length - 1; i++) {
			for (int j = products.length - 1; j > i; j--) {
				if ((int) products[j][1] < (int) products[j - 1][1]) {
					Object[] temp = products[j];
					products[j] = products[j - 1];
					products[j - 1] = temp;
				}
			}
		}
	}

	public static void addPurchase(int index) {
		cart[index]++;
	}

	public static void showPurchaseProducts() {
		for (int i = 0; i < cart.length; i++) {
			if (cart[i] == 0) {
				continue;
			}
			System.out.println(getProductName(i) + " " + cart[i] + "個");
		}
	}

	public static int totalPrice() {
		int sum = 0;
		for (int i = 0; i < cart.length; i++) {
			sum += getProductPrice(i) * cart[i];
		}
		return sum;
	}

	public static void main(String[] args) {

		BufferedReader reader;

		try {
			reader = new BufferedReader(new InputStreamReader(System.in));

			if (Kanrisya.Authentication(reader)) {
				System.out.println("管理者画面へ移ります。");
				Kanrisya.customize(reader);
			}

			System.out.println("購入画面に移ります");
			User.buy(reader);

		} catch (IOException e) {
			System.err.println("重大な例外が発生しました。処理を終了します。");
			e.printStackTrace();
		}
	}
}

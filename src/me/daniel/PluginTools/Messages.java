package me.daniel.PluginTools;

public class Messages {
	private String message;
	private String article;

	public Messages(String message) {
		this.message = message;

		if (message.toLowerCase().charAt(0) == 'a' || message.toLowerCase().charAt(0) == 'e'
				|| message.toLowerCase().charAt(0) == 'i' || message.toLowerCase().charAt(0) == 'o'
				|| message.toLowerCase().charAt(0) == 'u') {
			article = "an";
		} else {
			article = "a";
		}

	}

	public void intoReadable() {
		message = message.toLowerCase();
		String ans = "";
		char ch = 0;

		for (int i = 0; i < message.length(); i++) {

			if (i == 0 || ch == '_') {
				char c = message.charAt(i);
				c = Character.toUpperCase(c);
				ans += c;
			} else if (!(message.charAt(i) == '_')) {
				ans += message.charAt(i);
			} else {
				ans += " ";
			}

			if (i > 0) {
				ch = message.charAt(i);
			}

		}

		message = ans;
	}

	public String getMessage() {
		return message;
	}

	public String getArticle() {
		return article;
	}

	public String intoClock() {
		int num = Integer.parseInt(message);
		String ans = "";
		ans += num / 60 + ":";

		if (num % 60 < 10) {
			ans += "0";
		}

		ans += num % 60;
		return ans;
	}
}
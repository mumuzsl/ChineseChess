package app.mumu.chinesechess.random;

public class MyRandom {
	public static String randomString(int len) {
		String s = new String();
		for (int i = 0; i < len; i++) {
			s += UpperLowerNumber(3);
		}
		return s;
	}

	public static String randomString(int origin, int bound) {
		return randomString(randomInt(origin, bound));
	}

	public static String randomNumberString(int len) {
		String s = new String();
		for (int i = 0; i < len; i++) {
			s += number();
		}
		return s;
	}

	public static String randomLetterString(int origin, int bound) {
		return randomLetterString(randomInt(origin, bound));
	}

	public static String randomLetterString(int len) {
		String s = new String();
		for (int i = 0; i < len; i++) {
			s += UpperLowerNumber(2);
		}
		return s;
	}

	private static char UpperLowerNumber(int n) {
		switch ((int) (Math.random() * n)) {
		case 0:
			return upperCase();
		case 1:
			return lowerCase();
		case 2:
			return number();
		default:
			break;
		}
		return 0;
	}

	private static char upperCase() {
		return (char) (int) (65 + Math.random() * 25);
	}

	private static char lowerCase() {
		return (char) (int) (97 + Math.random() * 25);
	}

	private static char number() {
		return (char) (int) (48 + Math.random() * 9);
	}

	private static double random(int origin, int bound) {
		return origin + (Math.random() * (bound - origin));
	}

	public static int randomInt(int origin, int bound) {
		return (int) (origin + (Math.random() * (bound - origin)));
	}
}

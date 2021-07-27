public class Recursion {
	int fib(int n) {return n <= 2 ? 1 : fib(n - 1) + fib(n - 2);}
	long fangs(long n) {return n == 1 ? 2 : 3 - (n % 2) + fangs(n - 1);}
	int divide(int a, int b) {
		int negxor = ((a >> 31 ^ b >> 31) << 1) + 1;
		return divhelp(a) < divhelp(b) ? 0 : negxor + divide(a - negxor * b, b);
	}
	int findCap(String s) {
		int len = s.length() - 1;
		char last = s.charAt(s.length() - 1);
		int oneIfUpper = (64 - last) >>> 31 & (last - 91) >>> 31;
		return len < 1 ? oneIfUpper : oneIfUpper + findCap(s.substring(0, s.length() - 1));
	}
	String reverse(String word, char c) {
		int ind = word.length() - 1;
		char lastLetter = word.charAt(ind);
		return ind == 0 ? word : "" + lastLetter + (lastLetter == word.charAt(ind - 1) ? c : "") + reverse(word.substring(0, ind), c);
	}
	String commas(int x, boolean posZero) {
		return (((x ^ (x >> 31)) - (x >> 31) - 1000) >>> 31) == 1 ? 
					String.format(x == 0 && !posZero ? "%d" : "%+d", x) : 
					commas(x / 1000, posZero) + "," + String.format("%03d", (~((x >>> 31) << 1) + 2) * (x % 1000));
	}
	int bigIntegerFromArr(int[] arr) {
		int aLen = arr.length - 1, arrCpy[] = new int[aLen];
		System.arraycopy(arr, 0, arrCpy, 0, aLen);
		return aLen < 1 ? arr[0] : Math.max(arr[aLen], bigIntegerFromArr(arrCpy));
	}
	int divhelp(int val) {return ((val ^ (val >> 31)) - (val >> 31));}
}
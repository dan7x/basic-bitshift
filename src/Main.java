import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * Daniel Xiao
 * Grade 12 Computer Science
 * March 6, 2021
 */

public class Main {

	public static void main(String[] args) throws IOException {
		HashMap<Integer, String> exercises = new HashMap<Integer, String>();
		exercises.put(1, "1 - fib(int n)");
		exercises.put(2, "2 - fangs(int n)");
		exercises.put(3, "3 - divide(int a, int b)");
		exercises.put(4, "4 - findCap(String s)");
		exercises.put(5, "5 - reverse(String word, char c)");
		exercises.put(6, "6 - commas(int x)");
		exercises.put(7, "7 - bigIntegerFromArr(int[] arr)");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean running = true;
		while(running) {
			System.out.println("\nINSTRUCTIONS : Input '0' to terminate program, otherwise, type a valid exercise number.\n");
			
			byte inputEx = getExerciseNumber(br, exercises);
			running = inputEx != 0;
			
			boolean exitExercise = false;
			while(!exitExercise && running) {
				exitExercise = handleInput(inputEx, br, exercises);
			}
			System.out.println("---------------------------------------");
		}
	}
	
	// ---------------------------------------- INPUT VALIDATION -------------------------------------------//
	
	static byte getExerciseNumber(BufferedReader br, HashMap<Integer, String> map) throws IOException {
		boolean exValid = false;
		byte inputEx = 1;
		
		do{
			printPrompt(map);
			try {
				inputEx = Byte.parseByte(br.readLine());
				exValid = (~(inputEx >> 7) & (((inputEx - 8) & 0xff) >>> 7)) == 1;
			}catch(NumberFormatException e) {
				
			}
			System.out.println(exValid ? "" : badEntry());
		} while(!exValid);
		
		return inputEx;
	}
	
	static boolean handleInput(byte ex, BufferedReader br, HashMap<Integer, String> exercises) throws IOException {	
		boolean isValidInput = false;
		Recursion r = new Recursion();
		do {
			switch(ex) {
				case 1:
					header(ex, exercises);
					final int fibIn = intSafeRead(br, "Input : ");
					isValidInput = fibIn > 0;
					System.out.println(isValidInput ? "Program Output : " + r.fib(fibIn) : "");
					break;
				case 2:
					header(ex, exercises);
					final int fangsIn = intSafeRead(br, "Input : ");
					isValidInput = fangsIn > 0;
					System.out.println(isValidInput ? "Program Output : " + r.fangs(fangsIn) : "");
					break;
				case 3:
					header(ex, exercises);
					final int a = intSafeRead(br, "Numerator : ");
					final int b = intSafeRead(br, "Denominator : ");
					isValidInput = b != 0;
					System.out.println(isValidInput ? "Program Output : " +  r.divide(a, b) : "");
					break;
				case 4:
					header(ex, exercises);
					System.out.print("String to check : ");
					System.out.println("Program Output : " +  r.findCap(br.readLine()));
					isValidInput = true;
					break;
				case 5:
					header(ex, exercises);
					System.out.print("String to check : ");
					final String strIn = br.readLine();
					final char c = charSafeRead(br, "Character : ");
					
					System.out.println("Program Output : " +  r.reverse(strIn, c));
					isValidInput = true;
					break;
				case 6:
					header(ex, exercises);
					final int commaIn = intSafeRead(br, "Input : ");
					char posZero = 'h';
					if(commaIn == 0) {
						posZero = charSafeRead(br, "Show positive zero (+0) ('Y' for yes)? : ");
					}
					System.out.println("Program Output : " + r.commas(commaIn, (posZero == 'Y' ? true : false)));
					isValidInput = true;
					break;
				case 7:
					header(ex, exercises);
					int[] bigArrIn = arraySafeRead(br, "Array (separate values by comma) : ");
					System.out.println("Program Output : " +  r.bigIntegerFromArr(bigArrIn));
					isValidInput = true;
					break;
			}
			if(!isValidInput) {
				System.out.println(badEntry());
			}
		}while(!isValidInput);
		
		System.out.print("Exit exercise and return to selection? (type '0' to exit, hit enter to continue) : ");
		String u = br.readLine();
		return (u.length() == 1 && u.charAt(0) == '0');
	}
	
	static int[] arraySafeRead(BufferedReader br, String prompt) throws IOException {
		boolean validArrEntered = false;
		int[] out = null;
		
		do {
			System.out.print(prompt);
			try {
				String[] tmp = br.readLine().replace(" ", "").split(",");
				out = new int[tmp.length];
				
				for(int i = 0; i < tmp.length; i++) {
					out[i] = Integer.parseInt(tmp[i]);
				}
				
				validArrEntered = true;
			} catch (NumberFormatException e) {
				System.out.println(badEntry());
			}
		} while(!validArrEntered);
		
		return out;
	}
	
	static int intSafeRead(BufferedReader br, String originalPrompt) throws IOException {
		boolean isInputValid = false;
		int out = 0;
		
		do {
			System.out.print(originalPrompt);
			try {
				out = Integer.parseInt(br.readLine());
				isInputValid = true;
			}catch(NumberFormatException e) {
				
			}
			System.out.println(isInputValid ? "" : badEntry());
		} while(!isInputValid);
		
		return out;
	}
	
	static char charSafeRead(BufferedReader br, String originalPrompt) throws IOException {
		boolean isInputValid = false;
		String out = "";
		do {
			System.out.print(originalPrompt);
			try {
				out = br.readLine();
				isInputValid = out.length() == 1;
			}catch(NumberFormatException e) {
				
			}
			System.out.println(isInputValid ? "" : badEntry());
		} while(!isInputValid);
		
		return out.charAt(0);
	}
	
	static void printPrompt(HashMap<Integer, String> map) {
		for(Integer i : map.keySet()) {
			System.out.println(map.get(i));
		}
		System.out.print("\nEnter exercise number from above list : ");
	}
	
	static void header(byte ex, HashMap<Integer, String> exercises) {
		System.out.println("---------------------------------------\n" + exercises.get((int)ex) + "\n");
	}
	
	static String badEntry() {
		return "\n<--- INVALID ENTRY, TRY AGAIN --->\n\n";
	}

}

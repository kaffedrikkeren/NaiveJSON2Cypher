public class Main {

    public static void main(String[] args) {
	  Long a = 1234567890L;
	  Long b = a/(long)Math.pow(10,9);
	  byte c = b.byteValue();
	  System.out.println(b);
	  System.out.println(c);
    }
}

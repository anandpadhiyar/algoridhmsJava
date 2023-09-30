public class HelloGoodbye {
    public static void main(String[] args) {
        String firstPerson = args[0];
        String secondPerson = args[1];
        String helloStr = String.format("Hello %s and %s.", firstPerson, secondPerson);
        String goodByeStr = String.format("Goodbye %s and %s.", secondPerson, firstPerson);
        System.out.println(helloStr);
        System.out.println(goodByeStr);
    }
}

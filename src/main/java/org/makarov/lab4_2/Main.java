package org.makarov.lab4_2;

public class Main {
    public static void main(String[] args) {
        String encodedMessage = "t2st3ng vetviph";
        StringBuilder decodedMessage = new StringBuilder();

        for (String word : encodedMessage.split(" ")) {
            decodedMessage.append(Decoder.decode(word)).append(" ");
        }

        System.out.printf("Encoded message: %s\n" + "Decoded message: %s%n",
                encodedMessage, decodedMessage);
    }
}

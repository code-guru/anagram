package se.king.mehdi.anagram;

import se.king.mehdi.utils.PrimeSupplier;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Verification {
    /* restrict the acceptable characters as a proper game */
    private Map<Character, Integer> charPrimeMap;

    public Verification() {
        final PrimeSupplier primeSupplier = new PrimeSupplier();
        final IntStream[] characterRanges = new IntStream[]{
                IntStream.rangeClosed(97, 121),  // latin english
                IntStream.rangeClosed(224, 246), // latin other
                IntStream.rangeClosed(248, 255)  // latin other
        };
        charPrimeMap = Arrays.stream(characterRanges)
                .reduce(IntStream::concat)
                .orElseGet(IntStream::empty)
                .boxed()
                .collect(Collectors.toMap(
                        charCode -> (char) (int) charCode,
                        charCode -> primeSupplier.getAsInt()
                ));
    }

    private String getAnagramId(String word) {
        BigInteger anagramId = BigInteger.valueOf(1);
        word = word.replaceAll("[\\s\\-_,.:;]", "");
        char[] chars = word.toCharArray();
        for (char ch : chars) {
            Integer charPrime = charPrimeMap.get(ch);
            if (charPrime == null) {
                // throws if the anagram has a character outside acceptable char range
                throw new IllegalArgumentException("The word cannot contains non-latin letters or numbers.");
            }

            anagramId = anagramId.multiply(BigInteger.valueOf(charPrime));
        }
        return anagramId.toString(Character.MAX_RADIX);
    }

    private boolean isWordValid(String word) {
        return word != null && word.length() > 1;
    }

    public int calculateScore(String word, String anagram) {
        if (!(isWordValid(word) && isWordValid(anagram))) {
            throw new NullPointerException("The word cannot be null or less than 2 characters.");
        }

        if (Objects.equals(getAnagramId(word), getAnagramId(anagram))) {
            return word.length();
        }

        return 0;
    }

}

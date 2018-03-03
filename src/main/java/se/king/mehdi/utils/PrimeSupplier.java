package se.king.mehdi.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;

public class PrimeSupplier implements IntSupplier {

    private final List<Integer> primes;

    public PrimeSupplier() {
        primes = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public int getAsInt() {
        Integer lastPrime = primes.size() == 0 ? 1 : primes.get(primes.size() - 1);
        Integer newPrime = null;
        final AtomicInteger atomicInteger = new AtomicInteger(lastPrime + 1);

        while (newPrime == null) {
            final int num = atomicInteger.get();
            boolean isPrime = primes.stream().allMatch(n ->
                    n == 1 || num % n != 0
            );
            if (isPrime) {
                newPrime = num;
            } else {
                atomicInteger.incrementAndGet();
            }

        }

        primes.add(newPrime);
        return newPrime;
    }

}

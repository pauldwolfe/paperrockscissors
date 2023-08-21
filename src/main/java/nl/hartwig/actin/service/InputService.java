package nl.hartwig.actin.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InputService {

    private final Scanner scanner;

    public InputService() {
        this.scanner = new Scanner(System.in);
    }

    public synchronized String getNextString() {
        return this.scanner.next();
    }

}


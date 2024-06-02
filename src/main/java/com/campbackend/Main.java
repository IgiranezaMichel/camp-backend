package com.campbackend;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
public class Main {
public static void main(String[] args) {
    // int leftLimit = 48; // numeral '0'
    // int rightLimit = 122; // letter 'z'
    // int targetStringLength = 20;
    // Random random = new Random();

    // String generatedString = random.ints(leftLimit, rightLimit + 1)
    //   .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
    //   .limit(targetStringLength)
    //   .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
    //   .toString();

    // System.out.println(generatedString);
    LocalDate userDate=LocalDate.of(2023, 06, 01);
    long date=userDate.until(LocalDate.now(),ChronoUnit.YEARS);
    // Period period=Period.between(userDate, LocalDate.now());
    System.out.println(date);
}
}

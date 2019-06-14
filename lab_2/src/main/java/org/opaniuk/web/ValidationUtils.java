package org.opaniuk.web;

import java.io.IOException;
import java.util.Optional;

public class ValidationUtils {
  public static boolean validateString(String value) {
    return Optional.ofNullable(value).filter(x -> !x.isEmpty()).isPresent();
  }

  public static boolean validateInt(String value) {
    return Optional.ofNullable(value)
        .filter(x -> !x.isEmpty())
        .filter(x -> x.chars().allMatch(Character::isDigit))
        .isPresent();
  }
}

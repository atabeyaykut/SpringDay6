package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class BurgerValidation {

    public static void validate(Burger burger) {
        if (burger == null) {
            throw new BurgerException("Burger payload is null", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(burger.getName())) {
            throw new BurgerException("Burger name is required", HttpStatus.BAD_REQUEST);
        }
        if (burger.getPrice() == null || burger.getPrice() < 0) {
            throw new BurgerException("Burger price must be non-negative", HttpStatus.BAD_REQUEST);
        }
        if (burger.getBreadType() == null) {
            throw new BurgerException("BreadType is required", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(burger.getContents())) {
            throw new BurgerException("Contents cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
}

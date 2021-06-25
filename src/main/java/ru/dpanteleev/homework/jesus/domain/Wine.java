package ru.dpanteleev.homework.jesus.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class Wine {

    private final String name;

    public String getName() {
        return name;
    }
}

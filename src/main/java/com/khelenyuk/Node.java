package com.khelenyuk;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {
    private String current;
    private String previous;
    private Integer cost;
}

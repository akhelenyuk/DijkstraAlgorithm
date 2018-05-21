package com.khelenyuk;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {
    private String currentNode;
    private String previousNode;
    private Integer cost;
}

package com.khelenyuk;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private String node;
    private Integer cost;
}

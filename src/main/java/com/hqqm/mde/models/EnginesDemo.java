package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnginesDemo {
    private int totalPages;
    private List<EngineDTO> engines;
}

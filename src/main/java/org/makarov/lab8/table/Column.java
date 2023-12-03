package org.makarov.lab8.table;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Column {
    @Getter
    private final Table table;
    private final List<Cell> cells;
    @Getter
    private int width;

    protected Column(Table table) {
        this.table = table;
        cells = new ArrayList<>();
        width = 0;
    }

    protected void addCell(Cell cell) {
        cells.add(cell);
        calculateWidth();
    }

    private void calculateWidth() {
        width = cells.stream().map(Cell::getWidth).max(Integer::compareTo).orElse(0);
    }
}

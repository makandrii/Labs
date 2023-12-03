package org.makarov.lab8.table;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Row {
    private final Table table;
    private final List<Cell> cells;

    protected Row(Table table) {
        this.table = table;
        cells = new ArrayList<>();
    }

    public void createCell(String data) {
        if (cells.size() + 1 > table.getColumns().size()) {
            table.createColumn();
        }
        StringCell newCell = new StringCell(this, table.getColumns().get(cells.size()), data);
        table.getColumns().get(cells.size()).addCell(newCell);
        cells.add(newCell);
    }

    public void createCell(double data) {
        if (cells.size() + 1 > table.getColumns().size()) {
            table.createColumn();
        }
        DoubleCell newCell = new DoubleCell(this, table.getColumns().get(cells.size()), data);
        table.getColumns().get(cells.size()).addCell(newCell);
        cells.add(newCell);
    }
}

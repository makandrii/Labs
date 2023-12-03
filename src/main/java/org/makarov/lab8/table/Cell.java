package org.makarov.lab8.table;

import lombok.Getter;

@Getter
public abstract class Cell {
    private final Row row;
    private final Column column;
    private int width;

    protected Cell(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    abstract public String getData();
}

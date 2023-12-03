package org.makarov.lab8.table;

import lombok.Getter;

@Getter
public class DoubleCell extends Cell {
    private Double data;

    protected DoubleCell(Row row, Column column, double data) {
        super(row, column);
        this.data = data;
        setWidth(getData().length());
    }

    @Override
    public String getData() {
        return data.toString();
    }
}

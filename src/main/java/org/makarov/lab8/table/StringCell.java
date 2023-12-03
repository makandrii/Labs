package org.makarov.lab8.table;

import lombok.Getter;

@Getter
public class StringCell extends Cell {
    private String data;

    protected StringCell(Row row, Column column, String data) {
        super(row, column);
        this.data = data;
        setWidth(getData().length());
    }

    @Override
    public String getData() {
        return data;
    }
}

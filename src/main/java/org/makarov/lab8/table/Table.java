package org.makarov.lab8.table;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Table {
    private final List<Column> columns;
    private final List<Row> rows;

    public Table() {
        columns = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public Row createRow() {
        Row newRow = new Row(this);
        rows.add(newRow);
        return newRow;
    }

    protected void createColumn() {
        Column newColumn = new Column(this);
        columns.add(newColumn);
    }

    public void print() {
        String rowSeparator = buildRowSeparator();
        StringBuilder result = new StringBuilder(rowSeparator);
        rows.forEach(row -> {
            row.getCells().forEach(cell -> result.append("| ")
                    .append(cell.getData())
                    .append(String.valueOf(' ')
                            .repeat(cell.getColumn().getWidth() - cell.getWidth() + 1)));
            result.append(" |").append("\n").append(rowSeparator);
        });
        System.out.println(result);
    }

    private String buildRowSeparator() {
        StringBuilder result = new StringBuilder();
        columns.forEach(column -> result.append("+-")
                .append(String.valueOf('-').repeat(column.getWidth() + 1)));
        return result.append("-+").append("\n").toString();
    }
}

package com.company.bandlist;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by dim on 22.12.16.
 */
public class BandModel extends AbstractTableModel {
    // Список загловков для колонок в таблице
    private static final String[] headers = {"ID", "Название", "Лидер", "Кол-во участников"};

    // Здесь мы храним список контактов, которые будем отображать в таблице
    private final List<Band> bands;

    public BandModel(List<Band> bands) {
        this.bands = bands;
    }

    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return bands.size();
    }

    @Override
    // Получить количество столбцов - их у нас стольк же, сколько полей
    // у класса Contact - всего пять
    public int getColumnCount() {
        return 4;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    // Получить объект для тображения в кокнретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    public Object getValueAt(int row, int col) {
        Band band = bands.get(row);
        // В зависимости от номера колонки возвращаем то или иное поле контакта
        switch (col) {
            case 0:
                return band.getId().toString();
            case 1:
                return band.getName();
            case 2:
                return band.getMainStar();
            case 3:
                return Integer.toString(band.getMembersNumber());
            default:
                return band.getName();
        }
    }
}

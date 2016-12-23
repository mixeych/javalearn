package com.company.bandlist;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * Created by dim on 22.12.16.
 */
public class BandFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final BandController bandController = new BandController();
    private final JTable bandTable = new JTable();

    // В конструкторе мы создаем нужные элементы
    public BandFrame() {
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        bandTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout manager
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gridbag);
        // Создаем кнопки
        Localize locale = Localize.getInstance();
        btnPanel.add(createButton(gridbag, gbc, locale.getField("main.button.update"), LOAD));
        btnPanel.add(createButton(gridbag, gbc, locale.getField("main.button.add"), ADD));
        btnPanel.add(createButton(gridbag, gbc, locale.getField("main.button.edit"), EDIT));
        btnPanel.add(createButton(gridbag, gbc, locale.getField("main.button.delete"), DELETE));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.EAST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(bandTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем контакты
        loadBands();
    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
            // Перегрузка данных
            case LOAD:
                loadBands();
                break;
            // Добавление записи
            case ADD:
                addBand();
                break;
            // Исправление записи
            case EDIT:
                editBand();
                break;
            // Удаление записи
            case DELETE:
                deleteBand();
                break;
        }
    }

    // Загрухить список контактов
    private void loadBands() {
        // Обращаемся к классу для загрузки списка контактов
        List<Band> bands = bandController.findBands();
        // Создаем модель, которой передаем полученный список
        BandModel bm = new BandModel(bands);
        // Передаем нашу модель таблице - и она может ее отображать
        bandTable.setModel(bm);
    }

    // Добавление контакта
    private void addBand() {
        // Создаем диалог для ввода данных
        EditBandDialog ebd = new EditBandDialog();
        // Обрабатываем закрытие диалога
        saveBand(ebd);
    }

    // Редактирование контакта
    private void editBand() {
        // Получаем выделеннуб строку
        int sr = bandTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(bandTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные контакта по его ID
            Band band = bandController.getBand(id);
            // Создаем диалог для ввода данных и передаем туда контакт
            EditBandDialog ebd = new EditBandDialog(bandController.getBand(id));
            // Обрабатываем закрытие диалога
            saveBand(ebd);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    // Удаление контакта
    private void deleteBand() {
        // Получаем выделеннуб строку
        int sr = bandTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(bandTable.getModel().getValueAt(sr, 0).toString());
            // Удаляем контакт
            bandController.deleteBand(id);
            // перегружаем список контактов
            loadBands();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

    // Общий метод для добавления и изменения контакта
    private void saveBand(EditBandDialog ebd) {
        // Если мы нажали кнопку SAVE
        if (ebd.isSave()) {
            // Получаем контакт из диалогового окна
            Band band = ebd.getBand();
            if (band.getId() != null) {
                // Если ID у контакта есть, то мы его обновляем
                bandController.updateBand(band);
            } else {
                // Если у контакта нет ID - значит он новый и мы его добавляем
                bandController.addBand(band);
            }
            loadBands();
        }
    }
}

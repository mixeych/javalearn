package com.company.bandlist;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class EditBandDialog extends JDialog implements ActionListener
{
    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;


    private final JTextPane name = new JTextPane();

    private final JTextPane mainStar = new JTextPane();

    private final JTextPane membersNumber = new JTextPane();

    // Поле для хранения ID контакта, если мы собираемся редактировать
    // Если это новый контакт - cjntactId == null
    private Long bandId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;

    public EditBandDialog() {
        this(null);
    }

    public EditBandDialog(Band band) {
        // Убираем layout - будем использовать абсолютные координаты
        setLayout(null);

        // Выстраиваем метки и поля для ввода
        buildFields();
        // Если нам передали контакт - заполняем поля формы
        initFields(band);
        // выстариваем кнопки
        buildButtons();

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 450, 200);
        // Делаем форму видимой
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private void buildFields() {
        // Набор метки и поля для Имени
        JLabel lblName = new JLabel("Имя:");
        // Выравнивание текста с правой стороны
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        // Выставляем координаты метки
        lblName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        // Кладем метку на форму
        add(lblName);
        // Выставляем координаты поля
        name.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // Делаем "бордюр" для поля
        name.setBorder(BorderFactory.createEtchedBorder());
        // Кладем поле на форму
        add(name);

        JLabel lblMainStar = new JLabel("Лидер:");
        lblMainStar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMainStar.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblMainStar);
        mainStar.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        mainStar.setBorder(BorderFactory.createEtchedBorder());
        add(mainStar);

        // Набор метки и поля для Email
        JLabel lblMembersNumber = new JLabel("Кол-во участников:");
        lblMembersNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMembersNumber.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblMembersNumber);
        membersNumber.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        membersNumber.setBorder(BorderFactory.createEtchedBorder());
        add(membersNumber);
    }

    // Если нам епередали контакт - заполняем поля из контакта
    private void initFields(Band band) {
        if (band != null) {
            bandId = band.getId();
            name.setText(band.getName());
            membersNumber.setText(Integer.toString(band.getMembersNumber()));
            mainStar.setText(band.getMainStar());
        }
    }

    // Размещаем кнопки на форме
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // Если нажали кнопку SAVE (сохранить изменения) - запоминаем этой
        save = SAVE.equals(action);
        // Закрываем форму
        setVisible(false);
    }

    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }

    // Создаем контакт из заполенных полей, который можно будет записать
    public Band getBand() {
        Band band = new Band(bandId, name.getText(), mainStar.getText(), Integer.parseInt(membersNumber.getText()));
        return band;
    }
}

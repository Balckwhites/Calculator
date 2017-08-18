package cn.edu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends MouseAdapter {
    JFrame list;
    // Container con;
    JTextField show;
    JButton[] jbNum = new JButton[10];
    JPanel jpMain; // �����
    JPanel jpRight; // ���������Ҫ���ڴ��������͵Ⱥ�
    JPanel jpLight; // ����������ڴ������,����, ��.��
    JButton dight; // С����
    JButton sign; // ������
    JButton add; // �Ӻ�
    JButton sub; // ����
    JButton multiply; // �˺�
    JButton divide; // ����
    JButton power; // ����
    JButton cos; // cos
    JButton sin; // sin
    JButton ln; // ln
    JButton ce; // ���
    JButton equal; // ����
    JButton mod; // ȡ��
    JButton sqrt; // sqrt
    double sum = 0; // ��ʱ���
    boolean b = false; // ���������Ƿ񱻵��,�����Ƿ���֣�����ʵ����һ�ε����ťʱ���
    operator i = operator.un; // ��¼�Ⱥŷ����ǰĳһ������������������ʵ�����ӻ���������

    int op; // ��¼������

// ������һ����+-*/%^
    enum operator {
        add, sub, mul, div, mod, pow, sin, cos, sqrt, ln, un
    }

    void display() {
        // ���������ڣ����һ��Text��
        list = new JFrame("������");
        list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list.setSize(360, 230);
        list.setLocation(400, 300);
        list.setBackground(Color.LIGHT_GRAY); // ���ô��ڱ�����ɫ
        list.setResizable(false);

        list.setLayout(new FlowLayout(FlowLayout.CENTER));
        show = new JTextField(31);
        show.setHorizontalAlignment(JTextField.RIGHT); // �ı����������Ҷ���
        show.setEditable(false); // �ı��򲻿ɱ༭
        list.add(show);
        // ������岢���ò���
        jpMain = new JPanel();
        jpRight = new JPanel();
        jpLight = new JPanel();
        jpMain.setLayout(new GridLayout(1, 2));
        jpRight.setLayout(new GridLayout(4, 3, 3, 3));
        jpLight.setLayout(new GridLayout(4, 3, 3, 3));
        list.add(jpMain);
        jpMain.add(jpLight);
        jpMain.add(jpRight);
        // ����0~9��ť����
        for (int i = 9; i >= 0; i--) {
            jbNum[i] = new JButton(String.valueOf(i));
            jbNum[i].setForeground(Color.BLUE);
            jpLight.add(jbNum[i]);
            jbNum[i].addMouseListener(this);
        }
        add = new JButton("+");
        sub = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("/");
        power = new JButton("x^y");
        sin = new JButton("sin");
        cos = new JButton("cos");
        ln = new JButton("ln");
        ce = new JButton("CE");
        equal = new JButton("=");
        mod = new JButton("%");
        sqrt = new JButton("sqrt");
        jpRight.add(divide);
        jpRight.add(sqrt);
        jpRight.add(ln);
        jpRight.add(multiply);
        jpRight.add(sin);
        jpRight.add(mod);
        jpRight.add(sub);
        jpRight.add(cos);
        jpRight.add(ce);
        jpRight.add(add);
        jpRight.add(power);
        jpRight.add(equal);

        // �����а�ťע�������
        dight = new JButton(".");
        sign = new JButton("��");
        jpLight.add(sign);
        jpLight.add(dight);
        add.addMouseListener(this);
        sub.addMouseListener(this);
        multiply.addMouseListener(this);
        divide.addMouseListener(this);
        power.addMouseListener(this);
        sin.addMouseListener(this);
        cos.addMouseListener(this);
        ln.addMouseListener(this);
        ce.addMouseListener(this);
        equal.addMouseListener(this);
        mod.addMouseListener(this);
        sqrt.addMouseListener(this);
        dight.addMouseListener(this);
        sign.addMouseListener(this);
        list.setVisible(true);

    }

    public void mouseClicked(MouseEvent e) {
        // 0~9������
        if (e.getSource() == jbNum[0]) {
            input(0, e);
        }
        if (e.getSource() == jbNum[1]) {
            input(1, e);
        }
        if (e.getSource() == jbNum[2]) {
            input(2, e);
        }
        if (e.getSource() == jbNum[3]) {
            input(3, e);
        }
        if (e.getSource() == jbNum[4]) {
            input(4, e);
        }
        if (e.getSource() == jbNum[5]) {
            input(5, e);
        }
        if (e.getSource() == jbNum[6]) {
            input(6, e);
        }
        if (e.getSource() == jbNum[7]) {
            input(7, e);
        }
        if (e.getSource() == jbNum[8]) {
            input(8, e);
        }
        if (e.getSource() == jbNum[9]) {
            input(9, e);
        }

        // С����,�����ţ�CE,�Ⱥ�
        if (e.getSource() == dight) {
            if (show.getText().indexOf('.') == -1) {
                show.setText(show.getText() + ".");
            }

        }
        if (e.getSource() == sign) {
            if (show.getText().indexOf("-") == -1) {
                show.setText("-" + show.getText());
            } else {
                show.setText(show.getText().replace('-', '\0'));
            }

        }
        if (e.getSource() == ce) {
            show.setText("0");
            sum = 0;
            i = operator.un;
            b = false;
        }
        outer: if (e.getSource() == equal) {
            try {
                if (i == operator.un) {
                    b = true;
                } else {
                    if (i == operator.add) {
                        sum += Double.parseDouble(show.getText());

                    }
                    if (i == operator.sub) {
                        sum -= Double.parseDouble(show.getText());

                    }
                    if (i == operator.mul) {
                        sum *= Double.parseDouble(show.getText());

                    }
                    if (i == operator.div) {
                        if (Double.parseDouble(show.getText()) != 0) {
                            sum /= Double.parseDouble(show.getText());

                        } else {
                            show.setText("ERROR");
                            b = true;
                            sum = 0;
                            break outer; // ��ִ��trimIn()���� ��Ļ��ʾ����
                        }
                    }
                    if (i == operator.mod) {
                        sum %= Double.parseDouble(show.getText());

                    }
                    if (i == operator.pow) {
                        sum = Math.pow(sum, Double.parseDouble(show.getText()));

                    }
                    trimIn(sum);
                }
            } catch (Exception ex) {
                show.setText("ERROR");
                b = true;
                sum = 0;
            }

            sum = 0;
            i = operator.un;
            b = true;
        }
        // �Ӽ��˳�//��ָ����//ȡ��
        if (e.getSource() == add) {
            cal(i);
            i = operator.add;
            b = true;

        }
        if (e.getSource() == sub) {
            cal(i);
            i = operator.sub;
            b = true;

        }
        if (e.getSource() == multiply) {
            cal(i);
            i = operator.mul;
            b = true;

        }
        if (e.getSource() == divide) {
            cal(i);
            i = operator.div;
            b = true;

        }
        if (e.getSource() == mod) {
            cal(i);
            i = operator.mod;
            b = true;

        }
        if (e.getSource() == power) {
            cal(i);
            i = operator.pow;
            b = true;

        }

        // sqrt��sin,cos,ln
        try {
            if (show.getText() != "ERROR") {
                if (e.getSource() == sqrt) {
                    sum = Math.sqrt(Double.parseDouble(show.getText()));
                    trimIn(sum);
                    b = true;
                }
                if (e.getSource() == sin) {
                    sum = Math.sin(Double.parseDouble(show.getText()));
                    trimIn(sum);
                    b = true;
                }
                if (e.getSource() == cos) {
                    sum = Math.cos(Double.parseDouble(show.getText()));
                    trimIn(sum);
                    b = true;
                }
                if (e.getSource() == ln) {
                    sum = Math.log(Double.parseDouble(show.getText()));
                    trimIn(sum);
                    b = true;
                }
            }
        } catch (Exception ex) {
            show.setText("ERROR");
            b = true;
        }
    }

    // ����������������ݺ�ȡģ�ķ���
    public void cal(operator i) {
        try {
            if (show.getText() != "ERROR") {
                if (i == operator.un) {
                    sum = Double.parseDouble(show.getText());
                }
                if (i == operator.add) {
                    sum += Double.parseDouble(show.getText());
                    trimIn(sum);
                }
                if (i == operator.sub) {
                    sum -= Double.parseDouble(show.getText());
                    trimIn(sum);
                }
                if (i == operator.mul) {
                    sum *= Double.parseDouble(show.getText());
                    trimIn(sum);
                }
                if (i == operator.div) {
                    if (Double.parseDouble(show.getText()) != 0) {
                        sum /= Double.parseDouble(show.getText());
                        trimIn(sum);
                    } else {
                        //����0�󣬰�һ����������
                        show.setText("ERROR");
                        sum = 0;
                        b = true;
                        i=operator.un;
                    }
                }
                //ȡ��
                if (i == operator.mod) {
                    sum %= Double.parseDouble(show.getText());
                    trimIn(sum);
                }
                //��ָ����
                if (i == operator.pow) {
                    sum = Math.pow(sum, Double.parseDouble(show.getText()));
                    trimIn(sum);
                }
            }
        } catch (Exception ex) {
            show.setText("ERROR");
            b = true;
        }
    }

    // �����������
    public void input(int i, MouseEvent e) {
        if (b == true) {
            show.setText(String.valueOf(i));
            b = false;
        } else {
            //�ж�0��.���������ʱ����ĵ�
            if (show.getText().indexOf('0') == 0 && e.getSource() != dight) {
                show.setText(String.valueOf(i));
            } else {
                show.setText(show.getText() + String.valueOf(i));
            }
        }
    }

    // sum����ʾ��������ȥ��С�����0
    public void trimIn(double sum) {
        // if (show.getText().indexOf('.') != -1 &&
// show.getText().endsWith("0")) {
// show.setText((String.valueOf(sum).substring(0, String.valueOf(sum)
// .indexOf('.'))));
// } else
        if (String.valueOf(sum).indexOf('.') != -1
                && String.valueOf(sum).endsWith("0")) {
            show.setText((String.valueOf(sum).substring(0, String.valueOf(sum)
                    .indexOf('.'))));

        } else if (Double.isNaN(sum)) {
            show.setText("ERROR");         //�� ������ʱ ��Ļ��ʾ���󣬲���sum����0 �������UN
            b = true;
            sum = 0;
            i = operator.un;
        } else if (Double.isInfinite(sum)) {
            show.setText("ERROR");          //����infinite(���޴�)ʱ��ʾ����SUM��0�������UN 
            b = true;
            sum = 0;
            i = operator.un;
        } else {
            show.setText(String.valueOf(sum));
        }
    }

    /**
     * @param args
*/
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Calculator c = new Calculator();
        c.display();
    }

}
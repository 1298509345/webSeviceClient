package example;

import com.HelloWorldServiceLocator;
import com.HelloWorld_PortType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        myUI();
    }

    public static void test(String[] form) {
        try {
            HelloWorldServiceLocator locator = new HelloWorldServiceLocator();
            HelloWorld_PortType service = locator.getHelloWorld();
            String str = service.sayHelloWorldFrom(form);
            System.out.println(str);
        } catch (javax.xml.rpc.ServiceException ex) {
            ex.printStackTrace();
        } catch (java.rmi.RemoteException ex) {
            ex.printStackTrace();
        }
    }


    public static void myUI() {
        JFrame frame = new JFrame("简易邮件发送");
        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponent(panel);
        frame.setVisible(true);
    }

    public static void placeComponent(JPanel panel) {
        panel.setLayout(null);
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的组件位置，由 width 和 height 指定组件的大小。
         */
        JLabel jLabel = new JLabel("收件人邮箱");
        jLabel.setBounds(10, 20, 80, 25);
        panel.add(jLabel);

        JTextField addrContent = new JTextField(50);
        addrContent.setBounds(100, 20, 165, 25);
        panel.add(addrContent);

        JLabel jLabel2 = new JLabel("主题");
        jLabel2.setBounds(10, 50, 80, 25);
        panel.add(jLabel2);

        JTextField title = new JTextField(20);
        title.setBounds(100, 50, 165, 25);
        panel.add(title);

        JLabel jLabel3 = new JLabel("邮件内容");
        jLabel3.setBounds(10, 100, 80, 25);
        panel.add(jLabel3);

        JTextArea mailContent = new JTextArea("请输入内容", 7, 30);
        mailContent.setBounds(100, 100, 200, 50);
        panel.add(mailContent);

        JButton send = new JButton("发送");
        send.setBounds(100, 200, 80, 25);
        panel.add(send);

        send.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String receiver = addrContent.getText();
          String topic = title.getText();
          String content = mailContent.getText();
          List<String> list = new ArrayList<>();
          list.add(receiver);
          list.add(topic);
          list.add(content);
          String[] info = new String[3];
          info[0] = list.get(0);
          info[1] = list.get(1);
          info[2] = list.get(2);
          String[] addr = list.get(0).split(";");
          for (int i = 0; i < addr.length; i++) {
            if (validateEmailAddress(info[0])) info[0] = addr[i];
            else {
              JOptionPane.showMessageDialog(null, "邮箱地址错误", "tips", JOptionPane.ERROR_MESSAGE);
              break;
            }
            test(info);
          }


        }
      });
    }

    public static boolean validateEmailAddress(String email) {
        if (email == null)
            return false;
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main extends JFrame {
    private SortLinkedList ll;
    private JTextArea listView;  // Used to view the current contents of the list
    private JTextField cmdTextField;
    private JTextField resultTextField;
    private JTextArea availableCmdsTextArea;

    // Help 
    String[] availableCmds = {
        "add index stringElement",
        "add stringElement",
        "remove stringElement",
        "remove index",
        "sort",
        "reverse",
        "isempty"
    };

    public Main() {
        ll = new SortLinkedList();
        listView = new JTextArea(10, 30);
        cmdTextField = new JTextField(20);
        resultTextField = new JTextField(20);
        availableCmdsTextArea = new JTextArea(5, 30);

        listView.setEditable(false);
        availableCmdsTextArea.setEditable(false);
        resultTextField.setEditable(false);

        // Build the UI
        setLayout(new BorderLayout());

        // Command result controls
        JPanel cmdResultPane = new JPanel();
        cmdResultPane.add(new JLabel("Command Result:"));
        cmdResultPane.add(resultTextField);

        // Enter command controls
        JPanel enterCmdPane = new JPanel();
        enterCmdPane.add(new JLabel("Enter Command:"));
        enterCmdPane.add(cmdTextField);

        // Label for list view TextArea
        JPanel listViewPane = new JPanel();
        listViewPane.add(new JLabel("List View:"));

        // Label for available commands TextArea
        JPanel availableCmdsPane = new JPanel();
        availableCmdsPane.add(new JLabel("Available Commands:"));

        // Add everything to the main frame
        add(availableCmdsPane, BorderLayout.NORTH);
        add(new JScrollPane(availableCmdsTextArea), BorderLayout.CENTER);
        add(cmdResultPane, BorderLayout.SOUTH);
        add(listViewPane, BorderLayout.WEST);
        add(new JScrollPane(listView), BorderLayout.EAST);
        add(enterCmdPane, BorderLayout.SOUTH);

        // Put list of available commands in appropriate text area
        for (String s : availableCmds) {
            availableCmdsTextArea.append(s + "\n");
        }

        // Usual JFrame stuff
        setTitle("Swing Sort Reverse Linked List Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Add ActionListener for cmd text field
        cmdTextField.addActionListener(new CmdTextListener());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    /**
     * Private inner class that responds to commands that
     * the user types into the command entry text field.
     */
    private class CmdTextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmdText = cmdTextField.getText();
            Scanner sc = new Scanner(cmdText);
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("add")) {
                if (sc.hasNextInt()) {
                    int index = sc.nextInt();
                    String element = sc.next();
                    ll.add(index, element);
                } else {
                    String element = sc.next();
                    ll.add(element);
                }
                listView.setText(ll.toString());
                return;
            }
            if (cmd.equalsIgnoreCase("remove")) {
                if (sc.hasNextInt()) {
                    int index = sc.nextInt();
                    String res = ll.remove(index);
                    resultTextField.setText(String.valueOf(res));
                } else {
                    String element = sc.next();
                    boolean res = ll.remove(element);
                    resultTextField.setText(String.valueOf(res));
                }
                listView.setText(ll.toString());
                return;
            }
            if (cmd.equalsIgnoreCase("isempty")) {
                resultTextField.setText(String.valueOf(ll.isEmpty()));
                return;
            }
            if (cmd.equalsIgnoreCase("sort")) {
                ll.sort();
                listView.setText(ll.toString());
                return;
            }
            if (cmd.equalsIgnoreCase("reverse")) {
                ll.reverse();
                listView.setText(ll.toString());
                return;
            }
        }
    }
}
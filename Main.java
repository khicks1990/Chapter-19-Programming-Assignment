import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application
{
    private SortLinkedList ll;
    private TextArea  listView;  // Used to view the current contents of the list
    private TextField cmdTextField;
    private TextField resultTextField;
    private TextArea availableCmdsTextArea;

    // Help 
    String [ ] availableCmds = 
                 {
                  "add index stringElement",
                  "add stringElement",
                  "remove stringElement",
                  "remove index",
                  "sort",
                  "reverse",
                  "isempty"
                  };

    @Override
    public void start(Stage stage) throws Exception 
    {
       ll = new SortLinkedList(); 
       listView = new TextArea();      
       cmdTextField = new TextField();
       resultTextField = new TextField();
       availableCmdsTextArea = new TextArea();

       listView.setEditable(false);
       availableCmdsTextArea.setEditable(false);
       resultTextField.setEditable(false);

       // Build the UI
       VBox outerVBox = new VBox(10);
       outerVBox.setPadding(new Insets(10));
       outerVBox.setAlignment(Pos.CENTER);

       // Command result controls
       HBox cmdResultPane = new HBox(10);
       cmdResultPane.getChildren().addAll(new Label("Command Result"), resultTextField);

       // Enter command controls
       HBox enterCmdPane = new HBox(10);
       enterCmdPane.getChildren().addAll(new Label("Enter Command"), cmdTextField);

       // Label for list view TextArea
       HBox listViewHBox = new HBox();
       Label listViewLabel = new Label("List View:");
       listViewHBox.getChildren().add(listViewLabel);

       // Label for available commands TextArea
       HBox availableCmdsHBox = new HBox();
       Label availableCmdsLabel = new Label("Available Commands:");
       availableCmdsHBox.getChildren().add(availableCmdsLabel);

       // Add everything to the outer VBox
       outerVBox.getChildren().addAll(
                                      availableCmdsHBox,
                                      availableCmdsTextArea,               
                                      cmdResultPane, 
                                      listViewHBox,
                                      listView,
                                      enterCmdPane                                      
                                     );
       // Put list of available commands in appropriate text area
       for (String s : availableCmds)
       {
           availableCmdsTextArea.appendText(s + "\n");
       }

       // Usual Scene and Stage stuff
       Scene scene = new Scene(outerVBox);
       stage.setScene(scene);
       stage.setTitle("Java FX Sort Reverse Linked List Demo");
       stage.show();       

       // Add Event Handler for cmd text field
       cmdTextField.setOnAction(new CmdTextListener());
    }

    public static void main(String [] args)
    {
        launch(args);
    }

     /**
       Private inner class that responds to commands that
       the user types into the command entry text field.     
    */

    private class CmdTextListener  implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent evt)
        {
            String cmdText = cmdTextField.getText();
            Scanner sc = new Scanner(cmdText);
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("add"))
            {
                if (sc.hasNextInt())
                {
                    int index = sc.nextInt();
                    String element = sc.next();
                    ll.add(index, element);                
                }
                else
                {
                    String element = sc.next();
                    ll.add(element);                
                }
                listView.setText(ll.toString());

                return;
            } 
            if (cmd.equalsIgnoreCase("remove"))
            {
                if (sc.hasNextInt())
                {
                    int index = sc.nextInt();
                    String res = ll.remove(index);
                    resultTextField.setText(String.valueOf(res));              
                }
                else
                {
                    String element = sc.next();
                    boolean res = ll.remove(element);
                    resultTextField.setText(String.valueOf(res));
                }
                listView.setText(ll.toString());

                return;
            } 
            if (cmd.equalsIgnoreCase("isempty"))
            {
                resultTextField.setText(String.valueOf(ll.isEmpty()));
                return;
            }
            if (cmd.equalsIgnoreCase("sort"))
            {
                ll.sort();
                listView.setText(ll.toString());
                return;
            }
            if (cmd.equalsIgnoreCase("reverse"))
            {
                ll.reverse();
                listView.setText(ll.toString());
                return;
            }
        }
    }
}
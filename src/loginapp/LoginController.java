package loginapp;

import Admin.AdminController;
import Students.StudentsController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    public void initialize (URL url, ResourceBundle rb) {

        if(this.loginModel.isDatabseConnected()) {
            this.dbstatus.setText("Database connected");
        } else {
            this.dbstatus.setText("Not connected to the database");
        }

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }

    public void Login(ActionEvent event) {
        System.out.println("Login event works");

        System.out.println(this.username.getText());
        System.out.println(this.password.getText());
        System.out.println(((option)this.combobox.getValue()).toString());

        try {
            if(this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.combobox.getValue()).toString())) {
                System.out.print("isLogin");
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();

                switch (((option)this.combobox.getValue()).toString()) {
                    case "admin":
                        adminLogin();
                        break;
                    case "student":
                        studentLogin();
                        break;
                }
            }
            else {
                this.loginStatus.setText("Wrong Credentials");
                System.out.print("isLogin not working");
            }

        }catch(Exception localException) {

        }
    }

    public void studentLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/students/student.fxml").openStream());

            StudentsController studentsController = (StudentsController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/admin.fxml").openStream());

            AdminController adminController = (AdminController) adminLoader.getController();

            Scene adminScene = new Scene(adminroot);
            adminStage.setScene(adminScene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

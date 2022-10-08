package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class databaseUtil {
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE username = ? and password = ?";
    public static void changeScene(ActionEvent event,String fxmlFile,String title,String username,String email ,String institution){
        Parent root=null;
        if (username != null && email != null && institution != null) {
        try {
            FXMLLoader loader = new FXMLLoader(databaseUtil.class.getResource(fxmlFile));
            root = loader.load();
            logInController logInController = loader.getController();
            logInController.setUserInformation(username, institution);
        }catch (IOException e){
            e.printStackTrace();
        }
        }else{
            try{ FXMLLoader loader = new FXMLLoader(databaseUtil.class.getResource(fxmlFile));
                root = loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void  validate(ActionEvent event,String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","nidhi@5100");

        // Step 2:Create a statement using connection object
        psInsert =connection.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
        psInsert.setString(1,username);
        psInsert.setString(2,password);
        psInsert.executeUpdate();
        changeScene(event,"logged-in.fxml","Welcome!",username,null,null);
    }

     public static void signUpUser(ActionEvent event, String username, String email, String institution,String password ){
         Connection connection = null;
         PreparedStatement psInsert = null;
         PreparedStatement psCheckUserExists = null;
         ResultSet resultSet = null;

         try{
             connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","nidhi@5100");
             psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username=?");
             psCheckUserExists.setString(1,username);

             resultSet = psCheckUserExists.executeQuery();

             if (resultSet.isBeforeFirst()){
                 System.out.println("user already exists!");
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("you cannot use this username.");
                 alert.show();
             }else{
                 psInsert =connection.prepareStatement("INSERT INTO users(username,password,email,institution) VALUES(?, ?, ?,?)");
                 psInsert.setString(1,username);
                 psInsert.setString(2,password);
                 psInsert.setString(3,email);
                 psInsert.setString(4,institution);
                 psInsert.executeUpdate();

                 changeScene(event,"logged-in.fxml","Welcome!",username, email,institution);
             }
         }catch(SQLException e){
             e.printStackTrace();
         } finally{
           if (resultSet!= null){
               try{
                   resultSet.close();
               }catch (SQLException e){
                   e.printStackTrace();
               }
           }
           if (psCheckUserExists != null){
               try {
                   psCheckUserExists.close();
               }catch (SQLException e){
                   e.printStackTrace();
               }
           }
           if (psInsert!= null){
               try{
                   psInsert.close();
               }catch (SQLException e){
                e.printStackTrace();
               }
           }
           if (connection !=null){
               try{
                   connection.close();
               }catch(SQLException e){
                   e.printStackTrace();
               }
           }
         }
     }

     public static void logInUser(ActionEvent event,String username,String password){
Connection connection=null;
PreparedStatement preparedStatement=null;
ResultSet resultSet = null;
try{
    connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","nidhi@5100");
    preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE username= ? AND password= ? ");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

    resultSet =preparedStatement.executeQuery();
    if (resultSet.next()) {
        System.out.println("user is there in database");
    }
    else{
        System.out.println("user is not there in database");
        System.out.println("Go to signup page for registration");
    }
//    if (!resultSet.isBeforeFirst()){
//        System.out.println("user not found in database!");
//        Alert alert=new Alert(Alert.AlertType.ERROR);
//        alert.setContentText("Provided credentials are incorrect");
//        alert.show();
//    }else {
//        while (resultSet.next()){
//            String retrievedPassword=resultSet.getString("password");
//            String retrievedEmail=resultSet.getString("email");
//            String retrievedInstitution=resultSet.getString("institution");
//            if (retrievedPassword.equals(password)){
//                changeScene(event,"logged_in.fxml","Welcome!",username,null,null);
//            } else {
//                System.out.println("Passwords did not match!");
//                Alert alert =new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("the provided credentials are incorrect!");
//                alert.show();
//            }
//        }
//    }
}catch(SQLException e){
    e.printStackTrace();
} finally {
  if (resultSet !=null) {
      try {
          resultSet.close();
      }catch(SQLException e){
          e.printStackTrace();
      }
  }
  if (preparedStatement !=null){
      try{
          preparedStatement.close();
      }catch (SQLException e){
          e.printStackTrace();
      }
  }
  if (connection!=null){
      try{
          connection.close();
      }catch (SQLException e){
          e.printStackTrace();
      }
  }
}
}
}


module com.company.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.company.weather to javafx.fxml;
    exports com.company.weather;
}
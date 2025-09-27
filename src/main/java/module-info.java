module com.example.gestion_etudiant {
    requires javafx.controls;
    requires jakarta.persistence; // Jakarta Persistence
    requires org.hibernate.orm.core;
    requires java.sql;
    requires javafx.fxml;

    opens com.example.gestion_etudiant to javafx.fxml;
    opens com.example.gestion_etudiant.model to org.hibernate.orm.core;
    exports com.example.gestion_etudiant;
}
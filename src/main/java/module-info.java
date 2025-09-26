module com.example.gestion_etudiant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;

    opens com.example.gestion_etudiant to javafx.fxml;
    exports com.example.gestion_etudiant;
}
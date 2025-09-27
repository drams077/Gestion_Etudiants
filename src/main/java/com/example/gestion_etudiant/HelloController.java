package com.example.gestion_etudiant;

import com.example.gestion_etudiant.dao.PersonneDAO;
import com.example.gestion_etudiant.model.Personne;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField ageField;

    @FXML
    private TableView<Personne> tableView;

    @FXML
    private TableColumn<Personne, String> nomColumn;

    @FXML
    private TableColumn<Personne, String> prenomColumn;

    private ObservableList<Personne> personnesData;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés
        nomColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNom()));
        prenomColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPrenom()));

        // Charger les données depuis la base
        personnesData = FXCollections.observableArrayList(PersonneDAO.findAll());
        tableView.setItems(personnesData);
    }

    @FXML
    private void onSaveClick(){

        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String age = ageField.getText().trim();

        if(!nom.isEmpty() && !prenom.isEmpty() && !age.isEmpty()){

            try {

                Personne personne = new Personne();
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setAge(age);

                PersonneDAO.save(personne);

                System.out.println("✅ Personne enregistrée avec succès !");

            } catch (Exception e) {
                System.out.println("Non Non Personne non enregistré");
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void onUpdateClick(){
        Personne personneSelected = tableView.getSelectionModel().getSelectedItem();
        if (personneSelected != null && !nomField.getText().isEmpty() && !prenomField.getText().isEmpty() && !ageField.getText().isEmpty()){
            try {
                personneSelected.setNom(nomField.getText());
                personneSelected.setPrenom(prenomField.getText());
                personneSelected.setAge(ageField.getText());

                PersonneDAO.update(personneSelected);
                tableView.refresh();
                //clearFields();

                System.out.println("✅ Personne modifiée avec succès !");

            } catch (Exception e) {
                System.out.println("Non Non Personne non modifiée");
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    private void onDeleteClick(){
        Personne personneSelected = tableView.getSelectionModel().getSelectedItem();
        if (personneSelected!=null &&  !nomField.getText().isEmpty() && !prenomField.getText().isEmpty() && !ageField.getText().isEmpty()){
            try {

                PersonneDAO.delete(personneSelected);
                tableView.refresh();
                //clearFields();

                System.out.println("✅ Personne supprimée avec succès !");

            } catch (Exception e) {
                System.out.println("Non Non Personne non supprimé");
                throw new RuntimeException(e);

        }
        }
    }
}

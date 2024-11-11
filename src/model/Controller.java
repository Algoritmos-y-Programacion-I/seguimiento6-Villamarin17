package model;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Species> speciesList;

    public Controller() {
        this.speciesList = new ArrayList<>();
    }

    public boolean registerSpecies(String name, String scientificName, String type, boolean hasFlowers, boolean hasFruits, double maxHeight, boolean isMigratory, double maxWeight) {
        Species species;

        switch (type.toUpperCase()) {
            case "FLORA":
                species = new Flora(name, scientificName, hasFlowers, hasFruits, maxHeight);
                break;
            case "FAUNA":
                species = new Fauna(name, scientificName, isMigratory, maxWeight);
                break;
            default:
                return false;
        }

        speciesList.add(species);
        return true;
    }

    public String showSpeciesList() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < speciesList.size(); i++) {
            msg.append((i + 1)).append(". ").append(speciesList.get(i).getName()).append("\n");
        }
        return msg.toString();
    }

    public boolean editSpecies(int index, String newName, String newScientificName) {
        if (index >= 0 && index < speciesList.size()) {
            Species species = speciesList.get(index);
            species.setName(newName);
            species.setScientificName(newScientificName);
            return true;
        }
        return false;
    }

    public boolean deleteSpecies(int index) {
        if (index >= 0 && index < speciesList.size()) {
            speciesList.remove(index);
            return true;
        }
        return false;
    }

    public String getSpeciesInfo(int index) {
        if (index >= 0 && index < speciesList.size()) {
            return speciesList.get(index).toString();  // assuming each species' toString() method provides detailed info
        }
        return "Species not found";
    }
}


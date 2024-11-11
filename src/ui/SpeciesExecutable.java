package ui;

import java.util.Scanner;
import model.Controller;

public class SpeciesExecutable {

    private Scanner sc;
    private Controller controller;

    public static void main(String[] args) {
        SpeciesExecutable exe = new SpeciesExecutable();
        exe.showMainMenu();
    }

    public SpeciesExecutable() {
        sc = new Scanner(System.in);
        controller = new Controller();
    }

    public void showMainMenu() {
        System.out.println("Welcome to Icesi Species");

        boolean stopFlag = false;
        while (!stopFlag) {
            System.out.println("\nType an option");
            System.out.println("1. Register a Species");
            System.out.println("2. Edit a Species");
            System.out.println("3. Delete a Species");
			System.out.println("4. View a Species");
            System.out.println("0. Exit");

            int mainOption = sc.nextInt();
            sc.nextLine();

            switch (mainOption) {
                case 1:
                    registerSpecies();
                    break;
                case 2:
                    editSpecies();
                    break;
                case 3:
                    deleteSpecies();
                    break;
				case 4:
					viewSpecies();
					break;
                case 0:
                    System.out.println("Thanks for using our system");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("You must type a valid option");
                    break;
            }
        }
    }

    public void registerSpecies() {
        System.out.println("Type the new Species' name: ");
        String name = sc.nextLine();

        System.out.println("Type the new Species' scientific name: ");
        String scientificName = sc.nextLine();

        System.out.println("Is this species Flora or Fauna?");
        String type = sc.nextLine();

        if (type.equalsIgnoreCase("Flora")) {
            System.out.println("Does it have flowers? (true/false): ");
            boolean hasFlowers = sc.nextBoolean();

            System.out.println("Does it have fruits? (true/false): ");
            boolean hasFruits = sc.nextBoolean();

            System.out.println("Enter the maximum height: ");
            double maxHeight = sc.nextDouble();

            if (controller.registerSpecies(name, scientificName, "Flora", hasFlowers, hasFruits, maxHeight, false, 0)) {
                System.out.println("Flora species registered successfully");
            } else {
                System.out.println("Error, Flora species couldn't be registered");
            }

        } else if (type.equalsIgnoreCase("Fauna")) {
            System.out.println("Is it migratory? (true/false): ");
            boolean isMigratory = sc.nextBoolean();

            System.out.println("Enter the maximum weight: ");
            double maxWeight = sc.nextDouble();

            if (controller.registerSpecies(name, scientificName, "Fauna", false, false, 0, isMigratory, maxWeight)) {
                System.out.println("Fauna species registered successfully");
            } else {
                System.out.println("Error, Fauna species couldn't be registered");
            }
        } else {
            System.out.println("Invalid type. Must be Flora or Fauna.");
        }
    }

    public void editSpecies() {
        System.out.println("Which Species do you want to edit?");
        String query = controller.showSpeciesList();

        if (!query.equals("")) {
            System.out.println(query);
            System.out.print("Enter the species number to edit: ");
            int index = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("Enter the new name: ");
            String newName = sc.nextLine();

            System.out.print("Enter the new scientific name: ");
            String newScientificName = sc.nextLine();

            if (controller.editSpecies(index, newName, newScientificName)) {
                System.out.println("Species edited successfully");
            } else {
                System.out.println("Error, species couldn't be edited");
            }
        } else {
            System.out.println("There aren't any species registered yet");
        }
    }

    public void deleteSpecies() {
        System.out.println("Which Species do you want to delete?");
        String query = controller.showSpeciesList();

        if (!query.equals("")) {
            System.out.println(query);
            System.out.print("Enter the species number to delete: ");
            int index = sc.nextInt() - 1;

            if (controller.deleteSpecies(index)) {
                System.out.println("Species deleted successfully");
            } else {
                System.out.println("Error, species couldn't be deleted");
            }
        } else {
            System.out.println("There aren't any species registered yet");
        }
    }

	public void viewSpecies() {
        System.out.println("Which Species do you want to view?");
        String query = controller.showSpeciesList();

        if (!query.equals("")) {
            System.out.println(query);
            System.out.print("Enter the species number to view: ");
            int index = sc.nextInt() - 1;
            sc.nextLine();

            String speciesInfo = controller.getSpeciesInfo(index);
            System.out.println(speciesInfo);
        } else {
            System.out.println("There aren't any species registered yet");
        }
    }
}
package emrs;

import java.util.ArrayList;

public class MedicalHistory {

    private String name;
    private ArrayList<String> diagnosis = new ArrayList<>();
    private ArrayList<String> tests = new ArrayList<>();
    private ArrayList<String> drugs = new ArrayList<>();

    public MedicalHistory(String name, String diagnosis, String tests, String drugs) {
        this.name = name;
        this.diagnosis.add(diagnosis);
        this.tests.add(tests);
        this.drugs.add(drugs);
    }

    public ArrayList<String> getTests() {
        return tests;
    }

    public ArrayList<String> getDrugs() {
        return drugs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getDiagnosis() {
        return diagnosis;
    }

    public void updateDiagnosis(String newDiagnosis) {
        if (newDiagnosis != null && !newDiagnosis.isEmpty()) {
            this.diagnosis.clear();
            this.diagnosis.add(newDiagnosis);
        } else {
            throw new IllegalArgumentException("Diagnosis cannot be empty.");
        }
    }

    public void updateTests(String newTest) {
        if (newTest != null && !newTest.isEmpty()) {
            this.tests.clear();
            this.tests.add(newTest);
        } else {
            throw new IllegalArgumentException("Test cannot be empty.");
        }
    }

    public void updateDrugs(String newDrugs) {
        if (newDrugs != null && !newDrugs.isEmpty()) {
            this.drugs.clear();
            this.drugs.add(newDrugs);
        } else {
            throw new IllegalArgumentException("Drugs cannot be empty.");
        }
    }


    public void updateMedicalHistory(String newDiagnosis, String newTest, String newDrugs) {
        updateDiagnosis(newDiagnosis);
        updateTests(newTest);
        updateDrugs(newDrugs);
    }


    public String getDetails() {
        return """
                Diagnosis: %s
                Test: %s
                Drugs: %s
                """.formatted(
                diagnosis.isEmpty() ? "None" : String.join(", ", diagnosis),
                tests.isEmpty() ? "None" : String.join(", ", tests),
                drugs.isEmpty() ? "None" : String.join(", ", drugs)
        );
    }

}

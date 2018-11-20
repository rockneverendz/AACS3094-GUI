package control;

import da.ProgrammeDA;
import domain.Programme;

public class MaintainProgrammeControl {

    private ProgrammeDA programmeDA;

    public MaintainProgrammeControl() {
        programmeDA = new ProgrammeDA();
    }

    public Programme selectRecord(String code) {
        return programmeDA.getRecord(code);
    }

    public void addRecord(Programme programme) {
        programmeDA.addRecord(programme);
    }

    public void updateRecord(Programme programme) {
        programmeDA.updateRecord(programme);
    }

    public void deleteRecord(Programme programme) {
        programmeDA.deleteRecord(programme);
    }
}

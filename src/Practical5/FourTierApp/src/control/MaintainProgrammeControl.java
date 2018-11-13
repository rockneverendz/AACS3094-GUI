package control;

import da.ProgrammeDA;
import domain.Programme;

public class MaintainProgrammeControl {

    private ProgrammeDA progDA;

    public MaintainProgrammeControl() {
        progDA = new ProgrammeDA();
    }

    public Programme selectRecord(String code) {
        return progDA.getRecord(code);
    }

}

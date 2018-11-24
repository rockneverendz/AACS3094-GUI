
import java.util.ArrayList;

public class SoftwareCheckOut {

    private String name;
    private String id;
    private String programme;
    private ArrayList<String> softwareList;

    public SoftwareCheckOut() {
    }

    public SoftwareCheckOut(String name, String id, String programme, ArrayList<String> softwareList) {
        this.name = name;
        this.id = id;
        this.programme = programme;
        this.softwareList = softwareList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getProgramme() {
        return programme;
    }

    public ArrayList<String> getSoftwareList() {
        return softwareList;
    }
}
package core;

public class LoadData {
    private String stringForFile;

    public LoadData(String stringForFile) {
        this.stringForFile = stringForFile;
    }

    public LoadData() {

    }

    @Override
    public String toString() {
        return "LoadData{" +
                "stringForFile='" + stringForFile + '\'' +
                '}';
    }

    public String getStringForFile() {
        return stringForFile;
    }

    public void setStringForFile(String stringForFile) {
        this.stringForFile = stringForFile;
    }
}

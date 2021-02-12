package core;

public class LoadDelete {
    private String letterDelete;
    private String typeDelete;
    private Integer sizeDelete;

    public LoadDelete() {
    }

    public LoadDelete(String letterDelete, String typeDelete, Integer sizeDelete) {
        this.letterDelete = letterDelete;
        this.typeDelete = typeDelete;
        this.sizeDelete = sizeDelete;
    }

    public String getLetterDelete() {
        return letterDelete;
    }

    public void setLetterDelete(String letterDelete) {
        this.letterDelete = letterDelete;
    }

    public String getTypeDelete() {
        return typeDelete;
    }

    public void setTypeDelete(String typeDelete) {
        this.typeDelete = typeDelete;
    }

    public Integer getSizeDelete() {
        return sizeDelete;
    }

    public void setSizeDelete(Integer sizeDelete) {
        this.sizeDelete = sizeDelete;
    }

    @Override
    public String toString() {
        return "LoadDelete{" +
                "letterDelete='" + letterDelete + '\'' +
                ", typeDelete='" + typeDelete + '\'' +
                ", sizeDelete=" + sizeDelete +
                '}';
    }
}

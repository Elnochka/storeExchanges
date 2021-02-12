package core;

public class LoadQuery {
    private String letterQuery;
    private String typeQuery;
    private Integer sizeQuery;

    public LoadQuery(String letterQuery, String typeQuery, Integer sizeQuery) {
        this.letterQuery = letterQuery;
        this.typeQuery = typeQuery;
        this.sizeQuery = sizeQuery;
    }

    public LoadQuery(String letterQuery, String typeQuery) {
        this.letterQuery = letterQuery;
        this.typeQuery = typeQuery;
    }

    public LoadQuery() {

    }

    public String getLetterQuery() {
        return letterQuery;
    }

    public void setLetterQuery(String letterQuery) {
        this.letterQuery = letterQuery;
    }

    public String getTypeQuery() {
        return typeQuery;
    }

    public void setTypeQuery(String typeQuery) {
        this.typeQuery = typeQuery;
    }

    public Integer getSizeQuery() {
        return sizeQuery;
    }

    public void setSizeQuery(Integer sizeQuery) {
        this.sizeQuery = sizeQuery;
    }

    @Override
    public String toString() {
        return "LoadQuery{" +
                "letterQuery=" + letterQuery +
                ", typeQuery='" + typeQuery + '\'' +
                ", sizeQuery=" + sizeQuery +
                '}';
    }
}

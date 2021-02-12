package core;

public class LoadUpdate {
    private String letterUpdate;
    private Integer priceUpdate;
    private Integer sizeUpdate;
    private String typeUpdate;

    public LoadUpdate(String letterUpdate, Integer priceUpdate, Integer sizeUpdate, String typeUpdate) {
        this.letterUpdate = letterUpdate;
        this.priceUpdate = priceUpdate;
        this.sizeUpdate = sizeUpdate;
        this.typeUpdate = typeUpdate;
    }

    public LoadUpdate() {

    }

    @Override
    public String toString() {
        return "LoadUpdate{" +
                "letterUpdate=" + letterUpdate +
                ", priceUpdate=" + priceUpdate +
                ", sizeUpdate=" + sizeUpdate +
                ", typeUpdate='" + typeUpdate + '\'' +
                '}';
    }

    public String getLetterUpdate() {
        return letterUpdate;
    }

    public void setLetterUpdate(String letterUpdate) {
        this.letterUpdate = letterUpdate;
    }

    public Integer getPriceUpdate() {
        return priceUpdate;
    }

    public void setPriceUpdate(Integer priceUpdate) {
        this.priceUpdate = priceUpdate;
    }

    public Integer getSizeUpdate() {
        return sizeUpdate;
    }

    public void setSizeUpdate(Integer sizeUpdate) {
        this.sizeUpdate = sizeUpdate;
    }

    public String getTypeUpdate() {
        return typeUpdate;
    }

    public void setTypeUpdate(String typeUpdate) {
        this.typeUpdate = typeUpdate;
    }

}

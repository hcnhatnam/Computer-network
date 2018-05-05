package stringExtractor;


public class GPS {
    private Double KinhDo;
    private Double ViDo;

    GPS(Double KD,Double VD){
        this.KinhDo=KD;
        this.ViDo=VD;

    }


    public Double getKinhDo() {
        return KinhDo;
    }

    public Double getViDo() {
        return ViDo;
    }

}

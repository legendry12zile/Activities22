package estacio.com.estacio_nov15;


public class StudGrades {
    String FName , LName;
    Long Score;

    public table(String FName, String LName, Long Score) {
        this.FName = FName;
        this.LName = LName;
        this.Score = Score;
    }

    public String getFName(){
    return FName;
    }

     public String getLName(){
    return LName;
    }
    public Long getScore(){
    return Score;
    }
}

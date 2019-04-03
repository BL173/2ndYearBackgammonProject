public class Move {
    public int from;
    public int to;
    public int from2;
    public int to2;
    public int diceNumber;
    public Boolean hit1=false;
    public Boolean hit2=false;
    public Boolean skipSecondMove=true;
    public String index;
    public Move(int f, int t, int d, Boolean h1){
        from = f;
        to=t;
        diceNumber=d;
        hit1=h1;
    }

    public void secondMove(int f2, int t2, Boolean h2){
        from2=f2;
        to2=t2;
        hit2=h2;
        //index=moveIndexToString();
        skipSecondMove=false;
    }
    public String toString(){
        String moveString=index+" ('";
        if(from==25){
            moveString+="BAR-"+to;
        }else if (to==0){
            moveString+=from+"-OFF";
        }else {
            moveString+=from+"-"+to;
        }
        if(hit1==true){
            moveString+="*'  ";
        }else{
            moveString+="'  ";
        }
        if(skipSecondMove==true){
            moveString+="SKIP)\n";
        }else {
            if(from2==25){
                moveString+="'BAR-"+to2;
            }else if (to2==0){
                moveString+="'"+from2+"-OFF";
            }else {
                moveString+="'"+from2+"-"+to2;
            }
            if(hit2==true){
                moveString+="*') \n";
            }else{
                moveString+="') \n";
            }
        }
        return moveString;
    }
    public Boolean repeatedMove(){
        if(from ==from2 && to ==to2){
            return true;
        }
        return false;
    }
}

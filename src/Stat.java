public  class Stat{

    private int currentValue;
    private int maxValue;
    private String nameOfValue;


    public Stat(int currentValue,int maxValue,String nameOfValue ){
        this.currentValue=currentValue;
        this.maxValue=maxValue;
        this.nameOfValue=nameOfValue;

    }

    public int getCurrentValue(){
        return currentValue;
    }

    public int getMaxValue(){
        return maxValue;
    }

    public int increaseValue(){
        currentValue = maxValue;
        System.out.println("The currenent value is "+currentValue);
        return currentValue;
    }
    public int decreaseValue(){
        if (currentValue > 0) {
            currentValue--;
        }
        else{
            System.out.println("You can not decrease this value anymore ! ");
        }
        return currentValue;
    }

    public float getPercentage(){
        float percentage  = (float) (currentValue*100)/maxValue;
        return percentage;

    }
    @Override
    public String toString(){
        return (nameOfValue + " : " + currentValue + "/" + maxValue );
    }

    public int upgradeMax(int boostAmount){
        maxValue += boostAmount;
        return maxValue;
    }

}
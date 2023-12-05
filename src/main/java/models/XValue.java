package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XValue implements Serializable {
    
    private boolean boolXMinus2;
    
    private boolean boolXMinus15;
    
    private boolean boolXMinus1;
    
    private boolean boolXMinus05;
    
    private boolean boolX0;
    
    private boolean boolX05;
    
    private boolean boolX1;
    
    private boolean boolX15;
    
    private boolean boolX2;
    public boolean isBoolXMinus1() {
        return boolXMinus1;
    }

    public boolean isBoolXMinus2() {
        return boolXMinus2;
    }

    public boolean isBoolX05() {
        return boolX05;
    }

    public boolean isBoolXMinus05() {
        return boolXMinus05;
    }

    public boolean isBoolX0() {
        return boolX0;
    }

    public boolean isBoolXMinus15() {
        return boolXMinus15;
    }

    public boolean isBoolX1() {
        return boolX1;
    }

    public boolean isBoolX2() {
        return boolX2;
    }

    public boolean isBoolX15() {
        return boolX15;
    }
    
    public void setBoolXMinus1(boolean boolXMinus1) {
        this.boolXMinus1 = boolXMinus1;
    }

    public void setBoolX0(boolean boolX0) {
        this.boolX0 = boolX0;
    }

    public void setBoolX1(boolean boolX1) {
        this.boolX1 = boolX1;
    }

    public void setBoolX2(boolean boolX2) {
        this.boolX2 = boolX2;
    }

    public void setBoolX05(boolean boolX05) {
        this.boolX05 = boolX05;
    }

    public void setBoolX15(boolean boolX15) {
        this.boolX15 = boolX15;
    }

    public void setBoolXMinus2(boolean boolXMinus2) {
        this.boolXMinus2 = boolXMinus2;
    }

    public void setBoolXMinus05(boolean boolXMinus05) {
        this.boolXMinus05 = boolXMinus05;
    }

    public void setBoolXMinus15(boolean boolXMinus15) {
        this.boolXMinus15 = boolXMinus15;
    }

    public List<Double> setAllX() {
        List<Double> allX = new ArrayList<>();
        if (boolXMinus2) {
            allX.add(-2D);
        }
        if (boolXMinus15) {
            allX.add(-1.5D);
        }
        if (boolXMinus1) {
            allX.add(-1D);
        }
        if (boolXMinus05) {
            allX.add(-0.5D);
        }
        if (boolX0) {
            allX.add(0D);
        }
        if (boolX05) {
            allX.add(0.5D);
        }
        if (boolX1) {
            allX.add(1D);
        }
        if (boolX15) {
            allX.add(1.5D);
        }
        if (boolX2) {
            allX.add(2D);
        }
        return allX;
    }
}

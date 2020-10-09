// methode 1 : 
// C:\Users\zhouy\Desktop\IFT3913\test\src\main\java\org\jfree\chart\axis\LogAxis.java


    private double calculateValueNoINF(double log) {
        double result = calculateValue(log);
        if (Double.isInfinite(result)) {
            result = Double.MAX_VALUE;
        }
        if (result <= 0.0) {
            result = Double.MIN_VALUE;
        }
        return result;
    }


// methode 2 : 
    // C:\Users\zhouy\Desktop\IFT3913\test\src\main\java\org\jfree\chart\axis\LogAxis.java


    @Override
    public void resizeRange(double percent, double anchorValue) {
        resizeRange2(percent, anchorValue);
    }

// methode 3 : 
// C:\Users\zhouy\Desktop\IFT3913\test\src\main\java\org\jfree\chart\axis\NumberTickUnitSource.java

    
@Override
    public TickUnit getLargerTickUnit(TickUnit unit) {
        TickUnit t = getCeilingTickUnit(unit);
        if (t.equals(unit)) {
            next();
            t = new NumberTickUnit(getTickSize(), getTickLabelFormat(), 
                    getMinorTickCount());
        }
        return t; 
    }
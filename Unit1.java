import java.util.*;
public class Unit1 {
    //<editor-fold desc="Main">
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the mass of the water in grams");
        double m = scan.nextDouble();
        System.out.println("Enter the starting temperature of the water in Celsius");
        double s = scan.nextDouble();
        if (s<-273.14){
            s = 273.14;
        }
        System.out.println("Enter the final temperature of the water in Celsius");
        double f = scan.nextDouble();
        if (f<-273.14){
            f = 273.14;
        }
        String initialPhase = "liquid";
        if(s<0){
            initialPhase = "solid";
        }
        if(s>100){
            initialPhase = "vapor";
        }
        String finalPhase = "liquid";
        if(f<0){
            finalPhase = "solid";
        }
        if(f>100){
            finalPhase = "vapor";
        }
        System.out.println("Mass: "+m+"g\nStarting Temperature: "+s+" C\nInitial Phase: "+initialPhase+" \nFinal Temperature: "+f+" C\nFinal Phase: "+finalPhase);
        boolean endothermic = false;
        if(f >= s){
            endothermic = true;
        }
        double heatEnergy = 0;
        if(initialPhase.equalsIgnoreCase("solid")) {
            heatEnergy += tempChangeSolid(m, s, f, finalPhase, endothermic);
            if(!finalPhase.equalsIgnoreCase("solid")){
                heatEnergy += fusion(m,endothermic);
                heatEnergy += tempChangeLiquid(m, 0, f, finalPhase, endothermic);
            }
            if(finalPhase.equalsIgnoreCase("vapor")){
                heatEnergy += vaporization(m, endothermic);
                heatEnergy += tempChangeVapor(m, 100, f, finalPhase, endothermic);
            }
        }
        if(initialPhase.equalsIgnoreCase("liquid")){
            heatEnergy += tempChangeLiquid(m, s, f, finalPhase, endothermic);
            if(finalPhase.equalsIgnoreCase("solid")){
                heatEnergy += fusion(m, endothermic);
                heatEnergy += tempChangeSolid(m, 0 , f, finalPhase, endothermic);
            }
            if(finalPhase.equalsIgnoreCase("vapor")){
                heatEnergy += vaporization(m ,endothermic);
                heatEnergy += tempChangeVapor(m, 100, f, finalPhase, endothermic);
            }
        }
        if(initialPhase.equalsIgnoreCase("vapor")){
            heatEnergy += tempChangeVapor(m, s, f, finalPhase, endothermic);
            if(!finalPhase.equalsIgnoreCase("vapor")){
                heatEnergy += vaporization(m, endothermic);
                heatEnergy += tempChangeLiquid(m, 100, f, finalPhase, endothermic);
            }
            if(finalPhase.equalsIgnoreCase("solid")){
                heatEnergy += fusion(m,endothermic);
                heatEnergy += tempChangeVapor(m, 0, f, finalPhase, endothermic);
            }
        }
        System.out.println("Total Heat Energy: "+heatEnergy+ "kJ");
    }
    //</editor-fold>
    public static double tempChangeSolid(double m, double s, double f, String endPhase, boolean endothermic){
        if (!endPhase.equals("solid"))
            f = 0;
            double energyChange = round(m*0.002108*(f-s));
        if(endothermic)
            System.out.println("Heating (solid): "+ energyChange +"kJ");
        else
            System.out.println("Cooling (solid): "+ energyChange +"kJ");
        return energyChange;
    }
    public static double tempChangeLiquid(double m, double s, double f, String endPhase, boolean endothermic){
        if (endPhase.equals("vapor"))
            f = 100;
        if(endPhase.equals("solid"))
            f=0;
        double energyChange = round(m*0.004184*(f-s));
        if(endothermic)
            System.out.println("Heating (liquid): "+ energyChange +"kJ");
        else
            System.out.println("Cooling (liquid): "+ energyChange +"kJ");
        return energyChange;
    }
    public static double tempChangeVapor(double m, double s, double f, String endPhase, boolean endothermic){
        if (!endPhase.equals("vapor"))
            f = 100;
        double energyChange = round(m*0.001996*(f-s));
        if(endothermic)
            System.out.println("Heating (vapor): "+ energyChange +"kJ");
        else
            System.out.println("Cooling (vapor): "+ energyChange +"kJ");
        return energyChange;
    }
    public static double fusion(double m, boolean endothermic) {
        double energyChange;
        if (endothermic) {
            energyChange = round(m * 0.333);
            System.out.println("Melting: " + energyChange + "kJ");
        }
        else {
            energyChange = round(m* -0.333);
            System.out.println("Freezing: " + energyChange + "kJ");
        }
        return energyChange;
    }
    public static double vaporization(double m, boolean endothermic) {
        double energyChange;
        if (endothermic) {
            energyChange = round(m * 2.257);
            System.out.println("Evaporation: " + energyChange + "kJ");
        }
        else {
            energyChange = round(m* -2.257);
            System.out.println("Condensation: " + energyChange + "kJ");
        }
        return energyChange;
    }
    public static double round(double x) {
        x*=1000;
        if(x>0)
            return (int)(x+0.5)/1000.0;
        else
            return (int)(x-0.5)/1000.0;
    }
}

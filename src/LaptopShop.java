import javax.swing.JOptionPane; //imported JOptionPane so dialogues could be shown
import java.util.Scanner; //Scanner imported to obtain console inputs

/**
 *  Author: Salman
 *
 *  This program asks user for staff name, tier, no of laptops sold of each category, validates the results,
 *  calculates commissions, bonuses, qualification for promotion and monthly salary.
 */
public class LaptopShop {

    /**
     Main handles the driver code for the program. Inputs are taken,
     processed and outputted in this method.
     */
    public static void main (String[] args){

        //constants declared
        final double MAX_VALID_SALARY = 10000;
        final int MAX_VALID_UNITS = 50;
        final int SUPER_BONUS = 1000;

        String loop;
        Scanner kbd = new Scanner(System.in); //Scanner object created

        //keeps running (looping) the program until user instructs to end
        do {

            //required declarations made
            double totPay = 0, totsales = 0, bonus = 0, baseSalary, supBonus = 0, commission = 0;
            int basicLap, midLap, highLap;
            String oldTier, newTier, endMessage;

            String name = (JOptionPane.showInputDialog("Enter name: ")).toUpperCase(); //asks for name

            char tier = ((JOptionPane.showInputDialog("Enter tier " +
                    "(L for Low, M for Middle or H for High): ")).toUpperCase()).charAt(0); //asks for tier
            //assigns literals to oldTier based on previous input and handles invalid entry
            if(tier =='L') {
                oldTier = "Low";
            } else if (tier =='M') {
                oldTier = "Middle";
            } else if (tier =='H') {
                oldTier = "High";
            } else {
                tier = 'L';
                oldTier = "Low";
                JOptionPane.showMessageDialog(null,"Invalid Entry\nLow Tier has been " +
                        "assigned to " + name);
            }

            //asks and validates base salary
            boolean salValidation = true;
            do {
                baseSalary = Double.parseDouble(JOptionPane.showInputDialog("Enter base salary: "));
                if (baseSalary>=0 && baseSalary<=MAX_VALID_SALARY){
                    salValidation = false;
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Entry");
                }
            }while(salValidation);

            //asks and validates no. of basic laptops sold
            boolean bLapValidation = true;
            do {
                basicLap = Integer.parseInt(JOptionPane.showInputDialog("Enter no. of basic laptops sold: "));
                if (basicLap >= 0 && basicLap <= MAX_VALID_UNITS){
                    commission += 50*basicLap;
                    totsales += 450.9*basicLap;
                    bLapValidation = false;
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Entry");
                }
            }while(bLapValidation);

            //asks and validates no. of mid-range laptops sold
            boolean mLapValidation = true;
            do {
                midLap = Integer.parseInt(JOptionPane.showInputDialog("Enter no. of mid-range laptops sold: "));
                if (midLap >= 0 && midLap <= MAX_VALID_UNITS){
                    commission += 100*midLap;
                    totsales += 850.5*midLap;
                    mLapValidation = false;
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Entry");
                }
            }while(mLapValidation);

            //asks and validates no. of high-end laptops sold
            boolean hLapValidation = true;
            do {
                highLap = Integer.parseInt(JOptionPane.showInputDialog("Enter no. of high-end laptops sold: "));
                if (highLap >= 0 && highLap <= MAX_VALID_UNITS){
                    commission += 150*highLap;
                    totsales += 1350.95*highLap;
                    hLapValidation = false;
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Entry");
                }
            }while(hLapValidation);

            //assigns bonus based on the total value of laptops sold
            if (totsales > 2500 && totsales <= 5500){
                bonus += 0.01*totsales;
            } else if (totsales > 5500 && totsales <= 10500){
                bonus += 75 + (totsales-5500)*0.02;
            }else if (totsales > 10500 && totsales <= 13500){
                bonus += 125 + (totsales-10500)*0.03;
            } else if (totsales > 13500){
                bonus += 375;
            }

            //decides whether the employee is eligible for promotion or super bonus
            if (commission >= 0.75*baseSalary){
                if (tier == 'L'){
                    newTier = "Middle";
                    endMessage = "Congrads, you got promoted to the Middle tier";
                } else if (tier == 'M'){
                    newTier = "High";
                    endMessage = "Congrads, you got promoted to the High tier";
                } else {
                    newTier = "High";
                    endMessage = "Congrads, you got the SUPER BONUS since you were already at the High tier";
                    supBonus += SUPER_BONUS;
                }
            } else {
                newTier = oldTier;
                endMessage = "Sorry, you didn't get promoted to the next tier this month";
            }

            totPay += baseSalary + commission + bonus + supBonus; //calculates monthly pay


            System.out.printf("\nSalesperson:\t%s\nStarting Tier:\t%s\nNew Tier:\t\t%s\n" +
                            "Laptops Sold:\t%d basic, %d mid-range, %d high-end\n\nBase Salary" +
                            ":\t$%,10.2f\nCommission:\t\t$%,10.2f\nBonus:\t\t\t$%,10.2f\nSuper Bonus:\t$%,10.2f" +
                            "\n\t\t\t\t-----------\nMonthly Pay:\t$%,10.2f\n\n%s\n",name , oldTier, newTier
                    , basicLap, midLap, highLap, baseSalary, commission, bonus, supBonus,
                    totPay, endMessage); //displays all the previously calculated information


            System.out.println("\nWould you like to generate payroll report for another salesperson\n" +
                    "Please Enter YES to do so or enter any other phrase to exit: "); //asks to terminate or continue
            loop = (kbd.nextLine()).toUpperCase();

        }while(loop.equals("YES"));

        kbd.close(); //closes scanner object
        System.exit(0); //exits program

    }
}


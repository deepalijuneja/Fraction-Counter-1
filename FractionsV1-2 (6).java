/*
Questions? Answers
1 .Can you complete this without using arrays? What is the least number of variables you can use to solve this problem?
Ans: Yes, I can complete this without using arrays too. 10 variables would be needed (min).
2. Can you use just one array to solve this? What would the data type be of that array?
Ans: Just one array won’t be able to help us here especially because we need to also initialize the array and store different values like split, reduced etc.
3. What does it mean to make a class so another class is inside (or part of) the first class,so that it is composed of other data types?  What does "composition" mean in that case?  How is it done?
Ans: Composition is a more efficient way to reuse code unlike inheritance which follows an is-a relationship. We use composition by creating a class that keeps a reference to an object, which can then be used in a single or more of its methods.
4. What are some solutions to the reduction problem other than Euclid's GCD algorithm?
Ans: I believe we can use the great common divisor method apart from the Euclid’s GCD algorithm.
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *The purpose of this program is read fraction from a text file (fractions.txt) and shows how many times a fraction occurs. As all the input is consumed, the program will list the fraction’s simplified form.
 * 
 * @author Deepali Juneja
 * @version 3 April 2020
 */

public class FractionsV1{
    private static int str = 0;
    /**
     *  Main method that uses the fractions.txt file and read the no. of lines so calculations can be done. 
     * 
     * @param args
     */
    public static void main(String[] args) {
      File f= new File("fractions.txt");
        
        try {
             Scanner readFile = new Scanner(f);
            while (readFile.hasNextLine()) {
                str++;
                readFile.nextLine();// Reading whole fraction from the text file.
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry! Your file could not be found");
        }
       
        String[] totalF = new String[str];// array that contains all the fractions as strings.
        try {
            Scanner readFile = new Scanner(f);
            for (int i = 0; i < str; i++) {
                totalF[i] = readFile.nextLine();
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry we could not find your file");
        }

        resultFractions(simplestForms(
                totalF));
                                
    }

    /**
     * In this method, the fraction is converted into the simplest form. 
     * @param totalF - the initial fraction
     * @return simplestForms - simplest form of the fraction
     */
    public static String[] simplestForms(String[] totalF) {
        String[] simplestForms = new String[str];
        String[] split = new String[2];
        int greatestCD, num_, den_;
        for (int i = 0; i < str; i++) {
            split = totalF[i].split("/");
            num_ = Integer.parseInt(split[0]);
            den_ = Integer.parseInt(split[1]);
            greatestCD = greatestCD(num_, den_);
            num_ /= greatestCD;
            den_ /= greatestCD;
            simplestForms[i] = num_ + "/" + den_;
        }
        return simplestForms;
    }

    /** 
     * In the method below, we calculator the greatest common divisor.
     * @param FNum - as the name suggests is the numerator var  
     * @param FDum - as the name suggests is the denominator var
     * @return greatest common division (greatestCD)
     */
    public static int greatestCD(int FNum, int FDum) {
        
        if (FDum == 0)// we know division by zero isn't possible so this step makes sure that doesn't happen
            return FNum;
        return greatestCD(FDum, FNum % FDum);
    }

    /**
     * In the method below, the fraction is seperated into FNum (numerator) and FDum (denominator)
     * @param totalF : simplest form
     */
    public static void resultFractions(String[] totalF) {
         int[] FNum = new int[str];
        int[] FDum = new int[str];
        String[] split = new String[2];
        for (int i = 0; i < str; i++) {
            split = totalF[i].split("/");
            FNum[i] = Integer.parseInt(split[0]);
            FDum[i] = Integer.parseInt(split[1]);
        }
        int num_, den_;
        int result = 0;
        boolean[] occurance = new boolean[str];// checks for any reprints
        for (int i = 0; i < str; i++) {
            num_ = FNum[i];
            den_ = FDum[i];
            for (int j = 0; j < str; j++) {
                if (!occurance[j]) {
                    if (den_ == FDum[j]) {
                        if (num_ == FNum[j]) {
                            result++;
                            occurance[j] = true;
                        }
                    }
                }
            }
            if (result != 0) {
                System.out.println(num_ + "/" + den_ + " \thas a result of: " + result);
            }
            result = 0;
        }
    }
}
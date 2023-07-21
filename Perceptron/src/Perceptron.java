import java.util.Random;
import java.util.Scanner;

public class Perceptron
{
    private static int bias=1;              //int declaration for bias variable
    private static int input1;               //global input variable declaration
    private static int input2;
    private static int input3;
    private static int input4;
    private static double weight1;               //global weight variable declarations
    private static double weight2;
    private static double weight3;
    private static double weight4;
    private static double weight5;
    private static double error1;              //global error variable declarations
    private static double error2;
    private static double error3;
    private static double error4;
    private static double error5;
    private static double S;                      //global summation variable declaration

    private static final double learningRate=0.1;         //learning rate set to 0.1

    public static void main(String[] args)
    {
        Random rand=new Random();            //imported random class to be used for random numbers
        Scanner in= new Scanner(System.in);         //imported scanner class to be used for read-in input
        System.out.println("Please enter the number representing the color of the top left pixel (-1 for black and 1 for white): ");
        input1=in.nextInt();
        System.out.println("PLease enter the number representing the color of the top right pixel: ");
        input2=in.nextInt();
        System.out.println("Please enter the number representing the color of the bottom left pixel: ");
        input3=in.nextInt();
        System.out.println("Please enter the number representing the color of the bottom right pixel: ");
        input4=in.nextInt();
        in.nextLine();
        System.out.println("Your inputs are: ");
        System.out.println(""+input1+" "+input2+" "+input3+" "+input4+"");
        weight1=rand.nextDouble(-1,1);          //weights set to be between -1 and 1
        weight2=rand.nextDouble(-1,1);
        weight3=rand.nextDouble(-1,1);
        weight4=rand.nextDouble(-1,1);
        weight5=rand.nextDouble(-1,1);
        int finalSum;          //final summation variable declaration
        int iter=0;               //iter variable set to track the amount of steps taken for perceptron to reach its goal


        do
        {
            iter++;          //iterate up for every pass
            S=guess(bias,input1,input2,input3,input4,weight1,weight2,weight3,weight4,weight5);  //calls the guess method to compute the weighted summation and puts it into S variable
            finalSum=stepFunction(S);        //calls the step function method to assign the weighted sum either as a 1 or -1
            System.out.println("");
            System.out.println("The current weighted sum of the inputs is: "+S);   //shows the current weighted sum of inputs
            System.out.println("");
            if(answer()==finalSum)                    //looks to see if final outcome matches the current guess
            {
                System.out.println("It took "+iter+" iteration(s) to get the correct output.\n");   //shows num of iterations
                if(finalSum==1)      //checks if finalSum equals 1
                {
                    System.out.println("The correct identification was 1, as there was either 2,3, or 4 white pixels in the image.");
                    System.out.println("The system correctly identified the image as bright.");
                    break;
                }
                else if(finalSum==-1)       //checks if the finalSum equals -1
                    System.out.println("The correct identification was -1, as there was either 0 or 1 white pixels in the image.");
                    System.out.println("The system correctly identified the image as dark.");
                break;
            }
            else
                error1=error(learningRate,finalSum,bias);         //calls on the error function to cacluate the error values for each input
                error2=error(learningRate,finalSum,input1);
                error3=error(learningRate,finalSum,input2);
                error4=error(learningRate,finalSum,input3);
                error5=error(learningRate,finalSum,input4);

                weight1=bias+error1;            //assigns new values to the weights that brings the perceptron closer to the goal
                weight2+=error2;
                weight3+=error3;
                weight4+=error4;
                weight5+=error5;
                System.out.println("The new weights are: \nBias Weight: "+weight1+" W1: "+weight2+" W2: "+weight3+" W3: "+weight4+" W4: "+weight5+".");  //shows the weights that will be checked on the next iteration
                System.out.println("Starting next iteration.");
                System.out.println("");
        } while(answer()!=finalSum);           //loops while the current guess does not equal the target state

    }

    public static double guess(int bias, int inp1, int inp2, int inp3,int inp4, double w1, double w2, double w3, double w4, double w5)  //method used to calculate the weighted sum of the inputs
    {
        return (bias*w1)+(inp1*w2)+(inp2*w3)+(inp3*w4)+(inp4*w5);      //returns the double value of the summed inputs/bias
    }

    public static int answer()              //all possible cases for a 2X2 image with black and white square possibilities, returns the target state
    {
        if(input1==1&&input2==1&&input3==1&&input4==1)    //checks for certain states based off the given parameters of the assignment and returns corresponding values
        {
            return 1;
        }
        else if(input1==-1&&input2==1&&input3==1&&input4==1)
        {
            return 1;
        }
        else if(input1==-1&&input2==-1&&input3==1&&input4==1)
        {
            return 1;
        }
        else if(input1==-1&&input2==-1&&input3==-1&&input4==1)
        {
            return -1;
        }
        else if(input1==-1&&input2==-1&&input3==-1&&input4==-1)
        {
            return -1;
        }
        else if(input1==1&&input2==-1&&input3==1&&input4==1)
        {
            return 1;
        }
        else if(input1==1&&input2==1&&input3==-1&&input4==1)
        {
            return 1;
        }
        else if(input1==1&&input2==1&&input3==1&&input4==-1)
        {
            return 1;
        }
        else if(input1==-1&&input2==1&&input3==-1&&input4==1)
        {
            return 1;
        }
        else if(input1==-1&&input2==1&&input3==1&&input4==-1)
        {
            return 1;
        }
        else if(input1==1&&input2==-1&&input3==-1&&input4==1)
        {
            return 1;
        }
        else if(input1==1&&input2==-1&&input3==1&&input4==-1)
        {
            return 1;
        }
        else if(input1==1&&input2==-1&&input3==-1&&input4==-1)
        {
            return -1;
        }
        else if(input1==-1&&input2==1&&input3==-1&&input4==-1)
        {
            return -1;
        }
        else if(input1==1&&input2==1&&input3==-1&&input4==-1)
        {
            return 1;
        }
        else
            return -1;
    }

    public static int stepFunction(double s)          //implements the step function by assigning positive values a +1 and negative values a -1, assumes 0 is positive
    {
        if(s>=0)          //checks the sign of the inputted double
        {
            return 1;       //returns 1 if positive
        }
        else
            return -1;         //returns -1 if negative
    }

    public static double error(double lr,int s,int inp)  //error function that uses the learning rate to calculate the error between current state and goal state
    {
        return lr*(answer()-s)*inp;          //uses the difference between the goal state and the current state, as well as passing through each input into this method, to multiply with the learning rate and move closer to the goal state
    }                                        //will be used to calculate the new weights

}

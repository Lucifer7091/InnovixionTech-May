
import java.util.Scanner;
public class calculator 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        double result=0;
        boolean flag=true;
        while(true)
        {
            System.out.print("Enter operator (+, -, *, /,%,^): ");
            char ch=sc.next().charAt(0);
            System.out.println("Enter the first number");
            double num1=sc.nextDouble();
            System.out.println("Enter the second number");
            double num2=sc.nextDouble();
            switch(ch)
            {
                case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) 
                {
                    result = num1 / num2;
                } else 
                {
                    System.out.println("Error: Division by zero is not allowed.");
                    flag =false;
                }
                break;
                case '%':
                result = num1 % num2;
                break;
                case '^':
                result = Math.pow(num1, num2);
                break;
            default:
                System.out.println("Error: Invalid operator.");
                flag=false;
            }
            if(flag)
            {
                System.out.println("The result is "+result);
            }
            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String response = sc.next().toLowerCase();
            if (response.equals("no")) {
                break;
            }
        }
    }
}

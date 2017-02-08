package notes;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * > Exception
 ** > Exception is an event, which occurs during the
 ** execution of a program, that disrupts the normal flow
 ** of the program's instruction.
 *
 * > Two types of exceptions: checked vs unchecked
 ** > Checked Exception (Requires extra attention.)
 *** > Describes a problem that is likely to happen
 *** at times, no matter how careful you are.
 **** > ex. Unexpected end of file
 ** > Unchecked Exception (Does not need to be dealt with.)
 *** > The exception is your fault. You should have
 *** anticipated the exception and been able to deal with it.
 **** > ex. NullPointerException/IndexOutOfBoundException
 */
public class TryCatch {
    public void getAge() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.printf("What is your age?: ");
                int age = in.nextInt();
                in.nextLine();
                System.out.printf("What is your age?: ");
                String name = in.nextLine();
                System.out.printf("Name: %s, Age: %d", name, age);
                break;
            }
            catch (InputMismatchException e) {
                System.out.printf("Y u do dis.\n");
            }
        }
    }

    public static void main(String[] args) {
        TryCatch tc = new TryCatch();
        tc.getAge();
    }
}

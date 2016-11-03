/**
 *
 * This is a JAVA program created by Gordon Quach for Alyssa Herbst!
 *
 */

import java.util.*;

public class Compliment {
    public static void main(String[]args){
        Scanner feels = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Alyssa, how are you feeling today?");
            System.out.println("__________________________________");
            System.out.println("1. Sad...");
            System.out.println("2. Tired...");
            System.out.println("3. Pretty good!");
            System.out.println("4. Best day ever! Let me tell you about it.");
            System.out.println("\t");
            choice = feels.nextInt();
        }
        while(choice != 1 && choice != 2 && choice != 3 && choice !=4);
        switch(choice)
        {
            case 1:
                System.out.println("1. I'm sorry, just tell me how I can make it better! P.S. - You are gorgeous!");
                break;
            case 2:
                System.out.println("2. Shh... just rest, sweetheart! Remind me to give you a nice massage later, beautiful!");
                break;
            case 3:
                System.out.println("3. That's awesome. Tell me about your day, dear! By the way, I hope you know that you are beautiful and gorgeous as always!");
                break;
            case 4:
                System.out.println("4. This is why I love you. Tell away, my love! <3 ");
                break;

        }
    }
}
package com.company;

public class Main {


    public static final String[] keyRows = {	"qwertyuiop",
            "asdfghjkl ",
            "^zxcvbnm ^",
            "   #####  "};

    public static Keyboard kb = new Keyboard(keyRows);

    public static void findPathToEnd(String str, Keyboard kb) {
        int startIndex = 0;
        char nextChar = str.charAt(startIndex);
        Hand leftHand, rightHand;
        if (Character.isUpperCase(nextChar)) {
            nextChar = Character.toLowerCase(nextChar);
            leftHand = new Hand(0, 2, "left");
            rightHand = new Hand(kb.kb.get(nextChar).x, kb.kb.get(nextChar).y,
                    "right");
            System.out.println("^: Use " + leftHand);
            System.out.println(nextChar + ": Use " + rightHand);
            startIndex++;
        } else {
            leftHand = new Hand(kb.kb.get(nextChar).x, kb.kb.get(nextChar).y,
                    "left");
            System.out.println(nextChar + ": Use " + leftHand);
            nextChar = str.charAt(++startIndex);
            rightHand = new Hand(kb.kb.get(nextChar).x, kb.kb.get(nextChar).y,
                    "right");
            System.out.println(nextChar + ": Use " + rightHand);
            startIndex++;
        }
        int totalEffort = 0;
        for (int i = startIndex; i < str.length(); i++) {
            nextChar = str.charAt(i);
            int leftHandEffort = kb.getEffort(leftHand, nextChar);
            int rightHandEffort = kb.getEffort(rightHand, nextChar);
            if (Character.isUpperCase(nextChar)) {
                int leftHandToShift = kb.getEffort(leftHand, '^');
                int rightHandToShift = kb.getEffort(rightHand, '^');
                if (leftHandToShift+rightHandEffort < rightHandToShift+leftHandEffort) {
                    leftHand.moveTo(kb.getClosestCoord(leftHand, '^'));
                    rightHand.moveTo(kb.getClosestCoord(rightHand, nextChar));
                    printMovement('^', leftHand, leftHandToShift);
                    printMovement(nextChar, rightHand, rightHandEffort);
                    totalEffort += leftHandToShift + rightHandEffort;
                } else {
                    rightHand.moveTo(kb.getClosestCoord(rightHand, '^'));
                    leftHand.moveTo(kb.getClosestCoord(leftHand, nextChar));
                    printMovement('^', rightHand, rightHandToShift);
                    printMovement(nextChar, leftHand, leftHandEffort);
                    totalEffort += rightHandToShift + leftHandEffort;
                }
            } else {
                if (leftHandEffort < rightHandEffort) {
                    leftHand.moveTo(kb.getClosestCoord(leftHand, nextChar));
                    printMovement(nextChar, leftHand, leftHandEffort);
                    totalEffort += leftHandEffort;
                } else {
                    rightHand.moveTo(kb.getClosestCoord(rightHand, nextChar));
                    printMovement(nextChar, rightHand, rightHandEffort);
                    totalEffort += rightHandEffort;
                }
            }
        }
        System.out.println("Total effort: " + totalEffort);
    }

    private static void printMovement(char c, Hand hand, int effort) {
        System.out.println(c + ": Use " + hand + " (Effort: " + effort + ")");
    }

    public static void main(String[] args) {
	
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            findPathToEnd(args[i], kb);
            System.out.println();
        }
    }
}

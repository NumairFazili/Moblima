package View;

public class Boundary {

    public static void Display(String[] str) {
        int count = 0;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + String.valueOf(count++) + " : " + str[i]);
        System.out.println(-1 + " : " + "Exit");
    }

    public static void DisplaySeating() {

    }
}


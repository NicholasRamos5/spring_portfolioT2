package com.nighthawk.spring_portfolio.mvc.lightboard;

import lombok.Data;
import java.util.Scanner;

@Data  // Annotations to simplify writing code (ie constructors, setters)
public class LightBoard {
    private Light[][] lights;

    /* Initialize LightBoard and Lights */
    public LightBoard(int numRows, int numCols) {
        this.lights = new Light[numRows][numCols];
        // 2D array nested loops, used for initialization
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                lights[row][col] = new Light();  // each cell needs to be constructed
            }
        }
    }

    /* Output is intended for API key/values */
    public String toString() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // data
                "{" + 
                "\"row\": " + row + "," +
                "\"column\": " + col + "," +
                "\"light\": " + lights[row][col] +   // extract toString data
                "}," ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1) + "]";
		return outString;
    }

    /* Output is intended for Terminal, effects added to output */
    public String toTerminal() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // reset
                "\033[m" +
                
                // color
                "\033[38;2;" + 
                lights[row][col].getRed() + ";" +  // set color using getters
                lights[row][col].getGreen() + ";" +
                lights[row][col].getBlue() + ";" +
                lights[row][col].getEffect() + "m" +
                lights[row][col].getOn() + ";" +
                // data, extract custom getters
                "{" +
                "\"" + "RGB\": " + "\"" + lights[row][col].getRGB() + "\"" +
                "," +
                "\"" + "Effect\": " + "\"" + lights[row][col].getEffectTitle() + "\"" +
                "," +
                "\"" + "on\": " + "\"" + lights[row][col].getOn() + "\"" +
                "," +
                "}," +
                // newline
                "\n" ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 2) + "\033[m" + "]";
		return outString;
    }

    /* Output is intended for Terminal, draws color palette */
    public String toColorPalette() {
        // block sizes
        final int ROWS = 5;
        final int COLS = 5;

        // Build large string for entire color palette
        String outString = "";
        // find each row
        for (int row = 0; row < lights.length; row++) {
            // repeat each row for block size
            for (int i = 0; i < ROWS; i++) {
                // find each column
                for (int col = 0; col < lights[row].length; col++) {
                    // repeat each column for block size
                    for (int j = 0; j < COLS; j++) {
                        // print single character, except at midpoint print color code
                        String c = (i == (int) (ROWS / 2) && j == (int) (COLS / 2) ) 
                            ? lights[row][col].getRGB()
                            : (j == (int) (COLS / 2))  // nested ternary
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : " ";

                        outString += 
                        // reset
                        "\033[m" +
                        
                        // color
                        "\033[38;2;" + 
                        lights[row][col].getRed() + ";" +
                        lights[row][col].getGreen() + ";" +
                        lights[row][col].getBlue() + ";" +
                        "7m" +

                        // color code or blank character
                        c +

                        // reset
                        "\033[m";
                    }
                }
                outString += "\n";
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString += "\033[m";
		return outString;
    }

    public void setColor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Red Value");
        Short red = scanner.nextShort();
        System.out.println("Enter Green Value");
        Short green = scanner.nextShort();
        System.out.println("Enter Blue Value");
        Short blue = scanner.nextShort();
        System.out.println("Enter Row Value");
        int row = scanner.nextInt();
        System.out.println("Enter Column Value");
        int col = scanner.nextInt();
        lights[row][col].setRGB(red, green, blue);
        scanner.close();
    }

    public void lightToggle(int row, int col) {
        if (lights[row][col].isOn()) {
            lights[row][col].setOn(true);
        }
        else {
            lights[row][col].setOn(true);
        }
        System.out.println("Light " + row + ", " + col + " to " + lights[row][col].isOn() + "!");
    }

    public void allOn() {
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                lights[i][j].setOn(true);
            }
        }
        System.out.println("All Lights On");
    }

    public void allOff() {
        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                lights[i][j].setOn(false);
            }
        }
        System.out.println("All Lights Off");
    }
    
    static public void main(String[] args) {
        // create and display LightBoard
        LightBoard lightBoard = new LightBoard(5, 5);
        System.out.println(lightBoard);  // use toString() method
        System.out.println(lightBoard.toTerminal());
        lightBoard.setColor();
        System.out.println(lightBoard.toColorPalette());
        lightBoard.allOn();
        System.out.println(lightBoard.toColorPalette());
        lightBoard.allOff();
        System.out.println(lightBoard.toColorPalette());
        lightBoard.lightToggle(1, 1);
        System.out.println(lightBoard.toColorPalette());
    }
}
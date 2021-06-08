////////////////////////////////////////////////////////////////////////////////
// File:            MovingAverage.java
// Course:          CSC 232 A, Spring 2021
// Author:          Anna Baig
//
// Acknowledgments: None
//
// Online sources:  None
////////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
* A Moving Average class that finds the moving average of numbers in three *different scenarios. The first scenario is a cumulative moving average, *second is filtered moving average, and third is a simple cumulative moving *average.
*
* @author annabaig_2023@depauw.edu
*/

public class MovingAverage {
  
  private double ct;
  private int F; 
  private int K;
  private int N;
  private double avg;
  private int high;
  private int low;
  private ArrayDeque<Double> contents;
  private static double A = 0.0;// min
  private static double B = 0.0;// max

/**
* Constructs a cumulative moving average with no parameters. Initialises the 
* value of ct which represents the sum of our numbers, K represents the 
* total numbers, and avg is used to find the average.
*/
  public MovingAverage() {
      this.ct = 0.0;
      this.K = 0;
      this.avg = 0.0;

  }

/**
* Constructs a "filtered" cumulative moving average that finds the average of * the numbers that lie between the paramaters high and low. The value of F 
* has been set to 1 to indicate a filtered cumulative moving average which 
* will later be called in the add method.
*
*@param low
*@param high
*/
  public MovingAverage(int low, int high) {
      this.high = high;
      this.low = low;
      this.ct = 0.0;
      this.K = 0;
      this.avg = 0.0;
      this.F = 1;
  }

/**
* Constructs a a "simple moving average with window size N" of the values 
* that are added to it.
*
* @param N
*/
  public MovingAverage(int N) {
      this.N = N;
      this.contents = new ArrayDeque<>();

      this.ct = 0.0;

      this.avg = 0.0;
  }

/**
* Adds a double X to cumulative moving average, or adds a double X to the 
* simple moving average case, or adds a double X to the filtered moving
* average case if the number is in the range of high to low.
*
* @param X
*/

  public void add(double X) {
        if (A == 0.0) {
            A = X;
        }

        // simple moving average case
        if (N != 0) { 
            if (contents.size() == N) {
                this.ct -= contents.removeFirst();
            }
            contents.addLast(X);
        }

        //filtered moving average case where if value is less than low or greater than high then it is ignored.
        if (F == 1) {
            if (X < low || X > high) {
                return;
            }
        }

        if (X < A) {
            A = X;
        } else if (X > B) {
            B = X;
        }


        this.ct += X;
        this.K++;
  }

/**
* Finds the average of the cumulative moving average, simple moving average 
* or the filtered moving average scenarios.
*/
  public double avg() {
        if (K == 0) {
            return 0.0;
        } else if (N == 0) {
            this.avg = this.ct / this.K;
        } else {
            this.avg = this.ct / contents.size();
        }
        return this.avg;
  }

/**
* Returns A as the minimum static number for the class. This value applies to * the whole class and is not restricted to any method.
*/

  public static double min() {
        return A;
  }

/**
* Returns A as the maximum static number for the class. This value applies to * the whole class and is not restricted to any method.
*/

  public static double max() {
        return B;
  }
}
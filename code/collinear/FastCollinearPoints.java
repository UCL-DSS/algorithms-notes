import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            throw new IllegalArgumentException("The argument shall not be null");
        }


        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i] == null || points[j] == null) throw new IllegalArgumentException("The points shall not be null");
                else if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("The points should be unique");
            }
        }



        segments = new LineSegment[points.length];
        numberOfSegments = 0;

        Arrays.sort(points);


        Point[] pivotedPoints = new Point[points.length];


        for (int i = 0; i < points.length; i++) {


            for (int m = 0; m < points.length; m++) {
                pivotedPoints[m] = points[m];
            }

            Arrays.sort(pivotedPoints, points[i].slopeOrder());

            // for (Point each: pivotedPoints) {
            //     StdOut.println(each.toString());
            // }

            StdOut.println("Piviot: " + points[i].toString());


            for (int j = 1; j < points.length - 2; j++) {
                if (points[i].slopeTo(pivotedPoints[j]) == points[i].slopeTo(pivotedPoints[j+1]) && points[i].slopeTo(pivotedPoints[j]) == points[i].slopeTo(pivotedPoints[j+2])) {
                    if (points[i].compareTo(pivotedPoints[j]) < 0) {
                        int k = j + 2;
                        while (k < points.length - 1
                                && points[i].slopeTo(pivotedPoints[k]) == points[i]
                                .slopeTo(pivotedPoints[k + 1])) {
                            StdOut.println("Found: " + pivotedPoints[k].toString());

                            k++;

                        }
                        StdOut.println("k: "+k);
                        StdOut.println("Num: " + numberOfSegments);
                        segments[numberOfSegments++] = new LineSegment(points[i], pivotedPoints[k]);
                    }
                }
            }
        }
    }
    public           int numberOfSegments()  {
        return numberOfSegments;
    }

    public LineSegment[] segments()  {
        LineSegment[] collinear = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            collinear[i] = segments[i];
        }
        return collinear;
    }

    // public static void main(String[] args) {
    //     Point[] myPoints = new Point[] { new Point(4, 4),
    //                                      new Point(1, 1),
    //                                      new Point(3, 3),
    //                                      new Point(2, 2),
    //                                      new Point(5, 3),
    //                                      new Point(2, 3),
    //                                      new Point(3, 4),
    //                                      new Point(4, 5),
    //                                      new Point(5, 6)
    //                                      };
    //
    //     FastCollinearPoints col = new FastCollinearPoints(myPoints);
    //     for (LineSegment each: col.segments()) {
    //         StdOut.println(each.toString());
    //     }
    // }
}
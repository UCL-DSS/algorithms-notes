import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] segments;
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("The argument shall not be null");
        }


        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i] == null || points[j] == null) throw new IllegalArgumentException("The points shall not be null");
                else if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("The points should be unique");
            }
        }

        Arrays.sort(points);

        segments = new LineSegment[points.length];
        numberOfSegments = 0;

        for (int i = 0; i <= points.length-4; i++)
            for (int j = i+1; j <= points.length-3; j++)
                for (int k = j+1; k <= points.length-2; k++)
                    for (int m = k+1; m <= points.length-1; m++)
                        if (Math.abs(points[i].slopeTo(points[j])) == Math.abs(points[i].slopeTo(points[k]))
                                && Math.abs(points[i].slopeTo(points[j])) == Math.abs(points[i].slopeTo(points[m]))) {
                            StdOut.println(points[i].toString()+"->"+points[j].toString()+"->"+points[k].toString()+"->"+points[m].toString());
                            segments[numberOfSegments++] = new LineSegment(points[i], points[m]);
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

    public static void main(String[] args) {
        Point[] myPoints = new Point[] { new Point(4, 4),
                                         new Point(1, 1),
                                         new Point(1, 1),
                                         new Point(3, 3),
                                         new Point(2, 2),
                                         new Point(5, 3),
                                         null
                                          };


        BruteCollinearPoints col = new BruteCollinearPoints(myPoints);
        for (LineSegment each: col.segments()) {
            StdOut.println(each.toString());
        }

    }
}
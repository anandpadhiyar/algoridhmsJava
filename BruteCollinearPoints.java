/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) throw new IllegalArgumentException();
        Point[] mutatedPoints = Arrays.copyOf(points, points.length);

        for (int i = 0; i < (mutatedPoints.length - 1); i++) {
            Point currPoint = mutatedPoints[i];
            Point nxtPoint = mutatedPoints[i + 1];
            if (currPoint == null || nxtPoint == null) throw new IllegalArgumentException();
            if (currPoint.compareTo(nxtPoint) == 0) throw new IllegalArgumentException();
        }

        Arrays.sort(mutatedPoints);
        int n = mutatedPoints.length;
        if (n < 4) {
            this.lineSegments = new LineSegment[0];
            return;
        }
        List<LineSegment> tmpLineSegments = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        Point pt1 = mutatedPoints[i];
                        Point pt2 = mutatedPoints[j];
                        Point pt3 = mutatedPoints[k];
                        Point pt4 = mutatedPoints[m];
                        double slope12 = pt1.slopeTo(pt2);
                        double slope13 = pt1.slopeTo(pt3);
                        double slope14 = pt1.slopeTo(pt4);
                        boolean flagSlopesEqual = (Math.abs(slope12) == Math.abs(slope13)) && (
                                Math.abs(slope12) == Math.abs(slope14));
                        if (flagSlopesEqual)
                            tmpLineSegments.add(new LineSegment(pt1, pt4));
                    }
                }
            }
        }
        this.lineSegments = tmpLineSegments.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        // the line segments
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }

    public static void main(String[] args) {
        Point[] points = new Point[] {
                new Point(10000, 0),
                new Point(15000, 5000),
                new Point(18000, 8000),
                new Point(20000, 10000), null
        };
        BruteCollinearPoints bp = new BruteCollinearPoints(points);
        System.out.println(bp.numberOfSegments());
    }
}
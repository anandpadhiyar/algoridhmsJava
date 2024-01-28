/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] mutatePoints = Arrays.copyOf(points, points.length);

        for (int i = 0; i < mutatePoints.length; i++) {
            if (mutatePoints[i] == null) throw new IllegalArgumentException();
        }

        Arrays.sort(mutatePoints);

        for (int i = 0; i < (mutatePoints.length - 1); i++) {
            Point currPoint = mutatePoints[i];
            Point nxtPoint = mutatePoints[i + 1];
            if (currPoint.compareTo(nxtPoint) == 0) throw new IllegalArgumentException();
        }

        int n = mutatePoints.length;
        if (n < 4) {
            this.lineSegments = new LineSegment[0];
            return;
        }
        List<LineSegment> tmpLineSegments = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Point p = points[i];
            Arrays.sort(mutatePoints, 0, mutatePoints.length, p.slopeOrder());
            int k = 1;
            while (k < n) {
                Point currenPt = mutatePoints[k];
                double currentPtSlope = p.slopeTo(currenPt);
                List<Point> collinearPoints = new LinkedList<>();
                collinearPoints.add(p);
                collinearPoints.add(currenPt);
                k = k + 1;
                while (k < n) {
                    Point nextPt = mutatePoints[k];
                    if (p.slopeTo(nextPt) != currentPtSlope) break;
                    collinearPoints.add(nextPt);
                    k = k + 1;
                }
                if (collinearPoints.size() >= 4) {
                    Point[] array = collinearPoints.toArray(new Point[0]);
                    Arrays.sort(array, 0, array.length);
                    if (p.compareTo(array[0]) <= 0) {
                        LineSegment ls = new LineSegment(array[0], array[array.length - 1]);
                        tmpLineSegments.add(ls);
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
                new Point(7372, 8695),
                new Point(6053, 8242),
                new Point(7194, 10373),
                new Point(1620, 9564),
                new Point(12822, 14447),
                new Point(14187, 13214),
                new Point(11793, 4718),
                new Point(5434, 7768),
                new Point(13186, 11476),
                new Point(6141, 12300),
                new Point(321, 9438),
                new Point(1187, 9522),
                new Point(5973, 288),
                new Point(6225, 18306),
                new Point(6311, 9198),
                new Point(10946, 13089),
                new Point(6698, 10632),
                new Point(11146, 10488),
                new Point(6213, 17448),
                new Point(4892, 3940),
                new Point(10602, 10240),
                new Point(11527, 3774),
                new Point(13760, 15126),
                new Point(14453, 14158),
                };
        FastCollinearPoints fp = new FastCollinearPoints(points);
        System.out.println(Arrays.toString(fp.segments()));
    }
}

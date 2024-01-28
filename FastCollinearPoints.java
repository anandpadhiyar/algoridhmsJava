/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] mutatePoints = Arrays.copyOf(points, points.length);

        for (int i = 0; i < (mutatePoints.length - 1); i++) {
            Point currPoint = mutatePoints[i];
            Point nxtPoint = mutatePoints[i + 1];
            if (currPoint == null || nxtPoint == null) throw new IllegalArgumentException();
            if (currPoint.compareTo(nxtPoint) == 0) throw new IllegalArgumentException();
        }

        int n = mutatePoints.length;
        if (n < 4) {
            this.lineSegments = new LineSegment[0];
            return;
        }
        List<LineSegment> tmpLineSegments = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Point p = mutatePoints[i];
            Arrays.sort(mutatePoints, 0, mutatePoints.length, p.slopeOrder());
            int k = 1;
            while (k < (n - 1)) {
                Point currenPt = mutatePoints[k];
                double currentPtSlope = p.slopeTo(currenPt);
                ArrayList<Point> collinearPoints = new ArrayList<Point>();
                collinearPoints.add(currenPt);
                k = k + 1;
                while ((k < (n - 1))) {
                    Point nextPt = mutatePoints[k];
                    if (p.slopeTo(nextPt) != currentPtSlope) break;
                    k = k + 1;
                    collinearPoints.add(nextPt);
                }
                if (collinearPoints.size() >= 3) {
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
}

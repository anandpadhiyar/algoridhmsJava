/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException(
                    "Argument can't be null, or have any null or repeated points");

        Point[] mutablePoints = Arrays.stream(points).toArray(Point[]::new);

        if (containsNullPoint(mutablePoints) || repeatedPoint(mutablePoints))
            throw new IllegalArgumentException(
                    "Argument can't be null, or have any null or repeated points");

        List<LineSegment> tmpSegments = new LinkedList<>();

        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];

            Arrays.sort(mutablePoints, 0, points.length, p.slopeOrder());
            int segmentStart = 1; // Skip index 0 always because it's slope of own point 'p'
            int segmentSize = 1; // point 'p' counts as part of the segment
            for (int q = segmentStart; q < points.length - 1; ++q) {
                if (p.slopeTo(mutablePoints[q]) == p.slopeTo(mutablePoints[q + 1])) {
                    if (segmentSize == 1)
                        segmentStart = q;
                    ++segmentSize;
                }
                else {
                    if (segmentSize >= 3)
                        constructSegment(segmentSize, segmentStart, mutablePoints, p, tmpSegments);
                    segmentSize = 1;
                }
            }
            if (segmentSize >= 3)
                constructSegment(segmentSize, segmentStart, mutablePoints, p, tmpSegments);
        }

        segments = tmpSegments.toArray(new LineSegment[0]);
    }

    private void constructSegment(int segmentSize, int segmentStart, Point[] mutablePoints, Point p,
                                  List<LineSegment> tmpSegments) {
        Point[] segmentPoints = new Point[segmentSize + 1]; // Plus one for that point 'p'
        segmentPoints[0] = p;
        for (int pos = 0; pos < segmentSize; ++pos) {
            segmentPoints[pos + 1] = mutablePoints[segmentStart + pos];
        }

        Arrays.sort(segmentPoints, 0, segmentPoints.length);
        if (p.compareTo(segmentPoints[0]) <= 0)
            tmpSegments
                    .add(new LineSegment(segmentPoints[0], segmentPoints[segmentSize]));
    }

    private boolean containsNullPoint(Point[] points) {
        return Arrays.stream(points).anyMatch(Objects::isNull);
    }

    private boolean repeatedPoint(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; ++i) {
            if (points[i].compareTo(points[i + 1]) == 0)
                return true;
        }
        return false;
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.stream(segments).toArray(LineSegment[]::new);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // set color and size
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.0075);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println(collinear.numberOfSegments());
        StdDraw.show();
    }
}

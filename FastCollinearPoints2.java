/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FastCollinearPoints2 {

    private final LineSegment[] segments;

    public FastCollinearPoints2(Point[] points) {
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
        System.out.println(fp.numberOfSegments());
        System.out.println(Arrays.toString(fp.segments()));
    }
}
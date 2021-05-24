package business.entities;

public class SVG {
    StringBuilder svg = new StringBuilder();

    final private int x;
    final private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg class=\"img-fluid\" " +
            "height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";


    private final String rectEmptyTemplate = "<rect x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" style=\"stroke:#000; fill: none\" />";
    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" style=\"stroke:#000; fill: #FAFAFA\" />";
    private final String filledRectTemplate = "<rect x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\"  style=\"stroke:#000; fill: #c9c9c9\" />";
    private final String lineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"black\" />";
    private final String lineDottedTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"black\" stroke-dasharray=\"4 4\" />";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(double x, double y, double width, double height) {
        svg.append(String.format(rectTemplate, x, y, width, height));
    }

    public void addEmptyRect(double x, double y, double width, double height) {
        svg.append(String.format(rectEmptyTemplate, x, y, width, height));
    }

    public void addFilledRect(double x, double y, double width, double height) {
        svg.append(String.format(filledRectTemplate, x, y, width, height));
    }

    public void addLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addDottedLine(double x1, double y1, double x2, double y2) {
        svg.append(String.format(lineDottedTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }

}

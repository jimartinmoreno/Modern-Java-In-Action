package modernjavainaction.chap13.defaultmethods;

public interface Resizable extends Drawable {

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);
    //TODO: uncomment, read the README for instructions
    //void setRelativeSize(int widthFactor, int heightFactor);

}

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Sprite {
    private Image image;
    private GraphicsContext ctx;
    private String path;
    private double posX,
        posY,
        velX,
        velY,
        width,
        height;

    public Sprite(Asset asset) {
        width = asset.getWidth();
        height = asset.getHeight();
        path = asset.getPath();

        image = new Image(path, width, height, false, false);
    }

    public void changeImage(Asset asset) {
        width = asset.getWidth();
        height = asset.getHeight();
        path = asset.getPath();

        image = new Image(path, width, height, false, false);
    }

    public void resizeImage(double width, double height) {
        image = new Image(path, width, height, false, false);
    }

    public boolean intersects(Sprite sprite) {
        return sprite.getSize().intersects(this.getSize());
    }

    public boolean intersects(Rectangle2D rectangle) {
        return rectangle.intersects(this.getSize());
    }

    public void update() {
        posX += velX;
        posY += velY;
    }
  
    public void updateTimescaled(int timescale) {
        posX += velX * timescale;
        posY += velY * timescale;
    }

    public void render() {
        ctx.drawImage(image, posX, posY);
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle2D getSize() {
        return new Rectangle2D(posX, posY, width, height);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
  
    public void setPos(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double[] getPos() {
        return new double[] { posX, posY };
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setVel(double velX, double velY) {
        this.velX = velX;
        this.velY = velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setCtx(GraphicsContext ctx) {
        this.ctx = ctx;
    }

/*    public void setPosXY(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setvel(double velX, double velY) {
        this.velX = velX;
        this.velY = velY;
    }

    public void addvel(double x, double y) {
        this.velX += x;
        this.velY += y;
    }

    public double getvelX() {
        return velX;
    }

    public double getvelY() {
        return velY;
    }

    public double getWidth() {
        return width;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(posX, posY, width, height);
    }

    public boolean intersectsSprite(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public void update(double time) {
        posX += velX * time;
        posY += velY * time;
    }*/
}

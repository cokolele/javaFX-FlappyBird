import javafx.geometry.Rectangle2D;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

class Restart implements GameObject {
    private int WIDTH = 134;
    private int HEIGHT = 47;
    private Asset asset = new Asset("/images/restart.png", WIDTH, HEIGHT);
    private Sprite sprite;

    public Restart(double screenWidth, double screenHeight, GraphicsContext ctx) {
        sprite = new Sprite(asset);
        sprite.setPosX(screenWidth / 2 - WIDTH / 2);
        sprite.setPosY(screenHeight - 80);
        sprite.setVel(0, 0);
        sprite.setCtx(ctx);
    }

    public boolean checkClick(double posX, double posY) {
        return sprite.intersects( new Rectangle2D(posX, posY, 1, 1) );
    }

    public void update(long now) {
    }

    public void render() {
        if (FlappyBird.gameEnded)
            sprite.render();
    }
}
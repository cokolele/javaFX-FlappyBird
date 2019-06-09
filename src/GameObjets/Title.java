import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

class Title implements GameObject {
    private int WIDTH = 178;
    private int HEIGHT = 48;
    private Asset asset = new Asset("/images/title.png", WIDTH, HEIGHT);
    private Sprite sprite;

    public Title(double screenWidth, double screenHeight, GraphicsContext ctx) {
        sprite = new Sprite(asset);
        sprite.setPosX(screenWidth / 2 - WIDTH / 2);
        sprite.setPosY(40);
        sprite.setVel(0, 0);
        sprite.setCtx(ctx);
    }

    public void update(long now) {
    }

    public void render() {
        if (!FlappyBird.gameStarted && !FlappyBird.gameEnded)
            sprite.render();
    }
}
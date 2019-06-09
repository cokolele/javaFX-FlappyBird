import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

class Bird implements GameObject {
    private int WIDTH = 56;
    private int HEIGHT = 40;
    private Asset assets[] = {
        new Asset("/images/bird1.png", WIDTH, HEIGHT),
        new Asset("/images/bird2.png", WIDTH, HEIGHT),
        new Asset("/images/bird3.png", WIDTH, HEIGHT)
    };
    private Sprite sprite;
    private int currentAssetIndex = 0;
    private long prevTime = 0;
    private float terminalVel = 8;
    private float shiftMax = 10;
    private float shiftDelta = 0;
    private double screenHeight;

    public Bird(double screenWidth, double screenHeight, GraphicsContext ctx) {
        this.screenHeight = screenHeight;

        sprite = new Sprite(assets[currentAssetIndex]);
        sprite.setPosX(screenWidth / 2 - WIDTH / 2);
        sprite.setPosY( FlappyBird.gameEnded ? screenHeight - 112 - HEIGHT : (screenHeight - 112) / 2 );
        sprite.setVel(0, 0);
        sprite.setCtx(ctx);
    }

    public void jumpHandler() {
        sprite.setVelY(-8);
    }

    public void update(long now) {
        if (!FlappyBird.gameStarted && !FlappyBird.gameEnded){
            updateBirdHovering();
        } else if (FlappyBird.gameEnded) {
            updateBirdFalldown();
        } else if (FlappyBird.gameStarted) {
            if (now - prevTime > 90000000) {
                updateSprite();
                prevTime = now;
            }

            if ((sprite.getPosY() + HEIGHT) > (screenHeight - 112) ||
                sprite.intersects(FlappyBird.activePipes[0]) ||
                sprite.intersects(FlappyBird.activePipes[1])
                ) {
                
                FlappyBird.gameStarted = false;
                FlappyBird.gameEnded = true;
            }

            updateBirdPlaying();
        }

        sprite.update();
    }

    public void updateBirdHovering() {
        double vel = sprite.getVelY();

        //disgusting, i know
        //but look, its, like, adaptive or what
        //bird can smoothly transition to this state from any posY
        //idk, it works dont touch it
        if (shiftDelta == 0) {
            sprite.setVelY(0.5);
            shiftDelta += 0.5;
        } else if (shiftDelta > 0) {
            if (vel > 0.1) {            
                if (shiftDelta < shiftMax / 2) {
                    float shift = (float) (vel * 1.06);
                    sprite.setVelY(shift);
                    shiftDelta += shift;
                } else {
                    float shift = (float) (vel * 0.8);
                    sprite.setVelY(shift);
                    shiftDelta += shift;
                }
            } else if (vel < 0.1) {
                if (vel > 0) {
                    sprite.setVelY(-0.5);
                } else {
                    float shift = (float) (vel * 1.06);
                    sprite.setVelY(shift);
                    shiftDelta += shift;
                }
            }
        } else if (shiftDelta < 0) {
            if (vel < -0.1) {            
                if (shiftDelta > -shiftMax / 2) {
                    float shift = (float) (vel * 1.06);
                    sprite.setVelY(shift);
                    shiftDelta += shift;
                } else {
                    float shift = (float) (vel * 0.8);
                    sprite.setVelY(shift);
                    shiftDelta += shift;
                }
            } else if (vel > -0.1) {
                if (vel < 0) {
                    sprite.setVelY(0.5);
                } else {
                    float shift = (float) (vel * 1.06);
                    sprite.setVelY(shift);
                    shiftDelta += shift;
                }
            }
        }
    }

    public void updateBirdPlaying() {
        double vel = sprite.getVelY();

        if (vel >= terminalVel)
            sprite.setVelY(vel + 0.2);
        else
            sprite.setVelY(vel + 0.44);
    }

    public void updateBirdFalldown() {
        if (sprite.getPosY() + HEIGHT >= screenHeight - 112) {
            sprite.setVel(0, 0);
            sprite.setPosY(screenHeight - 112 - HEIGHT);
        } else {
            updateBirdPlaying();
        }
        
    }

    public void updateSprite() {
        if (currentAssetIndex == 3)
            currentAssetIndex = 0;

        sprite.changeImage(assets[currentAssetIndex]);

        currentAssetIndex++;
    }

    public void render() {
        sprite.render();
    }
}
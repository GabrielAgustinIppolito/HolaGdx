package it.gabriel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.gabriel.model.data.Ball;
import it.gabriel.model.data.Block;
import it.gabriel.model.data.Paddle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HolaGdx extends ApplicationAdapter {
    private ShapeRenderer shape;
    private List<Ball> balls;
    private Paddle paddle;
    private List<Block> blocks;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        paddle = new Paddle();
        balls = new ArrayList<>();
        blocks = new ArrayList<>();
        Random ran = new Random();
        int blockWidth = 63;
        int blockHeight = 20;
        int gap = 10;
        for (int y = (int) (Gdx.graphics.getHeight() / 1.5); y < Gdx.graphics.getHeight(); y += blockHeight + gap) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + gap) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
        for (int i = 0; i < 1; i++) {
            balls.add(new Ball(
                    ran.nextInt(Gdx.graphics.getWidth()),
                    ran.nextInt(Gdx.graphics.getHeight()),
                    //ran.nextInt(100),
                    20,
                    10,
                    10
//					ran.nextInt(15),
//					ran.nextInt(15)
            ));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Ball ball : balls) {
            ball.checkCollision(paddle);
            ball.update();
            for (Block block : blocks) {
                block.draw(shape);
                ball.checkCollision(block);
                ball.draw(shape);
            }
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if (b.isDestroyed()) {
                    blocks.remove(b);
                    i--;
                }
            }
            paddle.update();
            paddle.draw(shape);
            shape.end();
        }
    }
}









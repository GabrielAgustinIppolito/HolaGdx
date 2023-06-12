package it.gabriel.model.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Ball extends Circle {
    private float xSpeed;
    private float ySpeed;
    Color color;

    public Ball(float x, float y, float radius, float xSpeed, float ySpeed) {
        this.x = x;
        this.y = y;
        this.radius = Math.max(radius, 1);
        this.xSpeed = Math.max(xSpeed, 1);
        this.ySpeed = Math.max(ySpeed, 1);
        if (x < radius){
            this.x = x + radius;
        }
        if (x > Gdx.graphics.getWidth() - radius ){
            this.x= Gdx.graphics.getWidth() - radius;
        }
        if (y<radius){
            this.y = radius;
        }
        if (y > Gdx.graphics.getHeight() - radius){
            this.y= Gdx.graphics.getHeight() - y;
        }
        this.color = Color.WHITE;
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;
        if (x > Gdx.graphics.getWidth() - radius || x < radius){
            xSpeed *= -1;
        }
        if (y > Gdx.graphics.getHeight() - radius || y < radius){
            ySpeed *= -1;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.circle(x, y, radius);
    }

    public void checkCollision(Paddle paddle){
        if(collidesWith(paddle)){
            color = Color.CORAL;
            ySpeed *= -1;
        } else {
            color = Color.WHITE;
        }
    }

    public void checkCollision(Block block){
        if(collidesWith(block)){
            color = Color.LIME;
            ySpeed *= -1;
            block.setDestroyed(true);
        } else {
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Rectangle rectangle){
         return Intersector.overlaps(this,rectangle);
    }

}

package it.gabriel.model.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle extends Rectangle {


    public Paddle() {
       this.y = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight()/100f * 90 );
       this.x = Gdx.graphics.getWidth()/2f;
       this.width = 80;
       this.height = 10;
    }

    public void update(){
        x = Gdx.input.getX() - this.width/2f;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width, height);
    }

}

package com.juicerspride.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


//figure out circle/ellipse parsing, doesn't work for some reason
public class TiledObjectUtil {
    public static void parseTiledObject(World world, MapObjects objects){
        for (MapObject object: objects){
            Shape shape;

            if (object instanceof PolylineMapObject){
                shape = createPolylineObject((PolylineMapObject) object);
                Body body;
                BodyDef bdef = new BodyDef();
                bdef.type = BodyDef.BodyType.StaticBody;
                body = world.createBody(bdef);
                body.createFixture(shape, 1.0f);
                shape.dispose();

            }
            else if (object instanceof CircleMapObject){
//                shape = createCircleObject((CircleMapObject) object);
                CircleMapObject circleObject = (CircleMapObject) object;
                Circle circle = circleObject.getCircle();
                BodyDef bdef = new BodyDef();
                bdef.position.set(circle.x, circle.y);
                bdef.type = BodyDef.BodyType.StaticBody;

                Body body = world.createBody(bdef);
                CircleShape circleShape = new CircleShape();
                circleShape.setRadius(circle.radius);
                body.createFixture(circleShape, 1.0f);
                circleShape.dispose();
;
            }
            else if (object instanceof EllipseMapObject){
                EllipseMapObject circleMapObject = (EllipseMapObject) object;
                Ellipse ellipse = circleMapObject.getEllipse();
                BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(ellipse.x, ellipse.y);
                bodyDef.type = BodyDef.BodyType.StaticBody;

                Body body = world.createBody(bodyDef);
                CircleShape circleShape = new CircleShape();
                circleShape.setRadius(ellipse.width / 2f);
                body.createFixture(circleShape, 1.0f);
                circleShape.dispose();

            }
            else{
                continue;
            }

//            Body body;
//            BodyDef bdef = new BodyDef();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            body = world.createBody(bdef);
//            body.createFixture(shape, 1.0f);
//            shape.dispose();

        }
    }

    private static ChainShape createPolylineObject(PolylineMapObject polyline){
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for (int i = 0; i< worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i*2]/Constants.PPM, vertices[i*2 + 1]/Constants.PPM);
        }

        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    private static CircleShape createCircleObject(CircleMapObject circle){
        CircleShape cirs = new CircleShape();
        cirs.setRadius(circle.getCircle().radius);
        Vector2 v = new Vector2(circle.getCircle().x, circle.getCircle().y);
        cirs.setPosition(v);
        return cirs;
    }

    private static CircleShape createCircleObject(EllipseMapObject ellipse){
        CircleShape cirs = new CircleShape();
        cirs.setRadius(ellipse.getEllipse().height);
        Vector2 v = new Vector2(ellipse.getEllipse().x, ellipse.getEllipse().y);
        cirs.setPosition(v);
        return cirs;
    }

}

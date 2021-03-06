/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import gdx.game.GamMain;
import gdx.game.objects.Tile;

/**
 *
 * @author Alex
 */
public class ScratchGamTiled implements Screen {
    
    GamMain game;
    OrthographicCamera camera;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    Tile arTiles [][] = new Tile [3][3];
    Tile tile1, tile2;
    SpriteBatch batch;
    BitmapFont font;
    int nCount = 0;
    
    public ScratchGamTiled(GamMain _game){
        game = _game;
        
        // Creating Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        
        // Loading Tiled Map
        tiledMap = new TmxMapLoader().load("tiledMap2.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
        
        // Create Tiles
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                arTiles[i][j] = new Tile(game, i, j);
            }
        }
        tile1 = null;
        tile2 = null;
        
        // Load Text
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font2.fnt"));
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        checkInput(); // Checks for input and handles any input
        update(); // Updates some game variables once every 10 frames
        graphics(); // Renders graphics
    }
    
    private void graphics(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(32/256f, 64/256f, 256/256f, 1);
        
        // Draw Tiled Map
        camera.update();
        tmr.setView(camera);
        tmr.render();
        
        // Draw Troop Count
        graphicsTiles();
    }
    
    private void checkInput(){
        if(Gdx.input.justTouched()){
            Vector2 vTemp = getMouseLocationOnMap();
            
            // Tests adding troops to tiles
            if(vTemp.x < 3 && vTemp.y < 3){
                arTiles[(int)vTemp.x][(int)vTemp.y].setTroopCount(arTiles[(int)vTemp.x][(int)vTemp.y].getTroopCount()+1);
                System.out.println("X: "+vTemp.x+" Y: "+vTemp.y+" Troops: "+arTiles[(int)vTemp.x][(int)vTemp.y].getTroopCount());
            }
            
            // Tests adjactency
            /*
            if(tile1 == null){
                tile1 = arTiles[(int)vTemp.x][(int)vTemp.y];
            } else {
                tile2 = arTiles[(int)vTemp.x][(int)vTemp.y];
                if(isAdjacent(tile1, tile2)){
                    System.out.println("X:"+tile1.getX()+" Y:"+tile1.getY()+" is adjacent to X:"+tile2.getX()+" Y:"+tile2.getY());
                } else {
                    System.out.println("X:"+tile1.getX()+" Y:"+tile1.getY()+" is not adjacent to X:"+tile2.getX()+" Y:"+tile2.getY());
                }
                tile1 = null;
                tile2 = null;
            }
            */
        }
    }
    
    private void update(){
        if(nCount == 10){
            nCount = 0;
            
            // Update TroopCount
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    arTiles[i][j].updateStr();
                }
            }
        } else {
            nCount++;
        }
    }
    
    private void graphicsTiles(){
        Tile tile;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tile = arTiles[i][j];
                int nAdd = (tile.getStr().length() == 1) ? 10: 0;
                
                // Troop Count
                batch.begin();
                font.draw(batch, tile.getStr(), i * 256 + 100 + nAdd, (j * 256 + 120)*(-1)+Gdx.graphics.getHeight());
                batch.end();
            }
        }
    }
    
    private Vector2 getMouseLocationOnMap(){
        Vector2 vMapLocation = new Vector2(0, 0);
        vMapLocation.x = Gdx.input.getX() / 256; // 256 is the tile size
        vMapLocation.y = Gdx.input.getY() / 256; // 256 is the tile size
        return vMapLocation;
    }
    
    private boolean isAdjacent(Tile tile1, Tile tile2){
        if(tile1.getX() == tile2.getX()){
            if(tile1.getY() == tile2.getY() + 1){
                return true;
            } else if(tile1.getY() == tile2.getY() - 1){
                return true;
            }
        }
        if(tile1.getY() == tile2.getY()){
            if(tile1.getX() == tile2.getX() + 1){
                return true;
            } else if(tile1.getX() == tile2.getX() - 1){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}

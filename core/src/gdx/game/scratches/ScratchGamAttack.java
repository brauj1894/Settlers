/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamMain;
import gdx.game.objects.Button;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class ScratchGamAttack implements Screen {

    Random ranGen = new Random();
    int nRandAttack, nRandDefend;
    SpriteBatch batch;
    GamMain game;
    Sprite sprBG;
    Texture txtBG, txtAttack, txtDefend,txtWhite1,txtWhite2;
    Button btnEndBattle, btnBattle;

    public ScratchGamAttack(GamMain _game) {
        game = _game;
        txtBG = new Texture("bg3.jpg");
        txtAttack = new Texture("button_attack.png");
        txtDefend = new Texture("button_defend.png");
        txtWhite1 = new Texture("button.png");
        txtWhite2 = new Texture("button.png");
        sprBG = new Sprite(txtBG, 0, 0, 1144, 744);
        btnBattle = new Button(500, 650, 100, 100, "button_battle.png");
        btnEndBattle = new Button(500, 100, 100, 100, "button_end-battle.png");
        batch = new SpriteBatch();
        //sprAttack.setX(200);
        //sprAttack.setY(500);

    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
        checkButtons();
        batch.begin();
        sprBG.draw(batch);
        batch.draw(txtAttack, 200, 650);
        batch.draw(txtWhite1, 325, 650);
        batch.draw(txtDefend, 800, 650);
        batch.draw(txtWhite2, 925, 650);
        btnBattle.draw(batch);
        btnEndBattle.draw(batch);

        batch.end();
        SpriteBatch spriteBatch;
        BitmapFont font;
        CharSequence sNumberAttackers = Integer.toString(nTroopsA);
        CharSequence sNumberDefenders = Integer.toString(nTroopsD);
        spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        font = new BitmapFont();
        font.draw(spriteBatch, sNumberAttackers, 350, 670);
        font.draw(spriteBatch, sNumberDefenders, 950, 670);
        spriteBatch.end();
    }
    boolean isBattle = false;
    boolean isTroopsA = true, isTroopsD = true;
    int nTroopsA = 5, nTroopsD = 3;

    private void checkButtons() {
        if (Gdx.input.justTouched()) {
            if (btnEndBattle.isMousedOver()) {
                game.changeScreen(0);
            }
            if (isTroopsA && isTroopsD) {
                if (btnBattle.isMousedOver()) {
                    nRandAttack = ranGen.nextInt(10);
                    System.out.println(nRandAttack);


                    nRandDefend = ranGen.nextInt(10);
                    System.out.println(nRandDefend);
                    isBattle = true;
                }

                if (isBattle) {
                    if (nRandAttack > nRandDefend) {
                        System.out.println("Defenders lost one");
                        nTroopsD -= 1;
                        System.out.println(nTroopsD);
                        if (nTroopsD == 0) {
                            isTroopsD = false;
                        }
                    } else if (nRandAttack < nRandDefend) {
                        System.out.println("Attackers lost one");
                        nTroopsA -= 1;
                        System.out.println(nTroopsA);
                        if (nTroopsA == 0) {
                            isTroopsA = false;
                        }
                    } else if (nRandAttack == nRandDefend) {
                        System.out.println("Nobody dies this night");
                    }
                }
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

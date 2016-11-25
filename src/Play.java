
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Play extends BasicGameState{

    Animation mm, up, down, left, right, stay, bend;
    Image map;
    Image one, two, three, four, five, six, seven, complete;

    int[] duration = {200, 200, 200, 200, 200, 200, 200, 200, 200};

    int rightLimit = 610, leftLimit = -15;
    int upLimit = 70, downLimit = 310;
    int mousePosX, mousePosY;

    float shiftX = 553;
    float shiftY = 150;

    boolean fbat = false, fhat = false, fhandcuff = false, fgun = false, fclock = false, fshoe = false, fbadge = false;
    boolean quit = false;

    Music back;
    Sound move, space, win;

    public Play(int state){

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        map = new Image("res/back3.jpg");
        one = new Image("res/bat.png");
        two = new Image("res/hat.png");
        three = new Image("res/handcuff.png");
        four = new Image("res/gun.png");
        five = new Image("res/clock.png");
        six = new Image("res/shoe.png");
        seven = new Image("res/badge.png");
        complete = new Image("res/missComplete.png");

        back = new Music("res/back.ogg");
       // win = new Sound("res/win.ogg");
        space = new Sound("res/space.ogg");
        move = new Sound("res/move.wav");



        Image[] walkUp = {new Image("res/wu1.png"), new Image("res/wu2.png"), new Image("res/wu3.png"), new Image("res/wu4.png"), new Image("res/wu5.png"), new Image("res/wu6.png"), new Image("res/wu7.png"), new Image("res/wu8.png"), new Image("res/wd9.png")};
        Image[] walkDown = {new Image("res/wd1.png"), new Image("res/wd2.png"), new Image("res/wd3.png"), new Image("res/wd4.png"), new Image("res/wd5.png"), new Image("res/wd6.png"), new Image("res/wd7.png"), new Image("res/wd8.png"), new Image("res/wd9.png")};
        Image[] walkLeft = {new Image("res/wl1.png"), new Image("res/wl2.png"), new Image("res/wl3.png"), new Image("res/wl4.png"), new Image("res/wl5.png"), new Image("res/wl6.png"), new Image("res/wl7.png"), new Image("res/wl8.png"), new Image("res/wl9.png")};
        Image[] walkRight = {new Image("res/wr1.png"), new Image("res/wr2.png"), new Image("res/wr3.png"), new Image("res/wr4.png"), new Image("res/wr5.png"), new Image("res/wr6.png"), new Image("res/wr7.png"), new Image("res/wr8.png"), new Image("res/wr9.png")};
        Image[] steady = {new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png"), new Image("res/s1.png")};
        Image[] bendiffound = {new Image("res/bend2.png"), new Image("res/bend2.png"), new Image("res/bend3.png"), new Image("res/bend3.png"), new Image("res/bend4.png"), new Image("res/bend4.png"), new Image("res/bend4.png"), new Image("res/bend2.png"), new Image("res/bend1.png")};
        up = new Animation(walkUp, duration, true);
        down = new Animation(walkDown, duration, true);
        left = new Animation(walkLeft, duration, true);
        right = new Animation(walkRight, duration, true);
        stay = new Animation(steady, duration, true);
        bend = new Animation(bendiffound, duration, true);

        mm = stay;

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Input input = gc.getInput();

        map.draw(0, 0, map.getWidth(), map.getHeight());

        if(fbat == false){
            g.drawString("Bat", 80, 30);
            one.draw(540, 290, one.getWidth()-344, one.getHeight()-152);
        }
        if(fhat == false){
            g.drawString("Hat", 20, 30);
            two.draw(35, 105, two.getWidth()-250, two.getHeight()-182);

        }
        if(fhandcuff == false){
            g.drawString("Handcuffs", 140, 30);
            three.draw(170, 200, three.getWidth()-750, three.getHeight()-326);
        }
        if(fgun == false){
            g.drawString("Rifle", 260, 30);
            four.draw(320, 325, four.getWidth()-803, four.getHeight()-266);
        }
        if(fclock == false){
            g.drawString("Clock", 340, 30);
            five.draw(400, 250, five.getWidth()-305, five.getHeight()-400);
        }
        if(fshoe == false){
            g.drawString("Boots", 450, 30);
            six.draw(400, 140, six.getWidth()-335, six.getHeight()-333);
        }
        if(fbadge == false){
            g.drawString("Badge", 540, 30);
            seven.draw(30, 320, seven.getWidth()-302, seven.getHeight()-403);
        }
        if(fbat == true && fhat == true && fhandcuff == true && fgun == true && fclock == true && fshoe == true && fbadge == true){
            sbg.enterState(4);
           /* complete.draw(0, 0);
            //win.loop();

            mousePosX = Mouse.getX();
            mousePosY = Mouse.getY();

            if((mousePosX > 275 && mousePosX < 365) && (mousePosY > 125 && mousePosY < 175)){
                if(Mouse.isButtonDown(0)) {
                    sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
                }
            }*/
        }


        mm.draw(shiftX, shiftY, mm.getWidth(), mm.getHeight());

        if(quit == true){
            g.drawString("Resume (R)", 250, 100);
            g.drawString("Main Menu (M)", 250, 150);
            g.drawString("Quit Game (Q)", 250, 200);
            if(quit == false){
                g.clear();
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        mm = stay;

        if(input.isKeyDown(Input.KEY_UP)){
            mm = up;
            shiftY -= delta * .1f;
            if(shiftY < upLimit){
                shiftY += delta * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            mm = down;
            shiftY += delta * .1f;
            if(shiftY > downLimit){
                shiftY -= delta * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            mm = right;
            shiftX += delta * .1f;
            if(shiftX > rightLimit){
                shiftX -= delta * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_LEFT)){
            mm = left;
            shiftX -= delta * .1f;
            if(shiftX < leftLimit){
                shiftX += delta * .1f;
            }
        }

        if(input.isKeyDown(Input.KEY_SPACE)){
            mm = bend;
            if((shiftX > 520 && shiftX < 600) && (shiftY < 300 && shiftY > 250)){
                fbat = true;
                space.play();
            }
            else if((shiftX > 30 && shiftX < 55) && (shiftY < 120 && shiftY > 50)){
                fhat = true;
                space.play();
            }

            else if((shiftX > 160 && shiftX < 190) && (shiftY < 230 && shiftY > 160)){
                fhandcuff = true;
                space.play();
            }

            else if((shiftX > 310 && shiftX < 400) && (shiftY < 320 && shiftY > 290)){
                fgun = true;
                space.play();
            }

            else if((shiftX > 387 && shiftX < 410) && (shiftY < 255 && shiftY > 215)){
                fclock = true;
                space.play();
            }

            else if((shiftX > 395 && shiftX < 430) && (shiftY < 140 && shiftY > 105)){
                fshoe = true;
                space.play();
            }
            else if((shiftX > 9 && shiftX < 35) && (shiftY < 310 && shiftY > 285)){
                fbadge = true;
                space.play();
            }
            else{
                move.play();
            }
        }

        if(input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        if(quit == true){
            if(input.isKeyDown(Input.KEY_R)){
                quit = false;
            }
            if(input.isKeyDown(Input.KEY_M)){
                sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
            if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.loop();
        back.setVolume(2.0f);
    }

    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.leave(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        back.stop();
    }

    @Override
    public int getID() {
        return 3;
    }


}
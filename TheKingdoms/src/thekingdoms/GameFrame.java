/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class GameFrame extends Canvas implements Runnable {
    
    public static int height = TheKingdoms.HEIGHT;
    public static int width = TheKingdoms.WIDTH;
    public static double scale = 1;
    public static int fps = 25;
    public static int period = 1000/fps;
    public int currentFps;
    
    public static final String NAME = "mikeys renderer";

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    Random rand = new Random();
    
    private boolean running = false;
    
    public SpriteSheetLoader loader;
    public Screen screen;
    
    Chunk[][] chunkMap = new Chunk[8][5];
    
    public GameFrame(){
        System.out.println("popo.o");
        this.setIgnoreRepaint(true);
        this.setBounds(0,0,width,height);
        start();
    }
    
    public void stop(){
        running = false;
    }
    
    public void start(){
        running = true;
        new Thread(this).start();
        
    }
    
    @Override
    public void run() {
        init();
        while(running){
            
            long beginTime = System.currentTimeMillis();
            tick();
            render();
            long timeTaken = System.currentTimeMillis() - beginTime;
            currentFps = 1000/(int)timeTaken;
            //System.out.println(currentFps);
            /*long sleepTime = period - timeTaken;
            try{
                Thread.sleep(sleepTime);
            }catch(Exception e){
            }*/
            
        }
        
    }
    
    public void tick(){
        
    }
    
    public void render(){
        Chunk renderMap[][] = loadMap(0,0);
        for (int x = 0; x<chunkMap.length; x++){
            for (int y = 0; y<chunkMap[x].length; y++){
                chunkMap[x][y].renderTopTiles(x*256, y*256);
            }
        }
        
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            requestFocus();
            return;
        }
        
        for( int y = 0; y < screen.height; y++){
            for(int x=0; x < screen.width; x++){
                pixels[x+(y*width)] = screen.pixels[x+(y*screen.width)];
            }
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        g.setColor(Color.BLUE);
        g.drawString("fps: "+currentFps,17,20);
        g.dispose();
        bs.show();
        
    }
    
    public void init(){
        
        loader = new SpriteSheetLoader();
        screen = new Screen(width, height, 16);
        //Sprite sprite = Sprites.tiles[0][0];
        //screen.renderSprite(0*sprite.width,0*sprite.height,sprite);
        
        for( int x = 0; x < chunkMap.length; x++){
            for(int y = 0; y < chunkMap[x].length; y++){
                chunkMap[x][y] = new Chunk(this, x*256, y*256);
                System.out.println(x+" "+y);
            }
        }
        
    }
    
    public Chunk[][] loadMap(int X, int Y){
        Chunk[][] returnChunk = new Chunk[(int)(8/scale)][(int)(5/scale)];
        System.out.println(returnChunk.length+" "+returnChunk[0].length);
        for(int x=0;x<returnChunk.length;x++){
            for(int y=0;y<returnChunk[x].length;y++){
                returnChunk[x][y] = chunkMap[X+x][Y+y];
            }
        }
        return returnChunk;
    }
    
}
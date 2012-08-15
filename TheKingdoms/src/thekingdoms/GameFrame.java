/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
/**
 *
 * @author Michael
 */
public class GameFrame extends Canvas implements Runnable, MouseListener, KeyListener, ActionListener, MouseWheelListener {
    
    public static int height = TheKingdoms.HEIGHT;
    public static int width = TheKingdoms.WIDTH;
    public static int screenWidth = 1536;
    public static int screenHeight = 1024;
    public static int scale = 0;
    public static double scales[] = {0.5,1,2};
    public static int fps = 25;
    public static int period = 1000/fps;
    public int currentFps;
    
    public static GameFrame theGame = new GameFrame();
    
    public static final String NAME = "TheKingdoms";

    private BufferedImage image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    Random rand = new Random();
    
    private boolean running = false;
    
    public SpriteSheetLoader loader;
    public Screen screen;
    
    Chunk[][][][] chunkMap = new Chunk[16][][][];
    Chunk[][] renderMap;
    
    
    public GameFrame(){
        this.setIgnoreRepaint(true);
        this.setBounds(0,0,width,height);
        addMouseListener(this);
        addMouseWheelListener(this);
        //BufferedImage.
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
            /*System.out.println("current runtime free memory in mb: "+(Runtime.getRuntime().freeMemory()/1024/1024));
            System.out.println("current runtime max memory in mb: "+(Runtime.getRuntime().maxMemory()/1024/1024));
            System.out.println("current runtime total memory in mb: "+(Runtime.getRuntime().totalMemory()/1024/1024));*/
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
        try{
            for (int x = 0; x<renderMap.length; x++){
                for (int y = 0; y<renderMap[0].length; y++){
                    renderMap[x][y].renderTopTiles((x*256), (y*256));
                }
            }
        }catch(Exception e){
        }
        /*Sprite testSprite = Sprites.tiles[3][0][0];
        System.out.println(testSprite.width+" "+testSprite.height);
        System.out.println(testSprite.pixels.length+" "+(testSprite.width+(testSprite.height*testSprite.height)));
        screen.renderSprite(0, 0, testSprite);*/
        
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            requestFocus();
            return;
        }
        
        for( int y = 0; y < screen.height; y++){
            for(int x=0; x < screen.width; x++){
                pixels[x+(y*screen.width)] = screen.pixels[x+(y*screen.width)];
            }
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1920, 1080);
        g.drawImage(image,0,0,screenWidth,screenHeight,null);
        g.setColor(Color.BLUE);
        g.drawString("fps: "+currentFps,17,20);
        g.dispose();
        bs.show();
        
    }
    
    public void init(){
        
        for(int i: pixels){
            i = -65280;
        }
        
        loader = new SpriteSheetLoader();
        screen = new Screen(screenWidth, screenHeight, 16);
        System.out.println("current total memory: "+Runtime.getRuntime().totalMemory()/1024/1024);
        long tmp = Runtime.getRuntime().freeMemory();
        chunkMap[0] = ChunkManager.getChunkManager().loadChunks(0, 0, 0, (int)(screenWidth/(256*GameFrame.scales[GameFrame.scale])), (int)(screenHeight/(256*GameFrame.scales[GameFrame.scale])), 1);
        long tmp2 = tmp - Runtime.getRuntime().freeMemory();
        System.out.println("memory used: "+tmp2);
        double tmp3 = tmp2/28/1024;
        System.out.println("per chunk: "+tmp3);
        updateRenderMap(0,0);
    }
    
    public void updateRenderMap(int offsetX, int offsetY){
        renderMap = new Chunk[(int)(screenWidth/(256*GameFrame.scales[GameFrame.scale]))][(int)(screenHeight/(256*GameFrame.scales[GameFrame.scale]))];
        //System.out.println((int)(1920/(256*GameFrame.scales[GameFrame.scale]))+" "+(int)(1080/(256*GameFrame.scales[GameFrame.scale])));
        
        double ratioX = (double)renderMap.length/(double)chunkMap[0].length;
        double ratioY = (double)renderMap[0].length/(double)chunkMap[0][0].length;
        int startX = (int)((chunkMap[0].length/2) - ((chunkMap[0].length/2)*ratioX));
        int startY = (int)((chunkMap[0][0].length/2) - ((chunkMap[0][0].length/2)*ratioY));
        for(int i=0;i<renderMap.length;i++){
            for(int j=0;j<renderMap[i].length;j++){
                if(i+startX>=0&&i+startX<chunkMap[0].length&&j+startY>=0&&j+startY<chunkMap[0][0].length)renderMap[i][j] = chunkMap[0][i+startX][j+startY][0];
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
        
        }else if(me.getButton()==MouseEvent.BUTTON2){
            System.out.println(MouseEvent.MOUSE_WHEEL);
        }else if(me.getButton()==MouseEvent.BUTTON3){
    
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
        
        }else if(me.getButton()==MouseEvent.BUTTON2){
            System.out.println(MouseEvent.MOUSE_WHEEL);
        }else if(me.getButton()==MouseEvent.BUTTON3){
    
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
        
        }else if(me.getButton()==MouseEvent.BUTTON2){
            System.out.println(MouseEvent.MOUSE_WHEEL);
        }else if(me.getButton()==MouseEvent.BUTTON3){
    
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("keyTyped Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("keyPressed Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("keyReleased Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("actionPerformed Not supported yet.");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        if((mwe.getWheelRotation()==1&&scale<scales.length-1)||(mwe.getWheelRotation()==-1&&scale>0)){
            scale+=mwe.getWheelRotation();
            updateRenderMap(0,0);
        }
    }
    
}
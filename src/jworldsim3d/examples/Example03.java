package jworldsim3d.examples;

import jworldsim3d.classes.wEngine;
import jworldsim3d.classes.wFont;
import jworldsim3d.classes.wScene;
import jworldsim3d.classes.wWindow;
import jworldsim3d.jWorldSim3D;
import jworldsim3d.structs.wColor4s;
import jworldsim3d.structs.math.wVector2i;

/**
'' ----------------------------------------------------------------------------
'' Пример 03: Шрифты Bitmap (.bmp)
'' В этом примере для отображения текста на экране используется шрифт, основанный
'' на bitmap. Также можно использовать для этой цели формат .png
'' ----------------------------------------------------------------------------
 * @author Nikolas
 * @author Адаптация Vuvk
 */
public class Example03 {
    
    public static void main(String[] args) { 
        int prevFps = 0, curFps;
        String wndCaption = "Example 03: Bitmap Fonts ";
        
        jWorldSim3D.init();
        if (!wEngine.start()) {
            System.out.println("wEngineStart() failed!");
            return;
        }
        
        wFont BitmapFont          = new wFont("Assets/Fonts/thunder16.png");   
        wFont BitmapFont_2        = new wFont("Assets/Fonts/myfont4.png");   
        wFont BitmapFont_3        = new wFont("Assets/Fonts/papyrus_bold.png");   
        wFont BitmapFont_Cyrillic = new wFont("Assets/Fonts/Cyr.xml");    
            
        wVector2i fromPos = new wVector2i();
        wVector2i toPos   = new wVector2i();
       
        wColor4s textColor = new wColor4s(255, 255, 255, 255);
        
        while (wEngine.isRunning()) {
            wScene.begin();   
            
            ///Draw text info
            fromPos.y=70; toPos.y=86;
            textColor.red=255; textColor.green=0; textColor.blue=0;
            BitmapFont.draw("I'll be back!", fromPos,toPos,textColor);

            fromPos.y=100; toPos.y=116;
            textColor.red=0; textColor.green=255; textColor.blue=0;
            BitmapFont_2.draw("WorldSim3D", fromPos,toPos,textColor);

            fromPos.y=130; toPos.y=146;
            textColor.red=0; textColor.green=0; textColor.blue=255;
            BitmapFont_3.draw("Game Over", fromPos,toPos,textColor);

            fromPos.y=170; toPos.y=186;
            textColor.red=0; textColor.green=255; textColor.blue=255;
            BitmapFont_Cyrillic.draw("У меня получится сделать игру мечты!", fromPos,toPos,textColor);
            
            //wScene.drawAll();
            wScene.end();
            
            wEngine.closeByEsc();
            
            /// update FPS
            curFps = wEngine.getFps();
            if (prevFps != curFps) {
                prevFps = curFps;
                wWindow.setCaption(wndCaption + " FPS: " + curFps);
            }
        }
        
        wEngine.stop();
    }    
}

package jworldsim3d.examples;

import jworldsim3d.classes.wEngine;
import jworldsim3d.classes.wFont;
import jworldsim3d.classes.wScene;
import jworldsim3d.classes.wWindow;
import jworldsim3d.jWorldSim3D;
import jworldsim3d.structs.wColor4s;
import jworldsim3d.structs.math.wVector2i;

/**
'' Пример 01: Hello World
'' Этот простой пример открывает окно WorldSim3D, показывает текст Hello World
'' на экране и ожидает когда пользователь закроет приложение.
'' ----------------------------------------------------------------------------
 * @author Nikolas
 * @author Адаптация Vuvk
 */
public class Example01 {

    public static void main(String[] args) {  
        int prevFps = 0, curFps;   
        String wndCaption = "Example 01";
        
        jWorldSim3D.init();
        
        wEngine.start();
        
        wFont font = new wFont("Assets/Fonts/myfont.bmp");
        wVector2i fromPos  = new wVector2i(120, 80);
        wVector2i toPos    = new wVector2i(250, 96);
        wColor4s backColor = wColor4s.ORANGE;
        wColor4s fontColor = new wColor4s(255, 0, 125, 0);
                
        wWindow.setCaption(wndCaption);
        
        while (wEngine.isRunning()) {
            wScene.begin(backColor);
            wScene.drawAll();
            font.draw("Hello, Java!", fromPos, toPos, fontColor);
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

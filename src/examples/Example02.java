package examples;

import jworldsim3d.classes.wEngine;
import jworldsim3d.classes.wFont;
import jworldsim3d.classes.gui.wGui;
import jworldsim3d.classes.gui.wGuiLabel;
import jworldsim3d.classes.gui.wGuiScrollBar;
import jworldsim3d.classes.gui.wGuiSkin;
import jworldsim3d.classes.wScene;
import jworldsim3d.classes.wTexture;
import jworldsim3d.classes.wWindow;
import jworldsim3d.enums.gui.wGuiCallerType;
import jworldsim3d.enums.gui.wGuiDefaultColor;
import jworldsim3d.structs.gui.wGuiEvent;
import jworldsim3d.structs.wColor4s;
import jworldsim3d.structs.math.wVector2i;

/**
 * @author Адаптация Vuvk
 */
public class Example02 {
    
    public static void main(String[] args) { 
        final int GUI_SCROLLBAR_ALPHA = 101;
        final int GUI_SCROLLBAR_RED   = 102;
        final int GUI_SCROLLBAR_GREEN = 103;
        final int GUI_SCROLLBAR_BLUE  = 104;

        int prevFps = 0, curFps;
        String wndCaption = "Example 02: 2D Images / Sprites ";
        
        if (!wEngine.start()) {
            System.out.println("wEngineStart() failed!");
            return;
        }

        wFont myFont = new wFont("Cyr.xml");    
    
        ///Variables
        wTexture Planet          = new wTexture("planet_1.png");
        wTexture Alien_face      = new wTexture("Face_alien.png");
        wTexture Crosshair       = new wTexture("crosshair.png");
        wTexture Power_icon      = new wTexture("power.png");
        wTexture Teleport_icon   = new wTexture("teleport.png");
        wTexture Worldsim3d_logo = new wTexture("WS3D_Logo.png");
        
        wVector2i fromPos = new wVector2i();
        wVector2i toPos   = new wVector2i();
        wVector2i tempVec = new wVector2i();

        float rotation = 0;        
        
        wGuiSkin skin = wGui.getSkin();
        skin.setFont(myFont);
        skin.setColor(wGuiDefaultColor.wGDC_SCROLLBAR, new wColor4s(255, 100, 0, 0));
        skin.setColor(wGuiDefaultColor.wGDC_BUTTON_TEXT, new wColor4s(255, 100, 100, 0));

        ///Create Gui objects
        fromPos.x = 10; fromPos.y = 250;
        toPos.x = 50;   toPos.y = 280;        
        new wGuiLabel("A", fromPos, toPos);
        
        fromPos.x=50;fromPos.y=250;toPos.x=300;toPos.y=265;
        wGuiScrollBar scroll_alpha = new wGuiScrollBar(true,fromPos,toPos);
        scroll_alpha.setMaxValue(255);
        scroll_alpha.setValue(255);
        scroll_alpha.setId(GUI_SCROLLBAR_ALPHA);
                
        fromPos.x = 10; fromPos.y = 280;
        toPos.x = 50; toPos.y = 310;
        new wGuiLabel("R", fromPos, toPos);
        
        fromPos.x=50;fromPos.y=280;toPos.x=300;toPos.y=295;
        wGuiScrollBar scroll_red = new wGuiScrollBar(true,fromPos,toPos);
        scroll_red.setMaxValue(255);
        scroll_red.setValue(255);
        scroll_red.setId(GUI_SCROLLBAR_RED);   

        fromPos.x = 10; fromPos.y = 310;
        toPos.x = 50; toPos.y = 340;
        new wGuiLabel("G", fromPos, toPos);
        
        fromPos.x=50;fromPos.y=310;toPos.x=300;toPos.y=325;
        wGuiScrollBar scroll_green = new wGuiScrollBar(true,fromPos,toPos);
        scroll_green.setMaxValue(255);
        scroll_green.setValue(255);
        scroll_green.setId(GUI_SCROLLBAR_GREEN);      

        fromPos.x = 10; fromPos.y = 340;
        toPos.x = 50; toPos.y = 370;
        new wGuiLabel("B",fromPos,toPos);

        fromPos.x=50;fromPos.y=340;toPos.x=300;toPos.y=355;
        wGuiScrollBar scroll_blue = new wGuiScrollBar(true,fromPos,toPos);
        scroll_blue.setMaxValue(255);
        scroll_blue.setValue(255);
        scroll_blue.setId(GUI_SCROLLBAR_BLUE);   
        
        wColor4s color = new wColor4s(255, 255, 255, 255);
        
        while (wEngine.isRunning()) {
            wScene.begin();            
            
            fromPos.x=4;fromPos.y=4;
            Alien_face.draw(fromPos, true, color);
            
            rotation-=.1;
            fromPos.x=640;fromPos.y=140;
            toPos.x=640+128/2; toPos.y=140+128/2;
            Planet.draw(fromPos,toPos,rotation);

            tempVec.x=370;tempVec.y=270;
            fromPos.x=0;fromPos.y=0;
            toPos.x=60;toPos.y=60;
            Crosshair.draw(tempVec,fromPos,toPos,true,color);

            tempVec.x=10;tempVec.y=550;
            fromPos.x=0;fromPos.y=0;
            toPos.x=192;toPos.y=32;
            Power_icon.draw(tempVec,fromPos,toPos,true,color);

            tempVec.x=690;tempVec.y=548;
            fromPos.x=0;fromPos.y=0;
            toPos.x=95;toPos.y=32;
            Teleport_icon.draw(tempVec,fromPos,toPos,true,color);

            tempVec.x=270;tempVec.y=130;
            fromPos.x=0;fromPos.y=0;
            toPos.x=400;toPos.y=67;
            Worldsim3d_logo.draw(tempVec,fromPos,toPos,true,color);

            ///Draw all Gui objects
            wGui.drawAll();

            ///Gui events
            if(wGui.isEventAvailable()) {
                wGuiEvent GUIEvent = wGui.readEvent();
                if (GUIEvent != null) {
                    if (GUIEvent.event == wGuiCallerType.wGCT_SCROLL_BAR_CHANGED) {
                        switch (GUIEvent.id) {
                            case GUI_SCROLLBAR_ALPHA:
                                color.alpha = (short) scroll_alpha.getValue();
                                break;
                            case GUI_SCROLLBAR_BLUE:
                                color.blue = (short) scroll_blue.getValue();
                                break;
                            case GUI_SCROLLBAR_GREEN:
                                color.green = (short) scroll_green.getValue();
                                break;
                            case GUI_SCROLLBAR_RED:
                                color.red = (short) scroll_red.getValue();
                                break;
                        }
                    }                    
                }
            }
            
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

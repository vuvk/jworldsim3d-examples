package jworldsim3d.examples;

import jworldsim3d.classes.wCamera;
import jworldsim3d.classes.wEngine;
import jworldsim3d.classes.wFont;
import jworldsim3d.classes.wInput;
import jworldsim3d.classes.wMaterial;
import jworldsim3d.classes.wMesh;
import jworldsim3d.classes.wNode;
import jworldsim3d.classes.wScene;
import jworldsim3d.classes.wTexture;
import jworldsim3d.classes.wWindow;
import jworldsim3d.enums.input.wKeyCode;
import jworldsim3d.enums.wMaterialFlags;
import jworldsim3d.jWorldSim3D;
import jworldsim3d.structs.wColor4s;
import jworldsim3d.structs.math.wVector2i;
import jworldsim3d.structs.math.wVector3f;

/**
'' ----------------------------------------------------------------------------
'' Пример 04: 3D модели - Меши и Ноды (узлы)
'' Пример загружает модель в формате .x (directX) и добавляет её на сцену как Нод.
'' Нод - это физический объект для добавления на сцену моделей, камер, света и т.д.
'' ----------------------------------------------------------------------------
 * @author Nikolas
 * @author Адаптация Vuvk
 */
public class Example04 {
    
    public static void main(String[] args) { 
        int prevFps = 0, curFps;
        String wndCaption = "Example 03: Bitmap Fonts ";
        
        jWorldSim3D.init();
        if (!wEngine.start()) {
            System.out.println("wEngineStart() failed!");
            return;
        }
        
        wFont BitmapFont = new wFont("Assets/Fonts/3.png"); 
            
        wVector2i fromPos = new wVector2i();
        wVector2i toPos   = new wVector2i();
        wVector3f vec     = new wVector3f();
        wVector3f vec2    = new wVector3f();
       
        boolean isOutline = false;
        
        wColor4s testColor = wColor4s.BLACK;
        
        wTexture MeshTexture = new wTexture("Assets/Models/Characters/Bioshock_dude/bioshock dude.png");
        wTexture FloorTexture = new wTexture("Assets/Textures/Floor_2.jpg");
        wMesh CharacterMesh = new wMesh("Assets/Models/Characters/Bioshock_dude/Bioshock dude.x");
        wMesh FloorMesh = new wMesh("Assets/Models/Sci-fi_floor2.x");        
        
        ///Scene node
        wNode SceneNode = wNode.createFromMesh(CharacterMesh);
        vec.set(0.1f,0.1f,0.1f);
        SceneNode.setScale(vec);
        SceneNode.setAnimationSpeed(20);

        wMaterial mat = SceneNode.getMaterial(0);
        mat.setTexture(0, MeshTexture);
        mat.setFlag(wMaterialFlags.wMF_LIGHTING, false);

        ///Floor node
        wNode FloorNode = wNode.createFromMesh(FloorMesh);
        vec.set(0.0f,-0.2f,0.0f);
        FloorNode.setPosition(vec);

        /*
        for (int i=0; i < FloorNode.getMaterialsCount(); ++i) {
            mat = wNodeGetMaterial(FloorNode,i);
            wMaterialSetTexture(mat,0,FloorTexture);
            wMaterialSetType(mat,wMT_LIGHTMAP);
            wMaterialSetFlag(mat,wMF_LIGHTING,false);
        }*/

        ///Camera        
        vec.set(0.0f,10.0f,-10.0f);
        vec2.set(0.0f,5.0f,0.0f);
        wCamera OurCamera = new wCamera(vec,vec2); 
        
        /*
        ///Ambient scene color
        wSceneSetAmbientLight(wCOLOR4f_WHITE);
        */
        
        while (wEngine.isRunning()) {
            wScene.begin();  
                       
            wScene.drawAll();
            
            ///Draw text info
            fromPos.x = 270; fromPos.y = 20; 
            toPos.x = 400; toPos.y = 36;
            BitmapFont.draw("3D model with animation", fromPos, toPos );

            fromPos.x = 100; fromPos.y = 40;
            toPos.x = 600; toPos.y = 70;
            BitmapFont.draw("SPACE: ON/OFF outline mode     1...0: select color lines",fromPos, toPos);

            fromPos.x = 190; fromPos.y = 520; 
            toPos.x = 300; toPos.y = 536;
            BitmapFont.draw("WorldSim3D supports the following formats:",fromPos, toPos);
            fromPos.x = 25; fromPos.y = 540; 
            toPos.x = 100; toPos.y = 556;
            BitmapFont.draw("with bone-based (sceletal) or morph target animations - x, ms3d, b3d, md2", fromPos, toPos );
            fromPos.x = 25; fromPos.y = 560; 
            toPos.x = 400; toPos.y = 576;
            BitmapFont.draw("without bone-based or morph animations - 3ds, obj, lwo, dae, stl and other", fromPos, toPos );

            if (wInput.isKeyHit(wKeyCode.wKC_SPACE)) {
                isOutline = !isOutline;
            }
            
            if(isOutline) {
                //OutlineNode(SceneNode,4,testColor);
            }

            if (wInput.isKeyHit(wKeyCode.wKC_KEY_1)) testColor = wColor4s.RED;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_2)) testColor = wColor4s.GREEN;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_3)) testColor = wColor4s.BLUE;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_4)) testColor = wColor4s.WHITE;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_5)) testColor = wColor4s.BLACK;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_6)) testColor = wColor4s.YELLOW;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_7)) testColor = wColor4s.MAGENTA;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_8)) testColor = wColor4s.INDIGO;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_9)) testColor = wColor4s.GOLD;
            if (wInput.isKeyHit(wKeyCode.wKC_KEY_0)) testColor = wColor4s.SILVER; 
            
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

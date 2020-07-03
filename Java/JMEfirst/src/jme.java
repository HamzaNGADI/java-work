import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.*;
import com.jme3.math.*;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.*;

public class hellojme extends SimpleApplication {

	@Override
	public void simpleInitApp() {
			
		Box wall = new Box(2f,2f,1f);
		Spatial wi = new Geometry("Box",wall);
		Material brik = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		brik.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
		wi.setMaterial(brik);
		wi.setLocalTranslation(2f,-2f,0f);
		rootNode.attachChild(wi);
		
		guiNode.detachAllChildren();
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText txt = new BitmapText(guiFont,false);
		txt.setSize(guiFont.getCharSet().getRenderedSize());
		txt.setText("hay JME");
		txt.setLocalTranslation(300, txt.getLineHeight(), 0);
		guiNode.attachChild(txt);
		
		Spatial teapo = assetManager.loadModel("Models/Teapot/Teapot.obj");
		Material matdef = new Material(assetManager,"Common/MatDefs/Misc/ShowNormals.j3md");
		teapo.setMaterial(matdef);
		this.rootNode.attachChild(teapo);
		
		
		Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
		ninja.scale(0.05f,0.05f,0.05f);
		ninja.rotate(0,-3f,0);
		ninja.setLocalTranslation(0f, -5f, -2f);
		rootNode.attachChild(ninja);
		
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		hellojme j = new hellojme();
		j.start();

	}

}

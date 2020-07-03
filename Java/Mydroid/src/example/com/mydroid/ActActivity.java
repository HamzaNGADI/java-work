package example.com.mydroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class ActActivity extends Activity {
	
	ProgressBar barreProgression;
 
	 // Comme pour les threads classiques, nous gardons la trace de son activité.
	 boolean enExecution = false;
	 // Notre sous-classe Handler qui gère le traitement des messages.
	 final Handler handler = new Handler() {
	 @Override
	 public void handleMessage(Message msg) {
	 barreProgression.incrementProgressBy(10);
	 }
	 };
	 @Override
	 public void onCreate(Bundle icicle) {
	 super.onCreate(icicle);
	 setContentView(R.layout.act);
	 barreProgression = (ProgressBar) findViewById(R.id.barre_progression);
	 }
	 public void onStart() {
	 super.onStart();
	 barreProgression.setProgress(0); // Réinitialise la barre de progression.
	 // Création d’ un thread d’ arrière-plan qui envoie un message au handler
	 // toutes les secondes.
	 Thread threadArrierePlan = new Thread(new Runnable() {
	 public void run() {
	 try {
	 for (int i = 0; enExecution && i < 10; ++i) {
	 // Mise en pause d’ une seconde
	 Thread.sleep(1000);
	 // Création d’ un message vide
	 Message msg = handler.obtainMessage();
	 // Envoi du message au handler
	 handler.sendMessage(msg);
	 }
	 } catch (Throwable t) { }
	 }
	 });
	 enExecution = true;
	 // Lancement du thread d’ arrière-plan
	 threadArrierePlan.start();
	 }
	 public void onStop() {
	 super.onStop();
	 enExecution = false;
	 }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

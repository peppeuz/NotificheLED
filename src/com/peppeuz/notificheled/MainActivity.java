package com.peppeuz.notificheled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {
	int LED_NOTIFICATION_ID;
	Button led;
	NotificationManager nm;
	Button stop;
	Spinner listaColori;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listaColori = (Spinner) findViewById(R.id.listaColori);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		led = (Button) findViewById(R.id.led);
		stop = (Button) findViewById(R.id.stop);
		stop.setOnClickListener(this);
		led.setOnClickListener(this);
		LED_NOTIFICATION_ID = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void AccendiLed(int codiceColore) {
		// Toast.makeText(getApplicationContext(), "Yup",
		// Toast.LENGTH_LONG).show();

		Notification notif = new Notification();
		notif.flags = Notification.FLAG_SHOW_LIGHTS;
		notif.ledARGB = codiceColore;
		notif.ledOnMS = 100;
		notif.ledOffMS = 100;
		nm.notify(2303, notif);
	}

	public void StopLed() {
		if (nm != null) {
			nm.cancel(2303);
		}
	}

	public int assegnaColore() {
		int codiceColore = Color.BLUE;
		String colore = String.valueOf(listaColori.getSelectedItem());
		if (colore.equals("Blu")) {
			codiceColore = Color.BLUE;
		}
		if (colore.equals("Rosso")) {
			codiceColore = Color.RED;
		}
		if (colore.equals("Verde")) {
			codiceColore = Color.GREEN;
		}
		return codiceColore;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view == led) {
			int codiceColore = assegnaColore();

			AccendiLed(codiceColore);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder.setTitle("Spegni lo schermo");
			// inseriamo qui il titolo della Dialog Box
			alertDialogBuilder
					.setMessage(
							"Blocca lo schermo per vedere lampeggiare il LED")
					// inseriamo qui la domanda da porre all'utente
					.setCancelable(false)

					.setPositiveButton("ok",
							new DialogInterface.OnClickListener() {
								// scriviamo qui il testo del bottone per
								// annullare e l'azione da eseguire
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			// con questi due metodi viene effettivamente generato l'alert e
			// successivamente mostrato
		}

		if (view == stop)
			StopLed();
	}

}

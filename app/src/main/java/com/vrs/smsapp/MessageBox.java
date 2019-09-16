package com.vrs.smsapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MessageBox extends Activity implements OnClickListener {

	// GUI Widget
	Button btnInbox;
	TextView lblMsg, lblNo;
	ListView lvMsg;

	// Cursor Adapter
	SimpleCursorAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messagebox);

		// Init GUI Widget
		btnInbox = (Button) findViewById(R.id.btnInbox);
		btnInbox.setOnClickListener(this);

		lvMsg = (ListView) findViewById(R.id.lvMsg);

	}

	@Override
	public void onClick(View v) {

		if (v == btnInbox) {

			// Create Inbox box URI
			Uri inboxURI = Uri.parse("content://sms/inbox");

			// List required columns
			String[] reqCols = new String[] { "_id", "address", "body" };

			// Get Content Resolver object, which will deal with Content
			// Provider
			ContentResolver cr = getContentResolver();

			// Fetch Inbox SMS Message from Built-in Content Provider
			Cursor c = cr.query(inboxURI, reqCols, null, null, null);

			// Attached Cursor with adapter and display in listview
			adapter = new SimpleCursorAdapter(this, R.layout.row, c,
					new String[] { "body", "address" }, new int[] {
							R.id.lblMsg, R.id.lblNumber });
			lvMsg.setAdapter(adapter);

		}
	}
}

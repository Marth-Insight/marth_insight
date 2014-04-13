package com.spaceappschallenge.weatheronmars.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TriviaAdapter {

	private static final String DATABASE_NAME = "MarsWeather.db";
	private static final String DB_PATH = "/data/data/com.spaceappschallenge.weatheronmars/databases/";

	TriviaHelper tHelper;
	SQLiteDatabase db;
	Context mContext;

	public TriviaAdapter(Context context) {
		tHelper = TriviaHelper.getInstance(context);
		mContext = context;
	}

	public void open() throws SQLException {
		db = tHelper.getWritableDatabase();

	}

	public void close() {
		tHelper.close();
	}

	// Adds a trivia
	public long createTrivia() throws SQLException {
		return 0;
		// ContentValues values = new ContentValues();
		// values.put(TriviaContract.Trivia.COLUMN_NAME_TRIVIA_DESCRIPTION,
		// "trial");
		// values.put(TriviaContract.Trivia.COLUMN_NAME_TRIVIA_ID, "1");
		// return addRow(TriviaContract.Trivia.TABLE_NAME, values);
	}

	private long addRow(String tableName, ContentValues values) {
		return db.insert(tableName, null, values);
	}

	// Returns a Trivia
	public String TriviaResult() {
		String strTriviaDescription = null;

		String[] projection = { TriviaContract.Trivia._ID,
				TriviaContract.Trivia.COLUMN_NAME_TRIVIA_DESCRIPTION };
		String sortOrder = "RANDOM()";
		String limit = "1";

		Cursor cursor = db.query(TriviaContract.Trivia.TABLE_NAME, projection,
				null, null, null, null, sortOrder, limit);

		if (cursor.moveToNext()) {

			strTriviaDescription = cursor
					.getString(cursor
							.getColumnIndex(TriviaContract.Trivia.COLUMN_NAME_TRIVIA_DESCRIPTION));
		}

		return strTriviaDescription;
	}

	// Returns a Trivia based on category
	public String TriviaResult(String category, String minTemp, String maxTemp,
			String value) {

		String selection = null;
		String[] selectionArgs = null;

		// category = "Temperature";

		if (category == "Temperature") {
			selection = TriviaContract.Trivia.COLUMN_NAME_GROUP_NAME + " = '"
					+ category + "' AND "
					+ TriviaContract.Trivia.COLUMN_NAME_MAX + " > ? AND "
					+ TriviaContract.Trivia.COLUMN_NAME_MIN + " < ? AND "
					+ TriviaContract.Trivia.COLUMN_NAME_MAX + " > ? AND "
					+ TriviaContract.Trivia.COLUMN_NAME_MIN + " < ?";
			selectionArgs = new String[] { minTemp, minTemp, maxTemp, maxTemp };
		} else if (category == "General") {
			selection = TriviaContract.Trivia.COLUMN_NAME_GROUP_NAME + " = '"
					+ category + "'";
			selectionArgs = null;
		} else {
			selection = TriviaContract.Trivia.COLUMN_NAME_GROUP_NAME + " = '"
					+ category + "' AND "
					+ TriviaContract.Trivia.COLUMN_NAME_MAX + " > ? AND "
					+ TriviaContract.Trivia.COLUMN_NAME_MIN + " < ?";
			selectionArgs = new String[] { value, value };
		}

		String strTriviaDescription = null;

		String[] projection = { TriviaContract.Trivia._ID,
				TriviaContract.Trivia.COLUMN_NAME_TRIVIA_DESCRIPTION };
		/*
		 * selection = TriviaContract.Trivia.COLUMN_NAME_GROUP_NAME + " = " +
		 * category + " AND " + TriviaContract.Trivia.COLUMN_NAME_MAX + " < ? "
		 * + TriviaContract.Trivia.COLUMN_NAME_MIN + " > ?"; selectionArgs = new
		 * String[]{ "-69" };
		 */
		String sortOrder = "RANDOM()";
		String limit = "1";

		Cursor cursor = db.query(TriviaContract.Trivia.TABLE_NAME, projection,
				selection, selectionArgs, null, null, sortOrder, limit);

		if (cursor.moveToNext()) {

			strTriviaDescription = cursor
					.getString(cursor
							.getColumnIndex(TriviaContract.Trivia.COLUMN_NAME_TRIVIA_DESCRIPTION));

		}

		return strTriviaDescription;
	}

	public void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public static boolean checkDatabase(Context context) {
		File dbFile = new File(DB_PATH + DATABASE_NAME);
		return dbFile.exists();
	}

}

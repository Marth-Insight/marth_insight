package com.spaceappschallenge.weatheronmars.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TriviaHelper extends SQLiteOpenHelper {

	private static TriviaHelper instance = null;
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "MarsWeather.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String DOUBLE_TYPE = " DOUBLE";
	private static final String DATETIME_TYPE = " DATETIME";
	private static final String BOOL_TYPE = " BOOL";
	private static final String PRIMARY_KEY = " PRIMARY KEY";
	private static final String COMMA_SEP = ",";

	private static final String SQL_CREATE_TRIVIA = "CREATE TABLE "
			+ TriviaContract.Trivia.TABLE_NAME + " ("
			+ TriviaContract.Trivia._ID + INTEGER_TYPE + PRIMARY_KEY
			+ COMMA_SEP + TriviaContract.Trivia.COLUMN_NAME_TRIVIA_ID
			+ TEXT_TYPE + COMMA_SEP
			+ TriviaContract.Trivia.COLUMN_NAME_TRIVIA_DESCRIPTION + TEXT_TYPE
			+ COMMA_SEP + TriviaContract.Trivia.COLUMN_NAME_MIN + DOUBLE_TYPE
			+ COMMA_SEP + TriviaContract.Trivia.COLUMN_NAME_MAX + DOUBLE_TYPE
			+ COMMA_SEP + TriviaContract.Trivia.COLUMN_NAME_GROUP_NAME
			+ TEXT_TYPE + " )";

	private static final String SQL_DELETE_TRIVIA = "DROP TABLE IF EXISTS "
			+ TriviaContract.Trivia.TABLE_NAME;

	public static TriviaHelper getInstance(Context context) {
		if (instance == null) {
			instance = new TriviaHelper(context.getApplicationContext());
		}
		return instance;
	}

	public TriviaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_TRIVIA);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_DELETE_TRIVIA);
		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

}

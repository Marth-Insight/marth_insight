package com.spaceappschallenge.weatheronmars.dao;

import android.provider.BaseColumns;

public class TriviaContract{
	
	public static abstract class Trivia implements BaseColumns{
		public static final String TABLE_NAME="Trivia";
		
		public static final String COLUMN_NAME_TRIVIA_ID="trivia_id";
		public static final String COLUMN_NAME_TRIVIA_DESCRIPTION="trivia_description";
		public static final String COLUMN_NAME_GROUP_NAME = "group_name";
		public static final String COLUMN_NAME_MIN = "min";
		public static final String COLUMN_NAME_MAX = "max";		
	}
    
    private TriviaContract() {}

}

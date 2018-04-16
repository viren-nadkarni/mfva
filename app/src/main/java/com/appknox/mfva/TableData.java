package com.appknox.mfva;

import android.provider.BaseColumns;

/**
 * Created by manikanta on 14/04/18.
 */

public class TableData {

public TableData()

    {

    }

    public static  abstract class TableInfo implements BaseColumns
    {

        public  static final String USER_NAME = "user_name" ;
        public  static final String Row_id = "Row_id" ;

        public  static final String USER_PASS = "user_pass" ;

        public  static final String DATABASE_NAME = "user_info" ;

        public  static final String TABLE_NAME = "userdata_info" ;




    }
}

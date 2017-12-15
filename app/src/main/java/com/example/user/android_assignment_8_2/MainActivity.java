package com.example.user.android_assignment_8_2;
//Package objects contain version information about the implementation and specification of a Java package.
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    ListView listView;
    //Android ListView is a view which groups several items and display them in vertical scrollable list.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design
        ListView listView = (ListView) findViewById(R.id.listview);
        //creating the listview
        listView.setAdapter(new MyAdapter(this));
        //creating Adapter object

    }
    //Creating MyAdaper class it extend the BaseAdapter class
    class MyAdapter extends BaseAdapter {
        //BaseAdapter:Common base class of common implementation for an Adapter that can be used in both ListView (by implementing
        // the specialized ListAdapter interface) and Spinner (by implementing the specialized SpinnerAdapter interface).
        ArrayList<SingleRow> list;
        //creating array list
        Context context;
        //Interface to global information about an application environment
        MyAdapter(Context c){
            context=c;
            list=new ArrayList<SingleRow>();
            //using getResources method to pull Name and phonenumber
            Resources res= c.getResources();
            //The Android resource system keeps track of all non-code assets associated with an application.
            String[] name=res.getStringArray(R.array.Name);
            //get the name from the array which we declared in the string
            String[] phoneNumber=  res.getStringArray(R.array.PhoneNumber);
            for (int i=0;i<7;i++){
                //Creating single row object
                list.add( new SingleRow( name[i],phoneNumber[i]));
            }
        }

        @Override
        public int getCount() {
            return list.size();
            //it will take the count and return the list size
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
            //position of the list
        }

        @Override
        public long getItemId(int position) {
            return position;
            //id of every item with the position
        }

        public class SingleRow {
            String Name;

            public SingleRow(String name, String phoneNumber) {
                Name = name;
                PhoneNumber = phoneNumber;
            }

            String PhoneNumber;

        }//here we declare the values we need to print in the grid view

        //getview method it take cares of all the views
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // LayoutInflater :Instantiates a layout XML file into its corresponding View objects.
            //
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            //here it call all the methods we used for layoutinflater
            //inflater.inflate is used to create the view from our xml file
            View row=   inflater.inflate(R.layout.activity2,parent,false);
            TextView name= (TextView) row.findViewById(R.id.textView);
            TextView phoneNumber= (TextView) row.findViewById(R.id.textView2);
            SingleRow tep= list.get(position);
            //get's the position
            name.setText(tep.Name);
            //printing them
            phoneNumber.setText(tep.PhoneNumber);
            return row ;
            //returning them
        }
    }
}


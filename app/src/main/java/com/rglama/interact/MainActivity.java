package com.rglama.interact;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    public static final int PICK_CONTACT_REQUEST = 1;
    public static final int PICK_Photo_REQUEST = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        // Figure out what to do based on the intent type
        if (intent.getType().indexOf("image/") != -1) {
            // Handle intents with image data ...
        } else if (intent.getType().equals("text/plain")) {
            // Handle intents with text ...
        }

        Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
        setResult(this.RESULT_OK, result);
        finish();
    }


    public void sendMessage(View view){
        EditText editText= (EditText) findViewById(R.id.editText);
        String phoneNumber= editText.getText().toString();
        Log.d("test","Phone number is" + phoneNumber);

        //prepend the protocol
        phoneNumber="tel:" + phoneNumber;
        Log.d("test","URI is" + phoneNumber);

        Uri uri= Uri.parse(phoneNumber);
        Intent intent=new Intent(Intent.ACTION_DIAL,uri);

        startActivity(intent);

    }

    public void openMap(View view){
        EditText editText= (EditText) findViewById(R.id.mapedit);
        String gettext=editText.getText().toString();
        String uriString="geo:0,0?q="+ gettext;
        Uri location=Uri.parse(uriString);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);

    }
    public void openweb(View view){
        EditText editText= (EditText) findViewById(R.id.webedit);
        String websiteDomain=editText.getText().toString();
        Uri webpage=Uri.parse(websiteDomain);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(mapIntent);

    }


    public void pickContact(View view) {

        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK){
            if(requestCode==PICK_CONTACT_REQUEST){
                Uri contactUri = data.getData();
                //Log has show a ID for Contact
                Log.d("Check",contactUri.toString());
                String[]projection={ContactsContract.CommonDataKinds.Phone.NUMBER};

            }else if (requestCode==PICK_Photo_REQUEST){
                Uri photoUri=data.getData();
                Log.d("Check",photoUri.toString());

            }
        }
    super.onActivityResult(requestCode, resultCode, data);

    }

    public void pickPhoto(View view) {

        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(pickPhotoIntent, PICK_CONTACT_REQUEST);
    }





}

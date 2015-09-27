package silentassassins.ezcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Scanner;

public class MainActivity extends Activity implements
        CreateNdefMessageCallback, OnNdefPushCompleteCallback {

    TextView textInfo;

    NfcAdapter nfcAdapter;

    EditText name;
    EditText phone;
    EditText email;
    EditText company;
    EditText address;
    EditText fax;
    EditText website;
    EditText position;
    String[] information;
    String everything;
    Button buttonmy;
    String pho, ema, com, add, fa, web, pos;
    Scanner scanner;
    //private String INFOTEXT = "infotext.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        //SharedPreferences prefs = this.getSharedPreferences("com.silentassassins.ezcard", Context.MODE_PRIVATE);


//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("profile.txt").getFile());

//        try {
//            scanner = new Scanner(getString(R.string.profileString));
//            Log.i("TAG", "SCANNER MADE CORRECTLY");
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            Log.i("TAG", "FILE NOT FOUND EXCEPTION");
//        }

/*
        String longS = getPreferences(MODE_PRIVATE).getString(
                "profileString", "Jaun$Carlos%7033446313%jack.schefer9@gmail.com%CVENT%2500$E$Meredith$Dr.%7032420123%jack.com%Manager");
        Log.i("TAG", longS);
        String[] parts = longS.split("%");
        Log.i("TAG", longS);
        Log.i("TAG", parts.length + "");
        //scanner.close();
        for(int i = 0; i < parts.length; i++)
        {
            parts[i].replace("$", " ");
        }
        name = (EditText) findViewById(R.id.editText);
        name.setText(parts[0].replace("$", " "));
        phone = (EditText) findViewById(R.id.editText2);
        phone.setText(parts[1].replace("$", " "));
        email = (EditText) findViewById(R.id.editText3);
        email.setText(parts[2].replace("$", " "));
        company = (EditText) findViewById(R.id.editText4);
        company.setText(parts[3].replace("$", " "));
        address = (EditText) findViewById(R.id.editText5);
        address.setText(parts[4].replace("$", " "));
        fax = (EditText) findViewById(R.id.editText6);
        fax.setText(parts[5].replace("$", " "));
        website = (EditText) findViewById(R.id.editText7);
        website.setText(parts[6].replace("$", " "));
        position = (EditText) findViewById(R.id.editText9);
        position.setText(parts[7].replace("$", " ")); */

        //scanner.close();

        buttonmy = (Button) findViewById(R.id.button);
        buttonmy.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        name = (EditText) findViewById(R.id.editText);
                        String nam = name.getText().toString();
                        //name.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                        if(!nam.equals("Nikash Sethi")) {
                            phone = (EditText) findViewById(R.id.editText2);
                            pho = phone.getText().toString();
                            phone.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                            email = (EditText) findViewById(R.id.editText3);
                            ema = email.getText().toString();
                            email.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                            company = (EditText) findViewById(R.id.editText4);
                            com = company.getText().toString();
                            company.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                            address = (EditText) findViewById(R.id.editText5);
                            add = address.getText().toString();
                            address.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                            fax = (EditText) findViewById(R.id.editText6);
                            fa = fax.getText().toString();
                            fax.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                            website = (EditText) findViewById(R.id.editText7);
                            web = website.getText().toString();
                            website.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                            position = (EditText) findViewById(R.id.editText9);
                            pos = position.getText().toString();
                            position.setText(scanner.nextLine(), TextView.BufferType.EDITABLE);
                        //saveProfile();
                        }
                        else
                        {
                            pho = "571-325-6017";
                            phone = (EditText) findViewById(R.id.editText2);
                            phone.setText(pho, TextView.BufferType.EDITABLE);
                            ema = "nikashsethi@gmail.com";
                            email = (EditText) findViewById(R.id.editText3);
                            email.setText(ema, TextView.BufferType.EDITABLE);
                            com = "EzCard Incorporated";
                            company = (EditText) findViewById(R.id.editText4);
                            company.setText(com, TextView.BufferType.EDITABLE);
                            add = "3025 Franklin Corner Lane";
                            address = (EditText) findViewById(R.id.editText5);
                            address.setText(add, TextView.BufferType.EDITABLE);
                            fa = "571-111-1111";
                            fax = (EditText) findViewById(R.id.editText6);
                            fax.setText(fa, TextView.BufferType.EDITABLE);
                            web = "nikashsethi.org";
                            website = (EditText) findViewById(R.id.editText7);
                            website.setText(web, TextView.BufferType.EDITABLE);
                            pos = "CEO";
                            position = (EditText) findViewById(R.id.editText9);
                            position.setText(pos, TextView.BufferType.EDITABLE);


                        }

                        information = new String[8];
                        information[0] = nam;
                        information[1] = pho;
                        information[2] = ema;
                        information[3] = com;
                        information[4] = add;
                        information[5] = fa;
                        information[6] = web;
                        information[7] = pos;

                        everything = information[0];

                        for (int x = 1; x < 8; x++)
                            everything += "%" + information[x];


                        try {

                            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("infotext", MODE_APPEND));
                            out.write(everything);
                            out.close();

                        } catch (Throwable t) {

                        }
                        //saveProfile();

                    }

                }
        );

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {

        } else {

            nfcAdapter.setNdefPushMessageCallback(this, this);
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)){
            Parcelable[] parcelables =
                    intent.getParcelableArrayExtra(
                            NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage inNdefMessage = (NdefMessage)parcelables[0];
            NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
            NdefRecord NdefRecord_0 = inNdefRecords[0];
            String inMsg = new String(NdefRecord_0.getPayload());
            textInfo.setText(inMsg);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {

        final String eventString = "onNdefPushComplete\n" + event.toString();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

            }
        });

    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

        String stringOut = "hello";
        byte[] bytesOut = everything.getBytes();

        NdefRecord appRecord = NdefRecord.createMime("application/.", bytesOut);
//        NdefRecord ndefRecordOut = new NdefRecord(
//                NdefRecord.TNF_MIME_MEDIA,
//                everything.getBytes(),
//                new byte[] {},
//                bytesOut);

        //NdefRecord ndefRecordOut = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, everything.getBytes(), new byte[] {}, bytesOut);

        NdefMessage ndefMessageout = new NdefMessage(appRecord);

        //saveProfile();

        return ndefMessageout;

    }
//    public void saveProfile()
//    {
//        String longs = everything;
//        longs.replace(" ", "$");
//
//        Log.i("TAG", "before saving");
//        getPreferences(MODE_PRIVATE).edit().putString("profileString", longs).commit();
//        Log.i("TAG", "after saving");
//    }



}
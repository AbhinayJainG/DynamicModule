package com.abhi.dynamicmodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText nameEd;
    Button submiBtn, uninstallBtn, getBtn;
    CardView deepakCard, sukeshCard, venkatCard;
    private int mySessionId, mySessionId2, mySessionId3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEd = findViewById(R.id.name_ed);
        submiBtn = findViewById(R.id.submit);

        uninstallBtn = findViewById(R.id.uninstall);
        uninstallBtn.setVisibility(View.GONE);

        getBtn = findViewById(R.id.getbtn);

        deepakCard = findViewById(R.id.deepak_card);
        sukeshCard = findViewById(R.id.sukesh_card);
        venkatCard = findViewById(R.id.venkat_card);


        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SplitInstallManager splitInstallManager =
                        SplitInstallManagerFactory.create(MainActivity.this);


                Set<String> installedModules = splitInstallManager.getInstalledModules();
                Log.e(TAG, "Installed Modules count: " + installedModules.size());

                List<String> mods = new ArrayList<>();

                if (installedModules.size() > 0) {

                    for (Iterator<String> it = installedModules.iterator(); it.hasNext(); ) {
                        //Log.e(TAG, "onClick: "+it.next());
                        mods.add(it.next());
                    }
                    Log.e(TAG, "onClick Mods: " + mods);
                    Toast.makeText(MainActivity.this, "Installed:" + mods, Toast.LENGTH_SHORT).show();

                 /*   splitInstallManager.deferredUninstall(mods).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            deepakCard.setVisibility(View.GONE);
                            sukeshCard.setVisibility(View.GONE);
                            venkatCard.setVisibility(View.GONE);

                        }
                    });*/
                } else {
                    Toast.makeText(MainActivity.this, "Zero Modules installed..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deepakCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                try {
                    intent = new Intent(MainActivity.this,
                            Class.forName("com.abhi.deepak.DeepakActivity"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        sukeshCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                try {
                    intent = new Intent(MainActivity.this,
                            Class.forName("com.abhi.sukesh.SukeshActivity"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        venkatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    intent = new Intent(MainActivity.this,
                            Class.forName("com.abhi.venkat.VenkatActivity"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        final SplitInstallManager splitInstallManager =
                SplitInstallManagerFactory.create(MainActivity.this);


        if (splitInstallManager.getInstalledModules().contains("deepak")) {
            deepakCard.setVisibility(View.VISIBLE);
        } else {
            deepakCard.setVisibility(View.GONE);
        }

        if (splitInstallManager.getInstalledModules().contains("sukesh")) {
            sukeshCard.setVisibility(View.VISIBLE);
        } else {
            sukeshCard.setVisibility(View.GONE);
        }

        if (splitInstallManager.getInstalledModules().contains("venkat")) {
            venkatCard.setVisibility(View.VISIBLE);
        } else {
            venkatCard.setVisibility(View.GONE);
        }


        uninstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SplitInstallManager splitInstallManager =
                        SplitInstallManagerFactory.create(MainActivity.this);

                Set<String> installedModules = splitInstallManager.getInstalledModules();
                Log.e(TAG, "Installed Modules count: " + installedModules.size());

                List<String> mods = new ArrayList<>();

                if (installedModules.size() > 0) {

                    for (Iterator<String> it = installedModules.iterator(); it.hasNext(); ) {
                        //Log.e(TAG, "onClick: "+it.next());
                        mods.add(it.next());
                    }
                    Log.e(TAG, "onClick Mods: " + mods);

                    splitInstallManager.deferredUninstall(mods).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            deepakCard.setVisibility(View.GONE);
                            sukeshCard.setVisibility(View.GONE);
                            venkatCard.setVisibility(View.GONE);

                        }
                    });
                }
            }
        });

        submiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SplitInstallManager splitInstallManager =
                        SplitInstallManagerFactory.create(MainActivity.this);


                String nameVal = nameEd.getText().toString();

             //   Toast.makeText(MainActivity.this, "Value:" + nameVal, Toast.LENGTH_SHORT).show();

                if (nameVal.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Value cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (nameVal.equals("deepak")) {

                    SplitInstallRequest request =
                            SplitInstallRequest
                                    .newBuilder()
                                    .addModule("deepak")
                                    .build();

                    splitInstallManager.registerListener(new SplitInstallStateUpdatedListener() {
                        @Override
                        public void onStateUpdate(@NonNull SplitInstallSessionState state) {
                            if (state.sessionId() == mySessionId) {

                                switch (state.status()) {

                                    case SplitInstallSessionStatus.DOWNLOADING:
                                        /*Toast.makeText(MainActivity.this, "Hi, Please wait your " +
                                                "ondemand feature is set to download..",
                                                Toast.LENGTH_SHORT).show();*/
                                        break;
                                    case SplitInstallSessionStatus.INSTALLED:
                                        /*Toast.makeText(MainActivity.this, "Feature Is Installed",
                                                Toast.LENGTH_SHORT).show();*/
                                        deepakCard.setVisibility(View.VISIBLE);
                                        break;
                                    case SplitInstallSessionStatus.CANCELED:
                                        break;
                                    case SplitInstallSessionStatus.CANCELING:
                                        break;
                                    case SplitInstallSessionStatus.DOWNLOADED:
                                        break;
                                    case SplitInstallSessionStatus.FAILED:
                                        break;
                                    case SplitInstallSessionStatus.INSTALLING:
                                        break;
                                    case SplitInstallSessionStatus.PENDING:
                                        break;
                                    case SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION:
                                        break;
                                    case SplitInstallSessionStatus.UNKNOWN:
                                        break;
                                }
                            }
                        }
                    });

                    splitInstallManager.startInstall(request).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                         //   Toast.makeText(MainActivity.this, "Deepak Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Integer>() {
                        @Override
                        public void onSuccess(Integer result) {
                            mySessionId = result;
                          //  Toast.makeText(MainActivity.this, "Deepak Success: " + result, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Integer>() {
                        @Override
                        public void onComplete(@NonNull Task<Integer> task) {
                           // Toast.makeText(MainActivity.this, "Deepak Completed: " + task, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (nameVal.equals("sukesh")) {


                    SplitInstallRequest request2 =
                            SplitInstallRequest
                                    .newBuilder()
                                    .addModule("sukesh")
                                    .build();

                    splitInstallManager.registerListener(new SplitInstallStateUpdatedListener() {
                        @Override
                        public void onStateUpdate(@NonNull SplitInstallSessionState state) {
                            if (state.sessionId() == mySessionId2) {


                            switch (state.status()) {

                                case SplitInstallSessionStatus.DOWNLOADING:
                                    Log.d(TAG, "onStateUpdate: downloading");
                                   /* Toast.makeText(MainActivity.this, "Hi Sukesh, Please wait " +
                                            "your ondemand feature is set to download..", Toast.LENGTH_SHORT).show();*/
                                    break;
                                case SplitInstallSessionStatus.INSTALLED:
                                    Log.d(TAG, "onStateUpdate: Installed");
                                    //  Toast.makeText(MainActivity.this, "Sukesh is installed", Toast.LENGTH_SHORT).show();
                                    sukeshCard.setVisibility(View.VISIBLE);
                                    break;
                                case SplitInstallSessionStatus.CANCELED:
                                    Log.d(TAG, "onStateUpdate: Cancelled");
                                    break;
                                case SplitInstallSessionStatus.CANCELING:
                                    Log.d(TAG, "onStateUpdate: Cancelling");
                                    break;
                                case SplitInstallSessionStatus.DOWNLOADED:
                                    Log.d(TAG, "onStateUpdate: Downloaded");
                                    break;
                                case SplitInstallSessionStatus.FAILED:
                                    Log.d(TAG, "onStateUpdate: Failed");
                                    break;
                                case SplitInstallSessionStatus.INSTALLING:
                                    Log.d(TAG, "onStateUpdate: Installing");
                                    break;
                                case SplitInstallSessionStatus.PENDING:
                                    Log.d(TAG, "onStateUpdate: Pending");
                                    break;
                                case SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION:
                                    Log.d(TAG, "onStateUpdate: user confirmation");
                                    break;
                                case SplitInstallSessionStatus.UNKNOWN:
                                    Log.d(TAG, "onStateUpdate: Unknown");
                                    break;
                            }
                            }

                        }
                    });

                    splitInstallManager.startInstall(request2).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                           // Toast.makeText(MainActivity.this, "Sukesh Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Integer>() {
                        @Override
                        public void onSuccess(Integer result) {
                            mySessionId2 = result;
                          //  Toast.makeText(MainActivity.this, "Sukesh Success: " + result, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Integer>() {
                        @Override
                        public void onComplete(@NonNull Task<Integer> task) {
                           // Toast.makeText(MainActivity.this, "Sukesh Completed: " + task, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (nameVal.equals("venkat")) {

                    SplitInstallRequest request3 =
                            SplitInstallRequest
                                    .newBuilder()
                                    .addModule("venkat")
                                    .build();

                    splitInstallManager.registerListener(new SplitInstallStateUpdatedListener() {
                        @Override
                        public void onStateUpdate(@NonNull SplitInstallSessionState state) {
                            if (state.sessionId() == mySessionId3){

                            switch (state.status()) {

                                case SplitInstallSessionStatus.DOWNLOADING:
                                    /*Toast.makeText(MainActivity.this, "Hi Venkat, Please wait " +
                                            "your ondemand feature is set to download..", Toast.LENGTH_SHORT).show();*/
                                    break;
                                case SplitInstallSessionStatus.INSTALLED:
                                  //  Toast.makeText(MainActivity.this, "Venkat is installed", Toast.LENGTH_SHORT).show();
                                    //  if (splitInstallManager.getInstalledModules().contains("venkat")) {
                                    venkatCard.setVisibility(View.VISIBLE);
                                    // }
                                    break;
                                case SplitInstallSessionStatus.CANCELED:
                                    break;
                                case SplitInstallSessionStatus.CANCELING:
                                    break;
                                case SplitInstallSessionStatus.DOWNLOADED:
                                    break;
                                case SplitInstallSessionStatus.FAILED:
                                    break;
                                case SplitInstallSessionStatus.INSTALLING:
                                    break;
                                case SplitInstallSessionStatus.PENDING:
                                    break;
                                case SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION:
                                    break;
                                case SplitInstallSessionStatus.UNKNOWN:
                                    break;
                            }
                            }
                        }
                    });

                    splitInstallManager.startInstall(request3).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                          //  Toast.makeText(MainActivity.this, "Venkat Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Integer>() {
                        @Override
                        public void onSuccess(Integer result) {
                            mySessionId3 = result;
                          //  Toast.makeText(MainActivity.this, "Venkat Success: " + result, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Integer>() {
                        @Override
                        public void onComplete(@NonNull Task<Integer> task) {
                         //   Toast.makeText(MainActivity.this, "Venkat Completed: " + task, Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    Toast.makeText(MainActivity.this, "Value should be either of this three: \n" +
                            "deepak \n" +
                            "sukesh \n " +
                            "venkat", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}

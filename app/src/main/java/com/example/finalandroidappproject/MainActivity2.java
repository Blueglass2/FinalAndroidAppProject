package com.example.finalandroidappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity {
    // reference to entire database
    private static final String TAG = "EmailPassword";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Get Firebase Instance
        auth = FirebaseAuth.getInstance();
    }

    //https://firebase.google.com/docs/auth/android/password-auth?authuser=0
    public void signUp(View v) {

        EditText emailEditText = (EditText) findViewById(R.id.textEmailAddress);
        EditText passEditText = (EditText) findViewById(R.id.textPassword);

        String email = emailEditText.getText().toString();
        String password = passEditText.getText().toString();

        Log.e("KIM", "Typing " + email + " and " + password);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity2.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    /**
     * This method will be called to minimize the on screen keyboard in the Activity
     * When we get the current view, it is the view that has focus, which is the keyboard
     * Credit - Found and suggested by Ram Dixit, 2019
     *
     * Source:  https://www.youtube.com/watch?v=CW5Xekqfx3I
     */
    private void closeKeyboard() {
        View view = this.getCurrentFocus();     // view will refer to the keyboard
        if (view != null ){                     // if there is a view that has focus
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Source: https://suragch.medium.com/making-an-alertdialog-in-android-2045381e2edb
     * public void showAlertDialogue(View view, String str){
     *         AlertDialog.Builder builder = new AlertDialog.Builder(this);
     *         builder.setMessage(str);
     *         builder.setPositiveButton("OK", null);
     *         AlertDialog dialog = builder.create();
     *         dialog.show();
     *     }
     */

}
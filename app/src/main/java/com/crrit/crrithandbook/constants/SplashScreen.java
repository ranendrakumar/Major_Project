package com.crrit.crrithandbook.constants;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.crrit.crrithandbook.R;
import com.crrit.crrithandbook.databinding.ActivitySplashScreenBinding;
import com.crrit.crrithandbook.users.admin.SelectActivityAdmin;
import com.crrit.crrithandbook.users.faculty.SelectActivityFaculty;
import com.crrit.crrithandbook.users.student.SelectActivityStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        //full screen
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(binding.getRoot());
        Animation logoAnimation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.zoom_animation);
        Animation appnameAnimation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.zoom_animation);

        binding.imageView.setVisibility(View.VISIBLE);
        binding.imageView.startAnimation(logoAnimation);

        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                binding.appname.setVisibility(View.VISIBLE);
                binding.appname.startAnimation(appnameAnimation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


        firebaseAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkConn();
                checkUser();
            }
        },1000);
    }

//    private void checkUser() {
//        //get current user if logged
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser == null){
//            //user not logged in
//            startActivity(new Intent(SplashScreen.this, MainActivity.class));
//            finish();
//        }
//        else {
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//            ref.child(firebaseUser.getUid())
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot snapshot) {
//                            //progressDialog.dismiss();
//                            //get user type
//                            String userType = ""+snapshot.child("userType").getValue();
//                            //check user type
//                            if (userType.equals("user")){
//                                //this is for student open student dashboard
//                                startActivity(new Intent(SplashScreen.this, DashboardStudentActivity.class));
//                                finish();
//                            }
//                            else if (userType.equals("admin")){
//                                //this is for admin open admin dashboard
//                                startActivity(new Intent(SplashScreen.this, DashboardAdminActivity.class));
//                                finish();
//                            }
//
////                            else if (userType.equals("faculty")){
////                                //this is for faculty open faculty dashboard
////                                startActivity(new Intent(SplashScreen.this, DashboardFacultyActivity.class));
////                                finish();
////                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError error) {
//                        }
//                    });
//        }
//    }



    private void checkUser() {
        //get current user if logged
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            //user not logged in
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }
        else {
            //if user logged in ,check the type
            //check in DB
            // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            //Toast.makeText(SplashScreen.this, "Connect To The Network", Toast.LENGTH_SHORT).show();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(firebaseUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            //progressDialog.dismiss();
                            //get user type
                            String userType = ""+snapshot.child("userType").getValue();
                            //check user type
                            if (userType.equals("user")){
                                //this is for student open student dashboard
                                startActivity(new Intent(SplashScreen.this, SelectActivityStudent.class));
                                finish();
                            }
                            else if (userType.equals("admin")){
                                //this is for admin open admin dashboard
                                startActivity(new Intent(SplashScreen.this, SelectActivityAdmin.class));
                                finish();
                            }

                            else if (userType.equals("faculty")){
                                //this is for faculty open faculty dashboard
                                startActivity(new Intent(SplashScreen.this, SelectActivityFaculty.class));
                                finish();
                            }

                            //for error handling

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Toast.makeText(SplashScreen.this, "Connect To The Network", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }
    public void  checkConn(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (null!=networkInfo){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                //Toast.makeText(this, "WIFI", Toast.LENGTH_SHORT).show();
                //checkUser();
            }
            if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                //Toast.makeText(this, "MOBILE", Toast.LENGTH_SHORT).show();
                //checkUser();
            }
        }
        else {
            Toast.makeText(this, "PLEASE CONNECT TO THE INTERNET", Toast.LENGTH_SHORT).show();
            //binding.nointernet.setVisibility(View.VISIBLE);
            //startActivity(new Intent(SplashScreen.this, NoInternet.class));
            //finish();

        }
    }

}
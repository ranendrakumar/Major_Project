package com.crrit.crrithandbook.users.faculty;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.crrit.crrithandbook.R;
import com.crrit.crrithandbook.databinding.ActivitySemLessonplansFacultyBinding;
import com.crrit.crrithandbook.lessonplans11.DashboardFacultyActivitylp11;

public class SemLessonplansFacultyActivity extends AppCompatActivity {
    private ActivitySemLessonplansFacultyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primary));
            // window.setNavigationBarColor(this.getResources().getColor(R.color.blue));
        }
        super.onCreate(savedInstanceState);
        binding = ActivitySemLessonplansFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.Btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SemLessonplansFacultyActivity.this, DashboardFacultyActivitylp11.class));
            }
        });

    }
}
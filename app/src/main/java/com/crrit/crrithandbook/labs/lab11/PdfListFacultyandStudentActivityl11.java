package com.crrit.crrithandbook.labs.lab11;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crrit.crrithandbook.R;
import com.crrit.crrithandbook.databinding.ActivityPdfListFacultyandStudentActivityl11Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PdfListFacultyandStudentActivityl11 extends AppCompatActivity {

    private ActivityPdfListFacultyandStudentActivityl11Binding binding;

    private ArrayList<ModelPdfl11> pdfArrayList;
    private AdapterPdfFacultyandStudentl11 adapterPdfFacultyandStudentl11;
    private String categoryId,categoryTitle;
    private static final String TAG = "PDF_LIST_TAG";

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
        binding = ActivityPdfListFacultyandStudentActivityl11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        categoryId =intent.getStringExtra("categoryId");
        categoryTitle =intent.getStringExtra("categoryTitle");

        binding.subTitleTv.setText(categoryTitle);

        loadPdfList();

        //search
        binding.searchEtl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    adapterPdfFacultyandStudentl11.getFilter().filter(s);
                }
                catch (Exception e){
                    Log.d(TAG, "onTextChanged: "+e.getMessage());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadPdfList() {
        pdfArrayList = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Labs");
        ref.orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pdfArrayList.clear();
                        for (DataSnapshot ds: snapshot.getChildren()){
                            ModelPdfl11 model = ds.getValue(ModelPdfl11.class);
                            pdfArrayList.add(model);

                        }
                        // adapterPdfFaculty = new AdapterPdfFaculty(PdfListFacultyActivity.this,pdfArrayList);
                        adapterPdfFacultyandStudentl11 = new AdapterPdfFacultyandStudentl11(PdfListFacultyandStudentActivityl11.this,pdfArrayList);
                        binding.labRv.setAdapter(adapterPdfFacultyandStudentl11);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
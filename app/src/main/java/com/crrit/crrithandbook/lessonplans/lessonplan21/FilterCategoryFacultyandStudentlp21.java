package com.crrit.crrithandbook.lessonplans.lessonplan21;

import android.widget.Filter;

import com.crrit.crrithandbook.models.ModelCategorylp21;

import java.util.ArrayList;

public class FilterCategoryFacultyandStudentlp21 extends Filter {

    ArrayList<ModelCategorylp21> filterList;

    AdapterCategoryFacultyandStudentlp21 adapterCategoryFacultyandStudentlp21;

    public FilterCategoryFacultyandStudentlp21(ArrayList<ModelCategorylp21> filterList, AdapterCategoryFacultyandStudentlp21 adapterCategoryFacultyandStudentlp21) {
        this.filterList = filterList;
        this.adapterCategoryFacultyandStudentlp21 = adapterCategoryFacultyandStudentlp21;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if(constraint !=null&&constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelCategorylp21> filterModels = new ArrayList<>();
            for(int i=0;i<filterList.size();i++){
                if (filterList.get(i).getCategory().toUpperCase().contains(constraint)){
                    filterModels.add(filterList.get(i));

                }
            }

            results.count = filterModels.size();
            results.values = filterModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {
        adapterCategoryFacultyandStudentlp21.categoryArrayList1 = (ArrayList<ModelCategorylp21>)results.values;
        adapterCategoryFacultyandStudentlp21.notifyDataSetChanged();
    }
}

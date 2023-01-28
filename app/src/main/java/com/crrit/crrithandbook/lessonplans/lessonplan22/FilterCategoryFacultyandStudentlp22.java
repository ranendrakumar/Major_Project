package com.crrit.crrithandbook.lessonplans.lessonplan22;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterCategoryFacultyandStudentlp22 extends Filter {

    ArrayList<ModelCategorylp22> filterList;

    AdapterCategoryFacultyandStudentlp22 adapterCategoryFacultyandStudentlp22;

    public FilterCategoryFacultyandStudentlp22(ArrayList<ModelCategorylp22> filterList, AdapterCategoryFacultyandStudentlp22 adapterCategoryFacultyandStudentlp22) {
        this.filterList = filterList;
        this.adapterCategoryFacultyandStudentlp22 = adapterCategoryFacultyandStudentlp22;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if(constraint !=null&&constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelCategorylp22> filterModels = new ArrayList<>();
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
        adapterCategoryFacultyandStudentlp22.categoryArrayList1 = (ArrayList<ModelCategorylp22>)results.values;
        adapterCategoryFacultyandStudentlp22.notifyDataSetChanged();
    }
}

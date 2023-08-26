package com.example.traveltripapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.admin.fragment.AddCateFragment;
import com.example.traveltripapplication.data.repository.CateRepository;
import com.example.traveltripapplication.model.CategoryModel;

import java.util.concurrent.CompletableFuture;

public class AddCateViewModel extends BaseObservable {
  private CategoryModel categoryModel = new CategoryModel();
  @Bindable
  public CategoryModel getCategoryModel() {
    return categoryModel;
  }

  public void setCategoryModel(CategoryModel categoryModel) {
    this.categoryModel = categoryModel;
    notifyPropertyChanged(BR.categoryModel);
  }
  private addCateViewModelListener listener;

  public AddCateViewModel(addCateViewModelListener listener) {
    this.listener = listener;
  }

  public void onClickButtonAdd() {
    CompletableFuture<Long> addCateFuture = CateRepository.createCate(categoryModel);
    addCateFuture.thenAcceptAsync(result -> {
      categoryModel.setCateID(result);
      if (result >= 0) {
        listener.successAddCate(categoryModel);
        AddCateFragment.checkCreateCate = true;
      }
    });
  }

  public interface addCateViewModelListener {
    public void successAddCate(CategoryModel categoryModel);
  }
}

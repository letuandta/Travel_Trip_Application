package com.example.traveltripapplication.customer.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.customer.adapter.TourTicketAdapter;
import com.example.traveltripapplication.databinding.FragmentDetailTicketBinding;
import com.example.traveltripapplication.model.TourTicketModel;
import com.example.traveltripapplication.data.repository.TourRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TourTicketFragment extends Fragment implements TourTicketAdapter.TourTicketAdapterListener {

    FragmentDetailTicketBinding binding;
    long tour_id;

    public TourTicketFragment(long tour_id) {
        this.tour_id = tour_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailTicketBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<TourTicketModel> tourTicketModels = new ArrayList<>();
        tourTicketModels.add(new TourTicketModel());

        //Repository
        CompletableFuture<ArrayList<TourTicketModel>> future = TourRepository.getTicketByTourId(tour_id);
        future.thenAccept(result -> {
            tourTicketModels.clear();
            tourTicketModels.addAll(result);
        });
        try {
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e("get tour ticket error", "getTourTicket: " + e.getMessage());
        }
        //end

        //recycle view
        TourTicketAdapter adapter = new TourTicketAdapter(tourTicketModels, this);
        binding.rcvTicket.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rcvTicket.setAdapter(adapter);
        //end


        //register event
        binding.btnOrder.setOnClickListener(v -> {
            Toast.makeText(getContext(), "select " + adapter.getOrderTicket().size(), Toast.LENGTH_SHORT).show();
            CompleteInfoCustomerDialogFragment dialog = CompleteInfoCustomerDialogFragment.newInstance(tour_id, adapter.getOrderTicket());
            dialog.show(getParentFragmentManager(), "complete_customer_info");
        });
        //end
    }
}

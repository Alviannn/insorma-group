package dev.juviga.insorma.ui.transaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.Transaction;
import dev.juviga.insorma.data.repository.TransactionRepository;
import dev.juviga.insorma.data.repository.UserRepository;
import dev.juviga.insorma.data.shared.SharedData;
import dev.juviga.insorma.ui.adapter.TransactionDataAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionPage.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionFragment newInstance(String param1, String param2) {
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView transactionContainer;
    public static List<Transaction> transactions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_page, container, false);

        transactionContainer = view.findViewById(R.id.transactionContainer);

        SharedPreferences sp = this.getActivity().getSharedPreferences("LOGGED_IN_USER", Context.MODE_PRIVATE);
        String loggedInUsername = sp.getString("username", "");

        UserRepository userRepo = SharedData.USER_REPOSITORY;
        int idUser = userRepo.findByUsername(loggedInUsername).getId();

        TransactionRepository transactionRepository = new TransactionRepository();
        transactions = transactionRepository.findAllByUserId(idUser, true);

        TransactionDataAdapter adapter = new TransactionDataAdapter(getContext(), transactions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        transactionContainer.setLayoutManager(linearLayoutManager);
        transactionContainer.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }
}
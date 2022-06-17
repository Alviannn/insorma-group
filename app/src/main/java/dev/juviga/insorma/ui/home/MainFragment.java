package dev.juviga.insorma.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.Product;
import dev.juviga.insorma.data.repository.ProductRepository;
import dev.juviga.insorma.services.furnitures.FurnitureService;
import dev.juviga.insorma.services.furnitures.FurnituresCallback;
import dev.juviga.insorma.ui.adapter.ProductDataAdapter;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPage.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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

    RecyclerView productContainer;
    public static List<Product>products = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        productContainer = view.findViewById(R.id.productContainer);
        TextView tvEmptyProduct = view.findViewById(R.id.tv_empty_product);

        Log.d("Test fragment", "fragment Main");

        //take data from db
        ProductRepository productRepository = new ProductRepository();

        products = productRepository.findAll();

        Log.d("test size", String.valueOf(products.size()));

        if (products.isEmpty()) {
            tvEmptyProduct.setVisibility(View.VISIBLE);
            productContainer.setVisibility(View.INVISIBLE);
        } else {
            ProductDataAdapter productDataAdapter = new ProductDataAdapter(getContext(), products);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
            productContainer.setLayoutManager(gridLayoutManager);
            productContainer.setAdapter(productDataAdapter);

            productDataAdapter.notifyDataSetChanged();
        }
        return view;
    }
}
package com.example.typsy.ui.screens.conversionList;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.typsy.R;
import com.example.typsy.Util;
import com.example.typsy.data.local.entity.Conversion;
import com.example.typsy.ui.BaseAdapter;
import com.example.typsy.ui.RecyclerItemTouchHelper;
import com.example.typsy.ui.screens.conversionDetail.ConversionDialogFragment;
import com.example.typsy.ui.screens.newConversion.NewConversionFragment;

import java.util.List;

public class ConversionListFragment extends Fragment implements BaseAdapter.ClickListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    // private fields declaration
    private FragmentManager mFragmentManager;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private BaseAdapter mAdapter;
    private List<Conversion> mConversions;

    private ConversionListViewModel mViewModel;

    // creates a new instance of the fragment
    public static ConversionListFragment newInstance() {
        return new ConversionListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        // creating an instance of ConversionListViewModel from ViewModelProviders
        mViewModel = ViewModelProviders.of(this).get(ConversionListViewModel.class);

        // initializing fields
        mFragmentManager = getActivity().getSupportFragmentManager();
        mRecyclerView = view.findViewById(R.id.home_recycler_view);
        mFab = view.findViewById(R.id.home_fab);
        mCoordinatorLayout = view.findViewById(R.id.coordinator);
        mToolbar = view.findViewById(R.id.home_toolbar);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BaseAdapter(getActivity(), R.layout.list_item_conversion, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mFab.setOnClickListener(view1 -> {
            NewConversionFragment fragment = NewConversionFragment.newInstance();
            Util.replaceFragmentInActivity(mFragmentManager, fragment);
        });

        // observe conversion list
        mViewModel.getConversionList().observe(this, conversions -> {
            mConversions = conversions;
            mAdapter.add(mConversions);
        });

        return view;
    }

    // on swiped - deletes or restores conversion
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof BaseAdapter.ConversionViewHolder) {
            final Conversion deletedItem = mConversions.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            mAdapter.removeItem(viewHolder.getAdapterPosition());
            mViewModel.deleteConversion(deletedItem);
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, R.string.snack_delete, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.undo, view -> {
                mAdapter.restoreItem(deletedItem, deletedIndex);
                List<Conversion> conversions = mAdapter.getConversions();
                mViewModel.saveConversionList(conversions);
            });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }
    }

    // on card clicked - opens a conversion dialog
    @Override
    public void onCardClicked(int position) {
        Conversion conversion = mConversions.get(position);
        String id = String.valueOf(conversion.getId());
        ConversionDialogFragment fragment = ConversionDialogFragment.newInstance(id);
        Util.replaceFragmentInActivity(mFragmentManager, fragment);
    }
}
